/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dtos;

import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.ObjectUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class OUser extends Objects implements ForeingKeyObject, StatusObject {

    private String[] infoFK;

    public OUser(String[] info) {
        super(info);
        infoFK = info.clone();

    }

    public OUser() {
        super();
    }

    public String getCURP() {
        return info[1];
    }

    /**
     * metodo que retorna el nombre del usuario
     *
     * @return una cadena con el nombre del usuario
     */
    public String getName() {
        return info[2];
    }

    /**
     *
     * @return una cadena con el apellido paterno del usuario
     */
    public String getLastName1() {
        return info[3];
    }

    /**
     *
     * @return una cadena con el apellido materno del usuario
     */
    public String getLastName2() {
        return info[4];
    }

    public int getGender() {
        return Integer.parseInt(info[5]);
    }

    public String getEmail() {
        return info[6];
    }

    public String getPhoneNumber1() {
        return info[7];
    }

    public String getPhoneNumber2() {
        return info[8];
    }

    /**
     *
     * @return una cadena con el ID de la calle asociada a este usuario
     */
    public String getStreet() {
        return info[9];
    }

    public OStreet getStreetObject() {
        return ObjectUtils.getStreedObject(getStreet());
    }

    public String getStreet2() {
        return info[10];
    }

    public OStreet getStreetObject2() {
        return ObjectUtils.getStreedObject(getStreet2());
    }

    public String getInsideNumber() {
        return info[11];
    }

    public String getOutSideNumber() {
        return info[12];
    }

    /**
     *
     * @return una cadena con el ID del tipo de toma asociada a este usuario
     */
    public String getWaterIntakeType() {
        return info[13];
    }

    public OWaterIntakeTypes getWaterIntakesObject() {
        return ObjectUtils.getWaterIntakeTypeObject(getWaterIntakeType());
    }

    /**
     *
     * @return 1 si el usuario es titular o 2 si el usuario es consumidor
     */
    public int getUserType() {
        return Integer.parseInt(info[14]);
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
    @Override
    public int getStatus() {
        return Integer.parseInt(info[15]);
    }

    @Override
    public String getStatusString() {
        return CacheFactory.ITEMS_STATUS_CAT[getStatus()];
    }

    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

    public boolean isDelete() {
        return getStatus() == 3;
    }

    public LocalDateTime getDateUpdate() {
        return LocalDateTime.parse(info[16], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
    }

    public LocalDateTime getDateRegister() {
        return LocalDateTime.parse(info[17], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
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
    public void setData(String[] info) {
        super.setData(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
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
    public String[] getData() {
        return super.getData(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
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
        infoFK[9] = getStreetObject().getNombre();
        infoFK[10] = getStreetObject2() == null ? " " : getStreetObject2().getNombre();
        infoFK[13] = getWaterIntakesObject().getTypeName();
        infoFK[14] = getUserTypeString();
        infoFK[15] = getStatusString();
        return infoFK;
    }

}
