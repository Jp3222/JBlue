/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.modelo.funciones.OpPersonal;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.crypto.EncriptadoAES;
import com.jblue.vista.ventanas.Login;
import com.jblue.vista.ventanas.MenuConfigBD;
import com.jblue.vista.ventanas.MenuPrincipal;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class CLogin extends SuperControlador {

    private final Login LOGIN;
    private final MenuPrincipal MENU_PRINCIPAL;
    private final MenuConfigBD MENU_CONFIG_BD;

    public CLogin(Login LOGIN, MenuPrincipal MENU_PRINCIPAL, MenuConfigBD MENU_CONFIG_BD) {
        this.LOGIN = LOGIN;
        this.MENU_PRINCIPAL = MENU_PRINCIPAL;
        this.MENU_CONFIG_BD = MENU_CONFIG_BD;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        irMenu();
    }

    public boolean isDatosValidos() {
        String usuario = LOGIN.getJtfUsuario().getText();
        String contra = String.valueOf(LOGIN.getJpfPass().getPassword());
        OpPersonal op = new OpPersonal();
        OPersonal personal = op.get("usuario = '" + usuario + "' and contra = '" + contra + "'");
        if (personal != null) {
            EncriptadoAES en = new EncriptadoAES();
            //return en.comparador(personal.getUsuario(), personal.getContra(), usuario, pass);
            return personal.getUsuario().equals(usuario) && personal.getContra().equals(contra);
        }
        return false;
    }

    public void irMenu() {
        if (!isDatosValidos()) {
            JOptionPane.showMessageDialog(LOGIN, "Usuario y/o Contraseña no validos", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LOGIN.setVisible(false);
        LOGIN.estadoInicial();
        LOGIN.dispose();
        //
        if (!MENU_PRINCIPAL.isVisible() || !MENU_PRINCIPAL.isActive()) {
            MENU_PRINCIPAL.setVisible(true);
        }
    }

    public void irMenuConfigBD() {
        MENU_CONFIG_BD.setVisible(true);
    }

}
