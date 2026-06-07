package jsoftware.com.jblue.model.dto.wrp;

import java.util.Objects;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;

/**
 * Wrapper especializado para el contexto del caso de uso: Registro de
 * Empleados. Encapsula la información civil, credenciales de usuario del
 * sistema y sus estados de validación.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-07
 * @version 1.0
 */
public class EmployeeRegisterWrapperDTO extends ModuleWrapperDTO {

    private static final long serialVersionUID = 1L;

    private EmployeeDTO employee;
    private boolean employee_valid;

    private EmployeeUserDTO employee_user;
    private boolean employee_user_valid;

    public EmployeeRegisterWrapperDTO(String module_id, String module_name) {
        // Inicializa el contexto macro en el padre con tipo de movimiento "1" e indicación de auditoría
        super(module_id, module_name, "1", "REGISTRO DE EMPLEADO POR MODULO DEL SISTEMA");
        // Corrección: Acceso seguro a la transacción mediante el método getter público
        // Setea el ID 51 correspondiente al catálogo de la tabla afectada de empleados
        getTransaction().put("affected_table", "51");
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

    public boolean isEmployee_valid() {
        return employee_valid;
    }

    public void setEmployee_valid(boolean employee_valid) {
        this.employee_valid = employee_valid;
    }

    public boolean isEmployee_user_valid() {
        return employee_user_valid;
    }

    public void setEmployee_user_valid(boolean employee_user_valid) {
        this.employee_user_valid = employee_user_valid;
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
        if (obj == null || getClass() != obj.getClass()) {
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
        sb.append(super.toString()); // Reutiliza la metadata del padre (IDs, Nombres de módulo)
        sb.append("EmployeeRegisterWrapperDTO { ");
        sb.append("employee = ").append(employee).append(", ");
        sb.append("employee_valid = ").append(employee_valid).append(", ");
        sb.append("employee_user = ").append(employee_user).append(", ");
        sb.append("employee_user_valid = ").append(employee_user_valid);
        sb.append(" }");
        return sb.toString();
    }

    @Override
    public void clear() {
        // Corrección Defensiva: Solo limpia los mapas si los objetos ya fueron instanciados en el flujo
        if (employee != null && employee.getMap() != null) {
            employee.getMap().clear();
        }
        if (employee_user != null && employee_user.getMap() != null) {
            employee_user.getMap().clear();
        }

        // Reseteo de banderas lógicas del Wizard
        this.employee_valid = false;
        this.employee_user_valid = false;
    }
}
