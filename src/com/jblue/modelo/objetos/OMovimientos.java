/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.modelo.objetos.Objeto;

/**
 *
 * @author jp
 */
public class OMovimientos extends Objeto {

    public OMovimientos(String[] info) {
        super(info);
    }

    public OMovimientos() {
        super();
    }

    public String getMovimiento() {
        return _conjunto[1];
    }

    public String getDescripcion() {
        return _conjunto[2];
    }

}
