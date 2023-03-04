/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author jp
 */
public abstract class Objeto implements Serializable, Cloneable {

    protected String[] _info;
    protected String[] _infoSinFK;
    
    protected Objeto(String[] info) {
        this._info = info;
    }

    public Objeto() {
        this._info = null;
    }

    public String getId() {
        return _info[0];
    }

    public String[] getInfo() {
        return _info;
    }

    public void setInfo(String[] info) {
        this._info = info;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.deepHashCode(this._info);
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
        String[] x = Arrays.copyOfRange(_info, 1, _info.length);
        String[] y = Arrays.copyOfRange(other._info, 1, _info.length);
        return Arrays.deepEquals(x, y);
    }

    /**
     * Metodo que devuelve de 0 a n elemento del objeto en una cadena
     *
     * @param elementos - numero de elementos a devolver, si el numero de
     * elementos indicados es -1 devolvera todos los elementos
     * @return una cadena con el numero de elementos especificados
     */
    public String getStringElements(int... elementos) {
        String o = "";
        if ((elementos[0] == -1) || (elementos.length < 0 || elementos.length >= _info.length)) {
            for (String i : _info) {
                o += i + " ";
            }
        } else {
            for (int i : elementos) {
                o += _info[i] + " ";
            }
        }
        return o;
    }

    public void setValue(int index, String newValue) {
        _info[index] = newValue;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(_info);
    }

}
