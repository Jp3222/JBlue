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
        return values.get("type_name").toString();
    }

    public String getCurrentPrice() {
        return values.get("current_price").toString();
    }

    public String getPreviousPrice() {
        return values.get("previous_price").toString();
    }

    public String getSurcharge() {
        return values.get("surcharge").toString();
    }

}
