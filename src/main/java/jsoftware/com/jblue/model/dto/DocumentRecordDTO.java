package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al registro de control, auditoría y seguimiento de
 * bloques documentales en el sistema (DocumentRecord).
 * <br><br>
 * <strong>Estándar JBlue:</strong>
 * <ul>
 * <li>La Vista y los Controladores interactúan exclusivamente con objetos
 * {@code String}.</li>
 * <li>El DTO encapsula y transporta dichos datos dentro de su estructura de
 * mapa interna.</li>
 * <li>El DAO realiza la conversión y el casting final a tipos nativos (Integer,
 * Double, etc.) según requiera MySQL o SQLite.</li>
 * <li>Control defensivo de valores nulos mediante
 * {@code Func.nullSafeToString}.</li>
 * </ul>
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-14
 * @version 1.1
 */
public class DocumentRecordDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    /**
     * Inicializa el DTO asignando una capacidad inicial fija al mapa de datos.
     * <br>
     * Con 14 campos activos (id, user_id, process_id, sequence, observation,
     * document_count, document_start, document_end, status,
     * employee_register_id, employee_valid_id, date_update, date_register,
     * date_end), se define un tamaño de <strong>32</strong>.
     * <br>
     * Esto mantiene el factor de carga en un óptimo {@code 14 / 32 = 0.43} (muy
     * por debajo del umbral crítico de 0.75), eliminando la necesidad de
     * operaciones de re-hasheo en memoria durante el flujo transaccional.
     */
    public DocumentRecordDTO() {
        super(32);
    }

    // =========================================================================
    // MÉTODOS DE ACCESO (GETTERS) - RETORNAN SIEMPRE STRING (NULL-SAFE)
    // =========================================================================
    public String getUserId() {
        return Func.nullSafeToString(get("user_id"));
    }

    public String getProcessId() {
        return Func.nullSafeToString(get("process_id"));
    }

    public String getSequence() {
        return Func.nullSafeToString(get("sequence"));
    }

    public String getObservation() {
        return Func.nullSafeToString(get("observation"));
    }

    public String getDocumentCount() {
        return Func.nullSafeToString(get("document_count"));
    }

    public String getDocumentStart() {
        return Func.nullSafeToString(get("document_start"));
    }

    public String getDocumentEnd() {
        return Func.nullSafeToString(get("document_end"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public String getEmployeeRegisterId() {
        return Func.nullSafeToString(get("employee_register_id"));
    }

    public String getEmployeeValidId() {
        return Func.nullSafeToString(get("employee_valid_id"));
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

    // =========================================================================
    // REESCRITURA DE MÉTODOS ESTÁNDAR
    // =========================================================================
    @Override
    public String toString() {
        return (values != null) ? values.toString() : "{}";
    }
}
