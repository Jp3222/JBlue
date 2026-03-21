/*
 * Copyright (C) 2024 juan pablo campos casasanero
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
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.l4b.LoginRulers;
import jsoftware.com.jblue.model.service.LoginService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.views.win.ConfigWindow;
import jsoftware.com.jblue.views.win.LoginWindows;
import jsoftware.com.jblue.views.win.WMainMenu;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.sys.LaunchApp;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juan pablo campos casasanero
 */
public class LoginController extends WindowController {
    
    private static final long serialVersionUID = 1L;
    private final SystemSession sesion;
    private final LoginWindows view;
    private final ConfigWindow view_config;
    private final LoginService service;
    private WMainMenu WIN_MAIN_MENU;
    
    public LoginController(LoginWindows view, ConfigWindow view_config) {
        this.sesion = SystemSession.getInstancia();
        this.view = view;
        this.view_config = view_config;
        this.service = new LoginService(AppConfig.isDevMessages(), "LOGIN");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "login" ->
                login();
            case "config" ->
                config();
            case "show" ->
                show();
            case "change password" ->
                changePassword();
            default ->
                defaultCase(e.getActionCommand(), null, -1);
        }
    }
    
    private final String WHERE = "user = '%s' and password = '%s'";
    
    public synchronized void login() {
        try (JDBConnection connection = ConnectionFactory.getIntance().getMainConnection()) {
            if (view.isSesionActive()) {
                return;
            }
            view.setSesionActive(true);
            if (!LoginRulers.isWorkTime()) {
                returnMessage(view, "NO ES TIMPO DE TRABAJAR");
            }
            
            boolean res = service.login(connection, view.getUserString(), view.getPasswordString());
            
            if (!res) {
                //JOptionPane.showMessageDialog(view, service.getUserMessage() + ":" + service.getUserMessage());
                returnMessage(view, "[" + service.getErrorCode() + "] " + service.getUserMessage());
                return;
            }
            
            sesion.getWarnings();
            sesion.writer();
            
            if (!LaunchApp.getInstance().cache()) {
                System.out.println("ERROR EN LA CACHE");
                log("ERROR EN LA CACHE");
            }
            
            System.out.println("MEMORIA CACHE LISTA");
            log("MEMORIA CACHE LISTA");
            
            view.dispose();

            //Nuevo menu estandarizado a las aplicaciones
            WIN_MAIN_MENU = new WMainMenu(view);
            WIN_MAIN_MENU.setVisible(true);
            view.setSesionActive(false);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    @Override
    public void windowClosing(WindowEvent we) {
        synchronized (LaunchApp.getInstance().getMain()) {
            LaunchApp.getInstance().getMain().notify();
        }
    }
    
    public void config() {
        view.dispose();
        view_config.setVisible(true);
    }
    
    public void show() {
        JCheckBox o = view.getMostrar();
        String message = o.isSelected() ? "ocultar" : "mostrar";
        o.setToolTipText(message);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        boolean key_pressed = e.getKeyCode() == KeyEvent.VK_ENTER;
        boolean user_component = e.getComponent() == view.getUser();
        boolean password_component = e.getComponent() == view.getPassword();
        if (key_pressed && user_component) {
            view.getUser().transferFocus();
        } else if (key_pressed && password_component) {
            view.getLoginButton().requestFocus();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            login();
        }
    }
    
    void rmessage(String msg, int type) {
        JOptionPane.showMessageDialog(view, msg, "Inicio de sesion", type);
    }
    
    private void changePassword() {
    }
    
    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, "MAIN", method_name, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    public void log(String message) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, "MAIN", message);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
