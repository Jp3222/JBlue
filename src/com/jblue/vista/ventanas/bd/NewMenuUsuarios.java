/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas.bd;

import com.jblue.controlador.Usuarios;
import com.jblue.modelo.ConstBD;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.FormatoBD;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.tiempo.Fecha;
import com.jblue.vista.conf.SuperVentana;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class NewMenuUsuarios extends SuperVentana {

    /**
     *
     */
    private final Usuarios controlador;
    /**
     * Variable en la que se guarda el usuario buscado
     */
    private OUsuarios usuario_buscado;

    /**
     * Variable que la lista de donde se toma el usuario, si de la lista
     * auxiliar o de la cache
     */
    private boolean buscando;
    private final Fecha fecha;
    private final Operaciones<OUsuarios> operaciones;
    private final MemoCache<OUsuarios> memoria_cache;
    private final ArrayList<OUsuarios> cache;
    private final ArrayList<OUsuarios> lista_auxiliar;

    private final DefaultTableModel modelo_tabla;
    private final DefaultListModel<String> modelo_lista;

    /**
     * Creates new form NewUsuarios
     */
    public NewMenuUsuarios() {
        _TITULO = 3;
        this.lista_auxiliar = new ArrayList<>();
        this.modelo_lista = new DefaultListModel<>();
        this.fecha = new Fecha();
        this.operaciones = FabricaOpraciones.USUARIOS;
        this.memoria_cache = FabricaCache.MC_USUARIOS;
        this.cache = memoria_cache.getLista();
        this.buscando = false;

        this.initComponents();
        controlador = new Usuarios(this);

        this.modelo_tabla = (DefaultTableModel) jtUsuarios.getModel();
        this.jlUsuarios.setModel(modelo_lista);
        this.llamable();
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
    }

    @Override
    public void estadoInicial() {
        usuario_buscado = null;
        buscando = false;
        jtfNombre.requestFocus();
        initPanelDeRegistros();
        initPanelDeConsultas();
    }

    @Override
    protected void addComponentes() {
        super.addComponentes(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

    void initPanelDeRegistros() {
        jtfUsuarioBuscado1.setText(null);
        jtfNombre.setText(null);
        jtfAp.setText(null);
        jtfAm.setText(null);
        initJCB(jcbTipoToma, mantenerToma.isSelected());
        initJCB(jcbCalle, mantenerCalle.isSelected());
        initJCB(jcbEstado, mantenerEstado.isSelected());
        initJCB(jcbTitular, MantenerTitular.isSelected());
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

    void initJCB(JComboBox<String> componente, boolean mantener) {
        if (mantener) {
            return;
        }
        if (componente.getItemCount() == 0) {
            return;
        }
        componente.setSelectedIndex(0);
    }

    void initPanelDeConsultas() {
    }

    @Override
    protected void addEventos() {
        jPanel11.setVisible(false);

        jchbTitular.addChangeListener((ChangeEvent e) -> {
            
            if (jchbTitular.isSelected()) {
                jcbTitular.setEnabled(false);
                jPanel11.setVisible(false);
                return;
            }
            jcbTitular.setEnabled(true);
            jPanel11.setVisible(true);
        });

        jTabbedPane1.addChangeListener((e) -> {
            if (jpConsultas.isVisible()) {
                System.out.println("consultas visible");
                controlador.cargarTabla();
                return;
            }
            controlador.vaciarTabla();
            System.out.println("consultas no visible");

        });
    }

    void buscar(String texto_buscado, boolean solo_activos) {
        lista_auxiliar.clear();
        modelo_lista.removeAllElements();
        texto_buscado = limpiar(texto_buscado);
        for (OUsuarios o : cache) {
            if (solo_activos && !o.isActivo()) {
                continue;
            }
            String aux = limpiar(o.getStringR());
            if (aux.contains(texto_buscado)) {
                modelo_lista.addElement(o.getStringR());
                lista_auxiliar.add(o);
            }
        }

    }

    String limpiar(String txt) {
        return txt.trim().replace(" ", "").toLowerCase();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpRegistros = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jtfUsuarioBuscado1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlUsuarios = new javax.swing.JList<>();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
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
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jcbEstado = new javax.swing.JComboBox<>();
        mantenerEstado = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        guardar = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        actualizar = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        eliminar = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        cancelar = new javax.swing.JButton();
        jpConsultas = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        filtro_calle = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        filtro_toma = new javax.swing.JComboBox<>();
        jPanel29 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        filtro_roll = new javax.swing.JComboBox<>();
        jPanel28 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        filtro_estado = new javax.swing.JComboBox<>();
        jPanel31 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jtfBuscado2 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtUsuarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jpRegistros.setLayout(new javax.swing.BoxLayout(jpRegistros, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jtfUsuarioBuscado1.setPreferredSize(new java.awt.Dimension(500, 30));
        jtfUsuarioBuscado1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfUsuarioBuscado1KeyReleased(evt);
            }
        });
        jPanel1.add(jtfUsuarioBuscado1, java.awt.BorderLayout.PAGE_START);

        jlUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jlUsuarios);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jpRegistros.add(jPanel1);

        jSeparator1.setPreferredSize(new java.awt.Dimension(5, 662));
        jpRegistros.add(jSeparator1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Datos Del Usuario");
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel2.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel4.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Nombre: ");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel4.add(jLabel2, java.awt.BorderLayout.WEST);
        jPanel4.add(jtfNombre, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("A. Paterno");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel5.add(jLabel3, java.awt.BorderLayout.WEST);
        jPanel5.add(jtfAp, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5);

        jPanel6.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("A. Materno");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel6.add(jLabel4, java.awt.BorderLayout.WEST);
        jPanel6.add(jtfAm, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel6);

        jPanel9.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jchbTitular.setText("titular");
        jchbTitular.setToolTipText("Mantener el roll del usuario, seleccionado");
        jPanel9.add(jchbTitular, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel9);

        jPanel11.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("T. asociado");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel11.add(jLabel9, java.awt.BorderLayout.WEST);

        jPanel11.add(jcbTitular, java.awt.BorderLayout.CENTER);

        MantenerTitular.setText("Man.");
        MantenerTitular.setToolTipText("Mantener el titular seleccionado");
        jPanel11.add(MantenerTitular, java.awt.BorderLayout.LINE_END);

        jPanel3.add(jPanel11);

        jPanel7.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("T. Toma");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel7.add(jLabel5, java.awt.BorderLayout.WEST);

        jPanel7.add(jcbTipoToma, java.awt.BorderLayout.CENTER);

        mantenerToma.setText("Man.");
        mantenerToma.setToolTipText("Mantener el tipo de toma seleccionado");
        jPanel7.add(mantenerToma, java.awt.BorderLayout.LINE_END);

        jPanel3.add(jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Calle: ");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel8.add(jLabel6, java.awt.BorderLayout.WEST);

        jPanel8.add(jcbCalle, java.awt.BorderLayout.CENTER);

        mantenerCalle.setText("Man.");
        mantenerCalle.setToolTipText("Mantener la calle seleccionada\n");
        jPanel8.add(mantenerCalle, java.awt.BorderLayout.LINE_END);

        jPanel3.add(jPanel8);

        jPanel10.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Estado: ");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel10.add(jLabel8, java.awt.BorderLayout.WEST);

        jcbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona estado.", "Activo.", "Inactivo." }));
        jPanel10.add(jcbEstado, java.awt.BorderLayout.CENTER);

        mantenerEstado.setText("Man.");
        mantenerEstado.setToolTipText("Mantener el estado del usuario seleccionado\n");
        jPanel10.add(mantenerEstado, java.awt.BorderLayout.LINE_END);

        jPanel3.add(jPanel10);

        jPanel12.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel12.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jPanel12);

        jPanel13.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel13.setLayout(new java.awt.BorderLayout());

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel13.add(guardar, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel13);

        jPanel14.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel14.setLayout(new java.awt.BorderLayout());

        actualizar.setText("Actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });
        jPanel14.add(actualizar, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel14);

        jPanel15.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel15.setLayout(new java.awt.BorderLayout());

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel15.add(eliminar, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel15);

        jPanel16.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel16.setLayout(new java.awt.BorderLayout());

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel16.add(cancelar, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel16);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jpRegistros.add(jPanel2);

        jTabbedPane1.addTab("Registro de usuarios", jpRegistros);

        jpConsultas.setLayout(new java.awt.BorderLayout());

        jPanel19.setPreferredSize(new java.awt.Dimension(980, 162));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel21.setLayout(new javax.swing.BoxLayout(jPanel21, javax.swing.BoxLayout.LINE_AXIS));

        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.PAGE_AXIS));

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

        jPanel29.setLayout(new java.awt.BorderLayout());

        jLabel13.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel29.add(jLabel13, java.awt.BorderLayout.WEST);

        jPanel22.add(jPanel29);

        jPanel21.add(jPanel22);

        jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel27.setLayout(new java.awt.BorderLayout());

        jLabel14.setText("Roll");
        jLabel14.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel27.add(jLabel14, java.awt.BorderLayout.WEST);

        jPanel27.add(filtro_roll, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel27);

        jPanel28.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("Estado");
        jLabel15.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel28.add(jLabel15, java.awt.BorderLayout.WEST);

        jPanel28.add(filtro_estado, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel28);

        jPanel31.setLayout(new java.awt.BorderLayout());

        jLabel16.setText("Titular");
        jLabel16.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel31.add(jLabel16, java.awt.BorderLayout.WEST);
        jPanel31.add(jTextField5, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel31);

        jPanel21.add(jPanel24);

        jPanel19.add(jPanel21, java.awt.BorderLayout.CENTER);

        jPanel32.setLayout(new java.awt.BorderLayout());

        jLabel17.setText("Filtros");
        jLabel17.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel32.add(jLabel17, java.awt.BorderLayout.CENTER);

        jButton6.setText("Quitar filtros");
        jPanel32.add(jButton6, java.awt.BorderLayout.LINE_END);

        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jPanel32.add(jCheckBox1, java.awt.BorderLayout.LINE_START);

        jPanel19.add(jPanel32, java.awt.BorderLayout.NORTH);

        jpConsultas.add(jPanel19, java.awt.BorderLayout.NORTH);

        jPanel20.setLayout(new java.awt.BorderLayout());

        jPanel30.setPreferredSize(new java.awt.Dimension(980, 30));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jToggleButton2.setText("Recargar");
        jToggleButton2.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel30.add(jToggleButton2, java.awt.BorderLayout.LINE_START);

        jtfBuscado2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscado2KeyReleased(evt);
            }
        });
        jPanel30.add(jtfBuscado2, java.awt.BorderLayout.CENTER);

        jPanel23.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jButton4.setText("Siguiente");
        jButton4.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel23.add(jButton4, java.awt.BorderLayout.LINE_START);

        jButton5.setText("Next");
        jButton5.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel23.add(jButton5, java.awt.BorderLayout.CENTER);

        jPanel30.add(jPanel23, java.awt.BorderLayout.LINE_END);

        jPanel20.add(jPanel30, java.awt.BorderLayout.NORTH);

        jtUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "A. PATERNO", "A. MATERNO", "CALLE", "TIPO DE TOMA", "F. REGISTRO", "ESTADO", "TITULAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtUsuarios);

        jPanel20.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jpConsultas.add(jPanel20, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Consulta de usuarios", jpConsultas);

        getContentPane().add(jTabbedPane1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfUsuarioBuscado1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfUsuarioBuscado1KeyReleased
        buscando = true;
        buscar(jtfUsuarioBuscado1.getText(), true);
    }//GEN-LAST:event_jtfUsuarioBuscado1KeyReleased

    private void jlUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlUsuariosMouseClicked
        int index = jlUsuarios.getSelectedIndex();
        if (index < 0 || index >= jlUsuarios.getModel().getSize()) {
            return;
        }
        int clicks = evt.getClickCount();
        ArrayList<OUsuarios> aux;
        if (buscando) {
            aux = lista_auxiliar;
        } else {
            aux = cache;
        }
        if (index < 0 || index >= aux.size()) {
            return;
        }

        usuario_buscado = aux.get(index);

        switch (clicks) {
            case 1:
                if (buscando) {
                    jtfUsuarioBuscado1.setText(usuario_buscado.getStringR());
                }
                break;
            case 2:
                jtfNombre.setText(usuario_buscado.getNombre());
                jtfAp.setText(usuario_buscado.getAp());
                jtfAm.setText(usuario_buscado.getAm());
                jcbCalle.setSelectedItem(usuario_buscado.getInfoSinFK()[4]);
                jcbTipoToma.setSelectedItem(usuario_buscado.getInfoSinFK()[5]);
                jcbEstado.setSelectedIndex(usuario_buscado.getEstado() == 1 ? 1 : 2);

                if (usuario_buscado.isTitular()) {
                    jchbTitular.setSelected(true);
                    jcbTitular.setEnabled(false);
                } else {
                    jchbTitular.setSelected(false);
                    jcbTitular.setSelectedItem(usuario_buscado.getInfoSinFK()[8]);
                }

                botonesSecundarios();
                if (buscando) {
                    controlador.actualizarLista();
                }
                buscando = false;
                break;
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
        String[] o = _guardar();
        o = FormatoBD.bdEntrada(o);
        boolean insertar = operaciones.insertar(o);
        movimiento(insertar);
    }//GEN-LAST:event_guardarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        if (!validar()) {
            JOptionPane.showMessageDialog(this, "Error en los campos");
            return;
        }
        if (usuario_buscado == null) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningun usuario");
            return;
        }
        if (usuario_buscado.isTitular()) {
            ArrayList<OUsuarios> lista = operaciones.getLista("titular = " + usuario_buscado.getId());
            if (!lista.isEmpty()) {
                StringBuilder str = new StringBuilder("Este usuario tiene los siguientes consumidores\n");
                for (OUsuarios o : lista) {
                    str.append(o.getStringR()).append("\n");
                }
                JOptionPane.showMessageDialog(this, str.toString());
                return;
            }
        }
        String[] o = _guardar();
        o = FormatoBD.bdEntrada(o);
        String[] arr = ConstBD.BD_USUARIOS;
        arr = Arrays.copyOfRange(arr, 1, arr.length);
        boolean act = operaciones.actualizar(arr, o, "id = " + usuario_buscado.getId());
        movimiento(act);

    }//GEN-LAST:event_actualizarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
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
        movimiento(op);

    }//GEN-LAST:event_eliminarActionPerformed

    private void jtfBuscado2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscado2KeyReleased
        String texto_buscado = jtfBuscado2.getText();
        lista_auxiliar.clear();
        modelo_lista.removeAllElements();
        texto_buscado = limpiar(texto_buscado);
        for (OUsuarios o : cache) {
            String aux = limpiar(o.getStringR());
            if (aux.contains(texto_buscado)) {
                modelo_tabla.addRow(o.getInfoSinFK());
                lista_auxiliar.add(o);
            }
        }
    }//GEN-LAST:event_jtfBuscado2KeyReleased

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        JComboBox[] arr = {
            filtro_calle, filtro_estado, filtro_roll, filtro_toma
        };
        if (jCheckBox1.isSelected()) {
            for (JComboBox i : arr) {
                i.setEditable(true);
            }
        }
        for (JComboBox i : arr) {
            i.setEditable(false);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    public void movimiento(boolean ok) {
        if (ok) {
            memoria_cache.vaciar();
            memoria_cache.setQuery("titular = -1");
            memoria_cache.cargar();
            memoria_cache.setQuery("titular > 0");
            memoria_cache.cargar();
            memoria_cache.ordenarPorID();
            controlador.actualizarVistaPrincipal();
            estadoInicial();
            JOptionPane.showMessageDialog(this, "Operacion realizada");
        } else {
            JOptionPane.showMessageDialog(this, "Operacion no realizada");
        }
    }

    String[] _guardar() {
        Fecha fech = new Fecha();

        ArrayList<OCalles> tomas = FabricaCache.MC_CALLES.getLista();
        ArrayList<OTipoTomas> calles = FabricaCache.MC_TIPOS_DE_TOMAS.getLista();

        String nom = jtfNombre.getText();
        String ap = jtfAp.getText();
        String am = jtfAm.getText();

        if (rootPaneCheckingEnabled) {

        }

        String calle = calles.get(jcbCalle.getSelectedIndex()).getId();
        String toma = tomas.get(jcbTipoToma.getSelectedIndex()).getId();
        String registro = fech.getNewFechaActualString();
        String estado = jcbEstado.getSelectedIndex() == 1 ? "1" : "-1";
        String titular = this.jchbTitular.isSelected() ? "-1" : cache.get(jcbTitular.getSelectedIndex()).getId();

        String[] o = new String[]{
            nom, ap, am, calle, toma, registro, estado, titular
        };

        return o;
    }

    boolean validar() {
        int jtf = validarJFT();
        System.out.println(jtf);
        if (jtf < 3) {
            JOptionPane.showMessageDialog(this, "Error en la caja de texto: " + jtf);
            return false;
        }
        int jcb = validarJCB();
        if (jcb < 4) {
            JOptionPane.showMessageDialog(this, "Error en el combo box de texto: " + jtf);
            return false;
        }
        return true;
    }

    int validarJFT() {
        JTextField[] arr = {
            jtfNombre, jtfAp, jtfAm
        };
        int x = 0;
        for (JTextField i : arr) {
            String aux = i.getText();
            System.out.println(aux);
            if (!variableValida(aux)) {
                return x;
            }
            if (!soloTexto(aux)) {
                return x;
            }
            x++;
        }
        return arr.length;
    }

    int validarJCB() {
        if (jcbEstado.getSelectedIndex() == 0) {
            return 0;
        }
        return 4;
    }

    boolean variableValida(String txt) {
        return txt != null && !txt.isEmpty();
    }

    boolean soloTexto(String txt) {
        return txt.matches("( |[a-zA-Z]|[_ñÑáÁéÉíÍóÓúÚ]){1,30}");
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            SwingUtilities.invokeLater(() -> {
                controlador.cargarTodo();
            });
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        SwingUtilities.invokeLater(() -> {
            controlador.vaciarTodo();
        });
    }

// <editor-fold defaultstate="collapsed" desc="Variables de interfaz">      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox MantenerTitular;
    private javax.swing.JButton actualizar;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton eliminar;
    private javax.swing.JComboBox<String> filtro_calle;
    private javax.swing.JComboBox<String> filtro_estado;
    private javax.swing.JComboBox<String> filtro_roll;
    private javax.swing.JComboBox<String> filtro_toma;
    private javax.swing.JButton guardar;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JComboBox<String> jcbCalle;
    private javax.swing.JComboBox<String> jcbEstado;
    private javax.swing.JComboBox<String> jcbTipoToma;
    private javax.swing.JComboBox<String> jcbTitular;
    private javax.swing.JCheckBox jchbTitular;
    private javax.swing.JList<String> jlUsuarios;
    private javax.swing.JPanel jpConsultas;
    private javax.swing.JPanel jpRegistros;
    private javax.swing.JTable jtUsuarios;
    private javax.swing.JTextField jtfAm;
    private javax.swing.JTextField jtfAp;
    private javax.swing.JTextField jtfBuscado2;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfUsuarioBuscado1;
    private javax.swing.JCheckBox mantenerCalle;
    private javax.swing.JCheckBox mantenerEstado;
    private javax.swing.JCheckBox mantenerToma;
    // End of variables declaration//GEN-END:variables

//</editor-fold>
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

    public DefaultListModel<String> getModelo_lista() {
        return modelo_lista;
    }

    public DefaultTableModel getModelo_tabla() {
        return modelo_tabla;
    }

    public ArrayList<OUsuarios> getCache() {
        return cache;
    }

    public ArrayList<OUsuarios> getLista_auxiliar() {
        return lista_auxiliar;
    }

}
