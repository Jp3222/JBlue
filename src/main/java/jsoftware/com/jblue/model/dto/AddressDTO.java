package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente a la gestión de direcciones de usuarios (Address).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre los formularios de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 * @version 1.0
 */
public class AddressDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public AddressDTO() {
        // Inicializa con una capacidad de 16 para albergar de forma eficiente los 12 campos,
        // garantizando un factor de carga óptimo sin redimensionamientos en memoria.
        super(16);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia
    public String getUserId() {
        return Func.nullSafeToString(get("user_id"));
    }

    public String getStreet1Id() {
        return Func.nullSafeToString(get("street1_id"));
    }

    public String getStreet2Id() {
        return Func.nullSafeToString(get("street2_id"));
    }

    public String getInsideNumber() {
        return Func.nullSafeToString(get("inside_number"));
    }

    public String getOutsideNumber() {
        return Func.nullSafeToString(get("outside_number"));
    }

    public String getEmployeeId() {
        return Func.nullSafeToString(get("employee_id"));
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
        // Asegura un vaciado seguro en tus logs de Log4j2 sin riesgo de NullPointerException
        return (values != null) ? values.toString() : "{}";
    }
}
