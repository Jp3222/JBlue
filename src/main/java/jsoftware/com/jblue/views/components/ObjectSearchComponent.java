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
package jsoftware.com.jblue.views.components;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import jsoftware.com.jblue.controllers.compc.ListController;
import jsoftware.com.jblue.model.dtos.ForeingKeyObject;
import jsoftware.com.jblue.model.dtos.OStreet;
import jsoftware.com.jblue.model.dtos.OUser;
import jsoftware.com.jblue.model.dtos.OWaterIntakeTypes;
import jsoftware.com.jblue.model.dtos.Objects;
import jsoftware.com.jblue.model.dtos.StatusObject;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jblue.views.framework.ListSearchViewModel;
import jsoftware.com.jutil.view.WindowStates;

public final class ObjectSearchComponent<T extends Objects & StatusObject & ForeingKeyObject> extends JDialog implements WindowStates, ListSearchViewModel<T> {

    private static final long serialVersionUID = 1L;

    public static OStreet getStreet(JFrame padre) {
        final ObjectSearchComponent<OStreet> o = new ObjectSearchComponent<>(padre, true, CacheFactory.STREETS);
        o.setVisible(true);
        if (o.getReturnStatus() == ObjectSearchComponent.RET_CANCEL) {
            return null;
        }
        return o.getObjectSearch();
    }

    public static OUser getUser(JFrame padre) {
        ObjectSearchComponent<OUser> o = new ObjectSearchComponent<>(padre, true, CacheFactory.USERS);
        o.setVisible(true);
        if (o.getReturnStatus() == ObjectSearchComponent.RET_CANCEL) {
            return null;
        }
        return o.getObjectSearch();
    }

    public static OWaterIntakeTypes getWaterIntakeType(JFrame padre) {
        ObjectSearchComponent<OWaterIntakeTypes> o = new ObjectSearchComponent<>(padre, true, CacheFactory.WATER_INTAKES_TYPES);
        o.setVisible(true);
        if (o.getReturnStatus() == ObjectSearchComponent.RET_CANCEL) {
            return null;
        }
        return o.getObjectSearch();
    }

    private T object_search;
    //
    private final DefaultListModel<T> list_model;
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    private final ListController<T> list_controller;

    /**
     * Creates new form NewOkCancelDialog
     *
     * @param parent
     * @param modal
     * @param memo_list
     */
    public ObjectSearchComponent(JFrame parent, boolean modal, MemoListCache<T> memo_list) {
        super(parent, modal);
        initComponents();
        list_model = new DefaultListModel<>();
        objects_list.setModel(list_model);
        this.list_controller = new ListController<>(this, memo_list);
        build();
    }

    @Override
    public void build() {
        components();
        events();
        initComponents();
        finalState();
    }

    @Override
    public void events() {
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

        objects_list.addMouseListener(list_controller);
        //ok_button.addActionListener(controller);
    }

    @Override
    public void components() {
    }

    @Override
    public void initialState() {
    }

    @Override
    public void finalState() {
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
        search_field = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        objects_list = new javax.swing.JList<>();
        botones = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ok_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();

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
        jPanel1.add(search_field, java.awt.BorderLayout.CENTER);

        panel_central.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setMinimumSize(new java.awt.Dimension(600, 230));
        jPanel2.setLayout(new java.awt.BorderLayout());

        objects_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(objects_list);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panel_central.add(jPanel2, java.awt.BorderLayout.CENTER);

        panel_root.add(panel_central, java.awt.BorderLayout.CENTER);

        botones.setLayout(new java.awt.BorderLayout());
        botones.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(200, 30));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 5, 5));

        ok_button.setText("Seleccionar");
        ok_button.setPreferredSize(new java.awt.Dimension(200, 30));
        ok_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ok_buttonActionPerformed(evt);
            }
        });
        jPanel3.add(ok_button);
        getRootPane().setDefaultButton(ok_button);

        cancel_button.setText("Cancelar");
        cancel_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_buttonActionPerformed(evt);
            }
        });
        jPanel3.add(cancel_button);

        botones.add(jPanel3, java.awt.BorderLayout.EAST);

        panel_root.add(botones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panel_root, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_buttonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancel_buttonActionPerformed

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void ok_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ok_buttonActionPerformed
        doClose(RET_OK);
    }//GEN-LAST:event_ok_buttonActionPerformed

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botones;
    private javax.swing.JButton cancel_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<T> objects_list;
    private javax.swing.JButton ok_button;
    private javax.swing.JPanel panel_central;
    private javax.swing.JPanel panel_root;
    private javax.swing.JTextField search_field;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = RET_CANCEL;

    @Override
    public JList<T> getList() {
        return objects_list;
    }

    @Override
    public JTextField getTextComponentList() {
        System.out.println("componente:");
        System.out.println(search_field.getText());
        return search_field;
    }

    @Override
    public String getTextSearchList() {
        System.out.println();
        System.out.println("texto");
        System.out.println(search_field.getText());
        return search_field.getText().trim().toUpperCase().replace(" ", "");
    }

    @Override
    public DefaultListModel<T> getListModel() {
        return list_model;
    }

    @Override
    public void setCountElements(int count) {
    }

    @Override
    public int getCountElements() {
        return 0;
    }

    @Override
    public void setScreenListInfo() {
        T object = list_model.get(objects_list.getSelectedIndex());
        setObjectSearch(object);
        doClose(RET_OK);
    }

    @Override
    public T getObjectSearch() {
        return object_search;
    }

    @Override
    public void setObjectSearch(T o) {
        object_search = o;
    }
}
