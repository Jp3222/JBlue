/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto.wrp;

import java.util.Map;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;

/**
 *
 * @author juanp
 */
public class AdministrationWrapperDTO extends ModuleWrapperDTO {

    private AdministrationHistoryDTO administration_history;

    public AdministrationWrapperDTO(String module_id, String module_name) {
        super(module_id, module_name);
    }

    public AdministrationHistoryDTO getAdministration_history() {
        return administration_history;
    }

    public void setAdministration_history(AdministrationHistoryDTO administration_history) {
        this.administration_history = administration_history;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }

}
