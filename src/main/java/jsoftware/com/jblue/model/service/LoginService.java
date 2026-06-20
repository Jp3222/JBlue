/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.util.Optional;
import jsoftware.com.jblue.model.cryp.BCryptCrypto;
import jsoftware.com.jblue.model.crypto.DeterministicCrypto;
import jsoftware.com.jblue.model.dao.AdministrationHistoryDAO;
import jsoftware.com.jblue.model.dao.EmployeeUserDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO.EmployeeUserHistoryDAO;
import jsoftware.com.jblue.model.dao.SessionDAO;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.SessionDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptUpdateException;
import jsoftware.com.jblue.model.exp.service.LoginFailedException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 * Servicio encargado de la validación criptográfica de accesos, inicialización
 * y cierre de sesiones de usuario en JBlue.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-12
 * @version 2.0
 */
public class LoginService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private SystemSession system_session;
    private final EmployeeUserDAO employee_dao;
    private final AdministrationHistoryDAO administration_history_dao;
    private final EmployeeUserHistoryDAO history_dao;
    private final SessionDAO session_dao;

    // Constante del sistema para el cifrado determinista del usuario (Pimienta Global)
    private static final String SYSTEM_PEPPER = "JBl_u3#Pozo$2026_MasterKey";

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
        system_session = SystemSession.getInstancia();
        try {
            // [1] ENCRIPTAR EL USUARIO DE FORMA DETERMINISTA PARA LA BÚSQUEDA INDEXADA
            String secureUser = DeterministicCrypto.encryp(user, SYSTEM_PEPPER);
            // Buscamos en la base de datos únicamente pasando el usuario cifrado en Base64
            // Nota: Debes asegurarte de que tu método employee_dao.get reciba solo el usuario, o modificarlo
            Optional<EmployeeUserDTO> option = employee_dao.get(connection, secureUser);

            res = option.isEmpty();
            if (res) {
                log("INTENTO DE INICIO DE SESION FALLIDO (USUARIO INEXISTENTE): USUARIO=%s".formatted(user));
                user_message = "USUARIO O CONTRASEÑA INCORRECTOS"; // Mensaje genérico por seguridad
                error_code = 404;
                return false;
            }

            // Recuperamos el DTO del empleado encontrado
            EmployeeUserDTO employee = option.get();

            // [2] VALIDACIÓN DE LA CONTRASEÑA USANDO LA FUNCIÓN COMPARATIVA DE BCRYPT
            // El DTO recupera el hash almacenado en la columna 'password' de MySQL
            String dbPasswordHash = employee.getPassword();

            // Comparamos el texto plano de la vista contra el hash dinámico de la BD
            boolean isPasswordCorrect = BCryptCrypto.equalsEncryp(password, dbPasswordHash, null);

            if (!isPasswordCorrect) {
                log("INTENTO DE INICIO DE SESION FALLIDO (CONTRASEÑA ERRÓNEA): USUARIO=%s".formatted(user));
                user_message = "USUARIO O CONTRASEÑA INCORRECTOS";
                error_code = 401;
                return false;
            }

            // Removemos el hash del password de la memoria por seguridad una vez validado
            employee.put("password", null);

            // GUARDAR EMPLEADO EN LA SESIÓN GLOBAL DEL SISTEMA
            system_session.setUser(employee);
            dto.put("employee_id", employee.getId());

            // Control de concurrencia de sesiones activas
            res = session_dao.haveActiveSession(connection, employee.getId());
            if (res) {
                int session_id_old = history_dao.logout(connection, "CERRAR SESSION ABIERTA AUTOMÁTICAMENTE POR NUEVA AUTENTICACIÓN");
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

            // [3] REGISTRO EN BITACORA DE HISTORIAL
            int session_id = history_dao.login(connection, "INICIO DE SESIÓN");
            dto.put("history_start_id", String.valueOf(session_id));
            res = session_id > 0;
            if (!res) {
                throw new LoginFailedException();
            }

            // [4] REGISTRO DE SESION EN INFRAESTRUCTURA
            session_dao.insert(connection, dto);

            // [5] INFORMACION DE SESSION
            String db_user = history_dao.currentUser(connection);

            // GUARDAR ADMINISTRACION ACTUAL
            AdministrationHistoryDTO currentAdministration = administration_history_dao.getCurrentAdministration(connection);
            system_session.setCurrentAdministration(currentAdministration);

            // GUARDAR USUARIO DE BASE DE DATOS ASIGNADO Y SESIÓN ATÓMICA
            system_session.setCurrentDbUser(db_user);
            system_session.setCurrentSession(dto);

            // Validaciones defensivas de consistencia de sesión en RAM
            res = Func.isNotNull(SystemSession.getInstancia().getCurrentEmployee());
            if (!res) {
                throw new LoginFailedException(4, "EL USUARIO DE SESION NO SE GUARDO CORRECTAMENTE");
            }

            res = Func.isNotNull(SystemSession.getInstancia().getCurrentDbUser());
            if (!res) {
                throw new LoginFailedException(6, "ERROR DE SERVIDOR");
            }

            res = Func.isNotNull(SystemSession.getInstancia().getCurrent_instance());
            if (!res) {
                throw new LoginFailedException(7, "CREDENCIALES DE ADMINISTRACION NO VALIDAS");
            }

            log("INICIO DE SESION EXITOSO: USUARIO=%s".formatted(user));
            system_session.put("user-session", user);
            connection.commit();
            res = true; // Retornamos verdadero tras completar con éxito la transacción atómica

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
            SystemSession.setNull();
            connection.commit();
            res = true;
        } catch (SQLException ex) {
            user_message = "FIN DE SESION FALLIDO";
            error_code = -1;
            res = false;
            log(ex, "logout");
            connection.rollBack();
        } catch (DataAccesObjectException ex) {
            user_message = "FIN DE SESION FALLIDO";
            error_code = ex.getErrorCode();
            res = false;
            log(ex, "logout");
            connection.rollBack();
        } catch (ServiceException ex) {
            user_message = ex.getUserMessage();
            error_code = ex.getErrorCode();
            res = false;
            log(ex, "logout");
            connection.rollBack();
        } catch (Exception ex) {
            user_message = "FIN DE SESION FALLIDO";
            error_code = -3;
            res = false;
            log(ex, "logout");
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    public void log(Exception e, String method_name) {
        FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, getProcess_name(), method_name, e.getMessage());
    }

    public void log(String message) {
        FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, "MAIN", message);
    }
}
