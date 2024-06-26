/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos.sucls;

import com.jblue.modelo.envoltorios.env.EnvPersonal;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OUsuarios;

/**
 *
 * @author jp
 */
public class OPagos extends Objeto {

    public OPagos() {
        super();
    }

    public OPagos(String... info) {
        super(info);
    }

    public String getPersonal() {
        return _conjunto[1];
    }

    public String getUsuario() {
        return _conjunto[2];
    }

    public String getMesPagado() {
        return _conjunto[3];
    }

    public double getMonto() {
        return Double.parseDouble(_conjunto[4]);
    }

    public String getDia() {
        return _conjunto[5];
    }

    public String getMes() {
        return _conjunto[6];
    }

    public String getAño() {
        return _conjunto[7];
    }

    public String getFecha() {
        return getSubCon(5, 6, 7).replace(',', '-');
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        OPersonal personal = EnvPersonal.getPersonal(getPersonal());
        OUsuarios usuarios = EnvUsuario.getUsuarioXID(getUsuario());
        _conjuntoSinFK[1] = personal.getStringR();
        _conjuntoSinFK[2] = usuarios.getStringR();
    }

}
