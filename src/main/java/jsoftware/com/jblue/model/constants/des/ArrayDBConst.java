/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.constants.des;

/**
 *
 * @author juanp
 */
public class ArrayDBConst {

    protected static String[] CAT_GROUP_PREFIX_FIELDS = {"id", "prefix_type", "description", "date_update", "date_register", "date_end"};
    protected static String[] CAT_HISTORY_TYPE_MOV_FIELDS = {"id", "description", "status", "date_register"};
    protected static String[] CAT_PAYMENT_METHOD_FIELDS = {"id", "description", "code", "status", "date_register", "date_end"};
    protected static String[] CAT_PAYMENT_TYPE_FIELDS = {"id", "description", "description_long", "status", "date_update", "date_register", "date_end"};
    protected static String[] CAT_PROCESS_TYPE_FIELDS = {"id", "type_name", "description", "status", "date_register", "date_update", "date_end"};
    protected static String[] CAT_STATUS_FIELDS = {"id", "description", "affected_group", "date_update", "date_register", "date_finalize"};
    protected static String[] CAT_STREET_FIELDS = {"id", "street_name", "status", "date_update", "date_register", "date_end"};
    protected static String[] CAT_TABLES_DB_FIELDS = {"id", "group_prefix", "table_name", "description", "user_who_creates", "user_who_creates_name", "status", "date_update", "date_register", "date_end"};
    protected static String[] CAT_USER_CATEGORY_DOCUMENTS_FIELDS = {"id", "type_document", "description", "date_register"};
    protected static String[] CAT_USER_MOVEMENTS_FIELDS = {"id", "type_mov", "group", "status", "date_update", "date_register"};
    protected static String[] CAT_USER_TYPE_DOCUMENT_FIELDS = {"id", "user_document", "description", "type_document", "date_update", "date_register", "date_end"};
    protected static String[] DEV_PARAMETERS_FIELDS = {"id", "parameter", "description", "value", "unit", "data_type", "status", "db_user_create", "db_user_update", "date_update", "date_register"};
    protected static String[] EMP_EMPLOYEE_FIELDS = {"id", "curp", "first_name", "last_name1", "last_name2", "gender", "email", "date_birday", "phone_number1", "phone_number2", "employee_type", "status", "user", "password", "date_update", "date_register", "date_end"};
    protected static String[] EMP_EMPLOYEE_TYPE_FIELDS = {"id", "employee_type", "status", "date_register"};
    protected static String[] HYS_ADMINISTRATION_HISTORY_FIELDS = {"id", "year_of_administration", "root", "administrator", "president", "treasurer", "secretary", "plumber", "intern_1", "intern_2", "internt_3", "description", "status", "date_update", "date_register", "date_end"};
    protected static String[] HYS_HISTORY_FIELDS = {"id", "affected_environment_id", "affected_environment_desc", "affected_table", "affected_table_description", "id_mov", "date_register"};
    protected static String[] HYS_PROGRAM_HISTORY_FIELDS = {"id", "employee", "db_user", "affected_table", "type_mov", "description", "date_register"};
    protected static String[] LOGBOOK_TO_EMPLOYEE_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    protected static String[] LOGBOOK_TO_PARAMETES_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    protected static String[] LOGBOOK_TO_PAYMENT_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    protected static String[] LOGBOOK_TO_USER_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    protected static String[] LOGBOOK_TO_WATER_INTAKE_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    protected static String[] LOGBOOK_TO_WATER_INTAKES_TYPE_FIELDS = {"id", "enty_id", "type_mov", "old_values", "new_values", "description", "db_user", "date_register"};
    protected static String[] PRO_PROCESS_FIELDS = {"id", "process_type", "employee_start", "date_start", "employee_valid", "date_valid", "employee_payment", "date_payment", "employee_ends", "date_end", "employe_print", "date_print", "administration", "last_employee_update", "current_db_user", "original_user", "water_intake", "status", "date_register"};
    protected static String[] PYM_PAYMENTS_FIELDS = {"id", "payment_type", "uudi", "employee", "user", "water_intake", "water_intake_type", "payment_method", "total_cost", "amount_paid", "change_amount", "month_paid", "status", "date_update", "date_register", "date_end"};
    protected static String[] USR_BLOCKED_USER_FIELDS = {"id", "user", "description", "status", "date_update", "date_register", "date_end"};
    protected static String[] USR_USER_FIELDS = {"id", "curp", "first_name", "last_name1", "last_name2", "gender", "email", "phone_number1", "phone_number2", "street1", "street2", "inside_number", "outside_number", "water_intake_type", "user_type", "status", "last_employee_update", "date_last_update", "date_register", "date_end"};
    protected static String[] USR_USER_DOCUMENT_FIELDS = {"id", "user", "document_name", "document_path", "doc_file", "status", "date_register", "date_end"};
    protected static String[] USR_USER_TYPE_FIELDS = {"id", "user_type", "status", "date_register", "date_finalize"};
    protected static String[] USR_USERS_CONSUMERS_FIELDS = {"id", "water_intake", "holder", "consumer", "status", "date_update", "date_register", "date_end"};
    protected static String[] WKI_WATER_INTAKE_TYPE_FIELDS = {"id", "type_name", "current_price", "previous_price", "surcharge", "status", "date_update", "date_register", "date_end"};
    protected static String[] WKI_WATER_INTAKES_FIELDS = {"id", "cost_procedure", "water_intake_type", "user", "user_name", "street1", "street2", "location", "description", "status", "date_update", "date_register", "date_end"};
}
