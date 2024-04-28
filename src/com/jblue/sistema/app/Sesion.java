/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.app;

import com.jblue.modelo.objetos.OPersonal;

/**
 *
 * @author jp
 */
public class Sesion {

    private static Sesion instancia;

    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    private OPersonal personal;

    public Sesion() {
    }

    public Sesion(OPersonal personal) {
        this.personal = personal;
    }

    public OPersonal getPersonal() {
        return personal;
    }

    public void inicioSesion() {
        
    }

    public void finSesion() {
    }

}
