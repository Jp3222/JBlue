/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OHisMovimientos extends Objeto {

    public OHisMovimientos(String[] info) {
        super(info);
    }

    public OHisMovimientos() {
        super();
    }

    public String getPersonal() {
        return info[1];
    }

    public String getMovimiento() {
        return info[2];
    }

    public String getFecha() {
        return info[3];
    }

    public String getHora() {
        return info[4];
    }

}
