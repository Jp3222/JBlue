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
package com.jblue.controlador;

import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.Filtros;
import com.jblue.util.crypto.EncriptadoAES;
import com.jblue.modelo.fabricas.FabricaFuncionesBD;
import com.jblue.sistema.ConstSisMen;
import com.jblue.sistema.Sesion;
import com.jblue.sistema.Sistema;
import com.jblue.vista.windows.Login;
import com.jblue.vista.windows.MenuConfigBD;
import com.jblue.vista.windows.WMainMenu;
import java.awt.event.ActionEvent;
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
public class CLogin extends Controller {

    private final Login WIN_LOGIN;
    private final MenuConfigBD DB_CONFIG_MENU;
    private WMainMenu WIN_MAIN_MENU;

    public CLogin(Login WIN_LOGIN, MenuConfigBD DB_CONFIG_MENU) {
        this.WIN_LOGIN = WIN_LOGIN;
        this.DB_CONFIG_MENU = DB_CONFIG_MENU;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "session" ->
                login();
            case "config" -> {
                WIN_LOGIN.dispose();
                DB_CONFIG_MENU.setVisible(true);
            }
            case "show"-> {
                JCheckBox o = WIN_LOGIN.getMostrar();
                String message = o.isSelected() ? "ocultar" : "mostrar";
                o.setToolTipText(message);
            }
            default ->
                throw new AssertionError();
        }
    }

    private final String WHERE = "usuario = '%s' and contra = '%s'";

    public synchronized void login() {
        if (WIN_LOGIN.isSesionActive()) {
            return;
        }
        WIN_LOGIN.setSesionActive(true);
        if (!start()) {
            WIN_LOGIN.setSesionActive(false);
            return;
        }

        // Las caches deberan cargarse solo cuando se requieran
        if (!Sistema.getInstancia()._CargarCache()) {
            System.out.println(ConstSisMen.MEN_CACHE_ERR);
        }
        System.out.println(ConstSisMen.MEN_CACHE_OK);

        WIN_LOGIN.dispose();

        //Nuevo menu estandarizado a las aplicaciones 
        WIN_MAIN_MENU = new WMainMenu(WIN_LOGIN);
        WIN_MAIN_MENU.setVisible(true);
        WIN_LOGIN.setSesionActive(false);

    }

    public boolean start() {
        Optional<OPersonal> res = query(WIN_LOGIN.getUser().getText(),
                String.valueOf(WIN_LOGIN.getPassword().getPassword())
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
        FuncionesBD<OPersonal> op = FabricaFuncionesBD.getPersonal();
        try {
            res = op.get(null, WHERE.formatted(
                    EncriptadoAES.encriptar(user, password),
                    EncriptadoAES.encriptar(password, user)
            ));
        } catch (UnsupportedEncodingException
                | NoSuchAlgorithmException
                | InvalidKeyException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (res.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos");
            return Optional.empty();
        }
        return res;
    }

}
