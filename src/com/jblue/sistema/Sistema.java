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

    private final static Sistema instancia = new Sistema();

    public synchronized static Sistema getInstancia() {
        return instancia;
    }

    private SoConfig so;
    private boolean reinicio;
    private Conexion cn;

    private Sistema() {
        this.so = new SoConfig();
        this.reinicio = false;
    }

    public boolean archivosSistema() {
        so = new SoConfig();
        so.cargar();
        if (!so.getCDA().get(so.getCDA().ARCHIVO, 0).exists()) {
            so.construir();
            return false;
        }
        return true;
    }

    public boolean conexionBD() {
        try {
            cn = Conexion.getInstancia("jp", "12345", "jdbc:mysql://localhost/jblue");
            if (cn.getCn().isClosed()) {
                System.out.println("¡¡¡Conexion ok!!!");
            }
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

    }

    public boolean isReinicio() {
        return reinicio;
    }

    public void setReinicio(boolean reinicio) {
        this.reinicio = reinicio;
    }

    public Conexion getCn() {
        return cn;
    }

}
