/*
 * Copyright (C) 2025 juanp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jsoftware.com.jblue.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.swingw.modelos.JTableModel;
import jsoftware.com.jutil.util.JFunc;

/**
 *
 * @author juanp
 */
public class UserDao extends AbstractDAO implements TableComponentDAO<UserDTO> {

    private static final long serialVersionUID = 1L;

    public UserDao(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    // ----------------------------------------------------
    // 1. INSERCIÓN: Insertar y recuperar el ID generado
    // ----------------------------------------------------
    private static final String INSERT_SQL
            = "INSERT INTO usr_user (curp, first_name, last_name1, last_name2, gender, email, phone_number1, "
            + "phone_number2, street1, street2, inside_number, outside_number, "
            + "water_intake_type, user_type, status, last_employee_update) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public int insertUser(JDBConnection connection, UserDTO user) {
        int userId = 0;
        try (Connection conn = connection.getConnection(); // Obtener la conexión
                // Solicitamos a MySQL que nos devuelva las claves autogeneradas
                 PreparedStatement pstmt = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            // Asignación de parámetros (la numeración debe coincidir con el INSERT_SQL)
            pstmt.setString(1, user.getCurp());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName1());
            pstmt.setString(4, user.getLastName2());
            pstmt.setString(5, user.getGender());
            pstmt.setString(6, user.getEmail());
            pstmt.setString(7, user.getPhoneNumber1());
            pstmt.setString(8, user.getPhoneNumber2());
            pstmt.setString(9, user.getStreet1());

            // Manejo de campo NULLable: street2
            if (user.getStreet2() == null) {
                pstmt.setNull(10, Types.INTEGER);
            } else {
                pstmt.setString(10, user.getStreet2());
            }

            pstmt.setString(11, user.getInsideNumber());
            pstmt.setString(12, user.getOutsideNumber());
            pstmt.setString(13, user.getWaterIntakeType());
            pstmt.setString(14, user.getUserType());
            pstmt.setInt(15, user.getStatus());
            pstmt.setString(16, user.getLastEmployeeUpdate());
            pstmt.executeUpdate();
            // Recuperar el ID autogenerado
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    userId = rs.getInt(1);
                    user.getMap().put("id", userId); // Opcional: Asignar el ID al objeto User
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    /**
     * Actualiza dinámicamente solo las columnas modificadas del usuario. Esta
     * aproximación mejora el rendimiento y reduce las escrituras innecesarias
     * en la DB.
     *
     * @param connection La conexión transaccional.
     * @param old_user El DTO original con los valores antes de la edición.
     * @param new_user El DTO con los nuevos valores (incluye el ID).
     * @return true si al menos una fila fue afectada, false en caso contrario o
     * error.
     * @throws SQLException Si ocurre un error de JDBC.
     */
    public boolean updateUserDynamic(JDBConnection connection, UserDTO old_user, UserDTO new_user) throws SQLException {

        // 1. Listas para construir la consulta dinámicamente
        List<String> setColumns = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // 2. Comparación de campos: Añadir pares columna=valor a las listas solo si cambiaron
        // Nota: Se asume que getID() retorna el ID del usuario (clave WHERE)
        // Método auxiliar para comparación (funciona para String, int, etc.)
        autoSet(old_user.getCurp(), new_user.getCurp(), "curp", setColumns, parameters);
        autoSet(old_user.getFirstName(), new_user.getFirstName(), "first_name", setColumns, parameters);
        autoSet(old_user.getLastName1(), new_user.getLastName1(), "last_name1", setColumns, parameters);
        autoSet(old_user.getLastName2(), new_user.getLastName2(), "last_name2", setColumns, parameters);
        autoSet(old_user.getGender(), new_user.getGender(), "gender", setColumns, parameters);
        autoSet(old_user.getEmail(), new_user.getEmail(), "email", setColumns, parameters);
        autoSet(old_user.getPhoneNumber1(), new_user.getPhoneNumber1(), "phone_number1", setColumns, parameters);
        autoSet(old_user.getPhoneNumber2(), new_user.getPhoneNumber2(), "phone_number2", setColumns, parameters);
        autoSet(old_user.getStreet1(), new_user.getStreet1(), "street1", setColumns, parameters);

        // Manejo de Street2: Permitimos actualizar de valor a NULL (o viceversa)
        autoSet(old_user.getStreet2(), new_user.getStreet2(), "street2", setColumns, parameters);

        autoSet(old_user.getInsideNumber(), new_user.getInsideNumber(), "inside_number", setColumns, parameters);
        autoSet(old_user.getOutsideNumber(), new_user.getOutsideNumber(), "outside_number", setColumns, parameters);
        autoSet(old_user.getWaterIntakeType(), new_user.getWaterIntakeType(), "water_intake_type", setColumns, parameters);
        autoSet(old_user.getUserType(), new_user.getUserType(), "user_type", setColumns, parameters);
        autoSet(old_user.getStatus(), new_user.getStatus(), "status", setColumns, parameters);

        // 3. Incluir campo de auditoría obligatorio (se actualiza siempre que se llama a UPDATE)
        setColumns.add("last_employee_update");
        parameters.add(new_user.getLastEmployeeUpdate());

        // Si no hay campos para actualizar, la operación es innecesaria
        if (setColumns.isEmpty()) {
            // Podríamos retornar true o false dependiendo de la política. True si el registro existe.
            return true;
        }

        // 4. Construir la sentencia SQL dinámica
        String setClause = String.join("=?, ", setColumns) + "=?";
        String dynamicUpdateSQL = "UPDATE usr_user SET " + setClause + " WHERE id=?";

        // 5. Ejecutar la consulta
        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(dynamicUpdateSQL)) {

            // Asignar parámetros dinámicos
            int index = 1;
            for (Object param : parameters) {
                // Se necesita una lógica más robusta para tipos, pero para String/Integer simple:
                if (param == null) {
                    // Asume que todos los nulos comparados son de tipo VARCHAR (simplificación)
                    ps.setNull(index, Types.VARCHAR);
                } else if (param instanceof Integer) {
                    ps.setInt(index, (Integer) param);
                } else {
                    ps.setObject(index, param);
                }
                index++;
            }

            // Asignar parámetro WHERE (ID) al final
            ps.setString(index, new_user.getId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            // La impresión del stack trace y el retorno de false deben manejarse en el Controlador (capa superior)
            throw e; // Re-lanzar para que el controlador pueda hacer rollback
        }
    }

// ----------------------------------------------------
// Método Auxiliar (Debe estar en la clase DAO)
// ----------------------------------------------------
    private <T> void autoSet(T oldValue, T newValue, String columnName,
            List<String> setColumns, List<Object> parameters) {

        // Solo si el valor es diferente al anterior O si el nuevo valor es NULL y el anterior no lo era.
        // Usamos Objects.equals para manejar nulos de forma segura.
        if (!java.util.Objects.equals(oldValue, newValue)) {
            setColumns.add(columnName);
            parameters.add(newValue);
        }
    }

    // ----------------------------------------------------
    // 3. BORRADO LÓGICO (Actualizar Status)
    // ----------------------------------------------------
    private static final String LOGICAL_DELETE_SQL
            = "UPDATE usr_user SET status=?, date_end=?, last_employee_update=? WHERE id=?";

    public boolean logicalDeleteUser(JDBConnection connection, int userId, int employeeId) {
        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(LOGICAL_DELETE_SQL)) {

            // 1. Status de Borrado Lógico (usamos el ID definido en el modelo)
            pstmt.setInt(1, 3);
            // 2. date_end (Registramos la fecha de baja)
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            // 3. last_employee_update (Quién hizo la baja)
            pstmt.setInt(3, employeeId);
            // 4. id (Condición WHERE)
            pstmt.setInt(4, userId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Consulta SQL que selecciona todas las columnas de la tabla usr_user
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM usr_user WHERE id = ?";

    /**
     *
     * @param connection
     * @param dto
     * @return -1 si no existe(se puede insertar), 0 si hubo un fallo interno(no
     * se puede insertar), un numero mayor a 0(status del usuario, no se puede
     * insertar)
     */
    public int exists(JDBConnection connection, UserDTO dto) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT status FROM usr_user WHERE ");
        int search_count = 0;
        if (JFunc.isNotNullEmptyBlank(dto.getId())) {
            sb.append("id = ?");
            search_count++;
        }
        if (JFunc.isNotNullEmptyBlank(dto.getCurp())) {
            if (search_count > 0) {
                sb.append(" AND ");
            }
            sb.append("curp = ?");
            search_count++;

        }
        if (JFunc.isNotNullEmptyBlank(dto.getFirstName()) && JFunc.isNotNullEmptyBlank(dto.getLastName1()) && JFunc.isNotNullEmptyBlank(dto.getLastName2())) {
            if (search_count > 0) {
                sb.append(" AND ");
            }
            sb.append("first_name = ? AND last_name1 = ? AND last_name2 = ?");
            search_count++;
        }
        try (PreparedStatement ps = connection.getNewPreparedStatement(sb.toString())) {
            search_count = 1;
            if (JFunc.isNotNullEmptyBlank(dto.getId())) {
                ps.setString(search_count, dto.getId());
                search_count++;
            }
            if (JFunc.isNotNullEmptyBlank(dto.getCurp())) {
                ps.setString(search_count, dto.getCurp());
                search_count++;
            }
            if (JFunc.isNotNullEmptyBlank(dto.getFirstName()) && JFunc.isNotNullEmptyBlank(dto.getLastName1()) && JFunc.isNotNullEmptyBlank(dto.getLastName2())) {
                ps.setString(search_count, dto.getFirstName());
                search_count++;
                ps.setString(search_count, dto.getLastName1());
                search_count++;
                ps.setString(search_count, dto.getLastName2());
            }
            int status = 0;
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    status = rs.getInt("status");
                    return status;
                }
            }
            //no existe
            if (status == 0) {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //si hubo un error en el proceso retorna 0
        return 0;
    }

    /**
     * Mapea una fila completa del ResultSet al objeto DTO basado en Map.
     *
     * @param rs El ResultSet posicionado en la fila actual.
     * @return Un objeto UserDTO poblado.
     */
    public Optional<UserDTO> findById(JDBConnection connection, int userId) {
        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Usamos el nuevo método genérico para mapear todos los campos
                    UserDTO user = mapResultSetToDTO(rs);
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            // Manejo de la excepción
            e.printStackTrace();
        }
        // Retorna Optional.empty() si el usuario no existe o si ocurre un error
        return Optional.empty();
    }

    private UserDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        // Creamos una nueva instancia del DTO
        UserDTO user = new UserDTO();

        // Obtenemos los metadatos del ResultSet para conocer los nombres de las columnas
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnLabel(i); // Nombre de la columna
            Object value = rs.getObject(i);                 // Valor de la columna

            // Usamos el método set() del DTO para guardar el valor en el Map interno
            user.getMap().put(columnName, value);
        }
        return user;
    }

    @Override
    public List<UserDTO> getList(JDBConnection connection, JTableModel model) {
        List<UserDTO> userList = new ArrayList<>();

        // 1. Construir la consulta con StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  id, first_name, last_name1, last_name2, status, date_register ");
        sb.append("FROM usr_user");

        // 2. Ejecutar la consulta
        try (PreparedStatement pstmt = connection.getNewPreparedStatement(sb.toString()); ResultSet rs = pstmt.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();
            String[] arr = new String[md.getColumnCount()];
            model = new JTableModel(arr, 0);
            UserDTO user;
            while (rs.next()) {
                user = new UserDTO();
                for (String j : arr) {
                    user.put(j, rs.getString(j));
                }
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.getLogger(this.getClass().getName()).log(System.Logger.Level.ERROR, "Error en getList", ex);
        }

        return userList;
    }

}
