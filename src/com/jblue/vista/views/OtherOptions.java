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

import com.jblue.modelo.objetos.OPersonal;
import com.jblue.sistema.DevFlags;
import com.jblue.vista.windows.NewMenuPrincipal;
import com.jblue.sistema.Sesion;
import com.jblue.util.tools.ObjectUtils;
import com.jblue.vista.windows.AcercaDe;
import com.jblue.vista.windows.MenuCargos;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author juan-campos
 */
public class OtherOptions extends javax.swing.JPanel {

    private final NewMenuPrincipal father;
    private final JButton[] options;
    private final MenuCargos profile;
    private final JFrame[] arr_win_opt;
    //
    private final AcercaDe acerca;

    /**
     * Creates new form NewMenuType
     *
     * @param father
     */
    public OtherOptions(NewMenuPrincipal father) {
        initComponents();

        this.profile = new MenuCargos();

        this.arr_win_opt = new JFrame[]{
            profile
        };

        options = new JButton[]{
            profile_button, jButton2, jButton3,
            secretary_button, presidente_button, admin_button,
            tesorero_button, jButton8, help_button,};

        this.father = father;

        for (JButton i : options) {
            i.addActionListener(e -> {
                this.father.goToHome();
            });
        }

        acerca = new AcercaDe();

        profile_button.addActionListener(e -> setWIn(profile_button));
        secretary_button.addActionListener(e -> setWIn(secretary_button));
        tesorero_button.addActionListener(e -> setWIn(tesorero_button));
        presidente_button.addActionListener(e -> setWIn(presidente_button));
        admin_button.addActionListener(e -> setWIn(admin_button));

        help_button.addActionListener(e -> acerca.setVisible(true));

        checkToUserType();
    }

    public void setWIn(JButton i) {
        int index = profile.setMenu(i.getName());
        if (index == -1) {
            return;
        }
        profile.setVisible(true);
    }

    public void closeWindows() {
        for (JFrame i : arr_win_opt) {
            i.setVisible(false);
            i.dispose();
        }
    }

    private void checkToUserType() {
        OPersonal usuario = Sesion.getInstancia().getUsuario();
        if (!DevFlags.FUTURE_VIEW) {
            secretary_button.setEnabled(ObjectUtils.isSecretario(usuario));
            tesorero_button.setEnabled(ObjectUtils.isTesorero(usuario));
            presidente_button.setEnabled(ObjectUtils.isPresidente(usuario));
            admin_button.setEnabled(ObjectUtils.isAdministrador(usuario));
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

        help_button = new javax.swing.JButton();
        profile_button = new javax.swing.JButton();
        secretary_button = new javax.swing.JButton();
        tesorero_button = new javax.swing.JButton();
        presidente_button = new javax.swing.JButton();
        admin_button = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(900, 700));
        setName("Menu"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 700));
        setLayout(new java.awt.GridLayout(3, 3, 10, 10));

        help_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        help_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x128/help_x128.png"))); // NOI18N
        help_button.setText("Ayuda");
        help_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        help_button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(help_button);

        profile_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        profile_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x128/usuario.png"))); // NOI18N
        profile_button.setText("Perfil");
        profile_button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        profile_button.setName("Perfil"); // NOI18N
        profile_button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(profile_button);

        secretary_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        secretary_button.setText("Secretario");
        secretary_button.setName("Secretario"); // NOI18N
        add(secretary_button);

        tesorero_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        tesorero_button.setText("Tesorero");
        tesorero_button.setName("Tesorero"); // NOI18N
        add(tesorero_button);

        presidente_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        presidente_button.setText("Presidente");
        presidente_button.setName("Presidente"); // NOI18N
        add(presidente_button);

        admin_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        admin_button.setText("Administrador");
        admin_button.setName("Administrador"); // NOI18N
        add(admin_button);

        jButton2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jButton2.setText("Configuraciones");
        add(jButton2);

        jButton3.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jButton3.setText("Herramientas del Software");
        add(jButton3);

        jButton8.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jButton8.setText("Previos");
        jButton8.setName(""); // NOI18N
        add(jButton8);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admin_button;
    private javax.swing.JButton help_button;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton presidente_button;
    private javax.swing.JButton profile_button;
    private javax.swing.JButton secretary_button;
    private javax.swing.JButton tesorero_button;
    // End of variables declaration//GEN-END:variables
}
