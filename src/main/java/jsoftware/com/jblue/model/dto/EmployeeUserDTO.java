package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente a los usuarios y empleados del sistema (EmployeeUser).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre los formularios de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.1
 */
public class EmployeeUserDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public EmployeeUserDTO() {
        // Inicializa con una capacidad de 22 para albergar los 15 campos de forma eficiente.
        super(22);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject
    public String getEmployeeId() {
        return Func.nullSafeToString(get("employee_id"));
    }

    public String getOfficeId() {
        return Func.nullSafeToString(get("office_id"));
    }

    public String getUser() {
        return Func.nullSafeToString(get("user"));
    }

    public String getPassword() {
        return Func.nullSafeToString(get("password"));
    }

    public String getDescription() {
        return Func.nullSafeToString(get("description"));
    }

    public String getEmail() {
        return Func.nullSafeToString(get("email"));
    }

    public String getPhoneNumber() {
        return Func.nullSafeToString(get("phone_number"));
    }

    public String getEmployeeType() {
        return Func.nullSafeToString(get("employee_type"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public String getLastEmployeeUpdate() {
        return Func.nullSafeToString(get("last_employee_update"));
    }

    public String getLastUpdatePassword() {
        return Func.nullSafeToString(get("last_update_password"));
    }

    public String getDateUpdate() {
        return Func.nullSafeToString(get("date_update"));
    }

    public String getDateRegister() {
        return Func.nullSafeToString(get("date_register"));
    }

    public String getDateEnd() {
        return Func.nullSafeToString(get("date_end"));
    }

    @Override
    public String toString() {
        return (values != null) ? values.toString() : "{}";
    }
}
