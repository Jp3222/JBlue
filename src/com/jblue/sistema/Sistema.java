/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.vista.conf.Apariencia;
import com.jblue.vista.ventanas.Login;
import com.jbd.conexion.Conexion;
import com.jblue.util.crypto.EncriptadoAES;

/**
 *
 * @author jp
 */
public class Sistema {

    private final String SO;

    public Sistema() {
        SO = System.getProperty("os.name");
        Apariencia apariencia = new Apariencia(SO);
        apariencia.aparienciaPorDefecto();
        if (init()) {
            System.out.println("Conexion OK");
        }
    }

    private boolean init() {
        EncriptadoAES o = new EncriptadoAES();
        
        String key;
        String jp;
        String pass;
        String url;
        Conexion cn = Conexion.getInstancia("jp", "12345", "jdbc:mysql://localhost/jblue");
        return true;
    }

    public void conexionBD() {
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
