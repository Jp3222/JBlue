/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jbd.Exeption.ExeptionPrinter;
import com.jblue.vista.ventanas.Login;
import com.jbd.conexion.Conexion;
import com.jblue.sistema.so.SoConfig;
import com.jblue.sistema.so.SoLinux;
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

    SoConfig so;

    private Sistema() {

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

    public void conexionBD() {
        try {
            Conexion cn = Conexion.getInstancia("jp", "12345", "jdbc:mysql://localhost/jblue");
            if (cn.getCn().isClosed()) {
                System.out.println("¡¡¡Conexion ok!!!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void datosCache() {
    }

    public void run() {
        Runnable runnable = () -> {
            Login log = new Login();
            log.setVisible(true);
        };
        Thread th = new Thread(runnable, "j-blue");
        th.start();
    }

}
