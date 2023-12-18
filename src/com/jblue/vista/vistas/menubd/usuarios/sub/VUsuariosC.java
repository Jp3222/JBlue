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
package com.jblue.vista.vistas.menubd.usuarios.sub;

import com.jblue.mg.ModeloTablas;
import com.jblue.modelo.ConstGs;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.sucls.Objeto;
import com.jblue.util.Filtros;
import com.jblue.util.FuncJBlue;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import com.jblue.vista.jbmarco.VistaSimple;
import com.jblue.vista.comp.CBarraEstado;
import com.jblue.vista.vistas.menubd.usuarios.VUsuarios;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class VUsuariosC extends VistaSimple {

    private final ModeloTablas modelo_tabla;

    /**
     * Creates new form VUsuariosC
     *
     * @param root
     */
    public VUsuariosC(VUsuarios root) {
        initComponents();
        barra_estado = new CBarraEstado();
        memoria_cache = root.getMemo_cache();
        cache = memoria_cache.getLista();
        cache_aux = new ArrayList<>(cache.size());
        modelo_tabla = new ModeloTablas(ConstGs.BD_USUARIOS);
        modelo_tabla.setAllCellEditable(false);
        filtros = new JComponent[]{
            filtro_calle,
            filtro_toma,
            filtro_estado,
            filtro_is_titular,
            filtro_is_consumidor,
            filtro_Titular,
            filtro_quitar
        };
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
    protected void componentesEstadoFinal() {
        tabla_usuarios.setModel(modelo_tabla);
    }

    @Override
    public void componentesEstadoInicial() {
        activar_filtros.setSelected(false);
        FuncJBlue.habilitarComponentes(false, filtros);
    }

    @Override
    protected void manejoEventos() {
        activar_filtros.addItemListener((i) -> FuncJBlue.habilitarComponentes(activar_filtros.isSelected(), filtros));
        filtro_quitar.addActionListener(e -> FuncJBlue.habilitarComponentes(false, filtros));
        recargar.addActionListener(e -> recargarTabla());
        siguiente.addActionListener(e -> sigTabla());
        anterior.addActionListener(e -> antTabla());
    }

    private void recargarTabla() {
        if (!modelo_tabla.isDataEmpty()) {
            modelo_tabla.clear();
        }
        buscador_tabla.setText(null);
        pintarTabla(modelo_tabla, cache);
    }

    private void antTabla() {
    }

    private void sigTabla() {
    }

    private void filtros() {
        Predicate<OUsuarios> predicado = t -> activar_filtros.isSelected();
        if (comboBoxValido(filtro_calle)) {
            predicado.and(null);
        }

    }

    private boolean comboBoxValido(JComboBox o) {
        return o.getSelectedIndex() > 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_filtros = new javax.swing.JPanel();
        pf_bar_super = new javax.swing.JPanel();
        filtro_quitar = new javax.swing.JButton();
        activar_filtros = new javax.swing.JCheckBox();
        pf_filtros = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        filtro_calle = new javax.swing.JComboBox<>();
        jPanel26 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        filtro_toma = new javax.swing.JComboBox<>();
        jPanel28 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        filtro_estado = new javax.swing.JComboBox<>();
        jPanel24 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        filtro_is_titular = new javax.swing.JCheckBox();
        filtro_is_consumidor = new javax.swing.JCheckBox();
        jPanel31 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        filtro_Titular = new javax.swing.JTextField();
        panel_tabla = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        buscador_tabla = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        anterior = new javax.swing.JButton();
        siguiente = new javax.swing.JButton();
        recargar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_usuarios = new javax.swing.JTable();

        setName("Consulta de usuarios"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 660));
        setLayout(new java.awt.BorderLayout());

        panel_filtros.setPreferredSize(new java.awt.Dimension(1000, 170));
        panel_filtros.setLayout(new java.awt.BorderLayout());

        pf_bar_super.setLayout(new java.awt.BorderLayout());

        filtro_quitar.setText("Quitar filtros");
        filtro_quitar.setPreferredSize(new java.awt.Dimension(150, 29));
        pf_bar_super.add(filtro_quitar, java.awt.BorderLayout.LINE_END);

        activar_filtros.setText("Filtros");
        pf_bar_super.add(activar_filtros, java.awt.BorderLayout.CENTER);

        panel_filtros.add(pf_bar_super, java.awt.BorderLayout.NORTH);

        pf_filtros.setLayout(new java.awt.GridLayout(1, 2));

        jPanel22.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel22.setLayout(new java.awt.GridLayout(4, 1));

        jPanel25.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Calle");
        jLabel11.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel25.add(jLabel11, java.awt.BorderLayout.WEST);

        filtro_calle.setName("calle"); // NOI18N
        jPanel25.add(filtro_calle, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel25);

        jPanel26.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("T. Toma");
        jLabel12.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel26.add(jLabel12, java.awt.BorderLayout.WEST);

        filtro_toma.setName("ttoma"); // NOI18N
        jPanel26.add(filtro_toma, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel26);

        jPanel28.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("Estado");
        jLabel15.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel28.add(jLabel15, java.awt.BorderLayout.WEST);

        filtro_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ESTADO", "ACTIVO", "INACTIVO" }));
        filtro_estado.setName("estado"); // NOI18N
        jPanel28.add(filtro_estado, java.awt.BorderLayout.CENTER);

        jPanel22.add(jPanel28);

        pf_filtros.add(jPanel22);

        jPanel24.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel24.setLayout(new java.awt.GridLayout(4, 1));

        jPanel27.setLayout(new java.awt.GridLayout(1, 3));

        filtro_is_titular.setText("Titular");
        filtro_is_titular.setName("estitular"); // NOI18N
        filtro_is_titular.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel27.add(filtro_is_titular);

        filtro_is_consumidor.setText("Consumidor");
        filtro_is_consumidor.setName("esconsumidor"); // NOI18N
        filtro_is_consumidor.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel27.add(filtro_is_consumidor);

        jPanel24.add(jPanel27);

        jPanel31.setLayout(new java.awt.BorderLayout());

        jLabel16.setText("Titular");
        jLabel16.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel31.add(jLabel16, java.awt.BorderLayout.WEST);

        filtro_Titular.setName("titular"); // NOI18N
        jPanel31.add(filtro_Titular, java.awt.BorderLayout.CENTER);

        jPanel24.add(jPanel31);

        pf_filtros.add(jPanel24);

        panel_filtros.add(pf_filtros, java.awt.BorderLayout.CENTER);

        add(panel_filtros, java.awt.BorderLayout.NORTH);

        panel_tabla.setPreferredSize(new java.awt.Dimension(1000, 500));
        panel_tabla.setLayout(new java.awt.BorderLayout());

        jPanel30.setMinimumSize(new java.awt.Dimension(980, 30));
        jPanel30.setPreferredSize(new java.awt.Dimension(980, 35));
        jPanel30.setLayout(new java.awt.BorderLayout());

        buscador_tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscador_tablaKeyReleased(evt);
            }
        });
        jPanel30.add(buscador_tabla, java.awt.BorderLayout.CENTER);

        jPanel23.setLayout(new java.awt.BorderLayout());

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        anterior.setPreferredSize(new java.awt.Dimension(100, 40));
        anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anteriorActionPerformed(evt);
            }
        });
        jPanel23.add(anterior, java.awt.BorderLayout.WEST);

        siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/next-button.png"))); // NOI18N
        siguiente.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel23.add(siguiente, java.awt.BorderLayout.EAST);

        jPanel30.add(jPanel23, java.awt.BorderLayout.LINE_END);

        recargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/recargar.png"))); // NOI18N
        recargar.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel30.add(recargar, java.awt.BorderLayout.LINE_START);

        panel_tabla.add(jPanel30, java.awt.BorderLayout.NORTH);

        tabla_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_usuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla_usuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tabla_usuarios);

        panel_tabla.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        add(panel_tabla, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buscador_tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador_tablaKeyReleased
        txt_buscado = buscador_tabla.getText();
        buscador(txt_buscado);
    }//GEN-LAST:event_buscador_tablaKeyReleased

    private void anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_anteriorActionPerformed

    private void buscador(String buscado) {
        List<OUsuarios> toList = cache.stream().filter((t) -> Filtros.limpiar(t.getStringR()).contains(Filtros.limpiar(buscado))).toList();
        if (!modelo_tabla.isDataEmpty()) {
            modelo_tabla.clear();
        }
        for (OUsuarios i : toList) {
            modelo_tabla.addRow(i.getInfoSinFK());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activar_filtros;
    private javax.swing.JButton anterior;
    private javax.swing.JTextField buscador_tabla;
    private javax.swing.JTextField filtro_Titular;
    private javax.swing.JComboBox<String> filtro_calle;
    private javax.swing.JComboBox<String> filtro_estado;
    private javax.swing.JCheckBox filtro_is_consumidor;
    private javax.swing.JCheckBox filtro_is_titular;
    private javax.swing.JButton filtro_quitar;
    private javax.swing.JComboBox<String> filtro_toma;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panel_filtros;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JPanel pf_bar_super;
    private javax.swing.JPanel pf_filtros;
    private javax.swing.JButton recargar;
    private javax.swing.JButton siguiente;
    private javax.swing.JTable tabla_usuarios;
    // End of variables declaration//GEN-END:variables
    private final CBarraEstado barra_estado;
    private final MemoCache<OUsuarios> memoria_cache;
    private final ArrayList<OUsuarios> cache;
    private final ArrayList<OUsuarios> cache_aux;
    private final JComponent[] filtros;
    private String txt_buscado;

    @Override
    public void setVisible(boolean flag) {
        super.setVisible(flag);
        if (flag) {
            cargarFiltros();
            pintarTabla(modelo_tabla, cache);
            System.out.println("usuarios cargados");
        } else {
            quitarFiltros();
            modelo_tabla.clear();
            System.out.println("usuarios quitados");
        }
    }

    private ArrayList<OUsuarios> filtrarLista(final ArrayList<OUsuarios> lista, ArrayList<OUsuarios> aux, Predicate<OUsuarios> filtro) {
        aux.clear();
        for (OUsuarios i : lista) {
            if (filtro.test(i)) {
                aux.add(i);
            }
        }
        return aux;
    }

    private void pintarTabla(DefaultTableModel modelo, ArrayList<OUsuarios> lista) {
        for (OUsuarios i : lista) {
            modelo.addRow(i.getInfoSinFK());
        }
    }

    private void cargarFiltros() {
        ArrayList<OTipoTomas> a = FabricaCache.MC_TIPOS_DE_TOMAS.getLista();
        pintarJCBX(filtro_toma, a);
        ArrayList<OCalles> b = FabricaCache.MC_CALLES.getLista();
        pintarJCBX(filtro_calle, b);
    }

    private void quitarFiltros() {
        if (filtro_calle.getItemCount() > 0) {
            filtro_calle.removeAllItems();
        }
        if (filtro_toma.getItemCount() > 0) {
            filtro_toma.removeAllItems();
        }
        filtro_estado.setSelectedIndex(0);
        filtro_is_consumidor.setSelected(false);
        filtro_is_titular.setSelected(false);
        filtro_Titular.setText(null);
    }

    private <T extends Objeto> void pintarJCBX(JComboBox<String> txt, ArrayList<T> lista) {
        txt.addItem("Seleccione Elemento");
        for (Objeto i : lista) {
            txt.addItem(i.getStringR());
        }
    }

}
