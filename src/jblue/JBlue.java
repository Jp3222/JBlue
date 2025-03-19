/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template6
 */
package jblue;

import com.jblue.sistema.Sistema;
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
        LaunchApp.getInstance(new Sistema()).doWhileRun();
    }
}
