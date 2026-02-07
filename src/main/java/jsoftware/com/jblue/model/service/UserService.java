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
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.UserDocumentDTO;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class UserService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UserDao user_dao;
    private final ProcessDAO process_dao;
    private final UserDocumentDAO doc_dao;

    public UserService(UserDocumentDAO doc_dao, UserDao user_dao, ProcessDAO process_dao) {
        this.doc_dao = doc_dao;
        this.user_dao = user_dao;
        this.process_dao = process_dao;
    }

    public UserService(boolean flag_dev, String name_module) {
        this.doc_dao = new UserDocumentDAO(flag_dev, name_module);
        this.user_dao = new UserDao(flag_dev, name_module);
        this.process_dao = new ProcessDAO(flag_dev, name_module);
    }

    /**
     * Orquestador de Servicio: Registra exclusivamente nuevos titulares y
     * apertura su trámite inicial.
     * <p>
     * Este método implementa un flujo estricto de "Solo Inserción". Si el CURP
     * o identificador del usuario ya existe en el sistema, la operación se
     * aborta para evitar duplicidad.
     * </p>
     * <b>Pasos de la Transacción:</b>
     * <ol>
     * <li>Verificación de no existencia del usuario.</li>
     * <li>Inserción de {@code UserDTO} y registro en bitácora de usuarios.</li>
     * <li>Inicio del proceso/trámite vinculado al nuevo ID de usuario.</li>
     * <li>Registro en bitácora de procesos.</li>
     * </ol>
     *
     * @param connection Conexión activa con soporte transaccional.
     * @param process_type Categoría del trámite a iniciar.
     * @param user DTO con la información del nuevo titular.
     * @return El ID del trámite generado.
     * @throws SQLException Si el usuario ya existe, si falla la inserción o si
     * la bitácora es corrupta.
     * @throws Exception Para errores generales de ejecución.
     */
    public int save(JDBConnection connection, String process_type, UserDTO user) throws SQLException, Exception {
        boolean res;
        int process_id = -1;
        try {
            connection.setAutoCommit(false);
            //[1] REGISTRO DE DATOS DE USUARIO
            int[] exists = user_dao.exists(connection, user);
            int key;
            if (exists[0] > 0) {
                throw new SQLException("USUARIO EXISTENTE");
            } else {
                key = user_dao.insert(connection, user);
            }
            res = key > 0;
            if (!res) {
                throw new SQLException("REGISTRO DE USUARIO ERRONEO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.UserHistoryDAO.getInstance().insert(connection,
                    "SE REGISTRO EL USUARIO: %s - %s %s %s".formatted(
                            key,
                            user.getFirstName(),
                            user.getLastName1(),
                            user.getLastName2()
                    ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //[2]CAPTURA EL INICIO DE UN TRAMITE
            process_id = process_dao.startProcess(connection, process_type, String.valueOf(key));
            res = process_id > 0;
            if (!res) {
                throw new SQLException("REGISTRO DEL PROCESO CORRUPTO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.ProcessHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL TRAMITE DEL USUARIO: %s - %s %s %s".formatted(
                    key,
                    user.getFirstName(),
                    user.getLastName1(),
                    user.getLastName2()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
            throw e;
        } catch (Exception e) {
            connection.rollBack();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return process_id;
    }

    /**
     * Orquestador de Servicio: Ejecuta el flujo integral de registro y
     * validación de trámites.
     * <p>
     * Este método implementa una transacción ACID que garantiza la consistencia
     * entre el padrón de usuarios, la gestión documental y el historial de
     * procesos.
     * </p>
     * <b>Flujo de Operación:</b>
     * <ol>
     * <li><b>Gestión de Usuario:</b> Si el usuario existe, sincroniza cambios
     * mediante {@code updateOldUser}; de lo contrario, realiza un
     * {@code insert}.</li>
     * <li><b>Apertura de Trámite:</b> Registra el inicio del proceso en la
     * tabla correspondiente.</li>
     * <li><b>Gestión Documental:</b> Persiste la lista de documentos que
     * validan la identidad.</li>
     * <li><b>Validación:</b> Cambia el estatus del trámite a 'Validado' tras
     * completar la carga.</li>
     * <li><b>Auditoría:</b> Genera entradas en bitácora para cada paso del
     * proceso.</li>
     * </ol>
     *
     * @param connection Conexión activa (manejo manual de commit/rollback).
     * @param process_type Identificador de la categoría del trámite.
     * @param wrapper Contenedor con el usuario actual, copia para comparación y
     * lista de documentos.
     * @return El ID del trámite generado (PK de la tabla de procesos).
     * @throws SQLException Si ocurre un error en la base de datos o violación
     * de integridad.
     * @throws Exception Si ocurre un error en la lógica de negocio o parseo.
     */
    public int save(JDBConnection connection, String process_type, ProcessWrapperDTO wrapper) throws SQLException, Exception {
        boolean res;
        int process_id = -1;
        try {
            connection.setAutoCommit(false);
            UserDTO user = wrapper.getUser();
            List<UserDocumentDTO> user_docs = wrapper.getUser_document_list();
            //[1] REGISTRO DE DATOS DE USUARIO
            int[] exists = user_dao.exists(connection, user);
            int key;
            if (exists[0] > 0) {
                key = exists[0];
                user_dao.updateOldUser(connection, wrapper.getUser_copy(), user);
            } else {
                key = user_dao.insert(connection, user);
            }
            res = key > 0;
            if (!res) {
                throw new SQLException("REGISTRO DE USUARIO ERRONEO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.UserHistoryDAO.getInstance().insert(connection,
                    "SE REGISTRO EL USUARIO: %s - %s %s %s".formatted(
                            key,
                            user.getFirstName(),
                            user.getLastName1(),
                            user.getLastName2()
                    ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //[2]CAPTURA EL INICIO DE UN TRAMITE
            process_id = process_dao.startProcess(connection, process_type, String.valueOf(key));
            res = process_id > 0;
            if (!res) {
                throw new SQLException("REGISTRO DEL PROCESO CORRUPTO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.ProcessHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL TRAMITE DEL USUARIO: %s - %s %s %s".formatted(
                    key,
                    user.getFirstName(),
                    user.getLastName1(),
                    user.getLastName2()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //[3]REGISTRO DE DATOS QUE VALIDAN LA IDENTIDAD DEL USUARIO
            res = doc_dao.insert(connection, user_docs);
            if (!res) {
                throw new SQLException("REGISTRO DE DOCUMENTOS ERRONEO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.getInstance().insert(connection, Const.INDEX_USR_USER_DOCUMENT, "SE REGISTRARON LOS DOCUMENTOS DEL USUARIO: %s %s %s"
                    .formatted(user.getFirstName(), user.getLastName1(), user.getLastName2()));
            if (!res) {
                throw new SQLException("REGISTRO DE DOCUMENTOS EN BITACORA CORRUPTO");
            }
            //[4]ACTUALIZA EL TRAMITE A STATUS VALIDADO
            res = process_dao.validProcess(connection, process_id);
            if (!res) {
                throw new SQLException("REGISTRO DEL PROCESO CORRUPTO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.ProcessHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL TRAMITE DEL USUARIO: %s - %s %s %s".formatted(
                    key,
                    user.getFirstName(),
                    user.getLastName1(),
                    user.getLastName2()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
            throw e;
        } catch (Exception e) {
            connection.rollBack();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return process_id;
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
