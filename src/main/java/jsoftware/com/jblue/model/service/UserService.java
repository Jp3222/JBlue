/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.UserDao;
import jsoftware.com.jblue.model.dao.UserDocumentDAO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.UserDocumentDTO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
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
    private final ProcessDAO process_dao;
    private final UserDocumentDAO doc_dao;
    private final HistoryDAO.UserHistoryDAO history_dao;
    private final HistoryDAO.DocumentHistoryDAO history_doc_dao;

    public UserService(boolean flag_dev, String name_module) {
        super(flag_dev, name_module);
        user_dao = new UserDao(flag_dev, name_module);
        process_dao = new ProcessDAO(flag_dev, name_module);
        doc_dao = new UserDocumentDAO(flag_dev, name_module);
        history_dao = HistoryDAO.UserHistoryDAO.getInstance();
        history_doc_dao = HistoryDAO.DocumentHistoryDAO.getInstance();
    }

    /**
     * METODO QUE INICIA UN TRAMITE Y SIGUE EL SIGUIENTE FLUJO
     * <br>[1]- REGISTRA EL TRAMITE CON STATUS: INICIADO
     * <br>[2]- REGISTRA LA INFORMACION PERSONAL DEL USUARIO
     * <br>[3]- REGISTRA LOS DOCUMENTOS DE IDENTIDAD DEL USUARIO Y DEL TRAMITE
     * REALIZADO
     *
     * @param connection - CONEXION ACTIVA DE BASE DE DATOS
     * @param process_type - TIPO DE TRAMITE
     * @param dto - WRAPPER CON TODA LA INFROMACION DEL TRAMITE
     * @return EL ID DEL USUARIO REGISTRADO
     */
    public int saveProcess(JDBConnection connection, String process_type, ProcessWrapperDTO dto) throws SQLException, ServiceException, CorruptInsertionException, KeyNotGenerateException {
        //GENERACION DEL ID USUARIO
        int user_id = saveUser(connection, dto.getUser());

        //GENERACION DEL ID DEL TRAMITE
        int process_id = process_dao.startProcess(connection, process_type, String.valueOf(user_id));

        //SE REGISTRA EL TRAMITE ORIGINAL SOLO SI ES UNA NUEVA TOMA
        if (process_type.equals("1") && dto.getWater_intake().getId() == null) {
            dto.getWater_intake().put("original_process", process_id);
        }

        //SE REGISTRA EL ULTIMO TRAMITE REALIZADO
        dto.getWater_intake().put("last_process_type", process_type);

        //SE EL TRAMITE QUE SE PAGARA
        dto.getPayment().put("process_id", process_id);

        //SE GRABA EL ID DEL USUARIO Y DEL TRAMITE A LOS DOCUMENTOS DE IDENTIFICACION
        for (UserDocumentDTO i : dto.getUser_document_list()) {
            i.put("user_id", user_id);
            i.put("process_id", process_id);
        }

        //SE GUARDAN LOS DOCUMENTOS
        boolean res = saveUserDocumentList(connection, process_type, dto.getUser().toString(), dto.getUser_document_list());

        return user_id;
    }
    
    /**
     * Metodo que verifica la existencia de un usuario y su status actual
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
                error_code = 1;
                user_message = "EL ID RECUPERADO NO ES VALIDO";
                return false; // Forzamos false por inconsistencia de datos
            }

            // CORRECCIÓN: Ahora validamos la llave "status" (no "id" repetido)
            Object status_raw = user.get("status");
            boolean status_err = (status_raw == null) || Func.isNullEmptyBlank(status_raw.toString());

            if (status_err) {
                error_code = 2;
                user_message = "EL STATUS RECUPERADO NO ES VALIDO";
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

    public int saveUser(JDBConnection connection, UserDTO dto) throws SQLException, ServiceException, CorruptInsertionException, KeyNotGenerateException {
        //SE REGISTRA EL USUARIO
        int user_id = user_dao.insert(connection, dto);
        boolean res = user_id > 0;
        if (!res) {
            throw new ServiceException(ServiceException.SERVICE_INSERT_EXCEPTION, "ERROR AL REGISTRAR EL USUARIO");
        }
        //SE REGISTRA EL MOVIMIENTO EN EL HISTORIAL
        res = history_dao.insert(connection, "SE REGISTRO EL USUARIO: %s - %s".formatted(
                dto.getId(), dto.toString()
        ));
        if (!res) {
            throw new ServiceException(ServiceException.SERVICE_INSERT_EXCEPTION, "ERROR AL REGISTRAR EN BITACORA.");
        }
        return user_id;
    }

    public boolean saveUserDocumentList(JDBConnection connection, String process_type, String usr_name, List<UserDocumentDTO> list) throws SQLException, CorruptInsertionException, ServiceException {
        boolean res = doc_dao.insert(connection, list);
        if (!res) {
            throw new ServiceException(ServiceException.SERVICE_INSERT_EXCEPTION, "ERROR AL REGISTRAR DOCUMENTACION DEL USUARIO.");
        }
        //SE REGISTRA EL MOVIMIENTO EN EL HISTORIAL
        res = history_doc_dao.insert(connection, "SE REGISTRO DOCUMENTACION DEL USUARIO: %s.".formatted(usr_name));
        if (!res) {
            throw new ServiceException(ServiceException.SERVICE_INSERT_EXCEPTION, "ERROR AL REGISTRAR EN BITACORA");
        }
        return true;
    }

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(
                    AppFiles.DIR_PROG_LOG_TODAY,
                    getClass(), e,
                    getClass().getName(),
                    method_name,
                    e.getMessage()
            );
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
