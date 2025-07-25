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

    final String PROGRAM_NAME = "JBlue ";
    final String PROGRAM_VERSION = "07.25";
    final String PROGRAM_TITLE = String.format("%s - %s", PROGRAM_NAME, PROGRAM_VERSION);

    final ImageIcon PROGRAM_ICON = new ImageIcon(AppInfo.class.getResource("/com/jblue/media/img/x128/jblue_icono.png"));

    final String UPDATE_DATE = "13/07/2025";
}
