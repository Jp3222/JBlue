package jsoftware.com.jblue.model.dto;

import java.util.Map;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO que transporta los estados, marcas de tiempo y auditoría de los procesos
 * del sistema de cobros. Implementa el estándar de mapeo flexible mediante
 * cadenas.
 *
 * @author Juan P. Campos C.
 * @since 2026-07-18
 * @version 1.0
 */
public class ProcessDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public ProcessDTO(Map<String, Object> map) {
        super(map);
    }

    public ProcessDTO() {
        // Inicializa con una capacidad de 26 para albergar eficientemente los 18 campos,
        // evitando que el HashMap interno tenga que redimensionarse en memoria.
        super(26);
    }

    public String getSequenceProcess() {
        return Func.nullSafeToString(get("sequence_process"));
    }

    public String getProcessType() {
        return Func.nullSafeToString(get("process_type"));
    }

    public String getEmployeeStart() {
        return Func.nullSafeToString(get("employee_start"));
    }

    public String getDateStart() {
        return Func.nullSafeToString(get("date_start"));
    }

    public String getEmployeeValid() {
        return Func.nullSafeToString(get("employee_valid"));
    }

    public String getDateValid() {
        return Func.nullSafeToString(get("date_valid"));
    }

    public String getEmployeeMov() {
        return Func.nullSafeToString(get("employee_mov"));
    }

    public String getDateMov() {
        return Func.nullSafeToString(get("date_mov"));
    }

    public String getEmployeePayment() {
        return Func.nullSafeToString(get("employee_payment"));
    }

    public String getDatePayment() {
        return Func.nullSafeToString(get("date_payment"));
    }

    public String getEmployeeFinalize() {
        return Func.nullSafeToString(get("employee_finalize"));
    }

    public String getDateFinalize() {
        return Func.nullSafeToString(get("date_finalize"));
    }

    public String getEmployeePrint() {
        return Func.nullSafeToString(get("employee_print"));
    }

    public String getDatePrint() {
        return Func.nullSafeToString(get("date_print"));
    }

    public String getAdministrationStart() {
        return Func.nullSafeToString(get("administration_start"));
    }

    public String getAdministrationEnd() {
        return Func.nullSafeToString(get("administration_end"));
    }

    public String getCurrentDbUser() {
        return Func.nullSafeToString(get("current_db_user"));
    }

    public String getStatus() {
        return Func.nullSafeToString(get("status"));
    }

    public String getLastEmployeeUpdate() {
        return Func.nullSafeToString(get("last_employee_update"));
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
