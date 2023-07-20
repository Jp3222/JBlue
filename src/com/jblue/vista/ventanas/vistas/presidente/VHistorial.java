/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.jblue.vista.ventanas.vistas.presidente;

import com.jblue.modelo.objetos.OHisMovimientos;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class VHistorial extends javax.swing.JPanel {

    private final MemoCache<OHisMovimientos> memoria_cache;
    private final DefaultTableModel modelo;

    /**
     * Creates new form VHistorial
     */
    public VHistorial() {
        memoria_cache = new MemoCache(FabricaOpraciones.HISTORIAL_DE_MOVIMIENTOS);
        memoria_cache.setRangoActivo(true);
        memoria_cache.cargar();
        initComponents();
        modelo = (DefaultTableModel) jtHistorial.getModel();
    }

    private void cargar() {
        ArrayList<OHisMovimientos> lista = memoria_cache.getLista();
        System.out.println(lista.isEmpty());
        for (OHisMovimientos o : lista) {
            modelo.addRow(o.getInfo());
        }
    }

    private void vaciar() {
        if (modelo.getRowCount() <= 0) {
            return;
        }
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jbtRecargar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jbtAnterior = new javax.swing.JButton();
        jbtSiguiente = new javax.swing.JButton();
        rango = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtHistorial = new javax.swing.JTable();

        setName("vista historial de movimientos"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 200));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 45));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jbtRecargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        jbtRecargar.setPreferredSize(new java.awt.Dimension(100, 45));
        jbtRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRecargarActionPerformed(evt);
            }
        });
        jPanel3.add(jbtRecargar, java.awt.BorderLayout.WEST);

        jPanel4.setPreferredSize(new java.awt.Dimension(200, 45));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jbtAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        jbtAnterior.setPreferredSize(new java.awt.Dimension(100, 45));
        jbtAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtAnteriorActionPerformed(evt);
            }
        });
        jPanel4.add(jbtAnterior, java.awt.BorderLayout.WEST);

        jbtSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        jbtSiguiente.setPreferredSize(new java.awt.Dimension(100, 45));
        jbtSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSiguienteActionPerformed(evt);
            }
        });
        jPanel4.add(jbtSiguiente, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel4, java.awt.BorderLayout.LINE_END);

        rango.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rango.setText("1 - 1000");
        jPanel3.add(rango, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jtHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Personal", "Movimiento", "Fecha", "Hora"
            }
        ));
        jScrollPane1.setViewportView(jtHistorial);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRecargarActionPerformed
        memoria_cache.actualizar();
        cargar();
    }//GEN-LAST:event_jbtRecargarActionPerformed

    private void jbtSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtSiguienteActionPerformed
        memoria_cache.sig();
        memoria_cache.actualizar();
        rango.setText(memoria_cache.getLimite_min() + " - " + memoria_cache.getLimite_max());
        vaciar();
        cargar();
    }//GEN-LAST:event_jbtSiguienteActionPerformed

    private void jbtAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtAnteriorActionPerformed
        memoria_cache.ant();
        memoria_cache.actualizar();
        rango.setText(memoria_cache.getLimite_min() + " - " + memoria_cache.getLimite_max());
        vaciar();
        cargar();
    }//GEN-LAST:event_jbtAnteriorActionPerformed

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if (aFlag) {
            cargar();
        } else {
            vaciar();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtAnterior;
    private javax.swing.JButton jbtRecargar;
    private javax.swing.JButton jbtSiguiente;
    private javax.swing.JTable jtHistorial;
    private javax.swing.JLabel rango;
    // End of variables declaration//GEN-END:variables
}
