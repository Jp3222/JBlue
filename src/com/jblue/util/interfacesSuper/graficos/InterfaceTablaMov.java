/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.interfacesSuper.graficos;

import com.jblue.util.interfacesSuper.InterfaceListaOArr;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que contiene metodos para el movimiento de
 *
 * @author jp
 */
public interface InterfaceTablaMov extends InterfaceListaOArr, InterfaceGraficosMov {

    /**
     * Metodo que actualiza una elemento de la fila
     *
     * @param x fila
     * @param y columna
     * @param o elemento nuevo
     */
    void set(int x, int y, String o);

    /**
     * Metodo que sirve para filtrar la informacion que se obtiene
     *
     * @param str texto buscado
     * @param campo campo asociado
     */
    void buscar(String str, int... campo);

    /**
     * metodo que devuelve un elemento especifico
     *
     * @param x fila
     * @param y columna
     * @return el elemento solicitado
     */
    String get(int x, int y);

    /**
     * Metodo que devuelte el jtable que contenido
     *
     * @return un JTable
     */
    JTable getTable();

    /**
     * Metodo que devuelte el modelo contenido en el JTable
     *
     * @return un DefaultTableModel
     */
    DefaultTableModel getModel();

}
