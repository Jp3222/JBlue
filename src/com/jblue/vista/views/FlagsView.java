/*
 * Copyright (C) 2025 juanp
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
package com.jblue.vista.views;

/**
 *
 * @author juanp
 */
public class FlagsView extends javax.swing.JPanel {

    /**
     * Creates new form FlagsView
     */
    public FlagsView() {
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

        north_panel = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        root_panel = new javax.swing.JPanel();
        register_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        search_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(900, 700));
        setLayout(new java.awt.BorderLayout());

        north_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        north_panel.setLayout(new java.awt.BorderLayout(10, 10));

        jButton8.setText("jButton8");
        jButton8.setPreferredSize(new java.awt.Dimension(100, 30));
        north_panel.add(jButton8, java.awt.BorderLayout.WEST);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton1.setText("Actualizar");
        jPanel3.add(jButton1);

        jButton2.setText("Consultar");
        jPanel3.add(jButton2);

        north_panel.add(jPanel3, java.awt.BorderLayout.CENTER);

        jButton9.setText("jButton9");
        jButton9.setPreferredSize(new java.awt.Dimension(100, 30));
        north_panel.add(jButton9, java.awt.BorderLayout.EAST);

        add(north_panel, java.awt.BorderLayout.PAGE_START);

        root_panel.setLayout(new java.awt.CardLayout(10, 10));

        register_panel.setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel1.setLayout(new java.awt.GridLayout(15, 0));

        jLabel3.setText("HORA DE APERTURA");
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel1.add(jLabel3);

        jLabel4.setText("HORA DE CIERRE");
        jPanel1.add(jLabel4);

        jLabel5.setText("DÍA DE COBRO");
        jPanel1.add(jLabel5);

        jLabel6.setText("COBRO AUTOMATICO");
        jPanel1.add(jLabel6);

        jLabel7.setText("USUARIO MAESTRO");
        jPanel1.add(jLabel7);

        jLabel8.setText("CONTRASEÑA MAESTRA");
        jPanel1.add(jLabel8);

        jLabel9.setText("VALIDAR HORA");
        jPanel1.add(jLabel9);

        register_panel.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setLayout(new java.awt.GridLayout(15, 0));

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("kk:mm:ss"))));
        jPanel2.add(jFormattedTextField1);

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("kk:mm:ss"))));
        jPanel2.add(jFormattedTextField2);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        jPanel2.add(jSpinner1);

        jCheckBox1.setToolTipText("");
        jPanel2.add(jCheckBox1);

        jTextField1.setText("jTextField1");
        jPanel2.add(jTextField1);

        jTextField2.setText("jTextField2");
        jPanel2.add(jTextField2);
        jPanel2.add(jCheckBox2);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton3.setText("Actualizar");
        jPanel4.add(jButton3);

        jButton5.setText("Cancelar");
        jPanel4.add(jButton5);

        jPanel2.add(jPanel4);

        register_panel.add(jPanel2, java.awt.BorderLayout.CENTER);

        root_panel.add(register_panel, "card2");

        search_panel.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel5.setPreferredSize(new java.awt.Dimension(880, 30));
        jPanel5.setLayout(new java.awt.BorderLayout(10, 10));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jButton4, java.awt.BorderLayout.LINE_START);

        jPanel6.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        jPanel6.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        jPanel6.add(jButton7);

        jPanel5.add(jPanel6, java.awt.BorderLayout.LINE_END);

        jTextField3.setText("jTextField3");
        jPanel5.add(jTextField3, java.awt.BorderLayout.CENTER);

        search_panel.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Variable", "Valor", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        search_panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        root_panel.add(search_panel, "card3");

        add(root_panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel north_panel;
    private javax.swing.JPanel register_panel;
    private javax.swing.JPanel root_panel;
    private javax.swing.JPanel search_panel;
    // End of variables declaration//GEN-END:variables
}
