package jsoftware.com.jblue.model.service;

import java.io.IOException;
import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.models.AbstractService;
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

        // SE HACE ESTO TEMPORALMENTE PARA NO EJECUTAR NINGUNA OPERACION 
        // MIENTRAS SE PRUEBA LAS VALIDACIONES DE LA INTERFACES GRAFICA
//      if (!commitSuccess) {
//          return false;
//      }
        // Paso 1: Inicialización de auditoría macro (Independiente del autocommit)
        int transaction_id = transaction.insert(connection, dto.getTransaction());
        if (transaction_id <= 0) {
            returnMessageError(-1, "LA OPERACION NO SE PUDO REGISTRAR EN BITACORA MAESTRA");
            return false;
        }

        try {
            connection.setAutoCommit(false);

            // Paso 2: Apertura de bitácora de grano fino
            int start_id = hys.startTransactionReturn(connection, Const.INDEX_EMP_USER, "INICIO DE UNA TRANSACCION - EMP");
            if (start_id <= 0) {
                throw new ServiceException(1, "REGISTRO EN BITACORA CORRUPTO - START_TRANSACTION");
            }
            dto.getTransaction().put("hys_start_id", String.valueOf(start_id));

            // Paso 3: Inserción de la entidad Empleado (Datos civiles)
            int employee_id = employee.insert(connection, dto.getEmployee());
            if (employee.isError()) {
                throw new ServiceException(employee.getErrorCode(), employee.getUserMessage());
            }

            // Paso 4: Enriquecimiento dinámico para credenciales de acceso (Traspaso de Strings)
            dto.getEmployee_user().put("employee_id", String.valueOf(employee_id));
            dto.getEmployee_user().put("office_id", session.getCurrent_instance().getOfficeId());
            dto.getEmployee_user().put("description", dto.getEmployee().toString());
            dto.getEmployee_user().put("last_employee_update", session.getCurrentEmployee().getId());

            // Paso 5: Inserción de credenciales de usuario
            int user_id = user.insert(connection, dto.getEmployee_user());
            if (user.isError()) {
                throw new ServiceException(user.getErrorCode(), user.getUserMessage());
            }
            dto.getTransaction().put("enty_id", String.valueOf(user_id));

            // Paso 6: Cierre de la bitácora de grano fino
            int end_id = hys.endTransactionReturn(connection, Const.INDEX_EMP_USER, "FIN DE UNA TRANSACCION - EMP");
            if (end_id <= 0) {
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO - END_TRANSACTION");
            }
            // Nota: Validar si tu base de datos requiere "hys_end_id" o "hys_endt_id"
            dto.getTransaction().put("hys_end_id", String.valueOf(end_id));

            // Paso 7: Consolidación del bloque atómico
            connection.commit();
            commitSuccess = true;

        } catch (SQLException e) {
            rollback(connection);
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
            try {
                FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, dto.getModule_name(),
                        "[%s - WARN]: No se pudo actualizar el estado macro a OK en la auditoría: %s"
                                .formatted(getProcess_name(), transaction.getUserMessage()));
            } catch (IOException ex) {
                System.getLogger(EmployeeRegisterService.class.getName())
                        .log(System.Logger.Level.ERROR, "Fallo de E/S crítico al escribir el archivo log.", ex);
            }
        }

        return commitSuccess;
    }
}
