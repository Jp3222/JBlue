/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.models;

/**
 *
 * @author juanp
 */
public class AbstractService implements ServiceModel {

    protected String user_message;
    protected int error_code;

    public AbstractService() {
        this.user_message = null;
        this.error_code = 0;
    }

    @Override
    public String getUserMessage() {
        return user_message;
    }

    @Override
    public int getErrorCode() {
        return error_code;
    }

    @Override
    public boolean isError() {
        return error_code > 0;
    }
}
