/*
 * Copyright (C) 2025 juanp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.sys;

import com.formdev.flatlaf.FlatLightLaf;
import com.jblue.model.factories.CacheFactory;
import com.jblue.sys.app.AppConfig;
import com.jblue.sys.app.AppFiles;
import com.jblue.views.win.ConfigWindow;
import com.jblue.views.win.LoginWindows;
import com.jutil.dbcon.connection.JDBConnection;
import com.jutil.framework.MainSystem;
import com.jutil.jexception.JExcp;
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
 *
 * @author juanp
 */
public class JBlueMainSystem implements MainSystem {

    public static final String DATA_BASE_KEY = "connection";
    private static final Logger LOG = Logger.getLogger(JBlueMainSystem.class.getName());
    private final int code_1049 = 1049;
    /**
     *
     */
    private final Map<String, Object> resources;
    private final Properties propiedades;
    //private Conexion conexion;
    private JDBConnection connection;

    public JBlueMainSystem() {
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
            propiedades.put(AppConfig.DB_URL, database_url.formatted(
                    propiedades.getProperty(AppConfig.DB_MOTOR),
                    propiedades.getProperty(AppConfig.DB_HOST),
                    propiedades.getProperty(AppConfig.DB_PORT),
                    propiedades.getProperty(AppConfig.DB_NAME)
            ));
            connection = JDBConnection.getInstance(
                    propiedades.getProperty(AppConfig.DB_URL),
                    propiedades.getProperty(AppConfig.DB_USER),
                    propiedades.getProperty(AppConfig.DB_PASSWORD)
            );
            resources.put("connection", connection);
            resources.put("sys_flag_logs", AppConfig.isLogsDB());
            resources.put(AppConfig.DB_USER, propiedades.getProperty(AppConfig.DB_USER));
            resources.put(AppConfig.DB_PASSWORD, propiedades.getProperty(AppConfig.DB_PASSWORD));
            resources.put(AppConfig.DB_URL, propiedades.getProperty(AppConfig.DB_URL));
            connection.setShowQuery((boolean) resources.get("sys_flag_logs"));
        } catch (SQLException e) {
            SystemLogs.severeSysLogs(e.getMessage(), e);
            JExcp.getInstance(true, true).printAndShow(e, getClass());
            SystemLogs.infoSysLogs("ERROR EN CONEXION");
            return false;
        }
        SystemLogs.infoSysLogs("BASE DE DATOS CONECTADA");
        return true;
    }

    @Override
    public boolean appFiles() {
        File aux;
        //Directorios del sistema
        for (String i : com.jblue.sys.app.AppFiles.S_ALL_DIR_PROG) {
            aux = new File(i);
            if (aux.exists()) {
                System.out.println(log_messages.formatted(i, "EXISTE"));
            } else {
                aux.mkdir();
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
        try (FileInputStream input = new FileInputStream(AppFiles.FIL_ARC_CONFIG)) {
            propiedades.loadFromXML(input);

        } catch (IOException ex) {
            Logger.getLogger(JBlueMainSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(AppFiles.FIL_ARC_CONFIG.getAbsolutePath());
        SystemLogs.infoDbLogs("OPEN SYSTEM");
        return true;
    }

    @Override
    public boolean closeSys() {
        try {
            connection.close();
            SystemLogs.infoDbLogs("CLOSE SYSTEM");
        } catch (SQLException ex) {
            Logger.getLogger(JBlueMainSystem.class.getName()).log(Level.SEVERE, null, ex);
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
