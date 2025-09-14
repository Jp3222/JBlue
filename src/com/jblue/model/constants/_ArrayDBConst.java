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
public interface _ArrayDBConst {

    public static String[] CAT_GROUP_PREFIX_FIELDS = {
        "id",
        "prefix_type",
        "description",
        "date_update",
        "date_register",
        "date_end"
    };
    public static String[] CAT_HISTORY_TYPE_MOV_FIELDS = {
        "id",
        "description",
        "status",
        "date_register"
    };
    public static String[] CAT_PROCESS_TYPE_FIELDS = {
        "id",
        "type_name",
        "affected_table_id",
        "affected_table",
        "description",
        "status",
        "date_register"
    };
    public static String[] CAT_STATUS_FIELDS = {
        "id",
        "description",
        "affected_table",
        "date_update",
        "date_register",
        "date_finalize"
    };
    public static String[] CAT_STREET_FIELDS = {
        "id",
        "street_name",
        "status",
        "date_update",
        "date_register"
    };
    public static String[] CAT_TABLES_DB_FIELDS = {
        "id",
        "group_prefix",
        "table_name",
        "description",
        "user_who_creates",
        "user_who_creates_name",
        "status",
        "date_update",
        "date_register",
        "date_end"
    };
    public static String[] CAT_USER_MOVEMENTS_FIELDS = {
        "id",
        "type_mov",
        "group",
        "status",
        "date_update",
        "date_register"
    };
    public static String[] DEV_PARAMETERS_FIELDS = {
        "id",
        "parameter",
        "description",
        "value",
        "unit",
        "data_type",
        "status",
        "db_user_create",
        "db_user_update",
        "date_update",
        "date_register"
    };
    public static String[] EMP_EMPLOYEE_TYPES_FIELDS = {
        "id",
        "employee_type",
        "status",
        "date_register"
    };
    public static String[] EMP_EMPLOYEES_FIELDS = {
        "id",
        "curp",
        "first_name",
        "last_name1",
        "last_name2",
        "gender",
        "email",
        "date_birday",
        "phone_number1",
        "phone_number2",
        "employee_type",
        "status",
        "user",
        "password",
        "date_update",
        "date_register",
        "date_end"
    };
    public static String[] HYS_HISTORY_FIELDS = {
        "id",
        "affected_environment_id",
        "affected_environment_desc",
        "affected_table",
        "affected_table_description",
        "id_mov",
        "date_register"
    };
    public static String[] HYS_PROGRAM_HISTORY_FIELDS = {
        "id",
        "employee",
        "db_user",
        "affected_table",
        "type_mov",
        "description",
        "date_register"
    };
    public static String[] LOGBOOK_TO_PAYMENTS_FIELDS = {
        "id",
        "payment_id",
        "payment_type",
        "type_mov",
        "old_values",
        "new_values",
        "description",
        "db_user",
        "date_register"
    };
    public static String[] LOGBOOK_TO_USERS_FIELDS = {
        "id",
        "user_id",
        "type_mov",
        "old_values",
        "new_values",
        "description",
        "db_user",
        "date_register"
    };
    public static String[] LOGBOOK_TO_WATER_INTAKES_FIELDS = {
        "id",
        "water_intake_id",
        "user_id",
        "type_mov",
        "old_values",
        "new_values",
        "description",
        "db_user",
        "date_register"
    };
    public static String[] LOGBOOK_TO_WATER_INTAKES_TYPE_FIELDS = {
        "id",
        "water_intake_type_id",
        "type_mov",
        "old_values",
        "new_values",
        "description",
        "db_user",
        "date_register"
    };
    public static String[] PRO_CHANGE_OWNER_FIELDS = {
        "id",
        "price",
        "water_intake",
        "old_owner",
        "new_owner",
        "employee",
        "president",
        "description",
        "status",
        "date_register"
    };
    public static String[] PRO_PROCESS_FIELDS = {
        "id",
        "process_type",
        "employee_start",
        "date_start",
        "employee_valid",
        "date_valid",
        "employee_ends",
        "date_end",
        "president",
        "current_user",
        "original_user",
        "water_intake",
        "status",
        "date_register"
    };
    public static String[] PYM_OTHER_PAYMENTS_FIELDS = {
        "id",
        "employee",
        "user",
        "type_payment",
        "price",
        "status",
        "date_register",
        "date_payment"
    };
    public static String[] PYM_OTHER_PAYMENTS_TYPE_FIELDS = {
        "id",
        "type_payment",
        "description",
        "status",
        "files_path",
        "date_register",
        "date_end"
    };
    public static String[] PYM_PROCEDURE_PAYMENTS_FIELDS = {
        "id",
        "water_intake",
        "cost_total",
        "cost_operation",
        "cost_procedure",
        "cost_boot",
        "cost_clamp",
        "cost_stopcock"
    };
    public static String[] PYM_SERVICE_PAYMENTS_FIELDS = {
        "id",
        "employee",
        "user",
        "price",
        "month_name",
        "month",
        "status",
        "date_update",
        "date_register"
    };
    public static String[] PYM_SURCHARGE_PAYMENTS_FIELDS = {
        "id",
        "employee",
        "user",
        "price",
        "month_name",
        "month",
        "status",
        "date_update",
        "date_register"
    };
    public static String[] USR_USER_TYPE_FIELDS = {
        "id",
        "user_type",
        "status",
        "date_register",
        "date_finalize"
    };
    public static String[] USR_USERS_FIELDS = {
        "id",
        "curp",
        "first_name",
        "last_name1",
        "last_name2",
        "gender",
        "email",
        "phone_number1",
        "phone_number2",
        "street1",
        "street2",
        "inside_number",
        "outside_number",
        "water_intake_type",
        "user_type",
        "status",
        "date_last_update",
        "date_register"
    };
    public static String[] USR_USERS_CONSUMERS_FIELDS = {
        "id",
        "water_intake",
        "holder",
        "consumer",
        "status",
        "date_update",
        "date_register",
        "date_end"
    };
    public static String[] WKI_WATER_INTAKE_TYPE_FIELDS = {
        "id",
        "type_name",
        "current_price",
        "previous_price",
        "surcharge",
        "status",
        "date_update",
        "date_register",
        "date_end"
    };
    public static String[] WKI_WATER_INTAKES_FIELDS = {
        "id",
        "cost_procedure",
        "water_intake_type",
        "user",
        "street1",
        "street2",
        "location",
        "description",
        "status",
        "date_update",
        "date_register",
        "date_end"
    };
}
