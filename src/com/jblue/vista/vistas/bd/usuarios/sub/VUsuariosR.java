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
package com.jblue.vista.vistas.bd.usuarios.sub;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.sucls.Objeto;
import com.jblue.util.Filtros;
import com.jblue.util.FormatoBD;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.tiempo.Fecha;
import com.jblue.vista.normas.SuperVista;
import com.jblue.vista.vistas.normas.Vista;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jp
 */
public class VUsuariosR extends SuperVista {

    private final JTextField[] arr_campos_texto;
    private final JComboBox[] arr_campos_combo_box;

    /**
     * Creates new form VUsuarios
     *
     * @param root
     */
    public VUsuariosR(Vista root) {
        initComponents();
        memoria_cache = root.getMemo_cache();
        cache = memoria_cache.getLista();
        cache_aux = new ArrayList<>(cache.size());
        modelo_lista = new DefaultListModel<>();
        jlUsuarios.setModel(modelo_lista);

        arr_campos_texto = new JTextField[]{
            campo_nombre,
            campo_ap,
            campo_am
        };

        arr_campos_combo_box = new JComboBox[]{
            campo_titular,
            campo_tipo_toma,
            campo_calle
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

    }

    @Override
    public void componentesEstadoInicial() {
        if (!modelo_lista.isEmpty()) {
            modelo_lista.clear();
        }
        for (JTextField i : arr_campos_texto) {
            i.setText(null);
        }
        for (JComboBox i : arr_campos_combo_box) {
            if (i.getItemCount() == 0) {
                continue;
            }
            i.setSelectedIndex(0);
        }
        campo_titular.setEnabled(false);
        campo_no_casa.setText(null);
        campo_is_titular.setSelected(false);

    }

    @Override
    protected void manejoEventos() {
        campo_is_usuario.addItemListener(e -> evtItem(e));
        campo_is_titular.addItemListener(e -> evtItem(e));
        btn_guardar.addActionListener(e -> evtGuardar());
        btn_eliminar.addActionListener(e -> evtEliminar());
        btn_actualizar.addActionListener(e -> evtActualizar());
        btn_cancelar.addActionListener(e -> evtCancelar());
    }

    private void evtItem(ItemEvent e) {
        JCheckBox item = (JCheckBox) e.getSource();
        if (item == campo_is_usuario && campo_is_usuario.isSelected()) {
            campo_is_titular.setSelected(!campo_is_usuario.isSelected());
            campo_titular.setVisible(campo_is_usuario.isSelected());
            campo_titular.setEnabled(campo_is_usuario.isSelected());
        } else if (item == campo_is_titular && campo_is_titular.isSelected()) {
            campo_is_usuario.setSelected(!campo_is_titular.isSelected());
            campo_titular.setVisible(!campo_is_titular.isSelected());
        }

    }

    public void evtGuardar() {
        if (!camposValidos()) {
            return;
        }
        String[] valores = getInfo(false);
        String txt = "(";
        for (String i : valores) {
            txt = txt.concat(i).concat(",");
        }
        txt = txt.concat(")");
        JOptionPane.showMessageDialog(this, txt);
    }

    public void evtActualizar() {
    }

    private void mov(boolean mov) {
        if (mov) {
            memoria_cache.actualizar();
            pintarJCBX(campo_titular, cache);
            componentesEstadoInicial();
            JOptionPane.showMessageDialog(this, "Operacion Exitosa");
        } else {
            JOptionPane.showMessageDialog(this, "Operacion Erronea");
        }
    }

    private String[] getInfo(boolean act) {
        String nombre = campo_nombre.getText();
        String ap = campo_ap.getText();
        String am = campo_am.getText();
        String calle = campo_calle.getItemAt(campo_calle.getSelectedIndex());
        String no_casa = campo_no_casa.getText();
        String tipo_toma = campo_tipo_toma.getItemAt(campo_tipo_toma.getSelectedIndex());
        String registro;
        if (act) {
            registro = usuario_buscado.getRegistro();
        } else {
            LocalDate ld = LocalDate.now();
            registro = ld.format(Fecha.FORMATO);
        }
        String estado = campo_estado.getSelectedIndex() == 1 ? "1" : "-1";
        String titular = null;

        if (campo_is_titular.isSelected()) {
            titular = "-1";
        } else if (campo_is_usuario.isSelected() && campo_titular.getSelectedIndex() == 0) {
            titular = "-2";
        } else if (campo_is_usuario.isSelected()) {
            OUsuarios usuario = EnvUsuario.getUsuarioEnCache(
                    campo_titular.getItemAt(campo_titular.getSelectedIndex())
            );
            titular = usuario.getId();
        }

        String codigo = "NULL";
        String[] arr = new String[]{
            nombre, ap, am, calle, no_casa, tipo_toma, registro, estado, titular, codigo
        };
        arr = FormatoBD.bdEntrada(arr);
        return arr;
    }

    private boolean camposValidos() {
        return validarTextFields() && valirarComboBox();
    }

    private boolean validarTextFields() {
        String aux;
        for (JTextField i : arr_campos_texto) {
            aux = i.getText();
            if (Filtros.isNullOrBlank(aux)) {
                JOptionPane.showMessageDialog(this, String.format("Campo %s no valido", i.getName()));
                return false;
            }
        }
        return true;
    }

    private boolean valirarComboBox() {
        for (JComboBox i : arr_campos_combo_box) {
            if (i == campo_titular && (campo_is_titular.isSelected() || campo_is_usuario.isSelected())) {
                System.out.println("xd");
                continue;
            }
            if (!Filtros.swIsCbxRangoValido(i)) {
                System.out.println("xdddd");
                JOptionPane.showMessageDialog(this, String.format("Campo %s no valido", i.getName()));
                return false;
            }
        }
        if (campo_estado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, String.format("Campo %s no valido", campo_estado.getName()));
            return false;
        }
        return true;
    }

    public void evtEliminar() {
        int input = JOptionPane.showConfirmDialog(this, "¿Desea eliminar esta operacion?");
        if (input != JOptionPane.YES_OPTION) {
            return;
        }
        Operaciones<OUsuarios> operaciones = memoria_cache.getOperaciones();

    }

    public void evtCancelar() {
        int input = JOptionPane.showConfirmDialog(this, "¿Desea cancelar esta operacion?");
        if (input != JOptionPane.YES_OPTION) {
            return;
        }
        componentesEstadoInicial();
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
        jPanel12 = new javax.swing.JPanel();
        jtfBuscadorLista = new javax.swing.JTextField();
        solo_activos = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlUsuarios = new javax.swing.JList<>();
        panel_der = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        panelCampos = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campo_nombre = new javax.swing.JTextField();
        coincidencias = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        campo_ap = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        campo_am = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        campo_is_titular = new javax.swing.JCheckBox();
        campo_is_usuario = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        campo_titular = new javax.swing.JComboBox<>();
        man_titular_asociado = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        campo_tipo_toma = new javax.swing.JComboBox<>();
        man_tipo_toma = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        campo_calle = new javax.swing.JComboBox<>();
        man_calle = new javax.swing.JCheckBox();
        jPanel18 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campo_no_casa = new javax.swing.JTextField();
        sn_numero = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        campo_estado = new javax.swing.JComboBox<>();
        man_estado = new javax.swing.JCheckBox();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        campo_codigo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setName("Registro de Usuarios"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        panel_izq.setPreferredSize(new java.awt.Dimension(500, 670));
        panel_izq.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel12.setPreferredSize(new java.awt.Dimension(500, 45));
        jPanel12.setLayout(new java.awt.BorderLayout(10, 0));

        jtfBuscadorLista.setPreferredSize(new java.awt.Dimension(500, 30));
        jtfBuscadorLista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtfBuscadorListaKeyReleased(evt);
            }
        });
        jPanel12.add(jtfBuscadorLista, java.awt.BorderLayout.CENTER);

        solo_activos.setText("Activos");
        solo_activos.setPreferredSize(new java.awt.Dimension(100, 22));
        jPanel12.add(solo_activos, java.awt.BorderLayout.EAST);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/buscar.png"))); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel12.add(jLabel7, java.awt.BorderLayout.LINE_START);

        jLabel18.setPreferredSize(new java.awt.Dimension(500, 10));
        jPanel12.add(jLabel18, java.awt.BorderLayout.PAGE_START);

        panel_izq.add(jPanel12, java.awt.BorderLayout.NORTH);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 600));

        jlUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jlUsuarios);

        panel_izq.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(panel_izq, java.awt.BorderLayout.WEST);

        panel_der.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(500, 300));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(500, 700));

        jPanel3.setPreferredSize(new java.awt.Dimension(500, 600));
        jPanel3.setLayout(new java.awt.BorderLayout());

        panelCampos.setPreferredSize(new java.awt.Dimension(500, 600));
        panelCampos.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(500, 150));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel15.setLayout(new java.awt.GridLayout(2, 0));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Nombre: ");
        jLabel2.setMaximumSize(new java.awt.Dimension(60, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel4.add(jLabel2, java.awt.BorderLayout.NORTH);

        campo_nombre.setToolTipText("<html>\nCampo: Nombre\n<br>valores admitidos: Solo texto\n<br>tamaño maximo: 32 Caracteres");
        campo_nombre.setName("Nombre"); // NOI18N
        campo_nombre.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel4.add(campo_nombre, java.awt.BorderLayout.CENTER);

        coincidencias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencias.setText("0");
        coincidencias.setToolTipText("<html>\n<h1> Numero de coincidencias.</h1>\n<br>\n<p> Este campos e activa con un espacio.<br> y toma encuenta las coincidencias del nombre, apellido paterno y apellido materno</p>");
        coincidencias.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel4.add(coincidencias, java.awt.BorderLayout.EAST);

        jPanel15.add(jPanel4);

        jPanel5.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jPanel35.setPreferredSize(new java.awt.Dimension(250, 30));
        jPanel35.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("A. Paterno: ");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel35.add(jLabel3, java.awt.BorderLayout.NORTH);

        campo_ap.setToolTipText("<html>\nCampos: Apellido Paterno\n<br>Valor: Solo texto \n<br>Longitud: 32 Caracteres");
        campo_ap.setName("A. Paterno"); // NOI18N
        campo_ap.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel35.add(campo_ap, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel35);

        jPanel6.setPreferredSize(new java.awt.Dimension(250, 30));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel4.setText("A. Materno:");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel6.add(jLabel4, java.awt.BorderLayout.NORTH);

        campo_am.setToolTipText("<html> Campos: Apellido Materno\n<br>Valor: Solo texto <br>Longitud: 32 Caracteres");
        campo_am.setName("A. Materno"); // NOI18N
        campo_am.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel6.add(campo_am, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6);

        jPanel15.add(jPanel5);

        jPanel2.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel9.setLayout(new java.awt.GridLayout(1, 2));

        campo_is_titular.setText("Titutlar");
        campo_is_titular.setToolTipText("Mantener el roll del usuario, seleccionado");
        campo_is_titular.setPreferredSize(new java.awt.Dimension(500, 30));
        jPanel9.add(campo_is_titular);

        campo_is_usuario.setText("usuario");
        jPanel9.add(campo_is_usuario);

        jPanel2.add(jPanel9, java.awt.BorderLayout.SOUTH);

        panelCampos.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(6, 0));

        jPanel11.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("T. asociado");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel11.add(jLabel9, java.awt.BorderLayout.NORTH);

        campo_titular.setName("Titular Asociado"); // NOI18N
        campo_titular.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(campo_titular, java.awt.BorderLayout.CENTER);

        man_titular_asociado.setText("Man.");
        man_titular_asociado.setToolTipText("Mantener el titular seleccionado");
        man_titular_asociado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_titular_asociado.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel11.add(man_titular_asociado, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel11);

        jPanel7.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("T. Toma");
        jLabel5.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel7.add(jLabel5, java.awt.BorderLayout.NORTH);

        campo_tipo_toma.setName("Tipo de toma"); // NOI18N
        campo_tipo_toma.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel7.add(campo_tipo_toma, java.awt.BorderLayout.CENTER);

        man_tipo_toma.setText("Man.");
        man_tipo_toma.setToolTipText("Mantener el tipo de toma seleccionado");
        man_tipo_toma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_tipo_toma.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel7.add(man_tipo_toma, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel7);

        jPanel8.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Calle: ");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel8.add(jLabel6, java.awt.BorderLayout.NORTH);

        campo_calle.setName("Calle"); // NOI18N
        campo_calle.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel8.add(campo_calle, java.awt.BorderLayout.CENTER);

        man_calle.setText("Man.");
        man_calle.setToolTipText("Mantener la calle seleccionada\n");
        man_calle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_calle.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel8.add(man_calle, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel8);

        jPanel18.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("N. Casa");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel18.add(jLabel1, java.awt.BorderLayout.NORTH);

        campo_no_casa.setToolTipText("<html> Campo: Numero de casa  <br>Valor: Solo numeros <br>Longitud: 3 Caracteres");
        campo_no_casa.setName("Numero de Casa"); // NOI18N
        campo_no_casa.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel18.add(campo_no_casa, java.awt.BorderLayout.CENTER);

        sn_numero.setText("S/N");
        sn_numero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sn_numero.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel18.add(sn_numero, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel18);

        jPanel10.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Estado: ");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel10.add(jLabel8, java.awt.BorderLayout.NORTH);

        campo_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona Elemento.", "Activo.", "Inactivo." }));
        campo_estado.setName("Estado"); // NOI18N
        campo_estado.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel10.add(campo_estado, java.awt.BorderLayout.CENTER);

        man_estado.setText("Man.");
        man_estado.setToolTipText("Mantener el estado del usuario seleccionado\n");
        man_estado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        man_estado.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel10.add(man_estado, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel10);

        jPanel14.setPreferredSize(new java.awt.Dimension(500, 50));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("codigo");
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel14.add(jLabel10, java.awt.BorderLayout.NORTH);

        campo_codigo.setEditable(false);
        campo_codigo.setName("Codigo de Identificacion"); // NOI18N
        campo_codigo.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel14.add(campo_codigo, java.awt.BorderLayout.CENTER);

        jLabel19.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel14.add(jLabel19, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel14);

        panelCampos.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.add(panelCampos, java.awt.BorderLayout.CENTER);

        panelBotones.setPreferredSize(new java.awt.Dimension(500, 100));
        panelBotones.setLayout(new java.awt.GridLayout(2, 0));

        jPanel13.setPreferredSize(new java.awt.Dimension(500, 40));
        jPanel13.setLayout(new java.awt.GridLayout(1, 3));

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/disquete.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setPreferredSize(new java.awt.Dimension(166, 40));
        jPanel13.add(btn_guardar);

        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/actualizar.png"))); // NOI18N
        btn_actualizar.setText("Actualizar");
        btn_actualizar.setPreferredSize(new java.awt.Dimension(166, 40));
        jPanel13.add(btn_actualizar);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/eliminar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setPreferredSize(new java.awt.Dimension(166, 40));
        jPanel13.add(btn_eliminar);

        panelBotones.add(jPanel13);

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setPreferredSize(new java.awt.Dimension(500, 40));
        panelBotones.add(btn_cancelar);

        jPanel3.add(panelBotones, java.awt.BorderLayout.SOUTH);

        jScrollPane2.setViewportView(jPanel3);

        panel_der.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(panel_der, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jtfBuscadorListaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscadorListaKeyReleased
        String y = Filtros.limpiar(jtfBuscadorLista.getText());
        if (y.isBlank()) {
            return;
        }
        buscador(cache, cache_aux, (t) -> {
            String x = Filtros.limpiar(t.getStringR());
            return x.contains(y);
        });
        pintarLista(modelo_lista, cache_aux);
    }//GEN-LAST:event_jtfBuscadorListaKeyReleased

    private void buscador(ArrayList<OUsuarios> lista, ArrayList<OUsuarios> lista_aux, Predicate<OUsuarios> filtro) {
        lista_aux.clear();
        List<OUsuarios> toList = lista.stream().filter(filtro).toList();
        for (OUsuarios i : toList) {
            lista_aux.add(i);
        }
    }

    private void pintarLista(final DefaultListModel<String> modelo, final ArrayList<OUsuarios> lista) {
        modelo.clear();
        modelo.setSize(lista.size());
        int x = 0;
        for (OUsuarios i : lista) {
            modelo.add(x, String.format("%s - %s", i.getId(), i.getStringR()));
            x++;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JTextField campo_am;
    private javax.swing.JTextField campo_ap;
    private javax.swing.JComboBox<String> campo_calle;
    private javax.swing.JTextField campo_codigo;
    private javax.swing.JComboBox<String> campo_estado;
    private javax.swing.JCheckBox campo_is_titular;
    private javax.swing.JCheckBox campo_is_usuario;
    private javax.swing.JTextField campo_no_casa;
    private javax.swing.JTextField campo_nombre;
    private javax.swing.JComboBox<String> campo_tipo_toma;
    private javax.swing.JComboBox<String> campo_titular;
    private javax.swing.JLabel coincidencias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> jlUsuarios;
    private javax.swing.JTextField jtfBuscadorLista;
    private javax.swing.JCheckBox man_calle;
    private javax.swing.JCheckBox man_estado;
    private javax.swing.JCheckBox man_tipo_toma;
    private javax.swing.JCheckBox man_titular_asociado;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JPanel panel_der;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JCheckBox sn_numero;
    private javax.swing.JCheckBox solo_activos;
    // End of variables declaration//GEN-END:variables
    private final MemoCache<OUsuarios> memoria_cache;
    private final ArrayList<OUsuarios> cache;
    private final ArrayList<OUsuarios> cache_aux;
    private final DefaultListModel<String> modelo_lista;
    private OUsuarios usuario_buscado;

    @Override
    public void setVisible(boolean flag) {
        super.setVisible(flag); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if (flag) {
            cargarCampos();
        } else {
            vaciarCampos();
        }
    }

    private void cargarCampos() {
        ArrayList<OUsuarios> a = FabricaCache.MC_USUARIOS.getLista();
        List<OUsuarios> name = a.stream().filter(e -> e.isTitular()).toList();
        pintarJCBX(campo_titular, name);
        ArrayList<OTipoTomas> b = FabricaCache.MC_TIPOS_DE_TOMAS.getLista();
        pintarJCBX(campo_tipo_toma, b);
        ArrayList<OCalles> c = FabricaCache.MC_CALLES.getLista();
        pintarJCBX(campo_calle, c);
    }

    private void vaciarCampos() {
        if (campo_titular.getItemCount() > 0) {
            campo_titular.removeAllItems();
        }
        if (campo_calle.getItemCount() > 0) {
            campo_calle.removeAllItems();
        }
        if (campo_tipo_toma.getItemCount() > 0) {
            campo_tipo_toma.removeAllItems();
        }

    }

    private <T extends Objeto> void pintarJCBX(JComboBox<String> txt, List<T> lista) {
        txt.addItem("Seleccione Elemento");
        for (Objeto i : lista) {
            txt.addItem(i.getStringR());
        }
    }

}
