/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.objetos;

import com.jblue.modelo.objetos.sucls.Objeto;

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
        return _conjunto[1];
    }

    public String getNumero() {
        return _conjunto[2];
    }
    
    @Override
    public String getStringR(){
        return _conjunto[1] + " " + _conjunto[2];
    }
}
