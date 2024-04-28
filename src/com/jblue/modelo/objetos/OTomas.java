/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OTomas extends Objeto {

    private String tipo, precio;

    public OTomas(String[] info) {
        super(info);
        this.tipo = info[1];
        this.precio = info[2];
    }

    public OTomas() {
    }

    public String getTipo() {
        return tipo;
    }

    public String getPrecio() {
        return precio;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info);
        this.tipo = info[1];
        this.precio = info[2];
    }

    @Override
    public String[] getInfo() {
        return super.getInfo();
    }

}
