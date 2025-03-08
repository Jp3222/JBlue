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
     * @return el nombre del personal
     */
    public String getName() {
        return info[1];
    }

    /**
     *
     * @return los apellidos del personal
     */
    public String getLastNames() {
        return info[2];
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
    public String getType() {
        return info[3];
    }

    public String getCargoString() {
        return switch (getType()) {
            case "1":
                yield "Pasante";
            case "2":
                yield "Secretario";
            case "3":
                yield "Tesorero";
            case "4":
                yield "Presidente";
            case "5":
                yield "Administrador";
            default:
                yield "none";
        };
    }

    /**
     *
     * @return el nombre usuario encriptado del usuario
     */
    public String getUsuario() {
        return info[5];
    }

    /**
     *
     * @return la contraseña encriptada del usuario
     */
    public String getContra() {
        return info[6];
    }

    /**
     *
     * @return la fecha en que se registro el usuario
     */
    public String getFechaRegistro() {
        return info[7];
    }

    /**
     * metodo que un entero segun el estado del usuario
     * <br> -1 inactivo
     * <br> 0 no valido
     * <br> 1 activo
     *
     * @return un entero con el estado del usuario
     */
    public int getStatus() {
        return Integer.parseInt(info[4]);
    }

    public String getPeriodoString() {
        return info[8];
    }

    @Override
    public String toString() {
        return getName().concat(" ").concat(getLastNames());
    }

}
