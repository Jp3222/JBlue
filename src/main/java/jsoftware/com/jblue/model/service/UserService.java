/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Optional;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.BlockedUserDAO;
import jsoftware.com.jblue.model.dao.BlockedValuesDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.UserDao;
import jsoftware.com.jblue.model.dto.BlockedValuesDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class UserService extends AbstractService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UserDao user_dao;
    private final BlockedUserDAO blocked_dao;
    private final BlockedValuesDAO value_bloq_dao;
    private final HistoryDAO.UserHistoryDAO history_dao;

    public UserService(boolean flag_dev, String name_module) {
        super(flag_dev, name_module);
        user_dao = new UserDao(flag_dev, name_module);
        blocked_dao = new BlockedUserDAO(flag_dev, name_module);
        value_bloq_dao = new BlockedValuesDAO(flag_dev, name_module);
        history_dao = HistoryDAO.UserHistoryDAO.getInstance();
    }

    public int save(JDBConnection connection, UserDTO dto) {
        boolean res = false;
        int user_id = -1;
        try {
            Optional<BlockedValuesDTO> opt = value_bloq_dao.exists(connection, dto);
            if (!opt.isEmpty()) {
                BlockedValuesDTO g = opt.get();
                String msg = "EL SIGUIENTE VALOR: %s FUE BLOQUEADO EN LA FECHA %s CON FOLIO: %s POR EL SIGUIENTE MOTIVO: ";
                returnMessageError(Integer.parseInt(g.getId()), msg.formatted(
                        g.getLockValue(),
                        g.getDateRegister(),
                        g.getObservationLock()
                ));
                return user_id;
            }
            //SE REGISTRA EL USUARIO
            user_id = user_dao.insert(connection, dto);
            res = user_id > 0;
            if (!res) {
                returnMessageError("REGISTRO DE USUARIO CORRUPTO");
            }
            //SE REGISTRA EL MOVIMIENTO EN EL HISTORIAL
            res = history_dao.insert(connection, "SE REGISTRO EL USUARIO: %s - %s".formatted(
                    dto.getId(), dto.toString()
            ));
            if (!res) {
                returnMessageError("REGISTRO EN BITACORA CORRUPTO");
            }
            commit(connection);
        } catch (SQLException | CorruptInsertionException | KeyNotGenerateException ex) {
            rollback(connection);
            returnMessageError("ERROR AL REGISTRAR EL USUARIO");
            FuncLogs.logError(
                    AppFiles.DIR_PROG_LOG_TODAY,
                    getClass(), ex,
                    getProcess_name(), "save",
                    ex.getMessage()
            );
        }
        return user_id;
    }

    /**
     * Metodo que verifica la existencia de un usuario y su status actual
     *
     * @param connection
     * @param user
     * @return
     */
    public boolean exist(JDBConnection connection, UserDTO user) {
        boolean res;
        try {
            // Delegamos la búsqueda al DAO y enriquecemos el DTO
            res = user_dao.exists(connection, user);

            if (!res) {
                return res; // Si no existe en DB, salimos con false
            }

            // CORRECCIÓN: Validación de ID (Recuperado del Map del DTO)
            Object id_raw = user.get("id");
            boolean id_err = (id_raw == null) || Func.isNullEmptyBlank(id_raw.toString());

            if (id_err) {
                returnMessageError(1, "EL ID RECUPERADO NO ES VALIDO");
                return false; // Forzamos false por inconsistencia de datos
            }

            // CORRECCIÓN: Ahora validamos la llave "status" (no "id" repetido)
            Object status_raw = user.get("status");
            boolean status_err = (status_raw == null) || Func.isNullEmptyBlank(status_raw.toString());

            if (status_err) {
                returnMessageError(2, "EL STATUS RECUPERADO NO ES VALIDO");
                return false;
            }

            // Lógica de Negocio: Validación de estados específicos
            // Usamos el método getStatus() que realiza el cast final según tu estándar
            int currentStatus = Integer.parseInt(user.getStatus());

            /**
             * SI FUE ELIMINADO REVISAR PORQUE Y SI EL BLOQUEO NO ES PROBLEMA
             * PARA UNA NUEVA TOMA, QUITARLO
             */
            if (currentStatus == Const.INDEX_STATUS_ELIMINADO_3) {
                error_code = 3;
                user_message = "ESTE USUARIO FUE DEHABILITADO, REVISAR LOS MODULOS DE AUDITORIA CORRESPONDIENTES A ESTE EVENTO";
                return false;
            }

            /**
             * SI FUE DESHABILITADO REVISAR PORQUE Y SI EL BLOQUEO NO ES
             * PROBLEMA PARA UNA NUEVA TOMA, QUITARLO
             */
            if (currentStatus == Const.INDEX_STATUS_INACTIVO_2) {
                error_code = 4;
                user_message = "ESTE USUARIO FUE DEHABILITADO, REVISAR LOS MODULOS DE AUDITORIA CORRESPONDIENTES A ESTE EVENTO";
                return false;
            }

            /**
             * SI FUE PUESTO EN SITUACION CRITICA REVISAR PORQUE Y SI EL BLOQUEO
             * NO ES PROBLEMA PARA UNA NUEVA TOMA, QUITARLO
             */
            if (currentStatus == Const.INDEX_STATUS_SITUACION_CRITICA_31) {
                error_code = 5; // Cambiado a 5 para diferenciar de Inactivo
                user_message = "ESTE USUARIO ESTA EN SITUACION CRITICA, REVISAR LOS MODULOS DE AUDITORIA CORRESPONDIENTES A ESTE EVENTO";
                return false;
            }

            /**
             * SI FUE REPORTADO REVISAR PORQUE Y SI EL BLOQUEO NO ES PROBLEMA
             * PARA UNA NUEVA TOMA, QUITARLO
             */
            if (currentStatus == Const.INDEX_STATUS_REPORTADO_32) {
                error_code = 6; // Cambiado a 5 para diferenciar de Inactivo
                user_message = "ESTE USUARIO FUE REPORTADO, REVISAR LOS MODULOS DE AUDITORIA CORRESPONDIENTES A ESTE EVENTO";
                return false;
            }

            /**
             * NO SE PUEDE DAR DE ALTA UN USUARIO QUE YA ESTA REGISTRADO
             * POSIBLEMENTE EN OTRO PADRON, EN ESTE CASO CONTACTAR CON LA OTRA
             * OFICINA Y SI NO ES UN PROBLEMA OTRA TOMA, QUITAR EL PROQUEO
             */
            Object office_raw = user.getOfficeId();
            boolean office_id = (office_raw == null) || Func.isNullEmptyBlank(user.getOfficeId());
            if (currentStatus == Const.INDEX_STATUS_ACTIVO_1 && office_id && !user.getOfficeId().equals(session.getCurrent_instance().getOfficeId())) {
                error_code = 7; // Cambiado a 5 para diferenciar de Inactivo
                user_message = "ESTE USUARIO CUENTA CON UN REGISTRO ACTIVO EN OTRA ENTIDAD";
                return false;
            }

            /**
             * NO SE PUEDE REGISTRAR UN USUARIO SI YA ESTA REGISTRADO, EN ESTE
             * CASO DAR DE ALTA UNA NUEVA TOMA, NO UN NUEVO TITULAR
             */
            if (currentStatus == Const.INDEX_STATUS_ACTIVO_1) {
                error_code = 8; // Cambiado a 5 para diferenciar de Inactivo
                user_message = "ESTE USUARIO CUENTA CON UN REGISTRO ACTIVO";
                return false;
            }
        } catch (SQLException ex) {
            // Captura de errores de MySQL (ideal para Railway)
            user_message = "ERROR INESPERADO AL CONSULTAR USUARIO";
            error_code = ex.getErrorCode();
            res = false;
        }
        return res;
    }

}
