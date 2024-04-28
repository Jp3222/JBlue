/*
 * Copyright (C) 2023 jp
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
package com.jblue.vista.vistas.menubd.usuarios;

import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import com.jblue.vista.jbmarco.VistaExtendida;
import com.jblue.vista.vistas.menubd.usuarios.sub.VUsuariosC;
import com.jblue.vista.vistas.menubd.usuarios.sub.VUsuariosR;
import java.awt.CardLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author jp
 */
public class VUsuarios extends VistaExtendida {

    /**
     * Creates new form VUsuarios
     */
    public VUsuarios() {
        initComponents();
        memo_cache = FabricaCache.MC_USUARIOS;
        registros = new VUsuariosR(this);
        consultas = new VUsuariosC(this);
        ly = (CardLayout) getLayout();
        llamable();
    }

    @Override
    protected final void llamable() {
        construirComponentes();
        componentesEstadoFinal();
        componentesEstadoInicial();
        manejoEventos();
    }

    @Override
    protected void construirComponentes() {

    }

    @Override
    protected void componentesEstadoFinal() {
        add(consultas.getName(), consultas);
        add(registros.getName(), registros);
    }

    @Override
    public void componentesEstadoInicial() {
        showRegistros();

    }

    @Override
    protected void manejoEventos() {

    }

    public void showRegistros() {
        ly.show(this, registros.getName());
    }

    public void showConsultas() {
        ly.show(this, consultas.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("BD Usuarios"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 660));
        setLayout(new java.awt.CardLayout());
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public ArrayList<JMenu> getMenu() {
        ArrayList<JMenu> menu = new ArrayList<>();
        menu.add(itemMenuPrincipal());
        return menu;
    }

    private JMenu itemMenuPrincipal() {
        JMenu menu = new JMenu("Menu");

        menu.add(
                crearMenuItem("Registros de usuarios", null, e -> ly.show(this, registros.getName()))
        );

        menu.add(
                crearMenuItem("Consulta de usuarios", null, e -> ly.show(this, consultas.getName()))
        );

        menu.add(new JPopupMenu.Separator());

        menu.add(
                crearMenuItem("Salir", null, null)
        );

        return menu;
    }

    private JMenu itemMenuVer() {
        JMenu menu = new JMenu("Ver");
        return menu;
    }

    private JMenu itemMenuEditar() {
        return null;
    }

    public JMenuItem crearMenuItem(String texto, Icon icono, ActionListener evento) {
        JMenuItem item = new JMenuItem(texto);
        if (icono != null) {
            item.setIcon(icono);
        }
        if (evento != null) {
            item.addActionListener(evento);
        }
        return item;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private final MemoCache<OUsuarios> memo_cache;
    private final CardLayout ly;
    private final VUsuariosR registros;
    private final VUsuariosC consultas;

    @Override
    public Rectangle getBounds() {
        registros.getBounds();
        return super.getBounds(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public MemoCache<OUsuarios> getMemo_cache() {
        return memo_cache;
    }
    
    

}