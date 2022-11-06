/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jblue;

import com.jblue.sistema.Sistema;

/**
 *
 * @author jp
 *
 */
public class JBlue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sistema s = Sistema.getInstancia();
        if (s.archivosSistema()) {
            s.conexionBD();
            s.datosCache();
            s.run();
        }
    }

}
