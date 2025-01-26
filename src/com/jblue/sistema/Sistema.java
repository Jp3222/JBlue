/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jutil.framework.MainSystem;
import com.jblue.sistema.app.AppFiles;
import com.jblue.modelo.fabricas.FactoryCache;
import com.jblue.sistema.app.AppConfig;
import com.jblue.vista.windows.ConfigWindow;
import com.jblue.vista.windows.LoginWindows;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.jbd.conexion.Conexion;
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

/**
 * Sistema
 * <br> Esta clase es encargada de iniciar todos los datos del sistema
 *
 * @author jp
 */
public class Sistema implements MainSystem {

    private static final Logger LOG = Logger.getLogger(Sistema.class.getName());

    /**
     *
     */
    private final Map<String, Object> resources;
    private final Properties propiedades;
    private Conexion conexion;
    private DBConnection connection;

    public Sistema() {
        propiedades = new Properties(20);
        //
        resources = new HashMap<>(5);
        resources.put("propierties", propiedades);
        So.setDefaultLookAndFeel();
    }

    public Properties getPropiedades() {
        return propiedades;
    }

    @Override
    public boolean conectionDB() {
        boolean null_connection = propiedades.getProperty(AppConfig.DB_URL) == null
                || propiedades.getProperty(AppConfig.DB_USER) == null
                || propiedades.getProperty(AppConfig.DB_PASSWORD) == null;

        if (null_connection) {
            try {
                ConfigWindow o = new ConfigWindow();
                synchronized (o) {
                    o.setVisible(true);
                    o.wait();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            conexion = Conexion.getInstancia(
                    propiedades.getProperty(AppConfig.DB_USER),
                    propiedades.getProperty(AppConfig.DB_PASSWORD),
                    propiedades.getProperty(AppConfig.DB_URL)
            );
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        try {
            connection = DBConnection.getInstance(
                    propiedades.getProperty(AppConfig.DB_URL),
                    propiedades.getProperty(AppConfig.DB_USER),
                    propiedades.getProperty(AppConfig.DB_PASSWORD)
            );
            connection.setShowQuery(true);
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.log(Level.INFO, "DATA BASE CONNECTION");
        return true;
    }

    @Override
    public boolean appFiles() {
        File aux;
        for (String i : AppFiles.S_ALL_DIR_PROG) {
            aux = new File(i);
            if (aux.exists()) {
                LOG.log(Level.INFO, log_messages.formatted(i, "FUE LEIDO CORRECTAMENTE"));
            } else {
                aux.mkdir();
                LOG.log(Level.INFO, log_messages.formatted(i, "FUE CREADO CORRECTAMENTE"));
            }
        }

        for (String i : AppFiles.S_ARR_DIR_USER) {
            aux = new File(i);
            if (aux.exists()) {
                LOG.log(Level.INFO, log_messages.formatted(i, "FUE LEIDO CORRECTAMENTE"));
            } else {
                aux.mkdir();
                LOG.log(Level.INFO, log_messages.formatted(i, "FUE CREADO CORRECTAMENTE"));
            }
        }

        for (String i : AppFiles.S_ARR_PROG_ARC) {
            aux = new File(i);
            if (aux.exists()) {
                LOG.log(Level.INFO, log_messages.formatted(i, "FUE LEIDO CORRECTAMENTE"));
            } else {
                try {
                    aux.createNewFile();
                    LOG.log(Level.INFO, log_messages.formatted(i, "FUE CREADO CORRECTAMENTE"));
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
        }

        return true;
    }

    @Override
    public boolean cache() {
        return FactoryCache.cache_list || FactoryCache.loadCaches();
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
        LOG.log(Level.INFO, "OPEN SYSTEM");
        return true;
    }

    @Override
    public boolean closeSys() {
        try {
            conexion.closeRS();
            conexion.closeST();
            connection.close();
            LOG.log(Level.INFO, "CLOSE SYSTEM");

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

}
