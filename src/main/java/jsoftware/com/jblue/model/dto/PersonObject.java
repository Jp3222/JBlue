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
        return values.get("curp").toString();
    }

    public String getFirstName() {
        return values.get("first_name").toString();
    }

    public String getLastName1() {
        return values.get("last_name1").toString();
    }

    public String getLastName2() {
        return values.get("last_name2").toString();
    }

    public String getGender() {
        return values.get("Gender").toString();
    }

    public String getEmail() {
        return values.get("Email").toString();
    }

    public String getBirthdate() {
        return values.get("birthdate").toString();
    }

    public String getPhoneNumber1() {
        return values.get("phone_number1").toString();
    }

    public String getPhoneNumber2() {
        return values.get("phone_number2").toString();
    }

}
