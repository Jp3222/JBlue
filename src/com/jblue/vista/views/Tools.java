/*
 * Copyright (C) 2024 juan-campos
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
package com.jblue.vista.views;

import com.jblue.vista.tools.CSVExport;
import com.jblue.vista.tools.CSVImport;
import com.jblue.vista.tools.UserDirectory;
import com.jblue.vista.tools.UserDocuments;
import com.jutil.framework.ViewStates;
import javax.swing.JPanel;

/**
 *
 * @author juan-campos
 */
public class Tools extends JPanel implements ViewStates {

    private final CSVExport view1;
    private final CSVImport view2;
    private final UserDirectory view3;
    private final UserDocuments view4;

    /**
     * Creates new form Tools
     */
    public Tools() {
        initComponents();
        view1 = new CSVExport();
        view2 = new CSVImport();
        view3 = new UserDirectory();
        view4 = new UserDocuments();
        build();

    }

    @Override
    public final void build() {
        components();
        events();
        initialState();
        finalState();
    }

    @Override
    public void events() {
    }

    @Override
    public void components() {
        jTabbedPane1.add(view1, view1.getName());
        jTabbedPane1.add(view2, view2.getName());
        jTabbedPane1.add(view3, view3.getName());
        jTabbedPane1.add(view4, view4.getName());
    }

    @Override
    public void initialState() {
    }

    @Override
    public void finalState() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setName("tools"); // NOI18N
        setPreferredSize(new java.awt.Dimension(900, 700));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setName("Herramientas"); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(900, 700));
        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
