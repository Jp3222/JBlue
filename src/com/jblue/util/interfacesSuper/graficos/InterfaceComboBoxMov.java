/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.interfacesSuper.graficos;

import com.jblue.util.interfacesSuper.InterfaceListaOObj;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author jp
 */
public interface InterfaceComboBoxMov extends InterfaceListaOObj, InterfaceGraficosMov {

    JComboBox<String> getJComboBox();

    DefaultComboBoxModel<String> getModel();

}
