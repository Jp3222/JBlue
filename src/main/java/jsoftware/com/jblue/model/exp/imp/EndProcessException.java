/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp.imp;

import jsoftware.com.jblue.model.exp.ProcessException;
import static jsoftware.com.jblue.model.models.ExceptionCode.END_PROCESS_EXCEPTION;

/**
 *
 * @author juanp
 */
public class EndProcessException extends ProcessException {

    public EndProcessException() {
        super(END_PROCESS_EXCEPTION, "NO SE PUDO FINALIZAR EL TRAMITE");
    }

}
