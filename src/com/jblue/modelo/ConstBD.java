/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo;

/**
 *
 * @author jp
 */
public class ConstBD {

    private static final String NOMBRE = "nombre";
    private static final String ID = "id";
    private static final String AP = "ap";
    private static final String AM = "am";

//    public static final String[] BD_CONSUMIDORES = {
//        ID, NOMBRE, AP, AM, "titular", "registro", "estado"
//    };
//
//    public static final String[] BD_TITULARES = {
//        ID, NOMBRE, AP, AM, "calle", "tipo_toma", "registro", "estado"
//    };
    public static final String[] BD_USUARIOS = {
        ID, NOMBRE, AP, AM
    };

    public static final String[] BD_TOMAS_REGISTRADAS = {
        ID, "calle"
    };

    public static final String[] BD_TIPOS_DE_TOMAS = {
        ID, "tipo", "costo", "recargo"
    };

    public static final String[] BD_CALLES = {
        ID, "nombre", "numero"
    };
    
    public static final String[] BD_PERSONAL = {
        ID, NOMBRE, "apellidos", "cargo", "usuario", "contra", "registro", "estado", "permisos"
    };

    /**
     * Constante dedicada a las tablas de la base de datos
     * <br>0 - personal
     * <br>1 - titulares
     * <br>2 - consumidores
     * <br>3 - calles
     * <br>4 - tipo de tomas
     */
    public static final String[] TABLAS = {
        "personal", "titulares", "consumidores", "calles", "tipo_tomas"
    };

    public static String getAM() {
        
        return AM;
    }
    
}
