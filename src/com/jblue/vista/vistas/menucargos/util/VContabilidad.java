/*
 * Copyright (C) 2024 juan-campos
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
package com.jblue.vista.vistas.menucargos.util;

import com.jblue.controlador.Contabilidad;

/**
 *
 * @author juan-campos
 */
public class VContabilidad extends javax.swing.JPanel {

    /**
     * Creates new form VContabilidad
     */
    public VContabilidad() {
        initComponents();
        dia.setText(String.valueOf(Contabilidad.getSaldoDelDia()));
        mes.setText(String.valueOf(Contabilidad.getSaldoDelMes()));
        año.setText(String.valueOf(Contabilidad.getSaldoDelAño()));
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
        jLabel1 = new javax.swing.JLabel();
        dia = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mes = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        año = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        Mes = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.GridLayout(12, 1));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Saldo del dia acumulado: ");
        jLabel1.setToolTipText("");
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 19));
        jPanel1.add(jLabel1, java.awt.BorderLayout.WEST);

        dia.setEditable(false);
        jPanel1.add(dia, java.awt.BorderLayout.CENTER);

        add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Saldo del mes acumulado: ");
        jLabel2.setToolTipText("");
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 19));
        jPanel2.add(jLabel2, java.awt.BorderLayout.WEST);

        mes.setEditable(false);
        jPanel2.add(mes, java.awt.BorderLayout.CENTER);

        add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Saldo del año acumulado: ");
        jLabel3.setToolTipText("");
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 19));
        jPanel3.add(jLabel3, java.awt.BorderLayout.WEST);

        año.setEditable(false);
        jPanel3.add(año, java.awt.BorderLayout.CENTER);

        add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Consultar Fecha");
        jLabel4.setToolTipText("");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel4.add(jLabel4, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Dia");
        jLabel6.setPreferredSize(new java.awt.Dimension(42, 30));
        jPanel7.add(jLabel6, java.awt.BorderLayout.NORTH);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel7.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel7);

        jPanel8.setLayout(new java.awt.BorderLayout());

        Mes.setText("Mes");
        Mes.setPreferredSize(new java.awt.Dimension(42, 30));
        jPanel8.add(Mes, java.awt.BorderLayout.NORTH);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel8.add(jComboBox2, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Año");
        jLabel8.setPreferredSize(new java.awt.Dimension(42, 30));
        jPanel9.add(jLabel8, java.awt.BorderLayout.NORTH);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel9.add(jComboBox3, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel9);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jButton1.setText("Consultar");
        jPanel4.add(jButton1, java.awt.BorderLayout.EAST);

        add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Saldo");
        jLabel5.setToolTipText("");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel5.add(jLabel5, java.awt.BorderLayout.WEST);
        jPanel5.add(jTextField5, java.awt.BorderLayout.CENTER);

        add(jPanel5);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        add(jPanel10);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Mes;
    private javax.swing.JTextField año;
    private javax.swing.JTextField dia;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField mes;
    // End of variables declaration//GEN-END:variables
}
