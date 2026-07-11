/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.WaterIntakeUserDAO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class WaterIntakeUserService extends AbstractService {

    private WaterIntakeUserDAO wki_user;
    private HistoryDAO hys;

    public WaterIntakeUserService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        wki_user = new WaterIntakeUserDAO(dev_flag, user_message);
        hys = HistoryDAO.getInstance();
    }

    public boolean saveProcess(JDBConnection connection, WaterIntakeUserDTO dto) {
        boolean res = false;
        try {
            int insert = wki_user.insert(connection, dto);
            res = insert > 0;
            if (!res) {
                returnMessageError("TOMA DE AGUA NO REGISTRADA");
            }
            res = hys.insert(connection, Const.INDEX_WKI_USER, "SE REGISTRO EL TITULAR: %s - %s".formatted(dto.getId(), dto.getUserName()));
            if (!res) {
                returnMessageError("REGISTRO EN BITACORA CORRUPTO");
            }
            res = true;
        } catch (SQLException ex) {
            returnMessageError(ex.getErrorCode(), ex.getMessage());
        } catch (DataAccesObjectException ex) {
            returnMessageError(ex.getErrorCode(), ex.getMessage());
        }
        return res;
    }

}
