/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp;

/**
 *
 * @author juanp
 */
public class BlueException extends Exception {

    private static final long serialVersionUID = 1L;

    protected int error_code;
    protected String user_message;

    public BlueException(int error_code, String user_message) {
        this(error_code, user_message, user_message);
    }

    public BlueException(String user_message, String dev_msg) {
        this(-1, user_message, dev_msg);
    }

    public BlueException(int error_code, String user_message, String dev_msg) {
        super(dev_msg);
        this.error_code = error_code;
        this.user_message = user_message;
    }

    public int getErrorCode() {
        return error_code;
    }

    public String getUserMessage() {
        return user_message;
    }

}
