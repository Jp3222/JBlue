/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
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
    private final ProcessService process_service;
    private final TransactionHistoryService transaction_service;
    private final HistoryDAO history_dao;

    public OwnerRegisterProcessService(boolean flag_dev, String process_name) {
        super(flag_dev, process_name);
        process_service = new ProcessService(flag_dev, process_name);
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
        boolean res = false;
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
            dto.getTransaction().put("hys_start_id", String.valueOf(start_id));
            res = process_service.start(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            res = process_service.valid(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            res = process_service.payment(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            res = process_service.finalized(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            int end_id = history_dao.endTransactionReturn(connection, Const.INDEX_HYS_PROGRAM_HISTORY, "FIN DE UNA TRANSACCION");
            if (end_id <= 0) {
                rollback(connection);
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO - END_TRANSACTION");
            }
            //PASO 5.1: RECUPERACION DE ID - FIN DE LA TRANSACCION
            dto.getTransaction().put("hys_end_id", String.valueOf(end_id));
            //PASO 6 SI NO HUBO ERRORES SE CONFIRMA LA TRANSACCION
            commit(connection);
            res = true;
        } catch (SQLException | ServiceException | CorruptInsertionException | KeyNotGenerateException e) {
            rollback(connection);
            res = false;
            returnMessageError(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        // Cortamos de inmediato si la transacción base falló
        if (!res) {
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
        return res;
    }
}
