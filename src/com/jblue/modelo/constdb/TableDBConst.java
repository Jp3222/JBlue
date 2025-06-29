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
package com.jblue.modelo.constdb;

/**
 *
 * @author juanp
 */
interface TableDBConst extends HistoryConst, ArrayDBConst, ArrayGsConst {

    public static final Table STREETS = new Table(
            "street", STREET_FIELD, STREET_FIELD_GS
    );

    public static final Table WATER_INTAKES = new Table(
            "water_intakes_types", WATER_INTAKES_FIELD, WATER_INTAKES_FIELD_GS
    );
    public static final Table EMPLOYEES = new Table(
            "employees", EMPLOYEES_FIELD, EMPLOYEES_FIELD_GS
    );

    public static final Table USER = new Table(
            "users", USER_FIELD, USER_FIELD_GS
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
