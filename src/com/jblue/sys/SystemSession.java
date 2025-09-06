/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sys;

import com.jblue.model.constants.Const;
import com.jblue.model.dtos.OEmployee;
import com.jutil.dbcon.connection.JDBConnection;
import com.jutil.framework.LaunchApp;
import com.jutil.framework.LocalSession;
import com.jutil.jexception.JExcp;
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
    private OEmployee personal;
    private final JDBConnection connection;
    private final String query;

    private SystemSession() {
        connection = (JDBConnection) LaunchApp.getInstance().getResources("connection");
        this.query = "INSERT INTO history(employee, db_user, type, description) VALUES('%s',(%s),'%s','%s')";
    }

    public OEmployee getUsuario() {
        return personal;
    }

    @Override
    public boolean isOpen() {
        return personal != null;
    }

    @Override
    public void writer() {
    }

    @Override
    public void setUser(OEmployee user) {
        String id = user == null ? personal.getId() : user.getId();
        String description = user == null ? "FIN DE SESION" : "INICIO DE SESIÓN";
        personal = user;
        register(id, Const.INSERT_LOGIN, description);
    }

    void register(String employee, int type, String description) {
        String sql_user = "SELECT current_user()";
        try {

            int execute = connection.execute(query.formatted(employee, sql_user, type, description));
            if (execute == 0) {
                JOptionPane.showMessageDialog(null, "Error al registrar la bitacora");
            }
        } catch (SQLException ex) {
            JExcp.getInstance(false, true).show(ex, getClass());
        }
    }

    public void register(int type, String description) {
        register(personal.getId(), type, description);
    }

}
