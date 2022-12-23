/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

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

    /**
     * Metodo que devuelve el nombre del personal
     *
     * @return
     */
    public String getNombre() {
        return info[1];
    }

    public String getApellidos() {
        return info[2];
    }

    public String getCargo() {
        return info[3];
    }

    public String getUsuario() {
        return info[4];
    }

    public String getContra() {
        return info[5];
    }
    
    public String getFechaRegistro(){
        return info[6];
    }
    
}
