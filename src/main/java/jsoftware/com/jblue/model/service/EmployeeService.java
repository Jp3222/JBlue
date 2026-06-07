/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.EmployeeDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO.EmployeeHistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class EmployeeService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private EmployeeDAO employee_dao;
    private EmployeeHistoryDAO history_dao;

    public EmployeeService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        employee_dao = new EmployeeDAO(dev_flag, user_message);
        history_dao = EmployeeHistoryDAO.getInstance();
    }

    public int insert(JDBConnection connection, EmployeeDTO dto) {
        int pk = 0;
        boolean res = false;
        try {
            //REGISTRO DE EMPLEADO
            pk = employee_dao.insert(connection, dto);
            res = pk > 0;
            if (!res) {
                throw new ServiceException(1, "LOS DATOS DEL EMPLEADO NO SE HAN REGISTRADO CORRECTAMENTE");
            }
            //REGISTRO EN BITACORA
            res = history_dao.insert(connection, "SE REGISTRO EL EMPLEADO: %s - %s");
            if (!res) {
                throw new ServiceException(1, "REGISTRO EN BITACORA CORRUPTO");
            }
        } catch (SQLException ex) {
            error_code = 0;
            user_message = "";
        } catch (ServiceException ex) {
            System.getLogger(EmployeeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (CorruptInsertionException ex) {
            System.getLogger(EmployeeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (KeyNotGenerateException ex) {
            System.getLogger(EmployeeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return pk;
    }
}
