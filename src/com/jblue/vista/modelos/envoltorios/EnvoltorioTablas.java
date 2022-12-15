/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.modelos.envoltorios;

import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.SalidaDeErrores;
import com.jblue.vista.modelos.envoltorios.interfaces.MetodosBasicosTablas;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class EnvoltorioTablas<T extends Objeto> implements MetodosBasicosTablas, SalidaDeErrores {

    private final DefaultTableModel MODELO;
    private JTable TABLA;
    private ArrayList<T> LISTA;
    private Objeto o;

    public EnvoltorioTablas(JTable tabla, ArrayList<T> lista, T o) {
        this.TABLA = tabla;
        this.MODELO = (DefaultTableModel) TABLA.getModel();
        this.LISTA = lista;
        this.o = o;
    }

    @Override
    public void add(String[] data) {
        try {
            MODELO.addRow(data);
            actIndex(data);
            o.setInfo(data);
            LISTA.add((T) o.clone());

        } catch (CloneNotSupportedException ex) {
            System.out.println("error al algregar por clonacion" + ex.getMessage());
            ex.printStackTrace(pwError);
            closeBufferExeption();
        }
    }

    private void actIndex(String[] data) {
        String ultimoID = (String) MODELO.getValueAt(MODELO.getRowCount() - 2, 0);
        int id = Integer.parseInt(ultimoID);
        id = id + 1;
        MODELO.setValueAt(String.valueOf(id), MODELO.getRowCount() - 1, 0);
    }

    @Override
    public void remove(int i) {
        MODELO.removeRow(i);
        LISTA.remove(i);

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
        try {
            for (int i = 0; i < MODELO.getColumnCount(); i++) {
                MODELO.setValueAt(data[i], index, i);
            }
            o.setInfo(data);
            LISTA.set(index, (T) o.clone());
        } catch (CloneNotSupportedException ex) {
            System.out.println("error al actualizar por clonacion" + ex.getMessage());
            ex.printStackTrace(pwError);
            closeBufferExeption();
        }
    }

    @Override
    public void setTabla(JTable tabla) {
        this.TABLA = tabla;
    }

    @Override
    public void set(int fila, int columna, String dato) {
        this.TABLA.setValueAt(dato, fila, columna);
    }

    @Override
    public void mostrarDatos() {
        for (Objeto o : LISTA) {
            MODELO.addRow(o.getInfo());
        }
    }

    @Override
    public void ocultarDatos() {
        while (MODELO.getRowCount() > 0) {
            MODELO.removeRow(0);
        }

    }

    @Override
    public void recargar() {
        ocultarDatos();
        mostrarDatos();
    }

    public void setLISTA(ArrayList<T> LISTA) {
        this.LISTA.clear();
        this.LISTA = LISTA;
    }

}
