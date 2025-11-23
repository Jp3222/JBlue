/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.querys;

/**
 *
 * @author juanp
 */
public class PaymentQuerys {

    public static final String PAYMENT_INSERT = """
                                                 INERT INTO pym_payment
                                                 (payment_type, uudi, employee, 
                                                 user, water_intake, water_intake_type, payment_method, payment_concept, 
                                                 total_cost, amount_paid, change_amount, month_paid, status)
                                                 VALUES
                                                 (?,?,?,?,?,?,?,?,?,?,?,?,?)
                                                 """;

    public static final String PAYMENT_QUERY = getQuery(false, true, true);

    public static String getQuery(boolean status, boolean year, boolean order) {
        StringBuilder sb = new StringBuilder("SELECT * FROM pym_payment WHERE user = ? \n");
        if (year) {
            sb.append("AND YEAR(date_register) = YEAR(NOW()) \n");
        }
        if (status) {
            sb.append("AND status = ? \n");
        }
        if (order) {
            sb.append("ORDER BY id DESC \n");
        }
        return sb.toString();
    }
}
