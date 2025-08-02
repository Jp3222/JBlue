/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template6
 */
package jblue;

import com.jblue.sys.JBlueMainSystem;
import com.jutil.framework.LaunchApp;
/**
 * java
 *
 * @author jp
 *
 */
public class JBlue {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
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
        //Sistema o = new Sistema();
        JBlueMainSystem jBlueMainSystem = new JBlueMainSystem();
        LaunchApp.getInstance(jBlueMainSystem).doWhileRun();
    }

}
