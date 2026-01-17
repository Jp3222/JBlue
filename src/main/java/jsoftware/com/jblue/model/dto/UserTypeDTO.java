/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 *
 * @author juanp
 */
public class UserTypeDTO extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public UserTypeDTO() {
        super(5);
    }

    public String getUserType() {
        return get("user_type").toString();
    }
    
    public String getDescriptionLong(){
        return get("description_long").toString();
    }

    @Override
    public String toString() {
        return getUserType();
    }
    
    
}
