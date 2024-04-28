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

import com.jblue.util.mg.ModeloTablas;
import com.jblue.modelo.ConstGs;
import com.jblue.sistema.app.AppFiles;
import com.jblue.vista.comp.CSelectorDeArchivos;
import com.jblue.vista.jbmarco.VistaExtendida;
import com.jutil.jexception.Excp;
import com.mysql.cj.result.AbstractNumericValueFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author jp
 */
public class VPagosXOtros extends VistaExtendida {

    private final ModeloTablas modelo;

    /**
     * Creates new form VPagosXOtros
     */
    public VPagosXOtros() {
        initComponents();

        modelo = new ModeloTablas(ConstGs.BD_PAGOS_X_OTROS_TIPOS);
        jTable1.setModel(modelo);

        llamable();
    }

    @Override
    protected final void llamable() {
        super.llamable(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        manejoEventos();
    }

    @Override
    protected void manejoEventos() {

        jButton1.addActionListener(e -> {
            File file = CSelectorDeArchivos.seleccionarDocumento(null);
            if (file == null) {
                return;
            }
            try {
                System.out.println(file.getAbsoluteFile());
                File o = new File(AppFiles.FIL_DIR_PROG, file.getName());
                if (!o.exists()) {
                    o.createNewFile();
                }
                Files.copy(file.toPath(), new FileOutputStream(o));
            } catch (IOException ex) {
                Excp.impTerminal(ex, getClass(), true);
            }
        });

    }

    public void add() {
        NumberFormatter n = (NumberFormatter) jFormattedTextField2.getFormatter();
        
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
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setName("Pagos x Otros"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.LINE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 300));
        jPanel5.setLayout(new java.awt.GridLayout(5, 1, 0, 10));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Motivo");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel2.add(jLabel1, java.awt.BorderLayout.WEST);
        jPanel2.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel2);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Monto");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel4.add(jLabel3, java.awt.BorderLayout.WEST);

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        jPanel4.add(jFormattedTextField2, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel4);

        jButton1.setText("Añadir Documentos");
        jPanel5.add(jButton1);

        jPanel1.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Descripcion");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel3.add(jLabel2, java.awt.BorderLayout.NORTH);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel7.setLayout(new java.awt.GridLayout(2, 0));

        jPanel6.setLayout(new java.awt.GridLayout(1, 3));

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/disquete.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        jPanel6.add(btn_guardar);

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/sincronizar.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        jPanel6.add(btn_actualizar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/eliminar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        jPanel6.add(btn_eliminar);

        jPanel7.add(jPanel6);

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        jPanel7.add(btn_cancelar);

        jPanel1.add(jPanel7, java.awt.BorderLayout.SOUTH);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
