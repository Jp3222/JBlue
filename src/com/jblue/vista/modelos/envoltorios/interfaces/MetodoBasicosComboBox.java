/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.modelos.envoltorios.interfaces;

import javax.swing.JComboBox;

/**
 *
 * @author jp
 */
public interface MetodoBasicosComboBox {

    void setJComboBox(JComboBox<String> comboBox);

    void add(String data);

    void remove(int i);

    String get(int index);

    void set(int index, String data);

    void mostrarDatos();

    void ocultarDatos();

    void recargarDatos();

}
