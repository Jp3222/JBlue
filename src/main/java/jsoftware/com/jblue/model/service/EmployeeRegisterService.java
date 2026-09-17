package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.TransactionHistoryDTO;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Servicio maestro encargado de coordinar el caso de uso compuesto: Registro de
 * Empleados. Mantiene el aislamiento transaccional y la sincronización de
 * bitácoras en JBlue.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-04-22
 * @version 1.9
 */
public class EmployeeRegisterService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final EmployeeService employee;
    private final EmployeeUserService user;
    private final TransactionHistoryService transaction;

    public EmployeeRegisterService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        this.employee = new EmployeeService(dev_flag, process_name);
        this.user = new EmployeeUserService(dev_flag, process_name);
        this.transaction = new TransactionHistoryService(dev_flag, process_name);
    }

    public boolean insert(JDBConnection connection, SystemSession ss, EmployeeRegisterWrapperDTO dto) {
        if (connection == null || ss == null || dto == null) {
            return returnMessageError("PARÁMETROS DE ENTRADA INVÁLIDOS");
        }

        TransactionHistoryDTO transaction_dto = dto.getTransaction();
        boolean res = false;

        // [1] REGISTRO DE TRANSACCIÓN INICIAL (Status 34: Incompleto / En proceso)
        // Se ejecuta fuera del bloque transaccional principal para asegurar la persistencia de la auditoría
        try {
            res = transaction.insert(connection, ss, transaction_dto);
            if (!res) {
                return returnMessageError("TRANSACCIÓN FALLIDA: NO SE PUDO REGISTRAR LA BITÁCORA INICIAL");
            }
        } catch (SQLException | ServiceException e) {
            return returnMessageError("ERROR AL INICIAR BITÁCORA DE TRANSACCIÓN: " + e.getMessage());
        }

        // BLOQUE TRANSACCIONAL PRINCIPAL (Empleado + Usuario + Confirmación de Estado)
        try {
            // [2] Iniciar bloque atómico
            connection.setAutoCommit(false);

            String final_employee = ss.getCurrentEmployee().getOfficeId();
            String final_office = ss.getCurrent_instance().getOfficeId();
            String final_committee = ss.getCurrent_instance().getCommitteeId();

            // [3] Registro de Empleado
            EmployeeDTO emp = dto.getEmployee();
            emp.put("committee_id", final_committee);
            emp.put("last_employee_update", final_employee);

            res = employee.insert(connection, ss, emp);
            if (!res || employee.isError()) {
                rollback(connection);
                return returnMessageError(employee.getUserMessage());
            }

            // [4] Registro de Usuario asociado al Empleado
            EmployeeUserDTO usr = dto.getEmployee_user();
            usr.put("employee_id", emp.getId());
            usr.put("office_id", final_office);
            usr.put("description", emp.toString());
            usr.put("last_employee_update", final_employee);

            res = user.insert(connection, ss, usr);
            if (!res || user.isError()) {
                rollback(connection);
                return returnMessageError(user.getUserMessage());
            }

            // [5] Actualización del estado de la transacción a Status 1 (Activo / Exitoso)
            // Se realiza dentro del bloque atómico antes del commit definitivo
            res = transaction.update(connection, ss, transaction_dto);
            if (!res) {
                rollback(connection);
                return returnMessageError("TRANSACCIÓN FALLIDA AL CONFIRMAR ESTADO DE AUDITORÍA");
            }

            // [6] Confirmar cambios completos de Empleado, Usuario y Estado de Transacción
            commit(connection);
            return returnMessageError(SERVICE_EXECUTE_OK, "OPERACIÓN EXITOSA");

        } catch (SQLException | ServiceException e) {
            rollback(connection);
            return returnMessageError("ERROR DE SISTEMA: " + e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
