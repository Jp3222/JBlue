/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.vista.ventanas.Login;
import com.jblue.vista.ventanas.MenuBD;
import com.jblue.vista.ventanas.MenuPrincipal;
import com.jblue.vista.ventanas.MenuTesoreria;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class CMenuPrincipal extends SuperControlador {

    private final Login LOGIN;
    private final MenuPrincipal MENU_PRINCIPAL;
    private final MenuBD MENU_BD;
    private final MenuTesoreria MENU_TESORERIA;

    public CMenuPrincipal(Login LOGIN, MenuPrincipal MENU_PRINCIPAL, MenuBD MENU_BD, MenuTesoreria MENU_TESOSERIA) {
        this.LOGIN = LOGIN;
        this.MENU_PRINCIPAL = MENU_PRINCIPAL;
        this.MENU_BD = MENU_BD;
        this.MENU_TESORERIA = MENU_TESOSERIA;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "login":
                irLogin();
                break;
            case "menubd":
                irMenuBD();
            case "menuteso":
                irMenuTesoreria();
                break;
            default:
                JOptionPane.showConfirmDialog(null, "Ha ocurrido un error");
                System.out.println("error en: " + CMenuPrincipal.class.getName());
        }
    }

    public void irMenuBD() {
        if (!MENU_BD.isActive() || !MENU_BD.isVisible()) {
            //MENU_PRINCIPAL.setVisible(false);
            //MENU_PRINCIPAL.dispose();
            MENU_BD.setVisible(true);
        }
    }

    public void irMenuTesoreria() {
    }

    /**
     * Metodo que cierra el menu principal y todas las ventanas en caso de que
     * esten activas
     */
    public void irLogin() {
        if (MENU_BD.isVisible() || MENU_BD.isActive()) {
            MENU_BD.setVisible(false);
            MENU_BD.estadoInicial();
            MENU_BD.dispose();
        }
        if (MENU_TESORERIA.isVisible() || MENU_TESORERIA.isActive()) {
            MENU_TESORERIA.setVisible(false);

            MENU_TESORERIA.dispose();
        }
        MENU_PRINCIPAL.setVisible(false);
        MENU_PRINCIPAL.estadoInicial();
        MENU_PRINCIPAL.dispose();
        LOGIN.setVisible(true);
    }

}
