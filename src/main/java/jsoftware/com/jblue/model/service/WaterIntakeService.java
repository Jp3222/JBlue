/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.net.UnknownHostException;
import java.sql.SQLException;
import jsoftware.com.jblue.model.abst.AbstractService;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.WaterIntakeDAO;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class WaterIntakeService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final WaterIntakeDAO wi_dao;
    private final ProcessDAO process_dao;
    private final HistoryService hys_service;

    public WaterIntakeService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        wi_dao = new WaterIntakeDAO(dev_flag, process_name);
        process_dao = new ProcessDAO(dev_flag, process_name);
        hys_service = new HistoryService(dev_flag, user_message);
    }

    public boolean saveProcess(JDBConnection connection, SystemSession ss, WaterIntakeDTO dto) {
        boolean res = false;
        try {
            //[1]REGISTRA LOS DATOS DE LA NUEVA TOMA DE AGUA POTABLE]
            int water_intake_id = wi_dao.insert(connection, dto);
            res = water_intake_id > 0;
            if (!res) {
                throw new ServiceException(1, "REGISTRO DE USUARIO ERRONEO");
            }
            //REGISTRO EN BITACORA
            res = hys_service.insert(connection, ss, ss.getProgramHistoryDTO(Const.INDEX_WKI_WATER_INTAKES, water_intake_id));
            if (!res) {
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO");
            }
        } catch (ServiceException | SQLException | UnknownHostException | CorruptInsertionException | KeyNotGenerateException ex) {
            System.getLogger(WaterIntakeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
    }

}
