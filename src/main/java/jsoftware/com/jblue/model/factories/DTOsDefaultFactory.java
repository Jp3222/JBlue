/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import jsoftware.com.jblue.model.dto.BlockedUserDTO;
import jsoftware.com.jblue.util.Func;

/**
 *
 * @author juanp
 */
public class DTOsDefaultFactory {
    private static DTOsDefaultFactory instance;

    public synchronized static DTOsDefaultFactory getInstance() {
        if (Func.isNull(instance)) {
            instance = new DTOsDefaultFactory();
        }
        return instance;
    }
    
    
    public BlockedUserDTO getProcessLockDTO(String employee_id, String office_id) {
        BlockedUserDTO dto = new BlockedUserDTO();
        dto.put("apply_unlocked", "1");
        dto.put("description_update", "BLOQUEO GENERADO POR INFORMACION EXISTENTE");
        dto.put("description_register", "TRAMITE DE USUARIO BLOQUEADO");
        dto.put("observation_lock", "EL USUARIO TIENE INFORMACION EN OTRO PADRON, POR LO QUE DEBERA VERIFICAR ESTA INFORMACION ANTES DE CONTINUAR EL TRAMITE");
        dto.put("office_lock", office_id);
        dto.put("employee_register", employee_id);
        dto.put("status", "1");
        dto.put("last_employee_update", employee_id);
        return dto;
    }
}
