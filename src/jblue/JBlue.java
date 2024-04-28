/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template6
 */
package jblue;

import com.jblue.sistema.ConstSisMen;
import com.jblue.sistema.Sistema;
<<<<<<< HEAD
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
=======
import com.jutil.jexception.Excp;
>>>>>>> 8f3a8b17d2aa82b96abe53ac5619fbd9bd52515c

/**java
 *
 * @author jp
 *
 */
public class JBlue {

    private static final Logger LOG = Logger.getLogger(JBlue.class.getName());
    /**
     * @param args the command line arguments
     */
<<<<<<< HEAD
    public static void main(String[] args) throws IOException {
//        Sistema s = Sistema.getInstancia();
//        if (s.archivosSistema()) {
//            s.conexionBD();
//            s.datosCache();
//            s.run();
//        }

        
=======
    public static void main(String... args) {
        Sistema s = Sistema.getInstancia();
        do {

            try {
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
            } catch (InterruptedException ex) {
                Excp.imp(ex, JBlue.class, true, true);
            }

        } while (s.isReinicio());
        System.out.println("FIN DEL PROGRAMA");
        s.cerrarTodo();
        s._Stop();
>>>>>>> 8f3a8b17d2aa82b96abe53ac5619fbd9bd52515c
    }

}
