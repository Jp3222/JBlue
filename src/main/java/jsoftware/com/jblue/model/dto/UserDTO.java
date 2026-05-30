package jsoftware.com.jblue.model.dto;

import java.util.Map;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente a la información de los usuarios generales del sistema
 * (User).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre los formularios de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.2
 */
public class UserDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public UserDTO(Map<String, Object> map) {
        super(map);
    }

    public UserDTO() {
        // Inicializa con una capacidad de 26 para albergar eficientemente los 18 campos,
        // evitando que el HashMap interno tenga que redimensionarse en memoria.
        super(26);
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

    public String getEmail() {
        return Func.nullSafeToString(get("email"));
    }

    public String getPhoneNumber1() {
        return Func.nullSafeToString(get("phone_number1"));
    }

    public String getPhoneNumber2() {
        return Func.nullSafeToString(get("phone_number2"));
    }

    public String getUserType() {
        return Func.nullSafeToString(get("user_type"));
    }

    public String getLastEmployeeUpdate() {
        return Func.nullSafeToString(get("last_employee_update"));
    }

    public String getOfficeId() {
        return Func.nullSafeToString(get("office_id"));
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
