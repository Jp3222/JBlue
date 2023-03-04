/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.modelosgraficos;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.FormatoBD;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.interfacesSuper.graficos.InterfaceTablaMov;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 * @param <T>
 */
public class MovTablas<T extends Objeto> implements InterfaceTablaMov {

    private final JTable TABLA;
    private final DefaultTableModel MODELO;
    private final Operaciones<T> OPERACIONES;
    private String where;
    private final MemoCache<T> CACHE;

    public MovTablas(JTable tabla, MemoCache<T> cache) {
        this.TABLA = tabla;
        this.MODELO = (DefaultTableModel) tabla.getModel();
        this.OPERACIONES = cache.getOperaciones();
        this.CACHE = cache;
    }

    @Override
    public void add(String[] o) {
        CACHE.setUltimoId(CACHE.getUltimoId());
        o[0] = "" + CACHE.getUltimoId();
        o = FormatoBD.bdEntrada(o);
        MODELO.addRow(o);
    }

    @Override
    public void remove(int index) {
        if (index == 0) {
            T get = CACHE.getLista().get(1);
            CACHE.setPrimerId(Integer.parseInt(get.getId()));
        } else if (index == MODELO.getRowCount() - 1) {
            T get = CACHE.getLista().get(CACHE.getLista().size() - 2);
            CACHE.setPrimerId(Integer.parseInt(get.getId()));
        }
        MODELO.removeRow(index);
    }

    @Override
    public void set(int index, String[] col) {
        col = FormatoBD.bdEntrada(col);
        for (int i = 0; i < col.length; i++) {
            MODELO.setValueAt(col[i], index, i);
        }
    }

    @Override
    public void set(int x, int y, String o) {
        MODELO.setValueAt(TABLA, x, y);
    }

    @Override
    public String[] get(int index) {
        String[] info = new String[MODELO.getColumnCount()];
        for (int i = 0; i < info.length; i++) {
            info[i] = (String) MODELO.getValueAt(index, i);
        }
        return info;
    }

    @Override
    public String get(int x, int y) {
        return (String) MODELO.getValueAt(x, y);
    }

    @Override
    public JTable getTable() {
        return TABLA;
    }

    @Override
    public DefaultTableModel getModel() {
        return MODELO;
    }

    /**
     * Asigna una sentencia sql para vaciar la tabla
     *
     * @param where
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * elimina la sentencia sql data
     */
    public void deleteWhere() {
        this.where = null;
    }

    public String[] getSeleccionado() {
        int selectedRow = TABLA.getSelectedRow();
        String info[] = new String[MODELO.getColumnCount()];
        for (int i = 0; i < info.length; i++) {
            info[i] = (String) MODELO.getValueAt(selectedRow, i);
        }
        return info;
    }

    /**
     *
     */
    @Override
    public void llenar() {
        if (where != null) {
            CACHE.setQuery(where);
        }
        ArrayList<T> lista = CACHE.getLista();
        for (T t : lista) {
            MODELO.addRow(FormatoBD.bdSalida(t.getInfo()));
        }
    }

    @Override
    public void vaciar() {
        while (MODELO.getRowCount() > 0) {
            MODELO.removeRow(0);
        }
    }

    @Override
    public void actualizar() {
        vaciar();
        llenar();
    }

    @Override
    public void buscar(String str, int... campos) {
        String bus = str.trim().replace(" ", "").toUpperCase();
        ArrayList<T> memoria = CACHE.getLista();
        vaciar();
        for (T t : memoria) {
            String info = t.getStringElements(campos).replace(" ", "");
            if (info.contains(bus)) {
                MODELO.addRow(t.getInfo());
            }
        }
    }
}
