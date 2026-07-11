package jsoftware.com.jblue.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import jsoftware.com.jblue.model.dto.BlockedUserDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * DAO para el control de la tabla de auditoría y penalizaciones
 * usr_blocked_user.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 */
public class BlockedUserDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public BlockedUserDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Registra un nuevo bloqueo de usuario en el sistema.
     * <p>
     * Los campos relacionados con la liberación (unlock) se envían de forma
     * segura como nulos mediante setNull, ya que solo se pueblan al levantar la
     * penalización.
     * </p>
     */
    public int insert(JDBConnection connection, BlockedUserDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int generated_id = 0;
        String query = """
                       INSERT INTO usr_blocked_user
                       (user_id, description_register, description_update, description_end, 
                        observation_lock, observation_unlock, apply_unlocked, office_lock, 
                        office_unlock, employee_register, employee_update, employee_unlocked, 
                        type_lock, status)
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Llaves foráneas obligatorias de inicio
            ps.setInt(1, Integer.parseInt(dto.getUserId()));
            ps.setString(2, dto.getDescriptionRegister());

            // Campos de actualización/cierre opcionales al crear el registro
            setNull(ps, 3, dto.getDescriptionUpdate());
            setNull(ps, 4, dto.getDescriptionEnd());

            // Observaciones de bloqueo y desbloqueo
            ps.setString(5, dto.getObservationLock());
            setNull(ps, 6, dto.getObservationUnlock());

            // Banderas y oficinas (Lock obligatorios, Unlock opcionales)
            ps.setString(7, dto.getApplyUnlocked());
            ps.setInt(8, Integer.parseInt(dto.getOfficeLock()));
            setNull(ps, 9, Func.isNotNullEmptyBlank(dto.getOfficeUnlock()) ? Integer.valueOf(dto.getOfficeUnlock()) : null);

            // Empleados responsables del flujo
            ps.setInt(10, Integer.parseInt(dto.getEmployeeRegister()));
            setNull(ps, 11, Func.isNotNullEmptyBlank(dto.getEmployeeUpdate()) ? Integer.valueOf(dto.getEmployeeUpdate()) : null);
            setNull(ps, 12, Func.isNotNullEmptyBlank(dto.getEmployeeUnlocked()) ? Integer.valueOf(dto.getEmployeeUnlocked()) : null);

            // Configuración final de estado
            ps.setString(13, dto.getTypeLock());
            ps.setString(14, dto.getStatus());

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
     * Recupera un registro de bloqueo específico por su ID.
     */
    public Optional<BlockedUserDTO> exist(JDBConnection connection, int user_id) throws SQLException {
        Optional<BlockedUserDTO> result = Optional.empty();
        String query = "SELECT * FROM usr_blocked_user WHERE user_id = ? AND status = 1";

        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, user_id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    BlockedUserDTO dto = new BlockedUserDTO();
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

    /**
     * Recupera un registro de bloqueo específico por su ID.
     */
    public Optional<BlockedUserDTO> findById(JDBConnection connection, int id) throws SQLException {
        Optional<BlockedUserDTO> result = Optional.empty();
        String query = "SELECT * FROM usr_blocked_user WHERE id = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    BlockedUserDTO dto = new BlockedUserDTO();
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
