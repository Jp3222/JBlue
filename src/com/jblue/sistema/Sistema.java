/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.vista.conf.Apariencia;
import com.jblue.vista.ventanas.Login;

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
