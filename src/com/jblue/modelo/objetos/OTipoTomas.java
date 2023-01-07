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
    }

    public String getTipo() {
        return info[1];
    }

    public int getCosto() {
        return Integer.parseInt(info[2]);
    }
    
    public int getRecargo(){
        return Integer.parseInt(info[3]);
    }

}
