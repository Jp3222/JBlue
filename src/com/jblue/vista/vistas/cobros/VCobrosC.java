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
package com.jblue.vista.vistas.cobros;

import com.jblue.vista.normas.SuperVista;

/**
 *
 * @author jp
 */
class VCobrosC extends SuperVista {

    /**
     * Creates new form VCobrosR
     */
    public VCobrosC(VCobros root) {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        filtros_fecha = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setName("Cobros realizados"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanel15.setName("Cobros realizados"); // NOI18N
        jPanel15.setPreferredSize(new java.awt.Dimension(1200, 100));
        jPanel15.setLayout(new java.awt.BorderLayout());

        filtros_fecha.setModel(new javax.swing.SpinnerDateModel());
        jPanel15.add(filtros_fecha, java.awt.BorderLayout.PAGE_START);

        add(jPanel15, java.awt.BorderLayout.NORTH);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable2);

        add(jScrollPane3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner filtros_fecha;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void llamable() {
    }

    @Override
    public void componentesEstadoInicial() {
    }
}
