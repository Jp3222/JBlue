/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.objetos.sucls.OPagos;

/**
 *
 * @author jp
 */
public class OPagosServicio extends OPagos {

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
    protected String[] InfoSinFK() {
        super.InfoSinFK(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        _conjuntoSinFK[3] = getMesPagado();
        _conjuntoSinFK[4] = getMonto();
        _conjuntoSinFK[5] = getDia();
        _conjuntoSinFK[6] = getMes();
        _conjuntoSinFK[7] = getAño();
        return _conjuntoSinFK;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info);
        _conjuntoSinFK = InfoSinFK();
    }

    public String[] getInfoSinFK() {

        return _conjuntoSinFK;
    }

}
