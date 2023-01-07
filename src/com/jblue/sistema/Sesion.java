/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.modelo.objetos.OPersonal;

/**
 *
 * @author jp
 */
public class Sesion {

    private static Sesion instancia;

    public static synchronized Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    private OPersonal usuario;
    
    private Sesion() {
        this.usuario = null;
    }

    public OPersonal getUsuario() {
        return usuario;
    }

    public void setUsuario(OPersonal usuario) {
        this.usuario = usuario;
    }

    public void inicioSesion() {

    }

    public void finSesion() {

    }
}
