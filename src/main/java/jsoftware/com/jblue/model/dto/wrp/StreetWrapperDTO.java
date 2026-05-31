/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import java.util.Objects;
import jsoftware.com.jblue.model.dto.StreetDTO;

/**
 *
 * @author juanp
 */
public class StreetWrapperDTO extends ModuleWrapperDTO {

    private static final long serialVersionUID = 1L;

    private final StreetDTO street;
    private boolean valid;

    public StreetWrapperDTO(String module_id, String module_name) {
        super(module_id, module_name);
        street = new StreetDTO();
        valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public StreetDTO getStreet() {
        return street;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.street);
        hash = 29 * hash + (this.valid ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StreetWrapperDTO other = (StreetWrapperDTO) obj;
        if (this.valid != other.valid) {
            return false;
        }
        return Objects.equals(this.street, other.street);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("StreetWrapperDTO{");
        sb.append("street=").append(street);
        sb.append(", valid=").append(valid);
        sb.append('}');
        return sb.toString();
    }

}
