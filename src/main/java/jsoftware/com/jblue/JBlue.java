/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template6
 */
package jsoftware.com.jblue;

import jsoftware.com.jblue.sys.JBlueMainSystem;
import jsoftware.com.jutil.framework.LaunchApp;

/**
 * java
 *
 * @author jp
 * @version 1.0
 */
public class JBlue {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     *
     */
    public static void main(String... args) throws InterruptedException {
        /**
         * En caso de no creer necesitar reinicios en el sistema usar el metodo
         * "run"
         *
         * Nota: recuerda que en caso de que la clase de configuracion deje de
         * funcionar, siempre puedes crear una nueva y extender de "MainSystemn"
         * para probar distintas configuraciones
         */
        JBlueMainSystem jBlueMainSystem = new JBlueMainSystem();
        LaunchApp.getInstance(jBlueMainSystem).doWhileRun();
    }

}
