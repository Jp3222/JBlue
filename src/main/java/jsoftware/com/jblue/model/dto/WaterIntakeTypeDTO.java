package jsoftware.com.jblue.model.dto;

/**
 *
 * @author juanp
 */
public class WaterIntakeTypeDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public WaterIntakeTypeDTO() {
        super(15);
    }

    public String getId() {
        return get("id").toString();
    }

    public String getTypeName() {
        return get("type_name").toString();
    }

    public String getCurrentPrice() {
        return get("current_price").toString();
    }

    public String getPreviousPrice() {
        return get("previous_price").toString();
    }

    public String getSurcharge() {
        return get("surcharge").toString();
    }

    public String getChargeUnit() {
        return get("charge_unit").toString();
    }

    public String getUnitId() {
        return get("unit_id").toString();
    }

    public String getUnits() {
        return get("units").toString();
    }

    public String getApplyRules() {
        return get("apply_rules").toString();
    }

    public String getRound() {
        return get("round").toString();
    }

    public String getRoundUp() {
        return get("round_up").toString();
    }

    public String getBasePrice() {
        return get("base_price").toString();
    }

    public String getSurchargePercentage() {
        return get("surcharge_percentage").toString();
    }

    public String getAnnualIncrease() {
        return get("annual_increase").toString();
    }

    public String getLastEmployeeUpdate() {
        return get("last_employee_update").toString();
    }

    @Override
    public String toString() {
        return getTypeName();
    }

}
