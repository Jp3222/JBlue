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
    public static final String[] TABLA_USUARIOS = {
        ID, NOMBRE, AP, AM, "calle", "ncasa", "toma", REGISTRO, "estado", "titular", "codigo"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 tipo
     * <br> 3 costo
     * <br> 4 recargo
     */
    public static final String[] TABLA_TIPOS_DE_TOMAS = {
        ID, "tipo", "costo", "recargo"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 numero
     */
    public static final String[] TABLA_CALLES = {
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
    public static final String[] TABLA_PERSONAL = {
        ID, NOMBRE, "apellidos", "cargo", USUARIO, "contra", REGISTRO, "estado", "permisos", "fecha_inicio", "fecha_fin"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 personal
     * <br> 3 movimiento
     * <br> 4 fecha
     * <br> 5 hora
     */
    public static final String[] TABLA_HISTORIAL_MOVIMIENTOS = {
        ID, PERSONAL, "movimiento", "fecha", "hora"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 movimiento
     * <br> 3 descripcion
     */
    public static final String[] TABLA_MOVIMIENTOS = {
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
    public static final String[] TABLA_PAGOS_X_SERVICIO = {
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
    public static final String[] TABLA_PAGOS_X_RECARGO = {
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
    public static final String[] TABLA_PAGOS_X_OTROS = {
        ID, PERSONAL, USUARIO, MONTO, "motivo", "descripcion", DIA, MES, AÑO
    };

    public static final String[] TABLA_VAR_SIS = {
        ID, "clave", "valor", "nom_gf"
    };

    /**
     * Constante que guarda las tablas de la base de datos
     * <table>
     * <tr>
     * <th>#</th>
     * <th>Tabla</th>
     * </tr>
     *
     * <tr>
     * <th>0</th>
     * <td>Personal</td>
     * </tr>
     * <tr>
     * <th>1</th>
     * <td>Usuarios</td>
     * </tr>
     * <tr>
     * <th>2</th>
     * <td>Calles</td>
     * </tr>
     * <tr>
     * <th>3</th>
     * <th>Tipo de tomas</th>
     * </tr>
     * <tr>
     * <th>4</th>
     * <th>Historial</th>
     * </tr>
     * <tr>
     * <th>5</th>
     * <th>Movimientos</th>
     * </tr>
     * <tr>
     * <th>6</th>
     * <th>Pagos por el servicio</th>
     * </tr>
     * <tr>
     * <th>7</th>
     * <th>Pagos por recargos</th>
     * </tr>
     * <tr>
     * <th>8</th>
     * <th>Otros Pagos</th>
     * </tr>
     * <tr>
     * <th>9</th>
     * <th>Variables del sistema</th>
     * </tr>
     *
     * </table>
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
        "pagos_x_otros",
        "var_sis"
    };

    public static final String[][] CAMPOS = {
        TABLA_PERSONAL,
        TABLA_USUARIOS,
        TABLA_CALLES,
        TABLA_TIPOS_DE_TOMAS,
        TABLA_HISTORIAL_MOVIMIENTOS,
        TABLA_MOVIMIENTOS,
        TABLA_PAGOS_X_SERVICIO,
        TABLA_PAGOS_X_RECARGO,
        TABLA_PAGOS_X_OTROS,
        TABLA_VAR_SIS
    };

}
