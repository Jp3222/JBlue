package jsoftware.com.jblue.model.dto;

import java.util.Map;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO que gestiona la relación intermedia entre los procesos del sistema, los
 * usuarios del padrón y sus tomas de agua (Water Intake) correspondientes.
 *
 * @author Juan P. Campos C.
 * @since 2026-07-18
 * @version 1.0
 */
public class ProcessWaterIntakeUserDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public ProcessWaterIntakeUserDTO() {
        super(30);
    }

    public ProcessWaterIntakeUserDTO(Map<String, Object> map) {
        super(map);
    }

    public String getProcessId() {
        return Func.nullSafeToString(get("process_id"));
    }

    public String getSequence() {
        return Func.nullSafeToString(get("sequence"));
    }

    public String getProcessType() {
        return Func.nullSafeToString(get("process_type"));
    }

    public String getUserId() {
        return Func.nullSafeToString(get("user_id"));
    }

    public String getDocumentId() {
        return Func.nullSafeToString(get("document_id"));
    }

    public String getWaterIntakeUserId() {
        return Func.nullSafeToString(get("water_intake_user_id"));
    }

    public String getPaymentId() {
        return Func.nullSafeToString(get("payment_id"));
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

}
