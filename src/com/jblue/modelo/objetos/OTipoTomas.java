/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.bd.Objeto;

/**
 *
 * @author jp
 */
public class OTipoTomas extends Objeto {

    public OTipoTomas(String[] info) {
        super(info);
    }

    public OTipoTomas() {
        super();
    }

    @Override
    public String getStringR() {
        return getTipo();
    }

    @Override
    public String StringRepresentacion() {
        return getTipo();
    }

    public String getTipo() {
        return _conjunto[1];
    }

    public double getCosto() {
        return Double.parseDouble(_conjunto[2]);
    }

    public double getRecargo() {
        return Double.parseDouble(_conjunto[3]);
    }

    @Override
    public String toString() {
        return StringRepresentacion();
    }
    
    

}
