/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.util.Map;

/**
 *
 * @author juanp
 */
public class PaymentConceptDTO extends AuditableObjectMap {
    
    private static final long serialVersionUID = 1L;
    
    public PaymentConceptDTO(Map<String, Object> map) {
        super(map);
    }

    public PaymentConceptDTO() {
        super(7);
    }
    
    public String getDescription() {
        return get("description").toString();
    }
    
    public String getDescriptionLong() {
        return get("description_long").toString();
    }
    
}
