/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.model.dtos;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.jutil.dbcon.cn.JDBObjectModel;

/**
 *
 * @author jp
 */
public class Objects extends AbstractObject {

    public Objects() {
        super(null);
    }

    /**
     * Contruye una objeto basado en el array de elementos que pasa por
     * parametro
     *
     * @param info
     */
    public Objects(String[] info) {
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
    public Objects clone() throws CloneNotSupportedException {
        Objects o = null;
        try {
            o = (Objects) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Objects.class.getName()).log(Level.SEVERE, null, ex);
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
