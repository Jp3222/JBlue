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
package com.jblue.otros.news.vistas;

import com.jblue.controlador.CCobros;
import com.jblue.modelo.objetos.OUsuarios;
import com.jutil.swingw.modelos.JTableModel;
import java.awt.CardLayout;

/**
 *
 * @author juan-campos
 */
public class NewVCaja extends javax.swing.JPanel {

    private CardLayout ly;
    private JTableModel model;
    

    /**
     * Creates new form VCaja
     */
    public NewVCaja() {
        initComponents();
        
        model = new JTableModel(new String[]{
            "No.", "Usuario", "Mes Pagado"
        }, 0);
        
        table_history_paids.setModel(model);
        
        ly = (CardLayout) views_panel.getLayout();
        
        pay_button.addActionListener((ae) -> {
            ly.show(views_panel, pay_view.getName());
        });
        
        history_button.addActionListener((ae) -> {
            CCobros.printPaidsOfDay(model);
            ly.show(views_panel, history_view.getName());
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

        jPanel1 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jPanel15 = new javax.swing.JPanel();
        pay_button = new javax.swing.JButton();
        history_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        views_panel = new javax.swing.JPanel();
        pay_view = new javax.swing.JPanel();
        panel_busquedas = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buscador_lista = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_usuarios = new javax.swing.JList<>();
        panel_info = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        campo_tipo = new javax.swing.JTextField();
        btn_info_usuarios = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        campo_nombre = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        campo_toma = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        campo_costo = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        campo_meses_pag = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        chb_todos = new javax.swing.JCheckBox();
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
        btn_cobrar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btn_recargos = new javax.swing.JButton();
        btn_otros_pagos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_utilidades = new javax.swing.JButton();
        history_view = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_history_paids = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        saldo_del_dia = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        no_pagos = new javax.swing.JLabel();

        setName("Inicio"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(900, 30));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/lock.png"))); // NOI18N
        jToggleButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jToggleButton2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/desbloquear.png"))); // NOI18N
        jPanel1.add(jToggleButton2, java.awt.BorderLayout.WEST);

        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        pay_button.setText("Cobros");
        jPanel15.add(pay_button);

        history_button.setText("Pagos del dia");
        jPanel15.add(history_button);

        jPanel1.add(jPanel15, java.awt.BorderLayout.CENTER);

        jButton2.setText("jButton2");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel1.add(jButton2, java.awt.BorderLayout.EAST);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        views_panel.setLayout(new java.awt.CardLayout());

        pay_view.setName("pagos"); // NOI18N
        pay_view.setLayout(new javax.swing.BoxLayout(pay_view, javax.swing.BoxLayout.PAGE_AXIS));

        panel_busquedas.setOpaque(false);
        panel_busquedas.setPreferredSize(new java.awt.Dimension(700, 150));
        panel_busquedas.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(680, 40));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Buscador");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 19));
        jPanel6.add(jLabel1, java.awt.BorderLayout.LINE_START);
        jPanel6.add(buscador_lista, java.awt.BorderLayout.CENTER);

        panel_busquedas.add(jPanel6, java.awt.BorderLayout.NORTH);

        lista_usuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(lista_usuarios);

        panel_busquedas.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pay_view.add(panel_busquedas);

        panel_info.setOpaque(false);
        panel_info.setPreferredSize(new java.awt.Dimension(700, 400));
        panel_info.setLayout(new java.awt.BorderLayout());

        jPanel19.setOpaque(false);
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.GridLayout(4, 0, 0, 5));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Tipo de usuario");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel3.add(jLabel7, java.awt.BorderLayout.WEST);

        campo_tipo.setEditable(false);
        jPanel3.add(campo_tipo, java.awt.BorderLayout.CENTER);

        btn_info_usuarios.setText("Info de usuario");
        btn_info_usuarios.setPreferredSize(new java.awt.Dimension(150, 29));
        jPanel3.add(btn_info_usuarios, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel3);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Nombre");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel12.add(jLabel8, java.awt.BorderLayout.WEST);

        campo_nombre.setEditable(false);
        jPanel12.add(campo_nombre, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Tipo de toma");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel13.add(jLabel9, java.awt.BorderLayout.WEST);

        campo_toma.setEditable(false);
        campo_toma.setPreferredSize(new java.awt.Dimension(200, 36));
        jPanel13.add(campo_toma, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Costo");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel4.add(jLabel3, java.awt.BorderLayout.WEST);

        campo_costo.setEditable(false);
        campo_costo.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel4.add(campo_costo, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("Meses pagados");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel14.add(jLabel10, java.awt.BorderLayout.WEST);

        campo_meses_pag.setEditable(false);
        jPanel14.add(campo_meses_pag, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel14);

        jPanel19.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(700, 40));
        jPanel5.setLayout(new java.awt.BorderLayout());

        chb_todos.setText("Todos");
        chb_todos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chb_todos.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(chb_todos, java.awt.BorderLayout.EAST);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Mese a pagar");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel5.add(jLabel6, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(700, 60));
        jPanel11.setLayout(new java.awt.GridLayout(1, 12));

        ene.setText("ENE");
        ene.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ene.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ene.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ene.setPreferredSize(new java.awt.Dimension(10, 47));
        ene.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        ene.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(ene);

        feb.setText("FEB");
        feb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        feb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        feb.setPreferredSize(new java.awt.Dimension(10, 47));
        feb.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        feb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(feb);

        mar.setText("MAR");
        mar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        mar.setPreferredSize(new java.awt.Dimension(10, 47));
        mar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        mar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(mar);

        abr.setText("ABR");
        abr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        abr.setPreferredSize(new java.awt.Dimension(10, 47));
        abr.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        abr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(abr);

        may.setText("MAY");
        may.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        may.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        may.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        may.setPreferredSize(new java.awt.Dimension(10, 47));
        may.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        may.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(may);

        jun.setText("JUN");
        jun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        jun.setPreferredSize(new java.awt.Dimension(10, 47));
        jun.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        jun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(jun);

        jul.setText("JUL");
        jul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jul.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        jul.setPreferredSize(new java.awt.Dimension(10, 47));
        jul.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        jul.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(jul);

        ago.setText("AGO");
        ago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ago.setPreferredSize(new java.awt.Dimension(10, 47));
        ago.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        ago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(ago);

        sep.setText("SEP");
        sep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        sep.setPreferredSize(new java.awt.Dimension(10, 47));
        sep.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        sep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(sep);

        oct.setText("OCT");
        oct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        oct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        oct.setPreferredSize(new java.awt.Dimension(10, 47));
        oct.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        oct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(oct);

        nov.setText("NOV");
        nov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nov.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        nov.setPreferredSize(new java.awt.Dimension(10, 47));
        nov.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        nov.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(nov);

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

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Total: $");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel21.add(jLabel2, java.awt.BorderLayout.WEST);

        lbl_total.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lbl_total.setText("0.0");
        jPanel21.add(lbl_total, java.awt.BorderLayout.CENTER);

        btn_movimientos.setText("Movimientos");
        btn_movimientos.setToolTipText("");
        btn_movimientos.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel21.add(btn_movimientos, java.awt.BorderLayout.LINE_END);

        jPanel20.add(jPanel21);

        jPanel22.setOpaque(false);
        jPanel22.setLayout(new java.awt.BorderLayout(10, 0));

        Jlabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        Jlabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Jlabel1.setText("Cambio: $");
        Jlabel1.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel22.add(Jlabel1, java.awt.BorderLayout.LINE_START);

        lbl_cambio.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lbl_cambio.setText("0.0");
        jPanel22.add(lbl_cambio, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel22);

        panel_info.add(jPanel20, java.awt.BorderLayout.SOUTH);

        pay_view.add(panel_info);

        panel_operaciones.setOpaque(false);
        panel_operaciones.setPreferredSize(new java.awt.Dimension(680, 80));
        panel_operaciones.setLayout(new java.awt.GridLayout(2, 3));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        btn_cobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img5.png"))); // NOI18N
        btn_cobrar.setText("Cobrar");
        jPanel7.add(btn_cobrar);

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        jPanel7.add(btn_cancelar);

        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/limpiar.png"))); // NOI18N
        btn_limpiar.setText("Limpiar");
        jPanel7.add(btn_limpiar);

        panel_operaciones.add(jPanel7);

        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        btn_recargos.setText("Recargos");
        jPanel8.add(btn_recargos);

        btn_otros_pagos.setText("Otros Pagos");
        jPanel8.add(btn_otros_pagos);

        jButton1.setText("Pagos atrasados");
        jPanel8.add(jButton1);

        btn_utilidades.setText("Utilidades");
        jPanel8.add(btn_utilidades);

        panel_operaciones.add(jPanel8);

        pay_view.add(panel_operaciones);

        views_panel.add(pay_view, "pagos");

        history_view.setName("Historial"); // NOI18N
        history_view.setLayout(new java.awt.BorderLayout());

        table_history_paids.setModel(new javax.swing.table.DefaultTableModel(
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
        table_history_paids.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_history_paids);

        history_view.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel18.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("Total Acumulado:");
        jPanel18.add(jLabel4, java.awt.BorderLayout.LINE_START);

        saldo_del_dia.setToolTipText("Total de pagos acumulados");
        jPanel18.add(saldo_del_dia, java.awt.BorderLayout.CENTER);

        jPanel23.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel23.setLayout(new java.awt.BorderLayout());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("No.");
        jPanel23.add(jLabel5, java.awt.BorderLayout.CENTER);

        no_pagos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        no_pagos.setText("0");
        no_pagos.setToolTipText("Numero de pagos hechos.");
        no_pagos.setPreferredSize(new java.awt.Dimension(50, 16));
        jPanel23.add(no_pagos, java.awt.BorderLayout.LINE_END);

        jPanel18.add(jPanel23, java.awt.BorderLayout.EAST);

        history_view.add(jPanel18, java.awt.BorderLayout.SOUTH);

        views_panel.add(history_view, "Historial");

        add(views_panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel1;
    private javax.swing.JCheckBox abr;
    private javax.swing.JCheckBox ago;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cobrar;
    private javax.swing.JButton btn_info_usuarios;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_movimientos;
    private javax.swing.JButton btn_otros_pagos;
    private javax.swing.JButton btn_recargos;
    private javax.swing.JButton btn_utilidades;
    private javax.swing.JTextField buscador_lista;
    private javax.swing.JTextField campo_costo;
    private javax.swing.JTextField campo_meses_pag;
    private javax.swing.JTextField campo_nombre;
    private javax.swing.JTextField campo_tipo;
    private javax.swing.JTextField campo_toma;
    private javax.swing.JCheckBox chb_todos;
    private javax.swing.JCheckBox dic;
    private javax.swing.JCheckBox ene;
    private javax.swing.JCheckBox feb;
    private javax.swing.JButton history_button;
    private javax.swing.JPanel history_view;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JCheckBox jul;
    private javax.swing.JCheckBox jun;
    private javax.swing.JLabel lbl_cambio;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JList<OUsuarios> lista_usuarios;
    private javax.swing.JCheckBox mar;
    private javax.swing.JCheckBox may;
    private javax.swing.JLabel no_pagos;
    private javax.swing.JCheckBox nov;
    private javax.swing.JCheckBox oct;
    private javax.swing.JPanel panel_busquedas;
    private javax.swing.JPanel panel_info;
    private javax.swing.JPanel panel_operaciones;
    private javax.swing.JButton pay_button;
    private javax.swing.JPanel pay_view;
    private javax.swing.JLabel saldo_del_dia;
    private javax.swing.JCheckBox sep;
    private javax.swing.JTable table_history_paids;
    private javax.swing.JPanel views_panel;
    // End of variables declaration//GEN-END:variables
}
