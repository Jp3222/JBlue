package jsoftware.com.jblue.model.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO que gestiona la relación intermedia entre los procesos del sistema, los
 * usuarios del padrón y sus tomas de agua (Water Intake) correspondientes.
 *
 * @author Juan P. Campos C.
 * @since 2026-07-18
 * @version 1.0
 */
public class ProcessWaterIntakeUserDTO {

    private final Map<String, String> data;

    /**
     * Inicializa el contenedor de datos interno para la vinculación de la toma.
     */
    public ProcessWaterIntakeUserDTO() {
        this.data = new HashMap<>();
    }

    /**
     * Inyecta o actualiza un valor en la matriz de datos.
     *
     * @param key Identificador de la columna o propiedad.
     * @param value Información textual a almacenar.
     */
    public void put(String key, String value) {
        data.put(key, value);
    }

    /**
     * Recupera un valor del contenedor de datos de forma segura.
     *
     * @param key Identificador de la columna o propiedad.
     * @return El valor en formato String, o null si no existe.
     */
    public String get(String key) {
        return data.get(key);
    }

    // --- Métodos de Acceso Directo por Contrato (Getters Específicos) ---
    public String getId() {
        return data.get("id");
    }

    public String getProcessId() {
        return data.get("process_id");
    }

    public String getSequence() {
        return data.get("sequence");
    }

    public String getProcessType() {
        return data.get("process_type");
    }

    public String getUserId() {
        return data.get("user_id");
    }

    public String getDocumentId() {
        return data.get("document_id");
    }

    public String getWaterIntakeUserId() {
        return data.get("water_intake_user_id");
    }

    public String getPaymentId() {
        return data.get("payment_id");
    }

    public String getStatus() {
        return data.get("status");
    }

    public String getLastEmployeeUpdate() {
        return data.get("last_employee_update");
    }

    public String getDateUpdate() {
        return data.get("date_update");
    }

    public String getDateRegister() {
        return data.get("date_register");
    }

    public String getDateEnd() {
        return data.get("date_end");
    }

    /**
     * Devuelve la estructura completa de datos para persistencia o
     * validaciones.
     */
    public Map<String, String> toMap() {
        return data;
    }
}
