/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo;

/**
 *
 * @author jp
 */
public class Const {

    private static final String NOMBRE = "nombre";
    private static final String ID = "id";
    private static final String AP = "ap";
    private static final String AM = "am";

    public static final String[] BD_CONSUMIDORES = {
        ID, NOMBRE, AP, AM, "calle", "titular", "fregistro", "estado"
    };

    public static final String[] BD_TITULARES = {
        ID, NOMBRE, AP, AM, "calle", "fregistro", "estado"
    };

    public static final String[] BD_TOMAS_REGISTRADAS = {
        ID, "calle"
    };

    public static final String[] BD_TIPOS_DE_TOMAS = {
        ID, "tipo", "costo"
    };

    public static final String[] BD_CALLES = {
        ID, "nombre", "numero"
    };

    public static final String[] BD_USUARIOS = {
        ID, "nombre", "apellidos", "cargo", "usuario", "contraseña"
    };

}
