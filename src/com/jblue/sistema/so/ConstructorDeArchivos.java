/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class ConstructorDeArchivos {

    private String rutaUsuario;
    private ArrayList<String> rutasDeTrabajo;

    public ConstructorDeArchivos(String rutaUsuario) {
        this.rutaUsuario = rutaUsuario;
        this.rutasDeTrabajo = new ArrayList<>();
    }

}
