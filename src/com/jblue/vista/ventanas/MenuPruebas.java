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
package com.jblue.vista.ventanas;

import com.jblue.vista.normas.SuperVentana;
import com.jblue.vista.vistas.bd.usuarios.VUsuarios;
import com.jblue.vista.vistas.cobros.VCobros;
import com.jblue.vista.vistas.normas.Vista;

/**
 *
 * @author jp
 */
public class MenuPruebas extends SuperVentana {

    private final Vista[] array;

    /**
     * Creates new form Pruebas
     */
    public MenuPruebas() {
        array = new Vista[2];
        array[0] = new VCobros();
        array[1] = new VUsuarios();
        //
        initComponents();
        //
        int i = 0;
        for (Vista j : array) {
            tab_root.addTab(j.getName(), j);
            if (j.getIcon() != null) {
                tab_root.setIconAt(i, j.getIcon());
            }
            i++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tab_root = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ventana de pruebas");

        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel1.setLayout(new java.awt.BorderLayout());

        tab_root.setName(""); // NOI18N
        tab_root.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel1.add(tab_root, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane tab_root;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void llamable() {
    }

    @Override
    public void estadoInicial() {
    }
}
