/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.IOException;
import jsoftware.com.jblue.model.dao.EmployeeDAO;
import jsoftware.com.jblue.model.dao.EmployeeUserDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO.EmployeeHistoryDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO.EmployeeUserHistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeRegisterWrapperDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class EmployeeService extends AbstractService {

    private EmployeeDAO employee_dao;
    private EmployeeUserDAO employee_user_dao;
    private EmployeeHistoryDAO history_employee_dao;
    private EmployeeUserHistoryDAO history_employee_user_dao;

    public EmployeeService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        employee_dao = new EmployeeDAO(dev_flag, user_message);
        employee_user_dao = new EmployeeUserDAO(dev_flag, user_message);
        history_employee_dao = EmployeeHistoryDAO.getInstance();
        history_employee_user_dao = EmployeeUserHistoryDAO.getInstance();
    }

    public void insert(JDBConnection connection, EmployeeRegisterWrapperDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            int employee_id = employee_dao.insert(connection, dto.getEmployee());
            res = employee_id > 0;
            if (!res) {
                throw new ServiceException(1, "ERROR AL REGISTRAR DATOS DEL EMPLEADO");
            }
            res = history_employee_dao.insert(connection,
                    "SE REGISTRARON LOS DATOS DEL EMPLEADO: %s - %s".formatted(
                            String.valueOf(employee_id),
                            dto.getEmployee_user().getDescription()
                    )
            );
            if (!res) {
                throw new ServiceException(1, "ERROR AL REGISTRAR DATOS DEL EMPLEADO");
            }
            int employee_user_id = employee_user_dao.insert(connection, dto.getEmployee_user());
            res = employee_user_id > 0;
            if (!res) {
                throw new ServiceException(2, "ERROR AL REGISTRAR EL USUARIO DEL EMPLEADO");
            }
            res = history_employee_dao.insert(connection,
                    "SE REGISTRO EL USUARIO DEL EMPLEADO: %s - %s".formatted(
                            String.valueOf(employee_user_id),
                            dto.getEmployee_user().getDescription()
                    )
            );
            if (!res) {
                throw new ServiceException(1, "ERROR AL REGISTRAR DATOS DEL EMPLEADO");
            }
            connection.commit();
        } catch (Exception e) {
            log(e, "insert");
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, getProcess_name(), method_name, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void log(String message) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, "MAIN", message);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
