/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.util.Optional;
import jsoftware.com.jblue.model.dao.InstanceAuthDAO;
import jsoftware.com.jblue.model.dto.InstanceAuthDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class InstanceAuthService extends AbstractService {

    private InstanceAuthDAO dao;

    public InstanceAuthService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
    }

    public Optional<InstanceAuthDTO> get(JDBConnection connection, String uuid) {
        Optional<InstanceAuthDTO> res = Optional.empty();
        try {
            res = dao.getInstance(connection, uuid);
            if (res.isEmpty()) {
                throw new ServiceException(1, "OFICINA NO REGISTRADA");
            }
        } catch (SQLException ex) {
            System.getLogger(InstanceAuthService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ServiceException ex) {
            System.getLogger(InstanceAuthService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
    }
}
