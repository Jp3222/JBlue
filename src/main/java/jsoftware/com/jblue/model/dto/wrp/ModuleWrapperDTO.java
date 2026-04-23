/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class ModuleWrapperDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    /**
     * ID DEL PROCESO
     */
    private final String module_id;

    /**
     * NOMBRE DEL MODULO
     */
    private final String module_name;

    /**
     * EMPLEADO ACTUAL DEL SISTEMA
     */
    private EmployeeUserDTO current_employee;

    /**
     * ADMINISTRACION ACTUAL
     */
    private AdministrationHistoryDTO current_administration;

    /**
     * LISTA DE CONTROLADORES
     */
    private final Map<String, Controller> controller_map;

    public ModuleWrapperDTO(String module_id, String module_name) {
        this.module_id = module_id;
        this.module_name = module_name;
        this.controller_map = new HashMap<>(10);
    }

    public String getModule_id() {
        return module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public EmployeeUserDTO getCurrent_employee() {
        return current_employee;
    }

    public void setCurrent_employee(EmployeeUserDTO current_employee) {
        this.current_employee = current_employee;
    }

    public AdministrationHistoryDTO getCurrent_administration() {
        return current_administration;
    }

    public void setCurrent_administration(AdministrationHistoryDTO current_administration) {
        this.current_administration = current_administration;
    }

    public void putController(String name, Controller controller) {
        controller_map.put(name, controller);
    }

    public Controller getController(String name) {
        return controller_map.get(name);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.module_id);
        hash = 19 * hash + Objects.hashCode(this.module_name);
        hash = 19 * hash + Objects.hashCode(this.current_employee);
        hash = 19 * hash + Objects.hashCode(this.current_administration);
        hash = 19 * hash + Objects.hashCode(this.controller_map);
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
        if (!Objects.equals(this.module_name, other.module_name)) {
            return false;
        }
        if (!Objects.equals(this.current_employee, other.current_employee)) {
            return false;
        }
        if (!Objects.equals(this.current_administration, other.current_administration)) {
            return false;
        }
        return Objects.equals(this.controller_map, other.controller_map);
    }

    @Override
    public String toString() {
        return "ModuleWrapperDTO{" + "module_id=" + module_id + ", module_name=" + module_name + ", current_employee=" + current_employee + ", current_administration=" + current_administration + ", controller_map=" + controller_map + '}';
    }

}
