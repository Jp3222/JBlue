package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al registro de valores o folios bloqueados en el sistema (BlockedValues).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre las interfaces de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 * @version 1.0
 */
public class BlockedValuesDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public BlockedValuesDTO() {
        // Inicializa con una capacidad de 16 para albergar de forma eficiente los 10 campos,
        // garantizando un factor de carga óptimo sin redimensionamientos en memoria.
        super(16); 
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia

    public String getLockValue() {
        return Func.nullSafeToString(get("lock_value"));
    }

    public String getEmployeeLock() {
        return Func.nullSafeToString(get("employee_lock"));
    }

    public String getEmployeeUnlock() {
        return Func.nullSafeToString(get("employee_unlock"));
    }

    public String getObservationLock() {
        return Func.nullSafeToString(get("observation_lock"));
    }

    public String getObservationUnlock() {
        return Func.nullSafeToString(get("observation_unlock"));
    }

    public String getTypeLock() {
        return Func.nullSafeToString(get("type_lock"));
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
        // Asegura una impresión limpia en tus logs de Log4j2 previniendo excepciones nulas
        return (values != null) ? values.toString() : "{}";
    }
}