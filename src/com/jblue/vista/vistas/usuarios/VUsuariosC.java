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
package com.jblue.vista.vistas.usuarios;

import com.jutil.swingw.modelos.TableModel;
import com.jblue.modelo.ConstGs;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.absobj.Objeto;
import com.jblue.util.Filtros;
import com.jblue.util.FuncJBlue;
import com.jblue.modelo.factories.FabricaCache;
import com.jblue.util.tools.UtilUsuario;
import com.jblue.util.trash.MemoCache;
import com.jblue.vista.marco.vistas.VistaSimple;
import com.jblue.vista.vistas.VUsuarios;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class VUsuariosC extends VistaSimple {

    private final TableModel modelo_tabla;

    /**
     * Creates new form VUsuariosC
     *
     * @param root
     */
    public VUsuariosC(VUsuarios root) {
        initComponents();
        memoria_cache = root.getMemo_cache();
        cache = memoria_cache.getLista();
        mapa_filtros = new HashMap<>();
        modelo_tabla = new TableModel(ConstGs.TABLA_USUARIOS, 0);
        modelo_tabla.setCellsEditables(false);
        componentes_filtros = new JComponent[]{
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
        eventos();

    }

    @Override
    protected void componentesEstadoFinal() {
        tabla_usuarios.setModel(modelo_tabla);

        mapa_filtros.put(filtro_calle.getName(), (t) -> {
            return filtroCBXValido(filtro_calle)
                    ? filtro_calle.getItemAt(filtro_calle.getSelectedIndex()).getId().equals(t.getCalle()) : true;
        });

        mapa_filtros.put(filtro_toma.getName(), t -> {
            return filtroCBXValido(filtro_toma)
                    ? filtro_toma.getItemAt(filtro_toma.getSelectedIndex()).getId().equals(t.getToma()) : true;
        });

        mapa_filtros.put(filtro_estado.getName(), t -> {
            if (!filtroCBXValido(filtro_estado)) {
                return true;
            }
            String estado = switch (filtro_estado.getItemAt(filtro_estado.getSelectedIndex())) {
                case "ACTIVO":
                    yield "1";
                case "INACTIVO":
                    yield "-1";
                default:
                    yield "0";
            };
            return String.valueOf(t.getEstado()).equals(estado);
        });

        mapa_filtros.put(filtro_is_titular.getName(), t -> {
            if (!filtros.isSelected()) {
                return true;
            }
            if (filtro_is_titular.isSelected()) {
                return t.isTitular();
            } else if (filtro_is_consumidor.isSelected()) {
                return !t.isTitular();
            }
            return true;
        });

        mapa_filtros.put(buscador_tabla.getName(), (t) -> {
            return Filtros.isNullOrBlank(buscador_tabla.getText())
                    ? true : Filtros.limpiar(t.toString()).contains(Filtros.limpiar(buscador_tabla.getText()));

        });
    }

    @Override
    public void componentesEstadoInicial() {
        filtros.setSelected(false);
        FuncJBlue.habilitarComponentes(false, componentes_filtros);
    }

    @Override
    protected void eventos() {
        recargar.addActionListener(e -> {
            buscador_tabla.setText(null);
            recargarTabla(2, modelo_tabla, cache);
        });

        filtros.addItemListener((i) -> FuncJBlue.habilitarComponentes(filtros.isSelected(), componentes_filtros));
        filtro_quitar.addActionListener(e -> FuncJBlue.habilitarComponentes(false, componentes_filtros));
        filtro_calle.addItemListener(e -> buscador());
        filtro_toma.addItemListener(e -> buscador());
        filtro_estado.addItemListener(e -> buscador());
        filtro_is_titular.addItemListener(e -> {
            if (filtro_is_consumidor.isSelected()) {
                filtro_is_consumidor.setSelected(false);
            }
        });
        filtro_is_consumidor.addItemListener(e -> {
            if (filtro_is_titular.isSelected()) {
                filtro_is_titular.setSelected(false);
            }
        });

        buscador_tabla.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscador();
            }

        });
        //filtro_is_titular.addItemListener(e -> buscador());
        //filtro_is_consumidor.addItemListener(e -> buscador());
    }

    private boolean filtroCBXValido(JComboBox o) {
        return filtros.isSelected() && o.getSelectedIndex() > 0;
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
        filtros = new javax.swing.JCheckBox();
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

        filtros.setText("Filtros");
        pf_bar_super.add(filtros, java.awt.BorderLayout.CENTER);

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

        buscador_tabla.setName("buscador"); // NOI18N
        jPanel30.add(buscador_tabla, java.awt.BorderLayout.CENTER);

        jPanel23.setLayout(new java.awt.BorderLayout());

        anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/previous.png"))); // NOI18N
        anterior.setPreferredSize(new java.awt.Dimension(100, 40));
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

    private void buscador() {
        List<OUsuarios> toList;
        toList = cache.stream()
                .filter(mapa_filtros.get(filtro_calle.getName()))
                .filter(mapa_filtros.get(filtro_toma.getName()))
                .filter(mapa_filtros.get(filtro_estado.getName()))
                .filter(mapa_filtros.get(buscador_tabla.getName()))
                .toList();
        recargarTabla(1, modelo_tabla, toList);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anterior;
    private javax.swing.JTextField buscador_tabla;
    private javax.swing.JTextField filtro_Titular;
    private javax.swing.JComboBox<OCalles> filtro_calle;
    private javax.swing.JComboBox<String> filtro_estado;
    private javax.swing.JCheckBox filtro_is_consumidor;
    private javax.swing.JCheckBox filtro_is_titular;
    private javax.swing.JButton filtro_quitar;
    private javax.swing.JComboBox<OTipoTomas> filtro_toma;
    private javax.swing.JCheckBox filtros;
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

    private final MemoCache<OUsuarios> memoria_cache;
    private final ArrayList<OUsuarios> cache;
    private final JComponent[] componentes_filtros;
    private final Map<String, Predicate<OUsuarios>> mapa_filtros;

    @Override
    public void setVisible(boolean flag) {
        super.setVisible(flag);
        if (flag) {
            System.out.println("visible");
            cargarFiltros();
            pintarTabla(0, modelo_tabla, cache);
        } else {
            System.out.println("invisible");
            quitarFiltros();
            modelo_tabla.removeAllRows();
        }
    }

    private void pintarTabla(int where, DefaultTableModel model, List<OUsuarios> lista) {
        TableModel modelo = (TableModel) model;
        if (modelo.getRowCount() > 0) {
            modelo.removeAllRows();
        }
        for (OUsuarios i : lista) {
            modelo.addRow(i.getInfoSinFK());
        }
    }

    private void recargarTabla(int where, TableModel modelo_tabla, List<OUsuarios> lista) {
        List<String> rowData = modelo_tabla.getRowData(0);
        int size = lista.stream().filter((t) -> !rowData.contains(t.getId())).toList().size();
        if (rowData.size() == lista.size() && size == 0) {
            return;
        }
        if (modelo_tabla.getRowCount() > 0) {
            modelo_tabla.removeAllRows();
        }
        pintarTabla(where, modelo_tabla, lista);
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

    private <T extends Objeto> void pintarJCBX(JComboBox<T> txt, ArrayList<T> lista) {
        txt.addItem((T) new Objeto() {
            @Override
            public String toString() {
                return "Seleccione un elemento"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

        });
        for (T i : lista) {
            txt.addItem(i);
        }
    }

}
