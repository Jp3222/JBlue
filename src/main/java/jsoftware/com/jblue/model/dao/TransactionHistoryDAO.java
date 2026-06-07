package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jsoftware.com.jblue.model.dto.TransactionHistoryDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * DAO encargado de la persistencia y actualización del historial maestro de
 * transacciones (hys_transaction_history) en JBlue.
 * <br>
 * Sigue el estándar de aislamiento transaccional del framework, abstrayendo los
 * casteos finales requeridos por MySQL.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-07
 * @version 1.0
 */
public class TransactionHistoryDAO {

    /**
     * Inserta el registro inicial de una transacción en la base de datos (Paso
     * 1 del flujo).
     * <br>
     * Nota: Este método se ejecuta bajo el comportamiento de conexión actual
     * (Autocommit = True) e inyecta el ID autogenerado directamente en el DTO
     * para su posterior actualización.
     * <br>
     * Nota 2: CAMPOS OBLIGATIOS - type_mov, observation,
     * module_id,affected_table, employee_id,
     *
     * @param conn Conexión activa a la base de datos.
     * @param dto DTO que contiene la información del movimiento maestro.
     * @return El ID generado por la base de datos, o -1 si falla.
     * @throws SQLException Si ocurre un error en la sentencia SQL.
     */
    public int insert(JDBConnection conn, TransactionHistoryDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        String sql = "INSERT INTO hys_transaction_history "
                + "(type_mov, observation, module_id, ip, affected_table, db_user, employee_id, status) "
                + "VALUES (?, ?, ?, SUBSTRING_INDEX(USER(), '@', -1), ?, CURRENT_USER, ?, 34)";
        int id = -1;
        try (PreparedStatement ps = conn.getNewPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getTypeMov());
            ps.setString(2, dto.getObservation());
            ps.setString(3, dto.getModuleId());
            ps.setString(4, dto.getAffectedTable());
            ps.setString(5, dto.getEmployeeId());
            int executeUpdate = ps.executeUpdate();
            if (executeUpdate == PreparedStatement.EXECUTE_FAILED || executeUpdate != 1) {
                throw new CorruptInsertionException();
            }
            // Recuperación inmediata del ID generado para mantener sincronizado el DTO en memoria
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                id = rs.getInt(1);
                dto.put("id", String.valueOf(id)); // Seteo en el JDBMapObject heredado
            }
        }
        return id;
    }

    /**
     * Actualiza los índices técnicos de grano fino y consolida el estatus final
     * de la transacción a EXITOSA [1] (Paso 8.1 del flujo de éxito).
     * <br>
     * Diseñado para ser invocado tras un Commit exitoso, inyectando los IDs
     * generados en la bitácora de grano fino directamente desde el mapa de
     * Strings del DTO.
     *
     * @param conn Conexión activa a la base de datos (Autocommit = True).
     * @param dto DTO que contiene el ID de la transacción y los índices
     * hys_start_id y hys_end_id.
     * @return true si la actualización afectó al registro, false en caso
     * contrario.
     * @throws SQLException Si ocurre un error en la ejecución del UPDATE o
     * incompatibilidad de tipos.
     */
    public boolean updateStatusOK(JDBConnection conn, TransactionHistoryDTO dto) throws SQLException {
        // Corrección: Se elimina la coma errónea que estaba antes del WHERE ", WHERE id = ?"
        String sql = "UPDATE hys_transaction_history SET "
                + "hys_start_id = ?, "
                + "hys_endt_id = ?, "
                + "status = 1 "
                + "WHERE id = ?";

        try (PreparedStatement ps = conn.getNewPreparedStatement(sql)) {

            // 1. Manejo y casteo defensivo para hys_start_id (Índice 1)
            if (dto.getHysStartId() == null || dto.getHysStartId().trim().isEmpty()) {
                ps.setNull(1, java.sql.Types.INTEGER);
            } else {
                ps.setInt(1, Integer.parseInt(dto.getHysStartId()));
            }
            // 2. Manejo y casteo defensivo para hys_end_id (Índice 2)
            if (dto.getHysEndId() == null || dto.getHysEndId().trim().isEmpty()) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setInt(2, Integer.parseInt(dto.getHysEndId()));
            }
            // 3. Casteo explícito para la llave primaria del WHERE (Índice 3)
            // Se extrae directamente del JDBMapObject usando la llave "id"
            ps.setString(3, dto.getId());

            return ps.executeUpdate() > 0;
        }
    }
}
