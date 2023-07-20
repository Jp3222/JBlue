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
package com.jblue.vista.ventanas.vistas.compartidos;

import com.jblue.modelo.ConstGs;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OHisMovimientos;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.Filtros;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.crypto.EncriptadoAES;
import com.jblue.util.modelosgraficos.model.ModeloTablas;
import com.jblue.util.tiempo.Fecha;
import com.jblue.vista.normas.SuperVista;
import com.jblue.vista.ventanas.menus.administracion.MenuAdministrador;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author jp
 */
public class VPersonal extends SuperVista {

    private final MemoCache<OPersonal> memo_cache;
    private final ArrayList<OPersonal> cache;
    //
    private final ModeloTablas modelo_tabla;
    //
    private final JCheckBox[] PERMISOS;

    private final int SEC_USUARIOS,
            SEC_CALLES,
            SEC_TIPO_TOMAS,
            SEC_TOMAS_REG;

    private final JComponent componentes[];
    private final Fecha fecha;

    /**
     * Creates new form VPersonal
     */
    public VPersonal() {
        memo_cache = FabricaCache.MC_PERSONAL;
        cache = memo_cache.getLista();
        fecha = new Fecha();

        SEC_USUARIOS = 0;
        SEC_CALLES = 3;
        SEC_TIPO_TOMAS = 6;
        SEC_TOMAS_REG = 9;

        initComponents();

        this.PERMISOS = new JCheckBox[]{
            usuarios_ac, usuarios_es, usuarios_lc,
            calles_ac, calles_es, calles_lc,
            tipotomas_ac, tipotomas_es, tipotomas_lc,
            tomasreg_ac, tomasreg_es, tomasreg_lc
        };

        this.componentes = new JComponent[]{
            nombre, apellidos, estado, cargo, usuario, contra, periodo
        };

        panel_fecha_inicio.setVisible(periodo.isSelected());
        panel_fecha_fin.setVisible(periodo.isSelected());
        modelo_tabla = new ModeloTablas(ConstGs.BD_PERSONAL);
        jTable1.setModel(modelo_tabla);

        _blq(false);

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
    public void componentesEstadoInicial() {
        //
        nombre.setText(null);
        apellidos.setText(null);
        estado.setSelectedIndex(0);
        fecha_registro.setText(null);
        cargo.setSelectedIndex(0);
        usuario.setText(null);
        contra.setText(null);
        //
        periodo.setSelected(false);
        //
        _botones(false);
    }

    @Override
    protected void componentesEstadoFinal() {
        modelo_tabla.setAllCellEditable(false);
    }

    @Override
    protected void contruirComponentes() {
        JPopupMenu popup = new JPopupMenu("opciones");
        JMenuItem i1 = popup.add("Editar");
        i1.addActionListener(e -> {
        });

        JMenuItem i2 = popup.add("Eliminar");
        i2.addActionListener(e -> {
            int i = jTable1.getSelectedRow();
            OPersonal j = cache.get(i);
            ArrayList<OHisMovimientos> lista = FabricaOpraciones.HISTORIAL_DE_MOVIMIENTOS.getLista("id = " + j.getId());
            if (!lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El usuario: " + '"' + j.getStringR() + '"' + ", no se puede eliminar");
                return;
            }

            boolean eliminar = FabricaOpraciones.PERSONAL.eliminar("id = " + j.getId());
            if (eliminar) {
                _actualizarTabla();
                JOptionPane.showMessageDialog(this, "OPeraciones Exitosa");
            } else {
                JOptionPane.showMessageDialog(this, "OPeraciones Erronea");
            }
        });
        jTable1.setComponentPopupMenu(popup);
    }

    @Override
    protected void manejoEventos() {
        tab_root.addChangeListener(e -> {
            if (panel_consultas.isVisible()) {
                _cargarTabla();
            } else {
                modelo_tabla.clear();
            }
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
        panel_registros = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        apellidos = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        jPanel34 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        fecha_registro = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cargo = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        contra = new javax.swing.JPasswordField();
        periodo = new javax.swing.JCheckBox();
        panel_fecha_inicio = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        año_inicio = new javax.swing.JSpinner();
        jPanel24 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        mes_inicio = new javax.swing.JComboBox<>();
        jPanel23 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dia_inicio = new javax.swing.JSpinner();
        panel_fecha_fin = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        año_fin = new javax.swing.JSpinner();
        jPanel28 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        mes_fin = new javax.swing.JComboBox<>();
        jPanel29 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        dia_fin = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        usuarios_ac = new javax.swing.JCheckBox();
        usuarios_es = new javax.swing.JCheckBox();
        usuarios_lc = new javax.swing.JCheckBox();
        jPanel31 = new javax.swing.JPanel();
        calles_ac = new javax.swing.JCheckBox();
        calles_es = new javax.swing.JCheckBox();
        calles_lc = new javax.swing.JCheckBox();
        jPanel32 = new javax.swing.JPanel();
        tipotomas_ac = new javax.swing.JCheckBox();
        tipotomas_es = new javax.swing.JCheckBox();
        tipotomas_lc = new javax.swing.JCheckBox();
        jPanel33 = new javax.swing.JPanel();
        tomasreg_ac = new javax.swing.JCheckBox();
        tomasreg_es = new javax.swing.JCheckBox();
        tomasreg_lc = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        nuevo = new javax.swing.JButton();
        guardar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        panel_consultas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setName("Personal"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 700));
        setLayout(new java.awt.BorderLayout());

        tab_root.setMinimumSize(new java.awt.Dimension(800, 700));
        tab_root.setPreferredSize(new java.awt.Dimension(800, 700));

        panel_registros.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(1000, 638));
        jPanel9.setLayout(new java.awt.GridLayout(1, 3));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de usuario"));
        jPanel6.setPreferredSize(new java.awt.Dimension(333, 600));
        jPanel6.setLayout(new java.awt.GridLayout(7, 1));

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Nombre");
        jLabel1.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel11.add(jLabel1, java.awt.BorderLayout.NORTH);

        nombre.setName("Nombre"); // NOI18N
        nombre.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel11.add(nombre, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel11);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Apellidos");
        jLabel2.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel12.add(jLabel2, java.awt.BorderLayout.NORTH);

        apellidos.setName("Apellidos"); // NOI18N
        apellidos.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel12.add(apellidos, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel12);

        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel14.setText("Estado");
        jLabel14.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel19.add(jLabel14, java.awt.BorderLayout.NORTH);

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estado", "Activo", "Inactivo" }));
        estado.setName("Estado"); // NOI18N
        estado.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel19.add(estado, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel19);

        jPanel34.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("Fecha de registro");
        jLabel15.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel34.add(jLabel15, java.awt.BorderLayout.NORTH);

        fecha_registro.setEnabled(false);
        fecha_registro.setName("Fecha de Registro"); // NOI18N
        fecha_registro.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel34.add(fecha_registro, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel34);

        jPanel9.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del cargo"));
        jPanel7.setPreferredSize(new java.awt.Dimension(333, 600));
        jPanel7.setLayout(new java.awt.GridLayout(6, 1));

        jPanel5.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Cargo");
        jLabel3.setMinimumSize(new java.awt.Dimension(333, 18));
        jLabel3.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel5.add(jLabel3, java.awt.BorderLayout.NORTH);

        cargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccione cargo", "pasante", "secretario", "tesorero", "presidente", "administrador" }));
        cargo.setName("Cargo"); // NOI18N
        cargo.setPreferredSize(new java.awt.Dimension(333, 30));
        cargo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cargoItemStateChanged(evt);
            }
        });
        jPanel5.add(cargo, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel5);

        jPanel16.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Usuario");
        jLabel4.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel16.add(jLabel4, java.awt.BorderLayout.PAGE_START);

        usuario.setName("Usuario"); // NOI18N
        usuario.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel16.add(usuario, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel16);

        jPanel17.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Contraseña");
        jLabel5.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel17.add(jLabel5, java.awt.BorderLayout.PAGE_START);

        contra.setName("Contraseña"); // NOI18N
        contra.setPreferredSize(new java.awt.Dimension(333, 30));
        jPanel17.add(contra, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel17);

        periodo.setText("Limitar Periodo");
        periodo.setPreferredSize(new java.awt.Dimension(300, 50));
        periodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                periodoItemStateChanged(evt);
            }
        });
        periodo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                periodoStateChanged(evt);
            }
        });
        jPanel7.add(periodo);

        panel_fecha_inicio.setPreferredSize(new java.awt.Dimension(300, 50));
        panel_fecha_inicio.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Fecha de inicio");
        jLabel6.setPreferredSize(new java.awt.Dimension(98, 20));
        panel_fecha_inicio.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        jPanel21.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel21.setLayout(new java.awt.GridLayout(1, 3));

        jPanel22.setPreferredSize(new java.awt.Dimension(111, 60));
        jPanel22.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Año");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel22.add(jLabel9, java.awt.BorderLayout.PAGE_START);

        año_inicio.setModel(new javax.swing.SpinnerNumberModel(2023, 2023, null, 1));
        jPanel22.add(año_inicio, java.awt.BorderLayout.CENTER);

        jPanel21.add(jPanel22);

        jPanel24.setPreferredSize(new java.awt.Dimension(111, 60));
        jPanel24.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Mes");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel24.add(jLabel8, java.awt.BorderLayout.PAGE_START);

        mes_inicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC" }));
        jPanel24.add(mes_inicio, java.awt.BorderLayout.CENTER);

        jPanel21.add(jPanel24);

        jPanel23.setPreferredSize(new java.awt.Dimension(111, 60));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Dia");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel23.add(jLabel7, java.awt.BorderLayout.PAGE_START);

        dia_inicio.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel23.add(dia_inicio, java.awt.BorderLayout.CENTER);

        jPanel21.add(jPanel23);

        panel_fecha_inicio.add(jPanel21, java.awt.BorderLayout.CENTER);

        jPanel7.add(panel_fecha_inicio);

        panel_fecha_fin.setPreferredSize(new java.awt.Dimension(300, 50));
        panel_fecha_fin.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("Fecha de fin");
        jLabel10.setPreferredSize(new java.awt.Dimension(98, 20));
        panel_fecha_fin.add(jLabel10, java.awt.BorderLayout.PAGE_START);

        jPanel26.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel26.setLayout(new java.awt.GridLayout(1, 3));

        jPanel27.setPreferredSize(new java.awt.Dimension(111, 60));
        jPanel27.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Año");
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel27.add(jLabel11, java.awt.BorderLayout.PAGE_START);

        año_fin.setModel(new javax.swing.SpinnerNumberModel(2023, 2023, null, 1));
        jPanel27.add(año_fin, java.awt.BorderLayout.CENTER);

        jPanel26.add(jPanel27);

        jPanel28.setPreferredSize(new java.awt.Dimension(111, 60));
        jPanel28.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("Mes");
        jLabel12.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel28.add(jLabel12, java.awt.BorderLayout.PAGE_START);

        mes_fin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC" }));
        jPanel28.add(mes_fin, java.awt.BorderLayout.CENTER);

        jPanel26.add(jPanel28);

        jPanel29.setPreferredSize(new java.awt.Dimension(111, 60));
        jPanel29.setLayout(new java.awt.BorderLayout());

        jLabel13.setText("Dia");
        jLabel13.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel29.add(jLabel13, java.awt.BorderLayout.PAGE_START);

        dia_fin.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel29.add(dia_fin, java.awt.BorderLayout.CENTER);

        jPanel26.add(jPanel29);

        panel_fecha_fin.add(jPanel26, java.awt.BorderLayout.CENTER);

        jPanel7.add(panel_fecha_fin);

        jPanel9.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Permisos"));
        jPanel8.setPreferredSize(new java.awt.Dimension(333, 600));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios"));
        jPanel30.setPreferredSize(new java.awt.Dimension(333, 100));
        jPanel30.setLayout(new java.awt.BorderLayout());

        usuarios_ac.setText("AC");
        usuarios_ac.setToolTipText("Permiso de Acceso");
        usuarios_ac.setEnabled(false);
        usuarios_ac.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel30.add(usuarios_ac, java.awt.BorderLayout.LINE_START);

        usuarios_es.setText("ES");
        usuarios_es.setToolTipText("Permiso de Escritura");
        usuarios_es.setEnabled(false);
        usuarios_es.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel30.add(usuarios_es, java.awt.BorderLayout.CENTER);

        usuarios_lc.setText("LC");
        usuarios_lc.setToolTipText("Permiso de Lectura");
        usuarios_lc.setEnabled(false);
        usuarios_lc.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel30.add(usuarios_lc, java.awt.BorderLayout.LINE_END);

        jPanel8.add(jPanel30);

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder("Calles"));
        jPanel31.setPreferredSize(new java.awt.Dimension(333, 100));
        jPanel31.setLayout(new java.awt.BorderLayout());

        calles_ac.setText("AC");
        calles_ac.setToolTipText("Permiso de Acceso");
        calles_ac.setEnabled(false);
        calles_ac.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel31.add(calles_ac, java.awt.BorderLayout.LINE_START);

        calles_es.setText("ES");
        calles_es.setToolTipText("Permiso de Escritura");
        calles_es.setEnabled(false);
        calles_es.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel31.add(calles_es, java.awt.BorderLayout.CENTER);

        calles_lc.setText("LC");
        calles_lc.setToolTipText("Permiso de Lectura");
        calles_lc.setEnabled(false);
        calles_lc.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel31.add(calles_lc, java.awt.BorderLayout.LINE_END);

        jPanel8.add(jPanel31);

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de tomas"));
        jPanel32.setPreferredSize(new java.awt.Dimension(333, 100));
        jPanel32.setLayout(new java.awt.BorderLayout());

        tipotomas_ac.setText("AC");
        tipotomas_ac.setToolTipText("Permiso de Acceso");
        tipotomas_ac.setEnabled(false);
        tipotomas_ac.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel32.add(tipotomas_ac, java.awt.BorderLayout.LINE_START);

        tipotomas_es.setText("ES");
        tipotomas_es.setToolTipText("Permiso de Escritura");
        tipotomas_es.setEnabled(false);
        tipotomas_es.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel32.add(tipotomas_es, java.awt.BorderLayout.CENTER);

        tipotomas_lc.setText("LC");
        tipotomas_lc.setToolTipText("Permiso de Lectura");
        tipotomas_lc.setEnabled(false);
        tipotomas_lc.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel32.add(tipotomas_lc, java.awt.BorderLayout.LINE_END);

        jPanel8.add(jPanel32);

        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder("Tomas Registradas"));
        jPanel33.setPreferredSize(new java.awt.Dimension(333, 100));
        jPanel33.setLayout(new java.awt.BorderLayout());

        tomasreg_ac.setText("AC");
        tomasreg_ac.setToolTipText("Permiso de Acceso");
        tomasreg_ac.setEnabled(false);
        tomasreg_ac.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel33.add(tomasreg_ac, java.awt.BorderLayout.LINE_START);

        tomasreg_es.setText("ES");
        tomasreg_es.setToolTipText("Permiso de Escritura");
        tomasreg_es.setEnabled(false);
        tomasreg_es.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel33.add(tomasreg_es, java.awt.BorderLayout.CENTER);

        tomasreg_lc.setText("LC");
        tomasreg_lc.setToolTipText("Permiso de Lectura");
        tomasreg_lc.setEnabled(false);
        tomasreg_lc.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel33.add(tomasreg_lc, java.awt.BorderLayout.LINE_END);

        jPanel8.add(jPanel33);

        jPanel9.add(jPanel8);

        panel_registros.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setPreferredSize(new java.awt.Dimension(1000, 100));

        nuevo.setText("Nuevo");
        nuevo.setPreferredSize(new java.awt.Dimension(200, 40));
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        jPanel10.add(nuevo);

        guardar.setText("Guardar");
        guardar.setPreferredSize(new java.awt.Dimension(200, 40));
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel10.add(guardar);

        cancelar.setText("Cancelar");
        cancelar.setPreferredSize(new java.awt.Dimension(200, 40));
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel10.add(cancelar);

        panel_registros.add(jPanel10, java.awt.BorderLayout.SOUTH);

        tab_root.addTab("Registrar Personal", panel_registros);

        panel_consultas.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 35));
        jPanel1.setLayout(new java.awt.BorderLayout(5, 0));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(150, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, java.awt.BorderLayout.WEST);
        jPanel1.add(jTextField1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        jPanel1.add(jPanel2, java.awt.BorderLayout.EAST);

        panel_consultas.add(jPanel1, java.awt.BorderLayout.NORTH);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        panel_consultas.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tab_root.addTab("Consultar Personal", panel_consultas);

        add(tab_root, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cargoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cargoItemStateChanged
        switch (cargo.getSelectedIndex()) {
            case 1 ->
                pasante();
            case 2 ->
                secretario();
            case 3 ->
                tesorero();
            case 4 ->
                presidente();
            default ->
                defecto();
        }
    }//GEN-LAST:event_cargoItemStateChanged

    private void periodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_periodoItemStateChanged
        panel_fecha_inicio.setVisible(periodo.isSelected());
        panel_fecha_fin.setVisible(periodo.isSelected());
    }//GEN-LAST:event_periodoItemStateChanged

    private void periodoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_periodoStateChanged
        if (periodo.isSelected()) {
            valoresDia(dia_inicio);
            mes_inicio.setSelectedIndex(fecha.getMes() - 1);
            valoresAño(año_inicio);

            valoresDia(dia_fin);
            mes_fin.setSelectedIndex(fecha.getMes() - 1);
            valoresAño(año_fin);
        }
    }//GEN-LAST:event_periodoStateChanged

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        _blq(true);
        _botones(true);
    }//GEN-LAST:event_nuevoActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed

        Operaciones<OPersonal> personal = FabricaOpraciones.getPERSONAL();
        String[] o = _guardar();

        personal.insertar(o);
        componentesEstadoInicial();
        FabricaCache.MC_PERSONAL.actualizar();
        _actualizarTabla();
        JOptionPane.showMessageDialog(this, "Operacion Exitosa");
    }//GEN-LAST:event_guardarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        _blq(false);
        _botones(false);
    }//GEN-LAST:event_cancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        _actualizarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed


    }//GEN-LAST:event_jButton3ActionPerformed

    private String[] _guardar() {
        try {
            String nom = nombre.getText();
            String ap = apellidos.getText();
            String car = String.valueOf(cargo.getSelectedIndex());
            String user = encriptador.encriptar(usuario.getText(), String.valueOf(contra.getPassword()));
            String pass = encriptador.encriptar(String.valueOf(contra.getPassword()), usuario.getText());
            String regis = fecha_registro.getText();
            String esta = estado.getSelectedIndex() == 1 ? "1" : "-1";
            String permi = getPermisosAsignados();
            String per;
            if (periodo.isSelected()) {
                per = getPeriodo();
            } else {
                per = "IND";
            }
            return new String[]{
                nom, ap, car, user, pass, regis, esta, permi, per
            };
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(MenuAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getPeriodo() {
        String inicio = consFecha(dia_inicio, mes_inicio, año_inicio);
        String fin = consFecha(dia_fin, mes_fin, año_fin);
        StringBuilder sb = new StringBuilder(20);
        sb.append(inicio).append(":");
        sb.append(fin);
        return sb.toString();
    }

    public String consFecha(JSpinner dia, JComboBox<String> mes, JSpinner año) {
        StringBuilder sb = new StringBuilder();
        //
        SpinnerNumberModel _dia = (SpinnerNumberModel) dia.getModel();
        String d = String.valueOf(_dia.getNumber().intValue());
        if (d.length() == 1) {
            sb.append("0");
        }
        sb.append(d).append("-");
        int m = mes.getSelectedIndex();
        m += 1;
        if (m <= 9) {
            sb.append("0");
        }
        sb.append(m).append("-");
        SpinnerNumberModel _año = (SpinnerNumberModel) año.getModel();
        int y = _año.getNumber().intValue();
        sb.append(y);
        return sb.toString();
    }

    public void valoresDia(JSpinner sp) {
        SpinnerNumberModel model = (SpinnerNumberModel) sp.getModel();
        model.setMaximum(fecha.getMaxDiaDelMes());
        model.setValue(fecha.getDiaDelMes());
    }

    public void valoresAño(JSpinner sp) {
        SpinnerNumberModel model = (SpinnerNumberModel) sp.getModel();
        model.setMinimum(fecha.getAño());
        model.setMaximum(fecha.getAño() + 4);
        model.setValue(model.getMinimum());
    }

    final void _blq(boolean estado) {
        SwingUtilities.invokeLater(() -> {
            if (estado) {
                fecha_registro.setText(fecha.getNewFechaActualString());

            } else {
                fecha_registro.setText(null);
            }
            for (JComponent componente : componentes) {
                componente.setEnabled(estado);
            }
            if (periodo.isSelected()) {
                periodo.setSelected(false);
            }
            this.estado.setSelectedIndex(0);
            cargo.setSelectedIndex(0);
        });
    }

    private void _botones(boolean estado) {
        nuevo.setEnabled(!estado);
        guardar.setEnabled(estado);
        cancelar.setEnabled(estado);
    }

    private boolean _camposValidos() {
        JTextField[] comp = {
            nombre, apellidos, usuario, contra
        };

        for (JTextField i : comp) {
            if (Filtros.isNullOrBlank(i.getText())) {
                JOptionPane.showMessageDialog(this, "El campo: " + i.getName() + " no es valido");
                return false;
            }
        }
        return true;
    }

    private void defecto() {
        asignarPermisos(SEC_USUARIOS, 0);
        asignarPermisos(SEC_CALLES, 0);
        asignarPermisos(SEC_TIPO_TOMAS, 0);
        asignarPermisos(SEC_TOMAS_REG, 0);
    }

    public void pasante() {
        asignarPermisos(SEC_USUARIOS, 7);
        asignarPermisos(SEC_CALLES, 7);
        asignarPermisos(SEC_TIPO_TOMAS, 7);
        asignarPermisos(SEC_TOMAS_REG, 5);
    }

    public void secretario() {
        asignarPermisos(SEC_USUARIOS, 7);
        asignarPermisos(SEC_CALLES, 7);
        asignarPermisos(SEC_TIPO_TOMAS, 7);
        asignarPermisos(SEC_TOMAS_REG, 5);
    }

    public void tesorero() {
        asignarPermisos(SEC_USUARIOS, 7);
        asignarPermisos(SEC_CALLES, 7);
        asignarPermisos(SEC_TIPO_TOMAS, 7);
        asignarPermisos(SEC_TOMAS_REG, 3);
    }

    public void presidente() {
        asignarPermisos(SEC_USUARIOS, 7);
        asignarPermisos(SEC_CALLES, 7);
        asignarPermisos(SEC_TIPO_TOMAS, 7);
        asignarPermisos(SEC_TOMAS_REG, 7);
    }

    public void asignarPermisos(int seccion, int permisos) {
        int ac = seccion;
        int es = seccion + 1;
        int lc = seccion + 2;
        switch (permisos) {
            case 0 -> {
                PERMISOS[ac].setSelected(false);
                PERMISOS[es].setSelected(false);
                PERMISOS[lc].setSelected(false);
            }
            case 3 -> {
                PERMISOS[ac].setSelected(true);
                PERMISOS[es].setSelected(false);
                PERMISOS[lc].setSelected(true);
            }

            case 4 -> {
                PERMISOS[ac].setSelected(true);
                PERMISOS[es].setSelected(true);
                PERMISOS[lc].setSelected(false);
            }

            case 7 -> {
                PERMISOS[ac].setSelected(true);
                PERMISOS[es].setSelected(true);
                PERMISOS[lc].setSelected(true);
            }

        }
    }

    private String getPermisosAsignados() {
        StringBuilder sb = new StringBuilder();

        int usuarios, tipo_tomas, calles, tomas_reg;

        usuarios = getNivelPermisos(usuarios_ac.isSelected(), usuarios_es.isSelected(), usuarios_lc.isSelected());

        calles = getNivelPermisos(calles_ac.isSelected(), calles_es.isSelected(), calles_lc.isSelected());

        tipo_tomas = getNivelPermisos(tipotomas_ac.isSelected(), tipotomas_es.isSelected(), tipotomas_lc.isSelected());

        tomas_reg = getNivelPermisos(tomasreg_ac.isSelected(), tomasreg_es.isSelected(), tomasreg_lc.isSelected());

        sb.append(usuarios).append(tipo_tomas).append(calles).append(tomas_reg);

        return sb.toString();
    }

    private int getNivelPermisos(boolean... permisos) {
        if (permisos.length > 3) {
            return -1;
        }
        int nivel = 0;
        if (permisos[0]) {
            nivel += 1;
        }

        if (permisos[1]) {
            nivel += 2;
        }

        if (permisos[2]) {
            nivel += 4;
        }
        return nivel;
    }

    private void _cargarTabla() {
        for (OPersonal i : cache) {
            modelo_tabla.addRow(i.getInfo());
        }
    }

    private void _actualizarTabla() {
        modelo_tabla.clear();
        _cargarTabla();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidos;
    private javax.swing.JSpinner año_fin;
    private javax.swing.JSpinner año_inicio;
    private javax.swing.JCheckBox calles_ac;
    private javax.swing.JCheckBox calles_es;
    private javax.swing.JCheckBox calles_lc;
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox<String> cargo;
    private javax.swing.JPasswordField contra;
    private javax.swing.JSpinner dia_fin;
    private javax.swing.JSpinner dia_inicio;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JTextField fecha_registro;
    private javax.swing.JButton guardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> mes_fin;
    private javax.swing.JComboBox<String> mes_inicio;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton nuevo;
    private javax.swing.JPanel panel_consultas;
    private javax.swing.JPanel panel_fecha_fin;
    private javax.swing.JPanel panel_fecha_inicio;
    private javax.swing.JPanel panel_registros;
    private javax.swing.JCheckBox periodo;
    private javax.swing.JTabbedPane tab_root;
    private javax.swing.JCheckBox tipotomas_ac;
    private javax.swing.JCheckBox tipotomas_es;
    private javax.swing.JCheckBox tipotomas_lc;
    private javax.swing.JCheckBox tomasreg_ac;
    private javax.swing.JCheckBox tomasreg_es;
    private javax.swing.JCheckBox tomasreg_lc;
    private javax.swing.JTextField usuario;
    private javax.swing.JCheckBox usuarios_ac;
    private javax.swing.JCheckBox usuarios_es;
    private javax.swing.JCheckBox usuarios_lc;
    // End of variables declaration//GEN-END:variables
    private final EncriptadoAES encriptador = new EncriptadoAES();

}
