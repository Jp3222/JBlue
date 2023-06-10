/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas.menus.herramientas;

import com.jblue.modelo.ConstGs;
import com.jblue.modelo.ConstBD;
import com.jblue.sistema.Archivos;
import com.jblue.sistema.Sistema;
import com.jblue.util.archivos.ConstructorArchivos;
import com.jblue.util.tiempo.Fecha;
import com.jblue.util.tiempo.Hora;
import com.jblue.vista.normas.SuperVentana;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import com.jutil.soyjvm.SoInfo;
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
public class MenuCVS extends SuperVentana {

    private final Archivos archivos_del_sistema;
    private final ConstructorArchivos contructor;
    private final File ruta_automatica;
    private final String[] TABLAS;
    private final JFileChooser archivo_escogido;
    private final DefaultComboBoxModel<String> modelo_combo_box;

    public MenuCVS() {
        this.archivo_escogido = new JFileChooser(SoInfo.HOME);
        TABLAS = ConstGs.TABLAS;

        this.archivos_del_sistema = Sistema.getInstancia().getArchivos();
        this.contructor = archivos_del_sistema.getArchivos();
        this.ruta_automatica = contructor.get(contructor.DIRECTORIO, archivos_del_sistema.REPORTES);
        initComponents();
        modelo_combo_box = new DefaultComboBoxModel<>(TABLAS);
        jComboBox1.setModel(modelo_combo_box);
        llamable();
    }

    @Override
    protected final void llamable() {
        estadoFinal();
        estadoInicial();
        addEventos();

    }

    @Override
    public void estadoInicial() {
        jTextField1.setText(null);
        jComboBox1.setSelectedIndex(0);
        ruta_auto_act.setSelected(true);
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
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        Tabla = new javax.swing.JLabel();
        incluir_campos = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        ruta_auto_act = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 400));
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

        jTextField1.setPreferredSize(new java.awt.Dimension(250, 50));
        jPanel6.add(jTextField1, java.awt.BorderLayout.CENTER);

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

        jComboBox1.setPreferredSize(new java.awt.Dimension(100, 45));
        jPanel5.add(jComboBox1, java.awt.BorderLayout.CENTER);

        Tabla.setText("Tabla");
        Tabla.setPreferredSize(new java.awt.Dimension(150, 45));
        jPanel5.add(Tabla, java.awt.BorderLayout.LINE_START);

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
        jPanel4.setLayout(new java.awt.BorderLayout());

        jButton2.setText("Info");
        jButton2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel4.add(jButton2, java.awt.BorderLayout.EAST);

        ruta_auto_act.setSelected(true);
        ruta_auto_act.setText("Ruta automatica");
        jPanel4.add(ruta_auto_act, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            String tabla_seleccionada = ConstBD.TABLAS[jComboBox1.getSelectedIndex()];

            String[] campos = ConstBD.CAMPOS[jComboBox1.getSelectedIndex()];

            Conexion cn = Conexion.getInstancia();

            ResultSet select = cn.select(tabla_seleccionada);

            construirArchivo(select, tabla_seleccionada, campos);
        } catch (SQLException ex) {
            Logger.getLogger(MenuCVS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String nombre = nombre();
        jTextField1.setText(nombre);
    }//GEN-LAST:event_jButton3ActionPerformed

    public String nombre() {
        Fecha fecha = new Fecha();
        Hora hora = new Hora();
        StringBuilder s = new StringBuilder(jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
        s.append("_REP_");
        s.append(fecha.getNewFechaActualString());
        s.append("_");
        s.append(hora.getHoraActualString());
        String nombre = s.toString().replace("-|:", "_");
        return nombre;
    }

    public void construirArchivo(ResultSet rs, String tabla, String[] campos) {
        File ruta;
        if (ruta_auto_act.isSelected()) {
            ruta = ruta_automatica;
        } else {
            
            archivo_escogido.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            archivo_escogido.setMultiSelectionEnabled(false);
            archivo_escogido.setDialogTitle("Generar CSV");
            archivo_escogido.setApproveButtonText("Guardar");
            archivo_escogido.setDialogType(JFileChooser.SAVE_DIALOG);
            archivo_escogido.showOpenDialog(this);
            ruta = archivo_escogido.getSelectedFile();
            System.out.println(archivo_escogido.isDirectorySelectionEnabled());
        }
        if (ruta == null) {
            estadoInicial();
            JOptionPane.showMessageDialog(this, "Reporte cancelado", "Estado del reporte", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (jTextField1.getText() == null || jTextField1.getText().isEmpty()) {
            String nombre = nombre();
            jTextField1.setText(nombre);
        }
        try {
            File archivo = new File(ruta.getPath() + "/" + jTextField1.getText().trim() + ".csv");
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
            estadoInicial();
            Desktop.getDesktop().open(archivo);
        } catch (IOException ex) {
            Logger.getLogger(MenuCVS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void construirCabecera(FileWriter fw, String[] campos) {
        try {
            String filas = construirFilas(campos);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Tabla;
    private javax.swing.JCheckBox incluir_campos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JCheckBox ruta_auto_act;
    // End of variables declaration//GEN-END:variables
}
