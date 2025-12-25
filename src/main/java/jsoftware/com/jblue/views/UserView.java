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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import jsoftware.com.jblue.controllers.FactoryController;
import jsoftware.com.jblue.controllers.compc.ComboBoxController;
import jsoftware.com.jblue.controllers.compc.TableController;
import jsoftware.com.jblue.model.dao.StreetDAO;
import jsoftware.com.jblue.model.dao.UserDAO2;
import jsoftware.com.jblue.model.dao.WaterIntakeTypeDAO;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypesDTO;
import jsoftware.com.jblue.model.factories.TableModelFactory;
import jsoftware.com.jblue.util.Filters;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.util.GraphicsUtils;
import jsoftware.com.jblue.views.framework.AbstractProcessView;
import jsoftware.com.jblue.views.framework.DBObjectValues;
import jsoftware.com.jutil.db.JDBMapObject;
import jsoftware.com.jutil.swingw.modelos.JTableModel;
import jsoftware.com.jutil.util.Func;

/**
 *
 * @author juan pablo campos casasanero
 */
public final class UserView extends AbstractProcessView<UserDTO> implements DBObjectValues<UserDTO> {

    private static final long serialVersionUID = 1L;

    private CardLayout ly;
    private final JTableModel model;
    private UserDTO object_search;
    private String user_key;
    private ComboBoxController<StreetDTO> combo_box1;
    private ComboBoxController<StreetDTO> combo_box2;
    private ComboBoxController<WaterIntakeTypesDTO> combo_box3;

    /**
     * Creates new form NewUsuarios
     */
    public UserView(AbstractProcessView.ProcessViewBuilder builder) {
        super(builder);
        this.initComponents();
        initComponents();
        controller = FactoryController.getUserController(this);
        table_controller = new TableController<>(this, new UserDAO2(true, getProcessName()));
        model = TableModelFactory.getUserTableModel();
        objects_table.setModel(model);
        ly = (CardLayout) root_panel.getLayout();
        ly.show(root_panel, register_panel.getName());
        combo_box1 = new ComboBoxController<>(street1_field, new StreetDAO(true, getProcessName()));
        combo_box2 = new ComboBoxController<>(street2_field, new StreetDAO(true, getProcessName()));
        combo_box3 = new ComboBoxController<>(water_intakes_type_field, new WaterIntakeTypeDAO(true, getProcessName()));
        combo_box1.loadData();
        combo_box2.loadData();
        combo_box3.loadData();
        build();
    }

    @Override
    public void build() {
        components();
        events();
        finalState();
        initialState();
    }

    @Override
    public void events() {
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);

        objects_table.addMouseListener(table_controller);
        //
        save_button.addActionListener(controller);
        update_button.addActionListener(controller);
        delete_button.addActionListener(controller);
        cancel_button.addActionListener(controller);
        search_object.addActionListener(controller);
        //
        back_button.addActionListener(table_controller);
        next_button.addActionListener(table_controller);
        reload_button.addActionListener(table_controller);
        //
        register_button.addActionListener(table_controller);
        search_button.addActionListener(table_controller);
        add_file_button.addActionListener(controller);
        add_photo_button.addActionListener(controller);
    }

    @Override
    public void components() {
    }

    @Override
    public void initialState() {
        // Campos ya existentes
        first_name_field.setText(null);
        last_name1_field.setText(null);
        last_name2_field.setText(null);
        inside_number_field.setText(null);
        street1_field.setSelectedIndex(0);
        water_intakes_type_field.setSelectedIndex(0);
        user_status_field.setSelectedIndex(0);
        // --- Campos añadidos ---
        // Suponiendo que tienes un campo de texto para el ID y la CURP
        curp_field.setText(null);
        // Suponiendo que tienes campos de texto para el email y los números de teléfono
        email_field.setText(null);
        phone_number1_field.setText(null);
        phone_number2_field.setText(null);

        // Suponiendo que tienes campos de texto para la calle 2 y el número exterior
        street2_field.setSelectedIndex(0);
        outside_number_field.setText(null);
        date_last_update_field.setText(null);
        date_register_field.setText(null);

        view_show = 1;
        object_search = null;
        man_tipo_toma.setSelected(false);
        man_calle.setSelected(false);
        man_estado.setSelected(false);
        buttonGroup1.setSelected(jRadioButton1.getModel(), true);
        save_button.setEnabled(true);
        GraphicsUtils.setEnable(false,
                update_button,
                delete_button,
                add_consumer_button,
                show_consumer_list_button,
                add_file_button,
                add_photo_button
        );

    }

    @Override
    public void finalState() {
        if (getProcessId().equals("1")) {
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
        } else {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(true);
        }
        user_status_field.setSelectedIndex(1);
        System.out.println("ES UN PROCESO?: " + isProcess() + " - " + getProcessId());
        if (isProcess()) {
            this.remove(north_panel);
            user_data_panel.remove(p_user_type);
            user_data_panel.remove(p_date_update);
            user_data_panel.remove(p_date_register);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        north_panel = new javax.swing.JPanel();
        np_cp_center = new javax.swing.JPanel();
        register_button = new javax.swing.JButton();
        search_button = new javax.swing.JButton();
        np_cp_west = new javax.swing.JPanel();
        search_object = new javax.swing.JButton();
        np_cp_east = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        root_panel = new javax.swing.JPanel();
        register_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        user_data_panel = new javax.swing.JPanel();
        p_user_type = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        pc_nombre = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        first_name_field = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pc_ap = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        last_name1_field = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pc_am = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        last_name2_field = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        gender_field = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        pc_tipo_toma = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        water_intakes_type_field = new javax.swing.JComboBox<>();
        man_tipo_toma = new javax.swing.JCheckBox();
        pc_calle = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        street1_field = new javax.swing.JComboBox<>();
        man_calle = new javax.swing.JCheckBox();
        pc_ncasa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inside_number_field = new javax.swing.JTextField();
        sn_numero = new javax.swing.JCheckBox();
        p_status = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        user_status_field = new javax.swing.JComboBox<>();
        man_estado = new javax.swing.JCheckBox();
        p_date_update = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        man_estado1 = new javax.swing.JCheckBox();
        date_last_update_field = new javax.swing.JTextField();
        p_date_register = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        man_estado2 = new javax.swing.JCheckBox();
        date_register_field = new javax.swing.JTextField();
        complement_data_panel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        curp_field = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        email_field = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        phone_number1_field = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        phone_number2_field = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        street2_field = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        outside_number_field = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        date_birday_field = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        add_photo_button = new javax.swing.JButton();
        add_file_button = new javax.swing.JButton();
        add_consumer_button = new javax.swing.JButton();
        show_consumer_list_button = new javax.swing.JButton();
        option_panel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        save_button = new javax.swing.JButton();
        update_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();
        search_panel = new javax.swing.JPanel();
        panel_filtros = new javax.swing.JPanel();
        pf_bar_super = new javax.swing.JPanel();
        filtro_quitar = new javax.swing.JButton();
        filtros = new javax.swing.JCheckBox();
        pf_filtros = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        street_filter = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        water_intakes_filter = new javax.swing.JComboBox<>();
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
        table_panel = new javax.swing.JPanel();
        search_items_table = new javax.swing.JPanel();
        search_field = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        back_button = new javax.swing.JButton();
        next_button = new javax.swing.JButton();
        reload_button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        objects_table = new javax.swing.JTable();
        status_bar_panel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        count_label = new javax.swing.JLabel();
        count_field = new javax.swing.JLabel();
        range_field = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        total_label = new javax.swing.JLabel();
        total_field = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(450, 350));
        setName("Usuarios"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 700));
        setLayout(new java.awt.BorderLayout(5, 5));

        north_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        north_panel.setLayout(new java.awt.BorderLayout(10, 10));

        np_cp_center.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        register_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        register_button.setText("Registar");
        register_button.setActionCommand("register_view");
        np_cp_center.add(register_button);

        search_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        search_button.setText("Consultar");
        search_button.setActionCommand("search_view");
        np_cp_center.add(search_button);

        north_panel.add(np_cp_center, java.awt.BorderLayout.CENTER);

        np_cp_west.setPreferredSize(new java.awt.Dimension(100, 30));
        np_cp_west.setLayout(new java.awt.BorderLayout());

        search_object.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/search.png"))); // NOI18N
        search_object.setActionCommand("search_object");
        np_cp_west.add(search_object, java.awt.BorderLayout.CENTER);

        north_panel.add(np_cp_west, java.awt.BorderLayout.WEST);

        np_cp_east.setPreferredSize(new java.awt.Dimension(100, 30));
        np_cp_east.setLayout(new java.awt.BorderLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/configuraciones.png"))); // NOI18N
        np_cp_east.add(jButton3, java.awt.BorderLayout.CENTER);

        north_panel.add(np_cp_east, java.awt.BorderLayout.EAST);

        add(north_panel, java.awt.BorderLayout.NORTH);

        root_panel.setPreferredSize(new java.awt.Dimension(900, 700));
        root_panel.setLayout(new java.awt.CardLayout(10, 10));

        register_panel.setName("register"); // NOI18N
        register_panel.setLayout(new java.awt.BorderLayout());

        user_data_panel.setPreferredSize(new java.awt.Dimension(500, 600));
        user_data_panel.setLayout(new java.awt.GridLayout(11, 1, 0, 10));

        p_user_type.setLayout(new java.awt.GridLayout(1, 2));

        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Titular");
        jRadioButton1.setActionCommand("1");
        jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p_user_type.add(jRadioButton1);

        jRadioButton2.setText("Afiliado");
        jRadioButton2.setActionCommand("2");
        jRadioButton2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p_user_type.add(jRadioButton2);

        user_data_panel.add(p_user_type);

        pc_nombre.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel2.setText("Nombre: ");
        jLabel2.setMaximumSize(new java.awt.Dimension(60, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_nombre.add(jLabel2, java.awt.BorderLayout.WEST);

        first_name_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        first_name_field.setToolTipText("<html>\nCampo: Nombre\n<br>valores admitidos: Solo texto\n<br>tamaño maximo: 32 Caracteres");
        first_name_field.setName("Nombre"); // NOI18N
        first_name_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_nombre.add(first_name_field, java.awt.BorderLayout.CENTER);

        jLabel7.setPreferredSize(new java.awt.Dimension(60, 30));
        pc_nombre.add(jLabel7, java.awt.BorderLayout.LINE_END);

        user_data_panel.add(pc_nombre);

        pc_ap.setPreferredSize(new java.awt.Dimension(250, 30));
        pc_ap.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel3.setText("A. Paterno: ");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_ap.add(jLabel3, java.awt.BorderLayout.WEST);

        last_name1_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        last_name1_field.setToolTipText("<html>\nCampos: Apellido Paterno\n<br>Valor: Solo texto \n<br>Longitud: 32 Caracteres");
        last_name1_field.setName("A. Paterno"); // NOI18N
        last_name1_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_ap.add(last_name1_field, java.awt.BorderLayout.CENTER);

        jLabel9.setPreferredSize(new java.awt.Dimension(60, 30));
        pc_ap.add(jLabel9, java.awt.BorderLayout.LINE_END);

        user_data_panel.add(pc_ap);

        pc_am.setPreferredSize(new java.awt.Dimension(250, 30));
        pc_am.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel4.setText("A. Materno:");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_am.add(jLabel4, java.awt.BorderLayout.WEST);

        last_name2_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        last_name2_field.setToolTipText("<html> Campos: Apellido Materno\n<br>Valor: Solo texto <br>Longitud: 32 Caracteres");
        last_name2_field.setName("A. Materno"); // NOI18N
        last_name2_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_am.add(last_name2_field, java.awt.BorderLayout.CENTER);

        jLabel10.setPreferredSize(new java.awt.Dimension(60, 30));
        pc_am.add(jLabel10, java.awt.BorderLayout.LINE_END);

        user_data_panel.add(pc_am);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel25.setText("Genero");
        jLabel25.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel8.add(jLabel25, java.awt.BorderLayout.LINE_START);

        gender_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No. Definido", "Masculino.", "Femenino." }));
        gender_field.setName("Genero"); // NOI18N
        jPanel8.add(gender_field, java.awt.BorderLayout.CENTER);

        jLabel26.setPreferredSize(new java.awt.Dimension(60, 30));
        jPanel8.add(jLabel26, java.awt.BorderLayout.LINE_END);

        user_data_panel.add(jPanel8);

        pc_tipo_toma.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_tipo_toma.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel5.setText("T. Toma");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_tipo_toma.add(jLabel5, java.awt.BorderLayout.WEST);

        water_intakes_type_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        water_intakes_type_field.setName("Tipo de toma"); // NOI18N
        water_intakes_type_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_tipo_toma.add(water_intakes_type_field, java.awt.BorderLayout.CENTER);

        man_tipo_toma.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        man_tipo_toma.setText("M.");
        man_tipo_toma.setToolTipText("Mantener el tipo de toma seleccionado");
        man_tipo_toma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_tipo_toma.setPreferredSize(new java.awt.Dimension(60, 30));
        pc_tipo_toma.add(man_tipo_toma, java.awt.BorderLayout.EAST);

        user_data_panel.add(pc_tipo_toma);

        pc_calle.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_calle.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel6.setText("Calle: ");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_calle.add(jLabel6, java.awt.BorderLayout.WEST);

        street1_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        street1_field.setName("Calle"); // NOI18N
        street1_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_calle.add(street1_field, java.awt.BorderLayout.CENTER);

        man_calle.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        man_calle.setText("M.");
        man_calle.setToolTipText("Mantener la calle seleccionada\n");
        man_calle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_calle.setPreferredSize(new java.awt.Dimension(60, 30));
        pc_calle.add(man_calle, java.awt.BorderLayout.EAST);

        user_data_panel.add(pc_calle);

        pc_ncasa.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_ncasa.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel1.setText("Numero Interior");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_ncasa.add(jLabel1, java.awt.BorderLayout.WEST);

        inside_number_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        inside_number_field.setToolTipText("<html> Campo: Numero de casa  <br>Valor: Solo numeros <br>Longitud: 3 Caracteres");
        inside_number_field.setName("Numero Interior"); // NOI18N
        inside_number_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_ncasa.add(inside_number_field, java.awt.BorderLayout.CENTER);

        sn_numero.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        sn_numero.setText("S/N");
        sn_numero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sn_numero.setPreferredSize(new java.awt.Dimension(60, 30));
        pc_ncasa.add(sn_numero, java.awt.BorderLayout.EAST);

        user_data_panel.add(pc_ncasa);

        p_status.setPreferredSize(new java.awt.Dimension(500, 50));
        p_status.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel8.setText("Estado: ");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 25));
        p_status.add(jLabel8, java.awt.BorderLayout.WEST);

        user_status_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        user_status_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Elemento.", "Activo.", "Inactivo." }));
        user_status_field.setName("Estado"); // NOI18N
        user_status_field.setPreferredSize(new java.awt.Dimension(100, 30));
        p_status.add(user_status_field, java.awt.BorderLayout.CENTER);

        man_estado.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        man_estado.setText("M.");
        man_estado.setToolTipText("Mantener el estado del usuario seleccionado\n");
        man_estado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_estado.setPreferredSize(new java.awt.Dimension(60, 30));
        p_status.add(man_estado, java.awt.BorderLayout.EAST);

        user_data_panel.add(p_status);

        p_date_update.setPreferredSize(new java.awt.Dimension(500, 50));
        p_date_update.setLayout(new java.awt.BorderLayout());

        jLabel23.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel23.setText("F. de actualizacion");
        jLabel23.setPreferredSize(new java.awt.Dimension(150, 25));
        p_date_update.add(jLabel23, java.awt.BorderLayout.WEST);

        man_estado1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        man_estado1.setText("M.");
        man_estado1.setToolTipText("Mantener el estado del usuario seleccionado\n");
        man_estado1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_estado1.setPreferredSize(new java.awt.Dimension(60, 30));
        p_date_update.add(man_estado1, java.awt.BorderLayout.EAST);

        date_last_update_field.setEditable(false);
        date_last_update_field.setName("Fecha de Actualizacion"); // NOI18N
        p_date_update.add(date_last_update_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(p_date_update);

        p_date_register.setPreferredSize(new java.awt.Dimension(500, 50));
        p_date_register.setLayout(new java.awt.BorderLayout());

        jLabel24.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel24.setText("F. de registro");
        jLabel24.setPreferredSize(new java.awt.Dimension(150, 25));
        p_date_register.add(jLabel24, java.awt.BorderLayout.WEST);

        man_estado2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        man_estado2.setText("M.");
        man_estado2.setToolTipText("Mantener el estado del usuario seleccionado\n");
        man_estado2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_estado2.setPreferredSize(new java.awt.Dimension(60, 30));
        p_date_register.add(man_estado2, java.awt.BorderLayout.EAST);

        date_register_field.setEditable(false);
        date_register_field.setName("Fecha de Registro"); // NOI18N
        p_date_register.add(date_register_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(p_date_register);

        jTabbedPane1.addTab("Datos de usuario", user_data_panel);

        complement_data_panel.setLayout(new java.awt.GridLayout(11, 1, 10, 10));

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel22.setText("CURP");
        jLabel22.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel7.add(jLabel22, java.awt.BorderLayout.WEST);

        curp_field.setName("CURP"); // NOI18N
        jPanel7.add(curp_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel7);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Email");
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel2.add(jLabel11, java.awt.BorderLayout.WEST);

        email_field.setName("Correo Electronico"); // NOI18N
        jPanel2.add(email_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel14.setText("Telefono 1: ");
        jLabel14.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel3.add(jLabel14, java.awt.BorderLayout.WEST);

        phone_number1_field.setName("Telefono 1"); // NOI18N
        jPanel3.add(phone_number1_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel19.setText("Telefono 2:");
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel4.add(jLabel19, java.awt.BorderLayout.WEST);

        phone_number2_field.setName("Telefono 2"); // NOI18N
        jPanel4.add(phone_number2_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel20.setText("Calle 2:");
        jLabel20.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel5.add(jLabel20, java.awt.BorderLayout.WEST);

        street2_field.setName("Calle 2"); // NOI18N
        jPanel5.add(street2_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel21.setText("Numero exterior");
        jLabel21.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel6.add(jLabel21, java.awt.BorderLayout.WEST);

        outside_number_field.setName("Numero Exterior"); // NOI18N
        jPanel6.add(outside_number_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel6);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel27.setText("F. de nacimiento");
        jLabel27.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel9.add(jLabel27, java.awt.BorderLayout.WEST);

        date_birday_field.setName("Numero Exterior"); // NOI18N
        jPanel9.add(date_birday_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel9);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        add_photo_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/agregar-archivo.png"))); // NOI18N
        add_photo_button.setText("Foto");
        add_photo_button.setActionCommand("add_photo");
        jPanel1.add(add_photo_button);

        add_file_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/agregar-archivo.png"))); // NOI18N
        add_file_button.setText("Documento");
        add_file_button.setActionCommand("add_file");
        jPanel1.add(add_file_button);

        complement_data_panel.add(jPanel1);

        add_consumer_button.setText("Añadir Consumidor");
        complement_data_panel.add(add_consumer_button);

        show_consumer_list_button.setText("Consumidores");
        complement_data_panel.add(show_consumer_list_button);

        jTabbedPane1.addTab("Informacion complementaria.", complement_data_panel);

        register_panel.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        option_panel.setLayout(new java.awt.GridLayout(2, 0, 10, 10));

        jPanel13.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel13.setLayout(new java.awt.GridLayout(1, 3, 10, 10));

        save_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        save_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/disquete.png"))); // NOI18N
        save_button.setText("Guardar");
        save_button.setActionCommand("save");
        save_button.setPreferredSize(new java.awt.Dimension(166, 40));
        jPanel13.add(save_button);

        update_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        update_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/update_x32.png"))); // NOI18N
        update_button.setText("Actualizar");
        update_button.setActionCommand("update");
        update_button.setPreferredSize(new java.awt.Dimension(166, 40));
        jPanel13.add(update_button);

        delete_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        delete_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/eliminar.png"))); // NOI18N
        delete_button.setText("Eliminar");
        delete_button.setActionCommand("delete");
        delete_button.setPreferredSize(new java.awt.Dimension(166, 40));
        jPanel13.add(delete_button);

        option_panel.add(jPanel13);

        cancel_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cancel_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        cancel_button.setText("Cancelar");
        cancel_button.setActionCommand("cancel");
        cancel_button.setPreferredSize(new java.awt.Dimension(500, 40));
        option_panel.add(cancel_button);

        register_panel.add(option_panel, java.awt.BorderLayout.SOUTH);

        root_panel.add(register_panel, "register");

        search_panel.setMinimumSize(new java.awt.Dimension(900, 700));
        search_panel.setName("consult"); // NOI18N
        search_panel.setLayout(new java.awt.BorderLayout());

        panel_filtros.setPreferredSize(new java.awt.Dimension(1000, 170));
        panel_filtros.setLayout(new java.awt.BorderLayout(5, 5));

        pf_bar_super.setLayout(new java.awt.BorderLayout(5, 5));

        filtro_quitar.setText("Quitar filtros");
        filtro_quitar.setPreferredSize(new java.awt.Dimension(150, 29));
        pf_bar_super.add(filtro_quitar, java.awt.BorderLayout.LINE_END);

        filtros.setText("Filtros");
        pf_bar_super.add(filtros, java.awt.BorderLayout.CENTER);

        panel_filtros.add(pf_bar_super, java.awt.BorderLayout.NORTH);

        pf_filtros.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        jPanel22.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel22.setLayout(new java.awt.GridLayout(4, 1, 5, 5));

        jPanel25.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("Calle");
        jLabel12.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel25.add(jLabel12, java.awt.BorderLayout.WEST);

        street_filter.setName("calle"); // NOI18N
        jPanel25.add(street_filter, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel25);

        jPanel26.setLayout(new java.awt.BorderLayout());

        jLabel13.setText("T. Toma");
        jLabel13.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel26.add(jLabel13, java.awt.BorderLayout.WEST);

        water_intakes_filter.setName("ttoma"); // NOI18N
        jPanel26.add(water_intakes_filter, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel26);

        jPanel28.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("Estado");
        jLabel15.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel28.add(jLabel15, java.awt.BorderLayout.WEST);

        filtro_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ESTADO", "ACTIVO", "INACTIVO" }));
        filtro_estado.setName("estado"); // NOI18N
        jPanel28.add(filtro_estado, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel28);

        pf_filtros.add(jPanel22);

        jPanel24.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel24.setLayout(new java.awt.GridLayout(4, 1, 5, 5));

        jPanel27.setLayout(new java.awt.GridLayout(1, 3));

        filtro_is_titular.setText("Titular");
        filtro_is_titular.setName("estitular"); // NOI18N
        filtro_is_titular.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel27.add(filtro_is_titular);

        filtro_is_consumidor.setText("Consumidor");
        filtro_is_consumidor.setName("esconsumidor"); // NOI18N
        filtro_is_consumidor.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel27.add(filtro_is_consumidor);

        jPanel24.add(jPanel27);

        jPanel31.setLayout(new java.awt.BorderLayout());

        jLabel16.setText("Titular");
        jLabel16.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel31.add(jLabel16, java.awt.BorderLayout.WEST);

        filtro_Titular.setName("titular"); // NOI18N
        jPanel31.add(filtro_Titular, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel31);

        pf_filtros.add(jPanel24);

        panel_filtros.add(pf_filtros, java.awt.BorderLayout.CENTER);

        search_panel.add(panel_filtros, java.awt.BorderLayout.NORTH);

        table_panel.setPreferredSize(new java.awt.Dimension(1000, 500));
        table_panel.setLayout(new java.awt.BorderLayout(5, 5));

        search_items_table.setMinimumSize(new java.awt.Dimension(980, 30));
        search_items_table.setPreferredSize(new java.awt.Dimension(500, 30));
        search_items_table.setLayout(new java.awt.BorderLayout(5, 5));

        search_field.setName("buscador"); // NOI18N
        search_items_table.add(search_field, java.awt.BorderLayout.CENTER);

        jPanel23.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel23.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        back_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        back_button.setActionCommand("back");
        back_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel23.add(back_button);

        next_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        next_button.setActionCommand("next");
        next_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel23.add(next_button);

        search_items_table.add(jPanel23, java.awt.BorderLayout.LINE_END);

        reload_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        reload_button.setActionCommand("reload");
        reload_button.setPreferredSize(new java.awt.Dimension(100, 30));
        search_items_table.add(reload_button, java.awt.BorderLayout.LINE_START);

        table_panel.add(search_items_table, java.awt.BorderLayout.NORTH);

        objects_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        objects_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        objects_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        objects_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(objects_table);

        table_panel.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        search_panel.add(table_panel, java.awt.BorderLayout.CENTER);

        status_bar_panel.setPreferredSize(new java.awt.Dimension(100, 30));
        status_bar_panel.setLayout(new java.awt.BorderLayout());

        jPanel32.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel32.setLayout(new java.awt.BorderLayout());

        count_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count_label.setText("No.");
        jPanel32.add(count_label, java.awt.BorderLayout.CENTER);

        count_field.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count_field.setText("0");
        count_field.setToolTipText("Numero de pagos hechos.");
        count_field.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel32.add(count_field, java.awt.BorderLayout.LINE_END);

        status_bar_panel.add(jPanel32, java.awt.BorderLayout.WEST);

        range_field.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        range_field.setText("0 - 0");
        range_field.setToolTipText("");
        status_bar_panel.add(range_field, java.awt.BorderLayout.CENTER);

        jPanel29.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel29.setLayout(new java.awt.BorderLayout());

        total_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        total_label.setText("Total:");
        jPanel29.add(total_label, java.awt.BorderLayout.CENTER);

        total_field.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total_field.setText("0");
        total_field.setToolTipText("Numero de pagos hechos.");
        total_field.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel29.add(total_field, java.awt.BorderLayout.LINE_END);

        status_bar_panel.add(jPanel29, java.awt.BorderLayout.EAST);

        search_panel.add(status_bar_panel, java.awt.BorderLayout.SOUTH);

        root_panel.add(search_panel, "consult");

        add(root_panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_consumer_button;
    private javax.swing.JButton add_file_button;
    private javax.swing.JButton add_photo_button;
    private javax.swing.JButton back_button;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancel_button;
    private javax.swing.JPanel complement_data_panel;
    private javax.swing.JLabel count_field;
    private javax.swing.JLabel count_label;
    private javax.swing.JTextField curp_field;
    private javax.swing.JTextField date_birday_field;
    private javax.swing.JTextField date_last_update_field;
    private javax.swing.JTextField date_register_field;
    private javax.swing.JButton delete_button;
    private javax.swing.JTextField email_field;
    private javax.swing.JTextField filtro_Titular;
    private javax.swing.JComboBox<String> filtro_estado;
    private javax.swing.JCheckBox filtro_is_consumidor;
    private javax.swing.JCheckBox filtro_is_titular;
    private javax.swing.JButton filtro_quitar;
    private javax.swing.JCheckBox filtros;
    private javax.swing.JTextField first_name_field;
    private javax.swing.JComboBox<String> gender_field;
    private javax.swing.JTextField inside_number_field;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField last_name1_field;
    private javax.swing.JTextField last_name2_field;
    private javax.swing.JCheckBox man_calle;
    private javax.swing.JCheckBox man_estado;
    private javax.swing.JCheckBox man_estado1;
    private javax.swing.JCheckBox man_estado2;
    private javax.swing.JCheckBox man_tipo_toma;
    private javax.swing.JButton next_button;
    private javax.swing.JPanel north_panel;
    private javax.swing.JPanel np_cp_center;
    private javax.swing.JPanel np_cp_east;
    private javax.swing.JPanel np_cp_west;
    private javax.swing.JTable objects_table;
    private javax.swing.JPanel option_panel;
    private javax.swing.JTextField outside_number_field;
    private javax.swing.JPanel p_date_register;
    private javax.swing.JPanel p_date_update;
    private javax.swing.JPanel p_status;
    private javax.swing.JPanel p_user_type;
    private javax.swing.JPanel panel_filtros;
    private javax.swing.JPanel pc_am;
    private javax.swing.JPanel pc_ap;
    private javax.swing.JPanel pc_calle;
    private javax.swing.JPanel pc_ncasa;
    private javax.swing.JPanel pc_nombre;
    private javax.swing.JPanel pc_tipo_toma;
    private javax.swing.JPanel pf_bar_super;
    private javax.swing.JPanel pf_filtros;
    private javax.swing.JTextField phone_number1_field;
    private javax.swing.JTextField phone_number2_field;
    private javax.swing.JLabel range_field;
    private javax.swing.JButton register_button;
    private javax.swing.JPanel register_panel;
    private javax.swing.JButton reload_button;
    private javax.swing.JPanel root_panel;
    private javax.swing.JButton save_button;
    private javax.swing.JButton search_button;
    private javax.swing.JTextField search_field;
    private javax.swing.JPanel search_items_table;
    private javax.swing.JButton search_object;
    private javax.swing.JPanel search_panel;
    private javax.swing.JButton show_consumer_list_button;
    private javax.swing.JCheckBox sn_numero;
    private javax.swing.JPanel status_bar_panel;
    private javax.swing.JComboBox<StreetDTO> street1_field;
    private javax.swing.JComboBox<StreetDTO> street2_field;
    private javax.swing.JComboBox<jsoftware.com.jblue.model.dto.StreetDTO> street_filter;
    private javax.swing.JPanel table_panel;
    private javax.swing.JLabel total_field;
    private javax.swing.JLabel total_label;
    private javax.swing.JButton update_button;
    private javax.swing.JPanel user_data_panel;
    private javax.swing.JComboBox<String> user_status_field;
    private javax.swing.JComboBox<jsoftware.com.jblue.model.dto.WaterIntakeTypesDTO> water_intakes_filter;
    private javax.swing.JComboBox<WaterIntakeTypesDTO> water_intakes_type_field;
    // End of variables declaration//GEN-END:variables

    public UserDTO getObject() {
        return object_search;
    }

    @Override
    public JTextField getTextComponenteTable() {
        return search_field;
    }

    @Override
    public String getTextSearchTable() {
        return Filters.clearText(search_field.getText());
    }

    @Override
    public JTable getTable() {
        return objects_table;
    }

    @Override
    public JTableModel getModel() {
        return model;
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

    @Override
    public UserDTO getObjectSearch() {
        return object_search;
    }

    @Override
    public void setObjectSearch(JDBMapObject o) {
        object_search = (UserDTO) o;
    }

    @Override
    public void setRowsData(String... info) {
        count_field.setText(info[0]);
        range_field.setText(info[1]);
        total_field.setText(info[2]);
    }

    @Override
    public void setScreenTableInfo() {
        if (object_search.getUserType().equals("1")) {
            jRadioButton1.setSelected(true);
        } else {
            jRadioButton2.setSelected(true);
        }
        curp_field.setText(object_search.getCurp());
        first_name_field.setText(object_search.getFirstName());
        last_name1_field.setText(object_search.getLastName1());
        last_name2_field.setText(object_search.getLastName2());
        gender_field.setSelectedIndex(Integer.parseInt(object_search.getGender()));
        water_intakes_type_field.setSelectedItem((WaterIntakeTypesDTO) object_search.get("water_inatke_type_object"));
        street1_field.setSelectedItem((StreetDTO) object_search.get("street1_object"));
        street2_field.setSelectedItem((StreetDTO) object_search.get("street2_object"));
        inside_number_field.setText(object_search.getInsideNumber());
        outside_number_field.setText(object_search.getOutsideNumber());
        phone_number1_field.setText(object_search.getPhoneNumber1());
        phone_number2_field.setText(object_search.getPhoneNumber2());
        email_field.setText(object_search.getEmail());
        date_birday_field.setText(object_search.getBirthdate());

    }

    public String getUserKey() {
        return user_key;
    }

    public void setUserKey(String user_key) {
        this.user_key = user_key;
    }

    @Override
    public boolean isValuesOK() {
        boolean res = false;
        String msg = "EL CAMPO '%s' NO ES VALIDO";
        JTextField[] required = {
            first_name_field,
            last_name1_field,
            last_name2_field,
            curp_field
        };
        for (JTextField i : required) {
            res = !Filters.isNullOrBlank(i.getText());
            if (!res) {
                JOptionPane.showMessageDialog(this, msg, "CAMPOS ERRONEOS", JOptionPane.ERROR_MESSAGE);
            }
        }

        JComboBox[] requiered2 = {
            gender_field,
            water_intakes_type_field,
            street1_field
        };
        for (JComboBox i : requiered2) {
            res = i.getSelectedIndex() > 0;
            if (!res) {
                JOptionPane.showMessageDialog(this, msg, "CAMPOS ERRONEOS", JOptionPane.ERROR_MESSAGE);
            }
        }
        return res;
    }

    @Override
    public UserDTO getValues(boolean update) {
        Map<String, Object> map = new HashMap<>(18);
        if (update) {
            Func.addIfChanged(map, "curp", object_search.getCurp(), Formats.getTextFormat(curp_field.getText()));
            Func.addIfChanged(map, "first_name", object_search.getCurp(), Formats.getTextFormat(first_name_field.getText()));
            Func.addIfChanged(map, "last_name1", object_search.getCurp(), Formats.getTextFormat(last_name1_field.getText()));
            Func.addIfChanged(map, "last_name2", object_search.getCurp(), Formats.getTextFormat(last_name2_field.getText()));
            Func.addIfChanged(map, "gender", object_search.getCurp(), Formats.getTextFormat(String.valueOf(gender_field.getSelectedIndex())));
            Func.addIfChanged(map, "email", object_search.getCurp(), Formats.getTextFormat(email_field.getText()));
            Func.addIfChanged(map, "birthdate", object_search.getCurp(), Formats.getTextFormat(date_birday_field.getText()));
            Func.addIfChanged(map, "phone_number1", object_search.getCurp(), Formats.getTextFormat(phone_number1_field.getText()));
            Func.addIfChanged(map, "phone_number2", object_search.getCurp(), Formats.getTextFormat(phone_number2_field.getText()));
            StreetDTO str1 = street1_field.getItemAt(street1_field.getSelectedIndex());
            Func.addIfChanged(map, "street1", object_search.getCurp(), Formats.getTextFormat(str1.getId()));

            StreetDTO str2 = street2_field.getItemAt(street2_field.getSelectedIndex());
            Func.addIfChanged(map, "street2", object_search.getCurp(), Formats.getTextFormat(str2.getId()));

            Func.addIfChanged(map, "inside_number", object_search.getCurp(), Formats.getTextFormat(inside_number_field.getText()));
            Func.addIfChanged(map, "outside_number", object_search.getCurp(), Formats.getTextFormat(outside_number_field.getText()));
            WaterIntakeTypesDTO wki = water_intakes_type_field.getItemAt(water_intakes_type_field.getSelectedIndex());
            Func.addIfChanged(map, "water_intake_type", object_search.getCurp(), Formats.getTextFormat(wki.getId()));
            String user_type = buttonGroup1.getSelection().getActionCommand();
            Func.addIfChanged(map, "user_type", object_search.getCurp(), Formats.getTextFormat(user_type));

        } else {
            Func.putIfPresentAndNotBlank(map, "curp", Formats.getTextFormat(curp_field.getText()));
            Func.putIfPresentAndNotBlank(map, "first_name", Formats.getTextFormat(first_name_field.getText()));
            Func.putIfPresentAndNotBlank(map, "last_name1", Formats.getTextFormat(last_name1_field.getText()));
            Func.putIfPresentAndNotBlank(map, "last_name2", Formats.getTextFormat(last_name2_field.getText()));
            Func.putIfPresentAndNotBlank(map, "gender", Formats.getTextFormat(String.valueOf(gender_field.getSelectedIndex())));
            Func.putIfPresentAndNotBlank(map, "email", Formats.getTextFormat(email_field.getText()));
            Func.putIfPresentAndNotBlank(map, "birthdate", Formats.getTextFormat(date_birday_field.getText()));
            Func.putIfPresentAndNotBlank(map, "phone_number1", Formats.getTextFormat(phone_number1_field.getText()));
            Func.putIfPresentAndNotBlank(map, "phone_number2", Formats.getTextFormat(phone_number2_field.getText()));
            StreetDTO str1 = street1_field.getItemAt(street1_field.getSelectedIndex());
            Func.putIfPresentAndNotBlank(map, "street1", Formats.getTextFormat(str1.getId()));

            StreetDTO str2 = street2_field.getItemAt(street2_field.getSelectedIndex());
            Func.putIfPresentAndNotBlank(map, "street2", Formats.getTextFormat(str2.getId()));

            Func.putIfPresentAndNotBlank(map, "inside_number", Formats.getTextFormat(inside_number_field.getText()));
            Func.putIfPresentAndNotBlank(map, "outside_number", Formats.getTextFormat(outside_number_field.getText()));
            WaterIntakeTypesDTO wki = water_intakes_type_field.getItemAt(water_intakes_type_field.getSelectedIndex());
            Func.putIfPresentAndNotBlank(map, "water_intake_type", Formats.getTextFormat(wki.getId()));
            String user_type = buttonGroup1.getSelection().getActionCommand();
            Func.putIfPresentAndNotBlank(map, "user_type", Formats.getTextFormat(user_type));
        }
        return new UserDTO(map);
    }

}
