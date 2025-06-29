/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.modelo.objetos.OEmployee;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.framework.LaunchApp;
import com.jutil.framework.LocalSession;
import java.sql.SQLException;

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
        this.query = "INSERT INTO history(employee, type, description) VALUES(%s,%s,%s)";
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
        personal = user;
    }

    public void setMov(int type, String description) {
        try {
            connection.query(query.formatted(personal.getId(), type, description));
        } catch (SQLException ex) {
            SystemLogs.severeDbLogs("Error al registrar un movimiento", ex);
        }
    }

}
