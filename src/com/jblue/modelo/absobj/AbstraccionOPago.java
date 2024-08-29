/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.absobj;

import java.time.LocalDate;

/**
 *
 * @author jp
 */
public class AbstraccionOPago extends Objeto {

    public AbstraccionOPago() {
        super();
    }

    public AbstraccionOPago(String... info) {
        super(info);
    }

    public String getPersonal() {
        return info[1];
    }

    public String getUsuario() {
        return info[2];
    }

    public String getMesPagado() {
        return info[3];
    }

    public double getMonto() {
        return Double.parseDouble(info[4]);
    }

    public int getDia() {
        return Integer.parseInt(info[5]);
    }

    public int getMes() {
        return Integer.parseInt(info[6]);
    }

    public int getAño() {
        return Integer.parseInt(info[7]);
    }

    public LocalDate getFecha() {
        return LocalDate.of(getAño(), getMes(), getDia());
    }

}
