package jsoftware.com.jblue.model.dto;

import java.util.Map;

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

    public UserDTO(Map<String, Object> map) {
        super(map);
    }

    public UserDTO(int size) {
        super(size);
    }

    public UserDTO() {
        super(21);
    }

    public String getStreet1() {
        return get("street1").toString();
    }

    public String getStreet2() {
        return get("street2").toString();
    }

    public String getInsideNumber() {
        return get("inside_number").toString();
    }

    public String getOutsideNumber() {
        return get("outside_number").toString();
    }

    public String getUserType() {
        return get("user_type").toString();
    }

    public String getLastEmployeeUpdate() {
        return get("last_employee_ppdate").toString();
    }

    @Override
    public String toString() {
        return getFirstName().concat(" ")
                .concat(getLastName1())
                .concat(" ")
                .concat(getLastName2());
    }

}
