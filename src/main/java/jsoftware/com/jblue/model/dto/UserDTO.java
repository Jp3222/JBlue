package jsoftware.com.jblue.model.dto;

/**
 * Data Transfer Object (DTO) para la tabla usr_user que almacena todos los
 * datos como pares clave-valor dentro de un Map. Las claves son los nombres
 * exactos de las columnas SQL.
 *
 * NOTA: Los métodos getString y getInteger son duplicados aquí para mantener la
 * estructura original, pero idealmente deberían estar en AbstractMapDTO.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 */
public class UserDTO extends PersonObject implements Cloneable {

    private static final long serialVersionUID = 1L;

    public String getStreet1() {
        return values.get("street1").toString();
    }

    public String getStreet2() {
        return values.get("street2").toString();
    }

    public String getInsideNumber() {
        return values.get("inside_number").toString();
    }

    public String getOutsideNumber() {
        return values.get("outside_number").toString();
    }

    public String getWaterIntakeType() {
        return values.get("water_intake_type").toString();
    }

    public String getUserType() {
        return values.get("user_type").toString();
    }

    public String getLastEmployeeUpdate() {
        return values.get("last_employee_ppdate").toString();
    }

    @Override
    public UserDTO clone()  {
        try {
            return (UserDTO) super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        } catch (CloneNotSupportedException ex) {
            System.getLogger(UserDTO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

}
