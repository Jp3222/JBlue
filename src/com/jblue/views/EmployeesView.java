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
package com.jblue.views;

import com.jblue.controllers.FactoryController;
import com.jblue.controllers.compc.TableController;
import com.jblue.model.constants._Const;
import com.jblue.model.dtos.OEmployee;
import com.jblue.model.dtos.Objects;
import com.jblue.model.factories.CacheFactory;
import com.jblue.model.factories.TableModelFactory;
import com.jblue.util.Filters;
import com.jblue.util.EncriptadoAES;
import com.jblue.util.Fecha;
import com.jblue.util.Formats;
import com.jblue.util.GraphicsUtils;
import com.jblue.util.ObjectUtils;
import com.jblue.views.framework.DBValuesMapModel;
import com.jblue.views.framework.DBView;
import com.jutil.swingw.modelos.JTableModel;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;

/**
 *
 * @author jp
 */
public final class EmployeesView extends DBView implements DBValuesMapModel {

    private final JTableModel model;
    private final CardLayout ly;
    private OEmployee object_search;

    /**
     * Creates new form PersonalC
     *
     *
     */
    public EmployeesView() {
        initComponents();
        model = TableModelFactory.getEmployeesTableModel();
        objects_table.setModel(model);
        ly = (CardLayout) root_panel.getLayout();
        ly.show(root_panel, register_panel.getName());
        controller = FactoryController.getEmployeeController(this);
        table_controller = new TableController(this, CacheFactory.EMPLOYEES);
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
        //
        show_user_check.addActionListener((e) -> show(e));
        show_password_check.addActionListener((e) -> show(e));
    }

    private void show(ActionEvent e) {
        String action = e.getActionCommand();
        JCheckBox object = (JCheckBox) e.getSource();
        System.out.println(action);
        System.out.println(object.isSelected());
        switch (action) {
            case "show_user" ->
                user_field.setEchoChar((char) (object.isSelected() ? 0 : '*'));
            case "show_password" ->
                password_field.setEchoChar((char) (object.isSelected() ? 0 : '*'));
        }
    }

    @Override
    public void components() {
    }

    @Override
    public void initialState() {
        //Fields
        curp_field.setText(null);
        first_name_field.setText(null);
        last_name_1_field.setText(null);
        last_name_2_field.setText(null);
        gender_field.setSelectedIndex(0);
        email_field.setText(null);
        date_birday_field.setText(null);
        phone_number1_field.setText(null);
        phone_number2_field.setText(null);
        employee_type_field.setSelectedIndex(0);
        status_type_field.setSelectedIndex(0);
        user_field.setText(null);
        password_field.setText(null);
        date_last_update_field.setText(null);
        date_register_field.setText(null);
        date_end_check_field.setSelected(false);
        date_end_field.setText(null);
        date_end_field.setEditable(false);
        //
        view_show = 1;
        object_search = null;
        //
        save_button.setEnabled(true);
        GraphicsUtils.setEnable(false,
                update_button, delete_button
        );
        user_field.setEchoChar('*');
        user_field.setEchoChar('*');
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
        pc_nombre = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        first_name_field = new javax.swing.JTextField();
        pc_ap = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        last_name_1_field = new javax.swing.JTextField();
        pc_am = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        last_name_2_field = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        gender_field = new javax.swing.JComboBox<>();
        pc_tipo_toma = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        employee_type_field = new javax.swing.JComboBox<>();
        pc_calle = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        status_type_field = new javax.swing.JComboBox<>();
        pc_ncasa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        user_field = new javax.swing.JPasswordField();
        show_user_check = new javax.swing.JCheckBox();
        pc_estado = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        password_field = new javax.swing.JPasswordField();
        show_password_check = new javax.swing.JCheckBox();
        pc_estado1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        date_last_update_field = new javax.swing.JTextField();
        pc_estado2 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        date_register_field = new javax.swing.JTextField();
        complement_data_panel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        curp_field = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        email_field = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        phone_number1_field = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        phone_number2_field = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        date_birday_field = new javax.swing.JFormattedTextField();
        date_end_check_field = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        date_end_field = new javax.swing.JFormattedTextField();
        show_consumer_list_button = new javax.swing.JButton();
        options_panel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        save_button = new javax.swing.JButton();
        update_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();
        search_panel = new javax.swing.JPanel();
        panel_tabla = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        search_field = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        back_button = new javax.swing.JButton();
        next_button = new javax.swing.JButton();
        reload_button = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        objects_table = new javax.swing.JTable();
        status_bar_panel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        range = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(700, 600));
        setName("Personal"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 700));
        setLayout(new java.awt.BorderLayout());

        north_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        north_panel.setLayout(new java.awt.BorderLayout(5, 5));

        np_cp_center.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

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

        root_panel.setLayout(new java.awt.CardLayout(10, 10));

        register_panel.setName("register"); // NOI18N
        register_panel.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N

        user_data_panel.setPreferredSize(new java.awt.Dimension(500, 600));
        user_data_panel.setLayout(new java.awt.GridLayout(11, 1, 0, 10));

        pc_nombre.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel2.setText("Nombre: ");
        jLabel2.setMaximumSize(new java.awt.Dimension(60, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(150, 25));
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_nombre.add(jLabel2, java.awt.BorderLayout.WEST);

        first_name_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        first_name_field.setToolTipText("<html>\nCampo: Nombre\n<br>valores admitidos: Solo texto\n<br>tamaño maximo: 32 Caracteres");
        first_name_field.setName("Nombre"); // NOI18N
        first_name_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_nombre.add(first_name_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(pc_nombre);

        pc_ap.setPreferredSize(new java.awt.Dimension(250, 30));
        pc_ap.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel3.setText("A. Paterno: ");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_ap.add(jLabel3, java.awt.BorderLayout.WEST);

        last_name_1_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        last_name_1_field.setToolTipText("<html>\nCampos: Apellido Paterno\n<br>Valor: Solo texto \n<br>Longitud: 32 Caracteres");
        last_name_1_field.setName("A. Paterno"); // NOI18N
        last_name_1_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_ap.add(last_name_1_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(pc_ap);

        pc_am.setPreferredSize(new java.awt.Dimension(250, 30));
        pc_am.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel4.setText("A. Materno:");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_am.add(jLabel4, java.awt.BorderLayout.WEST);

        last_name_2_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        last_name_2_field.setToolTipText("<html> Campos: Apellido Materno\n<br>Valor: Solo texto <br>Longitud: 32 Caracteres");
        last_name_2_field.setName("A. Materno"); // NOI18N
        last_name_2_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_am.add(last_name_2_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(pc_am);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel25.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel25.setText("Genero");
        jLabel25.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel9.add(jLabel25, java.awt.BorderLayout.LINE_START);

        gender_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        gender_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- SELECCIONAR GENERO --", "No. Definido", "Masculino.", "Femenino." }));
        gender_field.setName("Genero"); // NOI18N
        jPanel9.add(gender_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(jPanel9);

        pc_tipo_toma.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_tipo_toma.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel5.setText("Tipo de Empleado");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_tipo_toma.add(jLabel5, java.awt.BorderLayout.WEST);

        employee_type_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        employee_type_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- SELECCIONAR CARGO --", "ADMINISTRADOR", "PRESIDENTE", "TESORERO", "SECRETARIO", "PASANTE" }));
        employee_type_field.setName("Tipo de Empleado"); // NOI18N
        pc_tipo_toma.add(employee_type_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(pc_tipo_toma);

        pc_calle.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_calle.setLayout(new java.awt.BorderLayout());

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel6.setText("Status");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_calle.add(jLabel6, java.awt.BorderLayout.WEST);

        status_type_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        status_type_field.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- SELECCIONAR STATUS --", "ACTIVO", "INACTIVO", "FINALIZADO" }));
        status_type_field.setName("Status"); // NOI18N
        status_type_field.setPreferredSize(new java.awt.Dimension(100, 30));
        pc_calle.add(status_type_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(pc_calle);

        pc_ncasa.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_ncasa.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel1.setText("Usuario");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_ncasa.add(jLabel1, java.awt.BorderLayout.WEST);

        user_field.setEditable(false);
        user_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        user_field.setName("Usuario"); // NOI18N
        pc_ncasa.add(user_field, java.awt.BorderLayout.CENTER);

        show_user_check.setActionCommand("show_user");
        show_user_check.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img2.png"))); // NOI18N
        show_user_check.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img3.png"))); // NOI18N
        pc_ncasa.add(show_user_check, java.awt.BorderLayout.LINE_END);

        user_data_panel.add(pc_ncasa);

        pc_estado.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_estado.setLayout(new java.awt.BorderLayout());

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel8.setText("Contraseña");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_estado.add(jLabel8, java.awt.BorderLayout.WEST);

        password_field.setEditable(false);
        password_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        password_field.setName("Contraseña"); // NOI18N
        pc_estado.add(password_field, java.awt.BorderLayout.CENTER);

        show_password_check.setActionCommand("show_password");
        show_password_check.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img2.png"))); // NOI18N
        show_password_check.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img3.png"))); // NOI18N
        pc_estado.add(show_password_check, java.awt.BorderLayout.LINE_END);

        user_data_panel.add(pc_estado);

        pc_estado1.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_estado1.setLayout(new java.awt.BorderLayout());

        jLabel27.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel27.setText("F. de actualizacion");
        jLabel27.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_estado1.add(jLabel27, java.awt.BorderLayout.WEST);

        date_last_update_field.setEditable(false);
        date_last_update_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        pc_estado1.add(date_last_update_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(pc_estado1);

        pc_estado2.setPreferredSize(new java.awt.Dimension(500, 50));
        pc_estado2.setLayout(new java.awt.BorderLayout());

        jLabel28.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel28.setText("F. de registro");
        jLabel28.setPreferredSize(new java.awt.Dimension(150, 25));
        pc_estado2.add(jLabel28, java.awt.BorderLayout.WEST);

        date_register_field.setEditable(false);
        date_register_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        pc_estado2.add(date_register_field, java.awt.BorderLayout.CENTER);

        user_data_panel.add(pc_estado2);

        jTabbedPane1.addTab("Datos de usuario", user_data_panel);

        complement_data_panel.setLayout(new java.awt.GridLayout(11, 1, 10, 10));

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel29.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel29.setText("CURP");
        jLabel29.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel10.add(jLabel29, java.awt.BorderLayout.WEST);

        curp_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        curp_field.setName("CURP"); // NOI18N
        jPanel10.add(curp_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel10);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel12.setText("Email");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel2.add(jLabel12, java.awt.BorderLayout.WEST);

        email_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        email_field.setName("Email"); // NOI18N
        jPanel2.add(email_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel14.setText("Telefono 1: ");
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel3.add(jLabel14, java.awt.BorderLayout.WEST);

        phone_number1_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        phone_number1_field.setName("Telefono 1"); // NOI18N
        jPanel3.add(phone_number1_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel3);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel30.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel30.setText("Telefono 2:");
        jLabel30.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel4.add(jLabel30, java.awt.BorderLayout.WEST);

        phone_number2_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        phone_number2_field.setName("Telefono 2"); // NOI18N
        jPanel4.add(phone_number2_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel31.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel31.setText("Fecha de cumpleaños");
        jLabel31.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel5.add(jLabel31, java.awt.BorderLayout.WEST);

        date_birday_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        date_birday_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        date_birday_field.setName("Fecha de cumpleaños"); // NOI18N
        jPanel5.add(date_birday_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel5);

        date_end_check_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        date_end_check_field.setText("Fecha de Finalizacion");
        complement_data_panel.add(date_end_check_field);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("F. de finalizacion");
        jLabel11.setPreferredSize(new java.awt.Dimension(150, 25));
        jPanel1.add(jLabel11, java.awt.BorderLayout.LINE_START);

        date_end_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        date_end_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        date_end_field.setName("Fecha de finalizacion"); // NOI18N
        jPanel1.add(date_end_field, java.awt.BorderLayout.CENTER);

        complement_data_panel.add(jPanel1);

        show_consumer_list_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        show_consumer_list_button.setText("Añadir Foto");
        show_consumer_list_button.setName("Añadir Foto"); // NOI18N
        complement_data_panel.add(show_consumer_list_button);

        jTabbedPane1.addTab("Informacion complementaria.", complement_data_panel);

        register_panel.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        options_panel.setLayout(new java.awt.GridLayout(2, 0));

        jPanel13.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel13.setLayout(new java.awt.GridLayout(1, 3));

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

        options_panel.add(jPanel13);

        cancel_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cancel_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        cancel_button.setText("Cancelar");
        cancel_button.setActionCommand("cancel");
        cancel_button.setPreferredSize(new java.awt.Dimension(500, 40));
        options_panel.add(cancel_button);

        register_panel.add(options_panel, java.awt.BorderLayout.SOUTH);

        root_panel.add(register_panel, "register");

        search_panel.setName("consult"); // NOI18N
        search_panel.setLayout(new java.awt.BorderLayout(5, 5));

        panel_tabla.setPreferredSize(new java.awt.Dimension(1000, 500));
        panel_tabla.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel30.setMinimumSize(new java.awt.Dimension(980, 30));
        jPanel30.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel30.setLayout(new java.awt.BorderLayout(5, 5));

        search_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        search_field.setName("buscador"); // NOI18N
        jPanel30.add(search_field, java.awt.BorderLayout.CENTER);

        jPanel23.setLayout(new java.awt.BorderLayout(5, 5));

        back_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        back_button.setActionCommand("back");
        back_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel23.add(back_button, java.awt.BorderLayout.WEST);

        next_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        next_button.setActionCommand("next");
        next_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel23.add(next_button, java.awt.BorderLayout.EAST);

        jPanel30.add(jPanel23, java.awt.BorderLayout.LINE_END);

        reload_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        reload_button.setActionCommand("reload");
        reload_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel30.add(reload_button, java.awt.BorderLayout.LINE_START);

        panel_tabla.add(jPanel30, java.awt.BorderLayout.NORTH);

        objects_table.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
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

        panel_tabla.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        search_panel.add(panel_tabla, java.awt.BorderLayout.CENTER);

        status_bar_panel.setPreferredSize(new java.awt.Dimension(100, 30));
        status_bar_panel.setLayout(new java.awt.BorderLayout());

        jPanel32.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel32.setLayout(new java.awt.BorderLayout());

        jLabel23.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("No.");
        jPanel32.add(jLabel23, java.awt.BorderLayout.CENTER);

        count.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count.setText("0");
        count.setToolTipText("Numero de pagos hechos.");
        count.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel32.add(count, java.awt.BorderLayout.LINE_END);

        status_bar_panel.add(jPanel32, java.awt.BorderLayout.WEST);

        range.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        range.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        range.setText("0 - 0");
        range.setToolTipText("");
        status_bar_panel.add(range, java.awt.BorderLayout.CENTER);

        jPanel29.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel29.setLayout(new java.awt.BorderLayout());

        jLabel17.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Total:");
        jPanel29.add(jLabel17, java.awt.BorderLayout.CENTER);

        total.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
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
    private javax.swing.JButton back_button;
    private javax.swing.JButton cancel_button;
    private javax.swing.JPanel complement_data_panel;
    private javax.swing.JLabel count;
    private javax.swing.JTextField curp_field;
    private javax.swing.JFormattedTextField date_birday_field;
    private javax.swing.JCheckBox date_end_check_field;
    private javax.swing.JFormattedTextField date_end_field;
    private javax.swing.JTextField date_last_update_field;
    private javax.swing.JTextField date_register_field;
    private javax.swing.JButton delete_button;
    private javax.swing.JTextField email_field;
    private javax.swing.JComboBox<String> employee_type_field;
    private javax.swing.JTextField first_name_field;
    private javax.swing.JComboBox<String> gender_field;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField last_name_1_field;
    private javax.swing.JTextField last_name_2_field;
    private javax.swing.JButton next_button;
    private javax.swing.JPanel north_panel;
    private javax.swing.JPanel np_cp_center;
    private javax.swing.JPanel np_cp_east;
    private javax.swing.JPanel np_cp_west;
    private javax.swing.JTable objects_table;
    private javax.swing.JPanel options_panel;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JPasswordField password_field;
    private javax.swing.JPanel pc_am;
    private javax.swing.JPanel pc_ap;
    private javax.swing.JPanel pc_calle;
    private javax.swing.JPanel pc_estado;
    private javax.swing.JPanel pc_estado1;
    private javax.swing.JPanel pc_estado2;
    private javax.swing.JPanel pc_ncasa;
    private javax.swing.JPanel pc_nombre;
    private javax.swing.JPanel pc_tipo_toma;
    private javax.swing.JTextField phone_number1_field;
    private javax.swing.JTextField phone_number2_field;
    private javax.swing.JLabel range;
    private javax.swing.JButton register_button;
    private javax.swing.JPanel register_panel;
    private javax.swing.JButton reload_button;
    private javax.swing.JPanel root_panel;
    private javax.swing.JButton save_button;
    private javax.swing.JButton search_button;
    private javax.swing.JTextField search_field;
    private javax.swing.JButton search_object;
    private javax.swing.JPanel search_panel;
    private javax.swing.JButton show_consumer_list_button;
    private javax.swing.JCheckBox show_password_check;
    private javax.swing.JCheckBox show_user_check;
    private javax.swing.JPanel status_bar_panel;
    private javax.swing.JComboBox<String> status_type_field;
    private javax.swing.JLabel total;
    private javax.swing.JButton update_button;
    private javax.swing.JPanel user_data_panel;
    private javax.swing.JPasswordField user_field;
    // End of variables declaration//GEN-END:variables

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
    public void setObjectSearch(Objects o) {
        this.object_search = (OEmployee) o;
    }

    @Override
    public OEmployee getObjectSearch() {
        return object_search;
    }

    @Override
    public void setRowsData(String... info) {
        count.setText(info[0]);
        range.setText(info[1]);
        total.setText(info[2]);
    }

    @Override
    public void setScreenTableInfo() {
        save_button.setEnabled(false);
        GraphicsUtils.setEnable(true, update_button, delete_button);
        //
        curp_field.setText(object_search.getCURP());
        first_name_field.setText(object_search.getFirstName());
        last_name_1_field.setText(object_search.getLastName1());
        last_name_2_field.setText(object_search.getLastName2());
        gender_field.setSelectedIndex(object_search.getGender());
        email_field.setText(object_search.getEmail());
        date_birday_field.setValue(Fecha.getStringToDate(object_search.getDateBirday()));
        phone_number1_field.setText(object_search.getPhoneNumber1());
        phone_number2_field.setText(object_search.getPhoneNumber2());
        employee_type_field.setSelectedItem(ObjectUtils.getDescriptionEmployeeTypeCAT(object_search.getEmployeeType()));
        status_type_field.setSelectedItem(ObjectUtils.getDescriptionStatusCAT(object_search.getStatus()));
        user_field.setText(object_search.getUser());
        password_field.setText(object_search.getPassword());
        date_last_update_field.setText(_Const.getLocalDateTimeToString(object_search.getDateUpdate()));
        date_register_field.setText(_Const.getLocalDateTimeToString(object_search.getDateRegister()));
        date_end_check_field.setSelected(object_search.getDateEnd() != null);
        date_end_field.setEditable(object_search.getDateEnd() != null);
        if (object_search.getDateEnd() != null) {
            date_end_field.setValue(Date.from(
                    object_search.getDateEnd().atZone(ZoneId.systemDefault()).toInstant()
            ));
        }
    }

    @Override
    public boolean isValuesOk() {
        JTextField[] arr = {
            first_name_field, last_name_1_field, last_name_2_field, user_field, password_field
        };
        for (JTextField i : arr) {

            if (Filters.isNullOrBlank(i.getText())) {
                JOptionPane.showMessageDialog(register_panel, "El campo: \"%s\" no es valido".formatted(i.getName()));
                return false;
            }
        }
        JComboBox[] arr2 = {
            gender_field, employee_type_field, status_type_field
        };
        for (JComboBox i : arr2) {
            if (i.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(register_panel, "El campo: \"%s\" no es valido".formatted(i.getName()));
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<String, String> getValues(boolean update) {
        Map<String, String> map;
        String _curp = curp_field.getText();
        String _first_name = first_name_field.getText();
        String _last_name1 = last_name_1_field.getText();
        String _last_name2 = last_name_2_field.getText();
        String _gender = String.valueOf(gender_field.getSelectedIndex());
        String _email = email_field.getText();
        String _date_birday = date_birday_field.getText();
        String _phone_number1 = phone_number1_field.getText();
        String _phone_number2 = phone_number2_field.getText();
        String _employee_type = String.valueOf(
                ObjectUtils.getIndexEmployeeCAT(employee_type_field.getSelectedItem().toString())
        );
        String _status = String.valueOf(
                ObjectUtils.getIndexStatusCAT(status_type_field.getSelectedItem().toString())
        );
        String _date_end = null;
        if (date_end_check_field.isSelected()) {
            _date_end = date_end_field.getText();
        }
        if (update) {
            map = saveUpdate(object_search, _curp, _first_name, _last_name1, _last_name2, _gender, _email, _date_birday, _phone_number1, _phone_number2, _employee_type, _status, _date_end);
        } else {
            String[] crd = credentials();
            String _user = crd[0];
            String _password = crd[0];
            map = saveInsert(_curp, _first_name, _last_name1, _last_name2, _gender, _email, _date_birday, _phone_number1, _phone_number2, _employee_type, _status, _date_end, _user, _password);
        }
        map = Formats.getDBFormatInputMap(map);
        //Se añaden despues de darle formato para no alterar las contraseñas encriptadas
        return map;
    }

    private Map<String, String> saveInsert(String curp, String first_name, String last_name1, String last_name2,
            String gender, String email, String date_birday, String phone_number1,
            String phone_number2, String employee_type, String status, String date_end, String user, String password) {

        Map<String, String> map = new HashMap<>();

        // Campos que tienen validación de no nulo y no en blanco
        Filters.putIfPresentAndNotBlank(map, "curp", curp);
        Filters.putIfPresentAndNotBlank(map, "first_name", first_name);
        Filters.putIfPresentAndNotBlank(map, "last_name1", last_name1);
        Filters.putIfPresentAndNotBlank(map, "last_name2", last_name2);
        Filters.putIfPresentAndNotBlank(map, "gender", gender);
        Filters.putIfPresentAndNotBlank(map, "email", email);
        Filters.putIfPresentAndNotBlank(map, "date_birday", date_birday);
        Filters.putIfPresentAndNotBlank(map, "phone_number1", phone_number1);
        Filters.putIfPresentAndNotBlank(map, "phone_number2", phone_number2);
        Filters.putIfPresentAndNotBlank(map, "employee_type", employee_type);
        Filters.putIfPresentAndNotBlank(map, "status", status);
        Filters.putIfPresentAndNotBlank(map, "date_end", date_end);
        Filters.putIfPresentAndNotBlank(map, "user", user);
        Filters.putIfPresentAndNotBlank(map, "password", password);
        return map;
    }

    private Map<String, String> saveUpdate(OEmployee object_search,
            String curp, String first_name, String last_name1,
            String last_name2, String gender, String email,
            String date_birday, String phone_number1, String phone_number2,
            String employee_type, String status, String date_end) {
        if (object_search == null) {
            throw new NullPointerException("Objeto buscado null");
        }
        Map<String, String> map = new HashMap<>();

        // Usando un método auxiliar para validar y agregar al mapa
        Filters.addIfChanged(map, "curp", object_search.getCURP(), curp);
        Filters.addIfChanged(map, "first_name", object_search.getFirstName(), first_name);
        Filters.addIfChanged(map, "last_name1", object_search.getLastName1(), last_name1);
        Filters.addIfChanged(map, "last_name2", object_search.getLastName2(), last_name2);
        Filters.addIfChanged(map, "gender", String.valueOf(object_search.getGender()), gender);
        Filters.addIfChanged(map, "email", object_search.getEmail(), email);
        Filters.addIfChanged(map, "date_birday", object_search.getDateBirday(), date_birday);
        Filters.addIfChanged(map, "phone_number1", object_search.getPhoneNumber1(), phone_number1);
        Filters.addIfChanged(map, "phone_number2", object_search.getPhoneNumber2(), phone_number2);
        Filters.addIfChanged(map, "employee_type", String.valueOf(object_search.getEmployeeType()), employee_type);
        Filters.addIfChanged(map, "status", String.valueOf(object_search.getStatus()), status);
        LocalDateTime dateEnd = object_search.getDateEnd();
        Filters.addIfChanged(map, "date_end", date_end != null ? dateEnd.format(_Const.DATE_TIME) : null, date_end);
        return map;
    }

    private String[] credentials() {
        String _user = null;
        String _password = null;

        try {
            _user = EncriptadoAES.doEncrypt(String.valueOf(user_field.getPassword()),
                    String.valueOf(password_field.getPassword()));

            _password = EncriptadoAES.doEncrypt(String.valueOf(password_field.getPassword()),
                    String.valueOf(user_field.getPassword()));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException
                | InvalidKeyException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(EmployeesView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String[]{
            _user, _password
        };
    }

}
