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
interface TableDBConst extends HistoryConst, ArrayDBConst, ArrayGsConst {

    // Constantes individuales (tus variables originales)
    public static final String TABLE_ALL_DATA_USER = "all_data_user";
    public static final String TABLE_CONTRACT_PROCEDURE = "contract_procedure";
    public static final String TABLE_EMPLOYEE_TYPES = "employee_types";
    public static final String TABLE_EMPLOYEES = "employees";
    public static final String TABLE_GENERAL_HISTORY = "general_history";
    public static final String TABLE_HISTORY = "history";
    public static final String TABLE_HISTORY_TYPE_MOV = "history_type_mov";
    public static final String TABLE_ITEMS_STATUS = "items_status";
    public static final String TABLE_LOGBOOK_TO_PAYMENTS = "logbook_to_payments";
    public static final String TABLE_LOGBOOK_TO_USERS = "logbook_to_users";
    public static final String TABLE_NEW_VIEW = "new_view";
    public static final String TABLE_OTHER_PAYMENTS = "other_payments";
    public static final String TABLE_OTHER_TYPE_PAYMENTS = "other_type_payments";
    public static final String TABLE_PARAMETERS = "parameters";
    public static final String TABLE_PROCEDURE_PAYMENTS = "procedure_payments";
    public static final String TABLE_SERVICE_PAYMENTS = "service_payments";
    public static final String TABLE_STREET = "street";
    public static final String TABLE_SURCHARGE_PAYMENTS = "surcharge_payments";
    public static final String TABLE_CONSUMERS = "user_consumers";
    public static final String TABLE_USER_MOVEMENTS = "user_movements";
    public static final String TABLE_USER_TYPE = "user_type";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_USERS_PAY = "users_pay";
    public static final String TABLE_WATER_INTAKES = "water_intakes";
    public static final String TABLE_WATER_INTAKES_TYPES = "water_intakes_types";

    // Arreglo que contiene todas las variables anteriores
    public static final String[] ALL_TABLES = {
        TABLE_ALL_DATA_USER,
        TABLE_CONSUMERS,
        TABLE_CONTRACT_PROCEDURE,
        TABLE_EMPLOYEE_TYPES,
        TABLE_EMPLOYEES,
        TABLE_GENERAL_HISTORY,
        TABLE_HISTORY,
        TABLE_HISTORY_TYPE_MOV,
        TABLE_ITEMS_STATUS,
        TABLE_LOGBOOK_TO_PAYMENTS,
        TABLE_LOGBOOK_TO_USERS,
        TABLE_NEW_VIEW,
        TABLE_OTHER_PAYMENTS,
        TABLE_OTHER_TYPE_PAYMENTS,
        TABLE_PARAMETERS,
        TABLE_PROCEDURE_PAYMENTS,
        TABLE_SERVICE_PAYMENTS,
        TABLE_STREET,
        TABLE_SURCHARGE_PAYMENTS,
        TABLE_USER_MOVEMENTS,
        TABLE_USER_TYPE,
        TABLE_USERS,
        TABLE_USERS_PAY,
        TABLE_WATER_INTAKES,
        TABLE_WATER_INTAKES_TYPES
    };

//    public static final Table ALL_DATA_USER = new Table(TABLE_ALL_DATA_USER, ALL_DATA_USER_FIELD, ALL_DATA_USER_FIELD_GS);
//    public static final Table CONSUMERS = new Table(TABLE_CONSUMERS, CONSUMERS_FIELD, CONSUMERS_FIELD_GS);
//    public static final Table CONTRACT_PROCEDURE = new Table(TABLE_CONTRACT_PROCEDURE, CONTRACT_PROCEDURE_FIELD, CONTRACT_PROCEDURE_FIELD_GS);
//    public static final Table EMPLOYEE_TYPES = new Table(TABLE_EMPLOYEE_TYPES, EMPLOYEE_TYPES_FIELD, EMPLOYEE_TYPES_FIELD_GS);
//    public static final Table GENERAL_HISTORY = new Table(TABLE_GENERAL_HISTORY, GENERAL_HISTORY_FIELD, GENERAL_HISTORY_FIELD_GS);
//    public static final Table HISTORY = new Table(TABLE_HISTORY, HISTORY_FIELD, HISTORY_FIELD_GS);
//    public static final Table HISTORY_TYPE_MOV = new Table(TABLE_HISTORY_TYPE_MOV, HISTORY_TYPE_MOV_FIELD, HISTORY_TYPE_MOV_FIELD_GS);
//    public static final Table ITEMS_STATUS = new Table(TABLE_ITEMS_STATUS, ITEMS_STATUS_FIELD, ITEMS_STATUS_FIELD_GS);
//    public static final Table LOGBOOK_TO_PAYMENTS = new Table(TABLE_LOGBOOK_TO_PAYMENTS, LOGBOOK_TO_PAYMENTS_FIELD, LOGBOOK_TO_PAYMENTS_FIELD_GS);
//    public static final Table LOGBOOK_TO_USERS = new Table(TABLE_LOGBOOK_TO_USERS, LOGBOOK_TO_USERS_FIELD, LOGBOOK_TO_USERS_FIELD_GS);
//    public static final Table NEW_VIEW = new Table(TABLE_NEW_VIEW, NEW_VIEW_FIELD, NEW_VIEW_FIELD_GS);
//    public static final Table OTHER_TYPE_PAYMENTS = new Table(TABLE_OTHER_TYPE_PAYMENTS, OTHER_TYPE_PAYMENTS_FIELD, OTHER_TYPE_PAYMENTS_FIELD_GS);
//    public static final Table PARAMETERS = new Table(TABLE_PARAMETERS, PARAMETERS_FIELD, PARAMETERS_FIELD_GS);
//    public static final Table PROCEDURE_PAYMENTS = new Table(TABLE_PROCEDURE_PAYMENTS, PROCEDURE_PAYMENTS_FIELD, PROCEDURE_PAYMENTS_FIELD_GS);
//    public static final Table USER_MOVEMENTS = new Table(TABLE_USER_MOVEMENTS, USER_MOVEMENTS_FIELD, USER_MOVEMENTS_FIELD_GS);
//    public static final Table USER_TYPE = new Table(TABLE_USER_TYPE, USER_TYPE_FIELD, USER_TYPE_FIELD_GS);
//    public static final Table USERS_PAY = new Table(TABLE_USERS_PAY, USERS_PAY_FIELD, USERS_PAY_FIELD_GS);
    // Las siguientes tablas ya las habías definido en tu código, solo las incluyo por completitud
    public static final Table STREETS = new Table("street", STREETS_FIELD, STREETS_FIELD_GS);
    public static final Table WATER_INTAKES_TYPES = new Table("water_intakes_types", WATER_INTAKES_TYPES_FIELD, WATER_INTAKES_TYPES_FIELD_GS);
    public static final Table WATER_INTAKES = new Table("water_intakes", WATER_INTAKES_FIELD, WATER_INTAKES_FIELD_GS);
    public static final Table EMPLOYEES = new Table("employees", EMPLOYEES_FIELD, EMPLOYEES_FIELD_GS);
    public static final Table USERS = new Table("users", USERS_FIELD, USERS_FIELD_GS);
    public static final Table SERVICE_PAYMENTS = new Table("service_payments", SERVICE_PAYMENTS_FIELD, SERVICE_PAYMENTS_FIELD_GS);
    public static final Table SURCHARGE_PAYMENTS = new Table("surcharge_payments", SURCHARGE_PAYMENTS_FIELD, SURCHARGE_PAYMENTS_FIELD_GS);
    public static final Table OTHER_PAYMENTS = new Table("other_payments", OTHER_PAYMENTS_FIELD, OTHER_PAYMENTS_FIELD_GS);
    public static final Table EMPLOYEES_TYPES = new Table("employee_types", EMPLOYEES_TYPES_FIELD, EMPLOYEES_TYPES_FIELD_GS);

}
