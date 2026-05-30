package jsoftware.com.jblue.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * DAO relacional para el control y asignación de ubicaciones residenciales en usr_address.
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 */
public class AddressDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public AddressDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Registra una dirección enlazada a un usuario. 
     * <p>
     * Realiza el cast final a Integer para las llaves foráneas de calles, 
     * operadores y sucursales.
     * </p>
     */
    public int insert(JDBConnection connection, AddressDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int generated_id = 0;
        String query = """
                       INSERT INTO usr_address
                       (user_id, street1_id, street2_id, inside_number, outside_number, 
                        employee_id, office_id, status)
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            // Relaciones foráneas indexadas obligatorias
            ps.setInt(1, Integer.parseInt(dto.getUserId()));
            ps.setInt(2, Integer.parseInt(dto.getStreet1Id()));
            
            // Calle secundaria opcional (Evaluación null-safe)
            setNull(ps, 3, Func.isNotNullEmptyBlank(dto.getStreet2Id()) ? Integer.valueOf(dto.getStreet2Id()) : null);
            
            // Identificadores de inmueble (Varchars tolerantes a letras)
            setNull(ps, 4, dto.getInsideNumber());
            setNull(ps, 5, dto.getOutsideNumber());
            
            // Auditoría institucional obligatoria
            ps.setInt(6, Integer.parseInt(dto.getEmployeeId()));
            ps.setInt(7, Integer.parseInt(dto.getOfficeId()));
            ps.setString(8, dto.getStatus());

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
     * Recupera una dirección de vivienda mediante su ID.
     */
    public Optional<AddressDTO> findById(JDBConnection connection, int id) throws SQLException {
        Optional<AddressDTO> result = Optional.empty();
        String query = "SELECT * FROM usr_address WHERE id = ?";
        
        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    AddressDTO dto = new AddressDTO();
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