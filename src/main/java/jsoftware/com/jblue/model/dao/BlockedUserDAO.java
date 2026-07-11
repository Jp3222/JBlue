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
import jsoftware.com.jblue.model.exp.imp.CorruptUpdateException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
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
    public boolean insert(JDBConnection connection, BlockedUserDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int generated_id = 0;
        boolean res = false;
        String query = """
                       INSERT INTO usr_blocked_user
                       (user_id, apply_unlocked, description_update, description_register, 
                       observation_lock, office_lock, employee_register, type_lock,
                       last_employee_update, status
                        )
                       VALUES (?,?,?,?,?,?,?,?,?,1)
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getUserId());
            ps.setString(2, dto.getApplyUnlocked());
            ps.setString(3, dto.getDescriptionUpdate());
            ps.setString(4, dto.getDescriptionRegister());
            ps.setString(5, dto.getOfficeLock());
            ps.setString(6, dto.getOfficeLock());
            ps.setString(7, dto.getEmployeeRegister());
            ps.setString(8, dto.getTypeLock());
            ps.setString(9, dto.getEmployeeUpdate());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == PreparedStatement.EXECUTE_FAILED || affectedRows != 1) {
                throw new CorruptInsertionException();
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                generated_id = rs.getInt(1);
                res = true;
                // Enriquecimiento dinámico JBlue
                dto.put("id", String.valueOf(generated_id));
                String now = Formats.getLocalDateTime(LocalDateTime.now());
                dto.put("date_update", now);
                dto.put("date_register", now);
            }
        }
        return res;
    }

    /**
     * ESTA FUNCION DESBLOQUE UN TRAMITE ASOCIADO A UN USUARIO QUE HAYA SIDO
     * DATO DE ALTA SOLO POR LA OFICINA ACTUAL
     *
     * @param connection
     * @param dto
     * @return
     * @throws SQLException
     * @throws CorruptInsertionException
     * @throws KeyNotGenerateException
     */
    public boolean updateStatusUnlockProcess(JDBConnection connection, BlockedUserDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException, CorruptUpdateException {
        boolean res = false;
        String query = """
                        UPDATE usr_blocked_user SET
                            apply_unlocked = ?,
                            description_update = ?,
                            description_end = ?,
                            observation_unlock = ?,
                            office_unlock=?,
                            employee_unlocked=?
                            last_employee_update=?,
                            status = 2
                        WHERE id = ? AND status = 1 AND office_lock = ? AND type_lock = 14
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getApplyUnlocked());
            ps.setString(2, dto.getDescriptionUpdate());
            ps.setString(3, dto.getDescriptionEnd());
            ps.setString(4, dto.getObservationUnlock());
            ps.setString(5, dto.getOfficeUnlock());
            ps.setString(6, dto.getEmployeeUnlocked());
            ps.setString(7, dto.getEmployeeUpdate());
            ps.setString(8, dto.getId());
            ps.setString(9, dto.getOfficeUnlock());
            int affected_row = ps.executeUpdate();
            res = affected_row == 1;
            if (!res) {
                throw new CorruptUpdateException("EL MOVIMIENTO TRATO DE AFECTAR A: %s REGISTROS".formatted(affected_row));
            }
            res = true;
        }
        return res;
    }
    /**
     * RECUPERA UN BLOQUEO AUTORIZADO EN CASO DE QUE EL USUARIO EXISTA
     * @param connection
     * @param user_id
     * @return
     * @throws SQLException 
     */
    public Optional<BlockedUserDTO> existLockAut(JDBConnection connection, int user_id) throws SQLException {
        Optional<BlockedUserDTO> result = Optional.empty();
        String query = "SELECT * FROM usr_blocked_user WHERE user_id = ? AND status = 1 AND apply_unlocked = 0 AND type_lock = 14";

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
