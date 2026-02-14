/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.querys;

/**
 *
 * @author juanp
 */
public class WaterIntakeUserQuery {

    // Paso A: Desactivar al titular actual (Solo si está activo/vigente)
    public static final String UPDATE_OLD_OWNER = """
    UPDATE wki_user 
    SET status = ?, 
        last_employee_update = ?, 
        last_process_type = ?, 
        date_end = CURRENT_TIMESTAMP 
    WHERE id = ? 
    AND status NOT IN (3, 6);
    """;

// Paso B: El INSERT se mantiene igual ya que genera un nuevo registro
    public static final String INSERT_NEW_OWNER = """
    INSERT INTO wki_user (
        user_id, water_intake_id, description, notes, 
        employee_register, last_employee_update, original_process, 
        last_process_type, status
    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
    """;
}
