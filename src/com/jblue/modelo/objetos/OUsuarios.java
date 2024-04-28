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
        return getSubCon(1, 2, 3).replace(',', ' ');
    }

    /**
     *
     * @return una cadena con el ID de la calle asociada a este usuario
     */
    public String getCalle() {
        return _conjunto[4];
    }

    public String getNumeroCasa() {
        return _conjunto[5];
    }

    /**
     *
     * @return una cadena con el ID del tipo de toma asociada a este usuario
     */
    public String getToma() {
        return _conjunto[6];
    }

    /**
     *
     * @return una cadena con la fecha de registro de este usuario
     */
    public String getRegistro() {
        return _conjunto[7];
    }

    /**
     *
     * @return un entero: 1 si el usuario esta activo -1 si el usuario esta
     * inactivo
     */
    public int getEstado() {
        return Integer.parseInt(_conjunto[8]);
    }

    public boolean isActivo() {
        return getEstado() > 0;
    }

    /**
     *
     * @return -1 si el usuario es titular en cualquier otro caso devuelve el id
     * del usuario al cual esta asociado
     */
    public String getTitutlar() {
        return _conjunto[9];
    }

    public boolean isTitular() {
        return Integer.parseInt(_conjunto[9]) == -1;
    }

    public String getCodigo() {
        return _conjunto[10];
    }

    private String[] InfoSinFK() {
        _conjuntoSinFK[4] = EnvUsuario.getCalleEnCache(getCalle()).getStringR();
        _conjuntoSinFK[6] = EnvUsuario.getTipoDeTomaEnCache(getToma()).getStringR();
        _conjuntoSinFK[8] = isActivo() ? "ACTIVO" : "INACTIVO";
        OUsuarios usuario = EnvUsuario.getUsuarioXID(getTitutlar());
        _conjuntoSinFK[9] = isTitular() ? "N/A" : usuario.getStringR();
        return _conjuntoSinFK;
    }

    /**
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 ap
     * <br> 4 am
     * <br> 5 calle
     * <br> 6 toma
     * <br> 7 registro
     * <br> 8 estado
     * <br> 9 titular
     *
     * @param info array que contiene la informacion en el orden mostrado arriba
     */
    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        _conjuntoSinFK = InfoSinFK();
    }

    /**
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 ap
     * <br> 4 am
     * <br> 5 calle
     * <br> 6 toma
     * <br> 7 registro
     * <br> 8 estado
     * <br> 9 titular
     *
     * @return un arreglo con la informacion en el orden mostrado
     */
    public String[] getInfoSinFK() {
        return _conjuntoSinFK;
    }

    /**
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 ap
     * <br> 4 am
     * <br> 5 calle
     * <br> 6 toma
     * <br> 7 registro
     * <br> 8 estado
     * <br> 9 titular
     *
     * @return un arreglo con la informacion en el orden mostrado
     */
    @Override
    public String[] getInfo() {
        return super.getInfo(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
