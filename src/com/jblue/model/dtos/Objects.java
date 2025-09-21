/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.model.dtos;

import com.jutil.dbcon.cn.JDBObjectMapModel;
import com.jutil.dbcon.cn.JDBObjectModel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Representa un objeto de transferencia de datos (DTO) genérico y simple.
 * <p>
 * Esta clase es una implementación concreta de {@link AbstractObject} que sirve
 * como un "recipiente" o contenedor básico para datos de una base de datos. La
 * información se almacena en un array de cadenas, y se asume que el primer
 * elemento del array (índice 0) es el identificador o clave primaria.
 *
 * @author jp
 * @see AbstractObject
 * @see JDBObjectModel
 * @see JDBObjectMapModel
 * @since 1.0
 */
public class Objects extends AbstractObject {

    /**
     * Construye un nuevo objeto vacío.
     * <p>
     * Inicializa el objeto con un array de información nulo.
     */
    public Objects() {
        super(null);
    }

    /**
     * Construye un objeto a partir de un array de datos.
     *
     * @param info Un array de {@link String} que contiene los valores de los
     * campos del registro.
     */
    public Objects(String[] info) {
        super(info);
    }

    // -------------------------------------------------------------------------
    // Implementaciones de metodos abstractos
    // -------------------------------------------------------------------------
    /**
     * Retorna el identificador (ID) del objeto.
     * <p>
     * Se asume que el ID es el primer elemento del array de información (índice
     * 0).
     *
     * @return El valor de la clave primaria.
     */
    @Override
    public String getId() {
        return info[0];
    }

    /**
     * Compara este objeto con otro basado en su identificador numérico.
     * <p>
     * Los identificadores son convertidos a enteros para realizar la
     * comparación.
     *
     * @param o El objeto {@link JDBObjectModel} con el que se va a comparar.
     * @return Un valor negativo si este objeto es menor, cero si son iguales, o
     * un valor positivo si este objeto es mayor.
     */
    @Override
    public int compareTo(JDBObjectModel o) {
        int x = Integer.parseInt(getId());
        int y = Integer.parseInt(o.getId());
        if (x > y) {
            return 1;
        } else if (x < y) {
            return -1;
        }
        return 0;
    }

    // -------------------------------------------------------------------------
    // Implementacion de la interfaz Cloneable
    // -------------------------------------------------------------------------
    /**
     * Crea y devuelve una copia superficial de este objeto.
     * <p>
     * La implementación se basa en el método {@code super.clone()} de la clase
     * padre {@link AbstractObject}.
     *
     * @return Una copia del objeto.
     * @throws CloneNotSupportedException Si el objeto no puede ser clonado.
     */
    @Override
    public Objects clone() throws CloneNotSupportedException {
        Objects o = null;
        try {
            o = (Objects) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Objects.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

}
