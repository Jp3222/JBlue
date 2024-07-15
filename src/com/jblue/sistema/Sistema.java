/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.sistema.app.AppFiles;
import com.jblue.util.cache.FabricaCache;
import com.jblue.vista.componentes.NewMenuConfigBD;
import com.jblue.vista.ventanas.Login;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import com.jutil.platf.So;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Sistema
 * <br> Esta clase es encargada de iniciar todos los datos del sistema
 *
 * @author jp
 */
public class Sistema {

    private static Sistema instancia;

    public synchronized static Sistema getInstancia() {
        if (instancia == null) {
            try {
                instancia = new Sistema();
            } catch (IOException ex) {
                Excp.impTerminal(ex, Sistema.class, true);
            }
        }
        return instancia;
    }

    /**
     *
     */
    private final Properties propiedades;
    private boolean reinicio;
    private Conexion conexion;
    public FileReader lector_propiedades;
    public FileWriter escritor_propiedades;

    private Sistema() throws IOException {
        propiedades = new Properties(20);
        this.reinicio = false;
        So.setDefaultLookAndFeel();
    }

    public boolean _CargarArchivos() {
        try {
            File file;
            String formato = "%s = %s";
            String mensaje;
            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(AppFiles.ARR_DIR_PROG));
            list.addAll(Arrays.asList(AppFiles.ARR_DIR_USER));
            for (String i : list) {
                file = new File(i);
                if (file.exists()) {
                    mensaje = "OK";
                } else {
                    file.mkdir();
                    mensaje = "NEW";
                }
                System.out.println(
                        String.format(formato, file.getAbsolutePath(), mensaje)
                );
            }

            for (String i : AppFiles.ARR_PROG_ARC) {
                file = new File(i);
                if (file.exists()) {
                    mensaje = "OK";
                } else {
                    file.createNewFile();
                    mensaje = "NEW";

                }
                System.out.println(
                        String.format(formato, file.getAbsolutePath(), mensaje)
                );
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean _ConexionBD() {
        //MenuConfigBD config = new MenuConfigBD();
        leerPropiedades();
        boolean conexionNull = propiedades.get("bd-url") == null;
        if (conexionNull) {
            String[] o = null;
            try {
                o = NewMenuConfigBD.getConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
            propiedades.put("bd-usuario", o[0]);
            propiedades.put("bd-contraseña", o[1]);
            propiedades.put("bd-url", o[2]);

//            synchronized (config) {
//                config.setSistema(this);
//                config.setVisible(true);
//                try {
//                    config.wait();
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
//                }
            escribirPropiedades();
//            }
        }

        try {
            conexion = Conexion.getInstancia(propiedades.getProperty("bd-usuario"),
                    propiedades.getProperty("bd-contraseña"),
                    propiedades.getProperty("bd-url"));

            if (conexionCerrada() || conexionNoValida()) {
                int in = JOptionPane.showConfirmDialog(null, "Hay un problema con las credenciales, dese reiniciarlas?");
                if (in == JOptionPane.YES_OPTION) {
                    propiedades.remove("bd-usuario");
                    propiedades.remove("bd-contraseña");
                    propiedades.remove("bd-url");
                    escribirPropiedades();
                    return false;
                }
            }
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
            int in = JOptionPane.showConfirmDialog(null, "Hay un problema con las credenciales, dese reiniciarlas?");
            if (in == JOptionPane.YES_OPTION) {
                propiedades.remove("bd-usuario");
                propiedades.remove("bd-contraseña");
                propiedades.remove("bd-url");
                escribirPropiedades();
            }
            System.exit(0);

        }

        return true;
    }

    public void reiniciarCredenciales() {
        int in = JOptionPane.showConfirmDialog(null, "Hay un problema con las credenciales, dese reiniciarlas?");
        if (in == JOptionPane.YES_OPTION) {
            propiedades.remove("bd-usuario");
            propiedades.remove("bd-contraseña");
            propiedades.remove("bd-url");
            escribirPropiedades();
        }

    }

    public void leerPropiedades() {
        try {
            lector_propiedades = new FileReader(AppFiles.FIL_ARC_CONFIG, Charset.defaultCharset());
            propiedades.load(lector_propiedades);
            lector_propiedades.close();
            System.out.println(propiedades);
        } catch (FileNotFoundException ex) {
            Excp.impTerminal(ex, getClass(), false);
        } catch (IOException ex) {
            Excp.impTerminal(ex, getClass(), false);
        }
    }

    public void escribirPropiedades() {
        try {
            if (!propiedades.isEmpty()) {
                leerPropiedades();
            }
            escritor_propiedades = new FileWriter(AppFiles.FIL_ARC_CONFIG, Charset.defaultCharset(), false);
            propiedades.store(escritor_propiedades, "Propiedades del programa");
            escritor_propiedades.close();
        } catch (FileNotFoundException ex1) {
            Excp.impTerminal(ex1, getClass(), false);
        } catch (IOException ex1) {
            Excp.impTerminal(ex1, getClass(), false);
        }
    }

    public boolean conexionCerrada() {
        boolean isClose = false;
        try {
            isClose = conexion.getConexion().isClosed();
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        } finally {
            System.out.println(isClose);
        }
        return isClose;
    }

    public boolean conexionNoValida() {
        boolean isClose = false;
        try {
            isClose = conexion.getConexion().isReadOnly();
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
        return isClose;
    }

    public boolean _CargarCache() {
        if (FabricaCache.cache) {
            return true;
        }
        return FabricaCache.iniciarCache();
    }

    public boolean _Run() {
        this.reinicio = false;
        Login log = new Login();
        log.setVisible(true);
        return log.isVisible();
    }

    public void _Stop() {
        System.exit(0);
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarTodo() {
        cerrarBD();
        cerrarPropiedades();
    }

    public void cerrarBD() {
        try {
            if (conexion == null || !conexion.isConectado()) {
                return;
            }
            conexion.getConexion().close();
        } catch (SQLException ex) {
            Excp.impTerminal(ex, getClass(), true);
        }
    }

    public void cerrarPropiedades() {
        try {
            if (escritor_propiedades != null) {
                escritor_propiedades.close();
            }
            if (lector_propiedades != null) {
                lector_propiedades.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean _ComprobarVersion() {
        String version = System.getProperty("java.version");
        CharSequence subsecuencia = version.subSequence(0, 2);

        int versionInt = Integer.parseInt(subsecuencia.toString());

        if (versionInt < 16) {
            JOptionPane.showMessageDialog(null, "Version de java no valida");
            return false;
        }
        return true;
    }

    public boolean isReinicio() {
        if (reinicio) {
            System.out.println("REINICIANDO SISTEMA");
        }
        return reinicio;
    }

    public Properties getPropiedades() {
        return propiedades;
    }

    public Conexion getConexion() {
        return conexion;
    }

}
