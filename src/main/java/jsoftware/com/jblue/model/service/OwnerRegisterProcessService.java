/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.Serializable;
import jsoftware.com.jblue.model.dto.ProcessWrapperDTO;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class OwnerRegisterProcessService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UserService user;
    private final PaymentService payment;
    private final WaterIntakeService water_intake;

    public OwnerRegisterProcessService(boolean flag_dev, String name_module) {
        user = new UserService(flag_dev, name_module);
        payment = new PaymentService(flag_dev, name_module);
        water_intake = new WaterIntakeService(flag_dev, name_module);
    }

    public boolean save(JDBConnection connection, String process_type, ProcessWrapperDTO dto) {
        boolean res = false;

        return res;
    }

    public PaymentService getPayment() {
        return payment;
    }

    public UserService getUser() {
        return user;
    }

    public WaterIntakeService getWater_intake() {
        return water_intake;
    }

}
