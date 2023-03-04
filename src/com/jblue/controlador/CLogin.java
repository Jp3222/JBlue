/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.sistema.Sesion;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.crypto.EncriptadoAES;
import com.jblue.vista.ventanas.Login;
import com.jblue.vista.ventanas.MenuConfigBD;
import com.jblue.vista.ventanas.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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

    /**
     * Metodo que encripta los datos entrates y los busca en la base de datos
     *
     * @return true existe un objeto al realizar una busqueda con los datos
     * encriptados
     */
    public boolean isDatosValidos() {
        try {
            String usuario = LOGIN.getJtfUsuario().getText();
            String contra = String.valueOf(LOGIN.getJpfPass().getPassword());

            EncriptadoAES en = new EncriptadoAES();
            Operaciones<OPersonal> o = FabricaCache.OP_PERSONAL;
            String where = "usuario = '" + en.encriptar(usuario, contra) + "' && contra = '" + en.encriptar(contra, usuario) + "'";
            OPersonal personal = o.get(where);
            if (personal != null) {
                Sesion sesion = Sesion.getInstancia();
                sesion.setUsuario(personal);
                boolean inicioSesion = sesion.inicioSesion();
                if (!inicioSesion) {
                    JOptionPane.showMessageDialog(MENU_PRINCIPAL, "ERROR AL REGISTRAR SESION");
                    return false;
                }
                return true;
            }
        } catch (UnsupportedEncodingException
                | InvalidKeyException
                | NoSuchAlgorithmException
                | BadPaddingException
                | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Metodo de traslado al menu
     */
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
