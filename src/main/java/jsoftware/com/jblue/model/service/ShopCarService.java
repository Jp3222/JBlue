/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import jsoftware.com.jblue.model.models.AbstractService;

/**
 *
 * @author juanp
 */
public class ShopCarService extends AbstractService {

    private PaymentService payment_service;

    public ShopCarService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
    }

    public boolean payment() {
        return false;
    }
}
