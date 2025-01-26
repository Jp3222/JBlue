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

import com.jblue.controlador.FactoryController;
import com.jblue.controlador.compc.ListController;
import com.jblue.controlador.compc.TableController;
import com.jblue.modelo.ConstBD;
import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.fabricas.FactoryCache;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.Filtros;
import com.jblue.util.cache.MemoListCache;
import com.jblue.vista.marco.ListSearchView;
import com.jblue.vista.marco.vistas.DBView;
import com.jutil.swingw.modelos.JTableModel;
import java.awt.CardLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juan-campos
 */
public class ShopCartView extends DBView implements ListSearchView {

    private final CardLayout ly;
    private final JTableModel table_model;
    private final DefaultListModel<OUsuarios> list_model;
    private OUsuarios object_search;
    ArrayList<JCheckBox> month_paid_list;
    private final ListController<OUsuarios> list_controller;
    private int count_elements;

    /**
     * Creates new form VCaja
     */
    public ShopCartView() {
        initComponents();

        list_model = new DefaultListModel();
        table_model = new JTableModel(new String[]{
            "No.", "Usuario", "Mes Pagado"
        }, 0);
        month_paid_list = new ArrayList<>(12);

        month_paid_list.addAll(Arrays.asList(
                ene, feb, mar,
                abr, may, jun,
                jul, ago, sep,
                oct, nov, dic
        ));
        objects_table.setModel(table_model);
        users_list.setModel(list_model);
        ly = (CardLayout) root_panel.getLayout();

        controller = FactoryController.getShopCartController(this);
        table_controller = new TableController(this, new MemoListCache(
                FuncionesBD.getObjects(ConstBD.TABLAS[6],
                        new String[]{
                            "id", "usuario", "mes"
                        }
                )));
        list_controller = new ListController(this, FactoryCache.USUARIOS);
        build();
    }

    @Override
    public final void build() {
        components();
        events();
        finalState();
        initialState();
    }

    @Override
    public void events() {

        pay_button.addActionListener(controller);
        cancel_button.addActionListener(controller);
        clear_button.addActionListener(controller);
        recargos_button.addActionListener(controller);
        other_pay_button.addActionListener(controller);
        pay_last_button.addActionListener(controller);
        util_button.addActionListener(controller);
        info_button.addActionListener(controller);
        lock_button.addActionListener(controller);
        search_user_button.addActionListener(controller);
        //
        all_months_buttons.addActionListener(controller);
        //
        search_field_list.addKeyListener((KeyListener) controller);
        //
        users_list.addMouseListener((MouseListener) controller);
        //

        register_button.addActionListener(table_controller);
        search_button.addActionListener(table_controller);
        reload_button.addActionListener(table_controller);
        back_button.addActionListener(table_controller);
        next_button.addActionListener(table_controller);
        //objects_table.addMouseListener(table_controller);
        //
    }

    @Override
    public void components() {

    }

    @Override
    public void initialState() {
        user_type_field.setText(null);
        name_user_field.setText(null);
        type_toma_field.setText(null);
        cost_field.setText(null);
        month_paid_field.setText(null);
    }

    @Override
    public void finalState() {

    }

    public JComponent getRootPanel() {
        return root_panel;
    }

    public boolean isRootPanelLock() {
        return lock_button.isSelected();
    }

    public void updateScreenInfo() {
        String user_type = object_search.isTitular() ? "Titular" : "Consumidor";
        user_type_field.setText(user_type);
        name_user_field.setText(object_search.getNombre());
        OTipoTomas get = FactoryCache.TIPO_DE_TOMAS.get(e -> e.getId().equals(object_search.getToma()));
        type_toma_field.setText(get.getTipo());
        cost_field.setText(String.valueOf(get.getCosto()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tools_bar_panel = new javax.swing.JPanel();
        lock_button = new javax.swing.JToggleButton();
        jPanel15 = new javax.swing.JPanel();
        register_button = new javax.swing.JButton();
        search_button = new javax.swing.JButton();
        search_user_button = new javax.swing.JButton();
        root_panel = new javax.swing.JPanel();
        register_panel = new javax.swing.JPanel();
        panel_busquedas = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search_field_list = new javax.swing.JTextField();
        count_elements_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        users_list = new javax.swing.JList<>();
        panel_info = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        user_type_field = new javax.swing.JTextField();
        info_button = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        name_user_field = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        type_toma_field = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cost_field = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        month_paid_field = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        all_months_buttons = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        ene = new javax.swing.JCheckBox();
        feb = new javax.swing.JCheckBox();
        mar = new javax.swing.JCheckBox();
        abr = new javax.swing.JCheckBox();
        may = new javax.swing.JCheckBox();
        jun = new javax.swing.JCheckBox();
        jul = new javax.swing.JCheckBox();
        ago = new javax.swing.JCheckBox();
        sep = new javax.swing.JCheckBox();
        oct = new javax.swing.JCheckBox();
        nov = new javax.swing.JCheckBox();
        dic = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        btn_movimientos = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        Jlabel1 = new javax.swing.JLabel();
        lbl_cambio = new javax.swing.JLabel();
        panel_operaciones = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        pay_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();
        clear_button = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        recargos_button = new javax.swing.JButton();
        other_pay_button = new javax.swing.JButton();
        pay_last_button = new javax.swing.JButton();
        util_button = new javax.swing.JButton();
        search_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        search_field_table = new javax.swing.JTextField();
        reload_button = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        back_button = new javax.swing.JButton();
        next_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        objects_table = new javax.swing.JTable();
        status_bar_panel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        range = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();

        setName("Inicio"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        tools_bar_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        tools_bar_panel.setLayout(new java.awt.BorderLayout(5, 5));

        lock_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/lock.png"))); // NOI18N
        lock_button.setActionCommand("lock");
        lock_button.setPreferredSize(new java.awt.Dimension(100, 30));
        lock_button.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/desbloquear.png"))); // NOI18N
        tools_bar_panel.add(lock_button, java.awt.BorderLayout.WEST);

        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        register_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        register_button.setText("Cobros");
        register_button.setActionCommand("register_view");
        jPanel15.add(register_button);

        search_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        search_button.setText("Pagos del dia");
        search_button.setActionCommand("search_view");
        jPanel15.add(search_button);

        tools_bar_panel.add(jPanel15, java.awt.BorderLayout.CENTER);

        search_user_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/search.png"))); // NOI18N
        search_user_button.setActionCommand("search_user_button");
        search_user_button.setPreferredSize(new java.awt.Dimension(100, 30));
        tools_bar_panel.add(search_user_button, java.awt.BorderLayout.EAST);

        add(tools_bar_panel, java.awt.BorderLayout.NORTH);

        root_panel.setLayout(new java.awt.CardLayout(5, 5));

        register_panel.setName("register"); // NOI18N
        register_panel.setLayout(new javax.swing.BoxLayout(register_panel, javax.swing.BoxLayout.PAGE_AXIS));

        panel_busquedas.setOpaque(false);
        panel_busquedas.setPreferredSize(new java.awt.Dimension(700, 150));
        panel_busquedas.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel1.setText("Buscador");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel6.add(jLabel1, java.awt.BorderLayout.LINE_START);

        search_field_list.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel6.add(search_field_list, java.awt.BorderLayout.CENTER);

        count_elements_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count_elements_label.setText("0");
        count_elements_label.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel6.add(count_elements_label, java.awt.BorderLayout.EAST);

        panel_busquedas.add(jPanel6, java.awt.BorderLayout.NORTH);

        users_list.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        users_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(users_list);

        panel_busquedas.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        register_panel.add(panel_busquedas);

        panel_info.setOpaque(false);
        panel_info.setPreferredSize(new java.awt.Dimension(700, 400));
        panel_info.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel19.setOpaque(false);
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.GridLayout(4, 0, 0, 5));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel7.setText("Tipo de usuario");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel3.add(jLabel7, java.awt.BorderLayout.WEST);

        user_type_field.setEditable(false);
        user_type_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel3.add(user_type_field, java.awt.BorderLayout.CENTER);

        info_button.setText("Info de usuario");
        info_button.setActionCommand("info");
        info_button.setPreferredSize(new java.awt.Dimension(150, 29));
        jPanel3.add(info_button, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel3);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel8.setText("Nombre");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel12.add(jLabel8, java.awt.BorderLayout.WEST);

        name_user_field.setEditable(false);
        name_user_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel12.add(name_user_field, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel9.setText("Tipo de toma");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel13.add(jLabel9, java.awt.BorderLayout.WEST);

        type_toma_field.setEditable(false);
        type_toma_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        type_toma_field.setPreferredSize(new java.awt.Dimension(200, 36));
        jPanel13.add(type_toma_field, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel3.setText("Costo: $");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel4.add(jLabel3, java.awt.BorderLayout.WEST);

        cost_field.setEditable(false);
        cost_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cost_field.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel4.add(cost_field, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel10.setText("Meses pagados");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel14.add(jLabel10, java.awt.BorderLayout.WEST);

        month_paid_field.setEditable(false);
        month_paid_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel14.add(month_paid_field, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel14);

        jPanel19.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(700, 40));
        jPanel5.setLayout(new java.awt.BorderLayout());

        all_months_buttons.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        all_months_buttons.setText("Todos");
        all_months_buttons.setActionCommand("all_months");
        all_months_buttons.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        all_months_buttons.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(all_months_buttons, java.awt.BorderLayout.EAST);

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Mese a pagar");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel5.add(jLabel6, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(700, 60));
        jPanel11.setLayout(new java.awt.GridLayout(1, 12));

        ene.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        ene.setText("ENE");
        ene.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ene.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ene.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ene.setPreferredSize(new java.awt.Dimension(10, 47));
        ene.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        ene.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(ene);

        feb.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        feb.setText("FEB");
        feb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        feb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        feb.setPreferredSize(new java.awt.Dimension(10, 47));
        feb.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        feb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(feb);

        mar.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        mar.setText("MAR");
        mar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        mar.setPreferredSize(new java.awt.Dimension(10, 47));
        mar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        mar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(mar);

        abr.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        abr.setText("ABR");
        abr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        abr.setPreferredSize(new java.awt.Dimension(10, 47));
        abr.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        abr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(abr);

        may.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        may.setText("MAY");
        may.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        may.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        may.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        may.setPreferredSize(new java.awt.Dimension(10, 47));
        may.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        may.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(may);

        jun.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jun.setText("JUN");
        jun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        jun.setPreferredSize(new java.awt.Dimension(10, 47));
        jun.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        jun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(jun);

        jul.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jul.setText("JUL");
        jul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jul.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        jul.setPreferredSize(new java.awt.Dimension(10, 47));
        jul.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        jul.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(jul);

        ago.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        ago.setText("AGO");
        ago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ago.setPreferredSize(new java.awt.Dimension(10, 47));
        ago.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        ago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(ago);

        sep.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        sep.setText("SEP");
        sep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        sep.setPreferredSize(new java.awt.Dimension(10, 47));
        sep.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        sep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(sep);

        oct.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        oct.setText("OCT");
        oct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        oct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        oct.setPreferredSize(new java.awt.Dimension(10, 47));
        oct.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        oct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(oct);

        nov.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        nov.setText("NOV");
        nov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nov.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        nov.setPreferredSize(new java.awt.Dimension(10, 47));
        nov.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        nov.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(nov);

        dic.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        dic.setText("DIC");
        dic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        dic.setPreferredSize(new java.awt.Dimension(10, 47));
        dic.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        dic.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(dic);

        jPanel10.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel19.add(jPanel10, java.awt.BorderLayout.SOUTH);

        panel_info.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel20.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel20.setLayout(new java.awt.GridLayout(2, 0));

        jPanel21.setOpaque(false);
        jPanel21.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Total: $");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel21.add(jLabel2, java.awt.BorderLayout.WEST);

        lbl_total.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        lbl_total.setText("0.0");
        jPanel21.add(lbl_total, java.awt.BorderLayout.CENTER);

        btn_movimientos.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        btn_movimientos.setText("Movimientos");
        btn_movimientos.setToolTipText("");
        btn_movimientos.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel21.add(btn_movimientos, java.awt.BorderLayout.LINE_END);

        jPanel20.add(jPanel21);

        jPanel22.setOpaque(false);
        jPanel22.setLayout(new java.awt.BorderLayout(10, 0));

        Jlabel1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        Jlabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Jlabel1.setText("Cambio: $");
        Jlabel1.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel22.add(Jlabel1, java.awt.BorderLayout.LINE_START);

        lbl_cambio.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        lbl_cambio.setText("0.0");
        jPanel22.add(lbl_cambio, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel22);

        panel_info.add(jPanel20, java.awt.BorderLayout.SOUTH);

        register_panel.add(panel_info);

        panel_operaciones.setOpaque(false);
        panel_operaciones.setPreferredSize(new java.awt.Dimension(680, 80));
        panel_operaciones.setLayout(new java.awt.GridLayout(2, 3, 5, 5));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        pay_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        pay_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img5.png"))); // NOI18N
        pay_button.setText("Cobrar");
        pay_button.setActionCommand("pay");
        jPanel7.add(pay_button);

        cancel_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cancel_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        cancel_button.setText("Cancelar");
        cancel_button.setActionCommand("cancel");
        jPanel7.add(cancel_button);

        clear_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        clear_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/limpiar.png"))); // NOI18N
        clear_button.setText("Limpiar");
        clear_button.setActionCommand("clear");
        jPanel7.add(clear_button);

        panel_operaciones.add(jPanel7);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        recargos_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        recargos_button.setText("Recargos");
        recargos_button.setActionCommand("surcharges");
        jPanel8.add(recargos_button);

        other_pay_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        other_pay_button.setText("Otros Pagos");
        other_pay_button.setActionCommand("other_payments");
        jPanel8.add(other_pay_button);

        pay_last_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        pay_last_button.setText("Pagos atrasados");
        pay_last_button.setActionCommand("late_payments");
        jPanel8.add(pay_last_button);

        util_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        util_button.setText("Utilidades");
        jPanel8.add(util_button);

        panel_operaciones.add(jPanel8);

        register_panel.add(panel_operaciones);

        root_panel.add(register_panel, "register");

        search_panel.setName("consult"); // NOI18N
        search_panel.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel1.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel16.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/search.png"))); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel16.add(jLabel11, java.awt.BorderLayout.WEST);
        jPanel16.add(search_field_table, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel16, java.awt.BorderLayout.CENTER);

        reload_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        reload_button.setActionCommand("reload");
        reload_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel1.add(reload_button, java.awt.BorderLayout.WEST);

        jPanel17.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        back_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        back_button.setActionCommand("back");
        back_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel17.add(back_button);

        next_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        next_button.setActionCommand("next");
        next_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel17.add(next_button);

        jPanel1.add(jPanel17, java.awt.BorderLayout.EAST);

        search_panel.add(jPanel1, java.awt.BorderLayout.NORTH);

        objects_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Usuario", "Mes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        objects_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(objects_table);

        search_panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        status_bar_panel.setPreferredSize(new java.awt.Dimension(100, 30));
        status_bar_panel.setLayout(new java.awt.BorderLayout());

        jPanel32.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel32.setLayout(new java.awt.BorderLayout());

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("No.");
        jPanel32.add(jLabel18, java.awt.BorderLayout.CENTER);

        count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count.setText("0");
        count.setToolTipText("Numero de pagos hechos.");
        count.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel32.add(count, java.awt.BorderLayout.LINE_END);

        status_bar_panel.add(jPanel32, java.awt.BorderLayout.WEST);

        range.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        range.setText("0 - 0");
        range.setToolTipText("");
        status_bar_panel.add(range, java.awt.BorderLayout.CENTER);

        jPanel29.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel29.setLayout(new java.awt.BorderLayout());

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Total:");
        jPanel29.add(jLabel17, java.awt.BorderLayout.CENTER);

        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("0");
        total.setToolTipText("Numero de pagos hechos.");
        total.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel29.add(total, java.awt.BorderLayout.LINE_END);

        status_bar_panel.add(jPanel29, java.awt.BorderLayout.EAST);

        search_panel.add(status_bar_panel, java.awt.BorderLayout.SOUTH);

        root_panel.add(search_panel, "consult");

        add(root_panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel1;
    private javax.swing.JCheckBox abr;
    private javax.swing.JCheckBox ago;
    private javax.swing.JCheckBox all_months_buttons;
    private javax.swing.JButton back_button;
    private javax.swing.JButton btn_movimientos;
    private javax.swing.JButton cancel_button;
    private javax.swing.JButton clear_button;
    private javax.swing.JTextField cost_field;
    private javax.swing.JLabel count;
    private javax.swing.JLabel count_elements_label;
    private javax.swing.JCheckBox dic;
    private javax.swing.JCheckBox ene;
    private javax.swing.JCheckBox feb;
    private javax.swing.JButton info_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox jul;
    private javax.swing.JCheckBox jun;
    private javax.swing.JLabel lbl_cambio;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JToggleButton lock_button;
    private javax.swing.JCheckBox mar;
    private javax.swing.JCheckBox may;
    private javax.swing.JTextField month_paid_field;
    private javax.swing.JTextField name_user_field;
    private javax.swing.JButton next_button;
    private javax.swing.JCheckBox nov;
    private javax.swing.JTable objects_table;
    private javax.swing.JCheckBox oct;
    private javax.swing.JButton other_pay_button;
    private javax.swing.JPanel panel_busquedas;
    private javax.swing.JPanel panel_info;
    private javax.swing.JPanel panel_operaciones;
    private javax.swing.JButton pay_button;
    private javax.swing.JButton pay_last_button;
    private javax.swing.JLabel range;
    private javax.swing.JButton recargos_button;
    private javax.swing.JButton register_button;
    private javax.swing.JPanel register_panel;
    private javax.swing.JButton reload_button;
    private javax.swing.JPanel root_panel;
    private javax.swing.JButton search_button;
    private javax.swing.JTextField search_field_list;
    private javax.swing.JTextField search_field_table;
    private javax.swing.JPanel search_panel;
    private javax.swing.JButton search_user_button;
    private javax.swing.JCheckBox sep;
    private javax.swing.JPanel status_bar_panel;
    private javax.swing.JPanel tools_bar_panel;
    private javax.swing.JLabel total;
    private javax.swing.JTextField type_toma_field;
    private javax.swing.JTextField user_type_field;
    private javax.swing.JList<OUsuarios> users_list;
    private javax.swing.JButton util_button;
    // End of variables declaration//GEN-END:variables

    @Override
    public JTextField getTextComponenteTable() {
        return search_field_table;
    }

    @Override
    public String getTextSearchTable() {
        return Filtros.limpiar(search_field_table.getText());
    }

    @Override
    public JTable getTable() {
        return objects_table;
    }

    @Override
    public DefaultTableModel getModel() {
        return table_model;
    }

    @Override
    public void setViewShow(int view_show) {
        this.view_show = view_show;
        String op = switch (view_show) {
            case 2:
                yield search_panel.getName();
            default:
                yield register_panel.getName();
        };
        ly.show(root_panel, op);
    }

    @Override
    public int getViewShow() {
        return view_show;
    }

    public ArrayList<String> getSelectMonths() {
        ArrayList<String> months = new ArrayList<>(12);
        for (JCheckBox i : month_paid_list) {
            if (i.isEnabled() && i.isSelected()) {
                months.add(i.getName());
            }
        }
        return months;
    }

    @Override
    public JList getList() {
        return users_list;
    }

    @Override
    public DefaultListModel<OUsuarios> getListModel() {
        return list_model;
    }

    @Override
    public JTextField getTextComponentList() {
        return search_field_list;
    }

    @Override
    public String getTextSearchList() {
        return Filtros.limpiar(search_field_list.getText());
    }

    @Override
    public void setCountElements(int i) {
        count_elements = i;
        users_list.setEnabled((i > 0));
        users_list.setSelectedIndex(0);
        count_elements_label.setText(String.valueOf(i));
    }

    @Override
    public int getCountElements() {
        return count_elements;
    }

    @Override
    public OUsuarios getObjectSearch() {
        return object_search;
    }

    @Override
    public void setObjectSearch(Objeto o) {
        object_search = (OUsuarios) o;
    }

    @Override
    public void setScreenListInfo() {

    }

    @Override
    public void setRowsData(String... info) {
        count.setText(info[0]);
        range.setText(info[1]);
        total.setText(info[2]);
    }

}
