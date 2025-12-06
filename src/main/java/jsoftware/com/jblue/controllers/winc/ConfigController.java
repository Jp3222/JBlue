/*
 * Copyright (C) 2024 juan-campos
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
package jsoftware.com.jblue.controllers.winc;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.ConfigurationPanel;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.sys.LaunchApp;

/**
 *
 * @author juan-campos
 */
public class ConfigController extends WindowController {

    private static final long serialVersionUID = 1L;

    private final ConfigurationPanel view;
    private final Properties properties;

    public ConfigController(ConfigurationPanel view) {
        this.view = view;
        properties = (Properties) LaunchApp.getInstance().getResources("propierties");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "next" -> {
                if (!sessionStart()) {
                    return;
                }
                view.nextView();
            }
            case "back" -> {
                if (!sessionStart()) {
                    return;
                }
                view.backView();
            }
            case "show_user" -> {
                JCheckBox o = (JCheckBox) e.getSource();
                JPasswordField user = view.getUser();
                user.setEchoChar(o.isSelected() ? ((char) 0) : '*');
            }
            case "show_password" -> {
                JCheckBox o = (JCheckBox) e.getSource();
                JPasswordField pass = view.getPassword();
                pass.setEchoChar(o.isSelected() ? ((char) 0) : '*');
            }
            case "save db" ->
                save();
            case "test db" ->
                testDB();
            default ->
                defaultCase(e.getActionCommand(), null, -1);
        }
    }

    //
    void save() {
        properties.setProperty(AppConfig.DB_USER, view.getSUser());
        properties.setProperty(AppConfig.DB_PASSWORD, view.getSPassword());
        //properties.setProperty(AppConfig.DB_URL, view.getSUrl());
        properties.setProperty(AppConfig.DB_MOTOR, view.getMotor());
        properties.setProperty(AppConfig.DB_PORT, view.getPort());
        properties.setProperty(AppConfig.DB_HOST, view.getHost());
        properties.setProperty(AppConfig.DB_NAME, view.getDataBaseName());
        //
        properties.setProperty(AppConfig.TITLE1, view.getTitle1());
        properties.setProperty(AppConfig.TITLE2, view.getTitle2());
        properties.setProperty(AppConfig.LOGIN_ICON, view.getIconPath());
        view.disposeWin();
    }

    void testDB() {
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection()) {
            boolean valid = connection.getConnection().isValid(1000);
            if (!valid) {
                JOptionPane.showMessageDialog(view, "Connexion no establecida");
                return;
            }

            JOptionPane.showMessageDialog(view, "Connexion valida");
            properties.setProperty(AppConfig.DB_URL, view.getURL());
            properties.setProperty(AppConfig.DB_USER, view.getSUser());
            properties.setProperty(AppConfig.DB_PASSWORD, view.getSPassword());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }
    }

    @Override
    public void windowClosed(WindowEvent we) {
        try (FileOutputStream out = new FileOutputStream(AppFiles.FIL_ARC_CONFIG);) {
            properties.storeToXML(out, "Configuracion");
        } catch (IOException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        synchronized (LaunchApp.getInstance().getMain()) {
            LaunchApp.getInstance().getMain().notifyAll();
        }
        synchronized (view) {
            view.notify();
        }
    }

    boolean sessionStart() {
        return SystemSession.getInstancia().getCurrentEmployee() != null;
    }

}
