/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 *
 * @author jp
 */
public class WaterIntakeTypesDTO extends AuditableObjectMap {

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

}
