/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.ProcessDAO;
import jsoftware.com.jblue.model.dao.WaterIntakeDAO;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 *
 * @author juanp
 */
public class WaterIntakeService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final WaterIntakeDAO wi_dao;
    private final ProcessDAO process_dao;

    public WaterIntakeService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        wi_dao = new WaterIntakeDAO(dev_flag, process_name);
        process_dao = new ProcessDAO(dev_flag, process_name);
    }

    public boolean saveProcess(JDBConnection connection, WaterIntakeDTO dto) {
        boolean res = false;
        try {
            //[1]REGISTRA LOS DATOS DE LA NUEVA TOMA DE AGUA POTABLE]
            int water_intake_id = wi_dao.insert(connection, dto);
            res = water_intake_id > 0;
            if (!res) {
                throw new ServiceException(1, "REGISTRO DE USUARIO ERRONEO");
            }

            //REGISTRO EN BITACORA
            res = HistoryDAO.getInstance().insert(connection, Const.INDEX_WKI_WATER_INTAKES, "SE REGISTRO LA TOMA: %s - %s".formatted(
                    water_intake_id,
                    dto.getDescription()
            ));
            if (!res) {
                throw new ServiceException(2, "REGISTRO EN BITACORA CORRUPTO");
            }
        } catch (ServiceException | SQLException ex) {
            System.getLogger(WaterIntakeService.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
    }
}
