/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.app;

import com.jblue.util.SoInfo;
import java.io.File;

/**
 *
 * @author jp
 */
public class AppFiles {

    //-- directorios programa usuario--//
    public static final String DIR_PROG = constURL(SoInfo.RUTA_DOCUMENTOS, ".jblue");

    public static final String DIR_PROG_USUARIOS = constURL(DIR_PROG, "Usuarios");

    public static final String DIR_PROG_PERSONAL = constURL(DIR_PROG, "Personal");

    public static final String DIR_PROG_MULTIMEDIA = constURL(DIR_PROG, "Multimedia");

    public static final String DIR_PROG_ARC_CONFIG = constURL(DIR_PROG, "jblue.config");

    public static final String[] ARR_DIR_PROG = {
        DIR_PROG, DIR_PROG_USUARIOS, DIR_PROG_PERSONAL, DIR_PROG_MULTIMEDIA
    };

    public static final String[] ARR_PROG_ARC = {
        DIR_PROG_ARC_CONFIG
    };
    
    //-- directorios del usuario--//
    public static final String DIR_USER = constURL(SoInfo.RUTA_DOCUMENTOS, "JBlue");

    public static final String DIR_USER_PAGOS = constURL(DIR_USER, "Pagos");

    public static final String DIR_USER_REPORTES = constURL(DIR_USER, "Reportes");

    public static final String DIR_USER_PDFS = constURL(DIR_USER, "PDFs");

    public static final String[] ARR_DIR_USER = {
        DIR_USER, DIR_USER_PAGOS, DIR_USER_REPORTES, DIR_USER_PDFS
    };

    public static final File FIL_DIR_PROG = new File(DIR_PROG);

    public static final File FIL_DIR_PROG_USUARIOS = new File(DIR_PROG_USUARIOS);

    public static final File FIL_DIR_PROG_PERSONAL = new File(DIR_PROG_PERSONAL);

    public static final File FIL_ARC_CONFIG = new File(DIR_PROG_ARC_CONFIG);

    public static final File ALL_DIR[] = {
        FIL_DIR_PROG, FIL_DIR_PROG_PERSONAL, FIL_DIR_PROG_USUARIOS
    };

    public static final File ALL_ARC[] = {
        FIL_ARC_CONFIG
    };

    private static String constURL(String... str) {
        StringBuilder sb = new StringBuilder(100);
        int size = str.length;
        for (int i = 0; i < size - 1; i++) {
            sb.append(str[i]).append(File.separator);
        }
        sb.append(str[size - 1]);
        return sb.toString();
    }
}
