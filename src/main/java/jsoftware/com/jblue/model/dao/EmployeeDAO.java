/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jutil.db.JDBConnection;

/**
 * Data Access Object (DAO) para la gestión de la tabla emp_employee.
 */
public class EmployeeDAO {

    // Constante para borrado lógico (Status = 3)
    private static final int STATUS_DELETED = 3;
    // Constante para status ACTIVO (Status = 1), útil para consultas
    private static final int STATUS_ACTIVE = 1;

    // ----------------------------------------------------------------------
    // CRUD: GET (SELECT ONE)
    // ----------------------------------------------------------------------
    /**
     * Obtiene un empleado por su ID. Solo devuelve empleados que no están
     * eliminados lógicamente.
     *
     * @param connection La conexión JDBC.
     * @param id El ID del empleado.
     * @return El objeto EmployeeDTO o {@code null} si no se encuentra.
     */
    public EmployeeDTO get(JDBConnection connection, int id) {
        String query = "SELECT * FROM emp_employee WHERE id = ? AND status != ?";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, id);
            ps.setInt(2, STATUS_DELETED);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDTO(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener empleado por ID: " + id, e);
        }
        return null;
    }
// ----------------------------------------------------------------------
    // CRUD: GET (SELECT ONE)
    // ----------------------------------------------------------------------

    /**
     * Obtiene un empleado por su ID. Solo devuelve empleados que no están
     * eliminados lógicamente.
     *
     * @param connection La conexión JDBC.
     * @param id El ID del empleado.
     * @return El objeto EmployeeDTO o {@code null} si no se encuentra.
     */
    public EmployeeDTO get(JDBConnection connection, String user, String password) {
        String query = "SELECT * FROM emp_employee WHERE user = ? AND password = ? AND status = ?";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, user);
            ps.setString(2, password);
            ps.setInt(3, STATUS_ACTIVE);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDTO(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener empleado por ID: " + user, e);
        }
        return null;
    }

    // ----------------------------------------------------------------------
    // CRUD: GETLIST (SELECT ALL ACTIVE)
    // ----------------------------------------------------------------------
    /**
     * Obtiene una lista de todos los empleados activos (status != 3).
     *
     * @param connection La conexión JDBC.
     * @return Lista de objetos EmployeeDTO.
     */
    public List<EmployeeDTO> getList(JDBConnection connection) {
        List<EmployeeDTO> employees = new ArrayList<>();
        String query = "SELECT * FROM emp_employee WHERE status != ? ORDER BY last_name1, first_name";

        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, STATUS_DELETED);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    employees.add(mapResultSetToDTO(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la lista de empleados.", e);
        }
        return employees;
    }

    /**
     * Mapea un ResultSet a un HysAdministrationHistoryDTO de forma dinámica.
     * <p>
     * Este método utiliza los metadatos del ResultSet para iterar sobre todas
     * las columnas del resultado y asignarlas al mapa interno del DTO. Esto
     * asume que {@code HysAdministrationHistoryDTO} implementa la lógica de un
     * DTO basado en mapa (como en {@code AbstractMapDTO}).
     * </p>
     *
     * @param rs El ResultSet posicionado en una fila válida (ya ejecutado).
     * @return Un DTO ({@code HysAdministrationHistoryDTO}) con los valores
     * mapeados.
     * @throws SQLException Si ocurre un error al acceder a los datos del
     * ResultSet (ej. columna no existe).
     */
    private static EmployeeDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        EmployeeDTO dto = new EmployeeDTO();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnLabel(i); // Nombre de la columna (o alias)
            Object value = rs.getObject(i);

            // Asume que dto.getMap() devuelve el mapa interno para inyección de valores.
            dto.getMap().put(columnName, value);
        }
        return dto;
    }

    // ----------------------------------------------------------------------
    // CRUD: INSERT
    // ----------------------------------------------------------------------
    /**
     * Inserta un nuevo registro de empleado en la base de datos.
     *
     * @param connection La conexión JDBC.
     * @param employee El objeto EmployeeDTO con los datos a insertar.
     * @return El ID generado para el nuevo empleado, o -1 si falla.
     */
    public int insert(JDBConnection connection, EmployeeDTO employee) {
        String query = "INSERT INTO emp_employee ("
                + "curp, first_name, last_name1, last_name2, gender, email, "
                + "birthdate, phone_number1, phone_number2, employee_type, status, user, password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Usamos Connection de JDBConnection para obtener las claves generadas
        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            int i = 1;
            ps.setString(i++, employee.getCurp());
            ps.setString(i++, employee.getFirstName());
            ps.setString(i++, employee.getLastName1());
            ps.setString(i++, employee.getLastName2());
            ps.setString(i++, employee.getGender());
            ps.setString(i++, employee.getEmail());
            ps.setObject(i++, employee.getBirthdate()); // LocalDate se mapea a SQL DATE
            ps.setString(i++, employee.getPhoneNumber1());
            ps.setString(i++, employee.getPhoneNumber2());
            ps.setString(i++, employee.getEmployeeType());
            ps.setInt(i++, employee.getStatus() > 0 ? employee.getStatus() : STATUS_ACTIVE); // Asigna 1 si no está seteado
            ps.setString(i++, employee.getUser());
            ps.setString(i++, employee.getPassword());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La inserción del empleado falló, no se afectaron filas.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Devuelve el ID generado
                } else {
                    throw new SQLException("La inserción del empleado falló, no se obtuvo ID generado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar empleado.", e);
        }
    }

    // ----------------------------------------------------------------------
    // CRUD: UPDATE
    // ----------------------------------------------------------------------
    /**
     * Actualiza un registro de empleado existente.
     *
     * @param connection La conexión JDBC.
     * @param employee El objeto EmployeeDTO con los datos a actualizar y el ID.
     * @return El número de filas afectadas (1 si fue exitoso, 0 si no se
     * encontró).
     */
    public int update(JDBConnection connection, EmployeeDTO employee) {
        String query = "UPDATE emp_employee SET "
                + "curp=?, first_name=?, last_name1=?, last_name2=?, gender=?, email=?, "
                + "birthdate=?, phone_number1=?, phone_number2=?, employee_type=?, status=?, user=?, password=? "
                + "WHERE id=?";

        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            int i = 1;
            ps.setString(i++, employee.getCurp());
            ps.setString(i++, employee.getFirstName());
            ps.setString(i++, employee.getLastName1());
            ps.setString(i++, employee.getLastName2());
            ps.setString(i++, employee.getGender());
            ps.setString(i++, employee.getEmail());
            ps.setObject(i++, employee.getBirthdate());
            ps.setString(i++, employee.getPhoneNumber1());
            ps.setString(i++, employee.getPhoneNumber2());
            ps.setString(i++, employee.getEmployeeType());
            ps.setInt(i++, employee.getStatus());
            ps.setString(i++, employee.getUser());
            ps.setString(i++, employee.getPassword());
            // WHERE clause
            ps.setString(i++, employee.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar empleado con ID: " + employee.getId(), e);
        }
    }

    // ----------------------------------------------------------------------
    // CRUD: DELETE (LÓGICO)
    // ----------------------------------------------------------------------
    /**
     * Realiza un borrado lógico del empleado, estableciendo el status a 3
     * (Eliminado) y registrando la fecha de fin (date_end).
     *
     * @param connection La conexión JDBC.
     * @param id El ID del empleado a eliminar.
     * @return El número de filas afectadas (1 si fue exitoso, 0 si no se
     * encontró).
     */
    public int delete(JDBConnection connection, int id) {
        String query = "UPDATE emp_employee SET status = ?, date_end = CURRENT_TIMESTAMP WHERE id = ?";

        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, STATUS_DELETED);
            ps.setInt(2, id);

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al realizar borrado lógico del empleado con ID: " + id, e);
        }
    }
}
