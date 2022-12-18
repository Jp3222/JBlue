/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.modelos.envoltorios.interfaces;

import javax.swing.JTable;

/**
 *
 * @author jp
 */
public interface MetodosBasicosTablas {

    void setTabla(JTable tabla);

    void add(String[] datos);

    void remove(int index);

    String[] get(int index);

    void set(int index, String[] dato);

    void set(int fila, int columna, String dato);

    void mostrarDatos();

    void ocultarDatos();

    void recargar();
}
