/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.constdb.Const;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * OPersonal
 * <br>Clase que modelo objetos segun la tabla personals
 *
 * @author jp
 */
public class OEmployee extends Objeto {

    /**
     * Construye un objeto OPersonal con la informacion dada en el array
     *
     * @param info informacion contenedora de filas de la tabla OPersonal
     */
    public OEmployee(String[] info) {
        super(info);
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
    public int getStatus() {
        return Integer.parseInt(info[4]);
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

    public String getEndDate() {
        return info[8];
    }

    @Override
    public String toString() {
        return getName().concat(" ").concat(getLastNames());
    }

}
