/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.objetos.sucls.Objeto;
import com.jblue.modelo.envoltorios.env.EnvUsuario;

/**
 *
 * @author jp
 */
public class OUsuarios extends Objeto {

    public OUsuarios(String[] info) {
        super(info);
        _conjuntoSinFK = InfoSinFK();
    }

    public OUsuarios() {
        super();
    }

    /**
     * metodo que retorna el nombre del usuario
     *
     * @return una cadena con el nombre del usuario
     */
    public String getNombre() {
        return _conjunto[1];
    }

    /**
     *
     * @return una cadena con el apellido paterno del usuario
     */
    public String getAp() {
        return _conjunto[2];
    }

    /**
     *
     * @return una cadena con el apellido materno del usuario
     */
    public String getAm() {
        return _conjunto[3];
    }

    /**
     * Metodo que concatena el nombre y los apellidos
     * <br> nombre + ap + am
     *
     * @return una cadena con el nombre completo del usuario
     */
    @Override
    public String getStringR() {
        return getSubCon(1, 2, 3).replace(",", " ");
    }

    /**
     *
     * @return una cadena con el ID de la calle asociada a este usuario
     */
    public String getCalle() {
        return _conjunto[4];
    }

    /**
     *
     * @return una cadena con el ID del tipo de toma asociada a este usuario
     */
    public String getToma() {
        return _conjunto[5];
    }

    /**
     *
     * @return una cadena con la fecha de registro de este usuario
     */
    public String getRegistro() {
        return _conjunto[6];
    }

    /**
     *
     * @return un entero: 1 si el usuario esta activo -1 si el usuario esta
     * inactivo
     */
    public int getEstado() {
        return Integer.parseInt(_conjunto[7]);
    }

    public boolean isActivo() {
        return getEstado() > 0;
    }

    /**
     *
     * @return -1 si el usuario es titular en cualquier otro caso devuelve el id
     * del usuario al cual esta asociado
     */
    public int getTitutlar() {
        int i = Integer.parseInt(_conjunto[8]);
        return i;
    }

    public boolean isTitular() {
        return Integer.parseInt(_conjunto[8]) == -1;
    }

    private String[] InfoSinFK() {
        return new String[]{
            _conjunto[0],
            _conjunto[1],
            _conjunto[2],
            _conjunto[3],
            EnvUsuario.getCalle(_conjunto[4]).getStringR(),
            EnvUsuario.getTipo_De_Toma(_conjunto[5]).getTipo(),
            _conjunto[6],
            isActivo() ? "ACTIVO" : "INACTIVO",
            isTitular() ? "N/A" : EnvUsuario.getUsuario(_conjunto[8]).getStringR()
        };
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        _conjuntoSinFK = InfoSinFK();
    }

    public String[] getInfoSinFK() {
        return _conjuntoSinFK;
    }

}
