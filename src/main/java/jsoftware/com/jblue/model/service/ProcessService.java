/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.SequenceDAO;
import jsoftware.com.jblue.model.dto.ProcessDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class ProcessService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private ProcessDAO dao;
    private SequenceDAO sequence_dao;

    public ProcessService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
    }

    public boolean insert(JDBConnection connection, ProcessDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            String sequence = sequence_dao.getNextValString(connection, user_message);
            //SE OBTIENE LA SECUENCIA DEL TRAMITE
            dto.put("sequence", sequence);
            res = dao.startProcess(connection, dto) > 0;
            if (!res) {
                returnMessageError("REGISTRO EN BITACORA CORRUPTO - TRAMITE");
                return false;
            }
            commit(connection);
        } catch (SQLException e) {
            rollback(connection);
            returnMessageError(e.getMessage());
        } catch (DataAccesObjectException e) {
            rollback(connection);
            returnMessageError(e.getErrorCode(), e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
