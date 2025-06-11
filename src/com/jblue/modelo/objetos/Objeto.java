/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.objetos.AbstractObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jblue.util.objetos.ObjectModel;

/**
 *
 * @author jp
 */
public class Objeto extends AbstractObject implements ObjectModel {

    public Objeto() {
        super(null);
    }

    /**
     * Contruye una objeto basado en el array de elementos que pasa por
     * parametro
     *
     * @param info
     */
    public Objeto(String[] info) {
        super(info);
    }

    @Override
    public int compareTo(Objeto t) {
        int x = Integer.parseInt(info[0]);
        int y = Integer.parseInt(t.info[0]);
        return Integer.compare(x, y);
    }

    @Override
    public String getId() {
        return info[0];
    }

    @Override
    public boolean isEmpty() {
        return info == null || info.length == 0;
    }

    /**
     *
     * @return @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Objeto clone() throws CloneNotSupportedException {
        Objeto o = null;
        try {
            o = (Objeto) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Objeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

}
