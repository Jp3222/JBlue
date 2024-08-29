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
package com.jblue.vista.vistas;

import com.jblue.modelo.ConstGs;
import com.jblue.util.trash.Operaciones;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.util.Filtros;
import com.jblue.util.FormatoBD;
import com.jblue.util.FuncJBlue;
import com.jblue.modelo.factories.FabricaCache;
import com.jblue.util.trash.MemoCache;
import com.jblue.vista.marco.vistas.VistaExtendida;
import com.jblue.vista.marco.contruccion.EvtRegistrosBD;
import com.jutil.swingw.modelos.TableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.jblue.vista.marco.contruccion.EvtSetInfoGrafica;

/**
 *
 * @author jp
 */
public class VTipoTomas extends VistaExtendida implements EvtSetInfoGrafica, EvtRegistrosBD {

    /**
     * Creates new form VTipoTomas
     */
    public VTipoTomas() {
        memo_cache = FabricaCache.MC_TIPOS_DE_TOMAS;
        cache = memo_cache.getLista();
        cache_aux = new ArrayList(cache.size());
        initComponents();
        modelo = new TableModel(ConstGs.TABLA_TIPOS_DE_TOMAS, 0);
        modelo.setCellsEditables(false);
        tabla_tipo_tomas.setModel(modelo);

        llamable();
    }

    @Override
    protected final void llamable() {
        construirComponentes();
        componentesEstadoFinal();
        componentesEstadoInicial();
        eventos();
    }

    @Override
    public void componentesEstadoInicial() {
        objeto_buscado = null;
        tabla_tipo_tomas.clearSelection();

        campo_tipo.setText(null);
        campo_costo.setText(null);
        campo_recargo.setText(null);
        habilitarBotones(false);
    }

    @Override
    protected void eventos() {
        btn_guardar.addActionListener(e -> evtGuardar());
        btn_eliminar.addActionListener(e -> evtEliminar());
        btn_actualizar.addActionListener(e -> evtActualizar());
        btn_cancelar.addActionListener(e -> evtCancelar());
    }

    @Override
    public void evtGuardar() {
        if (!datosValidos()) {
            return;
        }
        Operaciones<OTipoTomas> operaciones = memo_cache.getOperaciones();
        String[] datos = FormatoBD.bdEntrada(getInfo(false));
        boolean menesaje = operaciones.insert(datos);
        estado(menesaje);
    }

    @Override
    public void evtEliminar() {
        Operaciones<OTipoTomas> operaciones = memo_cache.getOperaciones();
        boolean menesaje = operaciones.delete("id = " + objeto_buscado.getId());
        estado(menesaje);
    }

    @Override
    public void evtActualizar() {
        if (!datosValidos()) {
            return;
        }
        Operaciones<OTipoTomas> operaciones = memo_cache.getOperaciones();
        String[] datos = FormatoBD.bdEntrada(getInfo(true));
        boolean menesaje = operaciones.actualizar(datos, "id = " + objeto_buscado.getId());
        estado(menesaje);
    }

    @Override
    public void evtCancelar() {
        if (evtCancelar(null)) {
            componentesEstadoInicial();
        }
    }

    public void estado(boolean ok) {
        String estado = ok ? "Exitosa" : "Erronea";
        if (ok) {
            componentesEstadoInicial();
            memo_cache.actualizar();
            recargar();
        }
        JOptionPane.showMessageDialog(this, String.format("Operacion %s", estado));
    }

    private boolean datosValidos() {
        JTextField[] o = {
            campo_tipo, campo_costo, campo_recargo
        };
        for (JTextField i : o) {
            if (Filtros.isNullOrBlank(i.getText())) {
                JOptionPane.showMessageDialog(this, String.format("Campo %s no valido", i.getName()));
                return false;
            }
        }
        if (!Filtros.soloNumerosDecimales(campo_costo.getText()) || !mayorQueCero(campo_costo.getText())) {
            JOptionPane.showMessageDialog(this, String.format("Campo %s no valido", campo_costo.getName()));
            return false;
        }
        if (!Filtros.soloNumerosDecimales(campo_recargo.getText()) || !mayorQueCero(campo_recargo.getText())) {
            JOptionPane.showMessageDialog(this, String.format("Campo %s no valido", campo_recargo.getName()));
            return false;
        }
        return true;
    }

    private boolean mayorQueCero(String o) {
        return Double.parseDouble(o) > 0;
    }

    public void habilitarBotones(boolean estado) {
        btn_guardar.setEnabled(!estado);
        btn_actualizar.setEnabled(estado);
        btn_eliminar.setEnabled(estado);
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
        tabla_usuarios = new javax.swing.JScrollPane();
        tabla_tipo_tomas = new javax.swing.JTable();
        panel_der = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panel_campos = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campo_tipo = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        campo_costo = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        campo_recargo = new javax.swing.JTextField();
        panel_botones = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setName("Menu Tipo De Tomas"); // NOI18N
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

        tabla_tipo_tomas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_tipo_tomas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_tipo_tomasMouseClicked(evt);
            }
        });
        tabla_usuarios.setViewportView(tabla_tipo_tomas);

        panel_izq.add(tabla_usuarios, java.awt.BorderLayout.CENTER);

        add(panel_izq, java.awt.BorderLayout.WEST);

        panel_der.setPreferredSize(new java.awt.Dimension(500, 700));
        panel_der.setLayout(new java.awt.BorderLayout());

        jLabel4.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Datos del tipo de toma");
        jLabel4.setPreferredSize(new java.awt.Dimension(500, 100));
        panel_der.add(jLabel4, java.awt.BorderLayout.NORTH);

        panel_campos.setPreferredSize(new java.awt.Dimension(500, 620));
        panel_campos.setLayout(new java.awt.GridLayout(6, 0, 0, 5));

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel2.setText("Tipo de toma:");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel8.add(jLabel2, java.awt.BorderLayout.NORTH);

        campo_tipo.setName("Tipo de toma"); // NOI18N
        jPanel8.add(campo_tipo, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel8);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel3.setText("Costo:");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel9.add(jLabel3, java.awt.BorderLayout.NORTH);

        campo_costo.setName("Costo"); // NOI18N
        jPanel9.add(campo_costo, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel9);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jLabel5.setLabelFor(campo_recargo);
        jLabel5.setText("Costo de recargo:");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel10.add(jLabel5, java.awt.BorderLayout.NORTH);

        campo_recargo.setName("Costo del recargo"); // NOI18N
        jPanel10.add(campo_recargo, java.awt.BorderLayout.CENTER);

        panel_campos.add(jPanel10);

        panel_der.add(panel_campos, java.awt.BorderLayout.CENTER);

        panel_botones.setPreferredSize(new java.awt.Dimension(500, 100));
        panel_botones.setLayout(new java.awt.GridLayout(2, 0));

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

        panel_botones.add(jPanel2);

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        panel_botones.add(btn_cancelar);

        panel_der.add(panel_botones, java.awt.BorderLayout.SOUTH);

        add(panel_der, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_tipo_tomasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_tipo_tomasMouseClicked
        if (evt.getClickCount() < 2) {
            return;
        }
        if (!isInfoValid()) {
            return;
        }
        setInfoEnPantalla(objeto_buscado);
        jtf_buscador.setText(null);
        FuncJBlue.pintarTabla((DefaultTableModel) tabla_tipo_tomas.getModel(), memo_cache.getListaObj());
        habilitarBotones(true);
    }//GEN-LAST:event_tabla_tipo_tomasMouseClicked

    private void jtf_buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_buscadorKeyReleased
        String aux = Filtros.limpiar(jtf_buscador.getText());
        buscando = !(aux == null || aux.isBlank());
        if (buscando) {
            if (!cache_aux.isEmpty()) {
                cache_aux.clear();
            }
            cache_aux.addAll(
                    cache.stream()
                            .filter(o -> Filtros.limpiarYChecar(o.toString(), aux))
                            .toList()
            );
            FuncJBlue.pintarTabla(modelo, (List) cache_aux);
        } else {
            FuncJBlue.pintarTabla(modelo, memo_cache.getListaObj());
        }
    }//GEN-LAST:event_jtf_buscadorKeyReleased

    private void btn_recargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_recargarActionPerformed
        jtf_buscador.setText(null);
        FuncJBlue.pintarTabla((DefaultTableModel) tabla_tipo_tomas.getModel(), memo_cache.getListaObj());

    }//GEN-LAST:event_btn_recargarActionPerformed
    private boolean buscando;

    public void setObjeto(int index) {
        if (index < 0 || index >= modelo.getRowCount()) {
            return;
        }
        ArrayList<OTipoTomas> aux;
        if (buscando) {
            aux = cache_aux;
        } else {
            aux = cache;
        }
        objeto_buscado = aux.get(index);
        jtf_buscador.setText(null);
        tabla_tipo_tomas.clearSelection();
    }

    @Override
    public boolean isInfoValid() {
        int i = tabla_tipo_tomas.getSelectedRow();
        setObjeto(i);
        return objeto_buscado != null;
    }

    void setInfoEnPantalla(OTipoTomas o) {
        if (o == null) {
            return;
        }
        campo_tipo.setText(o.getTipo());
        campo_costo.setText(String.valueOf(o.getCosto()));
        campo_recargo.setText(String.valueOf(o.getRecargo()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_ant;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_recargar;
    private javax.swing.JButton btn_sig;
    private javax.swing.JTextField campo_costo;
    private javax.swing.JTextField campo_recargo;
    private javax.swing.JTextField campo_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jtf_buscador;
    private javax.swing.JPanel panel_botones;
    private javax.swing.JPanel panel_campos;
    private javax.swing.JPanel panel_der;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JTable tabla_tipo_tomas;
    private javax.swing.JScrollPane tabla_usuarios;
    // End of variables declaration//GEN-END:variables
    private final MemoCache<OTipoTomas> memo_cache;
    private final ArrayList<OTipoTomas> cache;
    private final ArrayList<OTipoTomas> cache_aux;
    private final TableModel modelo;
    private OTipoTomas objeto_buscado;

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            cargar();
        } else {
            vaciar();
        }

    }

    private void cargar() {
        for (OTipoTomas i : cache) {
            modelo.addRow(i.getInfo());
        }
    }

    private void recargar() {
        modelo.removeAllRows();
        cargar();
    }

    private void vaciar() {
        modelo.removeAllRows();
    }

    @Override
    public void setInfoGrafica() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void evtBuscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String[] getInfo(boolean actualizacion) {
        return new String[]{
            campo_tipo.getText(),
            campo_costo.getText(),
            campo_recargo.getText()
        };
    }

    @Override
    public boolean camposValidos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}
