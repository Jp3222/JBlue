/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template6
 */
package jsoftware.com.jblue;

import jsoftware.com.jblue.model.constants.des.ConstainsMod;
import jsoftware.com.jblue.sys.JBlueMainSystem;
import jsoftware.com.jblue.util.LoggerRegister;
import jsoftware.com.jutil.sys.LaunchApp;

/**
 * Clase principal del proyecto JBlue.
 * <br>
 * Nota 1: En esta clase se puede alterar la version de las clases debido a la
 * migracion a maven
 * <br>
 * Nota 2: Esta version esta totalmente rota, no usar
 * 1 - JUtilidades
 *
 * @author jp
 * @version 2.0
 */
public class JBlue {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String... args) {
        /**
         * En caso de no creer necesitar reinicios en el sistema usar el metodo
         * "run"
         *
         * Nota: recuerda que en caso de que la clase de configuracion deje de
         * funcionar, siempre puedes crear una nueva y extender de "MainSystemn"
         * para probar distintas configuraciones
         */
        try {
            //LoggerRegister.logInfoWriter(ConstainsMod.PROGRAM, JBlue.class, "MAIN", "INICIO DEL SISTEMA");
            JBlueMainSystem jBlueMainSystem = new JBlueMainSystem();
            LaunchApp.getInstance(jBlueMainSystem).doWhileRun();
            //System.out.println("fin");
            //LoggerRegister.logInfoWriter(ConstainsMod.PROGRAM, JBlue.class, "MAIN", "FIN DEL SISTEMA");
        } catch (InterruptedException e) {
            LoggerRegister.logErrorWriter(ConstainsMod.PROGRAM, JBlue.class, e, "main");
        }

    }

}
