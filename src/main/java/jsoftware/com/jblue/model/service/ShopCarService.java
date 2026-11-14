/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.util.List;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.wrp.ShopCartWrapperDTO;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class ShopCarService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final WaterIntakeUserService wki_service;

    public ShopCarService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        wki_service = new WaterIntakeUserService(dev_flag, process_name);
    }

    public boolean calculation(JDBConnection connection, SystemSession ss, ShopCartWrapperDTO dto) {
        return false;
    }

    public boolean payment(JDBConnection connection, SystemSession ss, ShopCartWrapperDTO dto) {
        return false;
    }

    public List<WaterIntakeUserDTO> search(JDBConnection connection, SystemSession ss, ShopCartWrapperDTO dto) {
        List<WaterIntakeUserDTO> res = wki_service.search(connection, ss, dto);
        return res;
    }
}
