package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente a la vinculación y estado de tomas de agua por usuario
 * (WaterIntakeUser).
 * <br>
 * Utiliza la estructura interna dinámica de Map para transportar las cadenas de
 * texto de manera flexible entre los componentes de Swing y la capa DAO de
 * MySQL.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 * @version 1.0
 */
public class WaterIntakeUserDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public WaterIntakeUserDTO() {
        // Inicializa con una capacidad de 32 para albergar de forma eficiente los 21 campos,
        // garantizando un factor de carga óptimo sin redimensionamientos en memoria.
        super(32);
    }

    // Se omite getId() -> Heredado directamente de JDBMapObject de forma limpia
    public String getUserId() {
        return Func.nullSafeToString(get("user_id"));
    }

    public String getAddressId() {
        return Func.nullSafeToString(get("address_id"));
    }

    public String getWaterIntakeId() {
        return Func.nullSafeToString(get("water_intake_id"));
    }

    public String getWaterIntakeTypeId() {
        return Func.nullSafeToString(get("water_intake_type_id"));
    }

    public String getUserTypeId() {
        return Func.nullSafeToString(get("user_type_id"));
    }

    public String getOfficeId() {
        return Func.nullSafeToString(get("office_id"));
    }

    public String getUserName() {
        return Func.nullSafeToString(get("user_name"));
    }

    public String getDescription() {
        return Func.nullSafeToString(get("description"));
    }

    public String getObservation() {
        return Func.nullSafeToString(get("observation"));
    }

    public String getCurrentFiscalYear() {
        return Func.nullSafeToString(get("current_fiscal_year"));
    }

    public String getLastMonthPaid() {
        return Func.nullSafeToString(get("last_month_paid"));
    }

    public String getLastAmountPaid() {
        return Func.nullSafeToString(get("last_amount_paid"));
    }

    public String getEmployeeRegister() {
        return Func.nullSafeToString(get("employee_register"));
    }

    public String getLastEmployeeUpdate() {
        return Func.nullSafeToString(get("last_employee_update"));
    }

    public String getOriginalProcess() {
        return Func.nullSafeToString(get("original_process"));
    }

    public String getLastProcessType() {
        return Func.nullSafeToString(get("last_process_type"));
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
        // Asegura un vaciado seguro en tus logs de auditoría previniendo excepciones nulas
        return (values != null) ? values.toString() : "{}";
    }
}
