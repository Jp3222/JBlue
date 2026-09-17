/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import javax.swing.JTable;
import jsoftware.com.jblue.model.dao.ViewsDAO;
import jsoftware.com.jblue.model.dto.TransactionHistoryDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.SystemException;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public class OwnerRegisterProcessService extends AbstractService {

    private static final long serialVersionUID = 1L;
    private final ProcessService process_service;
    private final TransactionHistoryService transaction_service;
    private final ViewsDAO dao;

    public OwnerRegisterProcessService(boolean flag_dev, String process_name) {
        super(flag_dev, process_name);
        process_service = new ProcessService(flag_dev, process_name);
        transaction_service = new TransactionHistoryService(flag_dev, process_name);
        dao = new ViewsDAO(flag_dev, user_message);
    }

    /**
     *
     * @param connection
     * @param process_type
     * @param dto
     * @return
     */
    public boolean save(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        // Corrección: Inicialización por defecto para evitar errores de compilación al testear la interfaz
        boolean res = false;
        TransactionHistoryDTO transaction_dto = dto.getTransaction();
        // [1] REGISTRO DE TRANSACCIÓN INICIAL (Status 34: Incompleto / En proceso)
        // Se ejecuta fuera del bloque transaccional principal para asegurar la persistencia de la auditoría
        try {
            res = transaction_service.insert(connection, ss, transaction_dto);
            if (!res) {
                return returnMessageError("TRANSACCIÓN FALLIDA: NO SE PUDO REGISTRAR LA BITÁCORA INICIAL");
            }
        } catch (SQLException | ServiceException e) {
            return returnMessageError("ERROR AL INICIAR BITÁCORA DE TRANSACCIÓN: " + e.getMessage());
        }

        try {
            connection.setAutoCommit(false);
            //INICIO DEL TRAMITE
            //
            res = process_service.start(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            //VALIDACION DE TRAMITE
            res = process_service.valid(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            //VALIDACION DE PAGO
            res = process_service.payment(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            //MOVIMIENTO FINAL
            res = process_service.finalized(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }
            //MOVIMIENTO REALIZADO
            res = process_service.mov(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            ss.systemValid(connection);
            //PASO 6 SI NO HUBO ERRORES SE CONFIRMA LA TRANSACCION
            commit(connection);
            res = true;
        } catch (SQLException | SystemException e) {
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
        return res;
    }

    public boolean search(JDBConnection connection, ProcessWrapperDTO dto) {
        boolean res;
        try {
            UserDTO user = dto.getUser();
            res = process_service.exists(connection, user);
            if (process_service.isError()) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
                return false;
            }
            dto.setUser_exists(res);
        } catch (Exception e) {
            rollback(connection);
            res = false;
            returnMessageError(e.getMessage());
        }
        return res;
    }

    public boolean userLocked(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        // Corrección: Inicialización por defecto para evitar errores de compilación al testear la interfaz
        boolean res = false;
        if (false) {
            System.out.println(dto.toString());
        }
        try {
            /**
             * SE VERIFICA SI EL PROGRAMA ESTA EN SOLO LECTURA
             */
            //SI EL SISTEMA ESTA EN SOLO LECTURA NO REALIZA REGISTRO ALGUNO
            if (AppConfig.getParameterBoolean(connection, "SOLO_LECTURA")) {
                returnMessageError("EL SISTEMA ESTA EN MODO LECTURA");
                return false;
            }
        } catch (SQLException ex) {
            returnMessageError(ex.getMessage());
            return false;
        }
        try {
            connection.setAutoCommit(false);
            //INICIO DEL TRAMITE
            res = process_service.start(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }
            //VALIDACION DE TRAMITE
            res = process_service.invalid(connection, ss, dto);
            if (!res) {
                returnMessageError(process_service.getErrorCode(), process_service.getUserMessage());
            }

            ss.systemValid(connection);
            //PASO 6 SI NO HUBO ERRORES SE CONFIRMA LA TRANSACCION
            commit(connection);
            res = true;
        } catch (SQLException | SystemException e) {
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
        return res;
    }

    public boolean exist(JDBConnection connection, SystemSession ss, ProcessWrapperDTO dto) {
        boolean res = false;
        res = dto.getId() != null;
        if (!res) {
            return false;
        }
        process_service.search(connection, dto.getProcess(), dto.getProcess().getStatus());
        return res;
    }

    public boolean load(JDBConnection connnection, SystemSession ss, ProcessWrapperDTO dto, JTable model) {
        try {
            JTableModel conceptPayment = dao.conceptPayment(connnection, dto.getProcess());
            model.setModel(conceptPayment);
            return true;
        } catch (SQLException ex) {
            System.getLogger(OwnerRegisterProcessService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }
}
