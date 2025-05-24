/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.constdb.Const;
import com.jblue.util.tools.ObjectUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.jblue.util.objetos.ForeingKeyObject;
import com.jblue.util.objetos.StatusObject;

/**
 *
 * @author jp
 */
public class OUser extends Objeto implements ForeingKeyObject, StatusObject{

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
     * @return 1 si el usuario es titular o 2 si el usuario es consumidor
     */
    public int getUserType() {
        return Integer.parseInt(info[7]);
    }

    /**
     * @return "Titular" o "Consumidor".
     */
    public String getUserTypeString() {
        return switch (getUserType()) {
            case 1:
                yield "Titular";
            default:
                yield "Consumidor";
        };
    }

    /**
     *
     * @return true si el usuario es titular, false si es consumidor
     */
    public boolean isTitular() {
        return getUserType() == 1;
    }

    /**
     *
     * @return 1 si el usuario esta "activo", 2 si el usuario esta "inactivo" o
     * 3 si el usuario esta de "baja"
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

    public boolean isDelete() {
        return getStatus() == 3;
    }

    public LocalDateTime getDateRegister() {
        return LocalDateTime.parse(info[9], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    /**
     * @param info
     * <br> 0 id
     * <br> 1 first_name
     * <br> 2 last_name1
     * <br> 3 last_name2
     * <br> 4 street
     * <br> 5 house_number
     * <br> 6 water_intakes
     * <br> 7 user_type
     * <br> 8 status
     * <br> 9 date_register
     */
    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        infoFK = info.clone();
    }

    /**
     * <br> 0 id
     * <br> 1 first_name
     * <br> 2 last_name1
     * <br> 3 last_name2
     * <br> 4 street
     * <br> 5 house_number
     * <br> 6 water_intakes
     * <br> 7 user_type
     * <br> 8 status
     * <br> 9 date_register
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

    /**
     * <br> 0 id
     * <br> 1 first_name
     * <br> 2 last_name1
     * <br> 3 last_name2
     * <br> 4 street
     * <br> 5 house_number
     * <br> 6 water_intakes
     * <br> 7 user_type
     * <br> 8 status
     * <br> 9 date_register
     *
     * @return un arreglo con la informacion en el orden mostrado
     */
    @Override
    public String[] getInfoSinFK() {
        infoFK[4] = getStreetObject().getNombre();
        infoFK[6] = getWaterIntakesObject().getType();
        infoFK[7] = getUserTypeString();
        infoFK[8] = getStatusString();
        return infoFK;
    }

}
