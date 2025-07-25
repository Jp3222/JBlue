/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.modelo.constdb.Const;
import com.jblue.modelo.objetos.OEmployee;
import com.jutil.dbcon.connection.DBConnection;
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
public class Sesion implements LocalSession<OEmployee> {

    private static Sesion instancia;

    /**
     * Retorna una unica instancia de la clase Sesion
     *
     * @return
     */
    public static synchronized Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    /**
     * Empleado que ha iniciado session
     */
    private OEmployee personal;
    private final DBConnection connection;
    private final String query;

    private Sesion() {
        connection = (DBConnection) LaunchApp.getInstance().getResources("connection");
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
        String id = "1";
        String description = "INICIO DE SESIÓN";
        if (user == null) {
            id = personal.getId();
            description = "FIN DE SESIÓN";
        }
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
