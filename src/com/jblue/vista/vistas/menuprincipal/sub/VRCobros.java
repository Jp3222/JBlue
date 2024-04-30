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
package com.jblue.vista.vistas.menuprincipal.sub;

import com.jblue.util.mg.ModeloTablas;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.controlador.RegistroDePagos;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.sistema.Sesion;
import com.jblue.util.Filtros;
import com.jblue.util.FuncJBlue;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.interpad.pagos.LlavesTipoMov;
import com.jblue.vista.jbmarco.VistaSimple;
import com.jblue.vista.comp.CVisorUsuario;
import com.jblue.vista.vistas.menuprincipal.VCobros;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author jp
 */
public class VRCobros extends VistaSimple {

    /**
     * Creates new form VNewCobros
     *
     * @param root
     */
    public VRCobros(VCobros root) throws HeadlessException {
        memo_cache = FabricaCache.MC_USUARIOS;
        cache = memo_cache.getLista();
        cache_aux = new ArrayList<>(cache.size());
        visor = new CVisorUsuario(null, true);
        initComponents();
        cbx_meses = new JCheckBox[]{
            ene, feb, mar, abr, may, jun, jul, ago, sep, oct, nov, dic
        };
        modelo_pagos_recientes = new ModeloTablas("NO.", "Usuario", "Mes");
        jTable1.setModel(modelo_pagos_recientes);
        modelo_lista = new DefaultListModel<>();
        lista_usuarios.setModel(modelo_lista);
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
        buscador_lista.setText(null);
        if (!modelo_lista.isEmpty()) {
            modelo_lista.clear();
        }
        usuario_tipo.setText(null);
        usuario_nombre.setText(null);
        usuario_tipo_toma.setText(null);
        usuario_meses_pagados.setText(null);
        usuario_costo.setText(null);
        usuario_buscado = null;
        habilitarComponentes(false);
        chb_todos.setSelected(false);
        for (JCheckBox i : cbx_meses) {
            i.setSelected(false);
        }

    }

    @Override

    protected void componentesEstadoFinal() {

    }

    @Override
    protected void construirComponentes() {

    }

    @Override
    protected void manejoEventos() {

        //eventos de botones
        btn_cobrar.addActionListener(e -> evtCobrar(e));
        btn_cancelar.addActionListener(e -> evtCancelar(e));
        btn_limpiar.addActionListener(e -> evtLimpiar(e));
        btn_info_usuarios.addActionListener(e -> setInfoEnVisor(e));

        //btn_movimientos.addActionListener(e -> cobrar(e));
        chb_todos.addItemListener(e -> SwingUtilities.invokeLater(() -> {
            for (JCheckBox i : cbx_meses) {
                if (!i.isEnabled()) {
                    continue;
                }
                i.setSelected(!i.isSelected());
            }
        }));

        for (JCheckBox i : cbx_meses) {
            i.addChangeListener(e -> evtSumTotal());
        }

    }

    private void evtSumTotal() {
        if (usuario_buscado == null) {
            return;
        }
        int meses = getContadorMeses(cbx_meses);
        OTipoTomas toma = EnvUsuario.getTipoDeTomaEnCache(usuario_buscado.getToma());
        if (toma == null) {
            return;
        }
        double total = toma.getCosto() * meses;
        lbl_total.setText(String.valueOf(total));
    }

    private void evtCobrar(ActionEvent e) {
        String[] meses = getMesesSeleccionados(cbx_meses);
        String mensaje;

        if (meses.length > 0) {

            String dinero = JOptionPane.showInputDialog(
                    this,
                    "Ingresa el monto",
                    "Dinero Ingresado",
                    JOptionPane.INFORMATION_MESSAGE
            );

            Map<String, String> resultados;
            String estado;

            if (!Filtros.soloNumerosEnteros(dinero) && !Filtros.soloNumerosDecimales(dinero)) {
                mensaje = "Dato no valido";
            } else {

                resultados = RegistroDePagos.getInstancia().registrarPagoXServicio(
                        Sesion.getInstancia().getUsuario(),
                        usuario_buscado,
                        meses,
                        Double.parseDouble(dinero)
                );

                estado = resultados.get(LlavesTipoMov.LLAVE_ESTADO);

                mensaje = estado.equals(LlavesTipoMov.VALOR_CORRECTO)
                        ? resultados.get(LlavesTipoMov.LLAVE_DATOS)
                        : resultados.get(LlavesTipoMov.LLAVE_ERROR);

            }

        } else {
            mensaje = "Selecciona un mes";
        }

        JOptionPane.showMessageDialog(this, mensaje);

        componentesEstadoInicial();

    }

    private void evtCancelar(ActionEvent e) {
        int in = JOptionPane.showConfirmDialog(this, "¿Desea cancelar esta operacion?");
        if (in != JOptionPane.OK_OPTION) {
            return;
        }
        componentesEstadoInicial();
    }

    /**
     * Metodo para restablecer la etiqueda del "cambio"
     *
     * @param e
     */
    private void evtLimpiar(ActionEvent e) {
        String valor = "0.0";
        lbl_total.setText(valor);
        Jlabel1.setText(valor);
    }

    /**
     * Metodo para asignar el usuario al visor de usuarios
     *
     * @param e
     */
    private void setInfoEnVisor(ActionEvent e) {
        if (usuario_buscado == null) {
            return;
        }
        visor.setUsuario(usuario_buscado);
        visor.setTitle(usuario_buscado.getStringR());
        visor.setVisible(true);
    }

    /**
     * Metodo usado para colocar la informacion del usuario en pantalla
     *
     * @param i
     */
    private void setInfoEnPantalla(OUsuarios i) {
        if (i == null) {
            JOptionPane.showMessageDialog(this, "Usuario no valido");
            return;
        }
        String tipo = i.isTitular() ? "Titular" : "Consumidor";
        usuario_tipo.setText(tipo);
        usuario_nombre.setText(i.getStringR());
        OTipoTomas tipo_de_toma = EnvUsuario.getTipoDeTomaEnCache(i.getToma());
        usuario_tipo_toma.setText(tipo_de_toma.getStringR());
        usuario_costo.setText(String.valueOf(tipo_de_toma.getCosto()));
        ArrayList<String> lista = EnvUsuario.getMesesPagadosDelAño(i.getId());
        usuario_meses_pagados.setText(String.valueOf(lista.size()));
        pintarMesesPagados(lista);
        FuncJBlue.habilitarComponentes(lista.size() < 12, btn_cobrar, btn_limpiar);
    }

    private void pintarMesesPagados(List<String> lista) {
        chb_todos.setEnabled(lista.size() < 12);
        if (chb_todos.isSelected()) {
            return;
        }
        boolean hab;
        for (JCheckBox o : cbx_meses) {
            hab = lista.indexOf(o.getText()) != -1;
            o.setSelected(hab);
            o.setEnabled(!hab);
        }
    }

    private void _buscador(String txt) {
        modelo_lista.clear();
        cache_aux.clear();
        if (Filtros.isNullOrBlank(txt)) {
            return;
        }
        txt = Filtros.limpiar(txt);
        for (OUsuarios i : cache) {
            if (EnvUsuario.filtros(txt, i)) {
                cache_aux.add(i);
                modelo_lista.addElement(String.format("%s - %s", i.getId(), i.getStringR()));
            }
        }
    }

    private void habilitarComponentes(boolean o) {
        FuncJBlue.habilitarComponentes(o, btn_info_usuarios,
                btn_cobrar,
                btn_cancelar,
                btn_limpiar,
                btn_recargos,
                btn_otros_pagos,
                btn_movimientos,
                chb_todos);
        FuncJBlue.habilitarComponentes(o, cbx_meses);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panel_der = new javax.swing.JPanel();
        panel_busquedas = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buscador_lista = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista_usuarios = new javax.swing.JList<>();
        panel_info = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        usuario_tipo = new javax.swing.JTextField();
        btn_info_usuarios = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        usuario_nombre = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        usuario_tipo_toma = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        usuario_costo = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        usuario_meses_pagados = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        chb_todos = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        ene = new javax.swing.JCheckBox();
        feb = new javax.swing.JCheckBox();
        mar = new javax.swing.JCheckBox();
        abr = new javax.swing.JCheckBox();
        may = new javax.swing.JCheckBox();
        jun = new javax.swing.JCheckBox();
        jul = new javax.swing.JCheckBox();
        ago = new javax.swing.JCheckBox();
        sep = new javax.swing.JCheckBox();
        oct = new javax.swing.JCheckBox();
        nov = new javax.swing.JCheckBox();
        dic = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        btn_movimientos = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        Jlabel1 = new javax.swing.JLabel();
        lbl_cambio = new javax.swing.JLabel();
        panel_operaciones = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_cobrar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btn_recargos = new javax.swing.JButton();
        btn_otros_pagos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_utilidades = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(900, 660));
        setName("Registro de cobros"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 660));
        setLayout(new java.awt.BorderLayout());

        panel_izq.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagos Recientes"));
        panel_izq.setMinimumSize(new java.awt.Dimension(500, 660));
        panel_izq.setOpaque(false);
        panel_izq.setPreferredSize(new java.awt.Dimension(500, 660));
        panel_izq.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Usuario", "Mes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        panel_izq.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(panel_izq, java.awt.BorderLayout.WEST);

        panel_der.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de pagos"));
        panel_der.setMinimumSize(new java.awt.Dimension(500, 660));
        panel_der.setOpaque(false);
        panel_der.setPreferredSize(new java.awt.Dimension(700, 660));
        panel_der.setLayout(new java.awt.BorderLayout(0, 10));

        panel_busquedas.setOpaque(false);
        panel_busquedas.setPreferredSize(new java.awt.Dimension(700, 150));
        panel_busquedas.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(680, 40));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Buscador");
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 19));
        jPanel6.add(jLabel1, java.awt.BorderLayout.LINE_START);

        buscador_lista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscador_listaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscador_listaKeyReleased(evt);
            }
        });
        jPanel6.add(buscador_lista, java.awt.BorderLayout.CENTER);

        panel_busquedas.add(jPanel6, java.awt.BorderLayout.NORTH);

        lista_usuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lista_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_usuariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lista_usuarios);

        panel_busquedas.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        panel_der.add(panel_busquedas, java.awt.BorderLayout.PAGE_START);

        panel_info.setOpaque(false);
        panel_info.setPreferredSize(new java.awt.Dimension(700, 400));
        panel_info.setLayout(new java.awt.BorderLayout());

        jPanel19.setOpaque(false);
        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new java.awt.GridLayout(4, 0, 0, 5));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("Tipo de usuario");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel2.add(jLabel7, java.awt.BorderLayout.WEST);

        usuario_tipo.setEditable(false);
        jPanel2.add(usuario_tipo, java.awt.BorderLayout.CENTER);

        btn_info_usuarios.setText("Info de usuario");
        btn_info_usuarios.setPreferredSize(new java.awt.Dimension(150, 29));
        jPanel2.add(btn_info_usuarios, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel2);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("Nombre");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel12.add(jLabel8, java.awt.BorderLayout.WEST);

        usuario_nombre.setEditable(false);
        jPanel12.add(usuario_nombre, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel12);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Tipo de toma");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel13.add(jLabel9, java.awt.BorderLayout.WEST);

        usuario_tipo_toma.setEditable(false);
        usuario_tipo_toma.setPreferredSize(new java.awt.Dimension(200, 36));
        jPanel13.add(usuario_tipo_toma, java.awt.BorderLayout.CENTER);

        jPanel4.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Costo");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel4.add(jLabel3, java.awt.BorderLayout.WEST);

        usuario_costo.setEditable(false);
        usuario_costo.setPreferredSize(new java.awt.Dimension(23, 30));
        jPanel4.add(usuario_costo, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel9.add(jPanel13);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("Meses pagados");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel14.add(jLabel10, java.awt.BorderLayout.WEST);

        usuario_meses_pagados.setEditable(false);
        jPanel14.add(usuario_meses_pagados, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel14);

        jPanel19.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 40));
        jPanel1.setLayout(new java.awt.BorderLayout());

        chb_todos.setText("Todos");
        chb_todos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chb_todos.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel1.add(chb_todos, java.awt.BorderLayout.EAST);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Mese a pagar");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 19));
        jPanel1.add(jLabel6, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(700, 60));
        jPanel11.setLayout(new java.awt.GridLayout(1, 12));

        ene.setText("ENE");
        ene.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ene.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ene.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ene.setPreferredSize(new java.awt.Dimension(10, 47));
        ene.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        ene.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(ene);

        feb.setText("FEB");
        feb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        feb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        feb.setPreferredSize(new java.awt.Dimension(10, 47));
        feb.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        feb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(feb);

        mar.setText("MAR");
        mar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        mar.setPreferredSize(new java.awt.Dimension(10, 47));
        mar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        mar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(mar);

        abr.setText("ABR");
        abr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        abr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        abr.setPreferredSize(new java.awt.Dimension(10, 47));
        abr.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        abr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(abr);

        may.setText("MAY");
        may.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        may.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        may.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        may.setPreferredSize(new java.awt.Dimension(10, 47));
        may.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        may.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(may);

        jun.setText("JUN");
        jun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        jun.setPreferredSize(new java.awt.Dimension(10, 47));
        jun.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        jun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(jun);

        jul.setText("JUL");
        jul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jul.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        jul.setPreferredSize(new java.awt.Dimension(10, 47));
        jul.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        jul.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(jul);

        ago.setText("AGO");
        ago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        ago.setPreferredSize(new java.awt.Dimension(10, 47));
        ago.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        ago.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(ago);

        sep.setText("SEP");
        sep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        sep.setPreferredSize(new java.awt.Dimension(10, 47));
        sep.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        sep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(sep);

        oct.setText("OCT");
        oct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        oct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        oct.setPreferredSize(new java.awt.Dimension(10, 47));
        oct.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        oct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(oct);

        nov.setText("NOV");
        nov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nov.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        nov.setPreferredSize(new java.awt.Dimension(10, 47));
        nov.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        nov.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(nov);

        dic.setText("DIC");
        dic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/cruz.png"))); // NOI18N
        dic.setPreferredSize(new java.awt.Dimension(10, 47));
        dic.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/verificar.png"))); // NOI18N
        dic.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel11.add(dic);

        jPanel10.add(jPanel11, java.awt.BorderLayout.SOUTH);

        jPanel19.add(jPanel10, java.awt.BorderLayout.SOUTH);

        panel_info.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel20.setPreferredSize(new java.awt.Dimension(500, 100));
        jPanel20.setLayout(new java.awt.GridLayout(2, 0));

        jPanel21.setOpaque(false);
        jPanel21.setLayout(new java.awt.BorderLayout(10, 0));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Total: $");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel21.add(jLabel2, java.awt.BorderLayout.WEST);

        lbl_total.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lbl_total.setText("0.0");
        jPanel21.add(lbl_total, java.awt.BorderLayout.CENTER);

        btn_movimientos.setText("Movimientos");
        btn_movimientos.setToolTipText("");
        btn_movimientos.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel21.add(btn_movimientos, java.awt.BorderLayout.LINE_END);

        jPanel20.add(jPanel21);

        jPanel22.setOpaque(false);
        jPanel22.setLayout(new java.awt.BorderLayout(10, 0));

        Jlabel1.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        Jlabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Jlabel1.setText("Cambio: $");
        Jlabel1.setPreferredSize(new java.awt.Dimension(150, 30));
        jPanel22.add(Jlabel1, java.awt.BorderLayout.LINE_START);

        lbl_cambio.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lbl_cambio.setText("0.0");
        jPanel22.add(lbl_cambio, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel22);

        panel_info.add(jPanel20, java.awt.BorderLayout.SOUTH);

        panel_der.add(panel_info, java.awt.BorderLayout.CENTER);

        panel_operaciones.setOpaque(false);
        panel_operaciones.setPreferredSize(new java.awt.Dimension(680, 80));
        panel_operaciones.setLayout(new java.awt.GridLayout(2, 3));

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        btn_cobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/img5.png"))); // NOI18N
        btn_cobrar.setText("Cobrar");
        jPanel3.add(btn_cobrar);

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/cerca.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        jPanel3.add(btn_cancelar);

        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x32/limpiar.png"))); // NOI18N
        btn_limpiar.setText("Limpiar");
        jPanel3.add(btn_limpiar);

        panel_operaciones.add(jPanel3);

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        btn_recargos.setText("Recargos");
        jPanel5.add(btn_recargos);

        btn_otros_pagos.setText("Otros Pagos");
        jPanel5.add(btn_otros_pagos);

        jButton1.setText("Pagos atrasados");
        jPanel5.add(jButton1);

        btn_utilidades.setText("Utilidades");
        jPanel5.add(btn_utilidades);

        panel_operaciones.add(jPanel5);

        panel_der.add(panel_operaciones, java.awt.BorderLayout.PAGE_END);

        add(panel_der, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private boolean usuarioValido() {
        if (lista_usuarios.getModel().getSize() == 1) {
            lista_usuarios.setSelectedIndex(0);
        }
        setUsuario(lista_usuarios.getSelectedIndex());
        return usuario_buscado != null;
    }

    private void setUsuario(int index) {
        if (index < 0 || index > cache_aux.size()) {
            return;
        }
        usuario_buscado = cache_aux.get(index);
        buscador_lista.setText(null);
        lista_usuarios.clearSelection();
        modelo_lista.clear();
    }

    private void buscador_listaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador_listaKeyReleased
        _buscador(buscador_lista.getText());
    }//GEN-LAST:event_buscador_listaKeyReleased

    private void lista_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_usuariosMouseClicked
        if (evt.getClickCount() < 2) {
            return;
        }
        if (!usuarioValido()) {
            return;
        }
        visor.setUsuario(usuario_buscado);
        habilitarComponentes(true);
        SwingUtilities.invokeLater(() -> setInfoEnPantalla(usuario_buscado));

    }//GEN-LAST:event_lista_usuariosMouseClicked


    private void buscador_listaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscador_listaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!usuarioValido()) {
                return;
            }
            visor.setUsuario(usuario_buscado);
            habilitarComponentes(true);
            SwingUtilities.invokeLater(() -> setInfoEnPantalla(usuario_buscado));
        }
    }//GEN-LAST:event_buscador_listaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel1;
    private javax.swing.JCheckBox abr;
    private javax.swing.JCheckBox ago;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cobrar;
    private javax.swing.JButton btn_info_usuarios;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_movimientos;
    private javax.swing.JButton btn_otros_pagos;
    private javax.swing.JButton btn_recargos;
    private javax.swing.JButton btn_utilidades;
    private javax.swing.JTextField buscador_lista;
    private javax.swing.JCheckBox chb_todos;
    private javax.swing.JCheckBox dic;
    private javax.swing.JCheckBox ene;
    private javax.swing.JCheckBox feb;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox jul;
    private javax.swing.JCheckBox jun;
    private javax.swing.JLabel lbl_cambio;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JList<String> lista_usuarios;
    private javax.swing.JCheckBox mar;
    private javax.swing.JCheckBox may;
    private javax.swing.JCheckBox nov;
    private javax.swing.JCheckBox oct;
    private javax.swing.JPanel panel_busquedas;
    private javax.swing.JPanel panel_der;
    private javax.swing.JPanel panel_info;
    private javax.swing.JPanel panel_izq;
    private javax.swing.JPanel panel_operaciones;
    private javax.swing.JCheckBox sep;
    private javax.swing.JTextField usuario_costo;
    private javax.swing.JTextField usuario_meses_pagados;
    private javax.swing.JTextField usuario_nombre;
    private javax.swing.JTextField usuario_tipo;
    private javax.swing.JTextField usuario_tipo_toma;
    // End of variables declaration//GEN-END:variables
    private final MemoCache<OUsuarios> memo_cache;
    private final ArrayList<OUsuarios> cache;
    private final ArrayList<OUsuarios> cache_aux;
    private final ModeloTablas modelo_pagos_recientes;
    private final DefaultListModel<String> modelo_lista;
    private final JCheckBox[] cbx_meses;
    private OUsuarios usuario_buscado;
    private final CVisorUsuario visor;

    private int getContadorMeses(JCheckBox... meses) {
        int contador = 0;
        for (JCheckBox i : meses) {
            if (i.isEnabled() && i.isSelected()) {
                contador++;
            }
        }
        return contador;
    }

    private String[] getMesesSeleccionados(JCheckBox... meses) {
        ArrayList<String> meses_seleccionados = new ArrayList<>(12);
        for (JCheckBox i : meses) {
            if (i.isEnabled() && i.isSelected()) {
                meses_seleccionados.add(i.getText());
            }
        }
        return meses_seleccionados.toArray(String[]::new);
    }

    public void bloquear(boolean o) {
        FuncJBlue.bloquearArbolComponentes(o, panel_der);
        componentesEstadoInicial();
    }

}
