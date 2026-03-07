/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
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
    private final HistoryDAO.UserHistoryDAO history_dao;

    public UserService(UserDocumentDAO doc_dao, UserDao user_dao, ProcessDAO process_dao) {
        this.doc_dao = doc_dao;
        this.user_dao = user_dao;
        this.process_dao = process_dao;
        this.history_dao = HistoryDAO.UserHistoryDAO.getInstance();
    }

    public UserService(boolean flag_dev, String name_module) {
        this(new UserDocumentDAO(flag_dev, name_module),
                new UserDao(flag_dev, name_module),
                new ProcessDAO(flag_dev, name_module)
        );
    }

    public int saveProcess(JDBConnection connection, String process_type, ProcessWrapperDTO dto) throws SQLException {
        boolean res;
        int user_id = saveUser(connection, dto.getUser());
        res = user_id > 0;
        if (!res) {
            throw new SQLException("[1]REGISTRO INCORRECTO DEL TRAMITE");
        }
        int process_id = process_dao.startProcess(connection, process_type, String.valueOf(user_id));
        res = process_id > 0;
        if (!res) {
            throw new SQLException("[1]REGISTRO INCORRECTO DEL TRAMITE");
        }
        return user_id;
    }

    public int saveUser(JDBConnection connection, UserDTO dto) throws SQLException {
        int user_id = -1;
        boolean res = false;
        try {
            //SE REGISTRA EL USUARIO
            user_id = user_dao.insert(connection, dto);
            res = user_id > 0;
            if (!res) {
                throw new SQLException("REGISTRO INCORRECTO DE USUARIO");
            }
            //SE REGISTRA EL MOVIMIENTO EN EL HISTORIAL
            history_dao.insert(connection, "SE REGISTRO EL USUARIO: %s - %s".formatted(
                    dto.getId(), dto.toString()
            ));
        } catch (SQLException e) {
            throw e;
        }
        return user_id;
    }

    public boolean saveUserDocumentList(JDBConnection connection, String process_type, UserDocumentDTO dto) {
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
