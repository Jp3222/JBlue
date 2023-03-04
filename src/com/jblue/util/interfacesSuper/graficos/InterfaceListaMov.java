/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.interfacesSuper.graficos;

import com.jblue.util.interfacesSuper.InterfaceListaOObj;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author jp
 */
public interface InterfaceListaMov extends  InterfaceListaOObj, InterfaceGraficosMov {

    DefaultListModel<String> getModelo();

    JList<String> getLista();
}
