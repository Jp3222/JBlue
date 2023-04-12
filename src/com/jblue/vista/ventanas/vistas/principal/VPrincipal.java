/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jblue.vista.ventanas.vistas.principal;

import com.jblue.modelo.ConstBD;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.logicanegocio.Pagos;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.sistema.Sesion;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.tiempo.Fecha;
import com.jblue.vista.normas.SuperVista;
import com.jutil.jswing.jswingenv.EnvJTextField;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class VPrincipal extends SuperVista {

    private final Fecha fecha;
    //cache
    private final MemoCache<OUsuarios> memoria;
    private final ArrayList<OUsuarios> cache;
    private final ArrayList<OUsuarios> lista_aux;
    //
    private final Pagos pagos_con;
    private OUsuarios usuario_buscado;
    private OTipoTomas tipo_de_toma;

    private double dinero_a_pagar;
    private double total;
    //
    private final DefaultListModel modelo_lista;
    private final DefaultTableModel modelo_tabla;
    //
    private final EnvJTextField env;

    /**
     * Creates new form VPrincipal
     */
    public VPrincipal() {
        pagos_con = new Pagos();
        this.fecha = new Fecha();
        //
        this.memoria = FabricaCache.MC_USUARIOS;
        this.cache = memoria.getLista();
        this.lista_aux = new ArrayList<>(cache.size());
        //
        initComponents();
        this.jtfUsuario.setDisabledTextColor(Color.black);
        this.jtfTipoToma.setDisabledTextColor(Color.black);
        //
        this.modelo_lista = new DefaultListModel();
        this.modelo_tabla = (DefaultTableModel) jTable1.getModel();
        //
        this.jList1.setModel(modelo_lista);
        env = new EnvJTextField(jtfUsuarioBuscado1, "ejem: juan pablo");
        llamable();
        cargarPagosRecientes();
    }

    @Override
    protected final void llamable() {
        estadoFinal();
        estadoInicial();
        addComponentes();
        addEventos();
    }

    @Override
    protected void estadoFinal() {
        super.estadoFinal();
        env.borrarAlClick();
        env.borrarAlEscribir();
        env.borrarAlFoco();
    }

    @Override
    public void estadoInicial() {
        usuario_buscado = null;
        tipo_de_toma = null;
        dinero_a_pagar = 0;
        total = 0;

        if (!lista_aux.isEmpty()) {
            lista_aux.clear();
        }
        if (modelo_lista.getSize() > 0) {
            modelo_lista.removeAllElements();
        }
        jspMesesPagados.setEnabled(false);
        jbtCobrar.setEnabled(false);
        info_usuario.setEnabled(false);

        env.defecto();
        jtfUsuario.setText(null);
        jtfTipoToma.setText(null);
        jlbTotal.setText("0.0");
        jspMesesPagados.setValue(1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jtfUsuarioBuscado1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        info_usuario = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtfTipoToma = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jspMesesPagados = new javax.swing.JSpinner();
        jPanel19 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jlbTotal = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jlbCambio = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jbtCobrar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jbtCancelarCobro = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jbtLimpiar = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1200, 643));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pagos del dia");
        jLabel1.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel3.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Usuario", "Meses"
            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel3);

        jSeparator1.setPreferredSize(new java.awt.Dimension(10, 10));
        add(jSeparator1);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(600, 143));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jtfUsuarioBuscado1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfUsuarioBuscado1KeyReleased(evt);
            }
        });
        jPanel5.add(jtfUsuarioBuscado1, java.awt.BorderLayout.PAGE_START);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.setPreferredSize(new java.awt.Dimension(600, 250));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Datos del usuario");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel8.add(jLabel2, java.awt.BorderLayout.CENTER);

        info_usuario.setText("info del usuario");
        info_usuario.setPreferredSize(new java.awt.Dimension(200, 50));
        info_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                info_usuarioActionPerformed(evt);
            }
        });
        jPanel8.add(info_usuario, java.awt.BorderLayout.LINE_END);

        jPanel6.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Nombre");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel9.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jtfUsuario.setEnabled(false);
        jtfUsuario.setPreferredSize(new java.awt.Dimension(586, 50));
        jPanel9.add(jtfUsuario, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Tipo de toma");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel10.add(jLabel4, java.awt.BorderLayout.LINE_START);

        jtfTipoToma.setEnabled(false);
        jtfTipoToma.setPreferredSize(new java.awt.Dimension(586, 50));
        jPanel10.add(jtfTipoToma, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel10);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Meses a pagados");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel11.add(jLabel5, java.awt.BorderLayout.LINE_START);

        jspMesesPagados.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        jspMesesPagados.setPreferredSize(new java.awt.Dimension(586, 50));
        jspMesesPagados.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspMesesPagadosStateChanged(evt);
            }
        });
        jPanel11.add(jspMesesPagados, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel11);

        jPanel19.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Total:  $");
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel19.add(jLabel14, java.awt.BorderLayout.CENTER);

        jlbTotal.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jlbTotal.setText("0");
        jlbTotal.setPreferredSize(new java.awt.Dimension(444, 50));
        jPanel19.add(jlbTotal, java.awt.BorderLayout.LINE_END);

        jPanel6.add(jPanel19);

        jPanel20.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Cambio:  $");
        jLabel15.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel20.add(jLabel15, java.awt.BorderLayout.CENTER);

        jlbCambio.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jlbCambio.setText("0");
        jlbCambio.setPreferredSize(new java.awt.Dimension(444, 50));
        jPanel20.add(jlbCambio, java.awt.BorderLayout.LINE_END);

        jPanel6.add(jPanel20);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(600, 250));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel15.setPreferredSize(new java.awt.Dimension(586, 50));
        jPanel15.setLayout(new java.awt.BorderLayout());
        jPanel7.add(jPanel15);

        jPanel12.setPreferredSize(new java.awt.Dimension(586, 50));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jPanel14.setLayout(new java.awt.BorderLayout());

        jbtCobrar.setText("Cobrar");
        jbtCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCobrarActionPerformed(evt);
            }
        });
        jPanel14.add(jbtCobrar, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel14);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jbtCancelarCobro.setText("Cancelar");
        jbtCancelarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarCobroActionPerformed(evt);
            }
        });
        jPanel13.add(jbtCancelarCobro, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel13);

        jPanel21.setLayout(new java.awt.BorderLayout());

        jbtLimpiar.setText("limpiar");
        jbtLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLimpiarActionPerformed(evt);
            }
        });
        jPanel21.add(jbtLimpiar, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel21);

        jPanel7.add(jPanel12);

        jPanel16.setPreferredSize(new java.awt.Dimension(586, 50));
        jPanel16.setLayout(new java.awt.BorderLayout());
        jPanel7.add(jPanel16);

        jPanel17.setPreferredSize(new java.awt.Dimension(586, 50));
        jPanel17.setLayout(new java.awt.BorderLayout());
        jPanel7.add(jPanel17);

        jPanel18.setPreferredSize(new java.awt.Dimension(586, 50));
        jPanel18.setLayout(new java.awt.BorderLayout());
        jPanel7.add(jPanel18);

        jPanel4.add(jPanel7, java.awt.BorderLayout.SOUTH);

        add(jPanel4);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfUsuarioBuscado1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfUsuarioBuscado1KeyReleased
        modelo_lista.removeAllElements();
        lista_aux.clear();

        String texto_buscado = limpiar(jtfUsuarioBuscado1.getText());

        for (OUsuarios o : cache) {

            String aux = limpiar(o.getStringR());

            if (!aux.contains(texto_buscado) || !o.isActivo()) {
                continue;
            }

            modelo_lista.addElement(o.getStringR());
            lista_aux.add(o);
        }

    }//GEN-LAST:event_jtfUsuarioBuscado1KeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int index = jList1.getSelectedIndex();
        if (index < 0 || index >= lista_aux.size()) {
            return;
        }

        usuario_buscado = lista_aux.get(index);

        int clicks = evt.getClickCount();

        switch (clicks) {
            case 1:
                jtfUsuarioBuscado1.setText(usuario_buscado.getStringR());
                break;
            case 2:
                tipo_de_toma = EnvUsuario.getTipo_De_Toma(usuario_buscado.getInfoSinFK()[5]);
                dinero_a_pagar = tipo_de_toma.getCosto();
                total = dinero_a_pagar * (Integer) jspMesesPagados.getValue();
                jlbTotal.setText("" + total);
                SwingUtilities.invokeLater(() -> cargarDatosUsuario());
                break;
        }

    }//GEN-LAST:event_jList1MouseClicked

    private void info_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_info_usuarioActionPerformed

        if (usuario_buscado == null) {
            return;
        }

        String[] arr = usuario_buscado.getInfoSinFK();
        String[] cam = ConstBD.BD_USUARIOS;

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            s.append(cam[i]).append(": ").append(arr[i]).append("\n");
        }

        JOptionPane.showMessageDialog(this, s.toString(), "informacion de usuario", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_info_usuarioActionPerformed

    private void jspMesesPagadosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspMesesPagadosStateChanged

        total = dinero_a_pagar * (Integer) jspMesesPagados.getValue();
        jlbTotal.setText("" + total);

    }//GEN-LAST:event_jspMesesPagadosStateChanged

    private void jbtCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCobrarActionPerformed
        if (usuario_buscado == null) {
            return;
        }

        int meses_pagados = Integer.parseInt(String.valueOf(jspMesesPagados.getValue()));

        pagos_con.setUsuario(usuario_buscado);
        pagos_con.setPersonal(Sesion.getInstancia().getUsuario());
        pagos_con.setToma(tipo_de_toma);

        double cambio = pagos_con.hacerPago(meses_pagados, total);
        if (cambio >= 0) {
            jlbCambio.setText(String.valueOf(cambio));
            cargarPagosRecientes();
        }
        estadoInicial();

    }//GEN-LAST:event_jbtCobrarActionPerformed

    private void jbtCancelarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarCobroActionPerformed
        int o = JOptionPane.showConfirmDialog(this,
                "¿Esta seguro de cancelar esta operacion?",
                "Cancelar Operacion",
                JOptionPane.YES_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (o == JOptionPane.YES_OPTION) {
            estadoInicial();
        }
    }//GEN-LAST:event_jbtCancelarCobroActionPerformed

    private void jbtLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimpiarActionPerformed
        jlbCambio.setText(null);
    }//GEN-LAST:event_jbtLimpiarActionPerformed

    public void cargarDatosUsuario() {
        //datos del usuario
        jtfUsuarioBuscado1.setText(null);
        jtfUsuario.setText(usuario_buscado.getStringR());
        jtfTipoToma.setText(usuario_buscado.getInfoSinFK()[5]);
        //
        //
        jlbTotal.setText("" + dinero_a_pagar);
        jspMesesPagados.setEnabled(true);
        jbtCobrar.setEnabled(true);
        info_usuario.setEnabled(true);
        //
        jList1.clearSelection();
        modelo_lista.removeAllElements();
        lista_aux.clear();

    }

    String limpiar(String txt) {
        return txt.trim().replace(" ", "").replace("_", "").toUpperCase();
    }

    ArrayList<OPagosServicio> pagos_recientes = new ArrayList<>();

    public void cargarPagosRecientes() {

        while (modelo_tabla.getRowCount() > 0) {
            modelo_tabla.removeRow(0);
        }
        
        Operaciones<OPagosServicio> op = FabricaOpraciones.PAGOS_X_SERVICIO;
        String[] fh = fecha.get();
        StringBuilder s = new StringBuilder();
        s.append("dia = '").append(fh[0]).append("' and ");
        s.append("mes = '").append(fh[1]).append("' and ");
        s.append("año = '").append(fh[2]).append("'");
        ArrayList<OPagosServicio> lista = op.getLista(s.toString());
        int i = 0;

        for (OPagosServicio oPagosServicio : lista) {
            String[] info = {
                String.valueOf(i),
                oPagosServicio.getConjuntoSinFK()[2],
                oPagosServicio.getMesPagado()
            };
            
            modelo_tabla.addRow(info);
            i++;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton info_usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtCancelarCobro;
    private javax.swing.JButton jbtCobrar;
    private javax.swing.JButton jbtLimpiar;
    private javax.swing.JLabel jlbCambio;
    private javax.swing.JLabel jlbTotal;
    private javax.swing.JSpinner jspMesesPagados;
    private javax.swing.JTextField jtfTipoToma;
    private javax.swing.JTextField jtfUsuario;
    private javax.swing.JTextField jtfUsuarioBuscado1;
    // End of variables declaration//GEN-END:variables

}
