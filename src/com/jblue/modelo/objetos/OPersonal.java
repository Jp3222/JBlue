/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.objetos.sucls.Objeto;

/**
 * OPersonal
 * <br>Clase que modelo objetos segun la tabla personals
 *
 * @author jp
 */
public class OPersonal extends Objeto {

    /**
     * Construye un objeto OPersonal con la informacion dada en el array
     *
     * @param info informacion contenedora de filas de la tabla OPersonal
     */
    public OPersonal(String[] info) {
        super(info);
    }

    /**
     * Constuye un objeto OPersonal vacio el cual se puede llenar con el metodo
     * serInfo
     */
    public OPersonal() {
        super();
    }

    @Override
    public String getStringR() {
        return getSubCon(1, 2).replace(",", " ");
    }

    /**
     * @return el nombre del personal
     */
    public String getNombre() {
        return _conjunto[1];
    }

    /**
     *
     * @return los apellidos del personal
     */
    public String getApellidos() {
        return _conjunto[2];
    }

    /**
     * Metodo que contiene el cargo del personal
     * <br>1 root
     * <br>2 presidente
     * <br>3 tesorero
     * <br>4 pasante
     *
     * @return un entero con el cargo del personal
     */
    public String getCargo() {
        return _conjunto[3];
    }

    /**
     *
     * @return el nombre usuario encriptado del usuario
     */
    public String getUsuario() {
        return _conjunto[4];
    }

    /**
     *
     * @return la contraseña encriptada del usuario
     */
    public String getContra() {
        return _conjunto[5];
    }

    /**
     *
     * @return la fecha en que se registro el usuario
     */
    public String getFechaRegistro() {
        return _conjunto[6];
    }

    /**
     * metodo que un entero segun el estado del usuario
     * <br> -1 inactivo
     * <br> 0 no valido
     * <br> 1 activo
     *
     * @return un entero con el estado del usuario
     */
    public int getEstado() {
        return Integer.parseInt(_conjunto[7]);
    }

    /**
     * Metodo que devuelve una cadena de 5 caracteres con el tipo de operaciones
     * que puede hacer el usuario
     * <br> el primer espacion contiene 1 en caso de tener todos los permisos o
     * un numero menor a 4 segun los permisos
     * <br>
     * <br> 1 todos los permisos
     * <br> C permiso de creacion de registros
     * <br> R permiso de Lectura de registros
     * <br> U permiso de Modifiacion de registros
     * <br> D permiso de Eliminacion de registros
     *
     * @return
     */
    public String getPermisos() {
        return _conjunto[8];
    }

    /**
     * metodo que verifica si el usuario tiene todos los permisos
     *
     * @return true si y solo si el usuario tiene todos los permisos
     */
    public boolean allPermisos() {
        char[] p = _conjunto[8].toCharArray();
        int n = (char) p[0];
        return n == 1;
    }

}
