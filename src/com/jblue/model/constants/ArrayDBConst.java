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

    static final String ID_FIELD = "id";
    static final String DATE_LAST_UPDATE_FIELD = "date_update";
    static final String DATE_REGISTER_FIELD = "date_register";
    static final String DATE_END_FIELD = "date_end";
    static final String EMPLOYEE_FIELD = "employee";
    static final String STATUS_FIELD = "status";
    static final String NAME_FIELD = "first_name";
    static final String MONTH_FIELD = "month";
    static final String USER_FIELD = "user";

    public static final String[] USERS_FIELD = {
        ID_FIELD, "curp", "first_name", "last_name1", "last_name2", "gender",
        "email", "phone_number1", "phone_number2", "street1",
        "street2", "outside_number", "inside_number", "water_intake_type",
        "user_type", "status", "date_last_update", "date_register"

    };

    public static final String[] STREETS_FIELD = {
        ID_FIELD, "name", STATUS_FIELD
    };

    public static final String[] WATER_INTAKES_TYPES_FIELD = {
        ID_FIELD, "type_name", "current_price", "previous_price", "surcharge", STATUS_FIELD, DATE_LAST_UPDATE_FIELD, DATE_REGISTER_FIELD
    };
    public static final String[] WATER_INTAKES_FIELD = {
        ID_FIELD, "cost_operation", "water_intake_type", "procedure_payments", "user", "street1",
        "street2", "location", "descripcion", STATUS_FIELD, "date_update", DATE_REGISTER_FIELD
    };
    public static final String[] SERVICE_PAYMENTS_FIELD = {
        ID_FIELD, EMPLOYEE_FIELD, "user", "price",
        "month_name", "month", STATUS_FIELD, DATE_REGISTER_FIELD
    };

    public static final String[] SURCHARGE_PAYMENTS_FIELD = {
        ID_FIELD, EMPLOYEE_FIELD, "user", "price",
        "month_name", "month", STATUS_FIELD, DATE_REGISTER_FIELD
    };

    public static final String[] OTHER_PAYMENTS_FIELD = {
        ID_FIELD, EMPLOYEE_FIELD, "user", "price",
        "month", STATUS_FIELD, DATE_REGISTER_FIELD
    };

    public static final String[] EMPLOYEES_FIELD = {
        ID_FIELD, "first_name", "last_names", "employee_type",
        STATUS_FIELD, "user", "password", DATE_REGISTER_FIELD, "end_date"
    };

    public static final String[] EMPLOYEES_TYPES_FIELD = {
        ID_FIELD, "employee_type", STATUS_FIELD, DATE_REGISTER_FIELD
    };
}
