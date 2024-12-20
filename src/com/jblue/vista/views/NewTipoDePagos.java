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
package com.jblue.vista.views;

import java.time.LocalDate;

/**
 *
 * @author juan-campos
 */
public class NewTipoDePagos extends javax.swing.JPanel {

    /**
     * Creates new form NewTipoDePagos
     */
    public NewTipoDePagos() {
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

        tools_panel = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel15 = new javax.swing.JPanel();
        register_button = new javax.swing.JButton();
        search_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        root_panel = new javax.swing.JPanel();
        register_panel = new javax.swing.JPanel();
        space_left = new javax.swing.JPanel();
        panel_campos = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campo_tipo = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        campo_costo = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cb_fecha_limite = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JL = new javax.swing.JLabel();
        campo_dia = new javax.swing.JComboBox<>();
        campo_mes = new javax.swing.JComboBox<>();
        campo_año = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        space_righ = new javax.swing.JPanel();
        panel_botones = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        search_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_recargar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtf_buscador = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btn_ant = new javax.swing.JButton();
        btn_sig = new javax.swing.JButton();
        panel_izq = new javax.swing.JPanel();
        tabla_usuarios = new javax.swing.JScrollPane();
        tabla_tipo_tomas = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(900, 500));
        setName("Tipo de pagos"); // NOI18N

        tools_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        tools_panel.setLayout(new java.awt.BorderLayout(10, 10));

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/search.png"))); // NOI18N
        jToggleButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jToggleButton2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/desbloquear.png"))); // NOI18N
        tools_panel.add(jToggleButton2, java.awt.BorderLayout.WEST);

        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        register_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        register_button.setText("Registrar Tipo de Tomas");
        jPanel15.add(register_button);

        search_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        search_button.setText("Consultar los Tipos de Tomas");
        jPanel15.add(search_button);

        tools_panel.add(jPanel15, java.awt.BorderLayout.CENTER);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/configuraciones.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        tools_panel.add(jButton2, java.awt.BorderLayout.EAST);

        root_panel.setLayout(new java.awt.CardLayout());

        register_panel.setName("Registrar"); // NOI18N
        register_panel.setLayout(new java.awt.BorderLayout(10, 10));

        space_left.setPreferredSize(new java.awt.Dimension(30, 100));

        javax.swing.GroupLayout space_leftLayout = new javax.swing.GroupLayout(space_left);
        space_left.setLayout(space_leftLayout);
        space_leftLayout.setHorizontalGroup(
            space_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        space_leftLayout.setVerticalGroup(
            space_leftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        register_panel.add(space_left, java.awt.BorderLayout.WEST);

        panel_campos.setPreferredSize(new java.awt.Dimension(500, 620));
        panel_campos.setLayout(new java.awt.GridLayout(10, 0, 0, 5));

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Tipos de pagos.");
        jLabel4.setPreferredSize(new java.awt.Dimension(500, 100));
        panel_campos.add(jLabel4);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel2.setText("Motivo: ");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel8.add(jLabel2, java.awt.BorderLayout.WEST);

        campo_tipo.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        campo_tipo.setName("Tipo de toma"); // NOI18N
        jPanel8.add(campo_tipo, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel3.setText("Monto: ");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel9.add(jLabel3, java.awt.BorderLayout.WEST);

        campo_costo.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        campo_costo.setName("Costo"); // NOI18N
        jPanel9.add(campo_costo, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel5.setText("Descripcion");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel10.add(jLabel5, java.awt.BorderLayout.WEST);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel10.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel10);

        cb_fecha_limite.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cb_fecha_limite.setText("Poner fecha limite");
        panel_campos.add(cb_fecha_limite);

        jPanel7.setLayout(new java.awt.GridLayout(2, 3));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel6.setText("Dia");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel7.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel7.setText("Mes");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel7.add(jLabel7);

        JL.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        JL.setText("Año");
        JL.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel7.add(JL);

        campo_dia.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel7.add(campo_dia);

        campo_mes.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel7.add(campo_mes);

        campo_año.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel7.add(campo_año);

        panel_campos.add(jPanel7);

        jButton1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jButton1.setText("Adjuntar Documentos");
        panel_campos.add(jButton1);

        jButton3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jButton3.setText("Ver archivos adjuntos");
        panel_campos.add(jButton3);

        register_panel.add(panel_campos, java.awt.BorderLayout.CENTER);

        space_righ.setPreferredSize(new java.awt.Dimension(30, 100));

        javax.swing.GroupLayout space_righLayout = new javax.swing.GroupLayout(space_righ);
        space_righ.setLayout(space_righLayout);
        space_righLayout.setHorizontalGroup(
            space_righLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        space_righLayout.setVerticalGroup(
            space_righLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        register_panel.add(space_righ, java.awt.BorderLayout.EAST);

        panel_botones.setPreferredSize(new java.awt.Dimension(500, 80));
        panel_botones.setLayout(new java.awt.GridLayout(2, 0));

        jPanel3.setLayout(new java.awt.GridLayout(1, 3));

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/disquete.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        jPanel3.add(btn_guardar);

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/sincronizar.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        jPanel3.add(btn_actualizar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/eliminar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        jPanel3.add(btn_eliminar);

        panel_botones.add(jPanel3);

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        panel_botones.add(btn_cancelar);

        register_panel.add(panel_botones, java.awt.BorderLayout.SOUTH);

        root_panel.add(register_panel, "Registrar");

        search_panel.setName("Consultar"); // NOI18N
        search_panel.setLayout(new java.awt.BorderLayout());

        jPanel5.setMinimumSize(new java.awt.Dimension(100, 30));
        jPanel5.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel5.setLayout(new java.awt.BorderLayout(10, 10));

        btn_recargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        btn_recargar.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel5.add(btn_recargar, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/search.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel6.add(jLabel1, java.awt.BorderLayout.WEST);
        jPanel6.add(jtf_buscador, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.GridLayout(1, 2));

        btn_ant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        btn_ant.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(btn_ant);

        btn_sig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        btn_sig.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(btn_sig);

        jPanel5.add(jPanel11, java.awt.BorderLayout.EAST);

        search_panel.add(jPanel5, java.awt.BorderLayout.NORTH);

        panel_izq.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_izq.setLayout(new java.awt.BorderLayout(10, 10));

        tabla_tipo_tomas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_usuarios.setViewportView(tabla_tipo_tomas);

        panel_izq.add(tabla_usuarios, java.awt.BorderLayout.CENTER);

        search_panel.add(panel_izq, java.awt.BorderLayout.CENTER);

        root_panel.add(search_panel, "Consultar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tools_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(root_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(tools_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(root_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_ant;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_recargar;
    private javax.swing.JButton btn_sig;
    private javax.swing.JComboBox<Integer> campo_año;
    private javax.swing.JTextField campo_costo;
    private javax.swing.JComboBox<Integer> campo_dia;
    private javax.swing.JComboBox<String> campo_mes;
    private javax.swing.JTextField campo_tipo;
    private javax.swing.JCheckBox cb_fecha_limite;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextField jtf_buscador;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel panel_campos;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JButton register_button;
    private javax.swing.JPanel register_panel;
    private javax.swing.JPanel root_panel;
    private javax.swing.JButton search_button;
    private javax.swing.JPanel search_panel;
    private javax.swing.JPanel space_left;
    private javax.swing.JPanel space_righ;
    private javax.swing.JTable tabla_tipo_tomas;
    private javax.swing.JScrollPane tabla_usuarios;
    private javax.swing.JPanel tools_panel;
    // End of variables declaration//GEN-END:variables

    private void buildDate() {
        LocalDate o = LocalDate.now();
        
    }
}
