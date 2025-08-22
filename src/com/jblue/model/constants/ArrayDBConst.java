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
public interface ArrayDBConst {

    static final String ID = "id";
    static final String DATE_LAST_UPDATE = "date_update";
    static final String DATE_REGISTER = "date_register";
    static final String DATE_END = "date_end";
    static final String EMPLOYEE = "employee";
    static final String STATUS = "status";
    static final String NAME = "first_name";
    static final String MONTH = "month";
    static final String USER = "user";

    public static final String[] USER_FIELD = {
        ID, "first_name", "last_name1", "last_name2",
        "street", "house_number", "water_intakes", "user_type",
        STATUS, DATE_REGISTER
    };

    public static final String[] STREET_FIELD = {
        ID, "name", STATUS
    };

    public static final String[] WATER_INTAKES_TYPES_FIELD = {
        ID, "type", "previus_price", "price", "surcharge", STATUS, "date_update"
    };
    public static final String[] WATER_INTAKES_FIELD = {
        ID, "cost_operation", "water_intake_type", "procedure_payments", "user", "street1",
        "street2", "location", "descripcion", STATUS, "date_update", DATE_REGISTER
    };
    public static final String[] SERVICE_PAYMENT_FIELD = {
        ID, EMPLOYEE, "user", "price",
        "month_name", "month", STATUS, DATE_REGISTER
    };

    public static final String[] SURCHARGE_PAYMENT_FIELD = {
        ID, EMPLOYEE, "user", "price",
        "month_name", "month", STATUS, DATE_REGISTER
    };

    public static final String[] OTHER_PAYMENT_FIELD = {
        ID, EMPLOYEE, "user", "price",
        "month", STATUS, DATE_REGISTER
    };

    public static final String[] EMPLOYEES_FIELD = {
        ID, "first_name", "last_names", "employee_type",
        STATUS, "user", "password", DATE_REGISTER, "end_date"
    };

    public static final String[] EMPLOYEES_TYPES_FIELD = {
        ID, "employee_type", STATUS, DATE_REGISTER
    };
}
