/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.dto.UserDTO;

/**
 *
 * @author juanp
 */
public final class UserRegisterWrapperDTO extends ProcessWrapperDTO {

    private static final long serialVersionUID = 1L;

    private UserDTO user;
    private AddressDTO address;

    /**
     * Constructor para el proceso de registro de titular.
     *
     * @param module_id ID del módulo desde Swing
     * @param module_name Nombre del módulo
     * @param currentAdminId ID de la administración activa (Inyectado)
     * @param currentEmployeeId ID del empleado en sesión (Inyectado)
     */
    public UserRegisterWrapperDTO(String module_id, String module_name) {
        // El constructor padre ya ejecuta this.clear() e inicializa process = new ProcessDTO();
        super(module_id, module_name, 3, "REGISTRO DEL CONTRIBUYENTE POR MODULO DE REGISTRO DE USUARIOS");
        // Asignación homogénea usando la estructura de Map del DTO
        this.process.put("process_type", "1");
    }

    @Override
    public void clear() {
        super.clear(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.process.put("process_type", "17");
    }

}
