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
package com.jblue.views;

import com.jblue.views.components.ChooseFileComponent;
import com.jutil.dbcon.connection.JDBConnection;
import com.jutil.framework.ViewStates;
import com.jutil.swingw.modelos.JTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 */
public final class CSVImport extends javax.swing.JPanel implements ViewStates {

    /**
     * Creates new form CSVImport
     */
    public CSVImport() {
        initComponents();
        build();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        file_field = new javax.swing.JTextField();
        document_select_button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        table_name_field = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fields_input_field = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        save = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(900, 600));
        setName("Importar CSV"); // NOI18N
        setLayout(new java.awt.CardLayout(10, 10));

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(900, 200));
        jPanel1.setLayout(new java.awt.GridLayout(4, 0, 10, 10));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Archivo:");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel3.add(jLabel1, java.awt.BorderLayout.LINE_START);

        file_field.setEditable(false);
        jPanel3.add(file_field, java.awt.BorderLayout.CENTER);

        document_select_button.setText("Seleccionar Archivo");
        jPanel3.add(document_select_button, java.awt.BorderLayout.LINE_END);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Tabla:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel4.add(jLabel2, java.awt.BorderLayout.LINE_START);

        table_name_field.setEditable(false);
        jPanel4.add(table_name_field, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Campos Ingresados");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel5.add(jLabel3, java.awt.BorderLayout.LINE_START);

        fields_input_field.setEditable(false);
        jPanel5.add(fields_input_field, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5);

        jPanel6.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        save.setText("Guardar");
        jPanel7.add(save);

        Cancelar.setText("jButton2");
        jPanel7.add(Cancelar);

        jPanel6.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        add(jPanel6, "card4");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton document_select_button;
    private javax.swing.JTextField fields_input_field;
    private javax.swing.JTextField file_field;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton save;
    private javax.swing.JTextField table_name_field;
    // End of variables declaration//GEN-END:variables

    @Override
    public void build() {
        components();
        events();
        initialState();
        finalState();
    }

    @Override
    public void components() {

    }

    @Override
    public void events() {
        document_select_button.addActionListener((ae) -> {
            document_selected = ChooseFileComponent.seleccionarDocumento(null);
            if (document_selected == null && !document_selected.isFile()) {
                return;
            }
            file_field.setText(document_selected.getAbsolutePath());
            table_name_field.setText(document_selected.getName().replace(".csv", ""));
            csvProcessLoad(document_selected);
        });

        save.addActionListener((ae) -> {
            csvProcessInput();
        });
    }

    @Override
    public void initialState() {

    }

    @Override
    public void finalState() {
    }

    File document_selected;

    private void csvProcessLoad(File csv) {
        String aux;
        try (Reader r = new FileReader(csv); BufferedReader br = new BufferedReader(r);) {
            aux = br.readLine();
            if (aux.isBlank()) {
                return;
            }
            fields_input_field.setText(aux);
            JTableModel model = new JTableModel(aux.split(","), 0);
            jTable1.setModel(model);
            String[] arr;
            while ((aux = br.readLine()) != null) {
                arr = aux.split(",");
                model.addRow(arr);
            }
        } catch (IOException ex) {
            Logger.getLogger(CSVImport.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void csvProcessInput() {
//        DBConnection con = DBConnection.getInstance();
//        String name = table_name_field.getText();
//        String fields = fields_input_field.getText();
//        StringBuilder aux = new StringBuilder();
//        int i = 0;
//        JTableModel model = (JTableModel) jTable1.getModel();
//        while (i < jTable1.getRowCount() - 1) {
//            aux.append(getFormatt(model.getRow(i))).append(",\n");
//            //System.out.println(Arrays.toString(model.getRow(i)));
//            i++;
//        }
//        aux.append(getFormatt(model.getRow(i)));
//        try {
//            //JOptionPane.showMessageDialog(this, DBConnection.INSERT_COL.formatted(name, fields, aux.toString()));
//            //System.out.println(DBConnection.INSERT_COL.formatted(name, fields, aux.toString()));
//            fields = fields.replace("\"", "");
//            String aux2 = aux.toString().replace("\"", "");
//            boolean insert = con.insert(name, fields, aux);
//            if (insert) {
//                JOptionPane.showMessageDialog(this, DBConnection.INSERT_COL.formatted(name, fields, aux.toString()));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CSVImport.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private String getFormatt(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("NULL")) {
                continue;
            } else if (arr[i].isBlank()) {
                arr[i] = "NULL";
                continue;
            }
            arr[i] = "'%s'".formatted(arr[i]);
            //arr[i] = arr[i].replace("\"", "'");
        }
        return Arrays.toString(arr).replace("[", "(").replace("]", ")");
    }
}
