/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author jp
 */
public class WaterIntakeTypeDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public WaterIntakeTypeDTO(Map<String, Object> map) {
        super(map);
    }

    public WaterIntakeTypeDTO() {
        super();
    }

    /*
    , , , , , , , ,  
     */
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

    public boolean getChargeUnit() {
        return get("charge_unit").toString().equals("1");
    }

    public String getUnitId() {
        return get("unit_id").toString();
    }

    public String getUnits() {
        return get("units").toString();
    }

    public boolean getApplyRules() {
        return get("apply_rules").toString().equals("1");
    }

    public boolean getRound() {
        return get("round").toString().equals("1");
    }

    public boolean getRoundUp() {
        return get("round_up").toString().equals("1");
    }

    public BigDecimal getBasePrice() {
        return new BigDecimal(get("base_price").toString());
    }

    public BigDecimal getSurchargePercentage() {
        return new BigDecimal(get("surcharge_percentage").toString());
    }

    public BigDecimal getAnnualIncrease() {
        return new BigDecimal(get("annual_increase").toString());
    }

    public String getLastEmployeeUpdate() {
        return get("last_employee_update").toString();
    }

    @Override
    public String toString() {
        return getTypeName();
    }

}
