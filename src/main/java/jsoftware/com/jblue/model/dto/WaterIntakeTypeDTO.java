/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

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

    public WaterIntakeTypeDTO(int size) {
        super(size);
    }

    public WaterIntakeTypeDTO() {
        super();
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

    public String getLastEmployeeUpdate() {
        return get("last_employee_update").toString();
    }

    @Override
    public String toString() {
        return getTypeName();
    }

}
