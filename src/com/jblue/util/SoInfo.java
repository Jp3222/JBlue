/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import java.io.File;

/**
 *
 * @author jp
 */
public class SoInfo {

    public static final String SO_NOMBRE = System.getProperty("os.name");
    public static final String RUTA_DE_USUARIO = System.getProperty("user.home");
    public static final String LENGUAJE_DEL_SISTEMA = System.getProperty("user.language");
    //
    public static final String[] DICCIONARIO_DE_CARPETAS = getRutas();
    public static final String RUTA_ESCRITORIO = RUTA_DE_USUARIO + File.separator + DICCIONARIO_DE_CARPETAS[0];
    public static final String RUTA_DOCUMENTOS = RUTA_DE_USUARIO + File.separator + DICCIONARIO_DE_CARPETAS[1];

    public static String[] getRutas() {
        String documentos = null, escritorio = null;
        if (SO_NOMBRE.contains("windows") || LENGUAJE_DEL_SISTEMA.equals("en")) {
            escritorio = "Desktop";
            documentos = "Documents";
        } else if (LENGUAJE_DEL_SISTEMA.equals("es")) {
            escritorio = "Escritorio";
            documentos = "Documentos";
        }
        String DICCIONARIO[] = {escritorio, documentos};
        return DICCIONARIO;

    }

    //
    private SoInfo() {
    }

}
