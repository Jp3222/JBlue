/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp.service;

import jsoftware.com.jblue.model.exp.ServiceException;

/**
 *
 * @author juanp
 */
public class LoginFailedException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public LoginFailedException(int error_code, String user_message) {
        super(error_code, user_message);
    }
    
    public LoginFailedException(int error_code) {
        super(error_code, "NO SE PUDO INICIAR SESION CORRECTAMENTE");
    }
    
    public LoginFailedException(String user_message) {
        super(1, user_message);
    }

    public LoginFailedException() {
        this(1, "NO SE PUDO INICIAR SESION CORRECTAMENTE");
    }

}
