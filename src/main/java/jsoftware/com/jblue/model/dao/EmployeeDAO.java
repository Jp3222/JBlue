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
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Data Access Object (DAO) para la gestión transaccional de la tabla
 * emp_employee.
 * <br>
 * Controla el ciclo de vida de los datos personales y de ubicación del personal
 * de JBlue.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.0
 */
public class EmployeeDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public EmployeeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Inserta un nuevo empleado en la tabla emp_employee.
     * <p>
     * Aplica el método {@code setNull} de forma homogénea para todas las
     * columnas opcionales y enriquece el DTO con el ID autoincrementable y las
     * marcas de tiempo.
     * </p>
     *
     * * @param connection Conexión activa.
     * @param dto DTO de empleado con los datos de la vista Swing.
     * @return El ID generado (PK) por la base de datos.
     * @throws SQLException Si ocurre una violación de unicidad (CURP/RFC) o
     * error de red.
     * @throws CorruptInsertionException Si las filas afectadas no son
     * exactamente 1.
     * @throws KeyNotGenerateException Si MySQL no genera la llave subyacente.
     */
    public int insert(JDBConnection connection, EmployeeDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int employee_id = 0;
        String query = """
                       INSERT INTO emp_employee
                       (rfc, curp, first_name, last_name1, last_name2, gender, birthdate, 
                        personal_email, personal_number, street1, street2, inside_number, 
                        outside_number, status)
                       VALUES
                       (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // 1. Identificación Obligatoria
            ps.setString(1, dto.getRfc());
            ps.setString(2, dto.getCurp());
            ps.setString(3, dto.getFirstName());
            ps.setString(4, dto.getLastName1());

            // 2. Datos de Identidad Opcionales/Específicos (Manejo con setNull homogéneo)
            setNull(ps, 5, dto.getLastName2());
            ps.setString(6, dto.getGender());
            ps.setString(7, dto.getBirthdate());

            // 3. Contacto Opcional
            setNull(ps, 8, dto.getPersonalEmail());
            setNull(ps, 9, dto.getPersonalNumber());

            // 4. Dirección Física (street1 es mandatoria, el resto opcionales)
            ps.setString(10, dto.getStreet1());
            setNull(ps, 11, dto.getStreet2());
            setNull(ps, 12, dto.getInsideNumber());
            setNull(ps, 13, dto.getOutsideNumber());

            // 5. Estado Inicial
            ps.setString(14, dto.getStatus());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == PreparedStatement.EXECUTE_FAILED || affectedRows != 1) {
                throw new CorruptInsertionException();
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                employee_id = rs.getInt(1);

                // Flujo JBlue: Enriquecimiento post-operación del DTO
                dto.put("id", String.valueOf(employee_id));
                String now = Formats.getLocalDateTime(LocalDateTime.now());
                dto.put("date_update", now);
                dto.put("date_register", now);
            }
        }
        return employee_id;
    }

    /**
     * Realiza una actualización dinámica basada en deltas (Delta Update).
     * <br>
     * Compara los valores previos contra los nuevos del formulario y actualiza
     * exclusivamente las columnas modificadas.
     *
     * * @param connection Conexión transaccional.
     * @param oldEmp Estado actual guardado en la base de datos.
     * @param newEmp Nuevos cambios capturados de la interfaz Swing.
     * @return true si se registraron cambios en la BD, false si no hubo
     * mutación de datos.
     * @throws SQLException Si ocurre un error interno en el driver JDBC.
     */
    public boolean update(JDBConnection connection, EmployeeDTO oldEmp, EmployeeDTO newEmp) throws SQLException {
        List<String> setColumns = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // Escaneo selectivo de los campos mutables de la entidad
        autoSet(oldEmp.getRfc(), newEmp.getRfc(), "rfc", setColumns, parameters);
        autoSet(oldEmp.getCurp(), newEmp.getCurp(), "curp", setColumns, parameters);
        autoSet(oldEmp.getFirstName(), newEmp.getFirstName(), "first_name", setColumns, parameters);
        autoSet(oldEmp.getLastName1(), newEmp.getLastName1(), "last_name1", setColumns, parameters);
        autoSet(oldEmp.getLastName2(), newEmp.getLastName2(), "last_name2", setColumns, parameters);
        autoSet(oldEmp.getGender(), newEmp.getGender(), "gender", setColumns, parameters);
        autoSet(oldEmp.getBirthdate(), newEmp.getBirthdate(), "birthdate", setColumns, parameters);
        autoSet(oldEmp.getPersonalEmail(), newEmp.getPersonalEmail(), "personal_email", setColumns, parameters);
        autoSet(oldEmp.getPersonalNumber(), newEmp.getPersonalNumber(), "personal_number", setColumns, parameters);
        autoSet(oldEmp.getStreet1(), newEmp.getStreet1(), "street1", setColumns, parameters);
        autoSet(oldEmp.getStreet2(), newEmp.getStreet2(), "street2", setColumns, parameters);
        autoSet(oldEmp.getInsideNumber(), newEmp.getInsideNumber(), "inside_number", setColumns, parameters);
        autoSet(oldEmp.getOutsideNumber(), newEmp.getOutsideNumber(), "outside_number", setColumns, parameters);
        autoSet(oldEmp.getStatus(), newEmp.getStatus(), "status", setColumns, parameters);

        // Si no existen modificaciones en los JTextFields, se corta el tráfico a la DB
        if (setColumns.isEmpty()) {
            return false;
        }

        // Construcción de la sentencia SQL adaptativa
        String setClause = String.join("=?, ", setColumns) + "=?";
        String query = "UPDATE emp_employee SET " + setClause + " WHERE id = ? AND status = 1";

        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            int index = 1;
            for (Object param : parameters) {
                if (param == null) {
                    ps.setNull(index, Types.VARCHAR);
                } else {
                    ps.setObject(index, param);
                }
                index++;
            }

            // Cierre posicional del parámetro del WHERE (ID del Empleado)
            ps.setInt(index, Integer.parseInt(newEmp.getId()));
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Busca un empleado activo por su ID de forma unificada empleando metadatos
     * dinámicos.
     *
     * * @param connection Conexión activa.
     * @param employeeId Llave primaria de búsqueda.
     * @return Un Optional con el EmployeeDTO mapeado secuencialmente, o vacío
     * si no se encuentra.
     * @throws SQLException Si ocurre un error relacional.
     */
    public Optional<EmployeeDTO> findById(JDBConnection connection, int employeeId) throws SQLException {
        Optional<EmployeeDTO> res = Optional.empty();
        String query = "SELECT * FROM emp_employee WHERE id = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    int columns = md.getColumnCount();
                    String[] fields = new String[columns];

                    for (int i = 0; i < columns; i++) {
                        fields[i] = md.getColumnLabel(i + 1);
                    }

                    EmployeeDTO aux = new EmployeeDTO();
                    for (String field : fields) {
                        aux.getMap().put(field, rs.getString(field));
                    }
                    res = Optional.of(aux);
                }
            }
        }
        return res;
    }

    /**
     * Método utilitario interno para añadir deltas de campos modificados.
     */
    private <T> void autoSet(T oldValue, T newValue, String columnName, List<String> setColumns, List<Object> parameters) {
        if (!java.util.Objects.equals(oldValue, newValue)) {
            setColumns.add(columnName);
            parameters.add(newValue);
        }
    }
}
