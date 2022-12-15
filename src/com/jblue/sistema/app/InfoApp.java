/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.app;

import javax.swing.ImageIcon;

/**
 *
 * @author jp
 */
public interface InfoApp {

    String NOMBRE = "jblue ",
            VERSION = "0.0";
    
    String[] SECCION = {
        "Inicio de sesion", "Menu Principal", "Menu sobre Base de datos"
    };
    ImageIcon ICONO = new ImageIcon(InfoApp.class .getResource("/com/jblue/media/img/x128/jblue_icono.png"));
}
