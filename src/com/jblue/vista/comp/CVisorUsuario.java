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
package com.jblue.vista.comp;

import com.jblue.mg.ModeloTablas;
import com.jblue.modelo.ConstGs;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OPagosRecargos;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.cache.FabricaOpraciones;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author jp
 */
public class CVisorUsuario extends javax.swing.JDialog {

    public static CVisorUsuario showVisor(OUsuarios obj) {
        CVisorUsuario o = new CVisorUsuario(null, true);
        o.setUsuario(obj);
        o.setVisible(obj != null);
        return o;
    }

    private OUsuarios usuario;

    private final JTextField[] campos;
    private final ModeloTablas modelo_pagos_x_servicio;
    private final ModeloTablas modelo_pagos_x_recargo;
    private final ModeloTablas modelo_pagos_x_otros;

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
    public CVisorUsuario(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        campos = new JTextField[]{nombre, ap, am, calle, numero_casa, tipo_toma, fecha_registro, estado, titular, codigo};
        //
        modelo_pagos_x_servicio = new ModeloTablas(ConstGs.BD_PAGOS_X_SERVICIO);
        modelo_pagos_x_recargo = new ModeloTablas(ConstGs.BD_PAGOS_X_RECARGO);
        modelo_pagos_x_otros = new ModeloTablas(ConstGs.BD_PAGOS_X_OTROS);
        //
        tabla_pxs.setModel(modelo_pagos_x_servicio);
        tabla_pxr.setModel(modelo_pagos_x_recargo);
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
        tab_root.addChangeListener(i -> {
            if (panel_pxs.isVisible()) {
                cargarPagosXServicio();
            } else {
                modelo_pagos_x_servicio.clear();
            }
        });
        tab_root.addChangeListener(i -> {
            if (panel_pxr.isVisible()) {
                cargarPagosXRecargos();
            } else {
                modelo_pagos_x_recargo.clear();
            }
        });

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

        jPanel2 = new javax.swing.JPanel();
        panel_central = new javax.swing.JPanel();
        panel_lateral = new javax.swing.JPanel();
        pl_foto = new javax.swing.JLabel();
        pl_panel_central = new javax.swing.JPanel();
        nombre1 = new javax.swing.JLabel();
        apellidos2 = new javax.swing.JLabel();
        tab_root = new javax.swing.JTabbedPane();
        panel_datos_usuario = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panel_camp_id = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        nombre2 = new javax.swing.JTextField();
        panel_camp_nombre = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ap = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        am = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        calle = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        numero_casa = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tipo_toma = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        fecha_registro = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        estado = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        titular = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        panel_datos_contacto = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        panel_pxs = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_pxs = new javax.swing.JTable();
        panel_pxr = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_pxr = new javax.swing.JTable();
        panel_bottom = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 600));
        jPanel2.setLayout(new java.awt.BorderLayout());

        panel_central.setPreferredSize(new java.awt.Dimension(1000, 570));
        panel_central.setLayout(new java.awt.BorderLayout());

        panel_lateral.setPreferredSize(new java.awt.Dimension(300, 700));
        panel_lateral.setLayout(new java.awt.BorderLayout());

        pl_foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pl_foto.setText("Sin foto");
        pl_foto.setPreferredSize(new java.awt.Dimension(300, 250));
        panel_lateral.add(pl_foto, java.awt.BorderLayout.PAGE_START);

        pl_panel_central.setLayout(new java.awt.GridLayout(8, 0));

        nombre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre1.setText("jLabel2");
        nombre1.setPreferredSize(new java.awt.Dimension(300, 20));
        pl_panel_central.add(nombre1);

        apellidos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        apellidos2.setText("jLabel3");
        apellidos2.setPreferredSize(new java.awt.Dimension(300, 20));
        pl_panel_central.add(apellidos2);

        panel_lateral.add(pl_panel_central, java.awt.BorderLayout.CENTER);

        panel_central.add(panel_lateral, java.awt.BorderLayout.WEST);

        panel_datos_usuario.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel4.setPreferredSize(new java.awt.Dimension(700, 620));
        jPanel4.setLayout(new java.awt.GridLayout(11, 1, 5, 5));

        panel_camp_id.setLayout(new java.awt.BorderLayout());

        jLabel13.setText("Nombre:");
        jLabel13.setPreferredSize(new java.awt.Dimension(150, 30));
        panel_camp_id.add(jLabel13, java.awt.BorderLayout.WEST);

        nombre2.setEditable(false);
        panel_camp_id.add(nombre2, java.awt.BorderLayout.CENTER);

        jPanel4.add(panel_camp_id);

        panel_camp_nombre.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Nombre:");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 30));
        panel_camp_nombre.add(jLabel3, java.awt.BorderLayout.WEST);

        nombre.setEditable(false);
        panel_camp_nombre.add(nombre, java.awt.BorderLayout.CENTER);

        jPanel4.add(panel_camp_nombre);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Apellido Paterno:");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel8.add(jLabel4, java.awt.BorderLayout.WEST);

        ap.setEditable(false);
        jPanel8.add(ap, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Apellido Materno:");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel9.add(jLabel5, java.awt.BorderLayout.WEST);

        am.setEditable(false);
        jPanel9.add(am, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Calle:");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel10.add(jLabel6, java.awt.BorderLayout.WEST);

        calle.setEditable(false);
        jPanel10.add(calle, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel10);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Numero de casa");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel11.add(jLabel7, java.awt.BorderLayout.WEST);

        numero_casa.setEditable(false);
        jPanel11.add(numero_casa, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel11);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("TIpo de toma");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel12.add(jLabel8, java.awt.BorderLayout.WEST);

        tipo_toma.setEditable(false);
        tipo_toma.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel12.add(tipo_toma, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Fecha de registro");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel13.add(jLabel9, java.awt.BorderLayout.WEST);

        fecha_registro.setEditable(false);
        fecha_registro.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel13.add(fecha_registro, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("Estado");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel14.add(jLabel10, java.awt.BorderLayout.WEST);

        estado.setEditable(false);
        estado.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel14.add(estado, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel14);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Titular");
        jLabel11.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel15.add(jLabel11, java.awt.BorderLayout.WEST);

        titular.setEditable(false);
        titular.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel15.add(titular, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel15);

        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("Codigo:");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel16.add(jLabel12, java.awt.BorderLayout.WEST);

        codigo.setEditable(false);
        codigo.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel16.add(codigo, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel16);

        panel_datos_usuario.add(jPanel4, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Datos de usuario", panel_datos_usuario);

        panel_datos_contacto.setLayout(new java.awt.BorderLayout());

        jPanel20.setPreferredSize(new java.awt.Dimension(700, 620));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_datos_contacto.add(jPanel20, java.awt.BorderLayout.CENTER);

        tab_root.addTab("informacion de contacto", panel_datos_contacto);

        panel_pxs.setLayout(new java.awt.BorderLayout());

        tabla_pxs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla_pxs);

        panel_pxs.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Pagos x servicio", panel_pxs);

        panel_pxr.setLayout(new java.awt.BorderLayout());

        tabla_pxr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla_pxr);

        panel_pxr.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Pagos x recargos", panel_pxr);

        panel_central.add(tab_root, java.awt.BorderLayout.CENTER);

        jPanel2.add(panel_central, java.awt.BorderLayout.CENTER);

        panel_bottom.setPreferredSize(new java.awt.Dimension(1000, 35));
        panel_bottom.setLayout(new java.awt.BorderLayout());
        panel_bottom.add(jLabel1, java.awt.BorderLayout.CENTER);

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

        panel_bottom.add(jPanel24, java.awt.BorderLayout.EAST);

        jPanel2.add(panel_bottom, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
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

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    public OUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(OUsuarios usuario) {
        this.usuario = usuario;
        cargar();
    }

    private void cargar() {
        String[] arr = usuario.getInfoSinFK();
        nombre1.setText(usuario.getNombre());
        apellidos2.setText(String.format("%s %s", usuario.getAp(), usuario.getAm()));
        for (int i = 0; i < arr.length - 1; i++) {
            campos[i].setText(arr[i + 1]);
        }
    }

    private void cargarPagosXServicio() {
        Operaciones<OPagosServicio> o = FabricaOpraciones.getPAGOS_X_SERVICIO();
        ArrayList<OPagosServicio> lista = o.getLista("usuario = " + usuario.getId());
        for (OPagosServicio i : lista) {
            modelo_pagos_x_servicio.addRow(i.getInfoSinFK());
        }
    }

    private void cargarPagosXRecargos() {
        Operaciones<OPagosRecargos> o = FabricaOpraciones.getPAGOS_X_RECARGOS();
        ArrayList<OPagosRecargos> lista = o.getLista("usuario = " + usuario.getId());
        for (OPagosRecargos i : lista) {
            modelo_pagos_x_servicio.addRow(i.getInfoSinFK());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField am;
    private javax.swing.JTextField ap;
    private javax.swing.JLabel apellidos2;
    private javax.swing.JTextField calle;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField estado;
    private javax.swing.JTextField fecha_registro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombre;
    private javax.swing.JLabel nombre1;
    private javax.swing.JTextField nombre2;
    private javax.swing.JTextField numero_casa;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel panel_bottom;
    private javax.swing.JPanel panel_camp_id;
    private javax.swing.JPanel panel_camp_nombre;
    private javax.swing.JPanel panel_central;
    private javax.swing.JPanel panel_datos_contacto;
    private javax.swing.JPanel panel_datos_usuario;
    private javax.swing.JPanel panel_lateral;
    private javax.swing.JPanel panel_pxr;
    private javax.swing.JPanel panel_pxs;
    private javax.swing.JLabel pl_foto;
    private javax.swing.JPanel pl_panel_central;
    private javax.swing.JTabbedPane tab_root;
    private javax.swing.JTable tabla_pxr;
    private javax.swing.JTable tabla_pxs;
    private javax.swing.JTextField tipo_toma;
    private javax.swing.JTextField titular;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;
}
