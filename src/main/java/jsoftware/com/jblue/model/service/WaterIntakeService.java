/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.Serializable;
import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.WaterIntakeDAO;
import jsoftware.com.jblue.model.dto.WaterIntakesDTO;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class WaterIntakeService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final WaterIntakeDAO wi_dao;
    private final ProcessDAO process_dao;

    public WaterIntakeService(String process_id, WaterIntakeDAO user_dao, ProcessDAO process_dao) {
        this.wi_dao = user_dao;
        this.process_dao = process_dao;
    }

    public WaterIntakeService(boolean flag_dev, String name_module) {
        this.wi_dao = new WaterIntakeDAO(flag_dev, name_module);
        this.process_dao = new ProcessDAO(flag_dev, name_module);
    }

    public boolean save(JDBConnection connection, String process_type, WaterIntakesDTO dto) throws SQLException {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            //[1]REGISTRA LOS DATOS DE LA NUEVA TOMA DE AGUA POTABLE]
            int key = wi_dao.insert(connection, dto);
            res = key > 0;
            if (!res) {
                throw new SQLException("REGISTRO DE USUARIO ERRONEO");
            }
            //REGISTRO EN BITACORA
            res = HistoryDAO.getInstance().insert(connection, Const.INDEX_WKI_WATER_INTAKES, "SE REGISTRO LA TOMA: %s - %s".formatted(
                    key,
                    dto.getUserName()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //[2]ACTUALIZA EL STATUS A FINALIZADO
            res = process_dao.endProcess(connection, process_type, String.valueOf(key));
            if (!res) {
                throw new SQLException("REGISTRO DEL PROCESO CORRUPTO");
            }
            //REGISTRA EN BITACORA
            res = HistoryDAO.ProcessHistoryDAO.getInstance().insert(connection, "SE REGISTRO EL TRAMITE DE LA TOMA: %s".formatted(
                    key
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
            throw e;
        } catch (Exception e) {
            connection.rollBack();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
