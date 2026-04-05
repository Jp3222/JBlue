/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import java.util.Objects;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;

/**
 *
 * @author juanp
 */
public class EmployeeRegisterWrapperDTO extends ModuleWrapperDTO {

    private static final long serialVersionUID = 1L;

    private EmployeeDTO employee;
    private EmployeeUserDTO employee_user;

    public EmployeeRegisterWrapperDTO(String module_id, String module_name) {
        super(module_id, module_name);
        this.employee = new EmployeeDTO();
        this.employee_user = new EmployeeUserDTO();
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public EmployeeUserDTO getEmployee_user() {
        return employee_user;
    }

    public void setEmployee_user(EmployeeUserDTO employee_user) {
        this.employee_user = employee_user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.employee);
        hash = 37 * hash + Objects.hashCode(this.employee_user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeRegisterWrapperDTO other = (EmployeeRegisterWrapperDTO) obj;
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        return Objects.equals(this.employee_user, other.employee_user);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append("EmployeeRegisterWrapperDTO {");
        sb.append("employee = ").append(employee.getMap().toString());
        sb.append(", employee_user = ").append(employee_user.getMap().toString());
        sb.append('}');
        return sb.toString();
    }

}
