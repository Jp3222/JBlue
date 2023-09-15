/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas;

import com.jblue.util.tiempo.Fecha;
import com.jblue.vista.normas.SuperVentana;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author jp
 */
public class AcercaDe extends SuperVentana {

    /**
     * Creates new form AcercaDE
     */
    public AcercaDe() {
        initComponents();
        StyledDocument doc = jTextPane1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        //
        LocalDate fecha = LocalDate.of(2023, Month.OCTOBER.getValue(), 1);
        campo_fh_act.setText(fecha.format(Fecha.FORMATO));
        campo_vp.setText(TITULO_VER_PROGRAMA);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_jblue = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel_creditos = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        panel_app_info = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        campo_fh_act = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        campo_vp = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        campo_jvm = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Acerca de JBlue");
        setResizable(false);

        jTabbedPane1.setToolTipText("");

        panel_jblue.setLayout(new java.awt.BorderLayout(10, 10));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x128/img1.png"))); // NOI18N
        jLabel1.setText("JBlue");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 150));
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        panel_jblue.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jTextPane1.setEditable(false);
        jTextPane1.setText("Sistema de cobros y administracion para el servicio de agua potable.");
        jScrollPane2.setViewportView(jTextPane1);

        panel_jblue.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel2.setPreferredSize(new java.awt.Dimension(50, 30));
        panel_jblue.add(jLabel2, java.awt.BorderLayout.LINE_END);

        jLabel3.setPreferredSize(new java.awt.Dimension(50, 30));
        panel_jblue.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jLabel4.setPreferredSize(new java.awt.Dimension(50, 30));
        panel_jblue.add(jLabel4, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab("JBlue", panel_jblue);

        panel_creditos.setLayout(new java.awt.GridLayout(7, 1));

        jPanel10.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/jblue_iconox32.png"))); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel10.add(jLabel12, java.awt.BorderLayout.CENTER);

        panel_creditos.add(jPanel10);

        jPanel4.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel5.setText("Diseño");
        jLabel5.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel4.add(jLabel5, java.awt.BorderLayout.WEST);

        jTextField1.setEditable(false);
        jTextField1.setText("Juan Pablo Campos.");
        jPanel4.add(jTextField1, java.awt.BorderLayout.CENTER);

        panel_creditos.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout(5, 5));

        jLabel6.setText("Desarrollador.");
        jLabel6.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel5.add(jLabel6, java.awt.BorderLayout.WEST);

        jTextField2.setEditable(false);
        jTextField2.setText("Juan Pablo Campos");
        jPanel5.add(jTextField2, java.awt.BorderLayout.CENTER);

        panel_creditos.add(jPanel5);

        jTabbedPane1.addTab("Creditos", panel_creditos);

        panel_app_info.setLayout(new java.awt.GridLayout(6, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Fecha de actualizacion");
        jLabel7.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel1.add(jLabel7, java.awt.BorderLayout.WEST);

        campo_fh_act.setEditable(false);
        jPanel1.add(campo_fh_act, java.awt.BorderLayout.CENTER);

        panel_app_info.add(jPanel1);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Version del programa");
        jLabel9.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel3.add(jLabel9, java.awt.BorderLayout.WEST);

        campo_vp.setEditable(false);
        jPanel3.add(campo_vp, java.awt.BorderLayout.CENTER);

        panel_app_info.add(jPanel3);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Version de la JVM");
        jLabel8.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel2.add(jLabel8, java.awt.BorderLayout.WEST);

        campo_jvm.setEditable(false);
        jPanel2.add(campo_jvm, java.awt.BorderLayout.CENTER);

        panel_app_info.add(jPanel2);

        jTabbedPane1.addTab("Info. del programa", panel_app_info);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campo_fh_act;
    private javax.swing.JTextField campo_jvm;
    private javax.swing.JTextField campo_vp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel panel_app_info;
    private javax.swing.JPanel panel_creditos;
    private javax.swing.JPanel panel_jblue;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void llamable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void estadoInicial() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
