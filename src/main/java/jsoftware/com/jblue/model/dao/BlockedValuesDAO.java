package jsoftware.com.jblue.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import jsoftware.com.jblue.model.dto.BlockedValuesDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * DAO para la gestión de folios, recibos o valores bloqueados en
 * usr_blocked_values.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 */
public class BlockedValuesDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public BlockedValuesDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Registra un valor o rango bloqueado preventivamente en el sistema.
     */
    public int insert(JDBConnection connection, BlockedValuesDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int generated_id = 0;
        String query = """
                       INSERT INTO usr_blocked_values
                       (lock_value, employee_lock, employee_unlock, observation_lock, 
                        observation_unlock, type_lock, status)
                       VALUES (?, ?, ?, ?, ?, ?, ?)
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // El valor bloqueado puede requerir cast manual si es numérico puro, o se envía directo
            ps.setString(1, dto.getLockValue());
            ps.setInt(2, Integer.parseInt(dto.getEmployeeLock()));

            // El empleado de liberación es nulo al crear el registro
            setNull(ps, 3, Func.isNotNullEmptyBlank(dto.getEmployeeUnlock()) ? Integer.valueOf(dto.getEmployeeUnlock()) : null);

            ps.setString(4, dto.getObservationLock());
            setNull(ps, 5, dto.getObservationUnlock());

            ps.setString(6, dto.getTypeLock());
            ps.setString(7, dto.getStatus());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == PreparedStatement.EXECUTE_FAILED || affectedRows != 1) {
                throw new CorruptInsertionException();
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                generated_id = rs.getInt(1);

                // Enriquecimiento dinámico JBlue
                dto.put("id", String.valueOf(generated_id));
                String now = Formats.getLocalDateTime(LocalDateTime.now());
                dto.put("date_update", now);
                dto.put("date_register", now);
            }
        }
        return generated_id;
    }

    /**
     * Recupera el estado de un valor bloqueado mediante su clave primaria.
     */
    public Optional<BlockedValuesDTO> findById(JDBConnection connection, int id) throws SQLException {
        Optional<BlockedValuesDTO> result = Optional.empty();
        String query = "SELECT * FROM usr_blocked_values WHERE id = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    BlockedValuesDTO dto = new BlockedValuesDTO();
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        String label = md.getColumnLabel(i);
                        dto.getMap().put(label, rs.getString(label));
                    }
                    result = Optional.of(dto);
                }
            }
        }
        return result;
    }
}
