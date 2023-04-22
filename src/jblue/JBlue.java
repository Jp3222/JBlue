/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jblue;

import com.jblue.sistema.Sistema;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 *
 */
public class JBlue {

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) {
        //validando la version de java
        String versionStr = System.getProperty("java.version");
        CharSequence subsecuencia = versionStr.subSequence(0, 2);

        int versionInt = Integer.parseInt(subsecuencia.toString());
        if (versionInt < 17) {
            JOptionPane.showMessageDialog(null, "Version de java no valida");
            return;
        }                   
        //Inicio del sistema
        Sistema s = Sistema.getInstancia();
        do {

            if (!s.archivosSistema()) {
                System.out.println("ERROR EN LOS ARCHIVOS DEL PROGRAMA");
                break;
            }
            System.out.println("¡¡¡ARCHIVOS DEL PROGRAMA LISTOS!!!");

            if (!s.conexionBD()) {
                System.out.println("ERROR EN La CONEXION A LA BD");
                break;
            }
            System.out.println("¡¡¡CONEXION BD LISTA!!!");

            if (!s.run()) {
                System.out.println("ERROR AL CORRER EL PROGRAMA");
            }
            System.out.println("¡¡¡TODO OK!!!");

            if (s.isReinicio()) {
                System.gc();
                System.out.println("REINICIANDO EL PROGRAMA");
            }

        } while (s.isReinicio());

    }

}
