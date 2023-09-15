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
package com.jblue.vista.vistas.bd;

import com.jblue.vista.vistas.normas.Vista;
import java.util.ArrayList;
import javax.swing.JMenu;

/**
 *
 * @author jp
 */
public class VTipoTomas extends Vista {

    /**
     * Creates new form VTipoTomas
     */
    public VTipoTomas() {
        initComponents();
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
    public void componentesEstadoInicial() {
        campo_tipo.setText(null);
        campo_costo.setText(null);
        campo_recargo.setText(null);
        habilitarBotones(false);
    }

    public void habilitarBotones(boolean estado) {
        btn_guardar.setEnabled(!estado);
        btn_actualizar.setEnabled(estado);
        btn_eliminar.setEnabled(estado);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_izq = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtf_buscador = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btn_recargar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btn_ant = new javax.swing.JButton();
        btn_sig = new javax.swing.JButton();
        tabla_usuarios = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panel_der = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campo_tipo = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        campo_costo = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        campo_recargo = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        panel_izq.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_izq.setLayout(new java.awt.BorderLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(100, 30));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/buscar.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel4.add(jLabel1, java.awt.BorderLayout.WEST);
        jPanel4.add(jtf_buscador, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        btn_recargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        btn_recargar.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel5.add(btn_recargar, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        btn_ant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        btn_ant.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel6.add(btn_ant);

        btn_sig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        btn_sig.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel6.add(btn_sig);

        jPanel5.add(jPanel6, java.awt.BorderLayout.LINE_END);

        jPanel3.add(jPanel5);

        panel_izq.add(jPanel3, java.awt.BorderLayout.NORTH);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_usuarios.setViewportView(jTable1);

        panel_izq.add(tabla_usuarios, java.awt.BorderLayout.CENTER);

        panel_der.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_der.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Datos del tipo de toma");
        jLabel4.setPreferredSize(new java.awt.Dimension(500, 100));
        panel_der.add(jLabel4, java.awt.BorderLayout.NORTH);

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 620));
        jPanel1.setLayout(new java.awt.GridLayout(7, 0, 0, 5));

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Tipo de toma:");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel8.add(jLabel2, java.awt.BorderLayout.NORTH);
        jPanel8.add(campo_tipo, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Costo:");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel9.add(jLabel3, java.awt.BorderLayout.NORTH);
        jPanel9.add(campo_costo, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel5.setLabelFor(campo_recargo);
        jLabel5.setText("Costro de recargo:");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel10.add(jLabel5, java.awt.BorderLayout.NORTH);
        jPanel10.add(campo_recargo, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel10);

        panel_der.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel7.setLayout(new java.awt.GridLayout(2, 0));

        jPanel2.setLayout(new java.awt.GridLayout(1, 3));

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/disquete.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        jPanel2.add(btn_guardar);

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/sincronizar.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        jPanel2.add(btn_actualizar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/eliminar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        jPanel2.add(btn_eliminar);

        jPanel7.add(jPanel2);

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        jPanel7.add(btn_cancelar);

        panel_der.add(jPanel7, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel_izq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(panel_der, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel_izq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_der, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public ArrayList<JMenu> getMenu() {
        return null;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_ant;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_recargar;
    private javax.swing.JButton btn_sig;
    private javax.swing.JTextField campo_costo;
    private javax.swing.JTextField campo_recargo;
    private javax.swing.JTextField campo_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtf_buscador;
    private javax.swing.JPanel panel_der;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JScrollPane tabla_usuarios;
    // End of variables declaration//GEN-END:variables

}
