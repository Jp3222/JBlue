/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.net.UnknownHostException;
import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.EmployeeDAO;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.dto.ProgramHistoryDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
/**
 *
 * @author juanp
 */
public class EmployeeService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private EmployeeDAO employee_dao;
    //private EmployeeHistoryDAO history_dao;
    private HistoryService history;

    public EmployeeService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        employee_dao = new EmployeeDAO(dev_flag, user_message);
        //history_dao = EmployeeHistoryDAO.getInstance();
        history = new HistoryService(dev_flag, process_name);
    }

    public boolean insert(JDBConnection connection, SystemSession ss, EmployeeDTO dto) {
        boolean res = false;
        try {
            //REGISTRO DE EMPLEADO
            res = employee_dao.insert(connection, dto);
            if (!res) {
                returnMessageError(1, "LOS DATOS DEL EMPLEADO NO SE HAN REGISTRADO CORRECTAMENTE");
            }
            //HISTORIAL
            ProgramHistoryDTO hys = ss.getProgramHistoryDTO(8, Integer.parseInt(dto.getId()));
            hys.setDescription("SE REGISTRO EL USUARIO: " + dto.getId() + " - " + dto.toString());
            res = history.insert(connection, ss, hys);
            if (!res) {
                returnMessageError(1, "REGISTRO EN BITACORA CORRUPTO");
            }
            return returnMessageError(SERVICE_EXECUTE_OK, "OPERACION EXITOSA");
        } catch (SQLException ex) {
            log(ex, "insert");
            res = returnMessageError(ex.getErrorCode(), ex.getMessage());
        } catch (ServiceException ex) {
            res = returnMessageError(ex.getErrorCode(), ex.getUserMessage());
        } catch (CorruptInsertionException | KeyNotGenerateException ex) {
            res = returnMessageError(ex.getErrorCode(), ex.getMessage());
        } catch (UnknownHostException ex) {
            res = returnMessageError(ex.getMessage());
        }
        return res;
    }
}
