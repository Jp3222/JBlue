/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 *
 * @author jp
 */
public class StreetDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;
    
    public String getStreetName(){
        return get("street_name").toString();
    }

    @Override
    public String toString() {
        return getStreetName();
    }
    
    
}
