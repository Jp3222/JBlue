/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas.herramientas;

import com.jblue.modelo.ConstGs;
import com.jblue.modelo.ConstBD;
import com.jblue.sistema.app.AppFiles;
import com.jblue.util.tiempo.Fecha;
import com.jblue.util.tiempo.Hora;
import com.jblue.vista.jbmarco.VentanaSimple;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import com.jutil.soyjvm.So;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class MenuCVSExport extends VentanaSimple {

    private final File ruta_automatica;
    private final String[] TABLAS;
    private final JFileChooser ruta_escogida;
    private final DefaultComboBoxModel<String> modelo_combo_box;

    public MenuCVSExport() {
        this.ruta_escogida = new JFileChooser(So.USER_HOME);
        ruta_escogida.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        ruta_escogida.setMultiSelectionEnabled(false);
        ruta_escogida.setDialogTitle("Generar CSV");
        ruta_escogida.setApproveButtonText("Guardar");
        ruta_escogida.setDialogType(JFileChooser.SAVE_DIALOG);
        TABLAS = ConstGs.TABLAS;

        this.ruta_automatica = new File(AppFiles.DIR_USER_REPORTES);
        initComponents();
        modelo_combo_box = new DefaultComboBoxModel<>(TABLAS);
        tabla.setModel(modelo_combo_box);
        llamable();
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
        nombre_arch.setText(null);
        tabla.setSelectedIndex(0);
        ruta_auto_act.setSelected(true);
    }

    @Override
    protected void manejoEventos() {
        super.manejoEventos(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombre_arch = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        Tabla = new javax.swing.JLabel();
        tabla = new javax.swing.JComboBox<>();
        incluir_campos = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        ruta_auto_act = new javax.swing.JCheckBox();
        abrir = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 600));
        setResizable(false);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 135));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Generador de CSV");
        jLabel2.setMinimumSize(new java.awt.Dimension(500, 50));
        jLabel2.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel7.add(jLabel2, java.awt.BorderLayout.CENTER);

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Rapido");
        jPanel7.add(jLabel3, java.awt.BorderLayout.SOUTH);

        jPanel2.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel6.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Nombre der archivo");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 50));
        jPanel6.add(jLabel1, java.awt.BorderLayout.WEST);

        nombre_arch.setPreferredSize(new java.awt.Dimension(250, 50));
        jPanel6.add(nombre_arch, java.awt.BorderLayout.CENTER);

        jButton3.setText("Auto");
        jButton3.setToolTipText("Nombre Rapido.\nGenera un nombre rapido para el documento");
        jButton3.setPreferredSize(new java.awt.Dimension(100, 45));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, java.awt.BorderLayout.EAST);

        jPanel8.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 70));
        jPanel5.setLayout(new java.awt.BorderLayout());

        Tabla.setText("Tabla");
        Tabla.setPreferredSize(new java.awt.Dimension(150, 45));
        jPanel5.add(Tabla, java.awt.BorderLayout.LINE_START);

        tabla.setPreferredSize(new java.awt.Dimension(100, 45));
        jPanel5.add(tabla, java.awt.BorderLayout.CENTER);

        incluir_campos.setText("Incluir Campos");
        incluir_campos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incluir_campos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        incluir_campos.setPreferredSize(new java.awt.Dimension(121, 25));
        jPanel5.add(incluir_campos, java.awt.BorderLayout.PAGE_END);

        jPanel9.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jButton1.setText("Generar");
        jButton1.setPreferredSize(new java.awt.Dimension(150, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, java.awt.BorderLayout.NORTH);

        jPanel9.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        ruta_auto_act.setSelected(true);
        ruta_auto_act.setText("Ruta automatica");
        jPanel4.add(ruta_auto_act);

        abrir.setText("Abrir al generar");
        jPanel4.add(abrir);

        jButton2.setText("Info");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel4.add(jButton2);

        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            String tabla_seleccionada = ConstBD.TABLAS[tabla.getSelectedIndex()];

            String[] campos = ConstBD.CAMPOS[tabla.getSelectedIndex()];

            Conexion cn = Conexion.getInstancia();

            ResultSet select = cn.select(tabla_seleccionada);

            construirArchivo(select, tabla_seleccionada, campos);
        } catch (SQLException ex) {
            Logger.getLogger(MenuCVSExport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nombre_auto = nombreAutomatico();
        nombre_arch.setText(nombre_auto);
    }//GEN-LAST:event_jButton3ActionPerformed

    public void construirArchivo(ResultSet rs, String tabla, String[] campos) {
        File ruta;
        if (ruta_auto_act.isSelected()) {
            ruta = ruta_automatica;
        } else {
            ruta_escogida.showOpenDialog(this);
            ruta = ruta_escogida.getSelectedFile();
        }

        if (ruta == null) {
            componentesEstadoInicial();
            JOptionPane.showMessageDialog(this, "Reporte cancelado", "Estado del reporte", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (nombre_arch.getText() == null || nombre_arch.getText().isEmpty()) {
            String nombre = nombreAutomatico();
            nombre_arch.setText(nombre);
        }
        try {
            String nom_fr_fl = "%s/%s.%s";
            File archivo = new File(String.format(nom_fr_fl, ruta.getPath(), nombre_arch.getText().trim(), ".csv"));
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            try (FileWriter fr = new FileWriter(archivo)) {
                if (incluir_campos.isSelected()) {
                    construirCabecera(fr, campos);
                }

                String construirTabla = construirTabla(rs, campos);
                fr.write(construirTabla);
            }

            JOptionPane.showMessageDialog(this, "Reporte creado", "Estado del reporte", JOptionPane.INFORMATION_MESSAGE);
            componentesEstadoInicial();
            if (abrir.isSelected()) {
                Desktop.getDesktop().open(archivo);
            }

        } catch (IOException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
    }

    public void construirCabecera(FileWriter fw, String[] campos) {
        try {
            String filas = construirFilas(campos);
            fw.write(filas);
        } catch (IOException ex) {
            Logger.getLogger(MenuCVSExport.class.getName()).log(Level.SEVERE, null, ex);
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
                aux = construirFilas(info);
                sb.append(aux);
            }
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
        return sb.toString();
    }

    public String construirFilas(String[] campos) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < campos.length - 1; i++) {
            sb.append(campos[i]).append(",");
        }
        sb.append(campos[campos.length - 1]).append("\n");
        return sb.toString();
    }

    public String nombreAutomatico() {
        StringBuilder s = new StringBuilder(tabla.getItemAt(tabla.getSelectedIndex()));
        s.append("_REP_");
        s.append(Fecha.getNewFechaActualString());
        s.append("_");
        s.append(Hora.getHoraActualStr());
        return s.toString().replace("-|:", "_");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tabla;
    private javax.swing.JCheckBox abrir;
    private javax.swing.JCheckBox incluir_campos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField nombre_arch;
    private javax.swing.JCheckBox ruta_auto_act;
    private javax.swing.JComboBox<String> tabla;
    // End of variables declaration//GEN-END:variables
}
