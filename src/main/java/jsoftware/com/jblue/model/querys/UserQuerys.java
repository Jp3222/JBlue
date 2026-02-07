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

    public static final String INSERT_USER = """
                                            INSERT INTO usr_user(
                                                curp, first_name, last_name1, last_name2, 
                                                gender, birthdate, email, phone_number1, 
                                                phone_number2, street1, street2, inside_number, 
                                                outside_number, user_type, status,
                                                last_employee_update,
                                            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                                             """;

    public static final String UPDATE_USER_IDENTY = """
                                                    UPDATE usr_user SET
                                                        curp = ?,
                                                        first_name = ?,
                                                        last_name1 = ?,
                                                        last_name2 = ?,
                                                        gender = ?,
                                                        birthdate = ?,
                                                        last_employee_update = ?
                                                    WHERE id = ? AND status NOT IN(3, 6)
                                                    """;

    public static final String UPDATE_USER_CONTACT = """
                                                    UPDATE usr_user SET
                                                        email = ?,
                                                        phone_number1 = ?,
                                                        phone_number2 = ?,
                                                        last_employee_update = ?
                                                    WHERE id = ? AND status NOT IN(3, 6)
                                                    """;

    public static final String UPDATE_USER_ADDRES = """
                                                    UPDATE usr_user SET
                                                        user_type = ?,                                                        
                                                        last_employee_update = ?
                                                    WHERE id = ? AND status NOT IN(3, 6)

                                                    """;

    public static final String UPDATE_USER_TYPE = """
                                                    UPDATE usr_user SET
                                                        user_type = ?,                                                     
                                                        last_employee_update = ?
                                                    WHERE id = ? AND status NOT IN(3, 6)
                                                    """;

    public static final String UPDATE_USER_STATUS = """
                                                    UPDATE usr_user SET
                                                        status = ?,
                                                        last_employee_update = ?
                                                    WHERE id = ? AND status NOT IN(3, 6)
                                                    """;
}
