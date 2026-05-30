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
public class GenerateInformationException extends ServiceException {

    public GenerateInformationException() {
        super(GENERATE_INFORMATION_EXCEPTION, "LA INFORMACION NO FUE GENERADA CORRECTAMENTE");
    }
    
}
