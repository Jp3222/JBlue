/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import jsoftware.com.jutil.model.AbstractMapDTO;

/**
 *
 * @author juanp
 */
public class SessionDTO extends AbstractMapDTO {

    private static final long serialVersionUID = 1L;

    public SessionDTO() {
        super(10);
    }

    public String getEmployeeId() {
        return get("employee_id").toString();
    }

    public String getHistoryStartId() {
        return get("history_start_id").toString();
    }

    public String getDateIn() {
        return get("date_in").toString();
    }

    public String getHistoryEndId() {
        return get("history_end_id").toString();
    }

    public String getDateOut() {
        return get("date_out").toString();
    }

    public String getStatus() {
        return get("status").toString();
    }

    public String getDateRegister() {
        return get("date_register").toString();
    }

    @Override
    public String toString() {
        return values.toString();
    }

}
