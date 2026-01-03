/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dto;

import java.util.Map;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juanp
 */
public class StatusDTO extends JDBMapObject {

    private static final long serialVersionUID = 1L;

    public StatusDTO() {
        super(6);
    }

    public StatusDTO(Map<String, Object> map) {
        super(map);
    }

    public String getDescription() {
        return get("description").toString();
    }

    public String getAffectedGroup() {
        return get("affected_group").toString();
    }

    public String getDateUpdate() {
        return get("date_update").toString();
    }

    public String getDateRegister() {
        return get("date_register").toString();
    }

    public String getDateFinalize() {
        return get("date_finalize").toString();
    }

}
