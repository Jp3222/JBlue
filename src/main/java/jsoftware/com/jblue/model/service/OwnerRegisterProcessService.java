/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.dto.DocumentRecordDTO;
import jsoftware.com.jblue.model.dto.PaymentDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.UserDocumentationDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class OwnerRegisterProcessService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final UserService user_service;
    private final AddressService address_service;
    private final UserDocumentationService documentation_service;
    private final DocumentRecordService document_record_service;
    private final PaymentService payment_service;
    private final WaterIntakeService water_intake_service;
    private final WaterIntakeUserService wki_user_service;
    private final TransactionHistoryService transaction_service;
    private final HistoryDAO history_dao;

    public OwnerRegisterProcessService(boolean flag_dev, String process_name) {
        super(flag_dev, process_name);
        user_service = new UserService(flag_dev, process_name);
        address_service = new AddressService(flag_dev, process_name);
        documentation_service = new UserDocumentationService(flag_dev, process_name);
        document_record_service = new DocumentRecordService(flag_dev, user_message);
        payment_service = new PaymentService(flag_dev, process_name);
        water_intake_service = new WaterIntakeService(flag_dev, process_name);
        wki_user_service = new WaterIntakeUserService(flag_dev, process_name);
        transaction_service = new TransactionHistoryService(flag_dev, process_name);
        history_dao = HistoryDAO.getInstance();
    }

    /**
     *
     * @param connection
     * @param process_type
     * @param dto
     * @return
     */
    public boolean save(JDBConnection connection, ProcessWrapperDTO dto) throws SQLException {
        // Corrección: Inicialización por defecto para evitar errores de compilación al testear la interfaz
        boolean commitSuccess = false;
        SystemSession ss = SystemSession.getInstancia();
        if (ss.isLock()) {
            returnMessageError("LA SESION ACTUAL HA CADUCADO");
            return false;
        }

        if (ss.isAdministrationValid()) {
            returnMessageError("LA ADMINISTRACION ACTUAL NO HA SIDO REGISTRADA");
            return false;
        }
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
        int transaction_id = transaction_service.insert(connection, dto.getTransaction());
        if (transaction_id <= 0) {
            returnMessageError(-1, "LA OPERACION NO SE PUDO REGISTRAR EN BITACORA MAESTRA");
            return false;
        }
        try {
            connection.setAutoCommit(false);
            //PASO 2: REGISTRO DE INICIO DE UNA TRANSACCION
            int start_id = history_dao.startTransactionReturn(connection, Const.INDEX_HYS_PROGRAM_HISTORY, "INICIO DE UNA TRANSACCION");
            if (start_id <= 0) {
                rollback(connection);
                throw new ServiceException(1, "REGISTRO EN BITACORA CORRUPTO - START_TRANSACTION");
            }
            //PASO 2.1: RECUPERACION DE ID - INICIO DE LA TRANSACCION
            dto.getTransaction().put("hys_start_id", String.valueOf(start_id));
            //----------------------------------------------------------------//
            String final_office = ss.getCurrent_instance().getOfficeId();
            String final_employee = ss.getCurrentEmployee().getId();
            UserDTO user = dto.getUser();
            //SI EL USUARIO EXISTE, SE NOTIFICARA LAS OPERACIONES ALTERNATIVAS
            if (user_service.exist(connection, user)) {
                rollback(connection);
                returnMessageError(user_service.getErrorCode(), user_service.getUserMessage());
                return false;
            }
            //SI NO EXISTE, SE COLOCAN DATOS POR DEFECTO
            user.put("office_id", final_office);
            user.put("last_employee_update", final_employee);
            //SI EL PAGO FUE REALIZADO PRESENCIALMENTE TODOS LOS REGISTROS SERAN ACTIVOS,
            //SI NO, SE MARCAN COMO INACTIVOS
            String final_status = dto.isPayment_header_valid() ? "1" : "2";
            user.put("status", final_status);
            int user_id = user_service.save(connection, user);
            if (user_service.isError()) {
                rollback(connection);
                returnMessageError(user_service.getErrorCode(), user_service.getUserMessage());
                return false;
            }
            AddressDTO address = dto.getAddress();
            address.put("user_id", String.valueOf(user_id));
            address.put("employee_id", final_employee);
            address.put("office_id", final_office);
            address.put("status", "1");
            commitSuccess = address_service.insert(connection, address);
            if (!commitSuccess) {
                rollback(connection);
                returnMessageError(address_service.getErrorCode(), address_service.getUserMessage());
                return false;
            }
            List<UserDocumentationDTO> list = dto.getDocument_list();
            commitSuccess = documentation_service.insert(connection, list);
            if (!commitSuccess) {
                rollback(connection);
                returnMessageError(documentation_service.getErrorCode(), documentation_service.getUserMessage());
                return false;
            }
            DocumentRecordDTO document_record = dto.getDocument_record();
            document_record.put("employee_register_id", final_employee);
            document_record.put("status", "2");
            if (dto.isDocument_record_valid()) {
                document_record.put("employee_valid_id", final_employee);
                document_record.put("status", "1");
            }
            document_record.put("document_start", list.getFirst().getId());
            document_record.put("document_end", list.getLast().getId());
            commitSuccess = document_record_service.save(connection, document_record) > 0;
            if (!commitSuccess) {
                rollback(connection);
                returnMessageError(document_record_service.getErrorCode(), document_record_service.getUserMessage());
                return false;
            }
            WaterIntakeDTO wki = dto.getWater_intake();
            wki.put("status", "2");
            wki.put("employee_register", final_employee);
            wki.put("last_employee_update", final_employee);
            if (dto.isWater_intake_valid()) {
                wki.put("status", "1");
                wki.put("last_employee_update", final_employee);
            }
            commitSuccess = water_intake_service.saveProcess(connection, wki);
            if (!commitSuccess) {
                rollback(connection);
                returnMessageError(water_intake_service.getErrorCode(), water_intake_service.getUserMessage());
                return false;
            }
            WaterIntakeUserDTO wki_user = dto.getWki_user();
            PaymentDTO pym = dto.getPayment_header();
            wki_user.put("status", "2");
            wki_user.put("employee_register", final_employee);
            wki_user.put("last_employee_update", final_employee);
            if (pym.getStatus() == 1) {
                wki_user.put("status", final_status);
                wki_user.put("last_employee_update", final_employee);
            }
            this.wki_user_service.saveProcess(connection, wki_user);
            //----------------------------------------------------------------//
            // PASO 5: REGISTRO DEL FIN DE LA TRANSACCION
            int end_id = history_dao.endTransactionReturn(connection, Const.INDEX_HYS_PROGRAM_HISTORY, "FIN DE UNA TRANSACCION");
            if (end_id <= 0) {
                rollback(connection);
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO - END_TRANSACTION");
            }
            //PASO 5.1: RECUPERACION DE ID - FIN DE LA TRANSACCION
            dto.getTransaction().put("hys_end_id", String.valueOf(end_id));
            //PASO 6 SI NO HUBO ERRORES SE CONFIRMA LA TRANSACCION
            commit(connection);
            commitSuccess = true;
        } catch (SQLException | ServiceException | CorruptInsertionException | KeyNotGenerateException e) {
            rollback(connection);
            commitSuccess = false;
            returnMessageError(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        // Cortamos de inmediato si la transacción base falló
        if (!commitSuccess) {
            return false;
        }
        // Paso 8: Actualización del estado macro a OK en la auditoría
        boolean updateOk = transaction_service.updateStatusOk(connection, dto.getTransaction());

        // Si la auditoría macro falla, lo mandamos a los logs físicos a disco
        if (!updateOk || transaction_service.isError()) {
            FuncLogs.logError(
                    AppFiles.DIR_PROG_LOG_TODAY,
                    dto.getModule_name(),
                    "[%s - WARN]: No se pudo actualizar el estado macro a OK en la auditoría: %s"
                            .formatted(getProcess_name(), transaction_service.getUserMessage())
            );
        }
        return commitSuccess;
    }

    public PaymentService getPayment_service() {
        return payment_service;
    }

    public UserService getUser_service() {
        return user_service;
    }

    public WaterIntakeService getWater_intake_service() {
        return water_intake_service;
    }

}
