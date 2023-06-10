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
import java.awt.Component;
import java.awt.DefaultFocusTraversalPolicy;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class VCobros extends SuperVista {

    private final Fecha fecha;
    //cache
    private final MemoCache<OUsuarios> memoria_usuarios;
    private final ArrayList<OUsuarios> cache_usuarios;
    private final ArrayList<OUsuarios> lista_aux;
    //
    private final MemoCache<OPagosServicio> memoria_pagos;
    private final ArrayList<OPagosServicio> cache_pagos;
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

    private int meses_pagados;

    DefaultTableModel modelo_pagos;

    /**
     * Creates new form VPrincipal
     */
    public VCobros() {
        pagos_con = new Pagos();
        this.fecha = new Fecha();
        //
        this.memoria_usuarios = FabricaCache.MC_USUARIOS;
        this.cache_usuarios = memoria_usuarios.getLista();
        this.lista_aux = new ArrayList<>(cache_usuarios.size());
        //
        this.memoria_pagos = new MemoCache(FabricaOpraciones.PAGOS_X_SERVICIO);
        this.memoria_pagos.cargar();
        this.cache_pagos = memoria_pagos.getLista();
        //
        initComponents();
        this.modelo_lista = new DefaultListModel();
        this.modelo_tabla = (DefaultTableModel) jtPagosRecientes.getModel();
        //
        this.jList1.setModel(modelo_lista);
        env = new EnvJTextField(jtfUsuarioBuscado, "ejem: juan pablo");
        modelo_pagos = (DefaultTableModel) jtPagos.getModel();
        llamable();
    }

    @Override
    protected final void llamable() {
        contruirComponentes();
        componentesEstadoFinal();
        componentesEstadoInicial();
        manejoEventos();
    }

    @Override
    protected void componentesEstadoFinal() {
        super.componentesEstadoFinal();
        env.borrarAlClick();
        env.borrarAlEscribir();
        env.borrarAlFoco();
    }

    @Override
    public void componentesEstadoInicial() {
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

        usuario.setText(null);
        tipo_toma.setText(null);
        jlbTotal.setText("0.0");
        jspMesesPagados.setValue(1);
        fecha();
    }

    public void fecha() {
        Fecha fh = new Fecha();
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) filtro_dia.getEditor();
        JFormattedTextField textField = editor.getTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char s = textField.getText().charAt(textField.getText().length() - 1);
                if (Character.isDigit(s)) {
                    e.consume();
                }
            }

        });

    }

    @Override
    protected void manejoEventos() {
        jtapRoot.addChangeListener(e -> {
            if (jpHistorial.isVisible()) {
                cargarPagos();
                return;
            }
            vaciarPagos();
        });
        jtapRoot.addChangeListener(e -> {
            if (jpCobros.isVisible()) {
                cargarPagosRecientes();
                return;
            }
            vaciarPagosRecientes();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtapRoot = new javax.swing.JTabbedPane();
        jpCobros = new javax.swing.JPanel();
        panel_izq = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPagosRecientes = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        panel_der = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        info_usuario = new javax.swing.JButton();
        jlb_meses_pagados = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        tipo_usuario = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tipo_toma = new javax.swing.JLabel();
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
        jPanel33 = new javax.swing.JPanel();
        jtfUsuarioBuscado = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jpHistorial = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        filtro_dia = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        buscador = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtPagos = new javax.swing.JTable();

        setName("cobros"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jtapRoot.setPreferredSize(new java.awt.Dimension(1200, 643));

        jpCobros.setName("vista de cobros"); // NOI18N
        jpCobros.setLayout(new java.awt.BorderLayout());

        panel_izq.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagos del día"));
        panel_izq.setMinimumSize(new java.awt.Dimension(500, 700));
        panel_izq.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_izq.setLayout(new java.awt.BorderLayout());

        jtPagosRecientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Usuario", "Meses"
            }
        ));
        jtPagosRecientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtPagosRecientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtPagosRecientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtPagosRecientes);

        panel_izq.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.LINE_AXIS));

        jPanel17.setLayout(new java.awt.BorderLayout());

        jButton4.setText("ver total acumulado");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPreferredSize(new java.awt.Dimension(400, 50));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton4, java.awt.BorderLayout.CENTER);

        jPanel16.add(jPanel17);

        panel_izq.add(jPanel16, java.awt.BorderLayout.SOUTH);

        jpCobros.add(panel_izq, java.awt.BorderLayout.WEST);

        panel_der.setPreferredSize(new java.awt.Dimension(700, 700));
        panel_der.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del usuario"));
        jPanel6.setPreferredSize(new java.awt.Dimension(600, 500));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel8.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel2.setText("Meses Pagados:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel8.add(jLabel2, java.awt.BorderLayout.WEST);

        info_usuario.setText("info del usuario");
        info_usuario.setPreferredSize(new java.awt.Dimension(200, 200));
        info_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                info_usuarioActionPerformed(evt);
            }
        });
        jPanel8.add(info_usuario, java.awt.BorderLayout.LINE_END);

        jlb_meses_pagados.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jlb_meses_pagados.setText("0");
        jPanel8.add(jlb_meses_pagados, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Nombre:");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel9.add(jLabel3, java.awt.BorderLayout.WEST);

        usuario.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jPanel9.add(usuario, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel9);

        jPanel5.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        tipo_usuario.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jPanel5.add(tipo_usuario, java.awt.BorderLayout.CENTER);

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel12.setText("Tipo de usuario:");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel5.add(jLabel12, java.awt.BorderLayout.WEST);

        jPanel6.add(jPanel5);

        jPanel10.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Tipo de toma:");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel10.add(jLabel4, java.awt.BorderLayout.WEST);

        tipo_toma.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jPanel10.add(tipo_toma, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel10);

        jPanel11.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Meses a pagar:");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel11.add(jLabel5, java.awt.BorderLayout.WEST);

        jspMesesPagados.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        jspMesesPagados.setPreferredSize(new java.awt.Dimension(586, 50));
        jspMesesPagados.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspMesesPagadosStateChanged(evt);
            }
        });
        jPanel11.add(jspMesesPagados, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel11);

        jPanel19.setPreferredSize(new java.awt.Dimension(700, 50));
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

        jPanel20.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Cambio:  $");
        jLabel15.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel20.add(jLabel15, java.awt.BorderLayout.CENTER);

        jlbCambio.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jlbCambio.setText("0");
        jlbCambio.setPreferredSize(new java.awt.Dimension(444, 50));
        jPanel20.add(jlbCambio, java.awt.BorderLayout.LINE_END);

        jPanel6.add(jPanel20);

        jPanel7.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel6.add(jPanel7);

        jPanel15.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel6.add(jPanel15);

        jPanel12.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jPanel14.setLayout(new java.awt.BorderLayout());

        jbtCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img5.png"))); // NOI18N
        jbtCobrar.setText("Cobrar");
        jbtCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCobrarActionPerformed(evt);
            }
        });
        jPanel14.add(jbtCobrar, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel14);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jbtCancelarCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
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

        jPanel6.add(jPanel12);

        panel_der.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder("Cobros"));
        jPanel33.setPreferredSize(new java.awt.Dimension(700, 200));
        jPanel33.setLayout(new java.awt.BorderLayout());

        jtfUsuarioBuscado.setPreferredSize(new java.awt.Dimension(700, 35));
        jtfUsuarioBuscado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfUsuarioBuscadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfUsuarioBuscadoKeyReleased(evt);
            }
        });
        jPanel33.add(jtfUsuarioBuscado, java.awt.BorderLayout.NORTH);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 165));

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel33.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        panel_der.add(jPanel33, java.awt.BorderLayout.NORTH);

        jpCobros.add(panel_der, java.awt.BorderLayout.CENTER);

        jtapRoot.addTab("Cobros", jpCobros);

        jpHistorial.setLayout(new java.awt.BorderLayout());

        jPanel23.setPreferredSize(new java.awt.Dimension(1200, 150));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 30));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jCheckBox1, java.awt.BorderLayout.WEST);

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel8.setText("Filtros");
        jPanel1.add(jLabel8, java.awt.BorderLayout.CENTER);

        jButton5.setText("Quitar Filtros");
        jButton5.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel1.add(jButton5, java.awt.BorderLayout.LINE_END);

        jPanel23.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel30.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jPanel29.setLayout(new javax.swing.BoxLayout(jPanel29, javax.swing.BoxLayout.LINE_AXIS));

        jLabel9.setText("Dia");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel29.add(jLabel9);
        jPanel29.add(filtro_dia);

        jLabel10.setText("Mes");
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel29.add(jLabel10);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(jComboBox1);

        jLabel11.setText("Año");
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel29.add(jLabel11);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel29.add(jComboBox2);

        jPanel30.add(jPanel29, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel30);

        jPanel31.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel2.add(jPanel31);

        jPanel32.setPreferredSize(new java.awt.Dimension(1200, 50));
        jPanel2.add(jPanel32);

        jPanel23.add(jPanel2, java.awt.BorderLayout.CENTER);

        jpHistorial.add(jPanel23, java.awt.BorderLayout.NORTH);

        jPanel22.setPreferredSize(new java.awt.Dimension(1200, 500));
        jPanel22.setLayout(new java.awt.BorderLayout());

        jPanel24.setPreferredSize(new java.awt.Dimension(1200, 35));
        jPanel24.setLayout(new java.awt.BorderLayout());

        jPanel25.setPreferredSize(new java.awt.Dimension(400, 35));
        jPanel25.setLayout(new java.awt.BorderLayout());

        jPanel28.setPreferredSize(new java.awt.Dimension(100, 35));
        jPanel28.setLayout(new java.awt.BorderLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton3, java.awt.BorderLayout.CENTER);

        jPanel25.add(jPanel28, java.awt.BorderLayout.LINE_START);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Buscar: ");
        jPanel25.add(jLabel7, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel25, java.awt.BorderLayout.LINE_START);

        jPanel26.setPreferredSize(new java.awt.Dimension(400, 35));
        jPanel26.setLayout(new java.awt.BorderLayout());

        jPanel27.setPreferredSize(new java.awt.Dimension(200, 35));
        jPanel27.setLayout(new java.awt.BorderLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(100, 35));
        jPanel27.add(jButton1, java.awt.BorderLayout.EAST);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(100, 35));
        jPanel27.add(jButton2, java.awt.BorderLayout.WEST);

        jPanel26.add(jPanel27, java.awt.BorderLayout.LINE_END);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("1 - 1000");
        jPanel26.add(jLabel6, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel26, java.awt.BorderLayout.LINE_END);

        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorKeyReleased(evt);
            }
        });
        jPanel24.add(buscador, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel24, java.awt.BorderLayout.PAGE_START);

        jtPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Personal", "Usuario", "Mes Pagado", "Monto", "Dia", "Mes", "Año"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPagos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtPagos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtPagos.setShowGrid(true);
        jtPagos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtPagos);

        jPanel22.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jpHistorial.add(jPanel22, java.awt.BorderLayout.CENTER);

        jtapRoot.addTab("Historial de cobros", jpHistorial);

        add(jtapRoot, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfUsuarioBuscadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfUsuarioBuscadoKeyReleased
        modelo_lista.removeAllElements();
        lista_aux.clear();
        String texto_buscado = limpiar(jtfUsuarioBuscado.getText());
        for (OUsuarios o : cache_usuarios) {
            String aux = limpiar(o.getStringR());
            if (!aux.contains(texto_buscado) || !o.isActivo()) {
                continue;
            }
            modelo_lista.addElement(o.getStringR());
            lista_aux.add(o);
        }
    }//GEN-LAST:event_jtfUsuarioBuscadoKeyReleased

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        int clicks = evt.getClickCount();
        if (clicks < 2) {
            return;
        }

        int index = jList1.getSelectedIndex();
        if (index < 0 || index >= lista_aux.size()) {
            return;
        }
        agregarUsuario(index);
    }//GEN-LAST:event_jList1MouseClicked

    public void agregarUsuario(int index) {
        usuario_buscado = lista_aux.get(index);

        tipo_de_toma = EnvUsuario.getTipoDeTomaEnCache(usuario_buscado.getToma());
        meses_pagados = EnvUsuario.getMesesPagados(String.valueOf(fecha.getNewFechaActual().getYear()), usuario_buscado.getId());

        jlb_meses_pagados.setText(String.valueOf(meses_pagados));
        dinero_a_pagar = tipo_de_toma.getCosto();
        SpinnerNumberModel model = (SpinnerNumberModel) jspMesesPagados.getModel();
        System.out.println(model.getNumber().intValue());
        total = dinero_a_pagar * (Integer) jspMesesPagados.getValue();

        jlbTotal.setText("" + total);

        SwingUtilities.invokeLater(() -> cargarDatosUsuario());

    }

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
        SpinnerNumberModel model = (SpinnerNumberModel) jspMesesPagados.getModel();
        total = dinero_a_pagar * model.getNumber().intValue();
        jlbTotal.setText(String.valueOf(total));

    }//GEN-LAST:event_jspMesesPagadosStateChanged

    private void jbtCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCobrarActionPerformed
        if (usuario_buscado == null) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun usuario");
            return;
        }

        int meses_a_pagar = Integer.parseInt(String.valueOf(jspMesesPagados.getValue()));

        pagos_con.setUsuario(usuario_buscado);
        pagos_con.setPersonal(Sesion.getInstancia().getUsuario());
        pagos_con.setToma(tipo_de_toma);

        double cambio = pagos_con.hacerPago(meses_a_pagar, total);

        if (cambio != -1) {
            jlbCambio.setText(String.valueOf(cambio));
        }
        cargarPagosRecientes();
        componentesEstadoInicial();

        memoria_pagos.actualizar();
    }//GEN-LAST:event_jbtCobrarActionPerformed

    private void jbtCancelarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarCobroActionPerformed
        int o = JOptionPane.showConfirmDialog(this,
                "¿Esta seguro de cancelar esta operacion?",
                "Cancelar Operacion",
                JOptionPane.YES_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (o == JOptionPane.YES_OPTION) {
            componentesEstadoInicial();
        }
    }//GEN-LAST:event_jbtCancelarCobroActionPerformed

    private void jbtLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimpiarActionPerformed
        jlbCambio.setText("0.0");
    }//GEN-LAST:event_jbtLimpiarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        actualizarPagos();
    }//GEN-LAST:event_jButton3ActionPerformed


    private void buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyReleased
        SwingUtilities.invokeLater(() -> {
            String text = limpiar(buscador.getText());
            buscar(text);
        });
    }//GEN-LAST:event_buscadorKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            Operaciones<OPagosServicio> op = FabricaOpraciones.PAGOS_X_SERVICIO;
            String[] fh = fecha.get();

            StringBuilder s = new StringBuilder();
            s.append("dia = '").append(fh[0]).append("' and ");
            s.append("mes = '").append(fh[1]).append("' and ");
            s.append("año = '").append(fh[2]).append("'");

            ResultSet select = op.getCONEXION().select(op.getTABLA(), "monto", s.toString());
            double _total = 0;
            while (select.next()) {
                _total += select.getDouble(1);

            }
            JOptionPane.showMessageDialog(this, "Total Acumulado: " + _total);
        } catch (SQLException ex) {
            Logger.getLogger(VCobros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jtfUsuarioBuscadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfUsuarioBuscadoKeyPressed

        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (modelo_lista.getSize() > 2) {
                return;
            }
            agregarUsuario(0);
        }

    }//GEN-LAST:event_jtfUsuarioBuscadoKeyPressed

    void buscar(String txt) {
        vaciarPagos();
        lista_aux.clear();
        String aux;
        for (OPagosServicio o : cache_pagos) {
            aux = limpiar(o.getStringR());
            if (!aux.contains(txt)) {
                continue;
            }
            modelo_pagos.addRow(o.getInfoSinFK());
        }
    }

    public void cargarDatosUsuario() {
        //datos del usuario
        jtfUsuarioBuscado.setText(null);
        usuario.setText(usuario_buscado.getStringR());
        String tipo = usuario_buscado.isTitular() ? "Titular" : "Consumidor";
        tipo_usuario.setText(tipo);
        tipo_toma.setText(usuario_buscado.getInfoSinFK()[5]);
        //
        //
        jlbTotal.setText("" + dinero_a_pagar);
        if (meses_pagados < 12 && fecha.getNewFechaActual().getMonthValue() < 12) {
            jspMesesPagados.setEnabled(true);
            jbtCobrar.setEnabled(true);

        }
        info_usuario.setEnabled(true);
        //
        jList1.clearSelection();
        modelo_lista.removeAllElements();
        lista_aux.clear();

    }

    String limpiar(String txt) {
        return txt.trim().replace(" ", "").replace("_", "").toLowerCase();
    }

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
                oPagosServicio.getInfoSinFK()[2],
                oPagosServicio.getMesPagado()
            };
            modelo_tabla.addRow(info);
            i++;
        }
    }

    public void vaciarPagosRecientes() {

        while (modelo_tabla.getRowCount() > 0) {
            modelo_tabla.removeRow(0);
            modelo_tabla.fireTableRowsDeleted(0, 0);
        }
    }

    void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(VCobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarPagos() {
        for (OPagosServicio o : cache_pagos) {
            modelo_pagos.addRow(o.getInfoSinFK());
        }
    }

    public void vaciarPagos() {
        if (modelo_pagos.getRowCount() <= 0) {
            return;
        }

        while (modelo_pagos.getRowCount() > 0) {
            modelo_pagos.removeRow(0);
        }
    }

    public void actualizarPagos() {
        vaciarPagos();
        cargarPagos();
    }

    public JPanel getJpCobros() {
        return jpCobros;
    }

    public JPanel getJpHistorial() {
        return jpHistorial;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscador;
    private javax.swing.JSpinner filtro_dia;
    private javax.swing.JButton info_usuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtCancelarCobro;
    private javax.swing.JButton jbtCobrar;
    private javax.swing.JButton jbtLimpiar;
    private javax.swing.JLabel jlbCambio;
    private javax.swing.JLabel jlbTotal;
    private javax.swing.JLabel jlb_meses_pagados;
    private javax.swing.JPanel jpCobros;
    private javax.swing.JPanel jpHistorial;
    private javax.swing.JSpinner jspMesesPagados;
    private javax.swing.JTable jtPagos;
    private javax.swing.JTable jtPagosRecientes;
    private javax.swing.JTabbedPane jtapRoot;
    private javax.swing.JTextField jtfUsuarioBuscado;
    private javax.swing.JPanel panel_der;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JLabel tipo_toma;
    private javax.swing.JLabel tipo_usuario;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (!aFlag) {
            jtapRoot.setSelectedIndex(0);
            componentesEstadoInicial();
        }
    }

    @Override
    public Rectangle getBounds() {
        if (super.getBounds().getWidth() < 800 && panel_der.getBounds().getWidth() < 300) {
            if (panel_izq.isVisible()) {
                ocultarComponente(panel_izq, false);
            }
        } else if (super.getBounds().getWidth() > 800) {
            ocultarComponente(panel_izq, true);
        }
        return super.getBounds();
    }

    public void ocultarComponente(JComponent componente, boolean estado) {
        componente.setVisible(estado);
    }

}
