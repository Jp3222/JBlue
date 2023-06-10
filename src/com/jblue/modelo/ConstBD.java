/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo;

/**
 * Esta clase contiene variables relacionadas a la base de datos, solo para uso
 * interno
 *
 * @author jp
 */
public abstract class ConstBD {

    private static final String NOMBRE = "nombre";
    private static final String ID = "id";
    private static final String AP = "ap";
    private static final String AM = "am";
    private static final String USUARIO = "usuario";
    private static final String PERSONAL = "personal";
    private static final String MONTO = "monto";
    private static final String REGISTRO = "registro";
    private static final String DIA = "dia";
    private static final String MES = "mes";
    private static final String AÑO = "año";

    /**
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 ap
     * <br> 4 am
     * <br> 5 calle
     * <br> 6 toma
     * <br> 7 registro
     * <br> 8 estado
     * <br> 9 titular
     */
    public static final String[] BD_USUARIOS = {
        ID, NOMBRE, AP, AM, "calle", "ncasa", "toma", REGISTRO, "estado", "titular", "codigo"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 tipo
     * <br> 3 costo
     * <br> 4 recargo
     */
    public static final String[] BD_TIPOS_DE_TOMAS = {
        ID, "tipo", "costo", "recargo"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 numero
     */
    public static final String[] BD_CALLES = {
        ID, NOMBRE, "numero"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 apellidos
     * <br> 4 cargo
     * <br> 5 usuario
     * <br> 6 contra
     * <br> 7 registro
     * <br> 8 estado
     * <br> 9 permisos
     */
    public static final String[] BD_PERSONAL = {
        ID, NOMBRE, "apellidos", "cargo", USUARIO, "contra", REGISTRO, "estado", "permisos", "periodo"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 personal
     * <br> 3 movimiento
     * <br> 4 fecha
     * <br> 5 hora
     */
    public static final String[] BD_HISTORIAL_MOVIMIENTOS = {
        ID, PERSONAL, "movimiento", "fecha", "hora"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 movimiento
     * <br> 3 descripcion
     */
    public static final String[] BD_MOVIMIENTOS = {
        ID, "movimiento", "descripcion"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 personal
     * <br> 3 usuario
     * <br> 4 mes_pagado
     * <br> 5 monto
     * <br> 6 dia
     * <br> 7 mes
     * <br> 8 año
     */
    public static final String[] BD_PAGOS_X_SERVICIO = {
        ID, PERSONAL, USUARIO, "mes_pagado", MONTO, DIA, MES, AÑO
    };

    /**
     *
     * <br> 1 id
     * <br> 2 personal
     * <br> 3 usuario
     * <br> 4 mes_pagado
     * <br> 5 monto
     * <br> 6 estado
     * <br> 7 dia
     * <br> 8 mes
     * <br> 9 año
     */
    public static final String[] BD_PAGOS_X_RECARGO = {
        ID, PERSONAL, USUARIO, "mes_pagado", MONTO, "estado", DIA, MES, AÑO
    };

    /**
     *
     * <br> 1 id
     * <br> 2 personal
     * <br> 3 usuario
     * <br> 4 monto
     * <br> 5 motivo
     * <br> 6 descripcion
     * <br> 7 dia
     * <br> 8 mes
     * <br> 9 año
     */
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
        "movimiento",
        "pagos_x_servicio",
        "pagos_x_recargos",
        "pagos_x_otros"
    };

    public static final String[][] CAMPOS = {
        BD_PERSONAL,
        BD_USUARIOS,
        BD_CALLES,
        BD_TIPOS_DE_TOMAS,
        BD_HISTORIAL_MOVIMIENTOS,
        BD_MOVIMIENTOS,
        BD_PAGOS_X_SERVICIO,
        BD_PAGOS_X_RECARGO,
        BD_PAGOS_X_OTROS
    };

}
