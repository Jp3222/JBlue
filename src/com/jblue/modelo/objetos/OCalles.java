/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OCalles extends Objeto {

    private String nombre, numero;

    public OCalles(String[] info) {
        super(info);
        this.nombre = info[1];
        this.numero = info[2];
    }

    public OCalles() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info);
        this.nombre = info[1];
        this.numero = info[2];
    }

}
