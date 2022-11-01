/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jblue;

import com.jblue.sistema.Sistema;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author jp
 *
 */
public class JBlue {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        Sistema s = Sistema.getInstancia();
        if (s.archivosSistema()) {
            s.conexionBD();
            s.datosCache();
            s.run();
        }

//        Properties p = System.getProperties();
//        p.forEach((t, u) -> {
//            System.out.println(t + ": " +u);
//        });
    }

}
