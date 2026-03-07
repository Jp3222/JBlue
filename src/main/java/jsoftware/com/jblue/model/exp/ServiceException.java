/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp;

/**
 *
 * @author juanp
 */
public class ServiceException extends BlueException {
    
    public ServiceException(int error_code, String user_message) {
        super(error_code, user_message);
    }

    public ServiceException() {
        super(SERVICE_EXCEPTION, "FLUJO DE OPERACIONES ROTO");
    }
    
    
    
}
