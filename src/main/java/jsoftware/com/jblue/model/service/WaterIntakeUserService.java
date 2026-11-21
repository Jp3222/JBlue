/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.WaterIntakeUserDAO;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.dto.wrp.ShopCartWrapperDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.utils.platf.FuncLogs;
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

    public boolean saveProcess(JDBConnection connection, WaterIntakeUserDTO dto, WaterIntakeUserDTO old_dto) {
        boolean res = false;
        try {
            int type = dto.getLast_process_id();
            switch (type) {
                case 1, 2, 3://ALTAS
                    //SI ES DIFERENTE DE 1 ES CONSUMIDOR 
                    if (type != 1) {
                        dto.setIs_consumer(true);
                    }
                    //SE USA EL MISMO METODO PARA REGISTRAR CUALQUIER TOMA EN EL PADRON
                    return registerOwner(connection, dto);
                case 16://CAMBIO DE PROPIETARIO
                    return updateOwnerChange(connection, dto, old_dto.getId());
                case 4://DESCONEXION DE TOMA
                    return updateStatusDesconected(connection, dto);
                case 5://RECONEXION DE TOMA
                    return updateStatusReconected(connection, dto);
                case 6, 7://BAJAS DE TITULAR Y CONSUMIDOR
                    return updateStatus(connection, dto, "2");
                case 8, 9, 10, 11, 12://MOVIMIENTOS ADICIONALES
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
        dto.setDescription(obs);
        int insert = wki_user.insert(connection, dto);
        res = insert > 0;
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE REGISTRO EL TITULAR: %s - %s".formatted(dto.getId(), dto.getUser_name()));
        if (!res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        res = true;
        return res;
    }

    private boolean updateOwnerChange(JDBConnection connection, WaterIntakeUserDTO dto, int old_user) throws SQLException, DataAccesObjectException {
        boolean res = false;
        String obs = dto.getDescription();
        obs += "CAMBIO DE PROPIETARIO";
        dto.setDescription(obs);
        res = wki_user.updateOwnerChange(connection, dto, old_user);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE ACTUALIZO EL TITULAR DE LA TOMA: %s - %s".formatted(dto.getId(), dto.getUser_name()));
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
        dto.setDescription(obs);
        res = wki_user.updateStatusDesconected(connection, dto);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE DESCONECTO LA TOMA: %s - %s".formatted(dto.getId(), dto.getUser_name()));
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
        dto.setDescription(obs);
        res = wki_user.updateStatusReconected(connection, dto);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE RECONECTO LA TOMA: %s - %s".formatted(dto.getId(), dto.getUser_name()));
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
        dto.setDescription(obs);
        res = wki_user.updateStatus(connection, dto, new_status);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE DIO DE BAJA LA TOMA: %s - %s".formatted(dto.getId(), dto.getUser_name()));
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
        dto.setDescription(obs);
        res = wki_user.addMov(connection, dto);
        if (!res) {
            returnMessageError("TOMA DE AGUA NO REGISTRADA");
        }
        res = hys.insert(connection, Const.INDEX_WKI_USER, "SE REGISTRO EL TITULAR: %s - %s".formatted(dto.getId(), dto.getUser_name()));
        if (!res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        res = true;
        return res;
    }

    public List<WaterIntakeUserDTO> search(JDBConnection connection, SystemSession ss, ShopCartWrapperDTO dto) throws SQLException {
        return wki_user.search(connection, dto.getWki_user().getUser_name());
    }

    public boolean updateLastPayment(JDBConnection connection, SystemSession ss, WaterIntakeUserDTO dto) {
        boolean res = false;
        try {
            res = this.wki_user.updateLastPayment(connection, dto);
            if (!res) {
                return returnMessageError("ULTIMO PAGO NO ACTUALIZADO");
            }
//            res = hys.insert(connection, Const.INDEX_WKI_USER, "SE ACTUALIZO EL ULT PAGO: %s - %s".formatted(dto.getId(), dto.getUserName()));
//            if (!res) {
//                returnMessageError("REGISTRO EN BITACORA CORRUPTO");
//            }
        } catch (SQLException ex) {
            FuncLogs.log(getClass(), ex, process_name, "", ex.getMessage());
            return returnMessageError(ex.getMessage());
        }
        return res;
    }
}
