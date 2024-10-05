/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template6
 */
package jblue;

import com.jblue.sistema.ConstSisMen;
import com.jblue.sistema.Sistema;

/**
 * java
 *
 * @author jp
 *
 */
public class JBlue {

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) {
        Sistema s = Sistema.getInstancia();
        try {
            do {
                synchronized (s) {
                    System.out.println(s._ComprobarVersion()
                            ? ConstSisMen.MEN_JAVA_VERSION_OK : ConstSisMen.MEN_JAVA_VERSION_ERR);
                    System.out.println(s._CargarArchivos()
                            ? ConstSisMen.MEN_ARCHIVOS_OK : ConstSisMen.MEN_ARCHIVOS_ERR);
                    System.out.println(s._ConexionBD()
                            ? ConstSisMen.MEN_CONEXION_BD_OK : ConstSisMen.MEN_CONEXION_BD_ERR);
                    System.out.println(s._Run()
                            ? ConstSisMen.MEN_RUN_OK : ConstSisMen.MEN_RUN_ERR);
                    s.wait();
                }
            } while (s.isReinicio());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("FIN DEL PROGRAMA");
        s.cerrarTodo();
        s._Stop();
    }
}
