/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp.imp;

import jsoftware.com.jblue.model.exp.ProcessException;
import static jsoftware.com.jblue.model.models.ExceptionCode.START_PROCESS_EXCEPTION;

/**
 *
 * @author juanp
 */
public class PrintProcessException extends ProcessException {

    public PrintProcessException() {
        super(START_PROCESS_EXCEPTION, "NO SE PUDO INICIAR EL TRAMITE");
    }

}
