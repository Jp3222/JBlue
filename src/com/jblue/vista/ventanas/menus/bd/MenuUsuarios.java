/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas.menus.bd;

import com.jblue.modelo.ConstGs;
import com.jblue.controlador.CUsuarios;
import com.jblue.mg.ModeloTablas;
import com.jblue.modelo.ConstBD;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.Filtros;
import com.jblue.util.FormatoBD;
import com.jblue.util.Func;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.tiempo.Fecha;
import com.jblue.vista.normas.SuperVentana;
import com.jblue.vista.comp.CBarraEstado;
import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Code128;

/**
 *
 * @author jp
 */
public class MenuUsuarios extends SuperVentana {

    /**
     * Creates new form NewUsuarios
     */
    public MenuUsuarios() {
        _TITULO = 3;
        this.lista_auxiliar = new ArrayList<>(1000);
        this.modelo_lista = new DefaultListModel<>();
        this.lista_de_busqueda = new LinkedHashSet<>(1000);
        this.operaciones = FabricaOpraciones.USUARIOS;
        this.memoria_cache = FabricaCache.MC_USUARIOS;
        this.cache = memoria_cache.getLista();
        this.initComponents();
        this.modelo_tabla = new ModeloTablas(ConstGs.BD_USUARIOS);
        this.llamable();
        this.controlador = new CUsuarios(this);
        this.jtUsuarios.setModel(modelo_tabla);
        this.jlUsuarios.setModel(modelo_lista);
        //
        this.barra_estado = new CBarraEstado();
        barra_estado.setIdMin(memoria_cache.getLimite_min());
        barra_estado.setIdMax(memoria_cache.getLimite_max());
        barra_estado.setResultados(modelo_tabla.getRowCount());
        panel_tabla.add(barra_estado, BorderLayout.SOUTH);
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
        for (int i = 0; i < ConstGs.BD_USUARIOS.length; i++) {
            modelo_tabla.setCellEditable(i, false);
        }
    }
    
  

    @Override
    public void estadoInicial() {
        //tab_root.setSelectedIndex(0);
        usuario_buscado = null;
        buscando = false;
        jtfNombre.requestFocus();
        initPanelDeRegistros();

    }

    @Override
    protected void addComponentes() {
        super.addComponentes();

    }

    void initPanelDeRegistros() {
        jtfBuscadorLista.setText(null);
        jtfNombre.setText(null);
        jtfAp.setText(null);
        jtfAm.setText(null);
        jtfNumeroCasa.setText(null);
        codigo_bar.setText(null);

        _initJCB(jcbTipoToma, mantenerToma.isSelected());
        _initJCB(jcbCalle, mantenerCalle.isSelected());
        _initJCB(jcbEstado, mantenerEstado.isSelected());
        _initJCB(jcbTitular, MantenerTitular.isSelected());
        botonesPrimarios();
    }

    void botonesPrimarios() {
        guardar.setEnabled(true);
        actualizar.setEnabled(false);
        eliminar.setEnabled(false);
    }

    void botonesSecundarios() {
        guardar.setEnabled(false);
        actualizar.setEnabled(true);
        eliminar.setEnabled(true);
    }

    void _initJCB(JComboBox<String> componente, boolean mantener) {
        if (mantener || componente.getItemCount() == 0) {
            return;
        }
        componente.setSelectedIndex(0);
    }

    @Override
    protected void addEventos() {
        Set<KeyStroke> set = new LinkedHashSet<>(10);
        jtfNombre.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, set);
        jtfAm.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, set);
        jtfAp.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, set);

        jchbTitular.addActionListener(e -> {
            if (jchbTitular.isSelected()) {
                jPanel11.setVisible(false);
                return;
            }
            jPanel11.setVisible(true);
        });

        tab_root.addChangeListener((e) -> {
            if (panelConsultas.isVisible()) {
                filtros(activar_filtros.isSelected());
                controlador.cargarFiltros();
                controlador.cargarTabla();
                return;
            }
            controlador.vaciarFiltros();
            controlador.vaciarTabla();
        });
        filtro_calle.addItemListener((e) -> actualizarFiltros());
        filtro_toma.addItemListener((e) -> actualizarFiltros());
    }

    private void _buscadorEnLista(String texto_buscado, boolean solo_activos) {
        lista_auxiliar.clear();
        modelo_lista.removeAllElements();
        if (Filtros.isNullOrBlank(texto_buscado)) {
            return;
        }
        texto_buscado = Filtros.limpiar(texto_buscado);
        String aux;
        StringBuilder s = new StringBuilder();

        for (OUsuarios i : cache) {
            if (solo_activos && !i.isActivo()) {
                continue;
            }
            aux = Filtros.limpiar(i.getStringR());
            if (aux.contains(texto_buscado)) {
                s.append(i.getId()).append(" - ").append(i.getStringR());
                modelo_lista.addElement(s.toString());
                lista_auxiliar.add(i);
                s.delete(0, s.length());
            }
        }
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
        panelRegistros = new javax.swing.JPanel();
        panel_izq = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jtfBuscadorLista = new javax.swing.JTextField();
        activos = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlUsuarios = new javax.swing.JList<>();
        panel_der = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        panelCampos = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        coincidencias = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfAp = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jtfAm = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jchbTitular = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jcbTitular = new javax.swing.JComboBox<>();
        MantenerTitular = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jcbTipoToma = new javax.swing.JComboBox<>();
        mantenerToma = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jcbCalle = new javax.swing.JComboBox<>();
        mantenerCalle = new javax.swing.JCheckBox();
        jPanel18 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNumeroCasa = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jcbEstado = new javax.swing.JComboBox<>();
        mantenerEstado = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        codigo_bar = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        guardar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        panelConsultas = new javax.swing.JPanel();
        panel_filtros = new javax.swing.JPanel();
        pf_bar_super = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        activar_filtros = new javax.swing.JCheckBox();
        pf_filtros = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        filtro_calle = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        filtro_toma = new javax.swing.JComboBox<>();
        jPanel28 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        filtro_estado = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        filtro_is_titular = new javax.swing.JCheckBox();
        filtro_is_consumidor = new javax.swing.JCheckBox();
        jPanel31 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        filtro_Titular = new javax.swing.JTextField();
        panel_tabla = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        buscador_tabla = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        recargar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtUsuarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(500, 300));

        tab_root.setMinimumSize(new java.awt.Dimension(500, 200));
        tab_root.setOpaque(true);
        tab_root.setPreferredSize(new java.awt.Dimension(1000, 700));

        panelRegistros.setMinimumSize(new java.awt.Dimension(500, 200));
        panelRegistros.setLayout(new java.awt.BorderLayout());

        panel_izq.setPreferredSize(new java.awt.Dimension(500, 670));
        panel_izq.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel12.setPreferredSize(new java.awt.Dimension(500, 45));
        jPanel12.setLayout(new java.awt.BorderLayout(10, 0));

        jtfBuscadorLista.setPreferredSize(new java.awt.Dimension(500, 30));
        jtfBuscadorLista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscadorListaKeyReleased(evt);
            }
        });
        jPanel12.add(jtfBuscadorLista, java.awt.BorderLayout.CENTER);

        activos.setText("Activos");
        activos.setPreferredSize(new java.awt.Dimension(100, 22));
        jPanel12.add(activos, java.awt.BorderLayout.EAST);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/buscar.png"))); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel12.add(jLabel7, java.awt.BorderLayout.LINE_START);

        jLabel18.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel12.add(jLabel18, java.awt.BorderLayout.PAGE_START);

        panel_izq.add(jPanel12, java.awt.BorderLayout.NORTH);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 600));

        jlUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jlUsuarios);

        panel_izq.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelRegistros.add(panel_izq, java.awt.BorderLayout.WEST);

        panel_der.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_der.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setColumnHeaderView(jPanel3);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(500, 300));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 700));

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        panelCampos.setPreferredSize(new java.awt.Dimension(500, 600));
        panelCampos.setLayout(new java.awt.GridLayout(9, 0));

        jPanel4.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Nombre: ");
        jLabel2.setMaximumSize(new java.awt.Dimension(60, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(500, 20));
        jPanel4.add(jLabel2, java.awt.BorderLayout.NORTH);

        jtfNombre.setToolTipText("<html>\nCampo: Nombre\n<br>valores admitidos: Solo texto\n<br>tamaño maximo: 32 Caracteres");
        jtfNombre.setName("Nombre"); // NOI18N
        jtfNombre.setPreferredSize(new java.awt.Dimension(400, 30));
        jtfNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfNombreFocusLost(evt);
            }
        });
        jtfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfNombreKeyPressed(evt);
            }
        });
        jPanel4.add(jtfNombre, java.awt.BorderLayout.CENTER);

        coincidencias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencias.setText("0");
        coincidencias.setToolTipText("<html>\n<h1> Numero de coincidencias.</h1>\n<br>\n<p> Este campos e activa con un espacio.<br> y toma encuenta las coincidencias del nombre, apellido paterno y apellido materno</p>");
        coincidencias.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel4.add(coincidencias, java.awt.BorderLayout.EAST);

        panelCampos.add(jPanel4);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel35.setPreferredSize(new java.awt.Dimension(250, 30));
        jPanel35.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("A. Paterno: ");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel35.add(jLabel3, java.awt.BorderLayout.NORTH);

        jtfAp.setToolTipText("<html>\nCampos: Apellido Paterno\n<br>Valor: Solo texto \n<br>Longitud: 32 Caracteres");
        jtfAp.setName("A. Paterno"); // NOI18N
        jtfAp.setPreferredSize(new java.awt.Dimension(150, 30));
        jtfAp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfApKeyPressed(evt);
            }
        });
        jPanel35.add(jtfAp, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel35);

        jPanel6.setPreferredSize(new java.awt.Dimension(250, 30));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("A. Materno:");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel6.add(jLabel4, java.awt.BorderLayout.NORTH);

        jtfAm.setToolTipText("<html> Campos: Apellido Materno\n<br>Valor: Solo texto <br>Longitud: 32 Caracteres");
        jtfAm.setName("A. Materno"); // NOI18N
        jtfAm.setPreferredSize(new java.awt.Dimension(150, 30));
        jtfAm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfAmKeyPressed(evt);
            }
        });
        jPanel6.add(jtfAm, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6);

        panelCampos.add(jPanel5);

        jPanel9.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jchbTitular.setText("Titutlar");
        jchbTitular.setToolTipText("Mantener el roll del usuario, seleccionado");
        jchbTitular.setPreferredSize(new java.awt.Dimension(500, 20));
        jPanel9.add(jchbTitular, java.awt.BorderLayout.CENTER);

        panelCampos.add(jPanel9);

        jPanel11.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("T. asociado");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(jLabel9, java.awt.BorderLayout.WEST);

        jcbTitular.setPreferredSize(new java.awt.Dimension(340, 30));
        jcbTitular.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbTitularItemStateChanged(evt);
            }
        });
        jPanel11.add(jcbTitular, java.awt.BorderLayout.CENTER);

        MantenerTitular.setText("Man.");
        MantenerTitular.setToolTipText("Mantener el titular seleccionado");
        MantenerTitular.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MantenerTitular.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(MantenerTitular, java.awt.BorderLayout.LINE_END);

        panelCampos.add(jPanel11);

        jPanel7.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("T. Toma");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel7.add(jLabel5, java.awt.BorderLayout.WEST);

        jcbTipoToma.setPreferredSize(new java.awt.Dimension(340, 30));
        jPanel7.add(jcbTipoToma, java.awt.BorderLayout.CENTER);

        mantenerToma.setText("Man.");
        mantenerToma.setToolTipText("Mantener el tipo de toma seleccionado");
        mantenerToma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mantenerToma.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel7.add(mantenerToma, java.awt.BorderLayout.LINE_END);

        panelCampos.add(jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Calle: ");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel8.add(jLabel6, java.awt.BorderLayout.WEST);

        jcbCalle.setPreferredSize(new java.awt.Dimension(340, 30));
        jPanel8.add(jcbCalle, java.awt.BorderLayout.CENTER);

        mantenerCalle.setText("Man.");
        mantenerCalle.setToolTipText("Mantener la calle seleccionada\n");
        mantenerCalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mantenerCalle.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel8.add(mantenerCalle, java.awt.BorderLayout.LINE_END);

        panelCampos.add(jPanel8);

        jPanel18.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("N. Casa");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel18.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jtfNumeroCasa.setToolTipText("<html> Campo: Numero de casa  <br>Valor: Solo numeros <br>Longitud: 3 Caracteres");
        jtfNumeroCasa.setName("Numero de Casa"); // NOI18N
        jtfNumeroCasa.setPreferredSize(new java.awt.Dimension(340, 30));
        jPanel18.add(jtfNumeroCasa, java.awt.BorderLayout.CENTER);

        jCheckBox1.setText("Man.");
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel18.add(jCheckBox1, java.awt.BorderLayout.LINE_END);

        panelCampos.add(jPanel18);

        jPanel10.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Estado: ");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel10.add(jLabel8, java.awt.BorderLayout.WEST);

        jcbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona estado.", "Activo.", "Inactivo." }));
        jcbEstado.setName("Estado"); // NOI18N
        jcbEstado.setPreferredSize(new java.awt.Dimension(340, 30));
        jPanel10.add(jcbEstado, java.awt.BorderLayout.CENTER);

        mantenerEstado.setText("Man.");
        mantenerEstado.setToolTipText("Mantener el estado del usuario seleccionado\n");
        mantenerEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mantenerEstado.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel10.add(mantenerEstado, java.awt.BorderLayout.LINE_END);

        panelCampos.add(jPanel10);

        jPanel14.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("codigo");
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel14.add(jLabel10, java.awt.BorderLayout.LINE_START);

        codigo_bar.setEditable(false);
        codigo_bar.setName("Codigo Barras"); // NOI18N
        jPanel14.add(codigo_bar, java.awt.BorderLayout.CENTER);

        jLabel19.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel14.add(jLabel19, java.awt.BorderLayout.LINE_END);

        panelCampos.add(jPanel14);

        jPanel3.add(panelCampos);

        panelBotones.setPreferredSize(new java.awt.Dimension(500, 100));
        panelBotones.setLayout(new java.awt.GridLayout(2, 0));

        jPanel13.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel13.setLayout(new java.awt.GridLayout(1, 3));

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/disquete.png"))); // NOI18N
        guardar.setText("Guardar");
        guardar.setPreferredSize(new java.awt.Dimension(166, 40));
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel13.add(guardar);

        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/actualizar.png"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setPreferredSize(new java.awt.Dimension(166, 40));
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        jPanel13.add(actualizar);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/eliminar.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.setPreferredSize(new java.awt.Dimension(166, 40));
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel13.add(eliminar);

        panelBotones.add(jPanel13);

        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.setPreferredSize(new java.awt.Dimension(500, 40));
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        panelBotones.add(cancelar);

        jPanel3.add(panelBotones);

        jScrollPane2.setViewportView(jPanel3);

        panel_der.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        panelRegistros.add(panel_der, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Registro de usuarios", panelRegistros);

        panelConsultas.setPreferredSize(new java.awt.Dimension(1000, 670));
        panelConsultas.setLayout(new java.awt.BorderLayout());

        panel_filtros.setPreferredSize(new java.awt.Dimension(1000, 170));
        panel_filtros.setLayout(new java.awt.BorderLayout());

        pf_bar_super.setLayout(new java.awt.BorderLayout());

        jButton6.setText("Quitar filtros");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pf_bar_super.add(jButton6, java.awt.BorderLayout.LINE_END);

        activar_filtros.setText("Filtros");
        activar_filtros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                activar_filtrosItemStateChanged(evt);
            }
        });
        pf_bar_super.add(activar_filtros, java.awt.BorderLayout.CENTER);

        panel_filtros.add(pf_bar_super, java.awt.BorderLayout.NORTH);

        pf_filtros.setLayout(new java.awt.GridLayout(1, 2));

        jPanel22.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel22.setLayout(new java.awt.GridLayout(4, 1));

        jPanel25.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Calle");
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel25.add(jLabel11, java.awt.BorderLayout.WEST);

        jPanel25.add(filtro_calle, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel25);

        jPanel26.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("T. Toma");
        jLabel12.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel26.add(jLabel12, java.awt.BorderLayout.WEST);

        jPanel26.add(filtro_toma, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel26);

        jPanel28.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("Estado");
        jLabel15.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel28.add(jLabel15, java.awt.BorderLayout.WEST);

        filtro_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ESTADO", "ACTIVO", "INACTIVO" }));
        filtro_estado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtro_estadoItemStateChanged(evt);
            }
        });
        jPanel28.add(filtro_estado, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel28);

        pf_filtros.add(jPanel22);

        jPanel24.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel24.setLayout(new java.awt.GridLayout(4, 1));

        jPanel27.setLayout(new java.awt.GridLayout(1, 3));

        filtro_is_titular.setText("Titular");
        filtro_is_titular.setPreferredSize(new java.awt.Dimension(200, 30));
        filtro_is_titular.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtro_is_titularItemStateChanged(evt);
            }
        });
        jPanel27.add(filtro_is_titular);

        filtro_is_consumidor.setText("Consumidor");
        filtro_is_consumidor.setPreferredSize(new java.awt.Dimension(200, 30));
        filtro_is_consumidor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtro_is_consumidorItemStateChanged(evt);
            }
        });
        jPanel27.add(filtro_is_consumidor);

        jPanel24.add(jPanel27);

        jPanel31.setLayout(new java.awt.BorderLayout());

        jLabel16.setText("Titular");
        jLabel16.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel31.add(jLabel16, java.awt.BorderLayout.WEST);
        jPanel31.add(filtro_Titular, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel31);

        pf_filtros.add(jPanel24);

        panel_filtros.add(pf_filtros, java.awt.BorderLayout.CENTER);

        panelConsultas.add(panel_filtros, java.awt.BorderLayout.NORTH);

        panel_tabla.setPreferredSize(new java.awt.Dimension(1000, 500));
        panel_tabla.setLayout(new java.awt.BorderLayout());

        jPanel30.setMinimumSize(new java.awt.Dimension(980, 30));
        jPanel30.setPreferredSize(new java.awt.Dimension(980, 35));
        jPanel30.setLayout(new java.awt.BorderLayout());

        buscador_tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscador_tablaKeyReleased(evt);
            }
        });
        jPanel30.add(buscador_tabla, java.awt.BorderLayout.CENTER);

        jPanel23.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel23.setLayout(new java.awt.BorderLayout());

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        anterior.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel23.add(anterior, java.awt.BorderLayout.WEST);

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        siguiente.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel23.add(siguiente, java.awt.BorderLayout.EAST);

        jPanel30.add(jPanel23, java.awt.BorderLayout.LINE_END);

        recargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        recargar.setPreferredSize(new java.awt.Dimension(100, 30));
        recargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recargarActionPerformed(evt);
            }
        });
        jPanel30.add(recargar, java.awt.BorderLayout.LINE_START);

        panel_tabla.add(jPanel30, java.awt.BorderLayout.NORTH);

        jtUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtUsuarios);

        panel_tabla.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        panelConsultas.add(panel_tabla, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Consulta de usuarios", panelConsultas);

        getContentPane().add(tab_root, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfBuscadorListaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscadorListaKeyReleased
        buscando = true;
        _buscadorEnLista(jtfBuscadorLista.getText(), activos.isSelected());
    }//GEN-LAST:event_jtfBuscadorListaKeyReleased

    private void jlUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlUsuariosMouseClicked
        if (evt.getClickCount() != 2) {
            return;
        }

        int index = jlUsuarios.getSelectedIndex();

        ArrayList<OUsuarios> aux = buscando ? lista_auxiliar : cache;

        if (index < 0 || index >= aux.size()) {
            return;
        }

        usuario_buscado = aux.get(index);

        jtfNombre.setText(usuario_buscado.getNombre());
        jtfAp.setText(usuario_buscado.getAp());
        jtfAm.setText(usuario_buscado.getAm());
        jcbCalle.setSelectedItem(usuario_buscado.getInfoSinFK()[4]);
        jcbTipoToma.setSelectedItem(usuario_buscado.getInfoSinFK()[6]);
        jcbEstado.setSelectedIndex(usuario_buscado.getEstado() == 1 ? 1 : 2);
        jtfNumeroCasa.setText(usuario_buscado.getNumeroCasa());

        if (usuario_buscado.isTitular()) {
            jchbTitular.setSelected(true);
        } else {
            jchbTitular.setSelected(false);
            String arr[] = FormatoBD.bdSalida(usuario_buscado.getInfoSinFK());
            jcbTitular.setSelectedItem(arr[9]);
        }
        //
        if (jchbTitular.isSelected()) {
            jPanel11.setVisible(false);
        } else {
            jPanel11.setVisible(true);
        }
        botonesSecundarios();
        if (buscando) {
            jtfBuscadorLista.setText(null);
            modelo_lista.clear();
            buscando = false;
        }

    }//GEN-LAST:event_jlUsuariosMouseClicked

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "Esta seguro de cancelar esta operacion", "cancelar operacion", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (op == JOptionPane.YES_OPTION) {
            initPanelDeRegistros();
            controlador.actualizarLista();
        }
    }//GEN-LAST:event_cancelarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        if (!validar()) {
            JOptionPane.showMessageDialog(this, "Error en los campos");
            return;
        }
        String[] o = _getInfoUsuario(false);
        o = FormatoBD.bdEntrada(o);
        boolean insertar = operaciones.insertar(o);
        System.out.println(insertar);
        _movimiento(insertar);
    }//GEN-LAST:event_guardarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        if (!mensaje("una actualizacion")) {
            return;
        }
        if (!validar()) {
            JOptionPane.showMessageDialog(this, "Error en los campos");
            return;
        }
        if (usuario_buscado == null) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun usuario");
            return;
        }

        String[] o = _getInfoUsuario(true);
        o = FormatoBD.bdEntrada(o);
        String[] arr = ConstBD.BD_USUARIOS;
        arr = Arrays.copyOfRange(arr, 1, arr.length);
        boolean act = operaciones.actualizar(arr, o, "id = " + usuario_buscado.getId());
        _movimiento(act);

    }//GEN-LAST:event_actualizarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (!mensaje("una eliminacion")) {
            return;
        }
        if (usuario_buscado == null) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun usuario");
            return;
        }
        ArrayList<OUsuarios> lista = operaciones.getLista("titular = " + usuario_buscado.getId());

        if (!lista.isEmpty()) {
            StringBuilder str = new StringBuilder("Este usuario tiene los siguientes consumidores\n");
            for (OUsuarios o : lista) {
                str.append(o.getStringR()).append("\n");
            }
            JOptionPane.showMessageDialog(this, str.toString());
            return;
        }

        boolean op = operaciones.eliminar("id = " + usuario_buscado.getId());
        _movimiento(op);

    }//GEN-LAST:event_eliminarActionPerformed

    private void buscador_tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador_tablaKeyReleased
        if (activar_filtros.isSelected()) {
            actualizarFiltros();
            return;
        }
        lista_de_busqueda.clear();
        controlador.vaciarTabla();
        String txt = Filtros.limpiar(buscador_tabla.getText());
        String aux;
        for (OUsuarios i : cache) {
            aux = Filtros.limpiar(i.getStringR());
            if (aux.contains(txt)) {
                lista_de_busqueda.add(i);
            }
        }
        cargarTabla(modelo_tabla, lista_de_busqueda);
        barra_estado.setResultados(modelo_tabla.getRowCount());
    }//GEN-LAST:event_buscador_tablaKeyReleased

    private boolean _block;

    public void sleep() {
        _block = true;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MenuUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            _block = false;
        }
    }

    private void activar_filtrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_activar_filtrosItemStateChanged
        jButton6.setEnabled(activar_filtros.isSelected());
        filtros(activar_filtros.isSelected());

    }//GEN-LAST:event_activar_filtrosItemStateChanged

    private void recargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recargarActionPerformed
        controlador.actualizarTabla();
        buscador_tabla.setText(null);
        barra_estado.setResultados(modelo_tabla.getRowCount());
    }//GEN-LAST:event_recargarActionPerformed

    private void jcbTitularItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbTitularItemStateChanged


    }//GEN-LAST:event_jcbTitularItemStateChanged

    private void filtro_is_titularItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtro_is_titularItemStateChanged
        if (filtro_is_titular.isSelected()) {
            filtro_is_consumidor.setSelected(false);
        }
        actualizarFiltros();
    }//GEN-LAST:event_filtro_is_titularItemStateChanged

    private void filtro_is_consumidorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtro_is_consumidorItemStateChanged
        if (filtro_is_consumidor.isSelected()) {
            filtro_is_titular.setSelected(false);
        }
        actualizarFiltros();
    }//GEN-LAST:event_filtro_is_consumidorItemStateChanged

    private void filtro_estadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtro_estadoItemStateChanged

        actualizarFiltros();

    }//GEN-LAST:event_filtro_estadoItemStateChanged

    private void jtfApKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfApKeyPressed

        _coincidencias(evt);

    }//GEN-LAST:event_jtfApKeyPressed

    private void jtfNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNombreKeyPressed
        _coincidencias(evt);

    }//GEN-LAST:event_jtfNombreKeyPressed

    private void jtfAmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfAmKeyPressed

        _coincidencias(evt);

    }//GEN-LAST:event_jtfAmKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        filtro_calle.setSelectedIndex(0);
        filtro_toma.setSelectedIndex(0);
        if (filtro_is_titular.isSelected()) {
            filtro_is_titular.setSelected(false);
        }
        if (filtro_is_consumidor.isSelected()) {
            filtro_is_consumidor.setSelected(false);
        }
        filtro_estado.setSelectedIndex(0);
        filtro_Titular.setText(null);
        activar_filtros.setSelected(false);
        jButton6.setEnabled(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jtfNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfNombreFocusLost
        //buscando = true;
        //_buscarCoincidencias();
    }//GEN-LAST:event_jtfNombreFocusLost

    public void pintarCodigo(String[] arr, JPanel visor_codigos) {
        visor_codigos.removeAll();
        String s = codigo(arr);
        codigo_bar.setText(s);
        JBarcodeBean js = new JBarcodeBean(s, new Code128());
        visor_codigos.add(js, BorderLayout.CENTER);
        visor_codigos.updateUI();
    }

    public String codigo(String[] arr) {
        StringBuilder s = new StringBuilder();
        String v1 = cod(arr[0].split("_"));
        s.append(v1);
        String v2 = cod(arr[1].split("_"));
        s.append(v2);
        String v3 = cod(arr[2].split("_"));
        s.append(v3);
        return s.toString();
    }

    public String cod(String... arr) {
        int i = 0;
        for (String string : arr) {
            i += string.charAt(0);
        }
        return String.valueOf(i);
    }

    void _buscarCoincidencias() {
        StringBuilder s = new StringBuilder();
        if (Filtros.isNullOrBlank(jtfNombre.getText())) {
            s.append(jtfNombre.getText());
        }
        if (Filtros.isNullOrBlank(jtfAp.getText())) {
            s.append(jtfAp.getText());
        }
        if (Filtros.isNullOrBlank(jtfAm.getText())) {
            s.append(jtfAm.getText());
        }
        buscando = true;
        _buscadorEnLista(s.toString(), activos.isSelected());
    }

    void _coincidencias(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            coincidencias.setText(null);
            return;
        }

        if (e.getKeyChar() == KeyEvent.VK_TAB) {
            long contador = cache
                    .stream()
                    .filter((t) -> {
                        StringBuilder s = new StringBuilder();
                        if (!Filtros.isNullOrBlank(jtfNombre.getText())) {
                            s.append(jtfNombre.getText());
                        }
                        if (!Filtros.isNullOrBlank(jtfAp.getText())) {
                            s.append(jtfAp.getText());
                        }
                        if (!Filtros.isNullOrBlank(jtfAm.getText())) {
                            s.append(jtfAm.getText());
                        }
                        String x = Filtros.limpiar(t.getStringR());
                        String y = Filtros.limpiar(s.toString());
                        return x.contains(y);
                    })
                    .count();
            _buscarCoincidencias();
            coincidencias.setText(String.valueOf(contador));
            e.getComponent().transferFocus();
        }
    }

    public boolean mensaje(String mov) {
        StringBuilder s = new StringBuilder("¿Seguro que desea realizar ");
        s.append(mov);
        s.append("?");
        int res = JOptionPane.showConfirmDialog(this, s.toString());
        return res == JOptionPane.YES_OPTION;
    }

    public void cargarTabla(DefaultTableModel modelo, Set<OUsuarios> lista) {
        for (OUsuarios o : lista) {
            modelo.addRow(o.getInfoSinFK());
        }
    }

    public void filtros(boolean estado) {
        JComponent[] arr = {
            filtro_calle,
            filtro_estado,
            filtro_toma,
            filtro_is_titular,
            filtro_Titular
        };

        for (JComponent jComboBox : arr) {
            jComboBox.setEnabled(estado);
        }
        filtro_Titular.setEnabled(estado);
    }

    private void _movimiento(boolean estado) {
        if (estado) {
            memoria_cache.actualizar();
            controlador.actualizarVistaPrincipal();
            estadoInicial();
            JOptionPane.showMessageDialog(this, "Operacion realizada");
        } else {
            JOptionPane.showMessageDialog(this, "Operacion no realizada");
        }
    }

    private String[] _getInfoUsuario(boolean isActualizacion) {
        Fecha fech = new Fecha();
        ArrayList<OCalles> calles = FabricaCache.MC_CALLES.getLista();
        ArrayList<OTipoTomas> tomas = FabricaCache.MC_TIPOS_DE_TOMAS.getLista();
        //
        String nom = jtfNombre.getText();
        String ap = jtfAp.getText();
        String am = jtfAm.getText();
        String calle = calles.get(jcbCalle.getSelectedIndex()).getId();
        String ncasa = jtfNumeroCasa.getText();
        String toma = tomas.get(jcbTipoToma.getSelectedIndex()).getId();
        String registro = fech.getNewFechaActualString();
        if (isActualizacion) {
            registro = usuario_buscado.getRegistro();
        }
        String estado = jcbEstado.getSelectedIndex() == 1 ? "1" : "-1";

        String titular;

        if (jchbTitular.isSelected()) {
            titular = "-1";
        } else {
            String aux = EnvUsuario.getUsuarioEnCache(jcbTitular.getItemAt(jcbTitular.getSelectedIndex())).getId();
            if (Filtros.isNullOrBlank(aux) && aux.equalsIgnoreCase("-1")) {
                titular = "-1";
            } else {
                titular = aux;
            }
        }

        String codigo = codigo_bar.getText();

        if (Filtros.isNullOrBlank(codigo)) {
            codigo = "NULL";
        }

        String[] o = new String[]{
            nom, ap, am, calle, ncasa, toma, registro, estado, titular, codigo
        };

        return o;
    }

    boolean validar() {
        int jcb = validarComboBox();
        if (jcb < 4) {
            JOptionPane.showMessageDialog(this, "Error en el combo box de texto: " + jcb);
            return false;
        }
        return true && validarTextField();
    }

    boolean validarTextField() {
        JTextField[] arr = {
            jtfNombre, jtfAp, jtfAp
        };
        String aux;
        for (JTextField i : arr) {
            aux = i.getText();
            if (Filtros.isNullOrBlank(aux) || !Filtros.soloTexto(aux)) {
                JOptionPane.showMessageDialog(this, "Error en el campo: " + i.getName());
                return false;
            }
        }
        aux = jtfNumeroCasa.getText();
        if (!Filtros.soloNumeros(aux)) {
            JOptionPane.showMessageDialog(this, "Error en el campo: " + jtfNumeroCasa.getName());
            return false;
        }

        return true;
    }

    int validarComboBox() {
        if (jcbEstado.getSelectedIndex() == 0) {
            return 0;
        }
        return 4;
    }

    public void actualizarFiltros() {
        if (!activar_filtros.isSelected()) {
            return;
        }
        lista_de_busqueda.clear();
        controlador.vaciarTabla();
        for (OUsuarios i : cache) {
            boolean filtrado = _filtros(i, Filtros.limpiar(buscador_tabla.getText()));
            if (filtrado) {
                lista_de_busqueda.add(i);
            }
        }
        cargarTabla(modelo_tabla, lista_de_busqueda);
        barra_estado.setResultados(modelo_tabla.getRowCount());
    }

    /**
     *
     * @param u
     * @param txt
     * @return
     */
    private boolean _filtros(OUsuarios u, String txt) {
        boolean f = true;
        if (!Filtros.isNullOrBlank(txt)) {
            f = f && Filtros.limpiar(u.getStringR()).contains(txt);
        }
        if (Filtros.swIsCbxRangoValido(filtro_calle) && filtro_calle.getSelectedIndex() != 0) {
            String x = Filtros.limpiar(u.getInfoSinFK()[4]);
            String y = Filtros.limpiar(filtro_calle.getItemAt(filtro_calle.getSelectedIndex()));
            boolean r = y.equalsIgnoreCase(x);
            f = f && r;
        }
        if (Filtros.swIsCbxRangoValido(filtro_toma) && filtro_toma.getSelectedIndex() != 0) {
            String x = Filtros.limpiar(u.getInfoSinFK()[6]);
            String y = Filtros.limpiar(filtro_toma.getItemAt(filtro_toma.getSelectedIndex()));
            boolean r = y.equalsIgnoreCase(x);
            f = f && r;
        }

        if (filtro_is_titular.isSelected()) {
            f = f && u.isTitular();
        }

        if (filtro_is_consumidor.isSelected()) {
            f = f && !u.isTitular();
        }

        if (Filtros.swIsCbxRangoValido(filtro_estado) && filtro_estado.getSelectedIndex() != 0) {
            int i = filtro_estado.getSelectedIndex();
            boolean r;
            if (i == 1) {
                r = u.getEstado() == 1;
            } else {
                r = u.getEstado() == -1;
            }
            f = f && r;

        }
        return f;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox MantenerTitular;
    private javax.swing.JCheckBox activar_filtros;
    private javax.swing.JCheckBox activos;
    private javax.swing.JButton actualizar;
    private javax.swing.JButton anterior;
    private javax.swing.JTextField buscador_tabla;
    private javax.swing.JButton cancelar;
    private javax.swing.JTextField codigo_bar;
    private javax.swing.JLabel coincidencias;
    private javax.swing.JButton eliminar;
    private javax.swing.JTextField filtro_Titular;
    private javax.swing.JComboBox<String> filtro_calle;
    private javax.swing.JComboBox<String> filtro_estado;
    private javax.swing.JCheckBox filtro_is_consumidor;
    private javax.swing.JCheckBox filtro_is_titular;
    private javax.swing.JComboBox<String> filtro_toma;
    private javax.swing.JButton guardar;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> jcbCalle;
    private javax.swing.JComboBox<String> jcbEstado;
    private javax.swing.JComboBox<String> jcbTipoToma;
    private javax.swing.JComboBox<String> jcbTitular;
    private javax.swing.JCheckBox jchbTitular;
    private javax.swing.JList<String> jlUsuarios;
    private javax.swing.JTable jtUsuarios;
    private javax.swing.JTextField jtfAm;
    private javax.swing.JTextField jtfAp;
    private javax.swing.JTextField jtfBuscadorLista;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfNumeroCasa;
    private javax.swing.JCheckBox mantenerCalle;
    private javax.swing.JCheckBox mantenerEstado;
    private javax.swing.JCheckBox mantenerToma;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JPanel panelConsultas;
    private javax.swing.JPanel panelRegistros;
    private javax.swing.JPanel panel_der;
    private javax.swing.JPanel panel_filtros;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JPanel pf_bar_super;
    private javax.swing.JPanel pf_filtros;
    private javax.swing.JButton recargar;
    private javax.swing.JButton siguiente;
    private javax.swing.JTabbedPane tab_root;
    // End of variables declaration//GEN-END:variables
    private final CBarraEstado barra_estado;
    private boolean red_panel_root;
    private boolean red_panel_der;
    /**
     *
     */
    private final CUsuarios controlador;
    /**
     * Variable en la que se guarda el usuario buscado
     */
    private OUsuarios usuario_buscado;

    /**
     * Variable que la lista de donde se toma el usuario, si de la lista
     * auxiliar o de la cache
     */
    private boolean buscando;
    private final Operaciones<OUsuarios> operaciones;
    private final MemoCache<OUsuarios> memoria_cache;
    private final ArrayList<OUsuarios> cache;
    private final ArrayList<OUsuarios> lista_auxiliar;
    private final Set<OUsuarios> lista_de_busqueda;

    private final ModeloTablas modelo_tabla;
    private final DefaultListModel<String> modelo_lista;

    public JComboBox<String> getJcbCalle() {
        return jcbCalle;
    }

    public JComboBox<String> getJcbEstado() {
        return jcbEstado;
    }

    public JComboBox<String> getJcbTipoToma() {
        return jcbTipoToma;
    }

    public JComboBox<String> getJcbTitular() {
        return jcbTitular;
    }

    public DefaultListModel<String> getModeloLista() {
        return modelo_lista;
    }

    public ModeloTablas getModeloTabla() {
        return modelo_tabla;
    }

    public ArrayList<OUsuarios> getCache() {
        return cache;
    }

    public ArrayList<OUsuarios> getLista_auxiliar() {
        return lista_auxiliar;
    }

    public JComboBox<String> getFiltro_calle() {
        return filtro_calle;
    }

    public JComboBox<String> getFiltro_estado() {
        return filtro_estado;
    }

    public JComboBox<String> getFiltro_toma() {
        return filtro_toma;
    }

    @Override
    public Rectangle getBounds() {
        red_panel_root = super.getBounds().getWidth() < 800;
        red_panel_der = panel_der.getBounds().getWidth() < 400;
        if (red_panel_root && red_panel_der && panel_izq.isVisible()) {
            Func.ocultarComponente(false, panel_izq);
        } else if (!red_panel_root) {
            Func.ocultarComponente(true, panel_izq);
        }
        return super.getBounds();
    }

    @Override
    public void dispose() {
        super.dispose();
        estadoInicial();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            SwingUtilities.invokeLater(() -> controlador.cargarComboBoxes());
        } else {
            controlador.vaciarTodo();
        }
    }
}
