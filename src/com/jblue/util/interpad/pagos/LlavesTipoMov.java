/*
 * Copyright (C) 2023 juan-campos
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
package com.jblue.util.interpad.pagos;

/**
 *
 * @author juan-campos
 */
public interface LlavesTipoMov {

    public static String LLAVE_ESTADO = "ESTADO";
    public static String LLAVE_DATOS = "DATOS";
    public static String LLAVE_ERROR = "ERROR";
    public static String LLAVE_DINERO_SOBRANTE = "DINERO-INGRESADO";
    public static String LLAVE_DINERO_INGRESADO = "DINERO-SOBRANTE";
    public static String LLAVE_RECARGOS = "RECARGOS";
    public static String LLAVE_OTROS_PAGOS = "OTROS PAGOS";

    public static String[] TODAS_LLAVES = {
        LLAVE_ESTADO,
        LLAVE_DATOS,
        LLAVE_ERROR,
        LLAVE_DINERO_SOBRANTE,
        LLAVE_DINERO_INGRESADO,
        LLAVE_RECARGOS,
        LLAVE_OTROS_PAGOS
    };

    public static String VALOR_CORRECTO = "CORRECTO";
    public static String VALOR_INCORRECTO = "INCORRECTO";

    public static String FORMAT = "%s: %s";

    default String createErrMensaje(String mensaje) {
        return String.format(FORMAT, LLAVE_ERROR, mensaje);
    }

}
