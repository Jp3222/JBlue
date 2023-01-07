/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jbd.Exeption.ExeptionPrinter;
import com.jblue.vista.ventanas.Login;
import com.jbd.conexion.Conexion;
import com.jblue.sistema.so.SoConfig;
import java.sql.SQLException;

/**
 * Sistema
 * <br> Esta clase es encargada de iniciar todos los datos del sistema
 *
 * @author jp
 */
public class Sistema implements ExeptionPrinter {

    private static Sistema instancia;

    public synchronized static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    private SoConfig so;
    private Conexion cn;
    private boolean reinicio;

    private Sistema() {
        this.reinicio = false;
    }

    public boolean archivosSistema() {
        
        return true;
    }

    public boolean confgSistema() {
        return true;
    }

    public boolean conexionBD() {
        try {
            cn = Conexion.getInstancia("jp", "12345", "jdbc:mysql://localhost/jblue");
            if (cn.getCn().isClosed()) {
                System.out.println("¡¡¡Conexion ok!!!");
            }
            System.out.println("CONEXION A BASE DE DATOS");
            return !cn.getCn().isClosed();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void datosCache() {
    }

    public synchronized void run() {
        this.reinicio = false;
        Login log = new Login();
        log.setVisible(true);
        System.out.println("¡¡¡TODO OK!!!");
    }

    public boolean isReinicio() {
        if (reinicio) {
            System.out.println("REINICIANDO SISTEMA");
        }
        return reinicio;
    }

    public void setReinicio(boolean reinicio) {
        this.reinicio = reinicio;
    }

    public Conexion getCn() {
        return cn;
    }

}
