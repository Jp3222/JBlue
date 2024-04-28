/*
 * Copyright (C) 2024 juan-campos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.sistema;

import com.jblue.util.SoInfo;
import java.io.File;

/**
 *
 * @author juan-campos
 */
public class ConstSisMen {

    public static final String MEN_JAVA_VERSION_OK = "VERSION DE JAVA CORRECTA";
    public static final String MEN_JAVA_VERSION_ERR = "VERSION DE JAVA ERRONEA";
    public static final String MEN_CONEXION_BD_OK = "CONEXION ESTABLECIDA";
    public static final String MEN_CONEXION_BD_ERR = "CONEXION ERRONEA";
    public static final String MEN_CACHE_OK = "CACHE CARGADA";
    public static final String MEN_CACHE_ERR = "ERROR AL CARGAR CACHE";
    public static final String MEN_ARCHIVOS_OK = "ARCHIVOS CARGADOS";
    public static final String MEN_ARCHIVOS_ERR = "ERROR AL CARGAR ARCHIVOS";
    public static final String MEN_RUN_OK = "OK";
    public static final String MEN_RUN_ERR = "ERR";
    
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
