/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.objetos.AbstractObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.jutil.dbcon.cn.JDBObjectModel;

/**
 *
 * @author jp
 */
public class Objeto extends AbstractObject {

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

}
