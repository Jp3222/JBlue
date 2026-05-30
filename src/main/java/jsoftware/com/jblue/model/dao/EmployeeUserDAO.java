package jsoftware.com.jblue.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jsoftware.com.jblue.model.dto.EmployeeUserDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Data Access Object (DAO) para la gestión de accesos y credenciales del
 * personal.
 * <br>
 * Administra las transacciones sobre la tabla usr_employee_user.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-03-21
 * @version 1.0
 */
public class EmployeeUserDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public EmployeeUserDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Registra las credenciales de acceso para un empleado en el sistema.
     * <p>
     * Realiza el casteo manual correspondiente a enteros para las llaves
     * foráneas y enriquece el DTO tras el éxito de la operación.
     * </p>
     *
     * * @param connection Conexión activa.
     * @param dto Contenedor dinámico de datos de la credencial del empleado.
     * @return El ID de la credencial generado por la base de datos.
     * @throws SQLException Si ocurre un error relacional o duplicidad de nombre
     * de usuario.
     * @throws CorruptInsertionException Si las filas afectadas difieren de 1.
     * @throws KeyNotGenerateException Si no se logra recuperar la clave
     * autoincrementable.
     */
    public int insert(JDBConnection connection, EmployeeUserDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int generated_id = 0;
        String query = """
                       INSERT INTO usr_employee_user
                       (employee_id, office_id, user, password, description, 
                        email, phone_number, employee_type, status, last_employee_update)
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // 1. Relaciones Foráneas (El DAO realiza el cast final a Integer según MySQL)
            ps.setInt(1, Integer.parseInt(dto.getEmployeeId()));
            ps.setInt(2, Integer.parseInt(dto.getOfficeId()));

            // 2. Credenciales de Seguridad
            ps.setString(3, dto.getUser());
            ps.setString(4, dto.getPassword());

            // 3. Detalles de Perfil y Contacto Opcionales (Uso homogéneo de setNull)
            setNull(ps, 5, dto.getDescription());
            setNull(ps, 6, dto.getEmail());
            setNull(ps, 7, dto.getPhoneNumber());

            // 4. Configuración, Estado y Auditoría
            ps.setString(8, dto.getEmployeeType());
            ps.setString(9, dto.getStatus());
            ps.setInt(10, Integer.parseInt(dto.getLastEmployeeUpdate()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows == PreparedStatement.EXECUTE_FAILED || affectedRows != 1) {
                throw new CorruptInsertionException();
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                generated_id = rs.getInt(1);

                // Flujo JBlue: Enriquecimiento del DTO post-operación
                dto.put("id", String.valueOf(generated_id));
                String now = Formats.getLocalDateTime(LocalDateTime.now());
                dto.put("date_update", now);
                dto.put("date_register", now);
            }
        }
        return generated_id;
    }

    /**
     * Busca y valida un registro de credenciales activo mediante usuario y
     * contraseña.
     *
     * * @param connection Conexión activa.
     * @param user Nombre de usuario único del operador.
     * @param password Contraseña correspondiente.
     * @return Un Optional con el EmployeeUserDTO cargado dinámicamente, u
     * opcional vacío.
     * @throws SQLException Si ocurre un error en la ejecución del
     * PreparedStatement.
     */
    public Optional<EmployeeUserDTO> get(JDBConnection connection, String user, String password) throws SQLException {
        Optional<EmployeeUserDTO> result = Optional.empty();
        String query = "SELECT * FROM usr_employee_user WHERE user = ? AND password = ? AND status = 1";

        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            // Corrección en el orden secuencial de asignación de parámetros
            ps.setString(1, user);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                // Estructura segura sin duplicación de saltos en el cursor
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    int size = md.getColumnCount();
                    String[] fields = new String[size];

                    for (int i = 0; i < size; i++) {
                        fields[i] = md.getColumnLabel(i + 1);
                    }

                    EmployeeUserDTO dto = new EmployeeUserDTO();
                    for (String field : fields) {
                        // Forzamos minúsculas en las llaves del mapa para prevenir desajustes con el DTO
                        dto.getMap().put(field.toLowerCase(), rs.getString(field));
                    }
                    result = Optional.of(dto);
                }
            }
        }
        return result;
    }

    /**
     * Aplica actualizaciones selectivas (Delta Updates) modificando solo los
     * campos mutados.
     *
     * * @param connection Conexión transaccional activa.
     * @param oldUser Datos de las credenciales recuperados previamente de la
     * BD.
     * @param newUser Nuevos cambios recolectados de los formularios gráficos de
     * Swing.
     * @return true si se aplicó el query con éxito; false si no hubo cambios
     * detectados.
     * @throws SQLException Si ocurre un fallo en el motor relacional de MySQL.
     */
    public boolean update(JDBConnection connection, EmployeeUserDTO oldUser, EmployeeUserDTO newUser) throws SQLException {
        List<String> setColumns = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // Escaneo dinámico y seguro de deltas de texto
        autoSet(oldUser.getUser(), newUser.getUser(), "user", setColumns, parameters);
        autoSet(oldUser.getPassword(), newUser.getPassword(), "password", setColumns, parameters);
        autoSet(oldUser.getDescription(), newUser.getDescription(), "description", setColumns, parameters);
        autoSet(oldUser.getEmail(), newUser.getEmail(), "email", setColumns, parameters);
        autoSet(oldUser.getPhoneNumber(), newUser.getPhoneNumber(), "phone_number", setColumns, parameters);
        autoSet(oldUser.getEmployeeType(), newUser.getEmployeeType(), "employee_type", setColumns, parameters);
        autoSet(oldUser.getStatus(), newUser.getStatus(), "status", setColumns, parameters);
        autoSet(oldUser.getLastUpdatePassword(), newUser.getLastUpdatePassword(), "last_update_password", setColumns, parameters);

        // Control y parseo manual para llaves foráneas relacionales modificadas
        if (!java.util.Objects.equals(oldUser.getOfficeId(), newUser.getOfficeId())) {
            setColumns.add("office_id");
            parameters.add(Integer.valueOf(newUser.getOfficeId()));
        }

        // Si el operador no modificó ningún valor en Swing, evitamos el tráfico a la BD
        if (setColumns.isEmpty()) {
            return false;
        }

        // Adición del operador responsable del cambio
        setColumns.add("last_employee_update");
        parameters.add(Integer.valueOf(newUser.getLastEmployeeUpdate()));

        String setClause = String.join("=?, ", setColumns) + "=?";
        String query = "UPDATE usr_employee_user SET " + setClause + " WHERE id = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            int index = 1;
            for (Object param : parameters) {
                if (param == null) {
                    ps.setNull(index, Types.VARCHAR);
                } else if (param instanceof Integer intValue) {
                    ps.setInt(index, intValue);
                } else {
                    ps.setObject(index, param);
                }
                index++;
            }

            // Llave primaria del registro a actualizar (WHERE id = ?)
            ps.setInt(index, Integer.parseInt(newUser.getId()));
            return ps.executeUpdate() > 0;
        }
    }

    private <T> void autoSet(T oldValue, T newValue, String columnName, List<String> setColumns, List<Object> parameters) {
        if (!java.util.Objects.equals(oldValue, newValue)) {
            setColumns.add(columnName);
            parameters.add(newValue);
        }
    }
}
