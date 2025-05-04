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
package com.jblue.vista.windows;

import com.jblue.modelo.objetos.OEmployee;
import com.jblue.sistema.Sesion;
import com.jblue.vista.marco.OptionMenu;
import com.jblue.vista.marco.ventanas.VentanaSimple;
import com.jblue.vista.views.options.VContabilidad;
import com.jblue.vista.views.options.VDocumentos;
import com.jblue.vista.views.options.VPayments;
import com.jblue.vista.views.options.VPerfil;
import com.jblue.vista.views.options.EmployeeView;
import com.jblue.vista.views.options.VSuministros;
import java.awt.CardLayout;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author jp
 */
public final class MenuCargos extends VentanaSimple {

    /**
     * Creates new form MenuCargos
     */
    public MenuCargos() {
        initComponents();
        view_contabilidad = new VContabilidad();
        view_docs = new VDocumentos();
        view_payments = new VPayments();
        view_perfil = new VPerfil();
        view_personal = new EmployeeView();
        view_su = new VSuministros();
        ly = (CardLayout) root_panel.getLayout();

        default_icon = user_photo.getIcon();

        Perfil = new OptionMenu[]{
            view_perfil
        };
        Presidente = new OptionMenu[]{
            view_payments, view_docs, view_contabilidad
        };
        Tesorero = new OptionMenu[]{
            view_contabilidad, view_payments
        };
        Secretario = new OptionMenu[]{
            view_payments, view_su, view_docs
        };
        Pasante = new OptionMenu[]{
            view_docs
        };
        Administrador = new OptionMenu[]{
            view_contabilidad, view_docs, view_payments, view_perfil, view_personal, view_su
        };
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
    public void events() {
    }

    @Override
    public void components() {
        root_panel.add(view_contabilidad, view_contabilidad.getName());
        root_panel.add(view_docs, view_docs.getName());
        root_panel.add(view_payments, view_payments.getName());
        root_panel.add(view_perfil, view_perfil.getName());
        root_panel.add(view_personal, view_personal.getName());
        root_panel.add(view_su, view_su.getName());
    }

    @Override
    public void initialState() {
        if (user_photo.getIcon() != null) {
            user_photo.setIcon(default_icon);
        }
        if (name_user.getText() != null) {
            name_user.setText(null);
        }
        if (panel_option.getComponentCount() > 0) {
            panel_option.removeAll();
        }
    }

    @Override
    public void finalState() {
        OEmployee usuario = Sesion.getInstancia().getUsuario();
        name_user.setText(usuario.toString());
       // File userPhoto = MediaUtils.getUserPhoto(usuario.getId(), usuario.toString().concat("jpg"));
//        if (userPhoto != null) {
//            user_photo.setIcon(new ImageIcon(userPhoto.getAbsolutePath()));
//        }

    }

    public int setMenu(String menu) {
        int r = 0;
        switch (menu) {
            case "Perfil" ->
                addOptions(root_panel, Perfil);
            case "Presidente" ->
                addOptions(root_panel, Presidente);
            case "Tesorero" ->
                addOptions(root_panel, Tesorero);
            case "Administrador" ->
                addOptions(root_panel, Administrador);
            default ->
                r = -1;
        }
        panel_option.updateUI();
        return r;
    }

    public void addOptions(JPanel panel, OptionMenu... options) {
        initialState();
        for (OptionMenu i : options) {
            i.setEvenOption((e) -> {
                ly.show(panel, i.getOption().getName());
            });
            panel_option.add(i.getOption());
        }
        ly.show(panel, ((JPanel) options[0]).getName());
    }

    private void showTesorero() {
    }

    private void showPerfil() {
        initialState();
        for (OptionMenu i : Perfil) {
            i.setEvenOption((e) -> {
                ly.show(center_panel, i.getOption().getName());
            });
            panel_option.add(i.getOption());
        }
        ly.addLayoutComponent(center_panel, ((JComponent) Perfil[0]).getName());
    }

    private void showPresidente() {
        initialState();
        for (OptionMenu i : Presidente) {
            i.setEvenOption((e) -> {
                ly.show(center_panel, i.getOption().getName());
            });
            panel_option.add(i.getOption());
        }
    }

    private void showAdministrador() {
        initialState();
        for (OptionMenu i : Administrador) {
            i.setEvenOption((e) -> {
                ly.show(center_panel, i.getOption().getName());
            });
            panel_option.add(i.getOption());
            panel_option.add(i.getOption());
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

        left_pane = new javax.swing.JPanel();
        name_user = new javax.swing.JLabel();
        user_photo = new javax.swing.JLabel();
        panel_option = new javax.swing.JPanel();
        center_panel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        root_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(1000, 700));

        left_pane.setLayout(new java.awt.BorderLayout());

        name_user.setPreferredSize(new java.awt.Dimension(41, 60));
        left_pane.add(name_user, java.awt.BorderLayout.PAGE_START);

        user_photo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        user_photo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x128/img2.png"))); // NOI18N
        user_photo.setPreferredSize(new java.awt.Dimension(250, 200));
        left_pane.add(user_photo, java.awt.BorderLayout.CENTER);

        panel_option.setPreferredSize(new java.awt.Dimension(250, 400));
        panel_option.setLayout(new java.awt.GridLayout(8, 0));
        left_pane.add(panel_option, java.awt.BorderLayout.SOUTH);

        getContentPane().add(left_pane, java.awt.BorderLayout.LINE_START);

        center_panel.setLayout(new java.awt.BorderLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(10, 10));
        center_panel.add(jSeparator1, java.awt.BorderLayout.WEST);

        root_panel.setLayout(new java.awt.CardLayout());
        center_panel.add(root_panel, java.awt.BorderLayout.CENTER);

        getContentPane().add(center_panel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel center_panel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel left_pane;
    private javax.swing.JLabel name_user;
    private javax.swing.JPanel panel_option;
    private javax.swing.JPanel root_panel;
    private javax.swing.JLabel user_photo;
    // End of variables declaration//GEN-END:variables
    //Views
    private final VContabilidad view_contabilidad;
    private final VDocumentos view_docs;
    private final VPayments view_payments;
    private final VPerfil view_perfil;
    private final EmployeeView view_personal;
    private final VSuministros view_su;
    //Menus
    private final OptionMenu[] Perfil;
    private final OptionMenu[] Presidente;
    private final OptionMenu[] Tesorero;
    private final OptionMenu[] Secretario;
    private final OptionMenu[] Pasante;
    private final OptionMenu[] Administrador;
    //Latour
    private final CardLayout ly;
    //
    private final Icon default_icon;

}
