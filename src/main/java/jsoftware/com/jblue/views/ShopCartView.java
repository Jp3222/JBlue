/*
 * Copyright (C) 2024 juan pablo campos casasanero
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
package jsoftware.com.jblue.views;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jsoftware.com.jblue.controllers.FactoryController;
import jsoftware.com.jblue.model.dto.WaterIntakesDTO;
import jsoftware.com.jblue.model.factories.TableModelFactory;
import jsoftware.com.jblue.util.GraphicsUtils;
import jsoftware.com.jblue.views.framework.DBView;
import jsoftware.com.jblue.views.framework.ListSearchViewModel;
import jsoftware.com.jutil.db.JDBMapObject;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juan pablo campos casasanero
 * @version 1.0
 */
public class ShopCartView extends DBView implements ListSearchViewModel {

    private static final long serialVersionUID = 1L;

    private final CardLayout ly;
    private final JTableModel table_model;
    private final DefaultListModel<WaterIntakesDTO> list_model;
    private WaterIntakesDTO object_search;
    private ArrayList<JCheckBox> month_paid_list;

    private int count_elements;

    /**
     * Creates new form VCaja
     */
    public ShopCartView() {
        initComponents();
        list_model = new DefaultListModel();
        table_model = TableModelFactory.getPayment();
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
        controller = FactoryController.getShopCartController(this);
//        pay_button.addActionListener(controller);
//        cancel_button.addActionListener(controller);
//        clear_button.addActionListener(controller);
//        recargos_button.addActionListener(controller);
//        other_pay_button.addActionListener(controller);
//        pay_last_button.addActionListener(controller);
//        info_button.addActionListener(controller);
//        lock_button.addActionListener(controller);
//        search_user_button.addActionListener(controller);
//        mov_book_button.addActionListener(controller);
//        //
//        all_months_buttons.addActionListener(controller);
//        //
//        search_field_list.addKeyListener(controller);
//        //
//        users_list.addMouseListener(controller);
//        //
//
//        register_payment_button.addActionListener(table_controller);
//        search_payment_buttons.addActionListener(table_controller);
//        reload_button.addActionListener(table_controller);
//        back_button.addActionListener(table_controller);
//        next_button.addActionListener(table_controller);
//        for (JCheckBox i : month_paid_list) {
//            i.addActionListener(controller);
//        }
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
        total_field.setText(null);
        cambio_field.setText(null);

        for (JCheckBox i : month_paid_list) {
            i.setSelected(false);
            i.setEnabled(true);
        }

        object_search = null;
        count_elements_label.setText("0");

        GraphicsUtils.setEnable(false,
                ene, feb, mar,
                abr, may, jun,
                jul, ago, sep,
                oct, nov, dic,
                all_months_buttons);

        GraphicsUtils.setEnable(false,
                pay_button,
                clear_button,
                cancel_button);
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
        register_payment_button = new javax.swing.JButton();
        search_payment_buttons = new javax.swing.JButton();
        search_user_button = new javax.swing.JButton();
        root_panel = new javax.swing.JPanel();
        register_panel = new javax.swing.JPanel();
        search_register_panel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search_field_list = new javax.swing.JTextField();
        count_elements_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        users_list = new javax.swing.JList<>();
        user_info_panel = new javax.swing.JPanel();
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
        payment_info_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        all_months_buttons = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        month_paid_field = new javax.swing.JTextField();
        months_panel = new javax.swing.JPanel();
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
        money_panel = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        total_field = new javax.swing.JLabel();
        mov_book_button = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        Jlabel1 = new javax.swing.JLabel();
        cambio_field = new javax.swing.JLabel();
        option_panel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        pay_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();
        clear_button = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        recargos_button = new javax.swing.JButton();
        other_pay_button = new javax.swing.JButton();
        pay_last_button = new javax.swing.JButton();
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
        total_register_field = new javax.swing.JLabel();

        setName("Inicio"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 700));
        setLayout(new java.awt.BorderLayout());

        tools_bar_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        tools_bar_panel.setLayout(new java.awt.BorderLayout(10, 10));

        lock_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/lock.png"))); // NOI18N
        lock_button.setActionCommand("lock");
        lock_button.setPreferredSize(new java.awt.Dimension(100, 30));
        lock_button.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/desbloquear.png"))); // NOI18N
        tools_bar_panel.add(lock_button, java.awt.BorderLayout.WEST);

        jPanel15.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        register_payment_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        register_payment_button.setText("Cobros");
        register_payment_button.setActionCommand("register_view");
        jPanel15.add(register_payment_button);

        search_payment_buttons.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        search_payment_buttons.setText("Pagos del dia");
        search_payment_buttons.setActionCommand("search_view");
        jPanel15.add(search_payment_buttons);

        tools_bar_panel.add(jPanel15, java.awt.BorderLayout.CENTER);

        search_user_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/search.png"))); // NOI18N
        search_user_button.setActionCommand("search_user_button");
        search_user_button.setPreferredSize(new java.awt.Dimension(100, 30));
        tools_bar_panel.add(search_user_button, java.awt.BorderLayout.EAST);

        add(tools_bar_panel, java.awt.BorderLayout.NORTH);

        root_panel.setPreferredSize(new java.awt.Dimension(900, 520));
        root_panel.setLayout(new java.awt.CardLayout(10, 10));

        register_panel.setName("register"); // NOI18N
        register_panel.setLayout(new javax.swing.BoxLayout(register_panel, javax.swing.BoxLayout.PAGE_AXIS));

        search_register_panel.setOpaque(false);
        search_register_panel.setPreferredSize(new java.awt.Dimension(700, 150));
        search_register_panel.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel1.setText("Buscador");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel6.add(jLabel1, java.awt.BorderLayout.LINE_START);

        search_field_list.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel6.add(search_field_list, java.awt.BorderLayout.CENTER);

        count_elements_label.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        count_elements_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count_elements_label.setText("0");
        count_elements_label.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel6.add(count_elements_label, java.awt.BorderLayout.EAST);

        search_register_panel.add(jPanel6, java.awt.BorderLayout.NORTH);

        users_list.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        users_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(users_list);

        search_register_panel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        register_panel.add(search_register_panel);

        user_info_panel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        user_info_panel.setLayout(new java.awt.GridLayout(3, 0, 10, 10));

        jPanel3.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel7.setText("Tipo de usuario");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel3.add(jLabel7, java.awt.BorderLayout.WEST);

        user_type_field.setEditable(false);
        user_type_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel3.add(user_type_field, java.awt.BorderLayout.CENTER);

        info_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        info_button.setText("Info de usuario");
        info_button.setActionCommand("info");
        info_button.setPreferredSize(new java.awt.Dimension(150, 29));
        jPanel3.add(info_button, java.awt.BorderLayout.EAST);

        user_info_panel.add(jPanel3);

        jPanel12.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel8.setText("Nombre");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel12.add(jLabel8, java.awt.BorderLayout.WEST);

        name_user_field.setEditable(false);
        name_user_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel12.add(name_user_field, java.awt.BorderLayout.CENTER);

        user_info_panel.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel9.setText("Tipo de toma");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel13.add(jLabel9, java.awt.BorderLayout.WEST);

        type_toma_field.setEditable(false);
        type_toma_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        type_toma_field.setPreferredSize(new java.awt.Dimension(200, 36));
        jPanel13.add(type_toma_field, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel4.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel3.setText("Costo: $");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel4.add(jLabel3, java.awt.BorderLayout.WEST);

        cost_field.setEditable(false);
        cost_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cost_field.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel4.add(cost_field, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel4, java.awt.BorderLayout.EAST);

        user_info_panel.add(jPanel13);

        register_panel.add(user_info_panel);

        payment_info_panel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        payment_info_panel.setOpaque(false);
        payment_info_panel.setPreferredSize(new java.awt.Dimension(900, 150));
        payment_info_panel.setLayout(new java.awt.GridLayout(3, 0));

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

        payment_info_panel.add(jPanel5);

        jPanel14.setPreferredSize(new java.awt.Dimension(900, 23));
        jPanel14.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel10.setText("Meses pagados");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel14.add(jLabel10, java.awt.BorderLayout.WEST);

        month_paid_field.setEditable(false);
        month_paid_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel14.add(month_paid_field, java.awt.BorderLayout.CENTER);

        payment_info_panel.add(jPanel14);

        months_panel.setPreferredSize(new java.awt.Dimension(700, 60));
        months_panel.setLayout(new java.awt.GridLayout(1, 12, 10, 10));

        ene.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        ene.setText("ENE");
        ene.setActionCommand("month");
        ene.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ene.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ene.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ene.setPreferredSize(new java.awt.Dimension(10, 47));
        ene.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        ene.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(ene);

        feb.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        feb.setText("FEB");
        feb.setActionCommand("month");
        feb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        feb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        feb.setPreferredSize(new java.awt.Dimension(10, 47));
        feb.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        feb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(feb);

        mar.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        mar.setText("MAR");
        mar.setActionCommand("month");
        mar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        mar.setPreferredSize(new java.awt.Dimension(10, 47));
        mar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        mar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(mar);

        abr.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        abr.setText("ABR");
        abr.setActionCommand("month");
        abr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        abr.setPreferredSize(new java.awt.Dimension(10, 47));
        abr.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        abr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(abr);

        may.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        may.setText("MAY");
        may.setActionCommand("month");
        may.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        may.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        may.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        may.setPreferredSize(new java.awt.Dimension(10, 47));
        may.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        may.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(may);

        jun.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jun.setText("JUN");
        jun.setActionCommand("month");
        jun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        jun.setPreferredSize(new java.awt.Dimension(10, 47));
        jun.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        jun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(jun);

        jul.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jul.setText("JUL");
        jul.setActionCommand("month");
        jul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jul.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        jul.setPreferredSize(new java.awt.Dimension(10, 47));
        jul.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        jul.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(jul);

        ago.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        ago.setText("AGO");
        ago.setActionCommand("month");
        ago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ago.setPreferredSize(new java.awt.Dimension(10, 47));
        ago.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        ago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(ago);

        sep.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        sep.setText("SEP");
        sep.setActionCommand("month");
        sep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        sep.setPreferredSize(new java.awt.Dimension(10, 47));
        sep.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        sep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(sep);

        oct.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        oct.setText("OCT");
        oct.setActionCommand("month");
        oct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        oct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        oct.setPreferredSize(new java.awt.Dimension(10, 47));
        oct.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        oct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(oct);

        nov.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        nov.setText("NOV");
        nov.setActionCommand("month");
        nov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nov.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        nov.setPreferredSize(new java.awt.Dimension(10, 47));
        nov.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        nov.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(nov);

        dic.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        dic.setText("DIC");
        dic.setActionCommand("month");
        dic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        dic.setPreferredSize(new java.awt.Dimension(10, 47));
        dic.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        dic.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        months_panel.add(dic);

        payment_info_panel.add(months_panel);

        register_panel.add(payment_info_panel);

        money_panel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        money_panel.setPreferredSize(new java.awt.Dimension(900, 100));
        money_panel.setLayout(new java.awt.GridLayout(2, 0));

        jPanel21.setOpaque(false);
        jPanel21.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Total: $");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel21.add(jLabel2, java.awt.BorderLayout.WEST);

        total_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        total_field.setText("0.0");
        jPanel21.add(total_field, java.awt.BorderLayout.CENTER);

        mov_book_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        mov_book_button.setText("Movimientos");
        mov_book_button.setToolTipText("");
        mov_book_button.setActionCommand("mov_book");
        mov_book_button.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel21.add(mov_book_button, java.awt.BorderLayout.LINE_END);

        money_panel.add(jPanel21);

        jPanel22.setOpaque(false);
        jPanel22.setLayout(new java.awt.BorderLayout(10, 0));

        Jlabel1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        Jlabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Jlabel1.setText("Cambio: $");
        Jlabel1.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel22.add(Jlabel1, java.awt.BorderLayout.LINE_START);

        cambio_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cambio_field.setText("0.0");
        jPanel22.add(cambio_field, java.awt.BorderLayout.CENTER);

        money_panel.add(jPanel22);

        register_panel.add(money_panel);

        option_panel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        option_panel.setMinimumSize(new java.awt.Dimension(450, 60));
        option_panel.setOpaque(false);
        option_panel.setPreferredSize(new java.awt.Dimension(900, 80));
        option_panel.setLayout(new java.awt.GridLayout(2, 3, 10, 10));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        pay_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        pay_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img5.png"))); // NOI18N
        pay_button.setText("Cobrar");
        pay_button.setActionCommand("payments");
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

        option_panel.add(jPanel7);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

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

        option_panel.add(jPanel8);

        register_panel.add(option_panel);

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

        total_register_field.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_register_field.setText("0");
        total_register_field.setToolTipText("Numero de pagos hechos.");
        total_register_field.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel29.add(total_register_field, java.awt.BorderLayout.LINE_END);

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
    private javax.swing.JLabel cambio_field;
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
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox jul;
    private javax.swing.JCheckBox jun;
    private javax.swing.JToggleButton lock_button;
    private javax.swing.JCheckBox mar;
    private javax.swing.JCheckBox may;
    private javax.swing.JPanel money_panel;
    private javax.swing.JTextField month_paid_field;
    private javax.swing.JPanel months_panel;
    private javax.swing.JButton mov_book_button;
    private javax.swing.JTextField name_user_field;
    private javax.swing.JButton next_button;
    private javax.swing.JCheckBox nov;
    private javax.swing.JTable objects_table;
    private javax.swing.JCheckBox oct;
    private javax.swing.JPanel option_panel;
    private javax.swing.JButton other_pay_button;
    private javax.swing.JButton pay_button;
    private javax.swing.JButton pay_last_button;
    private javax.swing.JPanel payment_info_panel;
    private javax.swing.JLabel range;
    private javax.swing.JButton recargos_button;
    private javax.swing.JPanel register_panel;
    private javax.swing.JButton register_payment_button;
    private javax.swing.JButton reload_button;
    private javax.swing.JPanel root_panel;
    private javax.swing.JTextField search_field_list;
    private javax.swing.JTextField search_field_table;
    private javax.swing.JPanel search_panel;
    private javax.swing.JButton search_payment_buttons;
    private javax.swing.JPanel search_register_panel;
    private javax.swing.JButton search_user_button;
    private javax.swing.JCheckBox sep;
    private javax.swing.JPanel status_bar_panel;
    private javax.swing.JPanel tools_bar_panel;
    private javax.swing.JLabel total_field;
    private javax.swing.JLabel total_register_field;
    private javax.swing.JTextField type_toma_field;
    private javax.swing.JPanel user_info_panel;
    private javax.swing.JTextField user_type_field;
    private javax.swing.JList<jsoftware.com.jblue.model.dto.WaterIntakesDTO> users_list;
    // End of variables declaration//GEN-END:variables

    @Override
    public JTextField getTextComponenteTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTextSearchTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JTable getTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DefaultTableModel getModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setViewShow(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getViewShow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setObjectSearch(JDBMapObject o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public WaterIntakesDTO getObjectSearch() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setRowsData(String... info) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setScreenTableInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JList getList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public JTextField getTextComponentList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTextSearchList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DefaultListModel getListModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setCountElements(int count) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getCountElements() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setScreenListInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
