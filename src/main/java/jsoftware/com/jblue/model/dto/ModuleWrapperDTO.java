/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.util.Objects;
import jsoftware.com.jutil.model.AbstractMapDTO;

/**
 *
 * @author juanp
 */
public class ModuleWrapperDTO extends AbstractMapDTO {

    private static final long serialVersionUID = 1L;

    private String module_id;
    private String module_name;

    public ModuleWrapperDTO() {
        this.module_id = null;
        this.module_name = null;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
        put("module_id", module_id);
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
        put("module_name", module_name);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.module_id);
        hash = 53 * hash + Objects.hashCode(this.module_name);
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
        final ModuleWrapperDTO other = (ModuleWrapperDTO) obj;
        if (!Objects.equals(this.module_id, other.module_id)) {
            return false;
        }
        return Objects.equals(this.module_name, other.module_name);
    }

    @Override
    public String toString() {
        return values.toString();
    }

}
