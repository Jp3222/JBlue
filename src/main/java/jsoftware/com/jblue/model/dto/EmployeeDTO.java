package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente a la información personal e institucional de los
 * empleados (Employee).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre los formularios de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.1
 */
public class EmployeeDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public EmployeeDTO() {
        // Inicializa con una capacidad de 26 para albergar los 18 campos de forma eficiente,
        // evitando que el HashMap interno tenga que redimensionarse en memoria.
        super(26);
    }

    public String getRfc() {
        return Func.nullSafeToString(get("rfc"));
    }

    public String getCurp() {
        return Func.nullSafeToString(get("curp"));
    }

    public String getFirstName() {
        return Func.nullSafeToString(get("first_name"));
    }

    public String getLastName1() {
        return Func.nullSafeToString(get("last_name1"));
    }

    public String getLastName2() {
        return Func.nullSafeToString(get("last_name2"));
    }

    public String getGender() {
        return Func.nullSafeToString(get("gender"));
    }

    public String getBirthdate() {
        return Func.nullSafeToString(get("birthdate"));
    }

    public String getPersonalEmail() {
        return Func.nullSafeToString(get("personal_email"));
    }

    public String getPersonalNumber() {
        return Func.nullSafeToString(get("personal_number"));
    }

    public String getStreet1() {
        return Func.nullSafeToString(get("street1"));
    }

    public String getStreet2() {
        return Func.nullSafeToString(get("street2"));
    }

    public String getInsideNumber() {
        return Func.nullSafeToString(get("inside_number"));
    }

    public String getOutsideNumber() {
        return Func.nullSafeToString(get("outside_number"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
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
        // Garantiza una salida segura en tus logs de Log4j2 sin riesgo de NullPointerException
        return (values != null) ? values.toString() : "{}";
    }
}
