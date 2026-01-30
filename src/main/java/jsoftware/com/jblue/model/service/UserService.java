/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

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
import jsoftware.com.jutil.db.JDBConnection;

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
     *
     * @param connection
     * @param process_id
     * @param user
     * @param user_docs
     * @return
     */
    public int save(JDBConnection connection, String process_type, UserDTO user, List<UserDocumentDTO> user_docs) {
        boolean res = false;
        int process_id = -1;
        try {
            connection.setAutoCommit(false);
            //[1] REGISTRO DE DATOS DE USUARIO
            int[] exists = user_dao.exists(connection, user);
            int key;
            if (exists[0] > 0) {
                if (exists[1] != 1) {
                    throw new SQLException("USUARIO YA EXISTENTE");
                }
                key = exists[0];
            } else {
                key = user_dao.insert(connection, user);
            }
            user_dao.insert(connection, user);
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
            res = HistoryDAO.getInstance().insert(connection, Const.INDEX_USR_DOCUMENT, "SE REGISTRARON LOS DOCUMENTOS DEL USUARIO: %s %s %s"
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
            e.printStackTrace();
        } catch (Exception e) {
            connection.rollBack();
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return process_id;
    }

}
