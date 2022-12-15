/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jblue;

import com.jblue.sistema.Sistema;
import java.io.File;

/**
 *
 * @author jp
 *
 */
public class JBlue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Sistema s = Sistema.getInstancia();
        if (s.archivosSistema()) {
            if (s.archivosSistema()) {
                System.out.println("Archivos del sistema OK!!!");
                if (s.conexionBD()) {
                    System.out.println("Conexion a base de datos OK!!!");
                    if (s.run()) {
                        System.out.println("Programa en ejecucion OK!!!");
                    }
                }
            }

        }

    }

}
