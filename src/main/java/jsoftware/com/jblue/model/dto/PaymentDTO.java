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
        return values.get("payment_type").toString();
    }

    public String getUDDI() {
        return values.get("uddi").toString();
    }

    public String getEmployee() {
        return values.get("employee").toString();
    }

    public String getUser() {
        return values.get("user").toString();
    }

    public String getWaterInatkeType() {
        return values.get("water_intake_type").toString();
    }

    public String getPaymentMethod() {
        return values.get("payment_method").toString();
    }

    public String getPaymentConcept() {
        if (values.containsKey("date_end")) {
            return values.get("payment_concept").toString();
        }
        return "";

    }

    public String getTotalCost() {
        return values.get("total_cost").toString();
    }

    public String getAmountPaid() {
        return values.get("amount_paid").toString();
    }

    public String getChangeAmount() {
        return values.get("change_paid").toString();
    }

    public String getMonthsPaid() {
        return values.get("month_paid").toString();
    }

}
