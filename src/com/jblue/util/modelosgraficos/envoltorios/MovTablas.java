/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.modelosgraficos.envoltorios;

import com.jbd.conexion.Conexion;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.FormatoBD;
import com.jblue.util.modelosgraficos.interfaces.InterfaceTablaMov;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private int ultimo_id_leido, primer_id_leido;
    private int ant, sig, paso;
    private String where;

    public MovTablas(JTable tabla, Operaciones<T> operaciones) {
        this.TABLA = tabla;
        this.MODELO = (DefaultTableModel) tabla.getModel();
        this.OPERACIONES = operaciones;
        try {
            Conexion cn = operaciones.getCONEXION();
            ResultSet qr = cn.queryResult("SELECT * FROM " + operaciones.getTABLA() + " ORDER BY id desc LIMIT 1");
            if (qr.next()) {
                ultimo_id_leido = Integer.parseInt(qr.getString("id"));
            }
            cn.closeRS();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.ant = 1;
        this.sig = 100;
        this.paso = 100;
    }

    @Override
    public void add(String[] o) {
        ultimo_id_leido++;
        o[0] = "" + ultimo_id_leido;
        o = FormatoBD.bdEntrada(o);
        MODELO.addRow(o);
    }

    @Override
    public void remove(int index) {
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

    public void ant() {
        if (ant > (ant + paso)) {
            ant -= paso;
        }
    }

    public void sig() {
        ant += paso;
        sig += paso;
    }

    public void setPaso(int paso) {
        this.paso = paso;
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

    @Override
    public void llenar() {
        ArrayList<T> lista;
        if (where != null) {
            lista = OPERACIONES.getLista("id >= " + ant + " && id <= " + sig + " " + where);
        } else {
            lista = OPERACIONES.getLista("id >= " + ant + " && id <= " + sig);
        }
        //
        if (lista != null) {
            for (T t : lista) {
                MODELO.addRow(FormatoBD.bdSalida(t.getInfo()));
            }
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
}
