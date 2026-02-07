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

    static final String[] BITACORA_DE_TABLAS_FIELDS = {"creador_original", "descripcion_tabla", "ejecutado_por", "estado_registro", "fecha_creacion_sistema", "fecha_movimiento", "json_anterior", "json_nuevo", "log_id", "nombre_tabla", "prefijo", "registro_id", "resumen", "tipo_operacion"};
    static final String[] CAT_GROUP_PREFIX_FIELDS = {"date_end", "date_register", "date_update", "description", "id", "prefix_type"};
    static final String[] CAT_HISTORY_TYPE_MOV_FIELDS = {"date_register", "description", "id", "status"};
    static final String[] CAT_MODULE_FIELDS = {"date_end", "date_register", "date_update", "description", "description_end", "id", "module_name", "status"};
    static final String[] CAT_PAYMENT_METHOD_FIELDS = {"code", "date_end", "date_register", "description", "id", "status"};
    static final String[] CAT_PAYMENT_TYPE_FIELDS = {"date_end", "date_register", "date_update", "description", "description_long", "id", "status", "type_payment"};
    static final String[] CAT_PROCESS_TYPE_FIELDS = {"date_end", "date_register", "date_update", "description", "id", "status", "type_name"};
    static final String[] CAT_STATUS_FIELDS = {"affected_group", "affected_table", "date_finalize", "date_register", "date_update", "description", "id"};
    static final String[] CAT_STREET_FIELDS = {"date_end", "date_register", "date_update", "id", "status", "street_name"};
    static final String[] CAT_TABLES_DB_FIELDS = {"date_end", "date_register", "date_update", "description", "end_description", "group_prefix", "id", "status", "table_name", "update_description", "user_who_creates", "user_who_creates_name"};
    static final String[] CAT_USER_CATEGORY_DOCUMENTS_FIELDS = {"date_register", "description", "id", "type_document"};
    static final String[] CAT_USER_MOVEMENTS_FIELDS = {"date_register", "date_update", "group", "id", "status", "type_mov"};
    static final String[] CAT_USER_TYPE_DOCUMENT_FIELDS = {"date_end", "date_register", "date_update", "description", "id", "type_document", "user_document"};
    static final String[] DEV_PARAMETERS_FIELDS = {"data_type", "date_register", "date_update", "db_user_create", "db_user_update", "description", "id", "parameter", "status", "unit", "value"};
    static final String[] EMP_EMPLOYEE_FIELDS = {"birthdate", "curp", "date_end", "date_register", "date_update", "email", "employee_type", "first_name", "gender", "id", "last_name1", "last_name2", "password", "phone_number1", "phone_number2", "status", "user"};
    static final String[] EMP_EMPLOYEE_PERMISSIONS_FIELDS = {"date_end", "date_register", "date_update", "description", "employee_id", "id", "process_type_id", "profile_id", "status"};
    static final String[] EMP_EMPLOYEE_PROFILE_FIELDS = {"date_end", "date_register", "date_update", "description", "description_long", "id", "status"};
    static final String[] EMP_EMPLOYEE_TYPE_FIELDS = {"date_register", "employee_type", "id", "status"};
    static final String[] EMP_USER_FIELDS = {"date_end", "date_register", "date_update", "employee_id", "id", "status", "user_id"};
    static final String[] HISTORIAL_DE_MOVIMIENTOS_FIELDS = {"DESCRIPCION", "EMPLEADO", "FECHA DE REGISTRO", "ID", "TABLA AFECTADA", "TIPO DE MOVIMIENTO", "USUARIO DB"};
    static final String[] HYS_ADMINISTRATION_HISTORY_FIELDS = {"administrator", "date_end", "date_register", "date_update", "description", "id", "intern_1", "intern_2", "internt_3", "plumber", "president", "root", "secretary", "status", "treasurer", "year_of_administration"};
    static final String[] HYS_HISTORY_FIELDS = {"affected_environment_desc", "affected_environment_id", "affected_table", "date_register", "db_user", "enty_id", "id", "trigger_table", "type_mov"};
    static final String[] HYS_PROGRAM_HISTORY_FIELDS = {"affected_table", "date_register", "db_user", "description", "employee", "id", "type_mov"};
    static final String[] LOGBOOK_TO_EMPLOYEE_FIELDS = {"date_register", "db_user", "description", "employee_update", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_GROUP_PREFIX_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_PARAMETERS_FIELDS = {"date_register", "db_user", "description", "employee_update", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_PAYMENT_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_PAYMENT_TYPE_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_PROCESS_TYPE_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_TABLE_DB_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_USER_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_WATER_INTAKE_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOGBOOK_TO_WATER_INTAKES_TYPE_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] LOOGBOOK_TO_PAYMENT_CONCEPT_FIELDS = {"date_register", "db_user", "description", "enty_id", "id", "new_values", "old_values", "type_mov"};
    static final String[] NEW_VIEW_FIELDS = {"DESCRIPCION", "FECHA DE ACTUALIZACION", "FECHA DE FINALIZACION", "FECHA DE REGISTRO", "GRUPO AFECTADO", "ID"};
    static final String[] PRO_PROCESS_FIELDS = {"administration", "current_db_user", "date_end", "date_finalize", "date_payment", "date_print", "date_register", "date_start", "date_update", "date_valid", "employe_print", "employee_finalize", "employee_payment", "employee_start", "employee_valid", "id", "last_employee_update", "original_user", "process_type", "status", "water_intake"};
    static final String[] PYM_PAYMENT_CONCEPT_FIELDS = {"current_price", "date_end", "date_register", "date_update", "description", "description_long", "id", "mandatory_payment", "module", "previous_price", "status"};
    static final String[] PYM_PAYMENT_LIST_FIELDS = {"cost", "date_register", "id", "item_name", "payment", "payment_concept", "status"};
    static final String[] PYM_PAYMENTS_FIELDS = {"amount_paid", "change_amount", "date_end", "date_register", "date_update", "employee", "id", "month_paid", "payment_concept", "payment_method", "payment_type", "status", "total_cost", "user", "uudi", "water_intake", "water_intake_type"};
    static final String[] USR_BLOCKED_USER_FIELDS = {"date_end", "date_register", "date_update", "description", "id", "status", "user"};
    static final String[] USR_USER_FIELDS = {"birthdate", "curp", "date_end", "date_register", "date_update", "email", "first_name", "gender", "id", "inside_number", "last_employee_update", "last_name1", "last_name2", "outside_number", "phone_number1", "phone_number2", "status", "street1", "street2", "user_type"};
    static final String[] USR_USER_DOCUMENT_FIELDS = {"date_end", "date_register", "doc_file", "document_name", "document_path", "id", "status", "user"};
    static final String[] USR_USER_TYPE_FIELDS = {"date_finalize", "date_register", "date_update", "description_long", "id", "status", "user_type"};
    static final String[] USR_USERS_CONSUMERS_FIELDS = {"consumer", "date_end", "date_register", "date_update", "holder", "id", "status", "water_intake"};
    static final String[] WKI_USER_FIELDS = {"date_end", "date_register", "date_update", "description", "employee_register", "id", "last_employee_update", "last_process_type", "notes", "status", "user_id", "water_intake_id", "water_intake_type_id"};
    static final String[] WKI_WATER_INTAKE_TYPE_FIELDS = {"current_price", "date_end", "date_register", "date_update", "id", "last_employee_update", "previous_price", "status", "surcharge", "type_name"};
    static final String[] WKI_WATER_INTAKES_FIELDS = {"cost_procedure", "date_end", "date_register", "date_update", "description", "id", "last_employee_update", "location", "status", "street1", "street2", "user", "user_name", "water_intake_type"};
}
