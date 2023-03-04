/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.envoltorios.env.EnvUsuario;

/**
 *
 * @author jp
 */
public class OUsuarios extends Objeto {

    public OUsuarios(String[] info) {
        super(info);
        _infoSinFK = InfoSinFK();
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
        return _info[1];
    }

    /**
     *
     * @return una cadena con el apellido paterno del usuario
     */
    public String getAp() {
        return _info[2];
    }

    /**
     *
     * @return una cadena con el apellido materno del usuario
     */
    public String getAm() {
        return _info[3];
    }

    /**
     * Metodo que concatena el nombre y los apellidos
     * <br> nombre + ap + am
     *
     * @return una cadena con el nombre completo del usuario
     */
    public String getNombreStr() {
        return _info[1] + " " + _info[2] + " " + _info[3];
    }

    /**
     *
     * @return una cadena con el ID de la calle asociada a este usuario
     */
    public String getCalle() {
        return _info[4];
    }

    /**
     *
     * @return una cadena con el ID del tipo de toma asociada a este usuario
     */
    public String getToma() {
        return _info[5];
    }

    /**
     *
     * @return una cadena con la fecha de registro de este usuario
     */
    public String getRegistro() {
        return _info[6];
    }

    /**
     *
     * @return un entero: 1 si el usuario esta activo -1 si el usuario esta
     * inactivo
     */
    public int getEstado() {
        return Integer.parseInt(_info[7]);
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
        int i = Integer.parseInt(_info[8]);
        return i;
    }

    public boolean isTitular() {
        return Integer.parseInt(_info[8]) == -1;
    }

    private String[] InfoSinFK() {
        return new String[]{
            _info[0],
            _info[1],
            _info[2],
            _info[3],
            EnvUsuario.getCalle(_info[4]).getCalleStr(),
            EnvUsuario.getTipo_Toma(_info[5]).getTipo(),
            _info[6],
            isActivo() ? "ACTIVO" : "INACTIVO",
            isTitular() ? "TITULAR" : "CONSUMIDOR"
        };
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        _infoSinFK = InfoSinFK();
    }
    
    

    public String[] getInfoSinFK() {
        return _infoSinFK;
    }

}
