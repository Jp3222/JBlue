/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.objetos.AbstraccionObjeto;
import com.jblue.util.objetos.ModeloObjeto;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public class Objeto extends AbstraccionObjeto implements ModeloObjeto {

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
        return info == null;
    }

    /**
     *
     * @return
     */
    @Override
    public Objeto clone() {
        Objeto o = null;
        try {
            o = (Objeto) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Objeto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

}
