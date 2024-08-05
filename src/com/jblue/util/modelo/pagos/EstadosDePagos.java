/*
 * Copyright (C) 2024 juan-campos
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
package com.jblue.util.modelo.pagos;

/**
 *
 * @author juan-campos
 */
public interface EstadosDePagos {

    public static String ESTADO = "ESTADO";
    public static String DATOS = "DATOS";
    public static String ERROR = "ERROR";
    public static String DINERO_SOBRANTE = "DINERO-INGRESADO";
    public static String DINERO_INGRESADO = "DINERO-SOBRANTE";
    public static String RECARGOS = "RECARGOS";
    public static String OTROS_PAGOS = "OTROS PAGOS";

    public static String[] TODAS_LLAVES = {
        ESTADO,
        DATOS,
        ERROR,
        DINERO_SOBRANTE,
        DINERO_INGRESADO,
        RECARGOS,
        OTROS_PAGOS
    };

    public static String VALOR_CORRECTO = "CORRECTO";
    public static String VALOR_INCORRECTO = "INCORRECTO";

    public static String FORMAT = "%s: %s";

    default String createErrMensaje(String mensaje) {
        return String.format(FORMAT, ERROR, mensaje);
    }
}
