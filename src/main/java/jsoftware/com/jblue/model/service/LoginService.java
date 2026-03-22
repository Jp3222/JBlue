/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import jsoftware.com.jblue.model.dao.AdministrationHistoryDAO;
import jsoftware.com.jblue.model.dao.EmployeeUserDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO.EmployeeUserHistoryDAO;
import jsoftware.com.jblue.model.dao.SessionDAO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.HysAdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.SessionDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptUpdateException;
import jsoftware.com.jblue.model.exp.service.LoginFailedException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.util.EncriptadoAES;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class LoginService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final SystemSession system_session;
    private final EmployeeUserDAO employee_dao;
    private final AdministrationHistoryDAO administration_history_dao;
    private final EmployeeUserHistoryDAO history_dao;
    private final SessionDAO session_dao;

    public LoginService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        system_session = SystemSession.getInstancia();
        employee_dao = new EmployeeUserDAO(dev_flag, process_name);
        administration_history_dao = new AdministrationHistoryDAO(dev_flag, process_name);
        session_dao = new SessionDAO(dev_flag, process_name);
        history_dao = HistoryDAO.EmployeeUserHistoryDAO.getInstance();
    }

    public boolean login(JDBConnection connection, String user, String password) {
        boolean res = false;
        SessionDTO dto = new SessionDTO();
        connection.setAutoCommit(false);
        try {
            //[1] BUSCAR SI EXISTE EL EMPLEADO
            String us = EncriptadoAES.doEncrypt(user, password);
            String pw = EncriptadoAES.doEncrypt(password, user);
            Optional<EmployeeUserDTO> option = employee_dao.get(connection, us, pw);
            res = option.isEmpty();
            if (res) {
                log("INTENTO DE INICIO DE SESION: USUARIO=%s".formatted(user));
                return !res;
            }
            //GAURDAR EMPLEADO 
            EmployeeUserDTO employee = option.get();
            system_session.setUser(employee);
            dto.put("employee_id", employee.getId());
            res = session_dao.haveActiveSession(connection, employee.getId());
            if (res) {
                int session_id_old = history_dao.logout(connection, "CERRAR SESSION ABIERTA");
                res = session_id_old > 0;
                if (!res) {
                    throw new LoginFailedException(2);
                }
                dto.put("history_end_id", String.valueOf(session_id_old));
                res = session_dao.updateStatus(connection, dto);
                if (!res) {
                    throw new LoginFailedException(3);
                }
            }

            //[2] REGISTRO EN BITACORA
            int session_id = history_dao.login(connection, "INICIO DE SESIÓN");
            dto.put("history_start_id", String.valueOf(session_id));
            res = session_id > 0;
            if (!res) {
                throw new LoginFailedException();
            }
            System.out.println(employee);

            //[3] REGISTRO DE SESION
            session_dao.insert(connection, dto);
            //[4] INFORMACION DE SESSION
            String db_user = history_dao.currentUser(connection);
            //GUARDAR ADMINISTRACION ACTUAL
            HysAdministrationHistoryDTO currentAdministration = administration_history_dao.getCurrentAdministration(connection);
            system_session.setCurrentAdministration(currentAdministration);
            //GUARDAR USUARIO DE BASE DE DATOS ASIGNADO
            system_session.setCurrentDbUser(db_user);
            system_session.setCurrentSession(dto);
            //SI NO SE GUARDO EL EMPLEADO ACTUAL
            res = Func.isNotNull(SystemSession.getInstancia().getCurrentEmployee());
            if (!res) {
                throw new LoginFailedException(4, "EL USUARIO DE SESION NO SE GUARDO CORRECTAMENTE");
            }

//            //SI NO SE GUARDO LA ADMINISTRACION ACTUAL
//            res = Func.isNotNull(SystemSession.getInstancia().getCurrentAdministration());
//            if (!res) {
//                throw new LoginFailedException(5, "LA ADMINISTRACION ACTUAL NO SE GUARDO CORRECTAMENTE");
//            }

            //SI NO SE GUARDO EL USUARIO DB
            res = Func.isNotNull(SystemSession.getInstancia().getCurrentDbUser());
            if (!res) {
                throw new LoginFailedException(6, "ERROR DE SERVIDOR");
            }

//            //SI EL USUARIO QUE EJECUTO EL QUERY Y EL DE LAS CREDENCIALES NO SON EL MISMO
//            res = SystemSession.getInstancia().getCurrentDbUser().equals(LaunchApp.getInstance().getResources("user_db"));
//            if (!res) {
//                throw new LoginFailedException(7, "ERROR DE SERVIDOR");
//            }
            log("INICIO DE SESION: USUARIO=%s".formatted(user));
            system_session.put("user-session", user);
            connection.commit();
        } catch (SQLException ex) {
            user_message = "INICIO DE SESION FALLIDO";
            error_code = -1;
            res = false;
            log(ex, "login");
            connection.rollBack();
        } catch (DataAccesObjectException ex) {
            user_message = "INICIO DE SESION FALLIDO";
            error_code = ex.getErrorCode();
            res = false;
            log(ex, "login");
            connection.rollBack();
        } catch (ServiceException ex) {
            user_message = ex.getMessage();
            error_code = ex.getErrorCode();
            res = false;
            log(ex, "login");
            connection.rollBack();

        } catch (Exception ex) {
            user_message = "INICIO DE SESION FALLIDO";
            error_code = -2;
            res = false;
            log(ex, "login");
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    public boolean logout(JDBConnection connection) throws CorruptUpdateException {
        boolean res = false;
        String user = system_session.get("user-session").toString();
        try {
            connection.setAutoCommit(false);
            SessionDTO session = system_session.getCurrentSession();
            int history_end_id = history_dao.logout(connection, "FIN DE SESSION");
            if (history_end_id <= 0) {
                throw new LoginFailedException(1, "ERROR AL REGISTRAR EL FIN DE LA SESSION");
            }
            session.put("history_end_id", String.valueOf(history_end_id));
            res = session_dao.updateStatus(connection, session);
            if (!res) {
                throw new LoginFailedException(2, "ERROR AL REGISTRAR EL FIN DE LA SESSION");
            }
            log("FIN DE SESION: USUARIO=%s".formatted(user));
            connection.commit();
        } catch (SQLException ex) {
            user_message = "FIN DE SESION FALLIDO";
            error_code = -1;
            res = false;
            log(ex, "login");
            connection.rollBack();
        } catch (DataAccesObjectException ex) {
            user_message = "FIN DE SESION FALLIDO";
            error_code = ex.getErrorCode();
            res = false;
            log(ex, "login");
            connection.rollBack();
        } catch (ServiceException ex) {
            user_message = ex.getUserMessage();
            error_code = ex.getErrorCode();
            res = false;
            log(ex, "login");
            connection.rollBack();
        } catch (Exception ex) {
            user_message = "FIN DE SESION FALLIDO";
            error_code = -3;
            res = false;
            log(ex, "login");
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, getProcess_name(), method_name, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void log(String message) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, "MAIN", message);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
