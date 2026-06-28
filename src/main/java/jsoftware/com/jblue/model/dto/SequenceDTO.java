package jsoftware.com.jblue.model.dto;

import java.util.Map;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al control centralizado, filtrable y auditable de
 * contadores atómicos y secuencias personalizadas en el sistema (Sequence).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre los formularios de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-27
 * @version 1.0
 */
public class SequenceDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public SequenceDTO() {
        // Inicializa con una capacidad de 26 para albergar de forma eficiente los 17 campos,
        // garantizando que el HashMap interno no sufra redimensionamientos en memoria.
        super(26);
    }

    public SequenceDTO(Map<String, Object> map) {
        super(map);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia
    public String getSequencePrefix() {
        return Func.nullSafeToString(get("sequence_prefix"));
    }

    public void setSequencePrefix(String sequencePrefix) {
        put("sequence_prefix", sequencePrefix);
    }

    public String getSequenceName() {
        return Func.nullSafeToString(get("sequence_name"));
    }

    public void setSequenceName(String sequenceName) {
        put("sequence_name", sequenceName);
    }

    /**
     * Recupera el postfix. Mapeado exactamente igual al nombre físico de tu
     * columna (`squence_postfix`).
     */
    public String getSquencePostfix() {
        return Func.nullSafeToString(get("squence_postfix"));
    }

    /**
     * Asigna el postfix. Mapeado exactamente igual al nombre físico de tu
     * columna (`squence_postfix`).
     */
    public void setSquencePostfix(String squencePostfix) {
        put("squence_postfix", squencePostfix);
    }

    public String getCurrentValue() {
        return Func.nullSafeToString(get("current_value"));
    }

    public void setCurrentValue(String currentValue) {
        put("current_value", currentValue);
    }

    public String getIncrementBy() {
        return Func.nullSafeToString(get("increment_by"));
    }

    public void setIncrementBy(String incrementBy) {
        put("increment_by", incrementBy);
    }

    public String getTableId() {
        return Func.nullSafeToString(get("table_id"));
    }

    public void setTableId(String tableId) {
        put("table_id", tableId);
    }

    public String getProcessTypeId() {
        return Func.nullSafeToString(get("process_type_id"));
    }

    public void setProcessTypeId(String processTypeId) {
        put("process_type_id", processTypeId);
    }

    public String getRegistrationDescription() {
        return Func.nullSafeToString(get("registration_description"));
    }

    public void setRegistrationDescription(String registrationDescription) {
        put("registration_description", registrationDescription);
    }

    public String getUpdateDescription() {
        return Func.nullSafeToString(get("update_description"));
    }

    public void setUpdateDescription(String updateDescription) {
        put("update_description", updateDescription);
    }

    public String getDbUser() {
        return Func.nullSafeToString(get("db_user"));
    }

    public void setDbUser(String dbUser) {
        put("db_user", dbUser);
    }

    public String getClientIp() {
        return Func.nullSafeToString(get("client_ip"));
    }

    public void setClientIp(String clientIp) {
        put("client_ip", clientIp);
    }

    public String getEmployeeId() {
        return Func.nullSafeToString(get("employee_id"));
    }

    public void setEmployeeId(String employeeId) {
        put("employee_id", employeeId);
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public void setStatus(String status) {
        put("status", status);
    }

    public String getDateUpdate() {
        return Func.nullSafeToString(get("date_update"));
    }

    public void setDateUpdate(String dateUpdate) {
        put("date_update", dateUpdate);
    }

    public String getDateRegister() {
        return Func.nullSafeToString(get("date_register"));
    }

    public void setDateRegister(String dateRegister) {
        put("date_register", dateRegister);
    }

    public String getDateEnd() {
        return Func.nullSafeToString(get("date_end"));
    }

    public void setDateEnd(String dateEnd) {
        put("date_end", dateEnd);
    }

    @Override
    public String toString() {
        // Permite un vaciado seguro en consola/logs sin riesgo de NullPointerException
        return (values != null) ? values.toString() : "{}";
    }
}
