/*
 * Copyright (C) 2025 juanp
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.sys.app.AppConfig;
import jsoftware.com.jblue.views.framework.DBValuesMapModel;
import jsoftware.com.jblue.views.framework.SimpleView;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.jexception.JExcp;
import jsoftware.com.jutil.swingw.modelos.JTableModel;
import jsoftware.com.jutil.sys.LaunchApp;

/**
 *
 * @author juanp
 */
public final class ParametersView extends SimpleView implements DBValuesMapModel {

    private static ParametersView instance;
    private static final long serialVersionUID = 1L;

    public synchronized static ParametersView getInstance() {
        if (instance == null) {
            instance = new ParametersView();
        }
        return instance;
    }
    private final JTableModel model = new JTableModel(new String[]{"Parametro", "Valor", "Descripcion"}, 1);
    private final Map<String, String> parameters_map;

    /**
     * Creates new form FlagsView
     */
    private ParametersView() {
        initComponents();
        parameters_map = new HashMap<>(20);
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
        jTable1.setModel(model);
    }

    @Override
    public void events() {
        update_button.addActionListener((e) -> {
            final String query = "UPDATE " + Const.DEV_PARAMETERS_TABLE.getTableName() + " SET value = ? WHERE parameter = ?";
            JDBConnection connection = (JDBConnection) LaunchApp.getInstance().getResources("connection");
            String mess = "Parametros Actualizados";
            Map<String, String> values = getValues(true);
            System.out.println(values.toString());
            connection.setAutoCommit(false);
            try (var st = connection.getNewPreparedStatement(query)) {
                for (Map.Entry<String, String> entry : values.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    st.setString(1, val);
                    st.setString(2, key);
                    st.addBatch();
                }
                int[] executeBatch = st.executeBatch();
                System.out.println(Arrays.toString(executeBatch));
                connection.commit();
                st.clearBatch();
                loadData();
            } catch (SQLException ex) {
                connection.rollBack();
                System.getLogger(ParametersView.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } finally {
                connection.setAutoCommit(true);
                int icon = JOptionPane.INFORMATION_MESSAGE;
                JOptionPane.showMessageDialog(this, mess, "Parametros", icon);

            }
        });
    }

    @Override
    public void initialState() {
        open_hour_field.setText(AppConfig.getOpenHour().format(DateTimeFormatter.ofPattern(Const.TIME_FORMAT)));
        close_hour_field.setText(AppConfig.getCloseHour().format(DateTimeFormatter.ofPattern(Const.TIME_FORMAT)));
        last_pay_day_field.setValue(AppConfig.getPayDay());
        auto_pay_field.setSelected(AppConfig.isPayDay());
        hour_validate_field.setSelected(AppConfig.isHourValidate());
        master_password_field.setText(AppConfig.getMaterPassword());
        master_user_field.setText(AppConfig.getMaterUser());
        dev_messages.setSelected(AppConfig.isDevMessages());
        db_messages.setSelected(AppConfig.isDbMessages());
        test_messages.setSelected(AppConfig.isTestMessages());
        dev_function.setSelected(AppConfig.isDevFunction());
        test_function.setSelected(AppConfig.isTestFunction());
        dev_logs.setSelected(AppConfig.isLogsDev());
        test_logs.setSelected(AppConfig.isLogsTest());
        db_logs.setSelected(AppConfig.isLogsDB());
        loadData();

    }

    public void loadData() {
        JDBConnection conn = JDBConnection.getInstance();
        String query = JDBConnection.SELECT.formatted("parameter, value, description", "dev_parameters", "status = 1");
        try (ResultSet rs = conn.query(query)) {
            model.removeAllRows();
            while (rs.next()) {
                String[] data = {
                    rs.getString("parameter"),
                    rs.getString("value"),
                    rs.getString("description")
                };
                parameters_map.put(data[0], data[1]);
                model.addRow(data);
            }

        } catch (SQLException ex) {
            JExcp.getInstance(false, true).print(ex, getClass(), "load_data");
        }
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
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        root_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        register_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        open_hour_field = new javax.swing.JFormattedTextField();
        close_hour_field = new javax.swing.JFormattedTextField();
        last_pay_day_field = new javax.swing.JSpinner();
        auto_pay_field = new javax.swing.JCheckBox();
        master_user_field = new javax.swing.JTextField();
        master_password_field = new javax.swing.JTextField();
        hour_validate_field = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dev_messages = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        db_messages = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        test_messages = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        dev_function = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        test_function = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        dev_logs = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        test_logs = new javax.swing.JCheckBox();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        db_logs = new javax.swing.JCheckBox();
        search_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        update_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(900, 700));
        setName("Preferencias"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        north_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        north_panel.setLayout(new java.awt.BorderLayout(10, 10));

        jButton8.setText("jButton8");
        jButton8.setPreferredSize(new java.awt.Dimension(100, 30));
        north_panel.add(jButton8, java.awt.BorderLayout.WEST);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton1.setText("Actualizar");
        jPanel3.add(jButton1);

        jButton2.setText("Consultar");
        jPanel3.add(jButton2);

        north_panel.add(jPanel3, java.awt.BorderLayout.CENTER);

        jButton9.setText("jButton9");
        jButton9.setPreferredSize(new java.awt.Dimension(100, 30));
        north_panel.add(jButton9, java.awt.BorderLayout.EAST);

        add(north_panel, java.awt.BorderLayout.PAGE_START);

        root_panel.setLayout(new java.awt.BorderLayout());

        register_panel.setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel1.setLayout(new java.awt.GridLayout(15, 0));

        jLabel3.setText("HORA DE APERTURA");
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel1.add(jLabel3);

        jLabel4.setText("HORA DE CIERRE");
        jPanel1.add(jLabel4);

        jLabel5.setText("DÍA DE COBRO");
        jPanel1.add(jLabel5);

        jLabel6.setText("COBRO AUTOMATICO");
        jPanel1.add(jLabel6);

        jLabel7.setText("USUARIO MAESTRO");
        jPanel1.add(jLabel7);

        jLabel8.setText("CONTRASEÑA MAESTRA");
        jPanel1.add(jLabel8);

        jLabel9.setText("VALIDAR HORA");
        jPanel1.add(jLabel9);

        register_panel.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setLayout(new java.awt.GridLayout(15, 0));

        open_hour_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("kk:mm:ss"))));
        open_hour_field.setName("HORA_DE_APERTURA"); // NOI18N
        jPanel2.add(open_hour_field);

        close_hour_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("kk:mm:ss"))));
        close_hour_field.setName("HORA_DE_CIERRE"); // NOI18N
        jPanel2.add(close_hour_field);

        last_pay_day_field.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        last_pay_day_field.setName("ULTIMO_DIA_DE_PAGO"); // NOI18N
        jPanel2.add(last_pay_day_field);

        auto_pay_field.setToolTipText("");
        auto_pay_field.setName("RECARGO_AUTOMATICO"); // NOI18N
        jPanel2.add(auto_pay_field);

        master_user_field.setEditable(false);
        master_user_field.setName("USUARIO_MAESTRO"); // NOI18N
        jPanel2.add(master_user_field);

        master_password_field.setEditable(false);
        master_password_field.setName("CONTRASEÑA_MAESTRA"); // NOI18N
        jPanel2.add(master_password_field);

        hour_validate_field.setName("VALIDAR_HORA_DE_ENTRADA"); // NOI18N
        jPanel2.add(hour_validate_field);

        register_panel.add(jPanel2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Configuraciones Generales", register_panel);

        jPanel7.setLayout(new java.awt.GridLayout(12, 0));

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("MENSAJES_DEV");
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel8.add(jLabel1, java.awt.BorderLayout.LINE_START);

        dev_messages.setName("MENSAJES_DEV"); // NOI18N
        jPanel8.add(dev_messages, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("MENSAJES_DB");
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel9.add(jLabel2, java.awt.BorderLayout.LINE_START);

        db_messages.setName("MENSAJES_DB"); // NOI18N
        jPanel9.add(db_messages, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("MENSAJES_TEST");
        jLabel10.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel10.add(jLabel10, java.awt.BorderLayout.LINE_START);

        test_messages.setName("MENSAJES_TEST"); // NOI18N
        jPanel10.add(test_messages, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel10);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("FUNCIONES_DEV");
        jLabel11.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel11.add(jLabel11, java.awt.BorderLayout.LINE_START);

        dev_function.setName("FUNCIONES_DEV"); // NOI18N
        jPanel11.add(dev_function, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel11);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("FUNCIONES_TEST");
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel12.add(jLabel12, java.awt.BorderLayout.LINE_START);

        test_function.setName("FUNCIONES_TEST"); // NOI18N
        jPanel12.add(test_function, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel13.setText("LOGS_DEV");
        jLabel13.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel13.add(jLabel13, java.awt.BorderLayout.LINE_START);

        dev_logs.setName("LOGS_DEV"); // NOI18N
        jPanel13.add(dev_logs, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel14.setText("LOGS_TEST");
        jLabel14.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel14.add(jLabel14, java.awt.BorderLayout.LINE_START);

        test_logs.setName("LOGS_TEST"); // NOI18N
        jPanel14.add(test_logs, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel14);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("LOGS_DB");
        jLabel15.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel15.add(jLabel15, java.awt.BorderLayout.LINE_START);

        db_logs.setName("LOGS_DB"); // NOI18N
        db_logs.setOpaque(true);
        jPanel15.add(db_logs, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel15);

        jTabbedPane1.addTab("Funciones experimentales", jPanel7);

        search_panel.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel5.setPreferredSize(new java.awt.Dimension(880, 30));
        jPanel5.setLayout(new java.awt.BorderLayout(10, 10));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jButton4, java.awt.BorderLayout.LINE_START);

        jPanel6.setPreferredSize(new java.awt.Dimension(200, 40));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        jPanel6.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        jPanel6.add(jButton7);

        jPanel5.add(jPanel6, java.awt.BorderLayout.LINE_END);

        jTextField3.setText("jTextField3");
        jPanel5.add(jTextField3, java.awt.BorderLayout.CENTER);

        search_panel.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Variable", "Valor", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        search_panel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Parametros", search_panel);

        root_panel.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        update_button.setText("Actualizar");
        jPanel4.add(update_button);

        cancel_button.setText("Cancelar");
        jPanel4.add(cancel_button);

        root_panel.add(jPanel4, java.awt.BorderLayout.SOUTH);

        add(root_panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox auto_pay_field;
    private javax.swing.JButton cancel_button;
    private javax.swing.JFormattedTextField close_hour_field;
    private javax.swing.JCheckBox db_logs;
    private javax.swing.JCheckBox db_messages;
    private javax.swing.JCheckBox dev_function;
    private javax.swing.JCheckBox dev_logs;
    private javax.swing.JCheckBox dev_messages;
    private javax.swing.JCheckBox hour_validate_field;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JSpinner last_pay_day_field;
    private javax.swing.JTextField master_password_field;
    private javax.swing.JTextField master_user_field;
    private javax.swing.JPanel north_panel;
    private javax.swing.JFormattedTextField open_hour_field;
    private javax.swing.JPanel register_panel;
    private javax.swing.JPanel root_panel;
    private javax.swing.JPanel search_panel;
    private javax.swing.JCheckBox test_function;
    private javax.swing.JCheckBox test_logs;
    private javax.swing.JCheckBox test_messages;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean isValuesOk() {
        return true;
    }

    @Override
    public Map<String, String> getValues(boolean update) {
        Map<String, String> currentValues = new HashMap<>();

        // Mapeo directo de cada campo de la UI a su nombre de parámetro
        // Esto hace que el código sea legible y robusto.
        currentValues.put("open_hour".toUpperCase(), open_hour_field.getText());
        currentValues.put("close_hour".toUpperCase(), close_hour_field.getText());
        currentValues.put("last_pay_day".toUpperCase(), String.valueOf(last_pay_day_field.getValue()));
        currentValues.put("auto_pay".toUpperCase(), String.valueOf(auto_pay_field.isSelected()));
        currentValues.put("master_user".toUpperCase(), master_user_field.getText());
        currentValues.put("master_password".toUpperCase(), master_password_field.getText());
        currentValues.put("hour_validate".toUpperCase(), String.valueOf(hour_validate_field.isSelected()));
        currentValues.put("dev_messages".toUpperCase(), String.valueOf(dev_messages.isSelected()));
        currentValues.put("dev_function".toUpperCase(), String.valueOf(dev_function.isSelected()));
        currentValues.put("test_messages".toUpperCase(), String.valueOf(test_messages.isSelected()));
        currentValues.put("test_function".toUpperCase(), String.valueOf(test_function.isSelected()));
        currentValues.put("db_messages".toUpperCase(), String.valueOf(db_messages.isSelected()));
        currentValues.put("dev_logs".toUpperCase(), String.valueOf(dev_logs.isSelected()));
        currentValues.put("test_logs".toUpperCase(), String.valueOf(test_logs.isSelected()));
        currentValues.put("db_logs".toUpperCase(), String.valueOf(db_logs.isSelected()));

        Map<String, String> map = new HashMap<>(20);

        // Iterar sobre los valores actuales para verificar cambios
        for (Map.Entry<String, String> entry : currentValues.entrySet()) {
            String paramName = entry.getKey();
            String currentValue = entry.getValue();
            String originalValue = parameters_map.get(paramName);
            System.out.println(currentValue + "!=" + (originalValue));
            // Lógica de filtro: añadir al mapa solo si el valor ha cambiado.
            if (!currentValue.equals(originalValue)) {
                map.put(paramName, currentValue);
            }
        }
        return map;
    }

}
