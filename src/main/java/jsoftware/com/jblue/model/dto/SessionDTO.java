package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente a las sesiones de usuario en el sistema (Session).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre los formularios de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.2
 */
public class SessionDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public SessionDTO() {
        // Inicializa con una capacidad de 10 para albergar eficientemente los campos de sesión.
        super(10);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject
    public String getEmployeeId() {
        return Func.nullSafeToString(get("employee_id"));
    }

    public String getHistoryStartId() {
        return Func.nullSafeToString(get("history_start_id"));
    }

    public String getDateIn() {
        return Func.nullSafeToString(get("date_in"));
    }

    public String getHistoryEndId() {
        return Func.nullSafeToString(get("history_end_id"));
    }

    public String getDateOut() {
        return Func.nullSafeToString(get("date_out"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public String getDateRegister() {
        return Func.nullSafeToString(get("date_register"));
    }

    @Override
    public String toString() {
        return (values != null) ? values.toString() : "{}";
    }
}
