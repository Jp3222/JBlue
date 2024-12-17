/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.windows;

import com.jblue.controlador.Controller;
import com.jblue.controlador.FactoryController;
import com.jblue.sistema.Sistema;
import com.jblue.vista.marco.contruccion.ConstTitutlos;
import com.jblue.vista.marco.ventanas.VentanaSimple;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.swingw.wrappers.TextFieldWrapper;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jp
 */
public class Login extends VentanaSimple {

    private final MenuConfigBD MENU_CONFIG_BD;
    private final TextFieldWrapper FIELDS[];

    /**
     * Creates new form NewLogin
     *
     */
    public Login() {
        this.MENU_CONFIG_BD = new MenuConfigBD();
        initComponents();
        FIELDS = new TextFieldWrapper[2];
        FIELDS[0] = new TextFieldWrapper(user, "ejem: david123");
        FIELDS[1] = new TextFieldWrapper(password, "contraseña 12345");
        build();
        System.out.println("x" + getHeight());
        System.out.println("x" + getWidth());
    }

    @Override
    public final void build() {
        components();
        events();
        initialState();
        finalState();
    }

    @Override
    public void events() {
        Controller controller = FactoryController.getLoginController(this, MENU_CONFIG_BD);

        login_button.addActionListener(controller);
        config_button.addActionListener(controller);
        mostrar.addActionListener(controller);

        for (TextFieldWrapper envjtf : FIELDS) {
            envjtf.borrarAlClick();
            envjtf.borrarAlEscribir();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                synchronized (Sistema.getInstancia()) {
                    Sistema.getInstancia().notify();
                }
            }
        });

        String enter = "ENTER";

        user.getInputMap().put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ENTER), enter);
        user.getActionMap().put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                user.transferFocus();
            }
        });

        InputMap inputMap = password.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ENTER), enter);

        password.getActionMap().put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                password.transferFocus();
            }
        });
        password.getFocusTraversalPolicy();
    }

    @Override
    public void components() {
    }

    @Override
    public void initialState() {
        user.requestFocusInWindow();
        mostrar.setToolTipText("mostrar");
        mostrar.setSelected(false);
        //
        for (TextFieldWrapper envjtf : FIELDS) {
            envjtf.defecto();
        }
        setDefaultLookAndFeelDecorated(false);
    }

    @Override
    public void finalState() {
        user.requestFocus();
        try {
            Conexion instancia = Conexion.getInstancia();
            String estado = "Estado ";
            estado = estado.concat(instancia.isConectado() ? "Conectado" : "Desconectado");
            db_status.setText(estado);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 400));
        setPreferredSize(new java.awt.Dimension(30, 30));
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inicio de sesion");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(350, 50));
        getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 100));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x128/img1.png"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setIconTextGap(30);
        jLabel2.setPreferredSize(new java.awt.Dimension(350, 150));
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 300));
        jPanel4.setLayout(new java.awt.GridLayout(5, 1, 5, 5));

        jPanel5.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel5.setText("Usuario");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel5.add(jLabel5, java.awt.BorderLayout.NORTH);

        user.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        user.setNextFocusableComponent(password);
        user.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel5.add(user, java.awt.BorderLayout.CENTER);

        jLabel9.setPreferredSize(new java.awt.Dimension(30, 40));
        jPanel5.add(jLabel9, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jLabel6.setText("Contraseña");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel6.add(jLabel6, java.awt.BorderLayout.NORTH);

        password.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jPanel6.add(password, java.awt.BorderLayout.CENTER);

        mostrar.setToolTipText("mostrar");
        mostrar.setActionCommand("show");
        mostrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img2.png"))); // NOI18N
        mostrar.setPreferredSize(new java.awt.Dimension(30, 40));
        mostrar.setRolloverEnabled(false);
        mostrar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img3.png"))); // NOI18N
        jPanel6.add(mostrar, java.awt.BorderLayout.EAST);

        jPanel4.add(jPanel6);

        jPanel7.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel7.setPreferredSize(new java.awt.Dimension(30, 20));
        jPanel7.add(jLabel7, java.awt.BorderLayout.NORTH);

        login_button.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        login_button.setText("Iniciar Sesion");
        login_button.setActionCommand("session");
        jPanel7.add(login_button, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel7);
        jPanel4.add(jPanel3);

        jPanel2.setLayout(new java.awt.BorderLayout());

        db_status.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        db_status.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel2.add(db_status, java.awt.BorderLayout.NORTH);

        config_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img4.png"))); // NOI18N
        config_button.setActionCommand("config");
        config_button.setPreferredSize(new java.awt.Dimension(75, 40));
        jPanel2.add(config_button, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel2);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JButton config_button = new javax.swing.JButton();
    private final javax.swing.JLabel db_status = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel1;
    private final javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
    private final javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
    private final javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private final javax.swing.JButton login_button = new javax.swing.JButton();
    private final javax.swing.JCheckBox mostrar = new javax.swing.JCheckBox();
    private final javax.swing.JPasswordField password = new javax.swing.JPasswordField();
    private final javax.swing.JTextField user = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
    private boolean sesion_active = false;

    //
    @Override
    public void dispose() {
        super.dispose();
        SwingUtilities.invokeLater(() -> initialState());
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JTextField getUser() {
        return user;
    }

    public JCheckBox getMostrar() {
        return mostrar;
    }

    public boolean isSesionActive() {
        return sesion_active;
    }

    public void setSesionActive(boolean sesion_active) {
        this.sesion_active = sesion_active;
    }

}
