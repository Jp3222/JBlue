/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author jp
 */
public abstract class Objeto implements Serializable, Cloneable {
    
    private String id;
    private String[] info;

    public Objeto(String[] info) {
        this.id = info[0];
        this.info = info;
    }

    public Objeto() {
        this.id = null;
        this.info = null;
    }

    public String getId() {
        return id;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
        id = info[0];
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Arrays.deepHashCode(this.info);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        String[] A = Arrays.copyOfRange(this.info, 1, this.info.length);
        String[] B = Arrays.copyOfRange(other.info, 1, other.info.length);
        return Arrays.deepEquals(A, B);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(info);
    }

}
