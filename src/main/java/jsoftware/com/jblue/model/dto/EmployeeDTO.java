/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

/**
 * OPersonal
 * <br>Clase que modelo objetos segun la tabla personals
 *
 * @author jp
 */
public class EmployeeDTO extends PersonObject {

    private static final long serialVersionUID = 1L;

    public String getEmployeeType() {
        return get("employee_type").toString();
    }

    public String getUser() {
        return get("user").toString();
    }

    public String getPassword() {
        return get("password").toString();
    }
}
