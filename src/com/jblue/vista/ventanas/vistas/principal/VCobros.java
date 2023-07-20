/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jblue.vista.ventanas.vistas.principal;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.logicanegocio.Pagos;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.sistema.Sesion;
import com.jblue.util.Filtros;
import com.jblue.util.Func;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.tiempo.Fecha;
import com.jblue.vista.normas.SuperVista;
import com.jblue.vista.ventanas.componentes.CVisorUsuario;
import com.jutil.jswing.jswingenv.EnvJTextField;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class VCobros extends SuperVista {

    //componentes
    private final CVisorUsuario visor;
    //Envoltorio de caches
    private final MemoCache<OUsuarios> memoria_usuarios;
    private final MemoCache<OPagosServicio> memoria_pagos;
    //ArrayList cache
    private final ArrayList<OUsuarios> cache_usuarios;
    private final ArrayList<OPagosServicio> cache_pagos;
    //arraylist auxiliar
    private final ArrayList<OUsuarios> lista_aux;
    //
    private final Pagos pagos_con;
    private OUsuarios usuario_buscado;
    private OTipoTomas tipo_de_toma;
    //
    private double dinero_a_pagar;
    private double total;
    private int meses_pagados;
    //
    private final DefaultListModel modelo_lista;
    private final DefaultTableModel modelo_pagos_recientes;
    private final DefaultTableModel modelo_pagos;
    //
    private final EnvJTextField env;
    private final Fecha fecha;

    /**
     * Creates new form VPrincipal
     */
    public VCobros() {
        pagos_con = new Pagos();
        this.fecha = new Fecha();
        visor = new CVisorUsuario(null, true);
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
        this.modelo_pagos_recientes = (DefaultTableModel) jtPagosRecientes.getModel();
        //
        this.lista_usuarios.setModel(modelo_lista);
        env = new EnvJTextField(buscador_usuarios, "ejem: juan pablo");
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
        //
        jspMesesPagados.setEnabled(false);
        jbtCobrar.setEnabled(false);
        info_usuario.setEnabled(false);
        //
        env.defecto();
        //
        tipo_usuario.setText(null);
        usuario.setText(null);
        tipo_toma.setText(null);
        jlb_meses_pagados.setText(null);
        jlbTotal.setText("0.0");
        //
        jspMesesPagados.setValue(1);
    }

    @Override
    protected void manejoEventos() {
        tab_root.addChangeListener(e -> {
            if (panel_consultas.isVisible()) {
                cargarPagos();
                return;
            }
            vaciarPagos();
        });
        tab_root.addChangeListener(e -> {
            if (panel_registro.isVisible()) {
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

        tab_root = new javax.swing.JTabbedPane();
        panel_registro = new javax.swing.JPanel();
        panel_izq = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtPagosRecientes = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        panel_der = new javax.swing.JPanel();
        panel_busqueda = new javax.swing.JPanel();
        buscador_usuarios = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_usuarios = new javax.swing.JList<>();
        panel_datos = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        tipo_usuario = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        info_usuario = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tipo_toma = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jlb_meses_pagados = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jspMesesPagados = new javax.swing.JSpinner();
        jPanel19 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jlbTotal = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jlbCambio = new javax.swing.JLabel();
        panel_botones = new javax.swing.JPanel();
        jbtCobrar = new javax.swing.JButton();
        jbtCancelarCobro = new javax.swing.JButton();
        jbtLimpiar = new javax.swing.JButton();
        panel_consultas = new javax.swing.JPanel();
        panel_filtros = new javax.swing.JPanel();
        pf_bar_super = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        pf_filtros = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel18 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        panel_tabla = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        recargar = new javax.swing.JButton();
        buscador = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtPagos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();

        setName("cobros"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        tab_root.setPreferredSize(new java.awt.Dimension(1200, 643));

        panel_registro.setName("vista de cobros"); // NOI18N
        panel_registro.setLayout(new java.awt.BorderLayout());

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

        panel_registro.add(panel_izq, java.awt.BorderLayout.WEST);

        panel_der.setBorder(javax.swing.BorderFactory.createTitledBorder("Cobros"));
        panel_der.setPreferredSize(new java.awt.Dimension(700, 700));
        panel_der.setLayout(new java.awt.BorderLayout());

        panel_busqueda.setBorder(null);
        panel_busqueda.setMinimumSize(new java.awt.Dimension(500, 200));
        panel_busqueda.setPreferredSize(new java.awt.Dimension(700, 200));
        panel_busqueda.setLayout(new java.awt.BorderLayout());

        buscador_usuarios.setPreferredSize(new java.awt.Dimension(700, 35));
        buscador_usuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscador_usuariosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscador_usuariosKeyReleased(evt);
            }
        });
        panel_busqueda.add(buscador_usuarios, java.awt.BorderLayout.NORTH);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(700, 100));
        jScrollPane2.setRowHeaderView(null);

        lista_usuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lista_usuarios.setPreferredSize(new java.awt.Dimension(500, 100));
        lista_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_usuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lista_usuarios);

        panel_busqueda.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        panel_der.add(panel_busqueda, java.awt.BorderLayout.NORTH);

        panel_datos.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del usuario"));
        panel_datos.setPreferredSize(new java.awt.Dimension(700, 500));
        panel_datos.setLayout(new java.awt.GridLayout(8, 1, 0, 10));

        jPanel5.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        tipo_usuario.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        tipo_usuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel5.add(tipo_usuario, java.awt.BorderLayout.CENTER);

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel12.setText("Tipo de usuario:");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel5.add(jLabel12, java.awt.BorderLayout.WEST);

        info_usuario.setText("info del usuario");
        info_usuario.setPreferredSize(new java.awt.Dimension(150, 200));
        info_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                info_usuarioActionPerformed(evt);
            }
        });
        jPanel5.add(info_usuario, java.awt.BorderLayout.LINE_END);

        panel_datos.add(jPanel5);

        jPanel9.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Nombre:");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel9.add(jLabel3, java.awt.BorderLayout.WEST);

        usuario.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        usuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel9.add(usuario, java.awt.BorderLayout.CENTER);

        panel_datos.add(jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Tipo de toma:");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel10.add(jLabel4, java.awt.BorderLayout.WEST);

        tipo_toma.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        tipo_toma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel10.add(tipo_toma, java.awt.BorderLayout.CENTER);

        panel_datos.add(jPanel10);

        jPanel8.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel2.setText("Meses Pagados:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel8.add(jLabel2, java.awt.BorderLayout.WEST);

        jlb_meses_pagados.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jlb_meses_pagados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlb_meses_pagados.setText("0");
        jPanel8.add(jlb_meses_pagados, java.awt.BorderLayout.CENTER);

        panel_datos.add(jPanel8);

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

        panel_datos.add(jPanel11);

        jPanel19.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Total:  $");
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel19.add(jLabel14, java.awt.BorderLayout.CENTER);

        jlbTotal.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jlbTotal.setText("0.0");
        jlbTotal.setPreferredSize(new java.awt.Dimension(444, 50));
        jPanel19.add(jlbTotal, java.awt.BorderLayout.LINE_END);

        panel_datos.add(jPanel19);

        jPanel20.setPreferredSize(new java.awt.Dimension(700, 50));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Cambio:  $");
        jLabel15.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel20.add(jLabel15, java.awt.BorderLayout.CENTER);

        jlbCambio.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jlbCambio.setText("0.0");
        jlbCambio.setToolTipText("");
        jlbCambio.setPreferredSize(new java.awt.Dimension(444, 50));
        jPanel20.add(jlbCambio, java.awt.BorderLayout.LINE_END);

        panel_datos.add(jPanel20);

        panel_der.add(panel_datos, java.awt.BorderLayout.CENTER);

        panel_botones.setLayout(new java.awt.GridLayout(1, 3));

        jbtCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img5.png"))); // NOI18N
        jbtCobrar.setText("Cobrar");
        jbtCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCobrarActionPerformed(evt);
            }
        });
        panel_botones.add(jbtCobrar);

        jbtCancelarCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        jbtCancelarCobro.setText("Cancelar");
        jbtCancelarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarCobroActionPerformed(evt);
            }
        });
        panel_botones.add(jbtCancelarCobro);

        jbtLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/limpiar.png"))); // NOI18N
        jbtLimpiar.setText("Limpiar campos");
        jbtLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtLimpiarActionPerformed(evt);
            }
        });
        panel_botones.add(jbtLimpiar);

        panel_der.add(panel_botones, java.awt.BorderLayout.SOUTH);

        panel_registro.add(panel_der, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Cobros", panel_registro);

        panel_consultas.setLayout(new java.awt.BorderLayout());

        panel_filtros.setPreferredSize(new java.awt.Dimension(1200, 150));
        panel_filtros.setLayout(new java.awt.BorderLayout());

        pf_bar_super.setPreferredSize(new java.awt.Dimension(1200, 30));
        pf_bar_super.setLayout(new java.awt.BorderLayout());

        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pf_bar_super.add(jCheckBox1, java.awt.BorderLayout.WEST);

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel8.setText("Filtros");
        pf_bar_super.add(jLabel8, java.awt.BorderLayout.CENTER);

        jButton5.setText("Quitar Filtros");
        jButton5.setPreferredSize(new java.awt.Dimension(200, 30));
        pf_bar_super.add(jButton5, java.awt.BorderLayout.LINE_END);

        panel_filtros.add(pf_bar_super, java.awt.BorderLayout.PAGE_START);

        pf_filtros.setLayout(new java.awt.GridLayout(1, 2, 0, 10));

        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Dia:");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel3.add(jLabel1, java.awt.BorderLayout.WEST);
        jPanel3.add(jSpinner1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Mes: ");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel4.add(jLabel6, java.awt.BorderLayout.WEST);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel4.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Año: ");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel18.add(jLabel7, java.awt.BorderLayout.WEST);
        jPanel18.add(jSpinner3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel18);

        pf_filtros.add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(3, 1));
        pf_filtros.add(jPanel2);

        panel_filtros.add(pf_filtros, java.awt.BorderLayout.CENTER);

        panel_consultas.add(panel_filtros, java.awt.BorderLayout.NORTH);

        panel_tabla.setPreferredSize(new java.awt.Dimension(1200, 500));
        panel_tabla.setLayout(new java.awt.BorderLayout());

        jPanel24.setPreferredSize(new java.awt.Dimension(1200, 35));
        jPanel24.setLayout(new java.awt.BorderLayout());

        recargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        recargar.setPreferredSize(new java.awt.Dimension(100, 35));
        recargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarActionPerformed(evt);
            }
        });
        jPanel24.add(recargar, java.awt.BorderLayout.WEST);

        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorKeyReleased(evt);
            }
        });
        jPanel24.add(buscador, java.awt.BorderLayout.CENTER);

        jPanel26.setPreferredSize(new java.awt.Dimension(200, 35));
        jPanel26.setLayout(new java.awt.GridLayout(1, 0));

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        anterior.setPreferredSize(new java.awt.Dimension(100, 35));
        jPanel26.add(anterior);

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        siguiente.setPreferredSize(new java.awt.Dimension(100, 35));
        jPanel26.add(siguiente);

        jPanel24.add(jPanel26, java.awt.BorderLayout.LINE_END);

        panel_tabla.add(jPanel24, java.awt.BorderLayout.NORTH);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(1200, 402));

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

        panel_tabla.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        panel_consultas.add(panel_tabla, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Historial de cobros", panel_consultas);
        tab_root.addTab("tab3", jPanel6);
        tab_root.addTab("tab4", jPanel7);

        add(tab_root, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buscador_usuariosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador_usuariosKeyReleased
        buscador(buscador_usuarios.getText());
    }//GEN-LAST:event_buscador_usuariosKeyReleased

    private void buscador(String txt) {
        modelo_lista.removeAllElements();
        lista_aux.clear();
        if (Filtros.isNullOrBlank(txt)) {
            return;
        }

        txt = Filtros.limpiar(txt);

        for (OUsuarios o : cache_usuarios) {
            if (!EnvUsuario.filtros(txt, o)) {
                continue;
            }
            modelo_lista.addElement(o.getId() + " - " + o.getStringR());
            lista_aux.add(o);
        }
    }

    private void lista_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_usuariosMouseClicked
        int clicks = evt.getClickCount();
        if (clicks < 2) {
            return;
        }

        int index = lista_usuarios.getSelectedIndex();

        agregarUsuario(index);
    }//GEN-LAST:event_lista_usuariosMouseClicked

    public void agregarUsuario(int index) {
        if (index < 0 || index >= modelo_lista.getSize()) {
            return;
        }
        usuario_buscado = lista_aux.get(index);
        tipo_de_toma = EnvUsuario.getTipoDeTomaEnCache(usuario_buscado.getToma());
        meses_pagados = EnvUsuario.getMesesPagados(String.valueOf(fecha.getNewFechaActual().getYear()), usuario_buscado.getId());

        jlb_meses_pagados.setText(String.valueOf(meses_pagados));
        dinero_a_pagar = tipo_de_toma.getCosto();
        SpinnerNumberModel model = (SpinnerNumberModel) jspMesesPagados.getModel();

        total = dinero_a_pagar * (Integer) jspMesesPagados.getValue();
        jlbTotal.setText(String.valueOf(total));
        SwingUtilities.invokeLater(() -> cargarDatosUsuario());

    }

    private void info_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_info_usuarioActionPerformed

        if (usuario_buscado == null) {
            return;
        }
//        String[] arr = usuario_buscado.getInfoSinFK();
//        String[] cam = ConstBD.BD_USUARIOS;
//        StringBuilder s = new StringBuilder();
//
//        for (int i = 0; i < arr.length; i++) {
//            s.append(cam[i]).append(": ").append(arr[i]).append("\n");
//        }
//        JOptionPane.showMessageDialog(this, s.toString(), "informacion de usuario", JOptionPane.INFORMATION_MESSAGE);
        visor.setUsuario(usuario_buscado);
        visor.setVisible(true);
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

    private void recargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recargarActionPerformed
        actualizarPagos();
    }//GEN-LAST:event_recargarActionPerformed


    private void buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyReleased
        SwingUtilities.invokeLater(() -> {
            String text = Filtros.limpiar(buscador.getText());
            _buscador(text);
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

    private void buscador_usuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador_usuariosKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (modelo_lista.getSize() > 2) {
                return;
            }
            agregarUsuario(0);
        }
    }//GEN-LAST:event_buscador_usuariosKeyPressed

    private void jbtLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtLimpiarActionPerformed
        jlbCambio.setText("0.0");
    }//GEN-LAST:event_jbtLimpiarActionPerformed

    void _buscador(String txt) {
        vaciarPagos();
        lista_aux.clear();
        String aux;
        for (OPagosServicio o : cache_pagos) {
            aux = Filtros.limpiar(o.getStringR());
            if (!aux.contains(txt)) {
                continue;
            }
            modelo_pagos.addRow(o.getInfoSinFK());
        }
    }

    public void cargarDatosUsuario() {
        //datos del usuario
        buscador_usuarios.setText(null);
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

        } else {

        }
        info_usuario.setEnabled(true);
        //
        lista_usuarios.clearSelection();
        modelo_lista.removeAllElements();
        lista_aux.clear();

    }

    public void cargarPagosRecientes() {
        while (modelo_pagos_recientes.getRowCount() > 0) {
            modelo_pagos_recientes.removeRow(0);
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
            modelo_pagos_recientes.addRow(info);
            i++;
        }
    }

    public void vaciarPagosRecientes() {

        while (modelo_pagos_recientes.getRowCount() > 0) {
            modelo_pagos_recientes.removeRow(0);
            modelo_pagos_recientes.fireTableRowsDeleted(0, 0);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anterior;
    private javax.swing.JTextField buscador;
    private javax.swing.JTextField buscador_usuarios;
    private javax.swing.JButton info_usuario;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JButton jbtCancelarCobro;
    private javax.swing.JButton jbtCobrar;
    private javax.swing.JButton jbtLimpiar;
    private javax.swing.JLabel jlbCambio;
    private javax.swing.JLabel jlbTotal;
    private javax.swing.JLabel jlb_meses_pagados;
    private javax.swing.JSpinner jspMesesPagados;
    private javax.swing.JTable jtPagos;
    private javax.swing.JTable jtPagosRecientes;
    private javax.swing.JList<String> lista_usuarios;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel panel_busqueda;
    private javax.swing.JPanel panel_consultas;
    private javax.swing.JPanel panel_datos;
    private javax.swing.JPanel panel_der;
    private javax.swing.JPanel panel_filtros;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JPanel panel_registro;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JPanel pf_bar_super;
    private javax.swing.JPanel pf_filtros;
    private javax.swing.JButton recargar;
    private javax.swing.JButton siguiente;
    private javax.swing.JTabbedPane tab_root;
    private javax.swing.JLabel tipo_toma;
    private javax.swing.JLabel tipo_usuario;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables

    public JPanel getJpCobros() {
        return panel_registro;
    }

    public JPanel getJpHistorial() {
        return panel_consultas;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (!aFlag) {
            tab_root.setSelectedIndex(0);
            componentesEstadoInicial();
        }
    }

    @Override
    public Rectangle getBounds() {
        red_panel_su = super.getBounds().getWidth() < 800;
        red_panel_der = panel_der.getBounds().getWidth() < 300;
        if (red_panel_su && red_panel_der) {
            if (panel_izq.isVisible()) {
                Func.ocultarComponente(false, panel_izq);
            }
        } else if (!red_panel_su) {
            Func.ocultarComponente(true, panel_izq);
        }

        return super.getBounds();
    }

    private boolean red_panel_su;
    private boolean red_panel_der;
}
