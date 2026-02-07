/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 *
 * @author juanp
 */
public class WaterIntakeUserDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public WaterIntakeUserDTO() {
        super(15);
    }

    public String getUserId() {
        return get("user_id").toString();
    }

    public String getWaterIntakeId() {
        return get("water_intake_id").toString();
    }

    public String getDescription() {
        return get("description").toString();
    }

    public String getNotes() {
        return get("notes").toString();
    }

    public String getEmployeRegister() {
        return get("employee_register").toString();
    }

    public String getLastUpdateEmployee() {
        return get("last_update_employee").toString();
    }

    public String getOriginalProcessId() {
        return get("original_process").toString();
    }

    public String getLastProcessType() {
        return get("last_process_type").toString();
    }

}
