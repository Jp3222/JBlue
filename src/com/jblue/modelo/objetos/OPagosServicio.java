/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.modelo.objetos.AbstraccionOPago;

/**
 *
 * @author jp
 */
public class OPagosServicio extends AbstraccionOPago {

    public OPagosServicio() {
        super();
    }

    public OPagosServicio(String... info) {
        super(info);
    }

    @Override
    public String getStringR() {
        return _conjuntoSinFK[2];
    }
    
    @Override
    public String[] getInfoSinFK() {
        return _conjuntoSinFK;
    }

}
