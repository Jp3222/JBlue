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
public class Prop {

    /**
     * Carpeta oculta para la administracion de informacion
     */
    public static final String DIR_PROG = SoInfo.RUTA_DOCUMENTOS + "/.jblue";

    /**
     * Carpeta en la que se guarda informacion del usuario.
     * <br> Fotos
     * <br> Documentos
     * <br> Etc...
     */
    public static final String DIR_PROG_USUARIOS = DIR_PROG + "/Usuarios";

    /**
     * Carpeta en la que se guarda informacion del personal.
     * <br> Fotos
     * <br> Documentos
     * <br> Etc...
     */
    public static final String DIR_PROG_PERSONAL = DIR_PROG + "/Personal";

    /**
     *
     */
    public static final String DIR_ARC_CONFIG = DIR_PROG + "/config.txt";

    /**
     * Carpeta visible para los archivos generados
     */
    public static final String DIR_USU = SoInfo.RUTA_DOCUMENTOS + "/JBlue";

    /**
     *
     */
    public static final File FIL_DIR_PROG = new File(DIR_PROG);
    /**
     *
     */
    public static final File FIL_DIR_PROG_USUARIOS = new File(DIR_PROG_USUARIOS);
    /**
     *
     */
    public static final File FIL_DIR_PROG_PERSONAL = new File(DIR_PROG_PERSONAL);
    /**
     *
     */
    public static final File FIL_ARC_CONFIG = new File(DIR_ARC_CONFIG);

    public static final File ALL_DIR[] = {
        FIL_DIR_PROG, FIL_DIR_PROG_PERSONAL, FIL_DIR_PROG_USUARIOS
    };

    public static final File ALL_ARC[] = {
        FIL_ARC_CONFIG
    };

}
