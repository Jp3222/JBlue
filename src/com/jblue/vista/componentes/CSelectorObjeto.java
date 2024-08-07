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
package com.jblue.vista.componentes;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.modelo.objetos.Objeto;
import com.jblue.util.Filtros;
import com.jblue.util.fabricas.FabricaCache;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 *
 * @author jp
 * @param <T>
 */
public class CSelectorObjeto<T extends Objeto> extends javax.swing.JDialog {

    public static OCalles selectorCalle(JFrame padre) {
        CSelectorObjeto o = new CSelectorObjeto(padre, true, FabricaCache.MC_CALLES.getLista());
        o.setVisible(true);
        if (o.getReturnStatus() == CSelectorObjeto.RET_CANCEL) {
            return null;
        }
        return (OCalles) o.getObjeto();
    }

    public static OUsuarios selectorUsuarios(JFrame padre) {
        CSelectorObjeto o = new CSelectorObjeto(padre, true, FabricaCache.MC_USUARIOS.getLista());
        o.setVisible(true);
        if (o.getReturnStatus() == CSelectorObjeto.RET_CANCEL) {
            return null;
        }
        return (OUsuarios) o.getObjeto();
    }

    public static OUsuarios selectorSoloTitulares(JFrame padre) {
        ArrayList<OUsuarios> list = FabricaCache.MC_USUARIOS.subLista(o -> o.isTitular());
        CSelectorObjeto o = new CSelectorObjeto(padre, true, list);
        o.setVisible(true);
        if (o.getReturnStatus() == CSelectorObjeto.RET_CANCEL) {
            return null;
        }
        return (OUsuarios) o.getObjeto();
    }

    public static OUsuarios selectorSoloUsuarios(JFrame padre) {
        ArrayList<OUsuarios> list = FabricaCache.MC_USUARIOS.subLista(o -> !o.isTitular());
        CSelectorObjeto o = new CSelectorObjeto(padre, true, list);
        o.setVisible(true);
        if (o.getReturnStatus() == CSelectorObjeto.RET_CANCEL) {
            return null;
        }
        return (OUsuarios) o.getObjeto();
    }

    public static OTipoTomas selectorTipoDeToma(JFrame padre) {
        CSelectorObjeto o = new CSelectorObjeto(padre, true, FabricaCache.MC_TIPOS_DE_TOMAS.getLista());
        o.setVisible(true);
        if (o.getReturnStatus() == CSelectorObjeto.RET_CANCEL) {
            return null;
        }
        return (OTipoTomas) o.getObjeto();
    }

    private final DefaultListModel<String> modelo_lista;
    private final ArrayList<T> cache;
    private final ArrayList<T> cache_aux;
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form NewOkCancelDialog
     *
     * @param parent
     * @param modal
     * @param lista
     */
    public CSelectorObjeto(JFrame parent, boolean modal, ArrayList<T> lista) {
        super(parent, modal);
        initComponents();
        modelo_lista = new DefaultListModel<>();
        cache = lista;
        cache_aux = new ArrayList<>(lista.size());
        lista_usuarios.setModel(modelo_lista);
        cargar();
        //
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);

        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    public T getObjeto() {
        ArrayList<T> lista;
        System.out.println(buscado);
        if (buscado) {
            lista = cache_aux;
        } else {
            lista = cache;
        }
        return lista.get(lista_usuarios.getSelectedIndex());
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_root = new javax.swing.JPanel();
        panel_central = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_usuarios = new javax.swing.JList<>();
        botones = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        panel_root.setPreferredSize(new java.awt.Dimension(600, 300));
        panel_root.setLayout(new java.awt.BorderLayout());

        panel_central.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(600, 40));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Buscar");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 40));
        jPanel1.add(jLabel2, java.awt.BorderLayout.LINE_START);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, java.awt.BorderLayout.CENTER);

        panel_central.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setMinimumSize(new java.awt.Dimension(600, 230));
        jPanel2.setLayout(new java.awt.BorderLayout());

        lista_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lista_usuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lista_usuarios);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panel_central.add(jPanel2, java.awt.BorderLayout.CENTER);

        panel_root.add(panel_central, java.awt.BorderLayout.CENTER);

        botones.setLayout(new java.awt.BorderLayout());
        botones.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        okButton.setText("Seleccionar");
        okButton.setPreferredSize(new java.awt.Dimension(200, 30));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel3.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel3.add(cancelButton);

        botones.add(jPanel3, java.awt.BorderLayout.EAST);

        panel_root.add(botones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panel_root, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        doClose(RET_OK);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void lista_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lista_usuariosMouseClicked
        if (evt.getClickCount() == 2) {
            doClose(RET_OK);
        }
    }//GEN-LAST:event_lista_usuariosMouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (Filtros.isNullOrBlank(jTextField1.getText())) {
            buscado = false;
            cargar();
            return;
        }
        buscado = true;

        _buscador(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    private void _buscador(String txt) {
        modelo_lista.clear();
        cache_aux.clear();

        txt = Filtros.limpiar(txt);

        String aux;
        for (T i : cache) {
            aux = Filtros.limpiar(i.getStringR());
            if (!aux.contains(txt)) {
                continue;
            }
            StringBuilder sb = new StringBuilder(100);
            sb.append(i.getId()).append(" - ").append(i.getStringR());

            modelo_lista.addElement(sb.toString());
            cache_aux.add(i);
        }
    }

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    private void cargar() {
        modelo_lista.clear();
        for (Objeto i : cache) {
            StringBuilder sb = new StringBuilder(100);
            sb.append(i.getId()).append(" - ").append(i.getStringR());
            modelo_lista.addElement(sb.toString());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botones;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> lista_usuarios;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel panel_central;
    private javax.swing.JPanel panel_root;
    // End of variables declaration//GEN-END:variables
    private boolean buscado = false;
    private int returnStatus = RET_CANCEL;
}
