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

    public DataAccesObjectException(int error_code, String user_message) {
        super(error_code, user_message);
    }

}
