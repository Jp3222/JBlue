/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

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

    public String getTipo() {
        return info[1];
    }

    public double getCosto() {
        return Double.parseDouble(info[2]);
    }

    public double getRecargo() {
        return Double.parseDouble(info[3]);
    }

    @Override
    public String toString() {
        return getTipo();
    }

}
