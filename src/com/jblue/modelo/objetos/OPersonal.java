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

    private String nombre,
            apellidos,
            cargo,
            usuario,
            contra;

    /**
     * Construye un objeto OPersonal con la informacion dada en el array
     *
     * @param info informacion contenedora de filas de la tabla OPersonal
     */
    public OPersonal(String[] info) {
        super(info);
        this.nombre = info[1];
        this.apellidos = info[2];
        this.cargo = info[3];
        this.usuario = info[4];
        this.contra = info[5];
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
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContra() {
        return contra;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info);
        this.nombre = info[1];
        this.apellidos = info[2];
        this.cargo = info[3];
        this.usuario = info[4];
        this.contra = info[5];
    }

}
