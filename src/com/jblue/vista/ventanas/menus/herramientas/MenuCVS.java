/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas.menus.herramientas;

import com.jblue.modelo.ConstBD;
import com.jblue.sistema.Archivos;
import com.jblue.sistema.Sistema;
import com.jblue.sistema.so.ConstructorDeArchivos;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.soyjvm.SoInfo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class MenuCVS extends javax.swing.JFrame {

    private final Archivos archivos_del_sistema;
    private final ConstructorDeArchivos contructor;
    private final File ruta_automatica;

    public MenuCVS() {
        this.archivos_del_sistema = Sistema.getInstancia().getArchivos();
        this.contructor = archivos_del_sistema.getArchivos();
        this.ruta_automatica = contructor.get(contructor.DIRECTORIO, archivos_del_sistema.REPORTES);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        Tabla = new javax.swing.JLabel();
        incluir_campos = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        ruta_auto_act = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 135));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Generador de CSV");
        jLabel2.setMinimumSize(new java.awt.Dimension(500, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(500, 35));
        jPanel2.add(jLabel2, java.awt.BorderLayout.NORTH);

        jPanel6.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Nombre der archivo");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel6.add(jLabel1, java.awt.BorderLayout.WEST);
        jPanel6.add(jTextField1, java.awt.BorderLayout.CENTER);

        jLabel3.setPreferredSize(new java.awt.Dimension(100, 50));
        jPanel6.add(jLabel3, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 70));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Personal", "Usuarios" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(100, 45));
        jPanel5.add(jComboBox1, java.awt.BorderLayout.CENTER);

        jCheckBox1.setText("Todo");
        jCheckBox1.setPreferredSize(new java.awt.Dimension(100, 45));
        jPanel5.add(jCheckBox1, java.awt.BorderLayout.LINE_END);

        Tabla.setText("Tabla");
        Tabla.setPreferredSize(new java.awt.Dimension(150, 45));
        jPanel5.add(Tabla, java.awt.BorderLayout.LINE_START);

        incluir_campos.setText("Incluir Campos");
        incluir_campos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incluir_campos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        incluir_campos.setPreferredSize(new java.awt.Dimension(121, 25));
        jPanel5.add(incluir_campos, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel5, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 135));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        jButton1.setText("Generar");
        jButton1.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jPanel1.add(jPanel3);

        jPanel4.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jButton2.setText("Info");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel4.add(jButton2, java.awt.BorderLayout.EAST);

        ruta_auto_act.setSelected(true);
        ruta_auto_act.setText("Ruta automatica");
        jPanel4.add(ruta_auto_act, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String tabla = ConstBD.TABLAS[jComboBox1.getSelectedIndex()];
            System.out.println(tabla);
            String[] campos = ConstBD.CAMPOS[jComboBox1.getSelectedIndex()];
            System.out.println(Arrays.toString(campos));

            Conexion cn = Conexion.getInstancia();

            ResultSet select = cn.select(tabla);

            construirArchivo(select, tabla, campos);
        } catch (SQLException ex) {
            Logger.getLogger(MenuCVS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void construirArchivo(ResultSet rs, String tabla, String[] campos) {
        File ruta;
        if (ruta_auto_act.isSelected()) {
            ruta = ruta_automatica;
        } else {
            JFileChooser jfc = new JFileChooser(SoInfo.HOME);
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jfc.setMultiSelectionEnabled(false);
            jfc.showOpenDialog(this);
            ruta = jfc.getSelectedFile();
        }
        if (jTextField1.getText() == null || jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre no valido");
            return;
        }
        try {
            File archivo = new File(ruta.getPath() + "/" + jTextField1.getText().trim() + ".csv");
            if (!archivo.exists()) {
                System.out.println(archivo.getAbsolutePath());
                archivo.createNewFile();
            }
            try (FileWriter fr = new FileWriter(archivo)) {
                if (incluir_campos.isSelected()) {
                    construirCabecera(fr, campos);
                }
                String construirTabla = construirTabla(rs, campos);
                fr.write(construirTabla);
            }
            JOptionPane.showMessageDialog(this, "reporte creado");
        } catch (IOException ex) {
            Logger.getLogger(MenuCVS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void construirCabecera(FileWriter fw, String[] campos) {
        try {
            String filas = filas(campos);
            fw.write(filas);
        } catch (IOException ex) {
            Logger.getLogger(MenuCVS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String construirTabla(ResultSet rs, String[] campos) {
        StringBuilder sb = new StringBuilder();
        try {
            String[] info = new String[campos.length];
            String aux;
            while (rs.next()) {
                for (int i = 0; i < info.length; i++) {
                    info[i] = rs.getString(i + 1);
                }
                aux = filas(info);
                sb.append(aux);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuCVS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public String filas(String[] campos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < campos.length - 1; i++) {
            sb.append(campos[i]).append(",");
        }
        sb.append(campos[campos.length - 1]).append("\n");
        return sb.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tabla;
    private javax.swing.JCheckBox incluir_campos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox ruta_auto_act;
    // End of variables declaration//GEN-END:variables
}
