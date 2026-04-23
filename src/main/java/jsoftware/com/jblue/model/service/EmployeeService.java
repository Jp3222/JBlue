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
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class EmployeeService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private EmployeeDAO dao;
    private EmployeeHistoryDAO hys;

    public EmployeeService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        dao = new EmployeeDAO(dev_flag, user_message);
        hys = EmployeeHistoryDAO.getInstance();
    }

    public int insert(JDBConnection connection, EmployeeDTO dto) {
        int pk = 0;
        boolean res = false;
        try {
            //REGISTRO DE EMPLEADO
            pk = dao.insert(connection, dto);
            res = pk > 0;
            if (!res) {
                throw new ServiceException(1, "LOS DATOS DEL EMPLEADO NO SE HAN REGISTRADO CORRECTAMENTE");
            }
            //REGISTRO EN BITACORA
            res = hys.insert(connection, "SE REGISTRO EL EMPLEADO: %s - %s");
            if (!res) {
                throw new ServiceException(1, "REGISTRO EN BITACORA CORRUPTO");
            }
        } catch (SQLException ex) {
            System.getLogger(EmployeeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ServiceException ex) {
            System.getLogger(EmployeeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return pk;
    }
}
