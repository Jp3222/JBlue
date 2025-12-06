/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 *
 * @author juanp
 */
public class PersonObject extends AuditableObjectMap {

    private static final long serialVersionUID = 1L;

    public String getCurp() {
        return get("curp").toString();
    }

    public String getFirstName() {
        return get("first_name").toString();
    }

    public String getLastName1() {
        return get("last_name1").toString();
    }

    public String getLastName2() {
        return get("last_name2").toString();
    }

    public String getGender() {
        return get("Gender").toString();
    }

    public String getEmail() {
        return get("Email").toString();
    }

    public String getBirthdate() {
        return get("birthdate").toString();
    }

    public String getPhoneNumber1() {
        return get("phone_number1").toString();
    }

    public String getPhoneNumber2() {
        return get("phone_number2").toString();
    }

}
