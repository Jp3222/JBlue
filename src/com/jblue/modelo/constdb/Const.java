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

    private static final String ID = "id";

    public static final Table USER = new Table("users",
            ID, "first_name", "last_name1", "last_name2",
            "street", "house_number", "water_intakes", "user_type",
            "status", "date_register"
    );

    public static final Table STREETS = new Table("street",
            ID, "name"
    );

    public static final Table WATER_INTAKES = new Table("water_intakes",
            ID, "type", "price", "surcharge", "date_update"
    );

    public static final Table EMPLOYEES = new Table("employees", new String[]{
        ID, "first_name", "last_names", "employee_type",
        "status", "user", "password", "date_register", "end_date"
    });

    public static final Table SERVICE_PAYMENTS = new Table("service_payments", new String[]{
        "id", "employee", "user", "price", "month", "status", "date_register"
    });

    public static final Table SURCHARGE_PAYMENTS = new Table("surcharge_payments", new String[]{
        "id", "employee", "user", "price", "month", "status", "date_register"
    });

    public static final Table OTHER_PAYMENTS = new Table("other_payments", new String[]{
        "id", "employee", "user", "price", "month", "status", "date_register"
    });
}
