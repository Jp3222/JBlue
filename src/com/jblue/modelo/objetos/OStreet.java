/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OStreet extends Objeto {

    public OStreet(String... info) {
        super(info);
    }

    public OStreet() {
        super();
    }

    public String getNombre() {
        return info[1];
    }

    public String getLocation() {
        return "ToDo";
    }

    @Override
    public String toString() {
        return getNombre();
    }

}
