/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import jsoftware.com.jutil.db.model.JDBObject;

/**
 *
 * @author juanp
 */
public class PaymentDTO extends AuditableObjectMap implements AuditableModel, JDBObject {

    private static final long serialVersionUID = 1L;

    public String getPaymentType() {
        return get("payment_type").toString();
    }

    public String getUDDI() {
        return get("uddi").toString();
    }

    public String getEmployee() {
        return get("employee").toString();
    }

    public String getUser() {
        return get("user").toString();
    }

    public String getWaterInatkeType() {
        return get("water_intake_type").toString();
    }

    public String getPaymentMethod() {
        return get("payment_method").toString();
    }

    public String getPaymentConcept() {
        return get("payment_concept").toString();
    }

    public String getTotalCost() {
        return get("total_cost").toString();
    }

    public String getAmountPaid() {
        return get("amount_paid").toString();
    }

    public String getChangeAmount() {
        return get("change_paid").toString();
    }

    public String getMonthsPaid() {
        return get("month_paid").toString();
    }

}
