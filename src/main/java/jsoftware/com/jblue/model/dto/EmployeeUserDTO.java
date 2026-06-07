package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente a las credenciales, accesos y estado de usuarios de
 * empleados (EmployeeUser).
 * <br>
 * Utiliza la estructura heredada dinámica de Map para transportar las cadenas
 * de texto de manera flexible entre los componentes de Swing y la capa DAO de
 * MySQL, apegándose al estándar actual de JBlue.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-03-21
 * @version 1.0
 */
public class EmployeeUserDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public EmployeeUserDTO() {
        // Inicializa con una capacidad de 32 para albergar de forma eficiente los 15 campos (incluyendo id),
        // garantizando un factor de carga óptimo sin re-hasheos o redimensionamientos en memoria.
        super(32);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia
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
        // Asegura un vaciado seguro en tus logs de auditoría previniendo excepciones nulas
        return (values != null) ? values.toString() : "{}";
    }
}
