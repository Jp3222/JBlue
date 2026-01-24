/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class PaymentListDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public String getPayment() {
        return get("payment").toString();
    }

    public String getPaymentConcept() {
        return get("payment_concept").toString();
    }

    public String getItemName() {
        return get("item_name").toString();
    }

    public String getCost() {
        return get("cost").toString();
    }

    public String getStatus() {
        return get("status").toString();
    }

    public String getDateRegister() {
        return get("date_register").toString();
    }
}
