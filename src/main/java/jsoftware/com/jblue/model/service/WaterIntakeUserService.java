/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import jsoftware.com.jblue.model.dao.WaterIntakeUserDAO;
import jsoftware.com.jblue.model.dto.wrp.ProcessWrapperDTO;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class WaterIntakeUserService extends AbstractService {

    private WaterIntakeUserDAO wki_user;

    public WaterIntakeUserService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        wki_user = new WaterIntakeUserDAO(dev_flag, user_message);
    }

    public void saveProcess(JDBConnection connection, String process_type, ProcessWrapperDTO dto) {

    }

}
