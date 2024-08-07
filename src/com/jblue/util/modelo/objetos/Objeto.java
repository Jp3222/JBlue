/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.modelo.objetos;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author jp
 */
public class Objeto extends AbstraccionObjeto implements Serializable, Cloneable, Comparable<Objeto> {

    public Objeto() {
    }

    /**
     * Contruye una objeto basado en el array de elementos que pasa por
     * parametro
     *
     * @param info
     */
    public Objeto(String[] info) {
        this._conjunto = info;
        this._conjuntoSinFK = info.clone();
    }

    /**
     * construye un objeto con un array de valor 'null'
     *
     * @param <T>
     * @param o
     */
    public <T extends Objeto> Objeto(T o) {
        this._conjunto = o._conjunto;
        this._conjuntoSinFK = o._conjuntoSinFK;
    }

    /**
     * Devuelve el ID del objeto ubicado en la posicion 0 del array
     *
     * @return ID del objeto
     */
    public String getId() {
        return _conjunto[0];
    }

    /**
     * Devuelve el array principal del objeto.
     *
     * @return el array princiapl
     */
    public String[] getInfo() {
        return _conjunto;
    }

    public String[] getInfoSinFK() {
        return _conjuntoSinFK;
    }

    /**
     * asigna un array al contenedor principal
     *
     * @param info - array con la informacion principal(con FK)
     */
    public void setInfo(String[] info) {
        this._conjunto = info;
        this._conjuntoSinFK = info.clone();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.deepHashCode(this._conjunto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Objeto other = (Objeto) obj;
        String[] x = Arrays.copyOfRange(_conjunto, 1, _conjunto.length);
        String[] y = Arrays.copyOfRange(other._conjunto, 1, _conjunto.length);
        return Arrays.deepEquals(x, y);
    }

    /**
     * contruye un String el cual contiene un subconjunto de elementos del array
     * principal separado por comas o ',', segun los indicies o posiciones
     * indicadas por parametro
     *
     * @param indices - valores del subconjuto
     * @return subconjunto representado en String
     */
    public String getSubCon(int... indices) {
        StringBuilder o = new StringBuilder();
        int i = 0;
        while (i < indices.length - 1) {
            int indice = indices[i];
            o.append(_conjunto[indice]).append(",");
            i++;
        }
        i++;
        o.append(_conjunto[i]);
        return o.toString();
    }

    public boolean isEmpty() {
        return _conjunto == null;
    }

    /**
     * metodo que define la representacion del objeto en una cadeja, esto
     * orientado a la representacion garfica
     *
     * @return la representacion del objeto en un String
     */
    public String getStringR() {
        return _conjunto[0];
    }

    @Override
    public int compareTo(Objeto t) {
        int x = Integer.parseInt(_conjunto[0]);
        int y = Integer.parseInt(t._conjunto[0]);
        int comp = Integer.compare(x, y);
        return comp;
    }

    public void setValue(int index, String newValue) {
        _conjunto[index] = newValue;
        _conjuntoSinFK[index] = String.copyValueOf(newValue.toCharArray());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(_conjunto);
    }

    @Override
    public String StringRepresentacion() {
        return _conjunto[0];
    }

}
