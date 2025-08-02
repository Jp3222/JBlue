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
package com.jblue.views.components;

import com.jblue.model.constants.Const;
import com.jblue.model.factories.CacheFactory;
import com.jblue.model.dtos.OUser;
import com.jblue.model.dtos.Objeto;
import com.jblue.util.cache.MemoListCache;
import com.jutil.framework.ComponentStates;
import com.jutil.swingw.modelos.JTableModel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author jp
 */
public final class UserViewComponent extends JDialog implements ComponentStates {

    public static UserViewComponent showVisor(OUser obj) {
        if (obj == null) {
            return null;
        }
        UserViewComponent o = new UserViewComponent(null, true);
        o.setUsuario(obj);
        o.setTitle(obj.toString());
        o.setVisible(true);
        return o;
    }

    private OUser usuario;

    private final JTextField[] campos;
    private final JTableModel modelo_pagos_x_servicio;
    private final JTableModel modelo_pagos_x_recargo;
    private final JTableModel modelo_pagos_x_otros;

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form CVisorUsuario
     *
     * @param parent
     * @param modal
     */
    public UserViewComponent(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        campos = new JTextField[]{
            id_field,
            name_field,
            last_name1_field,
            last_name2_field,
            street_field,
            house_number_field,
            water_intake_type_field,
            date_register_field,
            status_field,
            user_type_field,
            dato_codigo
        };
        //
        modelo_pagos_x_servicio = new JTableModel(Const.SERVICE_PAYMENTS.getFields(), 0);
        modelo_pagos_x_recargo = new JTableModel(Const.SURCHARGE_PAYMENTS.getFields(), 0);
        modelo_pagos_x_otros = new JTableModel(Const.OTHER_PAYMENTS.getFields(), 0);
        //
        service_payments.setModel(modelo_pagos_x_servicio);
        surcharge_payments.setModel(modelo_pagos_x_recargo);
        other_payments.setModel(modelo_pagos_x_otros);
        //
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);

        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });

        CardLayout ly = (CardLayout) card_panel.getLayout();
        ly.show(card_panel, tab_info_usuario.getName());
        build();
    }
    private int returnStatus = RET_CANCEL;

    @Override
    public void build() {
        components();
        events();
        finalState();
        initialState();
    }

    @Override
    public void events() {
    }

    @Override
    public void components() {
    }

    @Override
    public void initialState() {
        for (JTextField i : campos) {
            i.setText(null);
        }
        if (modelo_pagos_x_servicio.getRowCount() > 0) {
            modelo_pagos_x_servicio.removeAllRows();
        }
        if (modelo_pagos_x_recargo.getRowCount() > 0) {
            modelo_pagos_x_recargo.removeAllRows();
        }
        if (modelo_pagos_x_otros.getRowCount() > 0) {
            modelo_pagos_x_recargo.removeAllRows();
        }
        tab_info_pagos.setSelectedIndex(0);
        tab_info_usuario.setSelectedIndex(0);

        CardLayout ly = (CardLayout) card_panel.getLayout();
        ly.show(card_panel, tab_info_usuario.getName());
    }

    @Override
    public void finalState() {
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        root_panel = new javax.swing.JPanel();
        left_panel = new javax.swing.JPanel();
        pl_foto = new javax.swing.JLabel();
        pl_panel_central = new javax.swing.JPanel();
        first_name_lat = new javax.swing.JLabel();
        last_name_lat = new javax.swing.JLabel();
        espacio1 = new javax.swing.JLabel();
        user_info = new javax.swing.JButton();
        payments_info = new javax.swing.JButton();
        center_panel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        card_panel = new javax.swing.JPanel();
        tab_info_pagos = new javax.swing.JTabbedPane();
        panel_pxs = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        recargar_pxs = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        filtro_pxs_año = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        service_payments = new javax.swing.JTable();
        panel_pxr = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        surcharge_payments = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        filtro_pxr_año = new javax.swing.JSpinner();
        panel_pxo = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        filtro_pxo_año = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        other_payments = new javax.swing.JTable();
        tab_info_usuario = new javax.swing.JTabbedPane();
        panel_datos_usuario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panel_camp_id = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        id_field = new javax.swing.JTextField();
        panel_camp_nombre = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        last_name1_field = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        last_name2_field = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        street_field = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        house_number_field = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        water_intake_type_field = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        date_register_field = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        status_field = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        user_type_field = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        dato_codigo = new javax.swing.JTextField();
        panel_datos_contacto = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panel_campo_correo = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        dato_correo = new javax.swing.JTextField();
        panel_campo_tel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        dato_tel_1 = new javax.swing.JTextField();
        panel_campo_tel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        dato_tel_2 = new javax.swing.JTextField();
        bottom_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        root_panel.setPreferredSize(new java.awt.Dimension(1000, 570));
        root_panel.setLayout(new java.awt.BorderLayout());

        left_panel.setPreferredSize(new java.awt.Dimension(300, 700));
        left_panel.setLayout(new java.awt.BorderLayout());

        pl_foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pl_foto.setText("Sin foto");
        pl_foto.setPreferredSize(new java.awt.Dimension(300, 250));
        pl_foto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pl_fotoMouseClicked(evt);
            }
        });
        left_panel.add(pl_foto, java.awt.BorderLayout.PAGE_START);

        pl_panel_central.setLayout(new java.awt.GridLayout(8, 0));

        first_name_lat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        first_name_lat.setText("Nombre");
        first_name_lat.setPreferredSize(new java.awt.Dimension(300, 20));
        pl_panel_central.add(first_name_lat);

        last_name_lat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        last_name_lat.setText("Apellidos");
        last_name_lat.setPreferredSize(new java.awt.Dimension(300, 20));
        pl_panel_central.add(last_name_lat);

        espacio1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        espacio1.setPreferredSize(new java.awt.Dimension(300, 20));
        pl_panel_central.add(espacio1);

        user_info.setText("Datos de usuario");
        user_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_infoActionPerformed(evt);
            }
        });
        pl_panel_central.add(user_info);

        payments_info.setText("Informacion de pagos");
        payments_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payments_infoActionPerformed(evt);
            }
        });
        pl_panel_central.add(payments_info);

        left_panel.add(pl_panel_central, java.awt.BorderLayout.CENTER);

        root_panel.add(left_panel, java.awt.BorderLayout.WEST);

        center_panel.setLayout(new java.awt.BorderLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(10, 5));
        center_panel.add(jSeparator1, java.awt.BorderLayout.WEST);

        card_panel.setLayout(new java.awt.CardLayout());

        tab_info_pagos.setName("Tab Info De Pagos"); // NOI18N

        panel_pxs.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 40));
        jPanel1.setLayout(new java.awt.BorderLayout());

        recargar_pxs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        recargar_pxs.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel1.add(recargar_pxs, java.awt.BorderLayout.LINE_END);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Año:");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel1.add(jLabel2, java.awt.BorderLayout.WEST);

        filtro_pxs_año.setModel(new javax.swing.SpinnerNumberModel());
        jPanel1.add(filtro_pxs_año, java.awt.BorderLayout.CENTER);

        panel_pxs.add(jPanel1, java.awt.BorderLayout.NORTH);

        service_payments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(service_payments);

        panel_pxs.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tab_info_pagos.addTab("Pagos del servicio", panel_pxs);

        panel_pxr.setLayout(new java.awt.BorderLayout());

        surcharge_payments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(surcharge_payments);

        panel_pxr.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(700, 40));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel6.add(jButton4, java.awt.BorderLayout.LINE_END);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Año:");
        jLabel17.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel6.add(jLabel17, java.awt.BorderLayout.WEST);

        filtro_pxr_año.setModel(new javax.swing.SpinnerNumberModel());
        jPanel6.add(filtro_pxr_año, java.awt.BorderLayout.CENTER);

        panel_pxr.add(jPanel6, java.awt.BorderLayout.NORTH);

        tab_info_pagos.addTab("Pagos por recargos", panel_pxr);

        panel_pxo.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(700, 40));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel7.add(jButton5, java.awt.BorderLayout.LINE_END);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Año:");
        jLabel18.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel7.add(jLabel18, java.awt.BorderLayout.WEST);

        filtro_pxo_año.setModel(new javax.swing.SpinnerNumberModel());
        jPanel7.add(filtro_pxo_año, java.awt.BorderLayout.CENTER);

        panel_pxo.add(jPanel7, java.awt.BorderLayout.NORTH);

        other_payments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(other_payments);

        panel_pxo.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        tab_info_pagos.addTab("Otros Pagos", panel_pxo);

        card_panel.add(tab_info_pagos, "Tab Info De Pagos");
        tab_info_pagos.getAccessibleContext().setAccessibleName("");

        tab_info_usuario.setName("Tab Info De Usuario"); // NOI18N

        panel_datos_usuario.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel4.setPreferredSize(new java.awt.Dimension(700, 620));
        jPanel4.setLayout(new java.awt.GridLayout(11, 1, 5, 5));

        panel_camp_id.setLayout(new java.awt.BorderLayout());

        jLabel13.setText("ID:");
        jLabel13.setPreferredSize(new java.awt.Dimension(150, 30));
        panel_camp_id.add(jLabel13, java.awt.BorderLayout.WEST);

        id_field.setEditable(false);
        panel_camp_id.add(id_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(panel_camp_id);

        panel_camp_nombre.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Nombre:");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 30));
        panel_camp_nombre.add(jLabel3, java.awt.BorderLayout.WEST);

        name_field.setEditable(false);
        panel_camp_nombre.add(name_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(panel_camp_nombre);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Apellido Paterno:");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel8.add(jLabel4, java.awt.BorderLayout.WEST);

        last_name1_field.setEditable(false);
        jPanel8.add(last_name1_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Apellido Materno:");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel9.add(jLabel5, java.awt.BorderLayout.WEST);

        last_name2_field.setEditable(false);
        jPanel9.add(last_name2_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Calle:");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel10.add(jLabel6, java.awt.BorderLayout.WEST);

        street_field.setEditable(false);
        jPanel10.add(street_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel10);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Numero de casa");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel11.add(jLabel7, java.awt.BorderLayout.WEST);

        house_number_field.setEditable(false);
        jPanel11.add(house_number_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel11);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("TIpo de toma");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel12.add(jLabel8, java.awt.BorderLayout.WEST);

        water_intake_type_field.setEditable(false);
        water_intake_type_field.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel12.add(water_intake_type_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Fecha de registro");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel13.add(jLabel9, java.awt.BorderLayout.WEST);

        date_register_field.setEditable(false);
        date_register_field.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel13.add(date_register_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("Estado");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel14.add(jLabel10, java.awt.BorderLayout.WEST);

        status_field.setEditable(false);
        status_field.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel14.add(status_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel14);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Titular");
        jLabel11.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel15.add(jLabel11, java.awt.BorderLayout.WEST);

        user_type_field.setEditable(false);
        user_type_field.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel15.add(user_type_field, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel15);

        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("Codigo:");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel16.add(jLabel12, java.awt.BorderLayout.WEST);

        dato_codigo.setEditable(false);
        dato_codigo.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel16.add(dato_codigo, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel16);

        panel_datos_usuario.add(jPanel4, java.awt.BorderLayout.CENTER);

        tab_info_usuario.addTab("Datos de usuario", panel_datos_usuario);

        panel_datos_contacto.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(700, 620));
        jPanel5.setLayout(new java.awt.GridLayout(11, 1, 5, 5));

        panel_campo_correo.setLayout(new java.awt.BorderLayout());

        jLabel14.setText("Correo electronico: ");
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 30));
        panel_campo_correo.add(jLabel14, java.awt.BorderLayout.WEST);

        dato_correo.setEditable(false);
        panel_campo_correo.add(dato_correo, java.awt.BorderLayout.CENTER);

        jPanel5.add(panel_campo_correo);

        panel_campo_tel1.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("Telefono 1:");
        jLabel15.setPreferredSize(new java.awt.Dimension(150, 30));
        panel_campo_tel1.add(jLabel15, java.awt.BorderLayout.WEST);

        dato_tel_1.setEditable(false);
        panel_campo_tel1.add(dato_tel_1, java.awt.BorderLayout.CENTER);

        jPanel5.add(panel_campo_tel1);

        panel_campo_tel2.setLayout(new java.awt.BorderLayout());

        jLabel16.setText("Telefono 2:");
        jLabel16.setPreferredSize(new java.awt.Dimension(150, 30));
        panel_campo_tel2.add(jLabel16, java.awt.BorderLayout.WEST);

        dato_tel_2.setEditable(false);
        panel_campo_tel2.add(dato_tel_2, java.awt.BorderLayout.CENTER);

        jPanel5.add(panel_campo_tel2);

        panel_datos_contacto.add(jPanel5, java.awt.BorderLayout.CENTER);

        tab_info_usuario.addTab("informacion complementaria", panel_datos_contacto);

        card_panel.add(tab_info_usuario, "Tab Info De Usuario");

        center_panel.add(card_panel, java.awt.BorderLayout.CENTER);

        root_panel.add(center_panel, java.awt.BorderLayout.CENTER);

        bottom_panel.setPreferredSize(new java.awt.Dimension(1000, 35));
        bottom_panel.setLayout(new java.awt.BorderLayout());
        bottom_panel.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel24.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel24.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel24.add(okButton);
        getRootPane().setDefaultButton(okButton);

        bottom_panel.add(jPanel24, java.awt.BorderLayout.EAST);

        root_panel.add(bottom_panel, java.awt.BorderLayout.SOUTH);

        getContentPane().add(root_panel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        doClose(RET_OK);
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void user_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_infoActionPerformed
        CardLayout ly = (CardLayout) card_panel.getLayout();
        ly.show(card_panel, tab_info_usuario.getName());

    }//GEN-LAST:event_user_infoActionPerformed

    private void payments_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payments_infoActionPerformed
        CardLayout ly = (CardLayout) card_panel.getLayout();
        ly.show(card_panel, tab_info_pagos.getName());

    }//GEN-LAST:event_payments_infoActionPerformed

    private void pl_fotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pl_fotoMouseClicked

        if (evt.getClickCount() == 2) {
            JOptionPane.showConfirmDialog(this, "Quiere agregar una foto a este usuario", "Foto de usuario", JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
    }//GEN-LAST:event_pl_fotoMouseClicked

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    public OUser getUsuario() {
        return usuario;
    }

    public void setUsuario(OUser usuario) {
        this.usuario = usuario;
        loadUserInformation();
    }

    private void loadUserInformation() {
        id_field.setText(usuario.getId());
        name_field.setText(usuario.getName());
        last_name1_field.setText(usuario.getLastName1());
        last_name2_field.setText(usuario.getLastName2());
        street_field.setText(usuario.getStreetObject().getNombre());
        house_number_field.setText(usuario.getHouseNumber());
        water_intake_type_field.setText(usuario.getWaterIntakesObject().getType());
        date_register_field.setText(usuario.getLastName2());
        status_field.setText(usuario.getStatusString());
        user_type_field.setText(usuario.getUserTypeString());
        loadUserPayments();
    }

    private void loadUserPayments() {
        loadServicePayment();
        loadSurchagePayment();
        loadOtherPayment();
    }

    private void loadServicePayment() {
        load(modelo_pagos_x_servicio, CacheFactory.SERVICE_PAYMENTS);
    }

    private void loadOtherPayment() {
        load(modelo_pagos_x_otros, CacheFactory.OTHER_PAYMENTS);
    }

    private void loadSurchagePayment() {
        load(modelo_pagos_x_recargo, CacheFactory.SURCHARGE_PAYMENTS);
    }

    private <T extends Objeto> void load(JTableModel model, MemoListCache<T> cache) {
        List<T> select = cache.getConnection().select("*", "user = %s".formatted(usuario.getId()));
        for (T i : select) {
            model.addRow(i.getInfo());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottom_panel;
    private javax.swing.JPanel card_panel;
    private javax.swing.JPanel center_panel;
    private javax.swing.JTextField date_register_field;
    private javax.swing.JTextField dato_codigo;
    private javax.swing.JTextField dato_correo;
    private javax.swing.JTextField dato_tel_1;
    private javax.swing.JTextField dato_tel_2;
    private javax.swing.JLabel espacio1;
    private javax.swing.JSpinner filtro_pxo_año;
    private javax.swing.JSpinner filtro_pxr_año;
    private javax.swing.JSpinner filtro_pxs_año;
    private javax.swing.JLabel first_name_lat;
    private javax.swing.JTextField house_number_field;
    private javax.swing.JTextField id_field;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField last_name1_field;
    private javax.swing.JTextField last_name2_field;
    private javax.swing.JLabel last_name_lat;
    private javax.swing.JPanel left_panel;
    private javax.swing.JTextField name_field;
    private javax.swing.JButton okButton;
    private javax.swing.JTable other_payments;
    private javax.swing.JPanel panel_camp_id;
    private javax.swing.JPanel panel_camp_nombre;
    private javax.swing.JPanel panel_campo_correo;
    private javax.swing.JPanel panel_campo_tel1;
    private javax.swing.JPanel panel_campo_tel2;
    private javax.swing.JPanel panel_datos_contacto;
    private javax.swing.JPanel panel_datos_usuario;
    private javax.swing.JPanel panel_pxo;
    private javax.swing.JPanel panel_pxr;
    private javax.swing.JPanel panel_pxs;
    private javax.swing.JButton payments_info;
    private javax.swing.JLabel pl_foto;
    private javax.swing.JPanel pl_panel_central;
    private javax.swing.JButton recargar_pxs;
    private javax.swing.JPanel root_panel;
    private javax.swing.JTable service_payments;
    private javax.swing.JTextField status_field;
    private javax.swing.JTextField street_field;
    private javax.swing.JTable surcharge_payments;
    private javax.swing.JTabbedPane tab_info_pagos;
    private javax.swing.JTabbedPane tab_info_usuario;
    private javax.swing.JButton user_info;
    private javax.swing.JTextField user_type_field;
    private javax.swing.JTextField water_intake_type_field;
    // End of variables declaration//GEN-END:variables

}
