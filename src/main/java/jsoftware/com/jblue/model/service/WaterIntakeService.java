/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.io.Serializable;
import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
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
    private final HysHistoryDAO history_dao;
    private final ProcessDAO process_dao;
    private final String process_id;

    public WaterIntakeService(String process_id, WaterIntakeDAO user_dao, HysHistoryDAO history_dao, ProcessDAO process_dao) {
        this.process_id = process_id;
        this.wi_dao = user_dao;
        this.history_dao = history_dao;
        this.process_dao = process_dao;
    }

    public WaterIntakeService(String process_id, boolean flag_dev, String name_module) {
        this.process_id = process_id;
        this.wi_dao = new WaterIntakeDAO(flag_dev, name_module);
        this.history_dao = new HysHistoryDAO(flag_dev, name_module);
        this.process_dao = new ProcessDAO(flag_dev, name_module);
    }

    public boolean save(JDBConnection connection, WaterIntakesDTO dto) {
        boolean res = false;
        try {
            connection.setAutoCommit(false);
            //registrar el usuario
            int key = wi_dao.insert(connection, dto);
            res = key > 0;
            if (!res) {
                throw new SQLException("REGISTRO DE USUARIO ERRONEO");
            }
            //registrar el bitacora
            res = history_dao.getHysUsersMovs().insertToUsers("SE REGISTRO LA TOMA: %s - %s".formatted(
                    key,
                    dto.getUserName()
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //registrar el proceso
            res = process_dao.endProcess(connection, process_id, String.valueOf(key));
            if (!res) {
                throw new SQLException("REGISTRO DEL PROCESO CORRUPTO");
            }
            //registrar en bitacora
            res = history_dao.insert(42, "SE REGISTRO EL TRAMITE DE LA TOMA: %s".formatted(
                    key
            ));
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollBack();
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
