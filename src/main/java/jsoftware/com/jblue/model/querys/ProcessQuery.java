/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.querys;

/**
 *
 * @author juanp
 */
public class ProcessQuery {

    /**
     *
     */
    public static final String INSERT_START_PROCESS = """
                                                      INSERT INTO pro_process(
                                                            process_type, 
                                                            employee_start,
                                                            administration, 
                                                            last_employee_update, 
                                                            current_db_user
                                                            status
                                                            ) VALUES(?,?,?,?,?,?)"
                                                    """;

    public static final String UPDATE_PROCESS_VALID = """
                                                UPDATE pro_process SET 
                                                    employee_valid = ?,
                                                    date_valid = CURRENT_TIMESTAMP,
                                                    administration = ?,
                                                    last_employee_update = ?, 
                                                    current_db_user = ?, 
                                                    status = ?
                                                WHERE id = ? 
                                                """;
    public static final String UPDATE_PROCESS_PAYMENT = """
                                                UPDATE pro_process SET 
                                                    employee_payment = ?,
                                                    date_payment = CURRENT_TIMESTAMP,
                                                    administration = ?,
                                                    last_employee_update = ?, 
                                                    current_db_user = ?, 
                                                    status = ?
                                                WHERE id = ? 
                                                """;

    public static final String UPDATE_PROCESS_END = """
                                                UPDATE pro_process SET 
                                                    employee_finalize = ?,
                                                    date_finalize = CURRENT_TIMESTAMP,
                                                    administration = ?,
                                                    last_employee_update = ?, 
                                                    current_db_user = ?, 
                                                    origina_user = ?,
                                                    water_intake = ?,
                                                    status = ?
                                                WHERE id = ? 
                                                """;

    public static final String UPDATE_PROCESS_PRINT = """
                                                UPDATE pro_process SET 
                                                    employee_print = ?,
                                                    date_print = CURRENT_TIMESTAMP,
                                                    last_employee_update = ?, 
                                                    current_db_user = ?, 
                                                    status =     ?
                                                WHERE id = ? 
                                                """;

    public static final String UPDATE_PROCESS_CADUCATE = """
                                                    UPDATE pro_process SET 
                                                        last_employee_update = ?, 
                                                        current_db_user = ?, 
                                                        status = ?,
                                                        date_end = CURRENT_TIMESTAMP;
                                                    WHERE id = ? 
                                                         """;
    public static final String UPDATE_PROCESS_CANCEL = """
                                                    UPDATE pro_process SET
                                                        last_emeployee_update = ?,
                                                        current_db_user,
                                                        status = ?,
                                                        date_end = CURRENT_TIMESTAMP
                                                       WHERE id = ?
                                                       """;

}
