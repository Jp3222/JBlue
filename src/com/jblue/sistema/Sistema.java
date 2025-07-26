/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.formdev.flatlaf.FlatLightLaf;
import com.jblue.modelo.constdb.Const;
import com.jutil.framework.MainSystem;
import com.jblue.sistema.app.AppFiles;
import com.jblue.modelo.fabricas.CacheFactory;
import com.jblue.sistema.app.AppConfig;
import com.jblue.vista.windows.ConfigWindow;
import com.jblue.vista.windows.LoginWindows;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.jexception.Excp;
import com.jutil.platf.JVMInfo;
import com.jutil.platf.So;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
public class Sistema implements MainSystem {
    public static final String DATA_BASE_KEY = "connection";
    private static final Logger LOG = Logger.getLogger(Sistema.class.getName());
    private final int code_1049 = 1049;
    /**
     *
     */
    private final Map<String, Object> resources;
    private final Properties propiedades;
    //private Conexion conexion;
    private DBConnection connection;

    public Sistema() {
        propiedades = new Properties(20);
        //
        resources = new HashMap<>(5);
        resources.put("propierties", propiedades);
        resources.put("sys_flag_logs", true);
        So.setDefaultLookAndFeel(new FlatLightLaf());
    }

    public Properties getPropiedades() {
        return propiedades;
    }

    @Override
    public boolean conectionDB() {
        boolean null_connection = false;
        for (String i : AppConfig.DB_KEYS) {
            if (propiedades.getProperty(i) == null) {
                null_connection = true;
            }
        }

        if (null_connection) {
            try {
                ConfigWindow o = new ConfigWindow();
                synchronized (o) {
                    o.setVisible(true);
                    o.wait();
                }
            } catch (InterruptedException ex) {
                SystemLogs.infoDbLogs(ex.getMessage());
            }
        }
        try {
            String database_url = "jdbc:%s://%s:%s/%s";
            connection = DBConnection.getInstance(
                    database_url.formatted(
                            propiedades.getProperty(AppConfig.DB_MOTOR),
                            propiedades.getProperty(AppConfig.DB_HOST),
                            propiedades.getProperty(AppConfig.DB_PORT),
                            propiedades.getProperty(AppConfig.DB_NAME)
                    ),
                    propiedades.getProperty(AppConfig.DB_USER),
                    propiedades.getProperty(AppConfig.DB_PASSWORD)
            );
            resources.put("connection", connection);
            resources.put("sys_flag_logs", AppConfig.isLogsDB());
            connection.setShowQuery((boolean) resources.get("sys_flag_logs"));
            System.out.println("xd:" + resources.get("sys_flag_logs"));
        } catch (SQLException e) {
            SystemLogs.severeSysLogs(e.getMessage(), e);
            Excp.SysExit();
        }
        SystemLogs.infoSysLogs("BASE DE DATOS CONECTADA");
        return true;
    }

    void showMessage(int error) {
        String msg = switch (error) {
            case code_1049:
                yield "Nombre de la base de datos incorrecto";
            default:
                yield "";
        };
        JOptionPane.showMessageDialog(null, msg, "ERROR: %s".formatted(error), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean appFiles() {
        File aux;
        //Directorios del sistema
        for (String i : AppFiles.S_ALL_DIR_PROG) {
            aux = new File(i);
            if (aux.exists()) {
                //SystemLogs.infoSysLogs(log_messages.formatted(i, "EXISTE"));
                System.out.println(log_messages.formatted(i, "EXISTE"));
            } else {
                aux.mkdir();
                //    SystemLogs.infoSysLogs(log_messages.formatted(i, "FUE CREADO CORRECTAMENTE"));
                System.out.println(log_messages.formatted(i, "FUE CREADO CORRECTAMENTE"));

            }
        }

        //Directorios del usuario
        for (String i : AppFiles.S_ARR_DIR_USER) {
            aux = new File(i);
            if (aux.exists()) {
                SystemLogs.infoSysLogs(log_messages.formatted(i, "EXISTE"));
            } else {
                aux.mkdirs();
                //SystemLogs.infoSysLogs(log_messages.formatted(i, "FUE CREADO CORRECTAMENTE"));
                System.out.println(log_messages.formatted(i, "FUE CREADO CORRECTAMENTE"));
            }
        }
        //Alchivos del programa
        for (String i : AppFiles.S_ARR_PROG_ARC) {
            System.out.println(i);
            aux = new File(i);
            if (aux.exists()) {
                System.out.println(aux.getAbsolutePath());
                SystemLogs.infoSysLogs(log_messages.formatted(i, "EXISTE"));
            } else {
                try {
                    aux.createNewFile();
                    SystemLogs.infoSysLogs(log_messages.formatted(i, "FUE CREADO CORRECTAMENTE"));
                } catch (IOException ex) {
                    SystemLogs.severeSysLogs(i, ex);
                }
            }
        }

        return true;
    }

    @Override
    public boolean cache() {
        boolean rs = CacheFactory.cache_list || CacheFactory.loadCaches();
        if (rs) {
            SystemLogs.infoSysLogs("MEMORIA CACHE LISTA");
        }
        return rs;
    }

    @Override
    public boolean run() {
        LoginWindows log = new LoginWindows();
        log.setVisible(true);
        return log.isVisible();
    }

    @Override
    public boolean openSys() {
        String JV = JVMInfo.JAVA_VERSION;

        try (FileInputStream input = new FileInputStream(AppFiles.FIL_ARC_CONFIG)) {
            propiedades.loadFromXML(input);

        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }

        resources.put("t_streets", Const.STREETS);
        resources.put("t_water_intakes", Const.WATER_INTAKES);
        resources.put("t_employees", Const.EMPLOYEES);
        resources.put("t_payments", Const.SERVICE_PAYMENTS);

        SystemLogs.infoDbLogs("OPEN SYSTEM");
        return true;
    }

    @Override
    public boolean closeSys() {
        try {
            connection.close();
            SystemLogs.infoDbLogs("CLOSE SYSTEM");
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }

        LOG.log(Level.INFO, "EXIT");
        System.exit(0);

        return true;
    }
    private final String log_messages = "EL DIRECTORIO: %s %s";

    @Override
    public Object getResources(String key) {
        return resources.get(key);
    }

    public static String buildMSG() {
        return null;
    }
}
