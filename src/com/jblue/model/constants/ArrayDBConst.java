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

    public static final String[] USER_FIELD = {
        ID, "first_name", "last_name1", "last_name2",
        "street", "house_number", "water_intakes", "user_type",
        "status", "date_register"
    };

    public static final String[] STREET_FIELD = {
        ID, "name", "status"
    };

    public static final String[] WATER_INTAKES_TYPES_FIELD = {
        ID, "type", "previus_price", "price", "surcharge", "status", "date_update"
    };
    public static final String[] WATER_INTAKES_FIELD = {
        ID,"water_intake_type", "cost_operation", "user", "street1", 
        "street2", "location", "status", "date_update", "date_register"
    };
    public static final String[] SERVICE_PAYMENT_FIELD = {
        ID, "employee", "user", "price",
        "month_name", "month", "status", "date_register"
    };

    public static final String[] SURCHARGE_PAYMENT_FIELD = {
        ID, "employee", "user", "price",
        "month_name", "month", "status", "date_register"
    };

    public static final String[] OTHER_PAYMENT_FIELD = {
        ID, "employee", "user", "price",
        "month", "status", "date_register"
    };

    public static final String[] EMPLOYEES_FIELD = {
        ID, "first_name", "last_names", "employee_type",
        "status", "user", "password", "date_register", "end_date"
    };

}
