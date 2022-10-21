/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.vista.ventanas.Login;
import com.jblue.vista.ventanas.MenuPrincipal;
import java.awt.event.ActionEvent;

/**
 *
 * @author jp
 */
public class CLogin extends SuperControlador {

    private final Login login;
    private final MenuPrincipal menuPrincipal;

    public CLogin(Login login, MenuPrincipal menuPrincipal) {
        this.login = login;
        this.menuPrincipal = menuPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        irMenu();
    }

    public void irMenu() {
        System.out.println("xd2");
        login.dispose();
        menuPrincipal.setVisible(true);
    }

}
