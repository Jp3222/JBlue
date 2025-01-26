/*
 * Copyright (C) 2023 jp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.modelo;

/**
 * Esta clase contiene variables para el uso externo o grafico(interfaces o
 * ventanas), la idea de estas cadenas es que sean escritas de forma mas
 * atractiva para el usuario
 *
 * @author jp
 */
public abstract class ConstGs {

    private static final String NOMBRE = "Nombre";
    private static final String ID = "ID";
    private static final String AP = "A. Paterno";
    private static final String AM = "A. Materno";
    private static final String USUARIO = "Usuario";
    private static final String PERSONAL = "Personal";
    private static final String MONTO = "Monto";
    //private static final String REGISTRO = "Registro";
    private static final String DIA = "Dia";
    private static final String MES = "Mes";
    private static final String ANIO = "Año";
    private static final String MES_PAGADO = "Mes Pagado";
    private static final String REGISTER = "F. Registro";

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
        ID, NOMBRE, AP, AM,
        "Calle", "No de Casa", "T. Toma",
        "Estado", "Tipo", REGISTER
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
     * s
     * <br> 1 id
     * <br> 2 nombre
     * <br> 3 numero
     */
    public static final String[] TABLA_CALLES = {
        ID, NOMBRE
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
        ID, NOMBRE, "apellidos", "cargo", USUARIO, "contra", "estado", "permisos", "Fecha de Inicio", "Fecha de Fin"
    };

    /**
     *
     * <br> 1 id
     * <br> 2 personal
     * <br> 3 movimiento
     * <br> 4 fecha
     * <br> 5 hora
     */
    public static final String[] TABLA_HISTORIAL_DE_MOVIMIENTOS = {
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
        ID, PERSONAL, USUARIO, MES_PAGADO, MONTO, DIA, MES, ANIO
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
        ID, PERSONAL, USUARIO, MES_PAGADO, MONTO, "Estado", DIA, MES, ANIO
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
        ID, PERSONAL, USUARIO, MONTO, "Motivo", "Descripcion", DIA, MES, ANIO
    };

    /**
     *
     * <br> 1 id
     * <br> 2 motivo
     * <br> 3 descripcion
     * <br> 4 monto
     * <br> 5 url_notas
     */
    public static final String[] TABLA_PAGOS_X_OTROS_TIPOS = {
        ID, "motivo", "descripcion", MONTO, "url_notas"
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
        "Personal",
        "Usuarios",
        "Calles",
        "Tipo de Tomas",
        "Historial de Movimientos",
        "Movimientos",
        "Pagos por el Servicio",
        "Pagos por Recargos",
        "Pagos por otros",
        "Variables del sistema"
    };

    public static final String[][] CAMPOS = {
        TABLA_PERSONAL,
        TABLA_USUARIOS,
        TABLA_CALLES,
        TABLA_TIPOS_DE_TOMAS,
        TABLA_HISTORIAL_DE_MOVIMIENTOS,
        TABLA_MOVIMIENTOS,
        TABLA_PAGOS_X_SERVICIO,
        TABLA_PAGOS_X_RECARGO,
        TABLA_PAGOS_X_OTROS
    };

}
