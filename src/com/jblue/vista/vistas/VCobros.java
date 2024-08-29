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

import com.jblue.vista.vistas.cobros.VRCobros;
import com.jblue.vista.vistas.cobros.VCCobros;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.sistema.Sesion;
import com.jblue.util.Filtros;
import com.jblue.modelo.factories.FabricaCache;
import com.jblue.util.trash.MemoCache;
import com.jblue.util.crypto.EncriptadoAES;
import com.jblue.vista.componentes.CSelectorObjeto;
import com.jblue.vista.componentes.CVisorUsuario;
import com.jblue.vista.marco.vistas.VistaExtendida;
import com.jutil.jexception.Excp;
import java.awt.CardLayout;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class VCobros extends VistaExtendida {

    /**
     * Creates new form VUsuarios
     */
    public VCobros() {
        initComponents();
        memoria_cache = FabricaCache.MC_USUARIOS;
        registros = new VRCobros(this);
        consultas = new VCCobros(this);
        ly_root = (CardLayout) panel_root.getLayout();
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
        panel_root.add(registros.getName(), registros);
        panel_root.add(consultas.getName(), consultas);
    }

    @Override
    public void componentesEstadoInicial() {
        lockComonents(false);
    }

    @Override
    protected void eventos() {
        bnt_registros.addActionListener(e -> {
            if (registros.isVisible()) {
                return;
            }
            ly_root.show(panel_root, registros.getName());
        });

        btn_consultas.addActionListener(e -> {
            if (consultas.isVisible()) {
                return;
            }
            ly_root.show(panel_root, consultas.getName());
        });
        btn_buscador.addActionListener(e -> evtBuscador());
        btn_bloq_caja.addActionListener(e -> evtBloc_cj());
        btn_desbloc_caja.addActionListener(e -> evtDesbloc_cj());
    }

    private void evtBuscador() {
        OUsuarios o = CSelectorObjeto.selectorUsuarios(null);
        if (o == null) {
            return;
        }

        CVisorUsuario.showVisor(o);
    }

    private void evtBloc_cj() {
        int in = JOptionPane.showConfirmDialog(this,
                "¿Desea bloquear el apartado de caja?",
                "Bloquear Caja",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (in == JOptionPane.YES_OPTION) {
            lockComonents(true);
        }
    }

    private void evtDesbloc_cj() {
        try {
            String entrada = JOptionPane.showInputDialog(this,
                    "Ingrese su contraseña para desbloquear caja",
                    "Desbloquear Caja",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (Filtros.isNullOrBlank(entrada)) {
                return;
            }
            Sesion instancia = Sesion.getInstancia();
            String desencriptar = EncriptadoAES.desencriptar(instancia.getUsuario().getUsuario(), entrada);
            entrada = EncriptadoAES.encriptar(entrada, desencriptar);
            if (entrada.equals(instancia.getUsuario().getContra())) {
                lockComonents(false);
            }

        } catch (UnsupportedEncodingException
                | NoSuchAlgorithmException
                | InvalidKeyException
                | NoSuchPaddingException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Desbloquear Caja", JOptionPane.ERROR_MESSAGE);
            Excp.impTerminal(ex, getClass(), false);
        }

    }

    private void lockComonents(boolean o) {
        btn_desbloc_caja.setEnabled(o);
        btn_bloq_caja.setEnabled(!o);
        registros.lock(!o);
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
        bar_panel_izq = new javax.swing.JPanel();
        btn_bloq_caja = new javax.swing.JButton();
        btn_desbloc_caja = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bar_panel_central = new javax.swing.JPanel();
        bnt_registros = new javax.swing.JButton();
        btn_consultas = new javax.swing.JButton();
        bar_panel_der = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_buscador = new javax.swing.JButton();
        panel_root = new javax.swing.JPanel();

        setName("Cobros"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1200, 660));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 30));
        jPanel1.setLayout(new java.awt.GridLayout(1, 3));

        bar_panel_izq.setPreferredSize(new java.awt.Dimension(300, 30));
        bar_panel_izq.setLayout(new java.awt.GridLayout(1, 5));

        btn_bloq_caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/bloqueado.png"))); // NOI18N
        btn_bloq_caja.setToolTipText("Bloquear caja");
        bar_panel_izq.add(btn_bloq_caja);

        btn_desbloc_caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/desbloquear.png"))); // NOI18N
        btn_desbloc_caja.setToolTipText("desbloquear caja");
        bar_panel_izq.add(btn_desbloc_caja);

        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setEnabled(false);
        bar_panel_izq.add(jButton1);

        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setEnabled(false);
        bar_panel_izq.add(jButton2);

        jPanel1.add(bar_panel_izq);

        bar_panel_central.setOpaque(false);
        bar_panel_central.setPreferredSize(new java.awt.Dimension(400, 30));
        bar_panel_central.setLayout(new java.awt.GridLayout(1, 0));

        bnt_registros.setText("Registros");
        bar_panel_central.add(bnt_registros);

        btn_consultas.setText("Consultas");
        bar_panel_central.add(btn_consultas);

        jPanel1.add(bar_panel_central);

        bar_panel_der.setOpaque(false);
        bar_panel_der.setPreferredSize(new java.awt.Dimension(300, 30));
        bar_panel_der.setLayout(new java.awt.BorderLayout());

        jLabel3.setPreferredSize(new java.awt.Dimension(30, 250));
        bar_panel_der.add(jLabel3, java.awt.BorderLayout.CENTER);

        btn_buscador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jblue/media/img/x24/buscar.png"))); // NOI18N
        btn_buscador.setToolTipText("Buscar Usuario");
        btn_buscador.setPreferredSize(new java.awt.Dimension(50, 30));
        bar_panel_der.add(btn_buscador, java.awt.BorderLayout.EAST);

        jPanel1.add(bar_panel_der);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        panel_root.setOpaque(false);
        panel_root.setLayout(new java.awt.CardLayout());
        add(panel_root, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bar_panel_central;
    private javax.swing.JPanel bar_panel_der;
    private javax.swing.JPanel bar_panel_izq;
    private javax.swing.JButton bnt_registros;
    private javax.swing.JButton btn_bloq_caja;
    private javax.swing.JButton btn_buscador;
    private javax.swing.JButton btn_consultas;
    private javax.swing.JButton btn_desbloc_caja;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panel_root;
    // End of variables declaration//GEN-END:variables
    private final VRCobros registros;
    private final VCCobros consultas;
    private final MemoCache<OUsuarios> memoria_cache;
    private final CardLayout ly_root;

    public MemoCache<OUsuarios> getMemoria_cache() {
        return memoria_cache;
    }

}
