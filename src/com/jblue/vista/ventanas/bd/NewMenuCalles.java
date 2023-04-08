/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas.bd;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.util.FormatoBD;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import com.jblue.vista.conf.SuperVentana;
import com.jutil.jswing.jswingenv.EnvJTextField;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class NewMenuCalles extends SuperVentana {

    private OCalles calle_buscada;
    private final ArrayList<OCalles> lista_auxiliar;
    private final MemoCache<OCalles> memoria_cache;
    private final ArrayList<OCalles> cache;
    private final Operaciones<OCalles> operaciones;
    private boolean buscando;
    //
    private final DefaultTableModel modelo;
    //
    private EnvJTextField bus;
    private EnvJTextField nom;
    private EnvJTextField num;

    /**
     * Creates new form NewMenuCalles
     */
    public NewMenuCalles() {
        _TITULO = 4;
        buscando = false;
        lista_auxiliar = new ArrayList<>();
        memoria_cache = FabricaCache.MC_CALLES;
        operaciones = FabricaOpraciones.CALLES;
        cache = memoria_cache.getLista();
        //
        initComponents();
        this.bus = new EnvJTextField(jtfBuscar, "ejem: CHIMALPOPOCA No 10");
        this.bus.defectoAlEnter();
        this.nom = new EnvJTextField(jtfNombre, "ejem: MALINCHE");
        this.num = new EnvJTextField(jtfNumero, "ejem: 10");
        modelo = (DefaultTableModel) jtCalles.getModel();
        llamable();
    }

    @Override
    protected final void llamable() {
        estadoFinal();
        estadoInicial();
        addEventos();
    }

    @Override
    protected void estadoFinal() {
        super.estadoFinal();
    }

    @Override
    protected void addComponentes() {
        super.addComponentes(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void estadoInicial() {
        calle_buscada = null;
        lista_auxiliar.clear();
        bus.defecto();
        nom.defecto();
        num.defecto();
        jtfNombre.requestFocus();
        botonesPrimarios();
    }

    public void botonesPrimarios() {
        jbtGuardar.setEnabled(true);
        jbtActualizar.setEnabled(false);
        jbtEliminar.setEnabled(false);
        jbtCancelar.setEnabled(true);
    }

    public void botonesSecundarios() {
        jbtGuardar.setEnabled(false);
        jbtActualizar.setEnabled(true);
        jbtEliminar.setEnabled(true);
        jbtCancelar.setEnabled(true);
    }

    void cargarTabla() {
        for (OCalles o : cache) {
            modelo.addRow(o.getInfo());
        }
    }

    void vaciarTabla() {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    void actualizarTabla() {
        vaciarTabla();
        cargarTabla();
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
        jtfBuscar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jbtRecrgar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jbtAnterior = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jbtSiguiente = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCalles = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jtfNombre = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jtfNumero = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jbtGuardar = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jbtActualizar = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jbtEliminar = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jbtCancelar = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 700));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jtfBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(jtfBuscar, java.awt.BorderLayout.NORTH);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel7.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jbtRecrgar.setText("Recargar");
        jbtRecrgar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRecrgarActionPerformed(evt);
            }
        });
        jPanel7.add(jbtRecrgar, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel8.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jPanel9);

        jPanel10.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jbtAnterior.setText("Anterior");
        jPanel10.add(jbtAnterior, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel10);

        jPanel11.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jbtSiguiente.setText("Siguiente");
        jPanel11.add(jbtSiguiente, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel11);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(500, 640));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jtCalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Numero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCalles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtCalles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jtCalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCallesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtCalles);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 700));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(500, 200));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Datos de la Calle");
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel12.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel12);

        jPanel13.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nombre: ");
        jPanel13.add(jLabel2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jtfNombre.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel14.add(jtfNombre, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel14);

        jPanel15.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Numero: ");
        jPanel15.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel15);

        jPanel16.setLayout(new java.awt.BorderLayout());

        jtfNumero.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel16.add(jtfNumero, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel16);

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel17, java.awt.BorderLayout.PAGE_END);

        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel19.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel19.setLayout(new java.awt.BorderLayout());

        jbtGuardar.setText("Guardar");
        jbtGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarActionPerformed(evt);
            }
        });
        jPanel19.add(jbtGuardar, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel19);

        jPanel20.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jbtActualizar.setText("Actualizar");
        jbtActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtActualizarActionPerformed(evt);
            }
        });
        jPanel20.add(jbtActualizar, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel20);

        jPanel21.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel21.setLayout(new java.awt.BorderLayout());

        jbtEliminar.setText("Eliminar");
        jbtEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEliminarActionPerformed(evt);
            }
        });
        jPanel21.add(jbtEliminar, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel21);

        jPanel22.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel22.setLayout(new java.awt.BorderLayout());

        jbtCancelar.setText("Cancelar");
        jbtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelarActionPerformed(evt);
            }
        });
        jPanel22.add(jbtCancelar, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel22);

        jPanel23.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel23.setLayout(new java.awt.BorderLayout());
        jPanel18.add(jPanel23);

        jPanel3.add(jPanel18, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarActionPerformed
        boolean valido = datosValidos();
        if (!valido) {
            JOptionPane.showMessageDialog(this, "INSERCCION ERRONEA", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] info = getInfo();
        info = FormatoBD.bdEntrada(info);
        operaciones.insertar(info);
        JOptionPane.showMessageDialog(this, "INSERCCION EXITOSA", "OPERACION REALIZADA", JOptionPane.WARNING_MESSAGE);

    }//GEN-LAST:event_jbtGuardarActionPerformed

    private void jbtActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtActualizarActionPerformed
        boolean valido = datosValidos();
        if (!valido) {
            JOptionPane.showMessageDialog(this, "ACTUALIZACION ERRONEA", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] info = getInfo();
        info = FormatoBD.bdEntrada(info);
        String[] campos = operaciones.getCAMPOS();
        operaciones.actualizar(campos, info, "id = " + calle_buscada.getId());
        JOptionPane.showMessageDialog(this, "ACTUALIZACION EXITOSA", "OPERACION REALIZADA", JOptionPane.WARNING_MESSAGE);

    }//GEN-LAST:event_jbtActualizarActionPerformed

    private void jbtEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEliminarActionPerformed
        if (calle_buscada == null) {
            JOptionPane.showMessageDialog(this, "ELIMINACION ERRONEA", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }
        operaciones.eliminar("id = " + calle_buscada.getId());
        JOptionPane.showMessageDialog(this, "ELIMINACION EXITOSA", "OPERACION REALIZADA", JOptionPane.WARNING_MESSAGE);

    }//GEN-LAST:event_jbtEliminarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        int x = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea cancelar la operacion?", "Cancelar Operacion", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
        if (x == JOptionPane.YES_OPTION) {
            estadoInicial();
        }
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jtCallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCallesMouseClicked
        int clicks = evt.getClickCount();

        ArrayList<OCalles> aux;

        if (buscando) {
            aux = lista_auxiliar;
        } else {
            aux = cache;
        }

        calle_buscada = aux.get(jtCalles.getSelectedRow());

        switch (clicks) {
            case 1:
                if (buscando) {
                    jtfBuscar.setText(calle_buscada.getStringR());
                }
                break;
            case 2:
                jtfNombre.setText(calle_buscada.getNombre());
                jtfNumero.setText(calle_buscada.getNumero());
                botonesSecundarios();
                jtCalles.clearSelection();
                actualizarTabla();
                buscando = false;
                break;
        }

    }//GEN-LAST:event_jtCallesMouseClicked

    private void jbtRecrgarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRecrgarActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_jbtRecrgarActionPerformed

    private void jtfBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscarKeyReleased
        buscando = true;
        lista_auxiliar.clear();
        vaciarTabla();
        //
        String texto_buscado = jtfBuscar.getText();
        texto_buscado = limpiar(texto_buscado);
        //
        for (OCalles o : cache) {
            String aux = limpiar(o.getStringR());
            if (aux == null || aux.isEmpty() || aux.contains(texto_buscado)) {
                modelo.addRow(o.getInfo());
                lista_auxiliar.add(o);
            }
        }
    }//GEN-LAST:event_jtfBuscarKeyReleased

    private String[] getInfo() {
        return new String[]{
            jtfNombre.getText(),
            jtfNumero.getText()
        };
    }

    private boolean datosValidos() {
        String[] info = getInfo();
        return varValidas(info) && info[0].matches("[a-zA-ZñÑ]") && info[1].matches("[0-9]{0,3}");
    }

    public boolean varValidas(String... txt) {
        for (String o : txt) {
            if (o == null || o.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private String limpiar(String txt) {
        return txt.trim().replace(" ", "").toUpperCase();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        SwingUtilities.invokeLater(() -> cargarTabla());
    }

    @Override
    public void dispose() {
        super.dispose();
        cerrar();
    }

    void cerrar() {
        SwingUtilities.invokeLater(() -> {
            vaciarTabla();
            estadoInicial();
        });
    }

// <editor-fold defaultstate="collapsed" desc="Generated Code">     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtActualizar;
    private javax.swing.JButton jbtAnterior;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtEliminar;
    private javax.swing.JButton jbtGuardar;
    private javax.swing.JButton jbtRecrgar;
    private javax.swing.JButton jbtSiguiente;
    private javax.swing.JTable jtCalles;
    private javax.swing.JTextField jtfBuscar;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfNumero;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>     
}
