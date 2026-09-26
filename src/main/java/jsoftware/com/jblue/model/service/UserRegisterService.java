/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.dto.ProcessDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.wrp.UserRegisterWrapperDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class UserRegisterService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private UserService user_service;
    private AddressService address_service;
    private ProcessDAO process_dao;

    public UserRegisterService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
    }

    public boolean save(JDBConnection connection, SystemSession ss, UserRegisterWrapperDTO dto) {
        boolean res = false;
        try {

            ProcessDTO process = dto.getProcess();
            res = process_dao.startProcess(connection, process);
            if (!res) {
                return returnMessageError(1, "ERROR AL REGISTRAR EL TRAMITE");
            }

            UserDTO user = dto.getUser();
            res = user_service.save(connection, ss, user);
            if (!res) {
                return returnMessageError(1, "ERROR AL REGISTRAR EL CONTRIBUYENTE");
            }

            AddressDTO address = dto.getAddress();
            res = address_service.insert(connection, address);
            if (!res) {
                return returnMessageError(1, "ERROR AL REGISTRAR EL DOMICILIO");
            }
            commit(connection);
            return returnMessageError(SERVICE_EXECUTE_OK, "OPERACION EXITOSA");
        } catch (SQLException ex) {
            res = false;
            returnMessageError(-1, ex.getMessage());
            rollback(connection);
        } catch (KeyNotGenerateException | CorruptInsertionException ex) {
            res = false;
            returnMessageError(SERVICE_EXECUTE_ERROR, ex.getUserMessage());
            rollback(connection);
        }
        return res;
    }
}
