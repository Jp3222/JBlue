package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al registro de usuarios bloqueados en el sistema
 * (BlockedUser).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre los formularios de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 * @version 1.0
 */
public class BlockedUserDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public BlockedUserDTO() {
        // Inicializa con una capacidad de 26 para albergar de forma eficiente los 18 campos,
        // garantizando que el HashMap interno no sufra redimensionamientos en memoria.
        super(26);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia
    public String getUserId() {
        return Func.nullSafeToString(get("user_id"));
    }

    public String getDescriptionRegister() {
        return Func.nullSafeToString(get("description_register"));
    }

    public String getDescriptionUpdate() {
        return Func.nullSafeToString(get("description_update"));
    }

    public String getDescriptionEnd() {
        return Func.nullSafeToString(get("description_end"));
    }

    public String getObservationLock() {
        return Func.nullSafeToString(get("observation_lock"));
    }

    public String getObservationUnlock() {
        return Func.nullSafeToString(get("observation_unlock"));
    }

    public String getApplyUnlocked() {
        return Func.nullSafeToString(get("apply_unlocked"));
    }

    public String getOfficeLock() {
        return Func.nullSafeToString(get("office_lock"));
    }

    public String getOfficeUnlock() {
        return Func.nullSafeToString(get("office_unlock"));
    }

    public String getEmployeeRegister() {
        return Func.nullSafeToString(get("employee_register"));
    }

    public String getEmployeeUpdate() {
        return Func.nullSafeToString(get("employee_update"));
    }

    public String getEmployeeUnlocked() {
        return Func.nullSafeToString(get("employee_unlocked"));
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
        // Permite un vaciado seguro en consola/logs sin riesgo de NullPointerException
        return (values != null) ? values.toString() : "{}";
    }
}
