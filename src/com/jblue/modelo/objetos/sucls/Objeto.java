/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos.sucls;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author jp
 */
public abstract class Objeto implements Serializable, Cloneable, Comparable<Objeto> {

    protected String[] _conjunto;
    protected String[] _conjuntoSinFK;

    protected Objeto(String[] info) {
        this._conjunto = info;
        this._conjuntoSinFK = info.clone();
    }

    public Objeto() {
        this._conjunto = null;
    }

    public String getId() {
        return _conjunto[0];
    }

    public String[] getInfo() {
        return _conjunto;
    }

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

    public String getSubCon(int... indices) {
        StringBuilder o = new StringBuilder();
        int i = 0;
        for (; i < indices.length - 1; i++) {
            int indice = indices[i];
            o.append(_conjunto[indice]).append(",");
        }
        o.append(_conjunto[i + 1]);
        return o.toString();
    }

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
        _conjuntoSinFK[index] = newValue;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(_conjunto);
    }

}
