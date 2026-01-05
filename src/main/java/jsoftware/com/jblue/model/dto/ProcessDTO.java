package jsoftware.com.jblue.model.dto;

import java.time.LocalDateTime;
import java.util.Map;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO para representar la información de un Proceso. Implementación basada en
 * arreglo de String indexado, similar a OUser.
 *
 * @author Gemini
 */
public class ProcessDTO extends JDBMapObject implements StatusObjectModel {

    private static final long serialVersionUID = 1L;

    public ProcessDTO(Map<String, Object> map) {
        super(map);
    }

    public ProcessDTO(int size) {
        super(size);
    }

    public ProcessDTO() {
        super();
    }

    public String getProcessType() {
        return get("process_type").toString();
    }

    public String getEmployeeStart() {
        return get("employee_start").toString();
    }

    public String getDateStart() {
        return get("date_start").toString();
    }

    public String getEmployeeValid() {
        return get("employee_valid").toString();
    }

    public String getDateValid() {
        return get("date_valid").toString();
    }

    public String getEmployeePayment() {
        return get("employee_payment").toString();
    }

    public String getDatePayment() {
        return get("date_payment").toString();
    }

    public String getEmployeeEnd() {
        return get("employee_ends").toString();
    }

    public String getDateEnd() {
        return get("date_end").toString();
    }

    public String getEmployeePrint() {
        return get("employe_print").toString();
    }

    public String getDatePrint() {
        return get("date_print").toString();
    }

    public String getAdministration() {
        return get("administration").toString();
    }

    public String getLastEmployeeUpdate() {
        return get("last_employee_update").toString();
    }

    public String getCurrentDbUser() {
        return get("current_db_user").toString();
    }

    public String getOriginalUser() {
        return get("original_user").toString();
    }

    public String getWaterIntake() {
        return get("water_intake").toString();
    }

    @Override
    public int getStatus() {
        return Integer.parseInt(get("status").toString());
    }

    @Override
    public String getStatusString() {
        return CacheFactory.CAT_STATUS[getStatus()];
    }

    @Override
    public boolean isActive() {
        return getStatus() != 3;
    }

    public LocalDateTime getDateRegister() {
        return Formats.getLocalDateTime(get("date_register").toString());
    }
}
