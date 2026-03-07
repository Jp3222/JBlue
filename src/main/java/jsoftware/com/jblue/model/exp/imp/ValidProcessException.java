/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp.imp;

import jsoftware.com.jblue.model.exp.ProcessException;
import static jsoftware.com.jblue.model.models.ExceptionCode.VALID_PROCESS_EXCEPTION;

/**
 *
 * @author juanp
 */
public class ValidProcessException extends ProcessException {

    public ValidProcessException() {
        super(VALID_PROCESS_EXCEPTION, "NO SE PUDO VAALIDAR EL TRAMITE");
    }

}
