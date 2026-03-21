/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 *
 * @author juanp
 */
public class EmployeeUserDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public EmployeeUserDTO() {
        super(15);
    }

    public String getEmployeeId() {
        return get("employee_id").toString();
    }

    public String getOfficeId() {
        return get("office_id").toString();
    }

    public String getUser() {
        return get("user").toString();
    }

    public String getPassword() {
        return get("password").toString();
    }

    public String getDescription() {
        return get("description").toString();
    }

    public String getEmail() {
        return get("email").toString();
    }

    public String getPhoneNumber() {
        return get("phone_number").toString();
    }

    public String getEmployeeType() {
        return get("employee_type").toString();
    }

    public String getLastEmployeeUpdate() {
        return get("last_update_employee").toString();
    }

    public String getLastUpdatePassword() {
        return get("last_update_password").toString();
    }

    @Override
    public String toString() {
        return values.toString();
    }
    
    
}
