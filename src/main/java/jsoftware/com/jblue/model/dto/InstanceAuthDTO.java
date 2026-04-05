/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 *
 * @author juanp
 */
public class InstanceAuthDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public InstanceAuthDTO() {
        super(20);
    }

    public String getUUID() {
        return get("uuid").toString();
    }

    public String getOfficeSysName() {
        return get("office_sys_name").toString();
    }

    public String getColonyId() {
        return get("colony_id").toString();
    }

    public String getMunicipalityId() {
        return get("municipality_id").toString();
    }

    public String getStateId() {
        return get("state_id").toString();
    }

    public String getOfficeId() {
        return get("office_id").toString();
    }

    public String getMasterUser() {
        return get("master_user").toString();
    }

    public String getPasswordUser() {
        return get("password_user").toString();
    }

    public String getOwnerName() {
        return get("owner_name").toString();
    }

    public String getDbUser() {
        return get("db_user").toString();
    }

    public String getEmployeeDefault() {
        return get("employee_default").toString();
    }
}
