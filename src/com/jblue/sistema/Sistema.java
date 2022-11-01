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
 * Sistema
 * <br> Esta clase es encargada de iniciar todos los datos del sistema
 *
 * @author jp
 */
public class Sistema {

    private final static Sistema instancia = new Sistema();

    public synchronized static Sistema getInstancia() {
        return instancia;
    }
   
    
    private final String SO;

    private Sistema() {
        SO = System.getProperty("os.name");
        Apariencia apariencia = new Apariencia(SO);
        apariencia.aparienciaPorDefecto();
        init("");
    }

    private boolean init(String key) {
        Conexion cn = Conexion.getInstancia("jp", "12345", "jdbc:mysql://localhost/jblue");
        System.out.println(cn.toString());
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
