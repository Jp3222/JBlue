package jsoftware.com.jblue.model.service;

import java.sql.SQLException;
import jsoftware.com.jblue.model.dao.TransactionHistoryDAO;
import jsoftware.com.jblue.model.dto.TransactionHistoryDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.models.AbstractService;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Servicio encargado de la lógica de negocio, validación y orquestación del
 * historial maestro de transacciones de JBlue.
 * <br>
 * Actúa como filtro defensivo antes de permitir que un registro toque el DAO.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-07
 * @version 1.1
 */
public class TransactionHistoryService extends AbstractService {

    private static final long serialVersionUID = 1L;
    private final TransactionHistoryDAO dao;

    public TransactionHistoryService(boolean dev_flag, String process_name) {
        super(dev_flag, process_name);
        this.dao = new TransactionHistoryDAO(dev_flag, process_name);
    }

    /**
     * Valida la integridad del DTO y procesa la inserción del Paso 1 del flujo
     * transaccional.
     *
     * * @param connection Conexión activa a la base de datos.
     * @param dto DTO con los Strings de la transacción capturados por el
     * sistema.
     * @return El ID autogenerado asignado por MySQL, o -1 si ocurre un fallo de
     * validación o persistencia.
     */
    public int insert(JDBConnection connection, TransactionHistoryDTO dto) {
        // 1. Filtros Defensivos Iniciales con Retorno Inmediato
        if (Func.isNull(connection) || connection.isClose()) {
            returnMessageError("ERROR DE CONEXIÓN O RED: La conexión no está activa.");
            return SERVICE_EXECUTE_ERROR; // Retorna código de falla (o -1 según prefieras)
        }
        if (Func.isNull(dto)) {
            returnMessageError("ERROR DE NEGOCIO: El DTO de transacciones es nulo.");
            return SERVICE_EXECUTE_ERROR;
        }

        // 2. Validaciones de Estructura de Datos (Mapeo de Strings de la Vista)
        if (Func.isNullEmptyBlank(dto.getTypeMov())) {
            returnMessageError("VALIDACIÓN: El tipo de movimiento (type_mov) es obligatorio.");
            return SERVICE_EXECUTE_ERROR;
        }
        if (Func.isNullEmptyBlank(dto.getObservation())) {
            returnMessageError("VALIDACIÓN: La observación es obligatoria para la bitácora.");
            return SERVICE_EXECUTE_ERROR;
        }
        if (Func.isNullEmptyBlank(dto.getModuleId()) || "0".equals(dto.getModuleId())) {
            returnMessageError("VALIDACIÓN: El ID de módulo es inválido o vacío.");
            return SERVICE_EXECUTE_ERROR;
        }
        if (Func.isNullEmptyBlank(dto.getAffectedTable()) || "0".equals(dto.getAffectedTable())) {
            returnMessageError("VALIDACIÓN: La tabla afectada es requerida para auditoría.");
            return SERVICE_EXECUTE_ERROR;
        }
        if (Func.isNullEmptyBlank(dto.getEmployeeId())) {
            returnMessageError("VALIDACIÓN: El ID de empleado es obligatorio.");
            return SERVICE_EXECUTE_ERROR;
        }

        int generatedId = -1;
        try {
            // Desactivamos autocommit para controlar el bloque atómico
            connection.setAutoCommit(false);

            // Se delega al DAO y este inyecta el ID en el mapa de Strings del DTO
            generatedId = dao.insert(connection, dto);

            if (generatedId > 0) {
                commit(connection); // El método commit de AbstractService ya hace setAutoCommit(true)
                return generatedId; // ¡ÉXITO! Retornamos el ID real de MySQL para el flujo posterior
            }

        } catch (SQLException ex) {
            rollback(connection);
            this.error_code = SERVICE_EXECUTE_ERROR;
            this.user_message = "Error SQL [" + ex.getErrorCode() + "]: " + ex.getMessage();
        } catch (DataAccesObjectException ex) {
            rollback(connection);
            this.error_code = SERVICE_EXECUTE_ERROR;
            this.user_message = "Error DAO: " + ex.getMessage();
        }

        return generatedId;
    }

    public boolean updateStatusOk(JDBConnection connection, TransactionHistoryDTO dto) {
        boolean res = false;
        connection.setAutoCommit(false);
        try {
            res = dao.updateStatusOK(connection, dto);
            if (res) {
                commit(connection);
            } else {
                rollback(connection);
            }
        } catch (SQLException ex) {
            rollback(connection);
            returnMessageError(ex.getErrorCode(), ex.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }
}
