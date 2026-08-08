/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.factories;

import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.l4b.ProcessValidators;
import jsoftware.com.jblue.model.validators.OwnerRegisterProcessValidator;

/**
 *
 * @author juanp
 */
public class ProcessValidatosFactory {

    public static ProcessValidators ProcessValidatosFactory(ProcessWrapperDTO dto) {
        if (dto.getProcess().getProcessType().equals("1")) {
            return new OwnerRegisterProcessValidator(dto);
        }
        return null;
    }
}
