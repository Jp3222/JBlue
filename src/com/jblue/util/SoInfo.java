/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import com.jutil.platf.So;
import java.io.File;

/**
 *
 * @author jp
 */
public class SoInfo {

    public static final String[] DICCIONARIO_DE_CARPETAS = getRutas();
    public static final String RUTA_ESCRITORIO = So.USER_HOME + File.separator + DICCIONARIO_DE_CARPETAS[0];
    public static final String RUTA_DOCUMENTOS = So.USER_HOME + File.separator + DICCIONARIO_DE_CARPETAS[1];

    public static String[] getRutas() {
        String escritorio = "Escritorio";
        String documentos = "Documentos";
        String diccionario[] = {escritorio, documentos};
        return diccionario;
    }

    private static String[] getRutasWindows() {
        return null;
    }

    private static String[] getRutasLinux() {
        return null;
    }

    private static String[] getRutasMac() {
        return null;
    }

    private String DESKTOP = "Desktop";
    private String DOCUMENTS = "Documents";

    private SoInfo() {
    }

}
