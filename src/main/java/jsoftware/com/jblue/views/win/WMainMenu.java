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
package jsoftware.com.jblue.views.win;

import java.awt.CardLayout;
import java.awt.event.WindowListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import jsoftware.com.jblue.controllers.winc.MainController;
import jsoftware.com.jblue.model.dto.StatusDTO;
import jsoftware.com.jblue.model.factories.ProcessViewFactory;
import jsoftware.com.jblue.views.CatalogViewFactory;
import jsoftware.com.jblue.views.CatalogViewerView;
import jsoftware.com.jblue.views.PaymentConceptView;
import jsoftware.com.jblue.views.ShopCartView;
import jsoftware.com.jblue.views.UserView;
import jsoftware.com.jblue.views.components.UserViewComponent;
import jsoftware.com.jblue.views.framework.AbstractAppWindows;
import jsoftware.com.jblue.views.mod.StreetView;
import jsoftware.com.jblue.views.mod.WaterIntakeTypeView;
import jsoftware.com.jblue.views.mod.pro.ConsumerRegisterProcessView;
import jsoftware.com.jblue.views.mod.pro.OwnerChangerProcessView;
import jsoftware.com.jblue.views.mod.pro.OwnerRegisterProcessView;

/**
 * Esta clase esta dedicada a la vista del menu principal de la aplicacion
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 */
public final class WMainMenu extends AbstractAppWindows {

    private static final long serialVersionUID = 1L;

    private final ProcessViewFactory factory;
    //
    private final CardLayout ly;
    private final ShopCartView shop_cart_view;
    private final OwnerRegisterProcessView owner_register_process_view;
    private final ConsumerRegisterProcessView consumer_register_process_view;
    private final OwnerChangerProcessView owner_changer_process_view;
    private final UserView user_process_view;
    private final StreetView street_view;
    private final WaterIntakeTypeView water_intakes_type_view;
    private final PaymentConceptView payment_concept_view;
    private final CatalogViewerView<StatusDTO> status_type_view;

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
        this.factory = ProcessViewFactory.getInstance();
        this.LOGIN = LOGIN;
        this.ABOUT = new AboutUs();
        this.PROFILE = new ProfileWindow();
        shop_cart_view = factory.getShopCarProcess();
        //PROCESO DE REGISTRO DE TITULAR
        owner_register_process_view = factory.getUserRegisterProcess();
        //PROCESO DE REGISTRO DE CONSUMIDOR
        consumer_register_process_view = factory.getConsumerRegisterProcess();
        //PROCESO DE CAMBIO DE PROPIETARIO
        owner_changer_process_view = factory.getOwnerChangerProcess();
        //PROCESO DE USUARIO
        user_process_view = factory.getUserProcess();
        //PROCESO DE CALLES
        street_view = factory.getStreetView();
        //PROCESO DE TIPO DE TOMAS DE AGUA POTABLE
        water_intakes_type_view = factory.getIntakeTypeView();
        //PROCESO DE CONCEPTOS DE COBRO
        payment_concept_view = new PaymentConceptView();
        //
        status_type_view = CatalogViewFactory.getStatusType(false, "Tipo de status");
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
        views_panel.add(owner_register_process_view, owner_register_process_view.getName());
        views_panel.add(consumer_register_process_view, consumer_register_process_view.getName());
        views_panel.add(owner_changer_process_view, owner_changer_process_view.getName());
        views_panel.add(user_process_view, user_process_view.getName());
        views_panel.add(street_view, street_view.getName());
        views_panel.add(water_intakes_type_view, water_intakes_type_view.getName());
        views_panel.add(payment_concept_view, payment_concept_view.getName());
        views_panel.add(status_type_view, status_type_view.getName());

    }

    @Override
    public void events() {
        controller = new MainController(this);
        addWindowListener((WindowListener) controller);
        exit_button.addActionListener(controller);
        btn_home.addActionListener(controller);
        //btn_usuarios.addActionListener(controller);
        btn_calles.addActionListener(controller);
        btn_tipo_tomas.addActionListener(controller);
        btn_tipo_pagos.addActionListener(controller);
        //Items del menu "Base de datos".
        users_view_item.addActionListener(controller);
        street_view_item.addActionListener(controller);
        water_intakes_view_item.addActionListener(controller);
        water_intakes_types_view_item.addActionListener(controller);
        service_payments_view_item.addActionListener(controller);
        user_consumer_item.addActionListener(controller);
        owner_register_process_item.addActionListener(controller);
        consumer_register_process_item.addActionListener(controller);
        status_type_item.addActionListener(controller);
        //
        parameters_view_item.addActionListener(controller);
        //
        about_item_view.addActionListener(controller);
        profile_item_view.addActionListener(controller);
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
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        parameters_view_item = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        status_type_item = new javax.swing.JMenuItem();
        street_view_item = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        water_intakes_types_view_item = new javax.swing.JMenuItem();
        users_view_item = new javax.swing.JMenuItem();
        user_consumer_item = new javax.swing.JMenuItem();
        water_intakes_view_item = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        service_payments_view_item = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        owner_register_process_item = new javax.swing.JMenuItem();
        consumer_register_process_item = new javax.swing.JMenuItem();
        owner_change_process_item = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
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
        btn_tipo_pagos.setText("Conceptos de pagos");
        btn_tipo_pagos.setToolTipText("Tipo de pagos");
        btn_tipo_pagos.setActionCommand("Conceptos de pago");
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

        jMenu10.setText("Administracion");

        jMenu11.setText("Empleados");

        jMenuItem15.setText("Registro de empleados");
        jMenu11.add(jMenuItem15);

        jMenuItem20.setText("Modificacion de Informacion");
        jMenu11.add(jMenuItem20);

        jMenuItem21.setText("Cambio de Contraseña");
        jMenu11.add(jMenuItem21);

        jMenu10.add(jMenu11);

        jMenuItem17.setText("Administracion Actual");
        jMenu10.add(jMenuItem17);

        jMenuItem16.setText("Historial de Movimientos");
        jMenu10.add(jMenuItem16);

        jMenuItem18.setText("Balance");
        jMenu10.add(jMenuItem18);

        jMenu1.add(jMenu10);

        jMenu12.setText("Herramientas de informacion.");

        jMenuItem8.setText("Exportar");
        jMenu12.add(jMenuItem8);

        jMenuItem7.setText("Importar");
        jMenu12.add(jMenuItem7);

        jMenu1.add(jMenu12);

        parameters_view_item.setText("Preferencias");
        jMenu1.add(parameters_view_item);
        jMenu1.add(jSeparator2);

        jMenuItem19.setText("Salir");
        jMenuItem19.setActionCommand("OUT");
        jMenu1.add(jMenuItem19);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Base de datos");

        jMenu5.setText("Catalogos");

        jMenuItem1.setText("Tipo de Usuarios");
        jMenu5.add(jMenuItem1);

        status_type_item.setText("Tipos de Status del Sistema");
        jMenu5.add(status_type_item);

        street_view_item.setText("Calles Registradas");
        jMenu5.add(street_view_item);

        jMenu2.add(jMenu5);

        jMenu7.setText("Tomas de agua potable.");

        water_intakes_types_view_item.setText("Tipos de Tomas de Agua");
        jMenu7.add(water_intakes_types_view_item);

        users_view_item.setText("Padron de Usuarios.");
        jMenu7.add(users_view_item);

        user_consumer_item.setText("Padron de Consumidores");
        jMenu7.add(user_consumer_item);

        water_intakes_view_item.setText("Padron de Tomas de Agua");
        jMenu7.add(water_intakes_view_item);

        jMenu2.add(jMenu7);

        jMenu8.setText("Pagos");

        jMenuItem12.setText("Concepto de Pagos");
        jMenu8.add(jMenuItem12);

        service_payments_view_item.setText("Pagos Registrados");
        service_payments_view_item.setActionCommand("Inicio");
        service_payments_view_item.setName("Inicio"); // NOI18N
        jMenu8.add(service_payments_view_item);

        jMenuItem3.setText("Detalle de Pagos");
        jMenu8.add(jMenuItem3);

        jMenu2.add(jMenu8);

        jMenu9.setText("Empleados");

        jMenuItem14.setText("Informacion de Empleados.");
        jMenu9.add(jMenuItem14);

        jMenuItem13.setText("Padron de Empleados");
        jMenu9.add(jMenuItem13);

        jMenu2.add(jMenu9);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Tramites");

        jMenuItem2.setText("Tramites");
        jMenu4.add(jMenuItem2);

        owner_register_process_item.setText("Alta de nuevo titular");
        owner_register_process_item.setActionCommand("Registro de titular");
        jMenu4.add(owner_register_process_item);

        consumer_register_process_item.setText("Alta de nuevo consumidor");
        consumer_register_process_item.setActionCommand("Registro de consumidor");
        jMenu4.add(consumer_register_process_item);

        owner_change_process_item.setText("Cambio de titular");
        jMenu4.add(owner_change_process_item);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Auditorias");

        jMenuItem4.setText("Tramites Realizados");
        jMenu6.add(jMenuItem4);

        jMenuItem5.setText("Historial de usuarios");
        jMenu6.add(jMenuItem5);

        jMenuItem9.setText("Pagos por el servicio");
        jMenu6.add(jMenuItem9);

        jMenuItem10.setText("Pagos por recargos");
        jMenu6.add(jMenuItem10);

        jMenuItem11.setText("Otros tipos de pagos");
        jMenu6.add(jMenuItem11);

        jMenuBar1.add(jMenu6);

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
    private javax.swing.JButton btn_calles;
    private javax.swing.JButton btn_home;
    private javax.swing.JButton btn_tipo_pagos;
    private javax.swing.JButton btn_tipo_tomas;
    private javax.swing.JPanel center_panel;
    private javax.swing.JMenuItem consumer_register_process_item;
    private javax.swing.JButton exit_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel label_title;
    private javax.swing.JPanel left_panel;
    private javax.swing.JMenuItem owner_change_process_item;
    private javax.swing.JMenuItem owner_register_process_item;
    private javax.swing.JMenuItem parameters_view_item;
    private javax.swing.JMenuItem profile_item_view;
    private javax.swing.JMenuItem service_payments_view_item;
    private javax.swing.JMenuItem status_type_item;
    private javax.swing.JMenuItem street_view_item;
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
