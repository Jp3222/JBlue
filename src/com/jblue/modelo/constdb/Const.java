/*
 * Copyright (C) 2025 juan-campos
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
package com.jblue.modelo.constdb;

/**
 *
 * @author juan-campos
 */
public class Const {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String ID = "id";

    private static final String[] USER_FIELD = {
        ID, "first_name", "last_name1", "last_name2",
        "street", "house_number", "water_intakes", "user_type",
        "status", "date_register"
    };

    private static final String[] STREET_FIELD = {
        ID, "name"
    };

    private static final String[] WATER_INTAKES_FIELD = {
        ID, "type", "price", "previus_price", "surcharge", "date_update"
    };

    private static final String[] SERVICE_PAYMENT_FIELD = {
        ID, "employee", "user", "price",
        "month", "status", "date_register"
    };

    private static final String[] SURCHARGE_PAYMENT_FIELD = {
        ID, "employee", "user", "price",
        "month", "status", "date_register"
    };

    private static final String[] OTHER_PAYMENT_FIELD = {
        ID, "employee", "user", "price",
        "month", "status", "date_register"
    };

    private static final String[] EMPLOYEES_FIELD = {
        ID, "first_name", "last_names", "employee_type",
        "status", "user", "password", "date_register", "end_date"
    };

    private static final String[] USER_FIELD_GS = {
        ID, "NOMBRE", "A. PATERNO", "A. MATERNO",
        "CALLE", "NO. CASA", "TIPO DE TOMA", "TIPO DE USUARIO",
        "ESTADO", "FECHA DE REGISTRO"
    };

    private static final String[] STREET_FIELD_GS = {
        ID, "NOMBRE"
    };

    private static final String[] WATER_INTAKES_FIELD_GS = {
        ID, "TIPO", "PRECIO", "PRECIO ANTERIOR", "RECARGO", "F. REGISTRO"
    };

    private static final String[] SERVICE_PAYMENT_FIELD_GS = {
        ID, "EMPLEADO", "USUARIO", "PRECIO",
        "MES", "ESTADO", "REGISTRO"
    };

    private static final String[] SURCHARGE_PAYMENT_FIELD_GS = {
        ID, "EMPLEADO", "USUARIO", "PRECIO",
        "MES", "ESTADO", "REGISTRO"
    };

    private static final String[] OTHER_PAYMENT_FIELD_GS = {
        ID, "EMPLEADO", "USUARIO", "PRECIO",
        "MES", "ESTADO", "REGISTRO"
    };

    private static final String[] EMPLOYEES_FIELD_GS = {
        ID, "NOMBRE", "APELLIDOS", "CARGO",
        "ESTADO", "USUARIO", "CONTRASEÑA", "F. REGISTRO", "F. FIN"
    };

    public static final Table USER = new Table(
            "users", USER_FIELD, USER_FIELD_GS
    );

    public static final Table STREETS = new Table(
            "street", STREET_FIELD, STREET_FIELD_GS
    );

    public static final Table WATER_INTAKES = new Table(
            "water_intakes", WATER_INTAKES_FIELD, WATER_INTAKES_FIELD_GS
    );

    public static final Table EMPLOYEES = new Table(
            "employees", EMPLOYEES_FIELD, EMPLOYEES_FIELD_GS
    );

    public static final Table SERVICE_PAYMENTS = new Table(
            "service_payments", SERVICE_PAYMENT_FIELD, SERVICE_PAYMENT_FIELD_GS
    );

    public static final Table SURCHARGE_PAYMENTS = new Table(
            "surcharge_payments", SURCHARGE_PAYMENT_FIELD, SURCHARGE_PAYMENT_FIELD_GS
    );

    public static final Table OTHER_PAYMENTS = new Table(
            "other_payments", OTHER_PAYMENT_FIELD, OTHER_PAYMENT_FIELD_GS
    );
}
