/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.sys.app;

import jsoftware.com.jblue.util.plataformas.OsConfig;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class AppFiles {

    private static final OsConfig os = OsConfig.getDefaultOsConfig();
    //-- directorios programa usuario--//
    public static final String DIR_PROG = constURL(os.getDocumentos(), ".jblue");

    public static final String DIR_PROG_USUARIOS = constURL(DIR_PROG, "Usuarios");

    public static final String DIR_PROG_PERSONAL = constURL(DIR_PROG, "Personal");

    public static final String DIR_PROG_MULTIMEDIA = constURL(DIR_PROG, "Multimedia");

    public static final String DIR_PROG_LOG = constURL(DIR_PROG, "Logs");

    public static final String DIR_PROG_LOG_TODAY = constURL(DIR_PROG_LOG, LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")));

    public static final String[] S_ALL_DIR_PROG = {
        DIR_PROG, DIR_PROG_USUARIOS, DIR_PROG_PERSONAL, DIR_PROG_MULTIMEDIA, DIR_PROG_LOG, DIR_PROG_LOG_TODAY
    };

    /**
     * archivo de configuracion
     */
    public static final String DIR_PROG_ARC_CONFIG = constURL(DIR_PROG, "jblue.xml");
    /**
     * archivo para logs del sistema
     */
    public static final String DIR_PROG_ARC_SYS_LOG = constURL(DIR_PROG_LOG_TODAY, "sys.log");
    /**
     * archivo para logs de los modulos
     */
    public static final String DIR_PROG_ARC_MOD_LOG = constURL(DIR_PROG_LOG_TODAY, "mod.log");
    /**
     * archivo para logs de la base de datos
     */
    public static final String DIR_PROG_ARC_DB_LOG = constURL(DIR_PROG_LOG_TODAY, "db.log");
    /**
     * archivo para logs desconocidos
     */
    public static final String DIR_PROG_ARC_UKW_LOG = constURL(DIR_PROG_LOG_TODAY, "ukw.log");

    public static final String[] S_ARR_PROG_ARC = {
        DIR_PROG_ARC_CONFIG, DIR_PROG_ARC_SYS_LOG, DIR_PROG_ARC_MOD_LOG, DIR_PROG_ARC_DB_LOG
    };

    //-- directorios del usuario--//
    public static final String DIR_USER = constURL(os.getDocumentos(), "JBlue");

    public static final String DIR_USER_PAGOS = constURL(DIR_USER, "Pagos");

    public static final String DIR_USER_REPORTES = constURL(DIR_USER, "Reportes");

    public static final String DIR_USER_PDFS = constURL(DIR_USER, "PDFs");

    public static final String[] S_ARR_DIR_USER = {
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
