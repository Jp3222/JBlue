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

    public EmployeeRegisterWrapperDTO() {
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
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.employee);
        hash = 29 * hash + Objects.hashCode(this.employee_user);
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
        StringBuilder sb = new StringBuilder(600);
        sb.append("EmployeeDTO: ").append(employee.toString()).append("\n");
        sb.append("EmployeeUserDTO: ").append(employee_user.toString()).append("\n");
        sb.append("Module: ").append(values.toString()).append("\n");
        return sb.toString();
    }

}
