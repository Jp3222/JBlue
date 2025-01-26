/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OCalles extends Objeto {

    public OCalles(String... info) {
        super(info);
    }

    public OCalles() {
        super();
    }

    public String getNombre() {
        return info[1];
    }

    public String getNumero() {
        return info[2];
    }
    
    public String getLocation(){
        return "ToDo";
    }

    @Override
    public String toString() {
        return getNombre().concat(" ").concat(getNumero());
    }

}
