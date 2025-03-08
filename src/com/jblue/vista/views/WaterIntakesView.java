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
import com.jblue.controlador.compc.TableController;
import com.jblue.modelo.ConstGs;
import com.jblue.modelo.fabricas.FactoryCache;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.Filtros;
import com.jblue.vista.marco.DBValues;
import com.jblue.vista.marco.vistas.DBView;
import com.jutil.swingw.modelos.JTableModel;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.jblue.vista.marco.TableSearchView;

/**
 *
 * @author juan-campos
 */
public final class WaterIntakesView extends DBView implements DBValues, TableSearchView {

    private final CardLayout ly;
    private final JTableModel model;
    private OTipoTomas object_search;

    /**
     * Creates new form NewTipoDeTomas
     */
    public WaterIntakesView() {
        initComponents();
        ly = (CardLayout) root_panel.getLayout();
        ly.show(root_panel, register_panel.getName());
        model = new JTableModel(ConstGs.TABLA_TIPOS_DE_TOMAS, 0);
        controller = FactoryController.getWaterIntakesController(this);
        table_controller = new TableController(this, FactoryCache.TIPO_DE_TOMAS);
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
    public void components() {
        objects_table.setModel(model);
    }

    @Override
    public void events() {
        back_button.addActionListener(table_controller);
        next_button.addActionListener(table_controller);
        reload_button.addActionListener(table_controller);
        objects_table.addMouseListener(table_controller);
        save_button.addActionListener(controller);
        update_button.addActionListener(controller);
        delete_button.addActionListener(controller);
        cancel_button.addActionListener(controller);
        register_button.addActionListener(table_controller);
        search_button.addActionListener(table_controller);
    }

    @Override
    public OTipoTomas getObjectSearch() {
        return object_search;
    }

    @Override
    public void setObjectSearch(Objeto o) {
        object_search = (OTipoTomas) o;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tools_panel = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel15 = new javax.swing.JPanel();
        register_button = new javax.swing.JButton();
        search_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        root_panel = new javax.swing.JPanel();
        register_panel = new javax.swing.JPanel();
        panel_campos = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campo_tipo = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        campo_costo = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        campo_recargo = new javax.swing.JTextField();
        options_panel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        save_button = new javax.swing.JButton();
        update_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();
        search_panel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        reload_button = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search_field = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        back_button = new javax.swing.JButton();
        next_button = new javax.swing.JButton();
        panel_izq = new javax.swing.JPanel();
        tabla_usuarios = new javax.swing.JScrollPane();
        objects_table = new javax.swing.JTable();
        status_bar_panel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        range = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();

        setName("Tipo de Tomas"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 700));
        setLayout(new java.awt.BorderLayout());

        tools_panel.setPreferredSize(new java.awt.Dimension(900, 30));
        tools_panel.setLayout(new java.awt.BorderLayout(10, 10));

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/search.png"))); // NOI18N
        jToggleButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jToggleButton2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/desbloquear.png"))); // NOI18N
        tools_panel.add(jToggleButton2, java.awt.BorderLayout.WEST);

        jPanel15.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        register_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        register_button.setText("Registrar Tipo de Tomas");
        register_button.setActionCommand("register_view");
        jPanel15.add(register_button);

        search_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        search_button.setText("Consultar los Tipos de Tomas");
        search_button.setActionCommand("search_view");
        jPanel15.add(search_button);

        tools_panel.add(jPanel15, java.awt.BorderLayout.CENTER);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/configuraciones.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        tools_panel.add(jButton2, java.awt.BorderLayout.EAST);

        add(tools_panel, java.awt.BorderLayout.NORTH);

        root_panel.setLayout(new java.awt.CardLayout(10, 10));

        register_panel.setName("register"); // NOI18N
        register_panel.setLayout(new java.awt.BorderLayout());

        panel_campos.setPreferredSize(new java.awt.Dimension(500, 620));
        panel_campos.setLayout(new java.awt.GridLayout(13, 0, 0, 10));

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Datos del tipo de toma");
        jLabel4.setPreferredSize(new java.awt.Dimension(500, 100));
        panel_campos.add(jLabel4);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel2.setText("Tipo de toma:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel8.add(jLabel2, java.awt.BorderLayout.WEST);

        campo_tipo.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        campo_tipo.setName("Tipo de toma"); // NOI18N
        jPanel8.add(campo_tipo, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel3.setText("Costo:");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel9.add(jLabel3, java.awt.BorderLayout.WEST);

        campo_costo.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        campo_costo.setName("Costo"); // NOI18N
        jPanel9.add(campo_costo, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel5.setText("Costo de recargo:");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel10.add(jLabel5, java.awt.BorderLayout.WEST);

        campo_recargo.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        campo_recargo.setName("Costo del recargo"); // NOI18N
        jPanel10.add(campo_recargo, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel10);

        register_panel.add(panel_campos, java.awt.BorderLayout.CENTER);

        options_panel.setPreferredSize(new java.awt.Dimension(500, 80));
        options_panel.setLayout(new java.awt.GridLayout(2, 0, 10, 10));

        jPanel3.setLayout(new java.awt.GridLayout(1, 3, 10, 10));

        save_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        save_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/disquete.png"))); // NOI18N
        save_button.setText("Guardar");
        jPanel3.add(save_button);

        update_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        update_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/sincronizar.png"))); // NOI18N
        update_button.setText("Actualizar");
        jPanel3.add(update_button);

        delete_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        delete_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/eliminar.png"))); // NOI18N
        delete_button.setText("Eliminar");
        jPanel3.add(delete_button);

        options_panel.add(jPanel3);

        cancel_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        cancel_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        cancel_button.setText("Cancelar");
        options_panel.add(cancel_button);

        register_panel.add(options_panel, java.awt.BorderLayout.SOUTH);

        root_panel.add(register_panel, "register");

        search_panel.setName("consult"); // NOI18N
        search_panel.setLayout(new java.awt.BorderLayout(5, 5));

        jPanel5.setMinimumSize(new java.awt.Dimension(100, 30));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel5.setLayout(new java.awt.BorderLayout(5, 5));

        reload_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        reload_button.setActionCommand("reload");
        reload_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel5.add(reload_button, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/search.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel6.add(jLabel1, java.awt.BorderLayout.WEST);
        jPanel6.add(search_field, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        back_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        back_button.setActionCommand("back");
        back_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(back_button);

        next_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        next_button.setActionCommand("next");
        next_button.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(next_button);

        jPanel5.add(jPanel11, java.awt.BorderLayout.EAST);

        search_panel.add(jPanel5, java.awt.BorderLayout.NORTH);

        panel_izq.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_izq.setLayout(new java.awt.BorderLayout(10, 10));

        objects_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        objects_table.setShowGrid(true);
        tabla_usuarios.setViewportView(objects_table);

        panel_izq.add(tabla_usuarios, java.awt.BorderLayout.CENTER);

        search_panel.add(panel_izq, java.awt.BorderLayout.CENTER);

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
    private javax.swing.JButton back_button;
    private javax.swing.JTextField campo_costo;
    private javax.swing.JTextField campo_recargo;
    private javax.swing.JTextField campo_tipo;
    private javax.swing.JButton cancel_button;
    private javax.swing.JLabel count;
    private javax.swing.JButton delete_button;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JButton next_button;
    private javax.swing.JTable objects_table;
    private javax.swing.JPanel options_panel;
    private javax.swing.JPanel panel_campos;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JLabel range;
    private javax.swing.JButton register_button;
    private javax.swing.JPanel register_panel;
    private javax.swing.JButton reload_button;
    private javax.swing.JPanel root_panel;
    private javax.swing.JButton save_button;
    private javax.swing.JButton search_button;
    private javax.swing.JTextField search_field;
    private javax.swing.JPanel search_panel;
    private javax.swing.JPanel status_bar_panel;
    private javax.swing.JScrollPane tabla_usuarios;
    private javax.swing.JPanel tools_panel;
    private javax.swing.JLabel total;
    private javax.swing.JButton update_button;
    // End of variables declaration//GEN-END:variables

    public OTipoTomas getObject() {
        return object_search;
    }

    @Override
    public JTextField getTextComponenteTable() {
        return search_field;
    }

    @Override
    public String getTextSearchTable() {
        return Filtros.limpiar(search_field.getText());
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
    public boolean isValuesOk() {
        boolean ok = true;
        JTextField[] text_fields = {
            campo_tipo, campo_costo, campo_recargo
        };
        for (JTextField i : text_fields) {
            if (Filtros.isNullOrBlank(i.getText())) {
                JOptionPane.showMessageDialog(this, "El campo %s no es valido", "Campo no valido", JOptionPane.ERROR_MESSAGE);
                ok = false;
                break;
            }
        }
        return ok;
    }

    @Override
    public String[] getDbValues() {
        String _type = campo_tipo.getText();
        String _cost = campo_costo.getText();
        String _fine = campo_recargo.getText();

        return new String[]{
            _type,
            _cost,
            _fine
        };
    }

    @Override
    public void setRowsData(String... info) {
        count.setText(info[0]);
        range.setText(info[1]);
        total.setText(info[2]);
    }

}
