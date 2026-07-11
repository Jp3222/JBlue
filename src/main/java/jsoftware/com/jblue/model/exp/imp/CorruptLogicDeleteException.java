/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp.imp;

import jsoftware.com.jblue.model.exp.DataAccesObjectException;

/**
 *
 * @author juanp
 */
public class CorruptLogicDeleteException extends DataAccesObjectException {

    public CorruptLogicDeleteException(int error_code, String user_message, String dev_msg) {
        super(error_code, user_message, dev_msg);
    }

    public CorruptLogicDeleteException(String dev_msg) {
        this(
                CORRUPT_LOGIC_DELETE_EXCEPTION, 
                "EL MOVIMIENTO DE ELIMINACION NO PUDO SER REALIZADO", 
                dev_msg
        );
    }
}
