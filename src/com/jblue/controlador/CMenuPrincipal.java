/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.sistema.Sesion;
import com.jblue.util.cache.FabricaCache;
import com.jblue.vista.ventanas.Login;
import com.jblue.vista.ventanas.MenuPrincipal;
import com.jblue.vista.ventanas.MenuTesoreria;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class CMenuPrincipal extends SuperControlador {

    private final Login LOGIN;
    private final MenuPrincipal MENU_PRINCIPAL;
    private final MenuTesoreria MENU_TESORERIA;
    //

    public CMenuPrincipal(Login LOGIN, MenuPrincipal MENU_PRINCIPAL, MenuTesoreria MENU_TESOSERIA) {
        this.LOGIN = LOGIN;
        this.MENU_PRINCIPAL = MENU_PRINCIPAL;
        this.MENU_TESORERIA = MENU_TESOSERIA;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "login":
                irLogin();
                break;
            case "menuteso":
                irMenuTesoreria();
                break;
            default:
                JOptionPane.showConfirmDialog(null, "Ha ocurrido un error");
                System.out.println("error en: " + CMenuPrincipal.class.getName());
        }
    }

    public void llenarCBTiposTomas() {
        JComboBox<String> tipoTomas = MENU_PRINCIPAL.getJcbTipoToma();
        for (OTipoTomas o : FabricaCache.MC_TIPOS_DE_TOMAS.getLista()) {
            tipoTomas.addItem(o.getTipo());
        }
    }

    public void llenarCBCalles() {
        JComboBox<String> calles = MENU_PRINCIPAL.getJcbCalle();
        for (OCalles o : FabricaCache.MC_CALLES.getLista()) {
            calles.addItem(o.getCalleStr());
        }
    }

    public void vaciarCBTiposTomas() {
        MENU_PRINCIPAL.getJcbTipoToma().removeAllItems();

    }

    public void vaciarCBCalles() {
        MENU_PRINCIPAL.getJcbCalle().removeAllItems();
    }

    public void irMenuTesoreria() {
        
    }

    public void irMenuUsuarios() {
        
    }

    public void irMenuCalles() {
    }

    public void irMenuTipoDeTomas() {
    }

    /**
     * Metodo que cierra el menu principal y todas las ventanas en caso de que
     * esten activas
     */
    public void irLogin() {
        if (MENU_PRINCIPAL.menusActivos()) {
            MENU_PRINCIPAL.cerrarMenus();
        }
        MENU_PRINCIPAL.setVisible(false);
        MENU_PRINCIPAL.estadoInicial();
        Sesion instancia = Sesion.getInstancia();
        boolean finSesion = instancia.finSesion();
        if (!finSesion) {
            JOptionPane.showMessageDialog(MENU_PRINCIPAL, "ERROR AL REGISTRAR FIN DE SESION");
        }
        LOGIN.setVisible(true);
    }

}
