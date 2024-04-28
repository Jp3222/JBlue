/*
 * Copyright (C) 2023 jp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.vista.vistas.menubd;

import com.jblue.util.mg.ModeloTablas;
import com.jblue.modelo.ConstGs;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.util.Filtros;
import com.jblue.util.FormatoBD;
import com.jblue.util.FuncJBlue;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import com.jblue.vista.jbmarco.VistaExtendida;
import com.jblue.vista.jbmarco.inter.EvtAsigInfo;
import com.jblue.vista.jbmarco.inter.EvtMetodosEstandarBD;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class VCalles extends VistaExtendida implements EvtAsigInfo, EvtMetodosEstandarBD {

    /**
     * Creates new form Calles
     */
    public VCalles() {
        initComponents();
        memo_cache = FabricaCache.MC_CALLES;
        cache = memo_cache.getLista();
        cache_aux = new ArrayList<>(cache.size());
        modelo = new ModeloTablas(ConstGs.BD_CALLES);
        modelo.setAllCellEditable(false);
        tabla_calles.setModel(modelo);
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
    protected void construirComponentes() {
    }

    @Override
    public void componentesEstadoInicial() {
        campo_nombre.setText(null);
        campo_numero.setText(null);
        habilitarBotones(false);

    }

    @Override
    protected void manejoEventos() {
        btn_guardar.addActionListener(e -> evtGuardar());
        btn_eliminar.addActionListener(e -> evtEliminar());
        btn_actualizar.addActionListener(e -> evtActualizar());
        btn_cancelar.addActionListener(e -> evtCancelar());
    }

    private void habilitarBotones(boolean estado) {
        btn_guardar.setEnabled(!estado);
        btn_actualizar.setEnabled(estado);
        btn_eliminar.setEnabled(estado);

    }

    // <editor-fold defaultstate="collapsed" desc="Metodos BD">
    @Override
    public void evtGuardar() {
        String[] info = getInfo();
        if (!datosValidos(campo_nombre, campo_numero)) {
            return;
        }
        info = FormatoBD.bdEntrada(info);
        Operaciones op = memo_cache.getOperaciones();
        boolean ok = op.insertar(info);
        estado(ok);
    }

    @Override
    public void evtActualizar() {
        String[] info = getInfo();
        if (!datosValidos(campo_nombre, campo_numero)) {
            return;
        }
        info = FormatoBD.bdEntrada(info);
        Operaciones op = memo_cache.getOperaciones();
        boolean ok = op.actualizar(info, "id = '" + objeto_buscado.getId() + "'");
        estado(ok);
    }

    @Override
    public void evtEliminar() {
        int option = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea eliminar este registro?");
        if (option == JOptionPane.YES_OPTION) {
            return;
        }
        Operaciones op = memo_cache.getOperaciones();
        boolean ok = op.eliminar("id = '" + objeto_buscado.getId() + "'");
        estado(ok);
    }

    @Override
    public void evtCancelar() {
        if (this.evtCancelar(this)) {
            componentesEstadoInicial();
        }
    }

    //</editor-fold>
    public void estado(boolean ok) {
        String estado = ok ? "Exitosa" : "Erronea";
        if (ok) {
            componentesEstadoInicial();
            memo_cache.actualizar();
            recargar();
        }
        JOptionPane.showMessageDialog(this, String.format("Operacion %s", estado));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_izq = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtf_buscador = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btn_recargar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btn_ant = new javax.swing.JButton();
        btn_sig = new javax.swing.JButton();
        jScroll1 = new javax.swing.JScrollPane();
        tabla_calles = new javax.swing.JTable();
        panel_der = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campo_nombre = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        campo_numero = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setName("Menu Calles"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 660));
        setLayout(new java.awt.BorderLayout());

        panel_izq.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_izq.setLayout(new java.awt.BorderLayout());

        jPanel3.setMinimumSize(new java.awt.Dimension(100, 30));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 80));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/buscar.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel4.add(jLabel1, java.awt.BorderLayout.WEST);

        jtf_buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtf_buscadorKeyReleased(evt);
            }
        });
        jPanel4.add(jtf_buscador, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        btn_recargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        btn_recargar.setPreferredSize(new java.awt.Dimension(100, 30));
        btn_recargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_recargarActionPerformed(evt);
            }
        });
        jPanel5.add(btn_recargar, java.awt.BorderLayout.WEST);

        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        btn_ant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        btn_ant.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel6.add(btn_ant);

        btn_sig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        btn_sig.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel6.add(btn_sig);

        jPanel5.add(jPanel6, java.awt.BorderLayout.LINE_END);

        jPanel3.add(jPanel5);

        panel_izq.add(jPanel3, java.awt.BorderLayout.NORTH);

        tabla_calles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_calles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_callesMouseClicked(evt);
            }
        });
        jScroll1.setViewportView(tabla_calles);

        panel_izq.add(jScroll1, java.awt.BorderLayout.CENTER);

        add(panel_izq, java.awt.BorderLayout.WEST);

        panel_der.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_der.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Datos de la Calle");
        jLabel4.setPreferredSize(new java.awt.Dimension(500, 100));
        panel_der.add(jLabel4, java.awt.BorderLayout.NORTH);

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 620));
        jPanel1.setLayout(new java.awt.GridLayout(7, 0, 0, 5));

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setLabelFor(campo_nombre);
        jLabel2.setText("Nombre:");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 19));
        jPanel8.add(jLabel2, java.awt.BorderLayout.NORTH);

        campo_nombre.setName("Nombre"); // NOI18N
        jPanel8.add(campo_nombre, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setLabelFor(campo_numero);
        jLabel3.setText("Numero:");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 19));
        jPanel9.add(jLabel3, java.awt.BorderLayout.NORTH);

        campo_numero.setName("Numero"); // NOI18N
        jPanel9.add(campo_numero, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel9);

        panel_der.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel7.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel7.setLayout(new java.awt.GridLayout(2, 0));

        jPanel2.setLayout(new java.awt.GridLayout(1, 3));

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/disquete.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        jPanel2.add(btn_guardar);

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/sincronizar.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        jPanel2.add(btn_actualizar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/eliminar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        jPanel2.add(btn_eliminar);

        jPanel7.add(jPanel2);

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        jPanel7.add(btn_cancelar);

        panel_der.add(jPanel7, java.awt.BorderLayout.SOUTH);

        add(panel_der, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_recargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recargarActionPerformed

        jtf_buscador.setText(null);
        FuncJBlue.pintarTabla((DefaultTableModel) tabla_calles.getModel(), memo_cache.getListaObj());

    }//GEN-LAST:event_btn_recargarActionPerformed

    private void jtf_buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_buscadorKeyReleased
        String aux = Filtros.limpiar(jtf_buscador.getText());
        buscando = !(aux == null || aux.isBlank());

    }//GEN-LAST:event_jtf_buscadorKeyReleased

    @Override
    public void setObjeto(int index) {
        if (index < 0 || index >= modelo.getRowCount()) {
            return;
        }
        if (buscando) {
            objeto_buscado = cache_aux.get(index);
        } else {
            objeto_buscado = cache.get(index);
        }
        jtf_buscador.setText(null);
        tabla_calles.clearSelection();
    }

    @Override
    public boolean objetoValido() {
        int i = tabla_calles.getSelectedRow();
        setObjeto(i);
        return objeto_buscado != null;
    }

    private void tabla_callesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_callesMouseClicked
        if (evt.getClickCount() < 2) {
            return;
        }
        if (!objetoValido()) {
            return;
        }

        setInfoEnPantalla(objeto_buscado);
        FuncJBlue.pintarTabla(modelo, (List) cache);
        habilitarBotones(true);
    }//GEN-LAST:event_tabla_callesMouseClicked

    private String[] getInfo() {
        return new String[]{
            campo_nombre.getText(),
            campo_numero.getText()
        };
    }

    private boolean datosValidos(JTextField... info) {
        for (JTextField i : info) {
            if (Filtros.isNullOrBlank(i.getText())) {
                JOptionPane.showMessageDialog(this, String.format("Campo %s no valido", i.getName()));
                return false;
            }
        }
        if (!Filtros.soloNumerosEnteros(info[1].getText())
                && !info[1].getText().equalsIgnoreCase("s/n")) {
            JOptionPane.showMessageDialog(this, String.format("Campo %s no valido", info[1].getName()));
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<JMenu> getMenu() {
        return null;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_ant;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_recargar;
    private javax.swing.JButton btn_sig;
    private javax.swing.JTextField campo_nombre;
    private javax.swing.JTextField campo_numero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScroll1;
    private javax.swing.JTextField jtf_buscador;
    private javax.swing.JPanel panel_der;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JTable tabla_calles;
    // End of variables declaration//GEN-END:variables
    private final MemoCache<OCalles> memo_cache;
    private final ModeloTablas modelo;
    private final ArrayList<OCalles> cache;
    private final ArrayList<OCalles> cache_aux;
    private OCalles objeto_buscado;
    private boolean buscando;

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            cargar();
        } else {
            modelo.clear();
        }
    }

    private void cargar() {
        for (OCalles i : cache) {
            modelo.addRow(i.getInfoSinFK());
        }
    }

    private void recargar() {
        modelo.clear();
        FuncJBlue.pintarTabla(modelo, memo_cache.getListaObj());
    }

    private void setInfoEnPantalla(OCalles objeto_buscado) {
        campo_nombre.setText(objeto_buscado.getNombre());
        campo_numero.setText(objeto_buscado.getNumero());
    }

}