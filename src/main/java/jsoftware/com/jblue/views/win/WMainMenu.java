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
package jsoftware.com.jblue.views.win;

import jsoftware.com.jblue.controllers.winc.MainController;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.views.components.UserViewComponent;
import jsoftware.com.jblue.views.framework.AbstractAppWindows;
import jsoftware.com.jblue.views.ParametersView;
import jsoftware.com.jblue.views.StreetsView;
import jsoftware.com.jblue.views.OtherPaymentTypesView;
import jsoftware.com.jblue.views.OtherPaymentsView;
import jsoftware.com.jblue.views.ProcessView;
import jsoftware.com.jblue.views.WaterIntakesTypesView;
import jsoftware.com.jblue.views.UserView;
import jsoftware.com.jblue.views.ShopCartView;
import jsoftware.com.jblue.views.SurchargePaymentsView;
import jsoftware.com.jblue.views.UserConsumerView;
import jsoftware.com.jblue.views.WaterIntakesView;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import jsoftware.com.jblue.views.AdministrationHistoryView;

/**
 *
 * @author juan-campos
 */
public final class WMainMenu extends AbstractAppWindows {

    //
    private final CardLayout ly;
    //Vistas de la base de datos
    private final ShopCartView shop_cart_view;
    private final UserView users_view;
    private final StreetsView street_view;
    private final WaterIntakesTypesView water_intakes_type_view;
    private final WaterIntakesView water_intakes;
    private final OtherPaymentTypesView other_payments_types_view;
    private final OtherPaymentsView other_payments_view;
    private final SurchargePaymentsView surcharge_payments_view;
    private final ProcessView process_view;
    private final UserConsumerView user_consumer;
    //
    private final ParametersView flag_view;
    //
    private final AdministrationHistoryView administration_history_view;
    private final LoginWindows LOGIN;
    private final AboutUs ABOUT;
    private final ProfileWindow PROFILE;
    //
    private UserViewComponent showVisor;
    //
    MainController controller;

    /**
     * Creates new form NewMenuPrincipal
     *
     * @param LOGIN
     */
    public WMainMenu(LoginWindows LOGIN) {
        initComponents();
        this.LOGIN = LOGIN;
        this.ABOUT = new AboutUs();
        this.PROFILE = new ProfileWindow();
        shop_cart_view = new ShopCartView();
        users_view = new UserView();
        street_view = new StreetsView();
        water_intakes_type_view = new WaterIntakesTypesView();
        water_intakes = new WaterIntakesView();
        other_payments_types_view = new OtherPaymentTypesView();
        other_payments_view = new OtherPaymentsView();
        surcharge_payments_view = new SurchargePaymentsView();
        process_view = new ProcessView();
        user_consumer = new UserConsumerView();
        administration_history_view = new AdministrationHistoryView();
        //
        flag_view = ParametersView.getInstance();
        //
        ly = (CardLayout) views_panel.getLayout();
        updateTitle(shop_cart_view.getName());
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
        views_panel.add(shop_cart_view, shop_cart_view.getName());
        views_panel.add(users_view, users_view.getName());
        views_panel.add(street_view, street_view.getName());
        views_panel.add(water_intakes_type_view, water_intakes_type_view.getName());
        views_panel.add(water_intakes, water_intakes.getName());
        views_panel.add(other_payments_types_view, other_payments_types_view.getName());
        views_panel.add(other_payments_view, other_payments_view.getName());
        views_panel.add(surcharge_payments_view, surcharge_payments_view.getName());
        views_panel.add(flag_view, flag_view.getName());
        views_panel.add(process_view, process_view.getName());
        views_panel.add(user_consumer, user_consumer.getName());
        views_panel.add(administration_history_view, administration_history_view.getName());

    }
    
    @Override
    public void events() {
        controller = new MainController(this);
        exit_button.addActionListener(controller);
        btn_home.addActionListener(controller);
        btn_usuarios.addActionListener(controller);
        btn_calles.addActionListener(controller);
        btn_tipo_tomas.addActionListener(controller);
        btn_tipo_pagos.addActionListener(controller);
        //Items del menu "Base de datos".
        users_view_item.addActionListener(controller);
        street_view_item.addActionListener(controller);
        water_intakes_view_item.addActionListener(controller);
        water_intakes_types_view_item.addActionListener(controller);
        other_type_payments_view_item.addActionListener(controller);
        other_payments_view_item.addActionListener(controller);
        surcharge_payments_view_item.addActionListener(controller);
        service_payments_view_item.addActionListener(controller);
        user_consumer_item.addActionListener(controller);
        process_item.addActionListener(controller);
        //
        parameters_view_item.addActionListener(controller);
        //
        about_item_view.addActionListener(controller);
        profile_item_view.addActionListener(controller);
        //
        administration_history_view_item.addActionListener(controller);
    }
    
    @Override
    public void initialState() {
        
    }
    
    @Override
    public void finalState() {
    }
    
    public void goToHome() {
        ly.show(views_panel, shop_cart_view.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        left_panel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        label_title = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_home = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btn_usuarios = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btn_calles = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        btn_tipo_tomas = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btn_tipo_pagos = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        exit_button = new javax.swing.JButton();
        center_panel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        views_panel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        profile_item_view = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        administration_history_view_item = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        parameters_view_item = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        users_view_item = new javax.swing.JMenuItem();
        user_consumer_item = new javax.swing.JMenuItem();
        water_intakes_view_item = new javax.swing.JMenuItem();
        water_intakes_types_view_item = new javax.swing.JMenuItem();
        street_view_item = new javax.swing.JMenuItem();
        service_payments_view_item = new javax.swing.JMenuItem();
        surcharge_payments_view_item = new javax.swing.JMenuItem();
        other_type_payments_view_item = new javax.swing.JMenuItem();
        other_payments_view_item = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        process_item = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        about_item_view = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        left_panel.setPreferredSize(new java.awt.Dimension(300, 700));
        left_panel.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        label_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_title.setText("Inicio");
        jPanel3.add(label_title, java.awt.BorderLayout.CENTER);

        left_panel.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x128/usuario.png"))); // NOI18N
        jLabel2.setToolTipText("Foto del personal");
        jLabel2.setPreferredSize(new java.awt.Dimension(41, 200));
        jPanel4.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel2.setLayout(new java.awt.BorderLayout());

        btn_home.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        btn_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/home.png"))); // NOI18N
        btn_home.setText("Inicio");
        btn_home.setToolTipText("Inicio");
        btn_home.setHideActionText(true);
        btn_home.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_home.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(btn_home, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel2);

        jPanel6.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel6.setLayout(new java.awt.BorderLayout());

        btn_usuarios.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        btn_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/user_x32.png"))); // NOI18N
        btn_usuarios.setText("Usuarios");
        btn_usuarios.setToolTipText("Usuarios");
        btn_usuarios.setHideActionText(true);
        btn_usuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_usuarios.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel6.add(btn_usuarios, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6);

        jPanel7.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel7.setLayout(new java.awt.BorderLayout());

        btn_calles.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        btn_calles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/calles.png"))); // NOI18N
        btn_calles.setText("Calles");
        btn_calles.setToolTipText("Calles");
        btn_calles.setHideActionText(true);
        btn_calles.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_calles.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel7.add(btn_calles, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        btn_tipo_tomas.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        btn_tipo_tomas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/water_intake_x32.png"))); // NOI18N
        btn_tipo_tomas.setText("Tipo de tomas");
        btn_tipo_tomas.setToolTipText("Tipo de tomas");
        btn_tipo_tomas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_tipo_tomas.setHideActionText(true);
        btn_tipo_tomas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_tipo_tomas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel8.add(btn_tipo_tomas, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        btn_tipo_pagos.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        btn_tipo_pagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/tipo de pago.png"))); // NOI18N
        btn_tipo_pagos.setText("Otros tipos de pagos");
        btn_tipo_pagos.setToolTipText("Tipo de pagos");
        btn_tipo_pagos.setHideActionText(true);
        btn_tipo_pagos.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn_tipo_pagos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel9.add(btn_tipo_pagos, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel1.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel10.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel10);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel3.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel11.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel11);

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        left_panel.add(jPanel4, java.awt.BorderLayout.CENTER);

        exit_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerrar-sesion.png"))); // NOI18N
        exit_button.setText("Salir");
        exit_button.setToolTipText("Salir");
        exit_button.setActionCommand("OUT");
        exit_button.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        exit_button.setPreferredSize(new java.awt.Dimension(100, 50));
        left_panel.add(exit_button, java.awt.BorderLayout.SOUTH);

        getContentPane().add(left_panel, java.awt.BorderLayout.LINE_START);

        center_panel.setLayout(new java.awt.BorderLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(10, 10));
        center_panel.add(jSeparator1, java.awt.BorderLayout.WEST);

        views_panel.setMinimumSize(new java.awt.Dimension(900, 700));
        views_panel.setPreferredSize(new java.awt.Dimension(900, 700));
        views_panel.setLayout(new java.awt.CardLayout());
        center_panel.add(views_panel, java.awt.BorderLayout.CENTER);

        getContentPane().add(center_panel, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Menu");

        profile_item_view.setText("Perfil");
        jMenu1.add(profile_item_view);

        jMenuItem6.setText("Panel de Admin");
        jMenuItem6.setToolTipText("Panel de Administracion");
        jMenu1.add(jMenuItem6);

        administration_history_view_item.setText("Administracion");
        administration_history_view_item.setName("Administracion"); // NOI18N
        jMenu1.add(administration_history_view_item);

        jMenuItem8.setText("Exportar");
        jMenu1.add(jMenuItem8);

        jMenuItem7.setText("Importar");
        jMenu1.add(jMenuItem7);

        parameters_view_item.setText("Preferencias");
        jMenu1.add(parameters_view_item);
        jMenu1.add(jSeparator2);

        jMenuItem19.setText("Salir");
        jMenuItem19.setActionCommand("OUT");
        jMenu1.add(jMenuItem19);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Base de datos");

        jMenu5.setText("Catalogos");

        jMenuItem1.setText("Tipo de usuarios");
        jMenu5.add(jMenuItem1);

        jMenuItem3.setText("Tipo de status");
        jMenu5.add(jMenuItem3);

        jMenu2.add(jMenu5);

        users_view_item.setText("Usuarios");
        jMenu2.add(users_view_item);

        user_consumer_item.setText("Consumidores");
        jMenu2.add(user_consumer_item);

        water_intakes_view_item.setText("Tomas Registradas");
        jMenu2.add(water_intakes_view_item);

        water_intakes_types_view_item.setText("Tipo de tomas");
        jMenu2.add(water_intakes_types_view_item);

        street_view_item.setText("Calles");
        jMenu2.add(street_view_item);

        service_payments_view_item.setText("Pagos por el Servicio");
        service_payments_view_item.setActionCommand("Inicio");
        service_payments_view_item.setName("Inicio"); // NOI18N
        jMenu2.add(service_payments_view_item);

        surcharge_payments_view_item.setText("Recargos");
        jMenu2.add(surcharge_payments_view_item);

        other_type_payments_view_item.setText("Otros tipos de pagos");
        jMenu2.add(other_type_payments_view_item);

        other_payments_view_item.setText("Otros pagos");
        jMenu2.add(other_payments_view_item);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Tramites");

        process_item.setText("Alta de toma");
        jMenu4.add(process_item);

        jMenuItem2.setText("Cambio de titular");
        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Ayuda");

        about_item_view.setText("Acerca de");
        jMenu3.add(about_item_view);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_item_view;
    private javax.swing.JMenuItem administration_history_view_item;
    private javax.swing.JButton btn_calles;
    private javax.swing.JButton btn_home;
    private javax.swing.JButton btn_tipo_pagos;
    private javax.swing.JButton btn_tipo_tomas;
    private javax.swing.JButton btn_usuarios;
    private javax.swing.JPanel center_panel;
    private javax.swing.JButton exit_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel label_title;
    private javax.swing.JPanel left_panel;
    private javax.swing.JMenuItem other_payments_view_item;
    private javax.swing.JMenuItem other_type_payments_view_item;
    private javax.swing.JMenuItem parameters_view_item;
    private javax.swing.JMenuItem process_item;
    private javax.swing.JMenuItem profile_item_view;
    private javax.swing.JMenuItem service_payments_view_item;
    private javax.swing.JMenuItem street_view_item;
    private javax.swing.JMenuItem surcharge_payments_view_item;
    private javax.swing.JMenuItem user_consumer_item;
    private javax.swing.JMenuItem users_view_item;
    private javax.swing.JPanel views_panel;
    private javax.swing.JMenuItem water_intakes_types_view_item;
    private javax.swing.JMenuItem water_intakes_view_item;
    // End of variables declaration//GEN-END:variables
    public static final String OUT = "OUT";
    
    @Override
    public void dispose() {
        closeWindows();
        super.dispose(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if (showVisor != null && showVisor.isVisible()) {
            showVisor.dispose();
        }
        
        SystemSession.getInstancia().setUser(null);
        SwingUtilities.invokeLater(() -> {
            LOGIN.setVisible(true);
        });
    }
    
    public CardLayout getCardLayout() {
        return ly;
    }
    
    public JLabel getLabelTitle() {
        return label_title;
    }
    
    public JPanel getViewsPanel() {
        return views_panel;
    }
    
    public AboutUs getABOUT() {
        return ABOUT;
    }
    
    public ProfileWindow getProfileWindow() {
        return PROFILE;
    }
    
    public void closeWindows() {
        ABOUT.dispose();
        PROFILE.dispose();
    }
    
}
