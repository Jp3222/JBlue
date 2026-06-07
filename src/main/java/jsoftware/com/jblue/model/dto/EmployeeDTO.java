package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente a los datos generales, de identificación fiscal y
 * contacto de empleados (Employee).
 * <br>
 * Utiliza la estructura heredada dinámica de Map para transportar las cadenas
 * de texto de manera flexible entre los componentes de Swing y la capa DAO de
 * MySQL, apegándose estrictamente al estándar de diseño de JBlue.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.0
 */
public class EmployeeDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public EmployeeDTO() {
        // Inicializa con una capacidad de 32 para albergar de forma eficiente los 18 campos (incluyendo id),
        // garantizando un factor de carga óptimo sin re-hasheos o redimensionamientos en memoria.
        super(32);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia
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
        // Garantiza un vaciado limpio de strings en logs de auditoría o consola previniendo NullPointerException
        return (values != null) ? values.toString() : "{}";
    }
}
