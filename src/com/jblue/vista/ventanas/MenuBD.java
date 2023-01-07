/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas;

import com.jblue.controlador.CMenuBD;
import com.jblue.util.eventos.TablasEvt.TablasEvt;
import com.jblue.vista.conf.SuperVentana;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author jp
 */
public class MenuBD extends SuperVentana {

    private final MenuPrincipal menuPrincipal;
    private CMenuBD CON_MENU_BD;

    /**
     * Creates new form MenuBD
     *
     * @param menuPrincipal
     */
    public MenuBD(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        initComponents();
        llamable();

    }

    @Override
    public final void llamable() {
        estadoFinal();
        estadoInicial();
        addComponentes();
        addEventos();
    }

    @Override
    public void estadoInicial() {
        jToolBar1.setFloatable(false);
        initPanelCalles();
        initPanelTTomas();
        initPanelTitulares();
    }

    public void initPanelCalles() {
        jtCalles.clearSelection();
        jtfCalleNombre.setText(null);
        jtfCalleNumero.setText(null);
        jbtCalleActualizar.setEnabled(false);
        jbtCalleEliminar.setEnabled(false);
        jbtCalleGuardar.setEnabled(true);
        jbtCalleCancelar.setEnabled(false);
    }

    public void initPanelTTomas() {
        jtTiposTomas.clearSelection();
        jtfTipoTomasNombre.setText(null);
        jtfTipoTomaCosto.setText(null);
        jtfTipoTomaRecargo.setText(null);
        jbtTTomasGuardar.setEnabled(true);
        jbtTTomasActualizar.setEnabled(false);
        jbtTTomasEliminar.setEnabled(false);
        jbtTTomasCancelar.setEnabled(false);
    }

    public void initPanelTitulares() {
        jtfTitularesNom.setText(null);
        jtfTitularesAp.setText(null);
        jtfTitularesAm.setText(null);
        if (!jchbCalle.isSelected()) {
            jcbTitularesCalle.setSelectedIndex(0);
        }
        if (!jchbToma.isSelected()) {
            jcbTitularesToma.setSelectedIndex(0);
        }
        if (!jchbEstado.isSelected()) {
            jcbTitularesEst.setSelectedIndex(0);
        }

    }

    public void vaciarComboBox() {
        while (jcbTitularesCalle.getItemCount() > 1) {
            jcbTitularesCalle.removeItemAt(1);
        }
        while (jcbTitularesToma.getItemCount() > 1) {
            jcbTitularesToma.removeItemAt(1);
        }
    }

    public void llenarComboBox() {
        ArrayList<String> calles = CON_MENU_BD.getListaCalles();
        for (String calle : calles) {
            jcbTitularesCalle.addItem(calle);
        }
        ArrayList<String> ttomas = CON_MENU_BD.getListaTTomas();
        for (String toma : ttomas) {
            jcbTitularesToma.addItem(toma);
        }
    }

    @Override
    protected void estadoFinal() {
        this.setTitle(NOMBRE + VERSION + " " + SECCION[2]);
        this.CON_MENU_BD = new CMenuBD(this);
        this.setIconImage(ICONO.getImage());
        //
        jcbTitularesCalle.addItem("CALLE");
        jcbTitularesToma.addItem("TIPO DE TOMA");
        jcbTitularesEst.addItem("ESTADO");
        jcbTitularesEst.addItem("ACTIVO");
        jcbTitularesEst.addItem("INACTIVO");
    }

    @Override
    public void addEventos() {
        jtabComplementos.addChangeListener(e -> {
            if (jpCalles.isVisible()) {
                CON_MENU_BD.llenarDatosCalles();
                initPanelCalles();
            } else {
                CON_MENU_BD.vaciarDatosCalles();
            }
            //
            if (jpTiposTomas.isVisible()) {
                CON_MENU_BD.llenarDatosTToma();
                initPanelTTomas();
            } else {
                CON_MENU_BD.vaciarDatosTToma();
            }
        });

        jtabRoot.addChangeListener((ce) -> {
            if (jpTitulares.isVisible()) {
                llenarComboBox();
            } else {
                vaciarComboBox();
            }
            if (jpComplementos.isVisible() && jpCalles.isVisible()) {
                CON_MENU_BD.llenarDatosCalles();
            } else {
                CON_MENU_BD.vaciarDatosCalles();
            }
        });
        //
        jbtSalir.addActionListener(e -> this.dispose());

        evtCalles();
        evtTiposTomas();
        evtTitulares();
    }

    public void evtCalles() {
        TablasEvt evt = new TablasEvt(jtCalles);
        evt.setJButtons(jbtCalleGuardar, jbtCalleActualizar, jbtCalleEliminar, jbtCalleCancelar);
        
        evt.addMouseListener(evt.MOUSE_CLICKED, (MouseEvent e) -> {
            jtfCalleNombre.setText((String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 1));
            jtfCalleNumero.setText((String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 2));
            jbtCalleGuardar.setEnabled(false);
            jbtCalleEliminar.setEnabled(true);
            jbtCalleActualizar.setEnabled(true);
            jbtCalleCancelar.setEnabled(true);
        });
        
        evt.addActionListener(0, e -> CON_MENU_BD.addCalles(
                "0",
                jtfCalleNombre.getText(),
                !jtfCalleNumero.getText().isEmpty() ? jtfCalleNumero.getText() : "S/N"
        ));
        evt.addActionListener(1, e -> CON_MENU_BD.setCalles(
                jtCalles.getSelectedRow(),
                (String) jtCalles.getValueAt(jtCalles.getSelectedRow(), 0),
                jtfCalleNombre.getText(),
                jtfCalleNumero.getText()
        ));
        evt.addActionListener(2, e -> CON_MENU_BD.removeCalle(
                jtCalles.getSelectedRow(),
                jtCalles.getValueAt(
                        jtCalles.getSelectedRow(),
                        0) + ""
        ));
        evt.addActionListener(3, e -> this.initPanelCalles());

    }

    public void evtTiposTomas() {
        TablasEvt evt = new TablasEvt(jtTiposTomas);
        evt.setJButtons(jbtTTomasGuardar, jbtTTomasActualizar, jbtTTomasEliminar, jbtTTomasCancelar
        );
        evt.addMouseListener(evt.MOUSE_CLICKED, e -> {
            jtfTipoTomasNombre.setText((String) jtTiposTomas.getValueAt(jtTiposTomas.getSelectedRow(), 1));
            jtfTipoTomaCosto.setText((String) jtTiposTomas.getValueAt(jtTiposTomas.getSelectedRow(), 2));
            jtfTipoTomaRecargo.setText((String) jtTiposTomas.getValueAt(jtTiposTomas.getSelectedRow(), 3));
            //
            jbtTTomasGuardar.setEnabled(false);
            jbtTTomasActualizar.setEnabled(true);
            jbtTTomasEliminar.setEnabled(true);
            jbtTTomasCancelar.setEnabled(true);
        });

        evt.addActionListener(0, e -> CON_MENU_BD.addTToma(
                "0",
                jtfTipoTomasNombre.getText(),
                jtfTipoTomaCosto.getText(),
                jtfTipoTomaRecargo.getText()
        ));

        evt.addActionListener(1, e -> CON_MENU_BD.setTToma(
                jtTiposTomas.getSelectedRow(),
                (String) jtTiposTomas.getValueAt(jtTiposTomas.getSelectedRow(), 0),
                jtfTipoTomasNombre.getText(),
                jtfTipoTomaCosto.getText(),
                jtfTipoTomaRecargo.getText()
        ));

        evt.addActionListener(2, e -> CON_MENU_BD.removeTToma(
                jtTiposTomas.getSelectedRow(),
                (String) jtTiposTomas.getValueAt(jtTiposTomas.getSelectedRow(), 0)
        ));
        evt.addActionListener(3, e -> initPanelTTomas());

    }

    public void evtTitulares() {
        TablasEvt t = new TablasEvt(jtTitulares);
        t.setJButtons(
                jbtTitularesGuardar, jbtTitularesActualizar,
                jbtTitularesEliminar, jbtTitularesCancelar
        );
        //
        t.addMouseListener(t.MOUSE_CLICKED, (e) -> {
            int row = jtTitulares.getSelectedRow();
            jtfTitularesNom.setText((String) jtTitulares.getValueAt(row, 0));
            jtfTitularesAp.setText((String) jtTitulares.getValueAt(row, 1));
            jtfTitularesAm.setText((String) jtTitulares.getValueAt(row, 2));
        });
        //
        t.addActionListener(3, e -> {
            System.out.println("Holaaa");
            initPanelTitulares();
        });
    }

    public String[] getIndex() {
        String i1 = jcbTitularesCalle.getItemAt(jcbTitularesCalle.getSelectedIndex());
        String i2 = jcbTitularesToma.getItemAt(jcbTitularesToma.getSelectedIndex());
        String i3 = jcbTitularesEst.getItemAt(jcbTitularesEst.getSelectedIndex());
        String[] split = i1.split(" ");
        String[] split1 = i2.split(" ");
        String[] split2 = i3.split(" ");
        return new String[]{split[0], split1[0], split2[0]};
    }

    public JTable getJtCalles() {
        return jtCalles;
    }

    public JTable getJtTiposTomas() {
        return jtTiposTomas;
    }

    public JTable getJtTomasRegistradas() {
        return jtTomasRegistradas;
    }

    public JTable getJtTitulares() {
        return jtTitulares;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jbtSalir = new javax.swing.JButton();
        jtabRoot = new javax.swing.JTabbedPane();
        jpTitulares = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTitulares = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jtfTitularesNom = new javax.swing.JTextField();
        jtfTitularesAp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jtfTitularesAm = new javax.swing.JTextField();
        jbtTitularesGuardar = new javax.swing.JButton();
        jbtTitularesActualizar = new javax.swing.JButton();
        jbtTitularesEliminar = new javax.swing.JButton();
        jbtTitularesCancelar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jcbTitularesCalle = new javax.swing.JComboBox<>();
        jcbTitularesToma = new javax.swing.JComboBox<>();
        jcbTitularesEst = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jchbToma = new javax.swing.JCheckBox();
        jchbCalle = new javax.swing.JCheckBox();
        jchbEstado = new javax.swing.JCheckBox();
        jpConsumidores = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jpComplementos = new javax.swing.JPanel();
        jtabComplementos = new javax.swing.JTabbedPane();
        jpCalles = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtCalles = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfCalleNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jbtCalleGuardar = new javax.swing.JButton();
        jbtCalleActualizar = new javax.swing.JButton();
        jbtCalleEliminar = new javax.swing.JButton();
        jbtCalleCancelar = new javax.swing.JButton();
        jtfCalleNumero = new javax.swing.JTextField();
        jpTiposTomas = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtTiposTomas = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfTipoTomasNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbtTTomasGuardar = new javax.swing.JButton();
        jbtTTomasActualizar = new javax.swing.JButton();
        jbtTTomasEliminar = new javax.swing.JButton();
        jbtTTomasCancelar = new javax.swing.JButton();
        jtfTipoTomaCosto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jtfTipoTomaRecargo = new javax.swing.JTextField();
        jpTomasRegistradas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtTomasRegistradas = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setRollover(true);
        jToolBar1.setPreferredSize(new java.awt.Dimension(100, 45));

        jbtSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerrar-sesion.png"))); // NOI18N
        jbtSalir.setActionCommand("login");
        jbtSalir.setFocusable(false);
        jbtSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jbtSalir);

        jtTitulares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "A. PATERNO", "A. MATERNO", "CALLE", "TIPO DE TOMA", "F. REGISTRO", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTitulares.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtTitulares);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del titular"));
        jPanel9.setOpaque(false);

        jLabel5.setText("Nombre");

        jtfTitularesNom.setPreferredSize(new java.awt.Dimension(180, 35));

        jtfTitularesAp.setPreferredSize(new java.awt.Dimension(180, 35));

        jLabel6.setText("A. Paterno.");

        jLabel7.setText("A. Materno");

        jtfTitularesAm.setPreferredSize(new java.awt.Dimension(180, 35));

        jbtTitularesGuardar.setText("Guardar");
        jbtTitularesGuardar.setPreferredSize(new java.awt.Dimension(70, 35));

        jbtTitularesActualizar.setText("Actualizar");
        jbtTitularesActualizar.setPreferredSize(new java.awt.Dimension(70, 35));

        jbtTitularesEliminar.setText("Eliminar");
        jbtTitularesEliminar.setPreferredSize(new java.awt.Dimension(70, 35));

        jbtTitularesCancelar.setText("Cancelar");
        jbtTitularesCancelar.setPreferredSize(new java.awt.Dimension(70, 35));

        jButton5.setText("Ant");
        jButton5.setPreferredSize(new java.awt.Dimension(70, 35));

        jLabel8.setText("Calle");

        jLabel9.setText("Tipo de toma");

        jLabel10.setText("Estado");

        jcbTitularesCalle.setPreferredSize(new java.awt.Dimension(180, 35));

        jcbTitularesToma.setPreferredSize(new java.awt.Dimension(180, 35));

        jcbTitularesEst.setPreferredSize(new java.awt.Dimension(180, 35));

        jButton6.setText("Sig");
        jButton6.setPreferredSize(new java.awt.Dimension(70, 35));

        jLabel11.setText("Tipo de toma");

        jchbToma.setText("Mantener T. Toma");

        jchbCalle.setText("Mantener Calle");

        jchbEstado.setText("Mantener Estado");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jchbCalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbTitularesCalle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfTitularesNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfTitularesAp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jcbTitularesToma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jchbToma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfTitularesAm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addComponent(jbtTitularesGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jchbEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbTitularesEst, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtTitularesEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtTitularesActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtTitularesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTitularesNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtTitularesGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTitularesAp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfTitularesAm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtTitularesActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtTitularesEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtTitularesCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbTitularesCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbTitularesToma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbTitularesEst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jchbToma)
                            .addComponent(jchbCalle)
                            .addComponent(jchbEstado))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jpTitularesLayout = new javax.swing.GroupLayout(jpTitulares);
        jpTitulares.setLayout(jpTitularesLayout);
        jpTitularesLayout.setHorizontalGroup(
            jpTitularesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitularesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTitularesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jpTitularesLayout.setVerticalGroup(
            jpTitularesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitularesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jtabRoot.addTab("Titulares", jpTitulares);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del consumidor"));
        jPanel10.setOpaque(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpConsumidoresLayout = new javax.swing.GroupLayout(jpConsumidores);
        jpConsumidores.setLayout(jpConsumidoresLayout);
        jpConsumidoresLayout.setHorizontalGroup(
            jpConsumidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConsumidoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpConsumidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpConsumidoresLayout.setVerticalGroup(
            jpConsumidoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsumidoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jtabRoot.addTab("Consumidores", jpConsumidores);

        jtabComplementos.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jtCalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "NUMERO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCalles.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jtCalles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtCalles.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jtCalles);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del tipo de toma"));
        jPanel14.setOpaque(false);

        jLabel1.setText("Nombre");

        jtfCalleNombre.setPreferredSize(new java.awt.Dimension(23, 40));

        jLabel2.setText("Numero");

        jbtCalleGuardar.setText("Guardar");
        jbtCalleGuardar.setPreferredSize(new java.awt.Dimension(70, 40));

        jbtCalleActualizar.setText("Actualizar");
        jbtCalleActualizar.setPreferredSize(new java.awt.Dimension(70, 40));

        jbtCalleEliminar.setText("Eliminar");
        jbtCalleEliminar.setPreferredSize(new java.awt.Dimension(70, 40));

        jbtCalleCancelar.setText("Cancelar");
        jbtCalleCancelar.setPreferredSize(new java.awt.Dimension(70, 40));

        jtfCalleNumero.setPreferredSize(new java.awt.Dimension(23, 40));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfCalleNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jbtCalleGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtCalleActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtCalleEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtCalleCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfCalleNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCalleNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfCalleNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtCalleGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtCalleActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtCalleEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtCalleCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpCallesLayout = new javax.swing.GroupLayout(jpCalles);
        jpCalles.setLayout(jpCallesLayout);
        jpCallesLayout.setHorizontalGroup(
            jpCallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCallesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpCallesLayout.setVerticalGroup(
            jpCallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtabComplementos.addTab("Calles", jpCalles);

        jtTiposTomas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TIPO", "COSTO", "RECARGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTiposTomas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtTiposTomas.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jtTiposTomas);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del tipo de toma"));
        jPanel12.setOpaque(false);

        jLabel3.setText("Nombre");

        jtfTipoTomasNombre.setPreferredSize(new java.awt.Dimension(23, 40));

        jLabel4.setText("Costo del tipo de toma");

        jbtTTomasGuardar.setText("Guardar");
        jbtTTomasGuardar.setPreferredSize(new java.awt.Dimension(70, 40));

        jbtTTomasActualizar.setText("Actualizar");
        jbtTTomasActualizar.setPreferredSize(new java.awt.Dimension(70, 40));

        jbtTTomasEliminar.setText("Eliminar");
        jbtTTomasEliminar.setPreferredSize(new java.awt.Dimension(70, 40));

        jbtTTomasCancelar.setText("Cancelar");
        jbtTTomasCancelar.setPreferredSize(new java.awt.Dimension(70, 40));

        jtfTipoTomaCosto.setPreferredSize(new java.awt.Dimension(23, 40));

        jLabel12.setText("Costo del Recargo");

        jtfTipoTomaRecargo.setPreferredSize(new java.awt.Dimension(23, 40));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfTipoTomasNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jbtTTomasGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtTTomasActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtTTomasEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtTTomasCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfTipoTomaCosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jtfTipoTomaRecargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTipoTomasNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTipoTomaCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfTipoTomaRecargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbtTTomasGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtTTomasActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtTTomasEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtTTomasCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpTiposTomasLayout = new javax.swing.GroupLayout(jpTiposTomas);
        jpTiposTomas.setLayout(jpTiposTomasLayout);
        jpTiposTomasLayout.setHorizontalGroup(
            jpTiposTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTiposTomasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpTiposTomasLayout.setVerticalGroup(
            jpTiposTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTiposTomasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTiposTomasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtabComplementos.addTab("Tipos de Tomas", jpTiposTomas);

        jpTomasRegistradas.setEnabled(false);

        jtTomasRegistradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTomasRegistradas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtTomasRegistradas.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jtTomasRegistradas);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la toma"));
        jPanel13.setOpaque(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpTomasRegistradasLayout = new javax.swing.GroupLayout(jpTomasRegistradas);
        jpTomasRegistradas.setLayout(jpTomasRegistradasLayout);
        jpTomasRegistradasLayout.setHorizontalGroup(
            jpTomasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTomasRegistradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpTomasRegistradasLayout.setVerticalGroup(
            jpTomasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTomasRegistradasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTomasRegistradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtabComplementos.addTab("Tomas Registradas", jpTomasRegistradas);

        javax.swing.GroupLayout jpComplementosLayout = new javax.swing.GroupLayout(jpComplementos);
        jpComplementos.setLayout(jpComplementosLayout);
        jpComplementosLayout.setHorizontalGroup(
            jpComplementosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtabComplementos)
        );
        jpComplementosLayout.setVerticalGroup(
            jpComplementosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtabComplementos)
        );

        jtabRoot.addTab("Complementos", jpComplementos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jtabRoot)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtabRoot))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbtCalleActualizar;
    private javax.swing.JButton jbtCalleCancelar;
    private javax.swing.JButton jbtCalleEliminar;
    private javax.swing.JButton jbtCalleGuardar;
    private javax.swing.JButton jbtSalir;
    private javax.swing.JButton jbtTTomasActualizar;
    private javax.swing.JButton jbtTTomasCancelar;
    private javax.swing.JButton jbtTTomasEliminar;
    private javax.swing.JButton jbtTTomasGuardar;
    private javax.swing.JButton jbtTitularesActualizar;
    private javax.swing.JButton jbtTitularesCancelar;
    private javax.swing.JButton jbtTitularesEliminar;
    private javax.swing.JButton jbtTitularesGuardar;
    private javax.swing.JComboBox<String> jcbTitularesCalle;
    private javax.swing.JComboBox<String> jcbTitularesEst;
    private javax.swing.JComboBox<String> jcbTitularesToma;
    private javax.swing.JCheckBox jchbCalle;
    private javax.swing.JCheckBox jchbEstado;
    private javax.swing.JCheckBox jchbToma;
    private javax.swing.JPanel jpCalles;
    private javax.swing.JPanel jpComplementos;
    private javax.swing.JPanel jpConsumidores;
    private javax.swing.JPanel jpTiposTomas;
    private javax.swing.JPanel jpTitulares;
    private javax.swing.JPanel jpTomasRegistradas;
    private javax.swing.JTable jtCalles;
    private javax.swing.JTable jtTiposTomas;
    private javax.swing.JTable jtTitulares;
    private javax.swing.JTable jtTomasRegistradas;
    private javax.swing.JTabbedPane jtabComplementos;
    private javax.swing.JTabbedPane jtabRoot;
    private javax.swing.JTextField jtfCalleNombre;
    private javax.swing.JTextField jtfCalleNumero;
    private javax.swing.JTextField jtfTipoTomaCosto;
    private javax.swing.JTextField jtfTipoTomaRecargo;
    private javax.swing.JTextField jtfTipoTomasNombre;
    private javax.swing.JTextField jtfTitularesAm;
    private javax.swing.JTextField jtfTitularesAp;
    private javax.swing.JTextField jtfTitularesNom;
    // End of variables declaration//GEN-END:variables

}
