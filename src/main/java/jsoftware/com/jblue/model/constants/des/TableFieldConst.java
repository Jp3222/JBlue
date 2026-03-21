/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.constants.des;

/**
 *
 * @author juanp
 */
public interface TableFieldConst {

    static final String[] BITACORA_DE_TABLAS_FIELDS = {"log_id", "fecha_movimiento", "tipo_operacion", "registro_id", "ejecutado_por", "resumen", "prefijo", "nombre_tabla", "descripcion_tabla", "estado_registro", "creador_original", "fecha_creacion_sistema", "json_anterior", "json_nuevo"};
    static final String[] CAT_COLONY_FIELDS = {"id", "description", "municipalities_id", "zip_code", "status", "date_update", "date_register", "date_end"};
    static final String[] CAT_ESTATUS_FIELDS = {"ID", "DESCRIPCION", "GRUPO AFECTADO", "FECHA DE ACTUALIZACION", "FECHA DE REGISTRO", "FECHA DE FINALIZACION"};
    static final String[] CAT_GROUP_PREFIX_FIELDS = {"id", "prefix_type", "description", "date_update", "date_register", "date_end"};
    static final String[] CAT_HISTORY_TYPE_MOV_FIELDS = {"id", "description", "status", "date_register"};
    static final String[] CAT_MODULE_FIELDS = {"id", "module_name", "description", "description_end", "status", "date_update", "date_register", "date_end"};
    static final String[] CAT_MUNICIPALITY_FIELDS = {"id", "description", "state_id", "status", "date_update", "date_register", "date_end"};
    static final String[] CAT_OFFICE_FIELDS = {"id", "description", "id_colony", "streed_id", "external_number", "internal_number", "email", "phone_number", "status", "date_update", "date_register", "date_end"};
    static final String[] CAT_PAYMENT_METHOD_FIELDS = {"id", "description", "code", "status", "date_register", "date_end"};
    static final String[] CAT_PAYMENT_TYPE_FIELDS = {"id", "type_payment", "description", "description_long", "status", "date_update", "date_register", "date_end"};
    static final String[] CAT_PROCESS_TYPE_FIELDS = {"id", "type_name", "description", "status", "date_register", "date_update", "date_end"};
    static final String[] CAT_STATES_FIELDS = {"id", "description", "status", "date_update", "date_register", "date_end"};
    static final String[] CAT_STATUS_FIELDS = {"id", "description", "affected_group", "affected_table", "date_update", "date_register", "date_finalize"};
    static final String[] CAT_STREET_FIELDS = {"id", "street_name", "colony_id", "status", "date_update", "date_register", "date_end"};
    static final String[] CAT_TABLES_DB_FIELDS = {"id", "group_prefix", "table_name", "description", "update_description", "end_description", "user_who_creates", "user_who_creates_name", "status", "date_update", "date_register", "date_end"};
    static final String[] CAT_UNIT_MEASURE_FIELDS = {"id", "unit_name", "unit_symbol", "fiscal_code", "magnitude_type", "base_factor", "is_base", "status", "date_register"};
    static final String[] CAT_USER_CATEGORY_DOCUMENTS_FIELDS = {"id", "description", "description_long", "date_register"};
    static final String[] CAT_USER_MOVEMENTS_FIELDS = {"id", "type_mov", "group", "status", "date_update", "date_register"};
    static final String[] CAT_USER_TYPE_DOCUMENT_FIELDS = {"id", "description", "description_long", "category", "date_update", "date_register", "date_end"};
    static final String[] DEV_PARAMETERS_FIELDS = {"id", "parameter", "description", "value", "unit", "data_type", "status", "db_user_create", "db_user_update", "last_update employee", "date_update", "date_register", "date_end"};
    static final String[] EMP_EMPLOYEE_FIELDS = {"id", "curp", "first_name", "last_name1", "last_name2", "gender", "birthdate", "personal_email", "personal_phone", "street1", "street2", "inside_number", "outside_number", "employee_type", "status", "user", "password", "date_update", "date_register", "date_end"};
    static final String[] EMP_EMPLOYEE_PERMISSIONS_FIELDS = {"id", "employee_id", "process_type_id", "profile_id", "description", "status", "date_update", "date_register", "date_end"};
    static final String[] EMP_EMPLOYEE_PROFILE_FIELDS = {"id", "description", "description_long", "status", "date_update", "date_register", "date_end"};
    static final String[] EMP_EMPLOYEE_TYPE_FIELDS = {"id", "employee_type", "status", "date_register"};
    static final String[] EMP_USER_FIELDS = {"id", "employee_id", "office_id", "user", "password", "description", "email", "phone_number", "employee_type", "status", "last_employee_update", "last_update_password", "date_update", "date_register", "date_end"};
    static final String[] ENTIDADES_REGISTRADAS_FIELDS = {"ID_ESTADO", "ESTATDO", "ID_MUNICIPIO", "MUNICIPIO", "ID_COLONIA", "COLONIA", "CODIGO POSTAL", "ESTATUS", "GRUPO AFECTADO", "FECHA_ACTUALIZACION", "FECHA_REGISTRO", "FECHA_FIN"};
    static final String[] HISTORIAL_DE_MOVIMIENTOS_FIELDS = {"ID", "EMPLEADO", "USUARIO DB", "TABLA AFECTADA", "TIPO DE MOVIMIENTO", "DESCRIPCION", "FECHA DE REGISTRO"};
    static final String[] HYS_ADMINISTRATION_HISTORY_FIELDS = {"id", "year_of_administration", "root", "administrator", "president", "treasurer", "secretary", "plumber", "intern_1", "intern_2", "internt_3", "description", "status", "date_update", "date_register", "date_end"};
    static final String[] HYS_HISTORY_FIELDS = {"id", "db_user", "affected_environment_id", "affected_environment_desc", "trigger_table", "affected_table", "type_mov", "enty_id", "date_register"};
    static final String[] HYS_PROGRAM_HISTORY_FIELDS = {"id", "employee", "db_user", "affected_table", "type_mov", "description", "date_register"};
    static final String[] HYS_SESSION_FIELDS = {"id", "employee_id", "history_id", "date_in", "date_out", "status", "date_register"};
    static final String[] LOGBOOK_TO_EMPLOYEE_FIELDS = {"id", "enty_id", "employee_update", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_GROUP_PREFIX_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_PARAMETERS_FIELDS = {"id", "enty_id", "employee_update", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_PAYMENT_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_PAYMENT_CONCEPT_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_PAYMENT_TYPE_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_PROCESS_TYPE_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_TABLE_DB_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_USER_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_WATER_INTAKE_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] LOGBOOK_TO_WATER_INTAKES_TYPE_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    static final String[] PRO_PROCESS_FIELDS = {"id", "process_type", "employee_start", "date_start", "employee_valid", "date_valid", "employee_wki_register", "date_wki_register", "employee_payment", "date_payment", "employee_finalize", "date_finalize", "employe_print", "date_print", "administration_start", "administration_end", "last_employee_update", "current_db_user", "user_id", "water_intake", "status", "date_update", "date_register", "date_end"};
    static final String[] PYM_PAYMENT_FIELDS = {"id", "payment_type", "uudi", "employee", "user", "water_intake", "water_intake_type", "payment_method", "payment_concept", "total_cost", "amount_paid", "change_amount", "month_paid", "status", "date_update", "date_register", "date_end"};
    static final String[] PYM_PAYMENT_CONCEPT_FIELDS = {"id", "description", "description_long", "current_price", "previous_price", "mandatory_payment", "unit", "module", "status", "date_update", "date_register", "date_end"};
    static final String[] PYM_PAYMENT_LIST_FIELDS = {"id", "payment_id", "payment_concept_id", "description", "quantity", "unit_id", "unit_price", "total_price", "status", "date_register"};
    static final String[] SYS_INSTANCE_AUTH_FIELDS = {"id", "uuid", "office_sys_name", "colony_id", "municipality_id", "state_id", "office_id", "master_user", "password_user", "owner_name", "db_user", "status", "date_update", "date_register", "date_end"};
    static final String[] USR_BLOCKED_USER_FIELDS = {"id", "user", "description", "status", "date_update", "date_register", "date_end"};
    static final String[] USR_USER_FIELDS = {"id", "curp", "first_name", "last_name1", "last_name2", "gender", "email", "birthdate", "phone_number1", "phone_number2", "street1", "street2", "inside_number", "outside_number", "user_type", "last_employee_update", "status", "date_update", "date_register", "date_end"};
    static final String[] USR_USER_DOCUMENT_FIELDS = {"id", "user", "document_name", "document_path", "doc_file", "document_type_id", "status", "date_register", "date_end"};
    static final String[] USR_USER_TYPE_FIELDS = {"id", "user_type", "description_long", "status", "date_update", "date_register", "date_finalize"};
    static final String[] USR_USERS_CONSUMERS_FIELDS = {"id", "water_intake", "holder", "consumer", "status", "date_update", "date_register", "date_end"};
    static final String[] WKI_USER_FIELDS = {"id", "user_id", "water_intake_id", "water_intake_type_id", "owner_name", "description", "employee_register", "last_employee_update", "original_process", "last_process_type", "status", "date_update", "date_register", "date_end"};
    static final String[] WKI_WATER_INTAKE_TYPE_FIELDS = {"id", "type_name", "current_price", "previous_price", "surcharge", "status", "last_employee_update", "date_update", "date_register", "date_end"};
    static final String[] WKI_WATER_INTAKES_FIELDS = {"id", "serial_number", "water_intake_type", "street1", "street2", "description", "status", "last_employee_update", "date_update", "date_register", "date_end"};
}
