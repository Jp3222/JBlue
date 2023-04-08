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

    final String NOMBRE_DEL_PROGRAMA = "jblue ",
            VERSION_DEL_PROGRAMA = "0.0";

    /**
     * <br> 0 "nueva ventana"
     * <br> 1 "Inicio de sesion"
     * <br> 2 "Menu Principal"
     * <br> 3 "BD usuarios"
     * <br> 4 "BD calles"
     * <br> 5 "BD tipo de tomas"
     * <br> 6 "Menu Tesoreria"
     * <br> 7 "Menu Presidente"
     */
    final String[] MENUS_DEL_PROGRAMA = {
        "Nueva ventana",
        "Inicio de sesion",
        "Menu principal",
        "Menu BD de usuarios",
        "Menu BD calles",
        "Menu BD tipo de tomas",
        "Tesorero",
        "Presidente"
    };

    final ImageIcon ICONO_DEL_PROGRAMA = new ImageIcon(InfoApp.class.getResource("/com/jblue/media/img/x128/jblue_icono.png"));

}
