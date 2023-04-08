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
    private static final String MONTO = "monto";
    private static final String REGISTRO = "registro";
    private static final String DIA = "dia", MES = "mes", AÑO = "año";

    public static final String[] BD_USUARIOS = {
        ID, NOMBRE, AP, AM, "calle", "toma", REGISTRO, "estado", "titular"
    };

    public static final String[] BD_TIPOS_DE_TOMAS = {
        ID, "tipo", "costo", "recargo"
    };

    public static final String[] BD_CALLES = {
        ID, "nombre", "numero"
    };

    public static final String[] BD_PERSONAL = {
        ID, NOMBRE, "apellidos", "cargo", USUARIO, "contra", REGISTRO, "estado", "permisos"
    };

    public static final String[] BD_HISTORIAL_MOVIMIENTOS = {
        ID, PERSONAL, "movimiento", "fecha", "hora"
    };

    public static final String[] BD_MOVIMIENTOS = {
        ID, "movimiento", "descripcion"
    };

    public static final String[] BD_PAGOS_X_SERVICIO = {
        ID, PERSONAL, USUARIO, "mes_pagado", MONTO, DIA, MES, AÑO
    };

    public static final String[] BD_PAGOS_X_RECARGO = {
        ID, PERSONAL, USUARIO, "mes_pagado", MONTO, "estado", DIA, MES, AÑO
    };

    public static final String[] BD_PAGOS_X_OTROS = {
        ID, PERSONAL, USUARIO, MONTO, "motivo", "descripcion", DIA, MES, AÑO
    };

    /**
     * Constante que guarda las tablas de la base de datos
     * <br>0 - personal
     * <br>1 - usuarios
     * <br>2 - calles
     * <br>3 - tipo de tomas
     * <br>4 - historial de movimientos
     * <br>5 - movimientos
     * <br>6 - pagos_x_servicio
     * <br>7 - pagos_x_recargos
     * <br>8 - pagos_x_otros
     *
     */
    public static final String[] TABLAS = {
        "personal",
        "usuarios",
        "calles",
        "tipo_tomas",
        "historial_movimientos",
        "movimientos",
        "pagos_x_servicio",
        "pagos_x_recargos",
        "pagos_x_otros"
    };

}
