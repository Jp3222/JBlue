/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.IOException;
import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.HistoryDAO.WaterIntakeHistoryDAO;
import jsoftware.com.jblue.model.dao.WaterIntakeTypeDAO;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypeDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class WaterIntakeTypeService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private WaterIntakeTypeDAO dao;
    private WaterIntakeHistoryDAO history_dao;

    public WaterIntakeTypeService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        this.dao = new WaterIntakeTypeDAO(dev_flag, user_message);
        this.history_dao = WaterIntakeHistoryDAO.getInstance();
    }

    public boolean insert(JDBConnection connection, WaterIntakeTypeDTO dto, EmployeeUserDTO emp) {
        boolean res = false;
        user_message = null;
        error_code = -1;
        try {
            dto.put("last_employee_update", emp.getId());
            connection.setAutoCommit(false);
            int key = dao.insert(connection, dto);
            res = key > 0;
            if (!res) {
                throw new ServiceException(1, "ERROR AL GUARDAR EL TIPO DE TOMA");
            }
            res = history_dao.insert(connection, "SE REGISTRO EL TIPO DE TOMA: %s - %s".formatted(dto.getId(), dto.getTypeName()));
            if (!res) {
                throw new ServiceException(2, "ERROR AL GUARDAR EL TIPO DE TOMA EN EL HISTORIAL");
            }
            connection.commit();
        } catch (CorruptInsertionException | KeyNotGenerateException ex) {
            user_message = "ERROR AL GUARDAR EL TIPO DE TOMA";
            error_code = ex.getErrorCode();
            connection.rollBack();
            log(ex, ex.getMessage());
        } catch (SQLException ex) {
            user_message = "ERROR AL GUARDAR EL TIPO DE TOMA";
            error_code = ex.getErrorCode();
            connection.rollBack();
            log(ex, ex.getMessage());
        } catch (ServiceException ex) {
            user_message = ex.getUserMessage();
            error_code = ex.getErrorCode();
            connection.rollBack();
            log(ex, ex.getMessage());
        } finally {
            if (Func.isNotNull(connection) && !connection.isClose()) {
                connection.setAutoCommit(true);
            }
        }
        return res;
    }

    public void log(Exception e, String method_name) {
        try {
            FuncLogs.logError(
                    AppFiles.DIR_PROG_LOG_TODAY,
                    getClass(), e,
                    getClass().getName(),
                    method_name,
                    e.getMessage()
            );
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
