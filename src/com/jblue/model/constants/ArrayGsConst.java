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

/**
 *
 * @author juanp
 */
interface ArrayGsConst {

    static final String ID_GS = "id";
    static final String DATE_LAST_UPDATE_GS = "F. DE MODIFICACION";
    static final String DATE_REGISTER_GS = "F. DE REGISTRO";
    static final String DATE_END_GS = "F. DE FINALIZACION";
    static final String EMPLOYEE_GS = "EMPLEADO";
    static final String STATUS_GS = "ESTADO";
    static final String NAME_GS = "NOMBRE";
    static final String MONTH_GS = "MES";
    static final String USER_GS = "USUARIO";

    public static final String[] USERS_FIELD_GS = {
        ID_GS, "CURP", NAME_GS, "A. PATERNO", "A. MATERNO", "EMAIL", "TELEFONO 1", "TELEFONO 2",
        "CALLE 1", "CALLE 2", "NO. INT", "NO. EXT", "TIPO DE TOMA", "TIPO DE USUARIO",
        STATUS_GS, DATE_REGISTER_GS
    };

    public static final String[] STREETS_FIELD_GS = {
        ID_GS, NAME_GS, STATUS_GS
    };

    public static final String[] WATER_INTAKES_TYPES_FIELD_GS = {
        ID_GS, "TIPO", "PRECIO ACTUAL", "PRECIO ANTERIOR", "RECARGO", "ESTATUS", DATE_LAST_UPDATE_GS, DATE_REGISTER_GS
    };

    public static final String[] WATER_INTAKES_FIELD_GS = {
        ID_GS, "TIPO DE TOMA", "COSTO DE OPERACION", "TITULAR", "CALLE 1",
        "CALLE 2", "UBICACION", "DETALLES", STATUS_GS, DATE_LAST_UPDATE_GS, DATE_REGISTER_GS
    };

    public static final String[] SERVICE_PAYMENTS_FIELD_GS = {
        ID_GS, EMPLOYEE_GS, USER_GS, "PRECIO",
        MONTH_GS, "NO. MES", STATUS_GS, DATE_REGISTER_GS
    };

    public static final String[] SURCHARGE_PAYMENTS_FIELD_GS = {
        ID_GS, EMPLOYEE_GS, USER_GS, "PRECIO",
        MONTH_GS, STATUS_GS, DATE_REGISTER_GS
    };

    public static final String[] OTHER_PAYMENTS_FIELD_GS = {
        ID_GS, EMPLOYEE_GS, USER_GS, "PRECIO",
        MONTH_GS, STATUS_GS, DATE_REGISTER_GS
    };

    public static final String[] EMPLOYEES_FIELD_GS = {
        ID_GS, NAME_GS, "APELLIDOS", "CARGO",
        STATUS_GS, USER_GS, "CONTRASEÑA", DATE_REGISTER_GS, "F. FIN"
    };

    public static final String[] EMPLOYEES_TYPES_FIELD_GS = {
        ID_GS, "TIPO DE EMPLEADO", STATUS_GS, DATE_REGISTER_GS, DATE_END_GS};
}
