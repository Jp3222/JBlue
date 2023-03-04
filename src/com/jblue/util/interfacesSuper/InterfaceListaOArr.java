/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.interfacesSuper;

/**
 *
 * @author jp
 */
public interface InterfaceListaOArr {

    /**
     * Metodo que añade un array de informacion a la tabla y a un arraylist
     * interno
     *
     * @param o array con informacion correspondiente al modelo
     */
    void add(String[] o);

    /**
     * Metodo que elimina un elemento del modelo grafico y el array list interno
     *
     * @param index fila seleccionada
     */
    void remove(int index);

    /**
     * Metodo que actualiza una fila de informacion
     *
     * @param index fila que se va actualizar
     * @param valores informacion por la cual se remplaza
     */
    void set(int index, String[] valores);

   
    /**
     * metodo que devuelve la inforacion de una fila
     *
     * @param index - fila
     * @return un array con la informacion de la fila indicada
     */
    String[] get(int index);
}
