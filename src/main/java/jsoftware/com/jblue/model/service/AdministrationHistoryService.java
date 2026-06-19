/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.time.LocalDate;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.AdministrationHistoryDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.AdministrationHistoryDTO;
import jsoftware.com.jblue.model.dto.wrp.AdministrationWrapperDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class AdministrationHistoryService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private AdministrationHistoryDAO dao;
    private HistoryDAO hys;

    public AdministrationHistoryService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        dao = new AdministrationHistoryDAO(dev_flag, user_message);
        hys = HistoryDAO.getInstance();
    }

    public void insert(JDBConnection connection, AdministrationWrapperDTO dto) {
        SystemSession ss = SystemSession.getInstancia();
        AdministrationHistoryDTO current_admin = ss.getCurrentAdministration();
        boolean res = false;
        try {
            //SE TRASPASA EL ID DE LA OFICINA, DE LA INSTANCIA ACTUAL
            ss.put("office_id", ss.getCurrent_instance().getOfficeId());
            ss.put("root", ss.getCurrent_instance().getEmployeeDefault());
            //SI LA ADMINISTRACION ACTUAL NO HA SIDO REGISTRADA, SE REGISTRA
            //SI NO ES ASI, SE ACTUALIZA
            if (Func.isNull(current_admin)) {
                int new_id = insert(connection, dto.getAdministration_history());
                res = new_id > 0;
            } else {
                update(connection, current_admin);
            }
        } catch (SQLException ex) {
            rollback(connection);
            returnMessageError("ERROR DE CONEXION");
        } catch (CorruptInsertionException | KeyNotGenerateException ex) {
            returnMessageError(ex.getErrorCode(), ex.getUserMessage());
        }

    }

    private int insert(JDBConnection connection, AdministrationHistoryDTO dto) throws CorruptInsertionException, SQLException, KeyNotGenerateException {
        boolean res = false;
        res = dao.insert(connection, dto);
        if (!res) {
            returnMessageError("NO SE PUDO REGISTRAR LA ADMNISTRACION");
        }
        res = hys.insert(connection, Const.INDEX_HYS_ADMINISTRATION_HISTORY, "REGISTRO DE LA ADMINISTRACION %s".formatted(LocalDate.now().getYear()));
        if (res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        return -1;
    }

    private boolean update(JDBConnection connection, AdministrationHistoryDTO dto) throws CorruptInsertionException, SQLException, KeyNotGenerateException {
        boolean res = false;
        res = dao.update(connection, dto);
        if (!res) {
            returnMessageError("NO SE PUDO REGISTRAR LA ADMNISTRACION");
        }
        res = hys.update(
                connection,
                Const.INDEX_HYS_ADMINISTRATION_HISTORY,
                "SE ACTUALIZO LA ADMINISTRACION CON ID: %s".formatted(dto.getId())
        );
        if (res) {
            returnMessageError("REGISTRO EN BITACORA CORRUPTO");
        }
        return res;
    }
}
