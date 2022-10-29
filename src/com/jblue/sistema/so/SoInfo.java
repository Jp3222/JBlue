/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import java.io.File;

/**
 *
 * @author jp
 */
public interface SoInfo {

    final String SO_NOMBRE = System.getProperty("so.name");
    final String RUTA_DE_USUARIO = System.getProperty("user.home");
    final String LENGUAJE_DEL_SISTEMA = System.getProperty("user.languaje");

}
