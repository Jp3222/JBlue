package jsoftware.com.jblue.model.dto;

import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 * DTO correspondiente al catálogo y registro de direcciones físicas de usuarios (UsrAddress).
 * <br><br>
 * <strong>Estándar JBlue:</strong> Regula el transporte seguro de strings de control,
 * el mapeo condicional de propiedad y la nulidad defensiva en las cadenas explicativas.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-19
 * @version 1.1
 */
public class AddressDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public AddressDTO() {
        super(32);
    }

    public String getUserId() {
        return Func.nullSafeToString(get("user_id"));
    }

    public String getStreet1Id() {
        return Func.nullSafeToString(get("street1_id"));
    }

    public String getStreet2Id() {
        return Func.nullSafeToString(get("street2_id"));
    }

    public String getInsideNumber() {
        return Func.nullSafeToString(get("inside_number"));
    }

    public String getOutsideNumber() {
        return Func.nullSafeToString(get("outside_number"));
    }

    public String getIsOwner() {
        return Func.nullSafeToString(get("is_owner"));
    }

    public String getObservation() {
        return Func.nullSafeToString(get("observation"));
    }

    public String getEmployeeId() {
        return Func.nullSafeToString(get("employee_id"));
    }

    public String getOfficeId() {
        return Func.nullSafeToString(get("office_id"));
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
        return (values != null) ? values.toString() : "{}";
    }
}