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
package com.jblue.vista.vistas.menubd;

import com.jblue.vista.jbmarco.VistaExtendida;

/**
 *
 * @author jp
 */
public class VPagosXOtros extends VistaExtendida {

    /**
     * Creates new form VPagosXOtros
     */
    public VPagosXOtros() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setName("Pagos x Otros"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.LINE_START);

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[]{30,30,30};;
        jPanel1Layout.rowHeights = new int[]{30,30,30};;
        jPanel1Layout.columnWeights = new double[]{30,30,30};;
        jPanel1Layout.rowWeights = new double[]{30,30,30};;
        jPanel1.setLayout(jPanel1Layout);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jTextField1.setText("jTextField1");
        jPanel2.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, new java.awt.GridBagConstraints());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("jLabel1");
        jPanel3.add(jLabel2, java.awt.BorderLayout.LINE_START);

        jTextField2.setText("jTextField1");
        jPanel3.add(jTextField2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, new java.awt.GridBagConstraints());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("jLabel1");
        jPanel4.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, new java.awt.GridBagConstraints());

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
