/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.exp;

/**
 *
 * @author juanp
 */
public class PaymentExeption extends BlueException {

    private static final long serialVersionUID = 1L;

    public PaymentExeption(int error_code, String user_message) {
        super(error_code, user_message);
    }

    public PaymentExeption(int error_code, String user_message, String dev_message) {
        super(error_code, user_message, dev_message);
    }

}
