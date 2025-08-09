/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.model.dtos;

import com.jblue.model.constants.Const;
import com.jblue.model.factories.CacheFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * OPersonal
 * <br>Clase que modelo objetos segun la tabla personals
 *
 * @author jp
 */
public class OEmployee extends Objects implements ForeingKeyObject, StatusObject {

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
     * <br>1 Pasante
     * <br>2 Secretario
     * <br>3 Tesorero
     * <br>4 Presidente
     * <br>5 Administrador.
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
     * @return 1 si el usuario esta "activo", 2 si el usuario esta "inactivo" o
     * 3 si el usuario esta de "baja"
     */
    @Override
    public int getStatus() {
        return Integer.parseInt(info[4]);
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
        return info[5];
    }

    /**
     *
     * @return la contraseña encriptada del usuario
     */
    public String getPassword() {
        return info[6];
    }

    public LocalDateTime getDateRegister() {
        return LocalDateTime.parse(info[7], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    public LocalDateTime getEndDate() {
        if (info[8] == null || info[8].isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(info[8], DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }

    @Override
    public String[] getInfoSinFK() {
        info_fk[3] = getCargoString();
        info_fk[4] = getStatusString();
        return info_fk;
    }

    @Override
    public String toString() {
        return getName().concat(" ").concat(getLastNames());
    }

}
