/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.EmployeeUserDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO.EmployeeUserHistoryDAO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class EmployeeUserService extends AbstractService {

    private EmployeeUserDAO dao;
    private EmployeeUserHistoryDAO hys;

    public EmployeeUserService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        dao = new EmployeeUserDAO(dev_flag, user_message);
        hys = EmployeeUserHistoryDAO.getInstance();
    }

    public int insert(JDBConnection connection, EmployeeUserDTO dto) {
        int pk = 0;
        boolean res = false;
        try {
            //REGISTRO DE EMPLEADO
            pk = dao.insert(connection, dto);
            res = pk > 0;
            if (!res) {
                throw new ServiceException(1, "LOS DATOS DEL USUARIO NO SE HAN REGISTRADO CORRECTAMENTE");
            }
            //REGISTRO EN BITACORA
            res = hys.insert(connection, "SE REGISTRO EN EL PADRON DE EMPLEADOS: %s - %s");
            if (!res) {
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO");
            }
        } catch (SQLException | ServiceException ex) {
            System.getLogger(EmployeeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (CorruptInsertionException | KeyNotGenerateException ex) {
            System.getLogger(EmployeeUserService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return pk;
    }
}
