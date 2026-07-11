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
public class CorruptUpdateException extends DataAccesObjectException {

    public CorruptUpdateException(int error_code, String user_message, String dev_msg) {
        super(error_code, user_message, dev_msg);
    }

    public CorruptUpdateException(String dev_msg) {
        this(CORRUPT_INSERTION_EXCEPTION, "LA ACTUALIZACION DE DATOS NO PUDO SER REALIZADA", dev_msg);
    }

}
