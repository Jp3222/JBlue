/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.DocumentRecordDAO;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dto.DocumentRecordDTO;
import jsoftware.com.jblue.model.exp.ServiceException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.util.FuncLogs;

/**
 *
 * @author juanp
 */
public class DocumentRecordService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final DocumentRecordDAO doc_record_dao;
    private final HistoryDAO history_dao;

    public DocumentRecordService(boolean flag_dev, String name_module) {
        super(flag_dev, name_module);
        doc_record_dao = new DocumentRecordDAO();
        history_dao = HistoryDAO.getInstance();
    }

    public int save(JDBConnection connection, DocumentRecordDTO dto) {
        boolean res = false;
        int doc_rec_id = -1;
        try {
            //SE REGISTRA EL USUARIO
            doc_rec_id = doc_record_dao.insert(connection, dto);
            res = doc_rec_id > 0;
            if (!res) {
                throw new ServiceException(ServiceException.SERVICE_INSERT_EXCEPTION, "ERROR AL REGISTRAR EL USUARIO");
            }
            //SE REGISTRA EL MOVIMIENTO EN EL HISTORIAL
            res = history_dao.insert(connection, 54, "SE REGISTRO DOCUMENTACION DE USUARIO".formatted(
                    dto.getId()
            ));
            if (!res) {
                throw new ServiceException(ServiceException.SERVICE_INSERT_EXCEPTION, "ERROR AL REGISTRAR EN BITACORA.");
            }
            commit(connection);
        } catch (SQLException | CorruptInsertionException | KeyNotGenerateException | ServiceException ex) {
            rollback(connection);
            returnMessageError("ERROR AL REGISTRAR EL USUARIO");
            FuncLogs.logError(
                    AppFiles.DIR_PROG_LOG_TODAY,
                    getClass(), ex,
                    getProcess_name(), "save",
                    ex.getMessage()
            );
        }
        return doc_rec_id;
    }

}
