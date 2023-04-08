/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.objetos.sucls.Objeto;

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

    public String getTipo() {
        return _conjunto[1];
    }

    public double getCosto() {
        return Double.parseDouble(_conjunto[2]);
    }

    public int getRecargo() {
        return Integer.parseInt(_conjunto[3]);
    }

}
