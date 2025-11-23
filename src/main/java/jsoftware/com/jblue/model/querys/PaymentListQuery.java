/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.querys;

/**
 *
 * @author juanp
 */
public class PaymentListQuery {

    public static final String PAYMENT_LIST_QUERY = """
                                                    INSERT INTO pym_payment_list
                                                    (payment, item_name, cost, status)
                                                    VALUES
                                                    (?,?,?,?);
                                                    """;
}
