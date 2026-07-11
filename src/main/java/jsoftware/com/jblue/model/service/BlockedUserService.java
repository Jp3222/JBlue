/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.BlockedUserDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.BlockedUserDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class BlockedUserService extends AbstractService {

    private static final long serialVersionUID = 1L;
    private BlockedUserDAO dao;

    public BlockedUserService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
    }

    public boolean save(JDBConnection connection, BlockedUserDTO dto) {
        boolean res = false;
        try {
            res = dao.insert(connection, dto);
            if (!res) {
                returnMessageError(1, "EL REGISTRO DE BLOQUEO NO PUDO SER REALIZADO");
            }
            res = HistoryDAO.getInstance().insert(connection, Const.INDEX_USR_USER, "EL USUARIO %s FUE BLOQUEADO");
            if (!res) {
                returnMessageError(2, "EL REGISTRO DE BLOQUEO EN BITACORA NO PUDO SER REALIZADO");
            }
            res = true;
        } catch (SQLException ex) {
            returnMessageError(ex.getMessage());
        } catch (CorruptInsertionException | KeyNotGenerateException ex) {
            returnMessageError(ex.getErrorCode(), ex.getUserMessage());
        }
        return res;
    }

    public void update() {
    }

    public void exist(JDBConnection connection, String id, String usr_id) {
        
    }
}
