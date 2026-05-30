/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.util.DTOFactory;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.db.JDBConnectionBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author juanp
 */
public class DataBaseTest {

    public static final Logger log = LoggerFactory.getLogger(DataBaseTest.class);

    private final Properties properties = new Properties(20);

    @Test
    public boolean openSys() {
        try (FileInputStream input = new FileInputStream(AppFiles.FIL_ARC_CONFIG)) {
            properties.loadFromXML(input);
        } catch (IOException e) {
            log.error("FALLO AL ABRIR ARCHIVOS", e);
        }
        return true;
    }

    @Test
    public void connection() {
        try (FileInputStream input = new FileInputStream(AppFiles.FIL_ARC_CONFIG)) {
            properties.loadFromXML(input);
        } catch (IOException e) {
            log.error("FALLO AL ABRIR ARCHIVOS EN BD", e);
        }
        String database_url = "jdbc:%s://%s:%s/%s";
        properties.put(AppConfig.DB_URL, database_url.formatted(properties.getProperty(AppConfig.DB_MOTOR),
                properties.getProperty(AppConfig.DB_HOST),
                properties.getProperty(AppConfig.DB_PORT),
                properties.getProperty(AppConfig.DB_NAME)
        ));

        JDBConnectionBuilder builder = new JDBConnectionBuilder();
        builder.setUser(properties.getProperty(AppConfig.DB_USER));
        builder.setPassword(properties.getProperty(AppConfig.DB_PASSWORD));
        String url = database_url.formatted(properties.getProperty(AppConfig.DB_MOTOR),
                properties.getProperty(AppConfig.DB_HOST),
                properties.getProperty(AppConfig.DB_PORT),
                properties.getProperty(AppConfig.DB_NAME)
        );
        builder.setUrl(url);
        builder.setTimeOut(5000);
        builder.setMinimumIdle(5000);
        builder.setMaxPollSize(20);
        builder.setFactory(new DTOFactory());
        ConnectionFactory intance = ConnectionFactory.getIntance(builder);
        if (intance.isOpen()) {
            try (JDBConnection connection = intance.getMainConnection();) {
                System.out.println(connection.getConnection().isClosed());
                System.out.println(connection.getConnection().isValid(1000));

            } catch (SQLException e) {
                log.error("FALLO AL CONNECTAR", e);
            }
        }
    }

}
