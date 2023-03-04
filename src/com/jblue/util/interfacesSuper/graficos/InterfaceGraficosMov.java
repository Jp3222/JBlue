/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.interfacesSuper.graficos;

/**
 * Clase que contiene metodos para el llenado de modelos de componentes graficos
 * como jspiner, jtable, jcombobox
 *
 * @author jp
 */
public interface InterfaceGraficosMov {

    /**
     * Metodo que carga datos en un modelo grafico
     */
    void llenar();

    /**
     * Metodo para vacia los datos del modelo graico
     */
    void vaciar();

    /**
     * Metodo que actualiza los datos del modelo grafico llamando al metodo
     * vacia seguido del metodo llenar
     */
    void actualizar();

}
