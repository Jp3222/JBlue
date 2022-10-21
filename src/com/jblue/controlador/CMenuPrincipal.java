/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.vista.ventanas.Login;
import com.jblue.vista.ventanas.MenuBD;
import com.jblue.vista.ventanas.MenuPrincipal;
import java.awt.event.ActionEvent;

/**
 *
 * @author jp
 */
public class CMenuPrincipal extends SuperControlador {

    private final Login LOGIN;
    private final MenuPrincipal MENU_PRINCIPAL;
    private final MenuBD MENU_BD;

    public CMenuPrincipal(Login login, MenuPrincipal menuPrincipal, MenuBD menuBD) {
        this.LOGIN = login;
        this.MENU_PRINCIPAL = menuPrincipal;
        this.MENU_BD = menuBD;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "login":
                irLogin();
                break;
            default:
                throw new AssertionError();
        }
    }

    public void irLogin() {
        MENU_PRINCIPAL.dispose();
        LOGIN.setVisible(true);
    }

}
