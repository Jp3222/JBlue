/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.AddressDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class AddressService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private AddressDAO dao;
    private HistoryDAO hys;

    public AddressService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        dao = new AddressDAO(dev_flag, process_name);
        hys = HistoryDAO.getInstance();
    }

    public boolean insert(JDBConnection connection, AddressDTO dto) {
        boolean res = false;
        try {
            res = dao.exists(connection, dto);
            if (res) {
                return res;
            }
            res = dao.insert(connection, dto);
            if (!res) {
                returnMessageError("NO SE PUDO REGISTRAR LA CALLE");
                return res;
            }
            res = hys.insert(connection, Const.INDEX_USR_ADDRESS, "SE REGISTRO EL DOMICILIO NO: %s".formatted(dto.getId()));
            if (!res) {
                returnMessageError("REGISTRO EN BITACORA CORRUPTO");
                return res;
            }
        } catch (SQLException e) {
            returnMessageError(e.getErrorCode(), e.getMessage());
            log(e, "insert");
        } catch (CorruptInsertionException e) {
            returnMessageError(e.getErrorCode(), e.getUserMessage());
            log(e, "insert");
        }
        return res;
    }
}
