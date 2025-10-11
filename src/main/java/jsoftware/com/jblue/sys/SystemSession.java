/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.sys;

import jsoftware.com.jblue.model.daos.HysHistoryDAO;
import jsoftware.com.jblue.model.dtos.AdministrationHistoryObject;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jutil.dbcon.connection.JDBConnection;
import jsoftware.com.jutil.framework.LaunchApp;
import jsoftware.com.jutil.framework.LocalSession;
import jsoftware.com.jutil.jexception.JExcp;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Esta clase define al personal de sesion actual, quien hace uso del programa,
 * por lo cual esta clase solo se puede instanciar una vez
 *
 * @author jp
 */
public class SystemSession implements LocalSession<OEmployee> {

    private static SystemSession instancia;

    /**
     * Retorna una unica instancia de la clase Sesion
     *
     * @return
     */
    public static synchronized SystemSession getInstancia() {
        if (instancia == null) {
            instancia = new SystemSession();
        }
        return instancia;
    }

    /**
     * Empleado que ha iniciado session
     */
    private OEmployee current_employee;
    private AdministrationHistoryObject current_administration;

    private final JDBConnection connection;

    private SystemSession() {
        connection = (JDBConnection) LaunchApp.getInstance().getResources("connection");
    }

    public OEmployee getCurrentEmployee() {
        return current_employee;
    }

    public AdministrationHistoryObject getCurrentAdministration() {
        return current_administration;
    }

    public void getWarnings() {
        StringBuilder sb = new StringBuilder(255);
        if (getCurrentEmployee() == null) {
            sb.append("El usuario no se ha registrado correctamente, No podra hacer registro algunos");
        }

        if (getCurrentAdministration() == null) {
            sb.append("La administracion actual no ha sido registrada, no podra hacer registro alguno");
        }
        
        if (!sb.isEmpty()) {
            JOptionPane.showConfirmDialog(null,
                    sb.toString(),
                    "Advertencias",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    @Override
    public boolean isOpen() {
        return current_employee != null;
    }

    @Override
    public void writer() {
    }

    @Override
    public void setUser(OEmployee user) {
        connection.setAutoCommit(false);
        try {
            String description = user == null ? "FIN DE SESION" : "INICIO DE SESIÓN";
            boolean register;
            if (user == null) {
                register = HysHistoryDAO.getINSTANCE().getHysEmployeeMovs().saveLogOut(current_employee, description);
            } else {
                register = HysHistoryDAO.getINSTANCE().getHysEmployeeMovs().saveLogin(user, description);

            }
            if (!register) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            current_employee = user;
        } catch (SQLException e) {
            connection.rollBack();
            JExcp.getInstance(false, true).print(e, getClass(), "setUser");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void load() {

    }

}
