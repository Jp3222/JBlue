/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.util.objetos.StatusObject;

/**
 *
 * @author jp
 */
public class OStreet extends Objeto implements StatusObject {

    public OStreet(String... info) {
        super(info);
    }

    public OStreet() {
        super();
    }

    public String getNombre() {
        return info[1];
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(info[2]);
    }

    @Override
    public String getStatusString() {
        return switch (getStatus()) {
            case 2:
                yield "Inactivo";
            case 3:
                yield "Borrado";
            default:
                yield "Activo";
        };
    }

    @Override
    public boolean isActive() {
        return getStatus() == 1;
    }

    public String getLocation() {
        return "ToDo";
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
