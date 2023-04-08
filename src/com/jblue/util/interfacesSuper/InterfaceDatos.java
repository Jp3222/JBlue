/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.interfacesSuper;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.sucls.Objeto;
import java.util.ArrayList;

/**
 *
 *
 * @author jp
 */
public interface InterfaceDatos {

    /**
     * Metodo encargado de cargar los datos en memoria
     * <br> la estructura por exelencia puede ser un ArrayList
     */
    void cargar();

    /**
     * Metodo encargado de remover los datos en memoria
     * <br> la estructura por exelencia puede ser un ArrayList
     */
    void vaciar();

    /**
     * Metodo encargado de actualizar los datos en memoria
     * <br>este metodo remplaza los datos ya existentes(utilizando el metodo set
     * o equivalente en alguna otra estructura) en caso de que la estructura no
     * este vacia
     * <br>
     * <br> la estructura por exelencia puede ser un ArrayList
     */
    void actualizar();

    void setQuery(String query);

    void deleteQuery();

    default <T extends Objeto> ArrayList<T> leer(Operaciones<T> operaciones, String query, int idMin, int idMax) {
        ArrayList<T> lista;
        String limites = "id >= " + idMin + " and id <= " + idMax;
        if (query == null || query.isEmpty()) {
            lista = operaciones.getLista("id > 0");
        } else {
            lista = operaciones.getLista(query + " and " + limites);
        }

        return lista;
    }
}
