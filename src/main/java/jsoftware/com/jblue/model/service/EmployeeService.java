/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.EmployeeDAO;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.JFunc;

/**
 *
 * @author juanp
 */
public class EmployeeService {

    private EmployeeDAO employee_dao;
    private HysHistoryDAO history_dao;

    public EmployeeService() {
    }

    public EmployeeDTO logIn(JDBConnection connection, String user, String password) {
        EmployeeDTO get = null;
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            get = employee_dao.get(connection, user, password);
            res = JFunc.isNotNull(res);
            if (!res) {
                throw new SQLException("BUSQUEDA CORRUPTA");
            }
            res = history_dao.getHysEmployeeMovs().saveLogin(get, "INICIO DE SESION");
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException ex) {
            connection.rollBack();
            System.getLogger(EmployeeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            connection.setAutoCommit(true);
        }
        return get;
    }

    public boolean logOut(JDBConnection connection, EmployeeDTO employee) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            res = history_dao.getHysEmployeeMovs().saveLogOut(employee, "FIN DE SESION");
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException ex) {
            connection.rollBack();
            System.getLogger(EmployeeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
