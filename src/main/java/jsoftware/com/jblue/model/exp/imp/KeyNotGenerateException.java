/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp.imp;

import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import static jsoftware.com.jblue.model.models.ExceptionCode.CORRUPT_INSERTION_EXCEPTION;

/**
 *
 * @author juanp
 */
public class KeyNotGenerateException extends DataAccesObjectException {

    public KeyNotGenerateException() {
        super(CORRUPT_INSERTION_EXCEPTION, "LA LLAVE PRIMARIA NO PUDO SER GENERADA");
    }

}
