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
package jsoftware.com.jblue.views.mod.com;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.controllers.viewc.ShopCartController;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.vw.WaterIntakeUserPaymentDTO;
import jsoftware.com.jblue.model.dto.wrp.ShopCartWrapperDTO;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jblue.views.framework.AbstractModuleView;
import jsoftware.com.jblue.views.framework.DBObjectValues;
import jsoftware.com.jblue.views.framework.ListSearchViewModel;
import jsoftware.com.jblue.views.framework.ShowDataModel;

/**
 *
 * @author juan pablo campos casasanero
 * @version 1.0
 */
public final class ShopCartView extends AbstractModuleView<ShopCartWrapperDTO> implements ListSearchViewModel<WaterIntakeUserDTO>, ShowDataModel, DBObjectValues<WaterIntakeUserPaymentDTO> {
    
    public static String DEFAULT_LAST_MONTH_PAID = "XX";
    public static String DEFAULT_LAST_YEAR_PAID = "XXXX";
    public static String DEFAULT_MONTHS_PAID = "0";
    
    private static final long serialVersionUID = 1L;

    /**
     * Objecto buscado en el apartado de busqueda
     */
    private WaterIntakeUserDTO object_search;
    /**
     * Modelo por defecto de la lista
     */
    private DefaultListModel<WaterIntakeUserDTO> list_model;
    private SpinnerNumberModel fiscal_year_model;
    /**
     * Lista de comboboxes
     */
    private final List<JCheckBox> list_months_paid;

    /**
     * Creates new form VCaja
     *
     * @param builder
     */
    @Deprecated
    public ShopCartView(ShopCartWrapperDTO builder) {
        super(builder);
        initComponents();
        list_model = new DefaultListModel<>();
        wki_user_list.setModel(list_model);
        list_months_paid = new ArrayList<>(12);
        list_months_paid.addAll(Arrays.asList(ene, feb, mar, abr, may, jun, jul, ago, sep, oct, nov, dic));
        build();
    }
    
    public ShopCartView(ShopCartWrapperDTO dto_wrapper, Controller controller) {
        super(dto_wrapper, controller);
        //Nueva vista
        initComponents();
        list_model = new DefaultListModel<>();
        wki_user_list.setModel(list_model);
        list_months_paid = new ArrayList<>(12);
        list_months_paid.addAll(Arrays.asList(ene, feb, mar, abr, may, jun, jul, ago, sep, oct, nov, dic));
        build();
    }
    
    @Override
    public void build() {
        components();
        events();
        initialState();
        finalState();
    }
    
    @Override
    public void components() {
        LocalDate date = LocalDate.now();
        fiscal_year_model = new SpinnerNumberModel(date.getYear(), date.getYear() - 5, date.getYear() + 5, 1);
        jSpinner1.setModel(fiscal_year_model);
    }
    
    @Override
    public void events() {
        ShopCartController con = (ShopCartController) controller;
        if (Func.isNotNull(con)) {
            search_field_list.addKeyListener(con);
            wki_user_list.addMouseListener(con);
        }
    }
    
    @Override
    public void initialState() {
        jSpinner1.setValue(LocalDate.now().getYear());
        all_months_button.setEnabled(false);
        for (JCheckBox i : list_months_paid) {
            i.setEnabled(false);
        }
        user_name_field.setText(null);
        user_type_field.setText(null);
        water_intake_type.setText(null);
        cost_field.setText(null);
        user_name_field.setText(null);
        total_field.setText("0.00");
        cambio_field.setText("0.00");
        months_paid_field.setText(DEFAULT_MONTHS_PAID);
        last_month_paid_field.setText(DEFAULT_LAST_MONTH_PAID);
        last_year_paid_field.setText(DEFAULT_LAST_YEAR_PAID);
        search_field_list.setText(null);
        DefaultListModel<WaterIntakeUserDTO> model = (DefaultListModel<WaterIntakeUserDTO>) wki_user_list.getModel();
        model.removeAllElements();
    }
    
    @Override
    public void finalState() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        root_panel = new javax.swing.JPanel();
        register_panel = new javax.swing.JPanel();
        search_register_panel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        count_elements_label = new javax.swing.JLabel();
        search_field_list = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        wki_user_list = new javax.swing.JList<>();
        user_info_panel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        user_name_field = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        user_type_field = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        water_intake_type = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cost_field = new javax.swing.JTextField();
        payment_info_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        all_months_button = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jPanel14 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        months_paid_field = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        last_month_paid_field = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        last_year_paid_field = new javax.swing.JTextField();
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

        jPanel11.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel1.setText("Buscador");
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 20));
        jPanel11.add(jLabel1);

        count_elements_label.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        count_elements_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count_elements_label.setText("0");
        count_elements_label.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel11.add(count_elements_label);

        jPanel6.add(jPanel11, java.awt.BorderLayout.WEST);

        search_field_list.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel6.add(search_field_list, java.awt.BorderLayout.CENTER);

        jButton1.setText("Buscar");
        jPanel6.add(jButton1, java.awt.BorderLayout.LINE_END);

        search_register_panel.add(jPanel6, java.awt.BorderLayout.NORTH);

        wki_user_list.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        wki_user_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(wki_user_list);

        search_register_panel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        register_panel.add(search_register_panel);

        user_info_panel.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        user_info_panel.setLayout(new java.awt.GridLayout(3, 0, 10, 10));

        jPanel12.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel8.setText("Usuario");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel12.add(jLabel8, java.awt.BorderLayout.WEST);

        user_name_field.setEditable(false);
        user_name_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel12.add(user_name_field, java.awt.BorderLayout.CENTER);

        user_info_panel.add(jPanel12);

        jPanel3.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel7.setText("Tipo de usuario");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel3.add(jLabel7, java.awt.BorderLayout.WEST);

        user_type_field.setEditable(false);
        user_type_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel3.add(user_type_field, java.awt.BorderLayout.CENTER);

        user_info_panel.add(jPanel3);

        jPanel13.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel9.setText("Tipo de toma");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel13.add(jLabel9, java.awt.BorderLayout.WEST);

        water_intake_type.setEditable(false);
        water_intake_type.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        water_intake_type.setPreferredSize(new java.awt.Dimension(200, 36));
        jPanel13.add(water_intake_type, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel4.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel3.setText("Cuota: $");
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

        all_months_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        all_months_button.setText("Todos");
        all_months_button.setActionCommand("all_months");
        all_months_button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        all_months_button.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(all_months_button, java.awt.BorderLayout.EAST);

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Mese a pagar");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel5.add(jLabel6, java.awt.BorderLayout.CENTER);
        jPanel5.add(jSpinner1, java.awt.BorderLayout.LINE_START);

        payment_info_panel.add(jPanel5);

        jPanel14.setPreferredSize(new java.awt.Dimension(900, 23));
        jPanel14.setLayout(new java.awt.GridLayout(1, 0, 10, 100));

        jPanel2.setLayout(new java.awt.BorderLayout());

        months_paid_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        months_paid_field.setText("0");
        jPanel2.add(months_paid_field, java.awt.BorderLayout.CENTER);

        jLabel10.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel10.setText("Meses Pagados: ");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel2.add(jLabel10, java.awt.BorderLayout.WEST);

        jPanel14.add(jPanel2);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel12.setText("Ultimos mes pagado: ");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel10.add(jLabel12, java.awt.BorderLayout.WEST);

        last_month_paid_field.setText("XX");
        jPanel10.add(last_month_paid_field, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel10);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel13.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel13.setText("Ultimo año pagado: ");
        jLabel13.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel9.add(jLabel13, java.awt.BorderLayout.WEST);

        last_year_paid_field.setText("XXXX");
        jPanel9.add(last_year_paid_field, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel9);

        payment_info_panel.add(jPanel14);

        months_panel.setPreferredSize(new java.awt.Dimension(700, 60));
        months_panel.setLayout(new java.awt.GridLayout(1, 12, 10, 10));

        ene.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        ene.setText("ENE");
        ene.setActionCommand("month");
        ene.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ene.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ene.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ene.setName("ENE"); // NOI18N
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
        feb.setName("FEB"); // NOI18N
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
        mar.setName("MAR"); // NOI18N
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
        abr.setName("ABR"); // NOI18N
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
        may.setName("MAY"); // NOI18N
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
        jun.setName("JUN"); // NOI18N
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
        jul.setName("JUL"); // NOI18N
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
        ago.setName("AGO"); // NOI18N
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
        sep.setName("SEP"); // NOI18N
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
        oct.setName("OCT"); // NOI18N
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
        nov.setName("NOV"); // NOI18N
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
        dic.setName("DIC"); // NOI18N
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

        total_field.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
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

        cambio_field.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        cambio_field.setText("0.0");
        jPanel22.add(cambio_field, java.awt.BorderLayout.CENTER);

        money_panel.add(jPanel22);

        register_panel.add(money_panel);

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
    private javax.swing.JCheckBox all_months_button;
    private javax.swing.JButton back_button;
    private javax.swing.JLabel cambio_field;
    private javax.swing.JTextField cost_field;
    private javax.swing.JLabel count;
    private javax.swing.JLabel count_elements_label;
    private javax.swing.JCheckBox dic;
    private javax.swing.JCheckBox ene;
    private javax.swing.JCheckBox feb;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JCheckBox jul;
    private javax.swing.JCheckBox jun;
    private javax.swing.JTextField last_month_paid_field;
    private javax.swing.JTextField last_year_paid_field;
    private javax.swing.JCheckBox mar;
    private javax.swing.JCheckBox may;
    private javax.swing.JPanel money_panel;
    private javax.swing.JTextField months_paid_field;
    private javax.swing.JPanel months_panel;
    private javax.swing.JButton mov_book_button;
    private javax.swing.JButton next_button;
    private javax.swing.JCheckBox nov;
    private javax.swing.JTable objects_table;
    private javax.swing.JCheckBox oct;
    private javax.swing.JPanel payment_info_panel;
    private javax.swing.JLabel range;
    private javax.swing.JPanel register_panel;
    private javax.swing.JButton reload_button;
    private javax.swing.JPanel root_panel;
    private javax.swing.JTextField search_field_list;
    private javax.swing.JTextField search_field_table;
    private javax.swing.JPanel search_panel;
    private javax.swing.JPanel search_register_panel;
    private javax.swing.JCheckBox sep;
    private javax.swing.JPanel status_bar_panel;
    private javax.swing.JLabel total_field;
    private javax.swing.JLabel total_register_field;
    private javax.swing.JPanel user_info_panel;
    private javax.swing.JTextField user_name_field;
    private javax.swing.JTextField user_type_field;
    private javax.swing.JTextField water_intake_type;
    private javax.swing.JList<WaterIntakeUserDTO> wki_user_list;
    // End of variables declaration//GEN-END:variables
    public static final int MONTHS_PAID = 0;
    public static final int MONTHS_NOT_PAID = 1;
    public static final int SELECTED_MONTHS = 2;
    
    @Override
    public void getData() {
        boolean res = isValuesOK();
        if (!res) {
            return;
        }
        dto_wrapper.setMonth_list_valid(res);
        dto_wrapper.setWki_usr_pym(getValues(true));
        System.out.println(dto_wrapper.getWki_usr_pym().toString());
    }
    
    public List<JCheckBox> getListMonth(int option) {
        return switch (option) {
            case ShopCartView.MONTHS_PAID ->
                list_months_paid
                .stream()
                .filter((t) -> t.isEnabled() && t.isSelected())
                .toList();
            case ShopCartView.MONTHS_NOT_PAID ->
                list_months_paid
                .stream()
                .filter((t) -> t.isEnabled() && !t.isSelected())
                .toList();
            case ShopCartView.SELECTED_MONTHS ->
                list_months_paid
                .stream()
                .filter((t) -> t.isEnabled() && t.isSelected())
                .toList();
            default ->
                list_months_paid;
        };
    }
    
    @Override
    public JList<WaterIntakeUserDTO> getList() {
        return wki_user_list;
    }
    
    @Override
    public JTextField getTextComponentList() {
        return search_field_list;
    }
    
    @Override
    public String getTextSearchList() {
        return search_field_list.getText().trim().replace(" ", "_").toUpperCase();
    }
    
    @Override
    public DefaultListModel<WaterIntakeUserDTO> getListModel() {
        return list_model;
    }
    
    @Override
    public void setCountElements(int count) {
        count_elements_label.setText(String.valueOf(count));
    }
    
    @Override
    public int getCountElements() {
        return Integer.parseInt(count_elements_label.getText());
    }
    
    @Override
    public void setScreenListInfo() {
        
    }
    
    @Override
    public WaterIntakeUserDTO getObjectSearch() {
        return object_search;
    }
    
    @Override
    public void setObjectSearch(WaterIntakeUserDTO object) {
        object_search = object;
    }
    
    @Override
    public void showData() {
        ShopCartWrapperDTO wrp = getDtoWrapper();
        if (wrp == null) {
            return;
        }
        WaterIntakeUserPaymentDTO dto = wrp.getWki_usr_pym();
        if (dto == null) {
            return;
        }
        user_name_field.setText(dto.getNombre_usuario());
        user_type_field.setText(dto.getTipo_usuario());
        water_intake_type.setText(dto.getTipo_toma());
        cost_field.setText(dto.getCuota().toString());
        
        last_month_paid_field.setText(dto.getUlt_mes_pago() == -1 ? DEFAULT_LAST_MONTH_PAID : String.valueOf(dto.getUlt_mes_pago()));
        last_year_paid_field.setText(dto.getUlt_año_pago() == -1 ? DEFAULT_LAST_YEAR_PAID : String.valueOf(dto.getUlt_año_pago()));
        months_paid_field.setText(String.valueOf(dto.getMeses_pagados()));
        //SI LA PLANILLA DE PAGO YA ESTA PAGADA, SE PASA AL SIGUIENTE AÑO
        if (dto.getStatus_pagos() == 7 || dto.getMeses_pagados() == 12 || dto.getUlt_año_pago() == 12) {
            fiscal_year_model.setMinimum(dto.getUlt_año_pago() + 1);
            fiscal_year_model.setValue(dto.getUlt_año_pago() + 1);
            //
            last_month_paid_field.setText(DEFAULT_LAST_MONTH_PAID);
            last_year_paid_field.setText(String.valueOf(dto.getUlt_año_pago()));
            months_paid_field.setText("0");
            for (JCheckBox i : list_months_paid) {
                i.setEnabled(true);
            }
            return;
        }
        // Estados previos de selección según DTO
        ene.setSelected(dto.isEne());
        feb.setSelected(dto.isFeb());
        mar.setSelected(dto.isMar());
        abr.setSelected(dto.isAbr());
        may.setSelected(dto.isMay());
        jun.setSelected(dto.isJun());
        jul.setSelected(dto.isJul());
        ago.setSelected(dto.isAgo());
        sep.setSelected(dto.isSep());
        oct.setSelected(dto.isOct());
        nov.setSelected(dto.isNov());
        dic.setSelected(dto.isDic());

        // OBTENEMOS LA FECHA DE REGISTRO DE LA TOMA
        LocalDateTime registrationDate = dto.getDate_register();
        int regYear = (registrationDate != null) ? registrationDate.getYear() : 0;
        int regMonth = (registrationDate != null) ? registrationDate.getMonthValue() : 1;

        // OBTENEMOS EL AÑO FISCAL SELECCIONADO EN EL SPINNER (ej. 2026)
        int selectedFiscalYear = (int) jSpinner1.getValue();

        // RECORREMOS CADA CHECKBOX DE MES (índice 0 = Ene [1], 1 = Feb [2], ..., 11 = Dic [12])
        for (int i = 0; i < list_months_paid.size(); i++) {
            JCheckBox checkBox = list_months_paid.get(i);
            int monthNumber = i + 1; // 1 para Ene, 2 para Feb, etc.

            boolean isEnabled = true;

            // REGLA 1: Si el año fiscal seleccionado es ANTERIOR al año de registro, se bloquea todo el año
            if (selectedFiscalYear < regYear) {
                isEnabled = false;
            } // REGLA 2: Si es el AÑO DE REGISTRO, solo se habilitan los meses EQUIVALENTES o POSTERIORES al mes de alta
            else if (selectedFiscalYear == regYear) {
                if (monthNumber < regMonth) {
                    isEnabled = false; // Meses anteriores al alta no aplican
                }
            }
            // REGLA 3: Si ya está pagado previamente (según tu DTO), usualmente se bloquea para evitar doble pago
            // (Puedes ajustar esta condición si permites re-pagar o corregir)
            if (checkBox.isSelected()) {
                // Si prefieres que lo ya pagado no se pueda desmarcar o modificar directamente aquí:
                isEnabled = false;
            }
            checkBox.setEnabled(isEnabled);
        }
    }
    
    @Override
    public boolean isValuesOK() {
        boolean res;
        List<JCheckBox> listMonth = getListMonth(SELECTED_MONTHS);
        res = listMonth.isEmpty();
        if (res) {
            JOptionPane.showMessageDialog(this, "SELECCIONE UN MES");
        }
        res = validateLinearMonthSelection(listMonth);
        return res;
    }

    /**
     * Verifica que una lista de estados de meses (true = seleccionado, false =
     * no seleccionado) mantenga una secuencia estrictamente lineal, sin huecos
     * (meses intermedios desmarcados).
     *
     * @param selectedMonths Lista ordenada de 12 elementos (Enero a Diciembre)
     * indicando si están seleccionados.
     * @return true si la selección es lineal/continua, false si hay "huecos" o
     * si se intenta pagar un mes futuro saltándose pendientes.
     */
    public boolean validateLinearMonthSelection(List<JCheckBox> selectedMonths) {
        if (selectedMonths == null || selectedMonths.isEmpty()) {
            return true;
        }
        
        boolean foundFirstSelected = false;
        boolean foundGapAfterStart = false;
        
        for (JCheckBox i : selectedMonths) {
            //SE EVALUAN SOLO LOS MESES HABILITADOS
            if (i.isEnabled()) {
                boolean isSelected = i.isSelected();
                if (isSelected) {
                    // Si ya habíamos encontrado un hueco después de haber empezado a seleccionar, la selección es inválida
                    if (foundGapAfterStart) {
                        return false;
                    }
                    foundFirstSelected = true;
                } else {
                    // Si ya encontramos al menos un mes seleccionado, y ahora encontramos uno falso, 
                    // marcamos que ha aparecido un "hueco"
                    if (foundFirstSelected) {
                        foundGapAfterStart = true;
                    }
                }
                
            }
        }
        
        return true;
    }
    
    @Override
    public WaterIntakeUserPaymentDTO getValues(boolean update) {
        ShopCartWrapperDTO wrp = getDtoWrapper();
        WaterIntakeUserPaymentDTO dto = wrp.getWki_usr_pym();
        dto.setUlt_año_pago(Integer.parseInt(jSpinner1.getValue().toString()));
        dto.setEne(ene.isSelected());
        dto.setFeb(feb.isSelected());
        dto.setMar(mar.isSelected());
        dto.setAbr(abr.isSelected());
        dto.setMay(may.isSelected());
        dto.setJun(jun.isSelected());
        dto.setJul(jul.isSelected());
        dto.setAgo(ago.isSelected());
        dto.setSep(sep.isSelected());
        dto.setOct(oct.isSelected());
        dto.setNov(nov.isSelected());
        dto.setDic(dic.isSelected());
        for (JCheckBox i : list_months_paid) {
            if (i.isEnabled() && i.isSelected()) {
                wrp.getMonths().add(get(i.getText()));
            }
        }
        return dto;
    }
    
    public int get(String var) {
        switch (var.toUpperCase()) {
            case "ENE" -> {
                return 1;
            }
            case "FEB" -> {
                return 2;
            }
            case "MAR" -> {
                return 3;
            }
            case "ABR" -> {
                return 4;
            }
            case "MAY" -> {
                return 5;
            }
            case "JUN" -> {
                return 6;
            }
            case "JUL" -> {
                return 7;
            }
            case "AGO" -> {
                return 8;
            }
            case "SEP" -> {
                return 9;
            }
            case "OCT" -> {
                return 10;
            }
            case "NOV" -> {
                return 11;
            }
            case "DIC" -> {
                return 12;
            }
            default ->
                throw new AssertionError();
        }
    }
    
}
