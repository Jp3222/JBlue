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
package com.jblue.vista.vistas.usuarios;

import com.jblue.vista.vistas.VUsuarios;

/**
 *
 * @author juan-campos
 */
public class VConsumidores extends javax.swing.JPanel {

    /**
     * Creates new form VConsumidores
     * @param aThis
     */
    public VConsumidores(VUsuarios aThis) {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campo_titular = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_consumidores = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setPreferredSize(new java.awt.Dimension(83, 30));
        jPanel1.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        campo_titular.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(campo_titular, java.awt.BorderLayout.CENTER);

        jLabel2.setPreferredSize(new java.awt.Dimension(41, 30));
        jPanel1.add(jLabel2, java.awt.BorderLayout.PAGE_END);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Titular: ");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 17));
        jPanel1.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jLabel4.setPreferredSize(new java.awt.Dimension(150, 17));
        jPanel1.add(jLabel4, java.awt.BorderLayout.LINE_END);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 600));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Consumidores");
        jPanel4.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        lista_consumidores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lista_consumidores);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.WEST);

        jPanel3.setLayout(new java.awt.GridLayout(8, 1));

        jButton1.setText("Agregar Consumidor");
        jPanel3.add(jButton1);

        jButton2.setText("Eliminar Consumidor");
        jPanel3.add(jButton2);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> campo_titular;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lista_consumidores;
    // End of variables declaration//GEN-END:variables
}
