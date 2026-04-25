/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template6
 */
package jsoftware.com.jblue;

import java.io.IOException;
import jsoftware.com.jblue.sys.JBlueMainSystem;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jutil.sys.LaunchApp;
import jsoftware.com.jutil.util.FuncLogs;

/**
 * Clase principal del proyecto JBlue.
 * <br>
 * Nota 1: El proyecto fue migrado y corregido.
 * <br>
 * Nota 2: Esta version esta incompleta pero ya es funciona, al igual que la lib
 * JUtilidades
 * <br>
 * Nota 3: Para futuras versiones, se pretende separar la capa de DAO's y DTO's
 * de este proyecto
 *
 * @author jp
 * @version 3.0
 */
public class JBlue {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String... args) throws IOException {
        /**
         * En caso de no creer necesitar reinicios en el sistema usar el metodo
         * "run"
         *
         * Nota: recuerda que en caso de que la clase de configuracion deje de
         * funcionar, siempre puedes crear una nueva y extender de "MainSystemn"
         * para probar distintas configuraciones
         */
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, "MAIN", "INICIO DEL SISTEMA DESDE JBLUE");
            JBlueMainSystem jBlueMainSystem = new JBlueMainSystem();
            LaunchApp.getInstance(jBlueMainSystem).doWhileRun();
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, "MAIN", "FIN DEL SISTEMA DESDE JBLUE");
        } catch (IOException | InterruptedException e) {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, JBlue.class, e, "MAIN", "main", e.getMessage());
        }

    }

}
