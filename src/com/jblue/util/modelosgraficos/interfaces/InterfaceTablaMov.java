/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.modelosgraficos.interfaces;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public interface InterfaceTablaMov extends InterfaceGraficosMov {

    void add(String[] o);

    void remove(int index);

    void set(int index, String[] col);

    void set(int x, int y, String o);

    String[] get(int index);

    String get(int x, int y);

    JTable getTable();

    DefaultTableModel getModel();
    
    
}
