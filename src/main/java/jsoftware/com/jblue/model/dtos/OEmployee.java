/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dtos;

import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.factories.CacheFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * OPersonal
 * <br>Clase que modelo objetos segun la tabla personals
 *
 * @author jp
 */
public class OEmployee extends Objects implements ForeingKeyObject, StatusObject {

    /**
     * employee_type, status, user, password, date_update, date_register,
     * date_end
     */
    private String[] info_fk;

    /**
     * Construye un objeto OPersonal con la informacion dada en el array
     *
     * @param info informacion contenedora de filas de la tabla OPersonal
     */
    public OEmployee(String[] info) {
        super(info);
        info_fk = info.clone();
    }

    /**
     * Constuye un objeto OPersonal vacio el cual se puede llenar con el metodo
     * serInfo
     */
    public OEmployee() {
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
    public String getFirstName() {
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

    public String getDateBirday() {
        return info[7];
    }

    public String getPhoneNumber1() {
        return info[8];
    }

    public String getPhoneNumber2() {
        return info[9];
    }

    public int getEmployeeType() {
        return Integer.parseInt(info[10]);
    }

    public String getStringEmployeeType() {
        return CacheFactory.EMPLOYEE_TYPE_CAT[getEmployeeType()];
    }

    /**
     *
     * @return 1 si el usuario esta "activo", 2 si el usuario esta "inactivo" o
     * 3 si el usuario esta de "baja"
     */
    @Override
    public int getStatus() {
        return Integer.parseInt(info[11]);
    }

    @Override
    public String getStatusString() {
        return CacheFactory.ITEMS_STATUS_CAT[getStatus()];
    }

    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

    /**
     *
     * @return el nombre usuario encriptado del usuario
     */
    public String getUser() {
        return info[12];
    }

    /**
     *
     * @return la contraseña encriptada del usuario
     */
    public String getPassword() {
        return info[13];
    }

    public LocalDateTime getDateRegister() {
        return LocalDateTime.parse(info[14], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
    }

    public LocalDateTime getDateUpdate() {
        return LocalDateTime.parse(info[15], DateTimeFormatter.ofPattern(_Const.DATE_TIME_FORMAT));
    }

    public LocalDateTime getDateEnd() {
        if (info[16] == null || info[16].isEmpty()) {
            return null;
        }
        return _Const.parseDateTime(info[16]);
    }

    @Override
    public void setData(String[] info) {
        super.setData(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.info_fk = info.clone();
    }

    @Override
    public String[] getInfoSinFK() {
        info_fk[5] = CacheFactory.CAT_GENDER[getGender()];
        info_fk[10] = getStringEmployeeType();
        info_fk[11] = getStatusString();
        return info_fk;
    }

    @Override
    public String toString() {
        return getFirstName().concat(" ").concat(getLastName1()).concat(" ").concat(getLastName2() ==null ? "":getLastName2());
    }

}
