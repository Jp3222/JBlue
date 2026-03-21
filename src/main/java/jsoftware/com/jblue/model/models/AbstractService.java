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

    private static final long serialVersionUID = 1L;

    protected String user_message;
    protected int error_code;
    private final boolean dev_flag;
    private final String process_name;

    public AbstractService(boolean dev_flag, String process_name) {
        this.user_message = null;
        this.error_code = 0;
        this.process_name = process_name;
        this.dev_flag = dev_flag;
    }

    @Override
    public String getUserMessage() {
        return user_message;
    }

    @Override
    public int getErrorCode() {
        return error_code;
    }

    public boolean isDev_flag() {
        return dev_flag;
    }

    public String getProcess_name() {
        return process_name;
    }

    @Override
    public boolean isError() {
        return error_code > 0;
    }
}
