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
package com.jblue.vista.vistas;

import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.fabricas.FabricaCache;
import com.jblue.util.trash.MemoCache;
import com.jblue.vista.marco.vistas.VistaExtendida;
import com.jblue.vista.vistas.usuarios.VConsumidores;
import com.jblue.vista.vistas.usuarios.VUsuariosC;
import com.jblue.vista.vistas.usuarios.VUsuariosR;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
        panel_registros = new VUsuariosR(this);
        panel_consultas = new VUsuariosC(this);
        panel_consumidores = new VConsumidores(this);
        llamable();
    }

    @Override
    protected final void llamable() {
        construirComponentes();
        componentesEstadoFinal();
        componentesEstadoInicial();
        eventos();
    }

    @Override
    protected void construirComponentes() {

    }

    @Override
    protected void componentesEstadoFinal() {
        jTabbedPane1.add(panel_registros.getName(), panel_registros);
        jTabbedPane1.add(panel_consultas.getName(), panel_consultas);
    }

    @Override
    public void componentesEstadoInicial() {

    }

    @Override
    protected void eventos() {
        jTabbedPane1.addChangeListener((e) -> {
            System.out.println(jTabbedPane1.getSelectedIndex());
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setName("BD Usuarios"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 660));
        setLayout(new java.awt.BorderLayout());
        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    private final MemoCache<OUsuarios> memo_cache;
    private final VUsuariosR panel_registros;
    private final VUsuariosC panel_consultas;
    private final VConsumidores panel_consumidores;

    @Override
    public Rectangle getBounds() {
        panel_registros.getBounds();
        return super.getBounds(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public MemoCache<OUsuarios> getMemo_cache() {
        return memo_cache;
    }

}
