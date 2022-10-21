/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas;

import com.jblue.controlador.CLogin;
import com.jblue.vista.conf.SuperVentana;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jp
 */
public class Login extends SuperVentana {

    private boolean clickJtfUsuario, clickJpfPass;
    private final MenuPrincipal menuPrincipal;
    private final CLogin cLogin;

    /**
     * Creates new form Login
     */
    public Login() {
        this.menuPrincipal = new MenuPrincipal(this);
        cLogin = new CLogin(this, menuPrincipal);
        //
        initComponents();
        llamable();
        //
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jpfPass = new javax.swing.JPasswordField();
        jbtSesion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jbtConfBD = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(350, 500));

        jLabel1.setText("Contraseña");
        jLabel1.setPreferredSize(new java.awt.Dimension(120, 30));

        jLabel2.setText("Usuario");
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 30));

        jtfUsuario.setText("ejem: maestro123");
        jtfUsuario.setPreferredSize(new java.awt.Dimension(146, 30));
        jtfUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfUsuarioMouseClicked(evt);
            }
        });
        jtfUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfUsuarioKeyPressed(evt);
            }
        });

        jpfPass.setText("jPasswordField1");
        jpfPass.setPreferredSize(new java.awt.Dimension(128, 30));
        jpfPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpfPassMouseClicked(evt);
            }
        });
        jpfPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpfPassKeyPressed(evt);
            }
        });

        jbtSesion.setText("Iniciar Sesion");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x128/usuario.png"))); // NOI18N

        jLabel4.setText("Estado:");
        jLabel4.setPreferredSize(new java.awt.Dimension(40, 40));

        jbtConfBD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/engranajes.png"))); // NOI18N
        jbtConfBD.setToolTipText("Configuracion de la base de datos");
        jbtConfBD.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(jpfPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtConfBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbtSesion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtConfBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public final void llamable() {
        this.setTitle(NOMBRE + VERSION + " " + SECCION[0]);
        init();
        addComponentes();
        addEventos();
    }

    @Override
    public void init() {
        this.clickJpfPass = false;
        this.clickJtfUsuario = false;
    }

    @Override
    public void addComponentes() {
    }

    @Override
    public void addEventos() {
        System.out.println("xd");
        jbtSesion.addActionListener(cLogin);
        jbtConfBD.addActionListener(cLogin);
    }


    private void jtfUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfUsuarioMouseClicked
        if (!clickJtfUsuario) {
            jtfUsuario.setText(null);
            clickJtfUsuario = true;
        }
    }//GEN-LAST:event_jtfUsuarioMouseClicked

    private void jpfPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpfPassMouseClicked
        if (!clickJpfPass) {
            jpfPass.setText(null);
            clickJpfPass = true;
        }
    }//GEN-LAST:event_jpfPassMouseClicked

    private void jtfUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfUsuarioKeyPressed
        if (!clickJtfUsuario) {
            jtfUsuario.setText(null);
            clickJtfUsuario = true;
        }
    }//GEN-LAST:event_jtfUsuarioKeyPressed

    private void jpfPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpfPassKeyPressed
        if (!clickJpfPass) {
            jpfPass.setText(null);
            clickJpfPass = true;
        }
    }//GEN-LAST:event_jpfPassKeyPressed

    public JPasswordField getJpfPass() {
        return jpfPass;
    }

    public JTextField getJtfUsuario() {
        return jtfUsuario;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtConfBD;
    private javax.swing.JButton jbtSesion;
    private javax.swing.JPasswordField jpfPass;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables
}
