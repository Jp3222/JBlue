/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template6
 */
package jblue;

import com.jblue.sistema.DevFlags;
import com.jblue.sistema.Sistema;
import com.jblue.sistema.console.ReadConsole;
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
         * Nota: Poner la bandera en false antes de subir una version final del
         * sistema
         */
        DevFlags.ALL_FLAGS = false;
        if (args.length > 0) {
            for (String i : args) {
                String[] split = i.split(",");
                ReadConsole.setFlags(split[0], split[1]);
            }
        }

        /**
         * En caso de no creer necesitar reinicios en el sistema usar el metodo
         * "run"
         *
         * Nota: recuerda que en caso de que la clase de configuracion deje de
         * funcionar, siempre puedes crear una nueva y extender de "MainSystemn"
         * para probar distintas configuraciones
         */
        LaunchApp.getInstance(new Sistema()).doWhileRun();
    }
}
