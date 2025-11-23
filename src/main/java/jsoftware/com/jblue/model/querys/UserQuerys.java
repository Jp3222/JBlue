/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.querys;

/**
 *
 * @author juanp
 */
public class UserQuerys {

    public static final String USER_INSERT = """
                                            INSERT INTO usr_user(
                                                curp, first_name, last_name1, last_name2, gender, 
                                                email, phone_number1, phone_number2, street1, street2, 
                                                inside_number, outside_number, water_intake_type, user_type, 
                                                status, last_employee_update
                                            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                                             """;
}
