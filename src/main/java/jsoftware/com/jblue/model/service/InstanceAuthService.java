/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import jsoftware.com.jblue.model.dao.InstanceAuthDAO;
import jsoftware.com.jblue.model.dto.InstanceAuthDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class InstanceAuthService extends AbstractService {

    private InstanceAuthDAO dao;

    public InstanceAuthService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        dao = new InstanceAuthDAO(dev_flag, process_name);
    }

    public Optional<InstanceAuthDTO> get(JDBConnection connection, String uuid, String progra_user) {
        Optional<InstanceAuthDTO> res = Optional.empty();
        try {
            res = dao.getInstance(connection, uuid);
            if (res.isEmpty()) {
                throw new ServiceException(1, "OFICINA NO REGISTRADA");
            }
            InstanceAuthDTO instance = res.get();
            if (!progra_user.equals(instance.getDbUser())) {
                throw new ServiceException(2, "CREDENCIAL DE ACCESO CORRUPTA");
            }
            SystemSession sys = SystemSession.getInstancia();
            sys.setCurrent_instance(instance);
        } catch (SQLException ex) {
            user_message = "HA OCURRIDO UN ERROR INESPERADO";
            error_code = ex.getErrorCode();
            log(ex, "get");
        } catch (ServiceException ex) {
            user_message = ex.getUserMessage();
            error_code = ex.getErrorCode();
            log(ex, "get");
        }
        return res;
    }

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(AppFiles.DIR_PROG_LOG_TODAY, getClass(), e, getProcess_name(), method_name, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

}
