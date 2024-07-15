/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas;

import com.jblue.sistema.Sistema;
import com.jblue.vista.marco.ventanas.VentanaSimple;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class MenuConfigBD extends VentanaSimple {

    private String usuario, contra, url;
    private Sistema sistema;
    private Conexion cn;

    /**
     * Creates new form MenuConfigBD
     */
    public MenuConfigBD() {
        this.url = "jdbc";
        sistema = Sistema.getInstancia();
        initComponents();
        llamable();
    }

    final void defecto() {
        this.usuario = "root";
        this.contra = "";
        this.url = "jdbc:mysql://localhost/jblue";
    }

    //"jp", "12345", "jdbc:mysql://localhost/jblue"
    void probarConexion() {
        usuario = String.valueOf(jPasswordField1.getPassword());
        contra = String.valueOf(jpfContra.getPassword());
    }

    @Override
    protected final void llamable() {
        construirComponentes();
        componentesEstadoFinal();
        componentesEstadoInicial();
        manejoEventos();
    }

    @Override
    public void componentesEstadoInicial() {
    }

    @Override
    protected void componentesEstadoFinal() {
        super.componentesEstadoFinal();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jpfContra = new javax.swing.JPasswordField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jcbMotor = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jtfHost = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfPuerto = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jtfBDNombre = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jbtGuardarDatos = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jbtProbarConexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);

        jPanel2.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel7.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x128/usuario.png"))); // NOI18N
        jLabel7.setText("Configuracion de Base de datos");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setPreferredSize(new java.awt.Dimension(800, 170));
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel2.add(jLabel7, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel3.setLayout(new java.awt.GridLayout(4, 1));

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 50));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Usuario");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);

        jPasswordField1.setText("usuario");
        jPanel1.add(jPasswordField1, java.awt.BorderLayout.CENTER);

        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img2.png"))); // NOI18N
        jCheckBox1.setPreferredSize(new java.awt.Dimension(50, 50));
        jCheckBox1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img3.png"))); // NOI18N
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });
        jPanel1.add(jCheckBox1, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel1);

        jPanel4.setPreferredSize(new java.awt.Dimension(800, 50));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Contraeña");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel4.add(jLabel2, java.awt.BorderLayout.NORTH);

        jpfContra.setText("contraseña");
        jpfContra.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel4.add(jpfContra, java.awt.BorderLayout.CENTER);

        jCheckBox2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img2.png"))); // NOI18N
        jCheckBox2.setPreferredSize(new java.awt.Dimension(50, 50));
        jCheckBox2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/img3.png"))); // NOI18N
        jCheckBox2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox2StateChanged(evt);
            }
        });
        jPanel4.add(jCheckBox2, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel4);

        jPanel6.setPreferredSize(new java.awt.Dimension(800, 80));
        jPanel6.setLayout(new java.awt.GridLayout(1, 4));

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Motor");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel5.add(jLabel9, java.awt.BorderLayout.NORTH);

        jcbMotor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mysql" }));
        jcbMotor.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel5.add(jcbMotor, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel5);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("Host");
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel12.add(jLabel10, java.awt.BorderLayout.NORTH);

        jtfHost.setText("localhost");
        jtfHost.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel12.add(jtfHost, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Puerto");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel13.add(jLabel3, java.awt.BorderLayout.NORTH);

        jtfPuerto.setText("3306");
        jtfPuerto.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel13.add(jtfPuerto, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("nombre");
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel14.add(jLabel11, java.awt.BorderLayout.NORTH);

        jtfBDNombre.setText("jblue");
        jtfBDNombre.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel14.add(jtfBDNombre, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel14);

        jPanel3.add(jPanel6);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(800, 50));
        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        jPanel9.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jbtGuardarDatos.setText("Guardar");
        jbtGuardarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarDatosActionPerformed(evt);
            }
        });
        jPanel9.add(jbtGuardarDatos, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jbtProbarConexion.setText("Probar conexion");
        jbtProbarConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtProbarConexionActionPerformed(evt);
            }
        });
        jPanel10.add(jbtProbarConexion, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel10);

        jPanel7.add(jPanel8);

        jPanel2.add(jPanel7, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtGuardarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarDatosActionPerformed
        synchronized (this) {
            String[] datos = getDatos();
            Properties propiedades = sistema.getPropiedades();
            propiedades.put("bd-usuario", datos[0]);
            propiedades.put("bd-contraseña", datos[1]);
            propiedades.put("bd-url", datos[2]);
            sistema.escribirPropiedades();
            setVisible(false);
            dispose();
            notify();
            System.exit(0);
        }
    }//GEN-LAST:event_jbtGuardarDatosActionPerformed

    private void jbtProbarConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtProbarConexionActionPerformed
        try {
            getDatos();
            Conexion con = Conexion.getInstancia(usuario, contra, url);
            boolean cerrado = con.getConexion().isClosed();

            String mensaje = "La conexion es: ";

            if (!cerrado) {
                mensaje += "valida";
            } else {
                mensaje += "no valida";
            }
            JOptionPane.showMessageDialog(this, mensaje);
            Conexion.ConexionNULL();

        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }

    }//GEN-LAST:event_jbtProbarConexionActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        if (jCheckBox1.isSelected()) {
            jPasswordField1.setEchoChar((char) 0);
        } else {
            jPasswordField1.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jCheckBox2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox2StateChanged
        if (jCheckBox2.isSelected()) {
            jpfContra.setEchoChar((char) 0);
        } else {
            jpfContra.setEchoChar('*');
        }
    }//GEN-LAST:event_jCheckBox2StateChanged

    public String[] getDatos() {
        usuario = String.valueOf(jPasswordField1.getPassword());
        contra = String.valueOf(jpfContra.getPassword());
        url = String.format("jdbc:%s://%s:%s/%s",
                jcbMotor.getItemAt(jcbMotor.getSelectedIndex()),
                jtfHost.getText(),
                jtfPuerto.getText(),
                jtfBDNombre.getText()
        );

        return new String[]{
            usuario, contra, url
        };
    }

    @Override
    public synchronized void dispose() {
        super.dispose();
        notify();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton jbtGuardarDatos;
    private javax.swing.JButton jbtProbarConexion;
    private javax.swing.JComboBox<String> jcbMotor;
    private javax.swing.JPasswordField jpfContra;
    private javax.swing.JTextField jtfBDNombre;
    private javax.swing.JTextField jtfHost;
    private javax.swing.JTextField jtfPuerto;
    // End of variables declaration//GEN-END:variables

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

}
