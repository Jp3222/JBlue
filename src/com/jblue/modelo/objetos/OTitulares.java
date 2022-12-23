/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OTitulares extends Objeto {

    public OTitulares(String[] info) {
        super(info);
    }

    public OTitulares() {
        super();
    }
    
    public String getNombre() {
        return info[1];
    }

    public String getAPaterno() {
        return info[2];
    }

    public String getAMaterno() {
        return info[3];
    }

    public String getCalle() {
        return info[4];
    }

    public String getTipoToma() {
        return info[5];
    }

    public String getRegistro() {
        return info[6];
    }

    public String getEstado() {
        return info[7];
    }

}
