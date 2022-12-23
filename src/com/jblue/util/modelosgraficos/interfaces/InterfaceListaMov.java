/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.modelosgraficos.interfaces;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author jp
 */
public interface InterfaceListaMov extends InterfaceGraficosMov {

    void add(String o);
    
    void remove(int index);
    
    void get(int index);
    
    void set(int index, String o);
    
    DefaultListModel<String> getModelo();
    
    JList<String> getLista();
}
