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
package jsoftware.com.jblue.sys;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.win.ConfigWindow;
import jsoftware.com.jblue.views.win.LoginWindows;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.db.JDBConnectionBuilder;
import jsoftware.com.jutil.platf.So;
import jsoftware.com.jutil.sys.MainSystem;

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

    public JBlueMainSystem() {
        propiedades = new Properties(20);
        //
        resources = new HashMap<>(5);
        resources.put("propierties", propiedades);
        resources.put("sys_flag_logs", true);
        So.setDefaultLookAndFeel(new FlatDarculaLaf());
    }

    public Properties getPropiedades() {
        return propiedades;
    }

    @Override
    public boolean conectionDB() {
        boolean res = false;
        try {
            boolean null_properties = false;
            for (String i : AppConfig.DB_KEYS) {
                if (propiedades.getProperty(i) == null) {
                    null_properties = true;
                }
            }
            if (null_properties) {
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
            String database_url = "jdbc:%s://%s:%s/%s";
            propiedades.put(AppConfig.DB_URL, database_url.formatted(
                    propiedades.getProperty(AppConfig.DB_MOTOR),
                    propiedades.getProperty(AppConfig.DB_HOST),
                    propiedades.getProperty(AppConfig.DB_PORT),
                    propiedades.getProperty(AppConfig.DB_NAME)
            ));
            
            JDBConnectionBuilder builder = new JDBConnectionBuilder();
            builder.setUser(propiedades.getProperty(AppConfig.DB_USER));
            builder.setPassword(propiedades.getProperty(AppConfig.DB_PASSWORD));
            String url = database_url.formatted(
                    propiedades.getProperty(AppConfig.DB_MOTOR),
                    propiedades.getProperty(AppConfig.DB_HOST),
                    propiedades.getProperty(AppConfig.DB_PORT),
                    propiedades.getProperty(AppConfig.DB_NAME)
            );
            builder.setUrl(url);
            builder.setTimeOut(5000);
            builder.setMinimumIdle(5000);
            builder.setMaxPollSize(20);
            JDBConnection generic_connection = builder.build();
            if (generic_connection == null) {
                throw new SQLException("LA CONFIGURACIONES DE BASE DE DATOS NO SE INICIARON CORRECTAMENTE");
            }
            ConnectionFactory intance = ConnectionFactory.getIntance(builder);
            boolean openFactory = intance.openFactory();
            if (!openFactory) {
                throw new SQLException("LA FABRICA DE CONEXION NO ABRIO");
            }

            resources.put(SiystemConsts.DATA_BASE_KEY, generic_connection);
            SystemLogs.infoSysLogs("BASE DE DATOS CONECTADA");
            res = true;
        } catch (SQLException ex) {
            System.getLogger(JBlueMainSystem.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (NullPointerException ex) {
            System.getLogger(JBlueMainSystem.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (Exception ex) {
            System.getLogger(JBlueMainSystem.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
    }

    @Override
    public boolean appFiles() {
        File aux;
        //Directorios del sistema
        for (String i : jsoftware.com.jblue.sys.app.AppFiles.S_ALL_DIR_PROG) {
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
        boolean close = ConnectionFactory.getIntance().close();
        if (!close) {
            SystemLogs.infoDbLogs("PROBLEMAS AL CERRAR CONEXION");
        }
        SystemLogs.infoDbLogs("CLOSE SYSTEM");
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
