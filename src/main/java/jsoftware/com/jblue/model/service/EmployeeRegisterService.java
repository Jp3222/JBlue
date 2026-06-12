package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 * Servicio maestro encargado de coordinar el caso de uso compuesto: Registro de
 * Empleados. Mantiene el aislamiento transaccional y la sincronización de
 * bitácoras en JBlue.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-04-22
 * @version 1.7
 */
public class EmployeeRegisterService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final EmployeeService employee;
    private final EmployeeUserService user;
    private final HistoryDAO hys;
    private final TransactionHistoryService transaction;

    public EmployeeRegisterService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        this.employee = new EmployeeService(dev_flag, process_name);
        this.user = new EmployeeUserService(dev_flag, process_name);
        this.hys = HistoryDAO.getInstance();
        this.transaction = new TransactionHistoryService(dev_flag, process_name);
    }

    public boolean insert(JDBConnection connection, EmployeeRegisterWrapperDTO dto) {
        // Corrección: Inicialización por defecto para evitar errores de compilación al testear la interfaz
        boolean commitSuccess = false;
        /**
         * SE VERIFICA SI EL PROGRAMA ESTA EN SOLO LECTURA
         */
        if (AppConfig.isDevMessages()) {
            System.out.println(dto.toString());
        }
        //SI EL SISTEMA ESTA EN SOLO LECTURA NO REALIZA REGISTRO ALGUNO
        if (AppConfig.getParameterBoolean("SOLO_LECTURA")) {
            returnMessageError("EL SISTEMA ESTA EN MODO LECTURA");
            return false;
        }
        // PASO 1: REGISTRO EN HISTORIAL DE TRANSACCIONES
        int transaction_id = transaction.insert(connection, dto.getTransaction());
        if (transaction_id <= 0) {
            returnMessageError(-1, "LA OPERACION NO SE PUDO REGISTRAR EN BITACORA MAESTRA");
            return false;
        }

        try {
            connection.setAutoCommit(false);
            //PASO 2: REGISTRO DE INICIO DE UNA TRANSACCION
            int start_id = hys.startTransactionReturn(connection, Const.INDEX_HYS_PROGRAM_HISTORY, "INICIO DE UNA TRANSACCION - EMP");
            if (start_id <= 0) {
                throw new ServiceException(1, "REGISTRO EN BITACORA CORRUPTO - START_TRANSACTION");
            }
            //PASO 2.1: RECUPERACION DE ID - INICIO DE LA TRANSACCION
            dto.getTransaction().put("hys_start_id", String.valueOf(start_id));

            //PASO 3: REGISTRO DE DATOS DEL EMPLEADO
            int employee_id = employee.insert(connection, dto.getEmployee());
            if (employee.isError()) {
                throw new ServiceException(employee.getErrorCode(), employee.getUserMessage());
            }

            //PASO 3.1: RECOPILACION DE DATOS
            dto.getEmployee_user().put("employee_id", String.valueOf(employee_id));
            dto.getEmployee_user().put("office_id", session.getCurrent_instance().getOfficeId());
            dto.getEmployee_user().put("description", dto.getEmployee().toString());
            dto.getEmployee_user().put("last_employee_update", session.getCurrentEmployee().getId());

            //PASO 4: REGISTRO DE DATOS EN EL PADRON DE EMPLEADOS
            int user_id = user.insert(connection, dto.getEmployee_user());
            if (user.isError()) {
                throw new ServiceException(user.getErrorCode(), user.getUserMessage());
            }
            //PASO 4.1 RECUPERACION DEL ID DE LA ENTIDAD GENERADA
            dto.getTransaction().put("enty_id", String.valueOf(user_id));

            // PASO 5: REGISTRO DEL FIN DE LA TRANSACCION
            int end_id = hys.endTransactionReturn(connection, Const.INDEX_HYS_PROGRAM_HISTORY, "FIN DE UNA TRANSACCION - EMP");
            if (end_id <= 0) {
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO - END_TRANSACTION");
            }
            //PASO 5.1: RECUPERACION DE ID - FIN DE LA TRANSACCION
            dto.getTransaction().put("hys_end_id", String.valueOf(end_id));

            //PASO 6 SI NO HUBO ERRORES SE CONFIRMA LA TRANSACCION
            connection.commit();
            commitSuccess = true;
        } catch (SQLException e) {
            rollback(connection);
            log(e, "insert");
            returnMessageError(e.getErrorCode(), e.getMessage());
        } catch (ServiceException | DataAccesObjectException e) {
            rollback(connection);
            returnMessageError(e.getErrorCode(), e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }

        // Cortamos de inmediato si la transacción base falló
        if (!commitSuccess) {
            return false;
        }
        // Paso 8: Actualización del estado macro a OK en la auditoría
        boolean updateOk = transaction.updateStatusOk(connection, dto.getTransaction());

        // Si la auditoría macro falla, lo mandamos a los logs físicos a disco
        if (!updateOk || transaction.isError()) {
            FuncLogs.logError(
                    AppFiles.DIR_PROG_LOG_TODAY,
                    dto.getModule_name(),
                    "[%s - WARN]: No se pudo actualizar el estado macro a OK en la auditoría: %s"
                            .formatted(getProcess_name(), transaction.getUserMessage())
            );
        }

        return commitSuccess;
    }
}
