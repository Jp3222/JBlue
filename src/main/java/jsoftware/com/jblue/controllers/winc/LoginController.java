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

import jsoftware.com.jblue.model.DBConnection;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jblue.util.Filters;
import jsoftware.com.jblue.util.EncriptadoAES;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.model.grs.LoginRulers;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.components.ChangePasswordComponent;
import jsoftware.com.jblue.views.win.LoginWindows;
import jsoftware.com.jblue.views.win.ConfigWindow;
import jsoftware.com.jblue.views.win.WMainMenu;
import jsoftware.com.jutil.sys.LaunchApp;
import jsoftware.com.jutil.jexception.Excp;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 */
public class LoginController extends WindowController {

    private final LoginWindows view;
    private final ConfigWindow view_config;
    private WMainMenu WIN_MAIN_MENU;

    public LoginController(LoginWindows view, ConfigWindow view_config) {
        this.view = view;
        this.view_config = view_config;
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
        if (view.isSesionActive()) {
            return;
        }
        if (!start()) {
            return;
        }

        // Las caches deberan cargarse solo cuando se requieran
        if (!LaunchApp.getInstance().cache()) {
            System.out.println("ERROR EN LA CACHE");
        }
        System.out.println("MEMORIA CACHE LISTA");

        view.dispose();

        //Nuevo menu estandarizado a las aplicaciones 
        WIN_MAIN_MENU = new WMainMenu(view);
        WIN_MAIN_MENU.setVisible(true);
        view.setSesionActive(false);

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

    public boolean start() {
        if (LoginRulers.isWorkTime()) {
            rmessage("No es hora de Trabajar", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        Optional<OEmployee> res = query(view.getUser().getText(),
                String.valueOf(view.getPassword().getPassword())
        );

        if (res.isEmpty()) {
            rmessage("Usuario y/o contraseña no validos", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (LoginRulers.isDateEnd(res.get())) {
            rmessage("Periodo de acceso finalizado", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        SystemSession sesion = SystemSession.getInstancia();
        sesion.setUser(res.get());
        sesion.getWarnings();
        return true;
    }

    public Optional<OEmployee> query(String user, String password) {
        Optional<OEmployee> res = Optional.empty();
        if (Filters.isNullOrBlank(user, password)) {
            return res;
        }
        DBConnection<OEmployee> op = ConnectionFactory.getEmployees();
        try {
            String encrypt_user = EncriptadoAES.doEncrypt(user, password);
            String encrypt_password = EncriptadoAES.doEncrypt(password, user);

            res = op.get("*", WHERE.formatted(
                    encrypt_user, encrypt_password
            ));
            if (res.isEmpty()) {
                return Optional.empty();
            }
        } catch (UnsupportedEncodingException
                | NoSuchAlgorithmException
                | InvalidKeyException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            Excp.impTerminal(ex, getClass(), true);
        }
        return res;
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

    private void changePassword() {
        boolean changePassword = ChangePasswordComponent.getChangePasswordComponent(view);
        if (!changePassword) {
            JOptionPane.showMessageDialog(view, "La contraseña no se pudo cambiar");
            return;
        }
        JOptionPane.showMessageDialog(view, "Contraseña Cambiada");
    }

    void rmessage(String msg, int type) {
        JOptionPane.showMessageDialog(view, msg, "Inicio de sesion", type);
    }
}
