/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.objetos.ObjetoFK;
import com.jblue.util.tools.ObjectUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class OUser extends Objeto implements ObjetoFK {

    private String[] infoFK;

    public OUser(String[] info) {
        super(info);
        infoFK = info.clone();

    }

    public OUser() {
        super();
    }

    /**
     * metodo que retorna el nombre del usuario
     *
     * @return una cadena con el nombre del usuario
     */
    public String getName() {
        return info[1];
    }

    /**
     *
     * @return una cadena con el apellido paterno del usuario
     */
    public String getLastName1() {
        return info[2];
    }

    /**
     *
     * @return una cadena con el apellido materno del usuario
     */
    public String getLastName2() {
        return info[3];
    }

    /**
     *
     * @return una cadena con el ID de la calle asociada a este usuario
     */
    public String getStreet() {
        return info[4];
    }

    public OCalles getStreetObject() {
        return ObjectUtils.getStreedObject(getStreet());
    }

    public String getHouseNumber() {
        return info[5];
    }

    /**
     *
     * @return una cadena con el ID del tipo de toma asociada a este usuario
     */
    public String getWaterIntakes() {
        return info[6];
    }

    public OWaterIntake getWaterIntakesObject() {
        return ObjectUtils.getWaterIntakesObject(getWaterIntakes());
    }

    /**
     *
     * @return 1 si el usuario es titular en cualquier otro caso devuelve el id
     * del usuario al cual esta asociado
     */
    public int getUserType() {
        return Integer.parseInt(info[7]);
    }

    public String getUserTypeString() {
        return switch (getUserType()) {
            case 1:
                yield "Titular";
            default:
                yield "Consumidor";
        };
    }

    public boolean isTitular() {
        return getUserType() == 1;
    }

    /**
     *
     * @return un entero: 1 si el usuario esta activo -1 si el usuario esta
     * inactivo
     */
    public int getStatus() {
        return Integer.parseInt(info[8]);
    }

    public String getStatusString() {
        return switch (getStatus()) {
            case 1:
                yield "Activo";
            case 2:
                yield "Inactivo";
            case 3:
                yield "Baja";
            default:
                throw new AssertionError();
        };
    }

    public boolean isActive() {
        return getStatus() == 1;
    }

    public LocalDateTime getDateRegister() {
        return LocalDateTime.parse(info[9], DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    /**
     * <br> 0 id
     * <br> 1 nombre
     * <br> 2 apellido paterno
     * <br> 3 apellido materno
     * <br> 4 calle
     * <br> 5 numero de casa
     * <br> 6 tipo de toma
     * <br> 7 tipo de usuario
     * <br> 8 estado
     * <br> 9 fecha de registro
     *
     * @param info array que contiene la informacion en el orden mostrado arriba
     */
    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        infoFK = info.clone();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" ");
        sb.append(getLastName1()).append(" ");
        sb.append(getLastName2());
        return sb.toString();
    }

    @Override
    public String[] getInfoSinFK() {
        infoFK[4] = getStreetObject().getNombre();
        infoFK[6] = getWaterIntakesObject().getType();
        infoFK[7] = getUserTypeString();
        infoFK[8] = getStatusString();

        return infoFK;
    }

}
