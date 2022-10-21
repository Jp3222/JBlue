/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.modelos.envoltorios;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public abstract class SuperModeloTablas implements MetodosBasicosTablas {

    private final DefaultTableModel MODELO;

    public SuperModeloTablas(DefaultTableModel modelo) {
        this.MODELO = modelo;
    }

    @Override
    public void add(String[] data) {
        MODELO.addRow(data);
    }

    @Override
    public void remove(int i) {
        MODELO.removeRow(i);
    }

    @Override
    public String[] get(int index) {
        String[] info = new String[MODELO.getColumnCount()];
        for (int i = 0; i < MODELO.getColumnCount(); i++) {
            info[i] = (String) MODELO.getValueAt(index, i);
        }
        return info;
    }

    @Override
    public void set(int index, String[] data) {
        for (int i = 0; i < MODELO.getColumnCount(); i++) {
            MODELO.setValueAt(data[i], index, i);
        }
    }

    public abstract void cargarDatos();

    public void vaciaDatos() {
        while (MODELO.getRowCount() > 0) {
            MODELO.removeRow(0);
        }
    }

    public void reiniciaDatos() {
        cargarDatos();
        vaciaDatos();
    }

}
