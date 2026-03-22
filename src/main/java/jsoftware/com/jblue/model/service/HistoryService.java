/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.HistoryDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public class HistoryService extends AbstractService {

    private static final long serialVersionUID = 1L;
    private HistoryDAO history_dao;

    public HistoryService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        history_dao = HistoryDAO.getNewInstance(dev_flag, user_message);
    }

    public void select(JDBConnection connection, EmployeeUserDTO employee, JTableModel model) {
        connection.setAutoCommit(false);
        boolean res = false;
        try {
            res = history_dao.select(connection,
                    Const.INDEX_HYS_PROGRAM_HISTORY,
                    "EL EMPLEADO %s CONSULTO SU HISTORIAL".formatted(
                            employee.getDescription())
            );
            if (!res) {
                throw new ServiceException(1, "ERROR AL REGISTRAR EL MOVIMIENTO");
            }
            List<HistoryDTO> list = history_dao.getList(connection, model);
            if (list.isEmpty()) {
                error_code = 1;
                user_message = "SIN DATOS PARA MOSTRAR";
            }
        } catch (SQLException e) {
            error_code = -1;
            user_message = "CONSULTA ERRONEA";
            connection.rollBack();
        } catch (ServiceException e) {
            error_code = -2;
            user_message = "CONSULTA ERRONEA";
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
