package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import jsoftware.com.jblue.model.dto.AddressDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * DAO encargado de la persistencia e integridad de las direcciones de usuarios
 * del sistema.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-06-19
 * @version 1.1
 */
public class AddressDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public AddressDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public boolean insert(JDBConnection connection, AddressDTO dto) throws SQLException, CorruptInsertionException {
        boolean success = false;
        String query = "INSERT INTO usr_address ("
                + "user_id, street1_id, street2_id, inside_number, outside_number, "
                + "is_owner, observation, employee_id, office_id, status"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, Integer.parseInt(dto.getUserId()));
            ps.setInt(2, Integer.parseInt(dto.getStreet1Id()));

            // 1. Calle 2 (Opcional - Esquina)
            String street2Str = dto.getStreet2Id();
            if (street2Str == null || street2Str.trim().isEmpty() || street2Str.equalsIgnoreCase("N/A")) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, Integer.parseInt(street2Str));
            }

            ps.setString(4, dto.getInsideNumber());

            // 2. Número Exterior (Opcional)
            String outsideStr = dto.getOutsideNumber();
            if (outsideStr == null || outsideStr.trim().isEmpty()) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, outsideStr);
            }

            // 3. Validación de Propietario (0 o 1)
            ps.setInt(6, Integer.parseInt(dto.getIsOwner()));

            // 4. Observación de la Relación de Propiedad (Opcional si es dueño)
            String observationStr = dto.getObservation();
            if (observationStr == null || observationStr.trim().isEmpty() || observationStr.equalsIgnoreCase("N/A")) {
                ps.setNull(7, Types.VARCHAR);
            } else {
                ps.setString(7, observationStr);
            }

            // 5. Datos de Control Operativo
            ps.setInt(8, Integer.parseInt(dto.getEmployeeId()));
            ps.setInt(9, Integer.parseInt(dto.getOfficeId()));
            ps.setInt(10, Integer.parseInt(dto.getStatus()));

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new CorruptInsertionException();
            } else if (rowsAffected > 1) {
                throw new CorruptInsertionException();
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    success = true;
                    dto.put("id", rs.getString(1));
                    dto.put("date_update", Formats.getLocalDateTime(LocalDateTime.now()));
                    dto.put("date_register", Formats.getLocalDateTime(LocalDateTime.now()));
                }
            }

        } catch (SQLException | CorruptInsertionException e) {
            throw e;
        }

        return success;
    }

    /**
     * Valida la existencia previa de un domicilio idéntico basándose en
     * criterios únicos de ubicación (user_id, street1_id, street2_id e
     * inside_number).
     * <br><br>
     * <strong>Estándar JBlue (Enriquecimiento):</strong> Si el registro existe
     * en la base de datos, el método recupera los campos {@code id},
     * {@code status}, {@code office_id} y {@code date_register}, y los inyecta
     * directamente dentro del mapa del DTO recibido, permitiendo su uso
     * inmediato en la UI.
     *
     * @param connection Conexión activa y segura bajo la transacción actual.
     * @param dto DTO con los criterios de búsqueda que será enriquecido tras el
     * éxito de la operación.
     * @return {@code true} si el domicilio ya se encuentra registrado;
     * {@code false} en caso contrario.
     * @throws SQLException Si ocurre un error en la sintaxis o conexión del
     * motor (MySQL/SQLite).
     */
    public boolean exists(JDBConnection connection, AddressDTO dto) throws SQLException {
        boolean isRegistered = false;

        // Query optimizado que busca por los criterios clave y recupera los campos solicitados
        String query = "SELECT id, status, office_id, date_register "
                + "FROM usr_address "
                + "WHERE user_id = ? AND street1_id = ? AND inside_number = ? ";

        String street2Str = dto.getStreet2Id();
        boolean hasStreet2 = !(street2Str == null || street2Str.trim().isEmpty() || street2Str.equalsIgnoreCase("N/A"));
        // Ajustamos dinámicamente el query para evaluar de forma correcta el estado NULL de la calle esquina
        if (hasStreet2) {
            query += "AND street2_id = ?";
        } else {
            query += "AND street2_id IS NULL";
        }
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            // 1. Inyección de parámetros base para el filtro
            ps.setInt(1, Integer.parseInt(dto.getUserId()));
            ps.setInt(2, Integer.parseInt(dto.getStreet1Id()));
            ps.setString(3, dto.getInsideNumber());
            if (hasStreet2) {
                ps.setInt(4, Integer.parseInt(street2Str));
            }
            // 2. Ejecución y lectura de datos
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isRegistered = true;
                    // 3. ENRIQUECIMIENTO DEL DTO: Mapeo manual de campos recuperados hacia Strings
                    dto.put("id", String.valueOf(rs.getInt("id")));
                    dto.put("status", String.valueOf(rs.getInt("status")));
                    dto.put("office_id", String.valueOf(rs.getInt("office_id")));
                    dto.put("date_register", rs.getString("date_register"));
                }
            }

        }
        return isRegistered;
    }
}
