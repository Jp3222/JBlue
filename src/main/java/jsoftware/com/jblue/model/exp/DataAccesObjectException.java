/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp;

import jsoftware.com.jblue.model.models.ExceptionCode;

/**
 *
 * @author juanp
 */
public class DataAccesObjectException extends BlueException implements ExceptionCode {

    private static final long serialVersionUID = 1L;

    public DataAccesObjectException() {
        super();
    }

    public DataAccesObjectException(String dev_msg) {
        super(dev_msg);
    }

    public DataAccesObjectException(int error_code, String user_message) {
        super(error_code, user_message);
    }

    public DataAccesObjectException(int error_code, String user_message, String dev_msg) {
        super(error_code, user_message, dev_msg);
    }

}
