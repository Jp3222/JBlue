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

    final String NOMBRE_DEL_PROGRAMA = "JBlue ";
    final String VERSION_DEL_PROGRAMA = "02.01.";
    final String TITULO_VER_PROGRAMA = String.format("%s - %s", NOMBRE_DEL_PROGRAMA, VERSION_DEL_PROGRAMA);
    
    final ImageIcon _ICONO_DEL_PROGRAMA = new ImageIcon(AppInfo.class.getResource("/com/jblue/media/img/x128/jblue_icono.png"));

}
