/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.StreetDAO;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.dto.wrp.StreetWrapperDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.imp.CorruptUpdateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class StreetService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final StreetDAO dao;
    private final HistoryDAO hys;

    public StreetService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        dao = new StreetDAO(dev_flag, user_message);
        hys = HistoryDAO.getInstance();
    }

    public boolean save(JDBConnection connection, SystemSession ss, StreetWrapperDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            String final_employee = ss.getCurrentEmployee().getId();
            String final_colony = ss.getCurrent_instance().getColonyId();

            //REGISTRO DEL OBJETO
            StreetDTO street = dto.getStreet();
            street.put("colony_id", final_colony);
            street.put("employee_last_update", final_employee);
            res = dao.insert(connection, street);
            if (!res) {
                return returnMessageError(connection, "LA CALLE " + street.getStreetName() + " NO PUDO SER REGISTRADA");
            }

            //REGISTRO EN BITACORA
            res = hys.insert(
                    connection,
                    Const.INDEX_INSERT,
                    "SE REGISTRO LA CALLE: %s - %s".formatted(street.getId(), street.getStreetName())
            );
            if (!res) {
                return returnMessageError(connection, "REGISTRO EN BITACORA CORRUPTO");

            }
            res = true;
            connection.commit();
            returnMessageError(SERVICE_EXECUTE_OK, "OPERACION EXITOSA");
        } catch (SQLException e) {
            returnMessageError(connection, e.getMessage());
        } catch (DataAccesObjectException e) {
            returnMessageError(connection, e.getUserMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    public boolean update(JDBConnection connection, SystemSession ss, StreetWrapperDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            StreetDTO street = dto.getStreet();
            street.put("employee_last_update", dto.getCurrent_employee().getId());
            res = dao.update(connection, street, street);
            if (!res) {
                returnMessageError("LA ACTUALIZACION NO PUDO LLEVARSE ACABO");
                rollback(connection);
                return false;
            }
            res = hys.update(
                    connection,
                    Const.INDEX_INSERT,
                    "SE ACTUALIZO LA CALLE: %s - %s".formatted(street.getId(), street.getStreetName())
            );
            if (!res) {
                returnMessageError("ACTUALIZACION EN BITACORA CORRUPTO");
                rollback(connection);
                return false;
            }
            res = true;
            connection.commit();
        } catch (SQLException e) {
            returnMessageError(e.getMessage());
        } catch (CorruptUpdateException e) {
            returnMessageError(e.getErrorCode(), e.getUserMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    public boolean delete(JDBConnection connection, SystemSession ss, StreetWrapperDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            StreetDTO street = dto.getStreet();
            street.put("colony_id", ss.getCurrent_instance().getColonyId());
            street.put("status", String.valueOf(Const.INDEX_STATUS_ELIMINADO_3));
            street.put("employee_last_update", dto.getCurrent_employee().getId());
            res = dao.insert(connection, street);
            if (!res) {
                returnMessageError("EL REGISTRO NO PUDO LLEVARSE ACABO");
                rollback(connection);
                return false;
            }
            res = hys.delete(
                    connection,
                    Const.INDEX_INSERT,
                    "SE ELIMINO LA CALLE: %s - %s".formatted(street.getId(), street.getStreetName())
            );
            if (!res) {
                returnMessageError("REGISTRO EN BITACORA CORRUPTO");
                rollback(connection);
                return false;
            }
            res = true;
            connection.commit();
        } catch (SQLException e) {
            returnMessageError(e.getMessage());
        } catch (DataAccesObjectException e) {
            returnMessageError(e.getErrorCode(), e.getUserMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
