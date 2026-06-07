package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al registro maestro e histórico de transacciones del
 * sistema (TransactionHistory).
 * <br>
 * Utiliza la estructura heredada dinámica de Map para transportar las marcas de
 * tiempo, estados y llaves polimórficas de manera flexible entre los
 * controladores de Swing y la capa DAO de MySQL, apegándose al estándar actual
 * de JBlue.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-07
 * @version 1.0
 */
public class TransactionHistoryDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public TransactionHistoryDTO() {
        // Inicializa con una capacidad de 16 para albergar de forma eficiente los 12 campos (incluyendo id),
        // garantizando un factor de carga óptimo por debajo del umbral de saturación (12/16 = 0.75),
        // evitando re-hasheos o redimensionamientos en memoria durante el flujo transaccional.
        super(16);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia
    public String getTypeMov() {
        return Func.nullSafeToString(get("type_mov"));
    }

    public String getObservation() {
        return Func.nullSafeToString(get("observation"));
    }

    public String getModuleId() {
        return Func.nullSafeToString(get("module_id"));
    }

    public String getIp() {
        return Func.nullSafeToString(get("ip"));
    }

    public String getHysStartId() {
        return Func.nullSafeToString(get("hys_start_id"));
    }

    public String getHysEndId() {
        return Func.nullSafeToString(get("hys_end_id"));
    }

    public String getAffectedTable() {
        return Func.nullSafeToString(get("affected_table"));
    }

    public String getEntyId() {
        return Func.nullSafeToString(get("enty_id"));
    }

    public String getDBUser() {
        return Func.nullSafeToString(get("db_user"));
    }

    public String getEmployeeId() {
        return Func.nullSafeToString(get("employee_id"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public String getDateRegister() {
        return Func.nullSafeToString(get("date_register"));
    }

    @Override
    public String toString() {
        // Asegura un vaciado seguro en tus logs de auditoría o inserciones de payload previniendo excepciones nulas
        return (values != null) ? values.toString() : "{}";
    }
}
