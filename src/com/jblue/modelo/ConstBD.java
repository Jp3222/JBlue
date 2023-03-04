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
    private static final String USUARIO = "usuario";
    private static final String PERSONAL = "personal";

//    public static final String[] BD_CONSUMIDORES = {
//        ID, NOMBRE, AP, AM, "titular", "registro", "estado"
//    };
//
//    public static final String[] BD_TITULARES = {
//        ID, NOMBRE, AP, AM, "calle", "tipo_toma", "registro", "estado"
//    };
    public static final String[] BD_USUARIOS = {
        ID, NOMBRE, AP, AM, "calle", "toma", "registro", "estado", "titular"
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
        ID, NOMBRE, "apellidos", "cargo", USUARIO, "contra", "registro", "estado", "permisos"
    };

    public static final String[] BD_HISTORIAL_MOVIMIENTOS = {
        ID, PERSONAL, "movimiento", "fecha", "hora"
    };

    public static final String[] BD_MOVIMIENTOS = {
        ID, "movimiento", "descripcion"
    };

    public static final String[] BD_PAGOS = {ID, PERSONAL, USUARIO, "meses", "pago", "estado", "fecha"};

    /**
     * Constante que guarda las tablas de la base de datos
     * <br>0 - personal
     * <br>1 - usuarios
     * <br>2 - calles
     * <br>3 - tipo de tomas
     * <br>4 - historial de movimientos
     * <br>5 - movimientos
     *
     */
    public static final String[] TABLAS = {
        "personal", "usuarios", "calles",
        "tipo_tomas", "historial_movimientos", "movimientos"
    };

}
