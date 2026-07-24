package jsoftware.com.jblue.model.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO que transporta los estados, marcas de tiempo y auditoría de los procesos 
 * del sistema de cobros. Implementa el estándar de mapeo flexible mediante cadenas.
 *
 * @author Juan P. Campos C.
 * @since 2026-07-18
 * @version 1.0
 */
public class ProcessDTO {

    private final Map<String, String> data;

    /**
     * Inicializa el contenedor de datos interno del proceso.
     */
    public ProcessDTO() {
        this.data = new HashMap<>();
    }

    /**
     * Inyecta o actualiza un valor en la matriz de datos del proceso.
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

    public String getSequenceProcess() {
        return data.get("sequence_process");
    }

    public String getProcessType() {
        return data.get("process_type");
    }

    public String getEmployeeStart() {
        return data.get("employee_start");
    }

    public String getDateStart() {
        return data.get("date_start");
    }

    public String getEmployeeValid() {
        return data.get("employee_valid");
    }

    public String getDateValid() {
        return data.get("date_valid");
    }

    public String getEmployeeMov() {
        return data.get("employee_mov");
    }

    public String getDateMov() {
        return data.get("date_mov");
    }

    public String getEmployeePayment() {
        return data.get("employee_payment");
    }

    public String getDatePayment() {
        return data.get("date_payment");
    }

    public String getEmployeeFinalize() {
        return data.get("employee_finalize");
    }

    public String getDateFinalize() {
        return data.get("date_finalize");
    }

    public String getEmployeePrint() {
        return data.get("employee_print");
    }

    public String getDatePrint() {
        return data.get("date_print");
    }

    public String getAdministrationStart() {
        return data.get("administration_start");
    }

    public String getAdministrationEnd() {
        return data.get("administration_end");
    }

    public String getCurrentDbUser() {
        return data.get("current_db_user");
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
     * Devuelve la estructura completa de datos para persistencia masiva o depuración.
     */
    public Map<String, String> toMap() {
        return data;
    }
}