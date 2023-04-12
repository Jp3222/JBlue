/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.jblue.vista.ventanas.menus.bd;

import com.jblue.controlador.CCalles;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.FormatoBD;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import com.jblue.vista.normas.SuperVentana;
import com.jutil.jswing.jswingenv.EnvJTextField;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class MenuCalles extends SuperVentana {

    private OCalles calle_buscada;
    private final ArrayList<OCalles> lista_auxiliar;
    private final MemoCache<OCalles> memoria_cache;
    private final ArrayList<OCalles> cache;
    private final Operaciones<OCalles> operaciones;
    private boolean buscando;

    private final CCalles controlador;

    private final DefaultTableModel modelo_tabla;

    /**
     * Creates new form NewMenuCalles
     */
    public MenuCalles() {
        _TITULO = 4;

        lista_auxiliar = new ArrayList<>();

        memoria_cache = FabricaCache.MC_CALLES;
        cache = memoria_cache.getLista();

        operaciones = FabricaOpraciones.CALLES;

        //
        initComponents();
        modelo_tabla = (DefaultTableModel) jtCalles.getModel();
        //
        controlador = new CCalles(this);
        llamable();
    }

    @Override
    protected final void llamable() {
        addComponentes();
        estadoFinal();
        estadoInicial();
        addEventos();
    }

    @Override
    protected void estadoFinal() {
        super.estadoFinal();
        jbtCancelar.setEnabled(true);
    }

    @Override
    protected void addComponentes() {
        super.addComponentes();
    }

    @Override
    public void estadoInicial() {
        calle_buscada = null;
        lista_auxiliar.clear();
        buscando = false;
        //
        jtfNombre.setText(null);
        jtfNumero.setText(null);
        //
        jtfNombre.requestFocus();
        botonesPrimarios();
    }

    public void botonesPrimarios() {
        jbtGuardar.setEnabled(true);
        jbtActualizar.setEnabled(false);
        jbtEliminar.setEnabled(false);
    }

    public void botonesSecundarios() {
        jbtGuardar.setEnabled(false);
        jbtActualizar.setEnabled(true);
        jbtEliminar.setEnabled(true);
    }

    @Override
    protected void addEventos() {
        super.addEventos();
        jbtRecrgar.addActionListener(e -> {
            buscando = false;
            lista_auxiliar.clear();
            calle_buscada = null;
            controlador.actualizarTabla();

        });
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
        jtfBuscador = new javax.swing.JTextField();
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
        jPanel14 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfNumero = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
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

        jtfBuscador.setPreferredSize(new java.awt.Dimension(500, 30));
        jtfBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscadorKeyReleased(evt);
            }
        });
        jPanel2.add(jtfBuscador, java.awt.BorderLayout.NORTH);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel7.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jbtRecrgar.setText("Recargar");
        jbtRecrgar.setPreferredSize(new java.awt.Dimension(100, 30));
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
        jbtAnterior.setEnabled(false);
        jbtAnterior.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel10.add(jbtAnterior, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel10);

        jPanel11.setPreferredSize(new java.awt.Dimension(100, 70));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jbtSiguiente.setText("Siguiente");
        jbtSiguiente.setEnabled(false);
        jbtSiguiente.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(jbtSiguiente, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel11);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel6.setPreferredSize(new java.awt.Dimension(500, 640));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 640));

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
        jtCalles.setPreferredSize(new java.awt.Dimension(500, 600));
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

        jPanel14.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nombre: ");
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel14.add(jLabel2, java.awt.BorderLayout.NORTH);

        jtfNombre.setPreferredSize(new java.awt.Dimension(500, 25));
        jtfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNombreKeyTyped(evt);
            }
        });
        jPanel14.add(jtfNombre, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel14);

        jPanel16.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Numero: ");
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 25));
        jPanel16.add(jLabel3, java.awt.BorderLayout.NORTH);

        jtfNumero.setPreferredSize(new java.awt.Dimension(500, 25));
        jtfNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNumeroKeyTyped(evt);
            }
        });
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

        jPanel24.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel24.setLayout(new java.awt.BorderLayout());
        jPanel18.add(jPanel24);

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
        String[] info = getInfo();
        if (info == null) {
            JOptionPane.showMessageDialog(this, "INSERCCION ERRONEA", "OPERACION REALIZADA", JOptionPane.WARNING_MESSAGE);
            return;
        }
        info = FormatoBD.bdEntrada(info);
        operaciones.insertar(info);
        JOptionPane.showMessageDialog(this, "INSERCCION EXITOSA", "OPERACION REALIZADA", JOptionPane.WARNING_MESSAGE);
        mov();

    }//GEN-LAST:event_jbtGuardarActionPerformed

    private void jbtActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtActualizarActionPerformed
        String[] info = getInfo();
        if (info == null) {
            JOptionPane.showMessageDialog(this, "ACTUALIZACION ERRONEA", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }
        info = FormatoBD.bdEntrada(info);
        String[] campos = Arrays.copyOfRange(operaciones.getCAMPOS(), 1, operaciones.getCAMPOS().length);
        operaciones.actualizar(campos, info, "id = " + calle_buscada.getId());
        JOptionPane.showMessageDialog(this, "ACTUALIZACION EXITOSA", "OPERACION REALIZADA", JOptionPane.WARNING_MESSAGE);
        mov();

    }//GEN-LAST:event_jbtActualizarActionPerformed

    private void jbtEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtEliminarActionPerformed
        if (calle_buscada == null) {
            JOptionPane.showMessageDialog(this, "ELIMINACION ERRONEA", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!borrable(calle_buscada.getId())) {
            JOptionPane.showMessageDialog(this, "ESTE ELEMENTO NO SE PUEDE BORRAR PORQUE ESTA ASOCIADO", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }

        operaciones.eliminar("id = " + calle_buscada.getId());
        JOptionPane.showMessageDialog(this, "ELIMINACION EXITOSA", "OPERACION REALIZADA", JOptionPane.WARNING_MESSAGE);
        mov();

    }//GEN-LAST:event_jbtEliminarActionPerformed

    private void jbtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelarActionPerformed
        int x = JOptionPane.showConfirmDialog(this, "¿Esta seguro de que desea cancelar la operacion?", "Cancelar Operacion", JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
        if (x == JOptionPane.YES_OPTION) {
            estadoInicial();
        }
    }//GEN-LAST:event_jbtCancelarActionPerformed

    private void jtCallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCallesMouseClicked
        int index = jtCalles.getSelectedRow();

        ArrayList<OCalles> aux;

        if (buscando) {
            aux = lista_auxiliar;
        } else {
            aux = cache;
        }

        if (index < 0 || index >= aux.size()) {
            return;
        }

        calle_buscada = aux.get(index);

        if (evt.getClickCount() == 2) {
            jtfNombre.setText(calle_buscada.getNombre());
            jtfNumero.setText(calle_buscada.getNumero());
            botonesSecundarios();
            jtCalles.clearSelection();
            controlador.actualizarTabla();
            buscando = false;
        }
    }//GEN-LAST:event_jtCallesMouseClicked

    private void jtfBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscadorKeyReleased

        buscando = true;
        controlador.buscar(jtfBuscador.getText(), cache, lista_auxiliar);

    }//GEN-LAST:event_jtfBuscadorKeyReleased

    private void jtfNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNombreKeyTyped
        if (jtfNombre.getText().length() >= 50) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jtfNombreKeyTyped

    private void jtfNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNumeroKeyTyped
        if (jtfNumero.getText().length() >= 3) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jtfNumeroKeyTyped

    public void mov() {
        memoria_cache.actualizar();
        controlador.actualizarTabla();
        estadoInicial();
    }

    public boolean borrable(String id) {
        Operaciones<OUsuarios> usuarios = FabricaOpraciones.USUARIOS;
        ArrayList<OUsuarios> lista = usuarios.getLista("calle = " + id);
        return lista.isEmpty();
    }

    private String[] getInfo() {
        String nombre = jtfNombre.getText();
        String numero = jtfNumero.getText();
        //validacion
        if (!datosValidos(nombre, numero)) {
            return null;
        }
        //
        if (numero.equals("0")) {
            numero = "S/N";
        }
        return new String[]{nombre, numero};
    }

    private boolean datosValidos(String... info) {
        return varValidas(info) && info[0].matches("[a-zA-ZñÑáéíóú]{1,50}") && (info[1].equals("S/N") || info[1].matches("[0-9]{1,3}"));
    }

    public boolean varValidas(String... txt) {
        for (String o : txt) {
            if (o == null || o.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        SwingUtilities.invokeLater(() -> {
            controlador.cargarTabla();
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        cerrar();
    }

    void cerrar() {
        SwingUtilities.invokeLater(() -> {
            controlador.vaciarTabla();
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
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
    private javax.swing.JTextField jtfBuscador;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfNumero;
    // End of variables declaration//GEN-END:variables

    @Override
    public void permisos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //</editor-fold>  

    public JTextField getJtfBuscar() {
        return jtfBuscador;
    }

    public DefaultTableModel getModelo_Tabla() {
        return modelo_tabla;
    }

    public ArrayList<OCalles> getLista_auxiliar() {
        return lista_auxiliar;
    }

}
