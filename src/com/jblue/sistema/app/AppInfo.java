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
public interface AppInfo {

    final String NOMBRE_DEL_PROGRAMA = "jblue ",
            VERSION_DEL_PROGRAMA = "1.0",
            TITULO_VER_PROGRAMA = String.format("%s - %s", NOMBRE_DEL_PROGRAMA, VERSION_DEL_PROGRAMA);
    /**
     * <br> 0 - "nueva ventana"
     * <br> 1 - "Inicio de sesion"
     * <br> 2 - "Menu Principal"
     * <br> 3 - "BD usuarios"
     * <br> 4 - "BD calles"
     * <br> 5 - "BD tipo de tomas"
     * <br> 6 - "Menu Tesoreria"
     * <br> 7 - "Menu Presidente"
     * <br> 8 - "Tomas Registradas"
     * <br> 9 - "Perfil de Usuario"
     * <br> 10 - "Administrador de Directorios"
     * <br> 11 - "Administrador de Documentos"
     * <br> 12 - "Comprobantes"
     * <br> 13 - "Administrador"
     */
    final String[] _MENUS_DEL_PROGRAMA = {
        "Nueva ventana",
        "Inicio de sesion",
        "Menu principal",
        "BD Usuarios",
        "BD Calles",
        "BD Tipo de Tomas",
        "Tesorero",
        "Presidente",
        "Tomas Registradas",
        "Perfil de Usuario",
        "Administrador de Directorios",
        "Administrador de Documentos",
        "Comprobantes",
        "Administrador"
    };

    final ImageIcon _ICONO_DEL_PROGRAMA = new ImageIcon(AppInfo.class.getResource("/com/jblue/media/img/x128/jblue_icono.png"));

}
