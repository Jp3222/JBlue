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
package com.jblue.controlador.winc;

import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.Filtros;
import com.jblue.util.crypto.EncriptadoAES;
import com.jblue.modelo.fabricas.ConnectionFactory;
import com.jblue.sistema.ConstSisMen;
import com.jblue.sistema.Sesion;
import com.jblue.vista.windows.LoginWindows;
import com.jblue.vista.windows.ConfigWindow;
import com.jblue.vista.windows.WMainMenu;
import com.jutil.framework.LaunchApp;
import com.jutil.jexception.Excp;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            default ->
                defaultCase(e.getActionCommand(), null, -1);
        }
    }

    private final String WHERE = "user = '%s' and password = '%s'";

    public synchronized void login() {
        if (view.isSesionActive()) {
            return;
        }
        view.setSesionActive(true);
        if (!start()) {
            view.setSesionActive(false);
            return;
        }

        // Las caches deberan cargarse solo cuando se requieran
        if (!LaunchApp.getInstance().cache()) {
            System.out.println(ConstSisMen.MEN_CACHE_ERR);
        }
        System.out.println(ConstSisMen.MEN_CACHE_OK);

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
        Optional<OPersonal> res = query(view.getUser().getText(),
                String.valueOf(view.getPassword().getPassword())
        );
        if (res.isEmpty()) {
            return false;
        }

        Sesion sesion = Sesion.getInstancia();
        sesion.setUsuario(res.get());

//        if (!sesion.inicioSesion()) {
//            JOptionPane.showMessageDialog(WIN_LOGIN, "ERROR AL REGISTRAR SESION");
//            return false;
//        }
        return true;
    }

    public Optional<OPersonal> query(String user, String password) {
        Optional<OPersonal> res = Optional.empty();
        if (Filtros.isNullOrBlank(user, password)) {
            return res;
        }
        JDBConnection<OPersonal> op = ConnectionFactory.getEmployees();
        try {
            res = op.get("*", WHERE.formatted(
                    EncriptadoAES.encriptar(user, password),
                    EncriptadoAES.encriptar(password, user)
            ));
            
        } catch (UnsupportedEncodingException
                | NoSuchAlgorithmException
                | InvalidKeyException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            Excp.impTerminal(ex, getClass(), true);
        }
        if (res.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
            return Optional.empty();
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

}
