/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.wrp.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class EmployeeRegisterService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private EmployeeService employee;
    private EmployeeUserService user;
    private HistoryDAO hys;

    public EmployeeRegisterService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        hys = HistoryDAO.getInstance();
    }

    public boolean insert(JDBConnection connection, EmployeeRegisterWrapperDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            res = hys.startTransaction(connection, Const.INDEX_EMP_USER, "INICIO DE UNA TRANSACCION - EMP");
            if (!res) {
                throw new ServiceException(1, "REGISTRO EN BITACORA CORRUPTO - START_TRANSACTION");
            }
            int employee_id = employee.insert(connection, dto.getEmployee());
            if (employee.isError()) {
                connection.rollBack();
                this.error_code = employee.getErrorCode();
                this.user_message = employee.getUserMessage();
                return false;
            }
            dto.getEmployee_user().put("employee_id", String.valueOf(employee_id));
            //
            int user_id = user.insert(connection, dto.getEmployee_user());
            if (user.isError()) {
                connection.rollBack();
                this.error_code = employee.getErrorCode();
                this.user_message = employee.getUserMessage();
                return false;
            }
            res = hys.endTransaction(connection, Const.INDEX_EMP_USER, "FIN DE UNA TRANSACCION - EMP");
            if (!res) {
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO - END_TRANSACTION");
            }
            connection.commit();
        } catch (SQLException | ServiceException ex) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
