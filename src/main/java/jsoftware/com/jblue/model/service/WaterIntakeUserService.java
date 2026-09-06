/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.WaterIntakeUserDAO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class WaterIntakeUserService extends AbstractService {

    private final WaterIntakeUserDAO wki_user;
    private final HistoryDAO hys;

    public WaterIntakeUserService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        wki_user = new WaterIntakeUserDAO(dev_flag, user_message);
        hys = HistoryDAO.getInstance();
    }

    public boolean saveProcess(JDBConnection connection, WaterIntakeUserDTO dto) {
        boolean res = false;
        try {
            String type = dto.getLastProcessId();
            switch (type) {
                case "1", "2", "3"://ALTAS
                    //SI ES DIFERENTE DE 1 ES CONSUMIDOR 
                    if (!type.equals("1")) {
                        dto.put("is_consumer", "1");
                    }
                    //SE USA EL MISMO METODO PARA REGISTRAR CUALQUIER TOMA EN EL PADRON
                    return registerOwner(connection, dto);
                case "16"://CAMBIO DE PROPIETARIO
                    return updateOwnerChange(connection, dto);
                case "4"://DESCONEXION DE TOMA
                    return updateStatusDesconected(connection, dto);
                case "5"://RECONEXION DE TOMA
                    return updateStatusReconected(connection, dto);
                case "6", "7"://BAJAS DE TITULAR Y CONSUMIDOR
                    return updateStatus(connection, dto, "2");
                case "8", "9", "10", "11", "12"://MOVIMIENTOS ADICIONALES
                    return addMov(connection, dto);
                default:
                    throw new AssertionError();
            }
        } catch (SQLException ex) {
            returnMessageError(ex.getErrorCode(), ex.getMessage());
        } catch (DataAccesObjectException ex) {
            returnMessageError(ex.getErrorCode(), ex.getMessage());
        }
        return res;
    }

    private boolean registerOwner(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException, DataAccesObjectException {
        boolean res = false;
        String obs = dto.getDescription();
        obs += "REGISTRO DE NUEVA TOMA DE AGUA POTABLE";
        dto.put("description", obs);
        int insert = wki_user.insert(connection, dto);
        res = insert > 0;
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE REGISTRO EL TITULAR: %s - %s".formatted(dto.getId(), dto.getUserName()));
        if (!res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        res = true;
        return res;
    }

    private boolean updateOwnerChange(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException, DataAccesObjectException {
        boolean res = false;
        String obs = dto.getDescription();
        obs += "CAMBIO DE PROPIETARIO";
        dto.put("description", obs);
        res = wki_user.updateOwnerChange(connection, dto, dto.get("old_user_id").toString());
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE ACTUALIZO EL TITULAR DE LA TOMA: %s - %s".formatted(dto.getId(), dto.getUserName()));
        if (!res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        res = true;
        return res;
    }

    private boolean updateStatusDesconected(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException, DataAccesObjectException {
        boolean res = false;
        String obs = dto.getDescription();
        obs += "MOVIMIENTO DE DESCONEXION";
        dto.put("description", obs);
        res = wki_user.updateStatusDesconected(connection, dto);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE DESCONECTO LA TOMA: %s - %s".formatted(dto.getId(), dto.getUserName()));
        if (!res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        res = true;
        return res;
    }

    private boolean updateStatusReconected(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException, DataAccesObjectException {
        boolean res = false;
        String obs = dto.getDescription();
        obs += "MOVIMIENTO DE RECONEXION";
        dto.put("description", obs);
        res = wki_user.updateStatusReconected(connection, dto);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE RECONECTO LA TOMA: %s - %s".formatted(dto.getId(), dto.getUserName()));
        if (!res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        res = true;
        return res;
    }

    private boolean updateStatus(JDBConnection connection, WaterIntakeUserDTO dto, String new_status) throws SQLException, DataAccesObjectException {
        boolean res = false;
        String obs = dto.getDescription();
        obs += "MOVIMIENTO DE BAJA";
        dto.put("description", obs);
        res = wki_user.updateStatus(connection, dto, new_status);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE DIO DE BAJA LA TOMA: %s - %s".formatted(dto.getId(), dto.getUserName()));
        if (!res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        res = true;
        return res;
    }

    private boolean addMov(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException, DataAccesObjectException {
        boolean res = false;
        String obs = dto.getDescription();
        obs += "MOVIMIENTO ADICIONAL";
        dto.put("description", obs);
        res = wki_user.addMov(connection, dto);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE REGISTRO EL TITULAR: %s - %s".formatted(dto.getId(), dto.getUserName()));
        if (!res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        res = true;
        return res;
    }
}
