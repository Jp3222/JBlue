/*
 * Copyright (C) 2025 juanp
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
package com.jblue.model.constants;

import static com.jblue.model.constants.ArrayDBConst.ID;

/**
 *
 * @author juanp
 */
interface ArrayGsConst {

    static final String ID = "id";

    public static final String[] USER_FIELD_GS = {
        ID, "NOMBRE", "A. PATERNO", "A. MATERNO",
        "CALLE", "NO. CASA", "TIPO DE TOMA", "TIPO DE USUARIO",
        "ESTADO", "FECHA DE REGISTRO"
    };

    public static final String[] STREET_FIELD_GS = {
        ID, "NOMBRE", "STATUS"
    };

    public static final String[] WATER_INTAKES_TYPES_FIELD_GS = {
        ID, "TIPO", "PRECIO ANTERIOR", "PRECIO", "RECARGO", "ESTATUS", "F. DE ACTUALIZACION"
    };

    public static final String[] WATER_INTAKES_FIELD_GS = {
        ID, "TIPO DE TOMA", "COSTO DE OPERACION", "TITULAR", "CALLE 1", 
        "CALLE 2", "UBICACION", "ESTADO", "F. DE ACTUALIZACION", "F. DE REGISTRO"
    };

    public static final String[] SERVICE_PAYMENT_FIELD_GS = {
        ID, "EMPLEADO", "USUARIO", "PRECIO",
        "MES", "NO. MES", "ESTADO", "REGISTRO"
    };

    public static final String[] SURCHARGE_PAYMENT_FIELD_GS = {
        ID, "EMPLEADO", "USUARIO", "PRECIO",
        "MES", "ESTADO", "REGISTRO"
    };

    public static final String[] OTHER_PAYMENT_FIELD_GS = {
        ID, "EMPLEADO", "USUARIO", "PRECIO",
        "MES", "ESTADO", "REGISTRO"
    };

    public static final String[] EMPLOYEES_FIELD_GS = {
        ID, "NOMBRE", "APELLIDOS", "CARGO",
        "ESTADO", "USUARIO", "CONTRASEÑA", "F. REGISTRO", "F. FIN"
    };

}
