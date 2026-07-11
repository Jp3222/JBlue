package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import java.util.List;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HistoryDAO;
import jsoftware.com.jblue.model.dao.UserDocumentationDAO;
import jsoftware.com.jblue.model.dto.UserDocumentationDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Servicio encargado de la lógica de negocio, validaciones preventivas y
 * orquestación de auditoría para la carga de documentos por lotes.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-19
 * @version 1.0
 */
public class UserDocumentationService extends AbstractService {

    private static final long serialVersionUID = 1L;

    private final UserDocumentationDAO dao;
    private final HistoryDAO hys;

    public UserDocumentationService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        this.dao = new UserDocumentationDAO(dev_flag, process_name);
        this.hys = HistoryDAO.getInstance();
    }

    /**
     * Procesa e inserta un lote completo de documentos digitalizados de forma
     * atómica, enriqueciendo sus IDs y registrando el evento de éxito en la
     * bitácora de programa.
     *
     * * @param connection Conexión activa controlada por el orquestador macro.
     * @param dtoList Lista de DTOs con la información y archivos binarios en
     * Base64.
     * @return true si todo el lote y su bitácora se asentaron correctamente;
     * false en caso contrario.
     */
    public boolean insert(JDBConnection connection, List<UserDocumentationDTO> dtoList) {
        boolean res = false;
        try {
            // 1. Inserción masiva por lote utilizando el PreparedStatement Batch nativo
            List<Integer> generatedIds = dao.insertBatch(connection, dtoList);

            if (generatedIds == null || generatedIds.isEmpty() || generatedIds.size() != dtoList.size()) {
                returnMessageError("NO SE PUDIERON REGISTRAR LOS DOCUMENTOS ASOCIADOS");
                return false;
            }

            // 2. Registro consecutivo en la bitácora interna de programa por cada documento exitoso
            for (UserDocumentationDTO dto : dtoList) {
                res = hys.insert(
                        connection,
                        Const.INDEX_DOC_USER_DOCUMENTATION,
                        "SE REGISTRO EL DOCUMENTO DIGITAL NO: %s - TIPO: %s".formatted(dto.getId(), dto.getTypeId())
                );

                if (!res) {
                    returnMessageError("REGISTRO EN BITACORA CORRUPTO - DOCUMENTACION");
                    return false;
                }
            }

        } catch (SQLException e) {
            returnMessageError(e.getErrorCode(), e.getMessage());
            log(e, "insertBatch");
            res = false;
        } catch (DataAccesObjectException e) {
            // Captura de excepciones controladas del DAO (ej. Archivo BLOB vacío o nulo)
            returnMessageError(e.getErrorCode(), e.getMessage());
            log(e, "insertBatch");
            res = false;
        }

        return res;
    }
}
