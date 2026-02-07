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
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.querys.UserQuerys;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public class UserDao extends AbstractDAO implements TableComponentDAO<UserDTO>, ListComponentDAO<UserDTO> {

    private static final long serialVersionUID = 1L;

    public UserDao(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Registra un nuevo titular en el sistema (usr_user).
     * <p>
     * El proceso de inserción valida de forma implícita la integridad de los
     * datos mediante tipos definidos en MySQL. Tras una inserción exitosa, el
     * objeto {@code UserDTO} se actualiza con el ID generado y las marcas de
     * tiempo.
     * </p>
     * * <b>Campos Obligatorios:</b>
     * <ul>
     * <li>Identidad:
     * {@code curp, first_name, last_name1, last_name2, gender}</li>
     * <li>Ubicación: {@code street1}</li>
     * <li>Configuración:
     * {@code water_intake_type, user_type, status, last_employee_update}</li>
     * </ul>
     * * <b>Campos Opcionales:</b>
     * <ul>
     * <li>{@code email, phone_number1, phone_number2, street2, inside_number, outside_number}</li>
     * </ul>
     *
     * @param connection Conexión activa a la base de datos (proporcionada por
     * ConnectionFactory).
     * @param user Objeto DTO con el mapa de datos capturado desde la vista.
     * @return El ID (PK) generado por la base de datos.
     * @throws SQLException Si ocurre un error de duplicidad (ej. CURP), falla
     * de conexión, o si el número de registros afectados es distinto de 1.
     * @throws Exception Para errores de conversión de tipos (parseInt) o nulos
     * inesperados.
     */
    public int insert(JDBConnection connection, UserDTO user) throws SQLException {
        int user_id = 0;
        String query = UserQuerys.INSERT_USER;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            //IDENTIDAD DE USUARIO
            ps.setString(1, user.getCurp());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName1());
            ps.setString(4, user.getLastName2());
            ps.setString(5, user.getGender());
            ps.setString(6, user.getBirthdate());
            //CONTACTO DE USUARIO
            setNull(ps, 7, user.getEmail());
            setNull(ps, 8, user.getPhoneNumber1());
            setNull(ps, 9, user.getPhoneNumber2());
            //DIRECCION DE USUARIO
            ps.setString(10, user.getStreet1());
            setNull(ps, 11, user.getStreet2());
            setNull(ps, 12, user.getInsideNumber());
            setNull(ps, 13, user.getOutsideNumber());
            //TIPO DE USUARIO
            ps.setString(14, user.getUserType());
            //STATUS DE USUARIO
            ps.setInt(15, user.getStatus());
            ps.setString(16, user.getLastEmployeeUpdate());
            int affected_row = ps.executeUpdate();
            if (affected_row == PreparedStatement.EXECUTE_FAILED || affected_row != 1) {
                throw new SQLException("INSERCCION DE USUARIO FALLIDA");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("LLAVE PRIMARIA CORRUPTA");
                }
                user_id = rs.getInt(1);
                user.getMap().put("id", user_id);
                user.getMap().put("date_update", Formats.getLocalDateTime(LocalDateTime.now()));
                user.getMap().put("date_resgiter", Formats.getLocalDateTime(LocalDateTime.now()));
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return user_id;
    }

    /**
     * Realiza una actualización selectiva (Delta Update) del usuario.
     * <p>
     * Compara los atributos del usuario original contra los nuevos valores y
     * construye dinámicamente la sentencia SQL. Si no se detectan cambios en
     * los campos editables, el método finaliza sin realizar peticiones a la
     * base de datos.
     * </p>
     *
     * @param connection Conexión transaccional activa.
     * @param old_user Estado previo del usuario (recuperado de la BD).
     * @param new_user Estado actual capturado en la vista.
     * @return true si se aplicaron cambios; false si no hubo diferencias o no
     * se afectaron filas.
     * @throws SQLException Si ocurre un error de integridad o pérdida de
     * conexión.
     */
    public boolean updateOldUser(JDBConnection connection, UserDTO old_user, UserDTO new_user) throws SQLException {
        StringBuilder sb = new StringBuilder(400);
        List<Object> params = new ArrayList<>();
        sb.append("UPDATE usr_user SET ");
        // 1. Registro de cambios (Campos de texto)
        Func.appendIfChanged(sb, params, "email", old_user.getEmail(), new_user.getEmail(), false);
        Func.appendIfChanged(sb, params, "phone_number1", old_user.getPhoneNumber1(), new_user.getPhoneNumber1(), false);
        Func.appendIfChanged(sb, params, "phone_number2", old_user.getPhoneNumber2(), new_user.getPhoneNumber2(), false);
        Func.appendIfChanged(sb, params, "inside_number", old_user.getInsideNumber(), new_user.getInsideNumber(), false);
        Func.appendIfChanged(sb, params, "outside_number", old_user.getOutsideNumber(), new_user.getOutsideNumber(), false);
        // 2. Registro de cambios (Campos numéricos/FK)
        Func.appendIfChanged(sb, params, "street1", old_user.getStreet1(), new_user.getStreet1(), true);
        Func.appendIfChanged(sb, params, "street2", old_user.getStreet2(), new_user.getStreet2(), true);

        if (params.isEmpty()) {
            return false; // Cero tráfico a Railway si no hay cambios
        }
        // 3. Cierre mandatorio (Empleado y WHERE)
        // Aquí NO hay coma antes de last_employee_update porque la utilidad la pone al final de cada campo previo
        sb.append("last_employee_update = ? WHERE id = ? AND status = 1");
        params.add(Integer.valueOf(new_user.getLastEmployeeUpdate()));
        params.add(Integer.valueOf(old_user.getId()));

        // 4. Ejecución
        try (PreparedStatement ps = connection.getNewPreparedStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            return ps.executeUpdate() > 0;
        }
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
    private <T> void autoSet(
            T oldValue, T newValue, String columnName, List<String> setColumns, List<Object> parameters
    ) {
        // Solo si el valor es diferente al anterior O si el nuevo valor es NULL y el anterior no lo era.
        // Usamos Objects.equals para manejar nulos de forma segura.
        if (!java.util.Objects.equals(oldValue, newValue)) {
            setColumns.add(columnName);
            parameters.add(newValue);
        }
    }

    public boolean delete(JDBConnection connection, int userId, int employeeId) throws SQLException {
        boolean res = false;
        String query = UserQuerys.UPDATE_USER_STATUS;
        try (PreparedStatement pstmt = connection.getNewPreparedStatement(query)) {
            pstmt.setInt(1, Const.INDEX_STATUS_ELIMINADO);
            pstmt.setInt(2, employeeId);
            pstmt.setInt(3, userId);

            int affectedRows = pstmt.executeUpdate();
            res = affectedRows == 1;
            if (!res) {
                throw new SQLException("EL QUERY AFECTO %s REGISTROS".formatted(affectedRows));
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    // Consulta SQL que selecciona todas las columnas de la tabla usr_user
    private static final String SELECT_BY_ID_SQL = "SELECT id, status FROM usr_user WHERE id = ?";

    /**
     * Verifica la existencia de un usuario por ID, CURP o Nombre Completo.
     *
     * * @param connection Conexión activa.
     * @param dto Datos del usuario a buscar.
     * @return int[] donde index 0 = ID del usuario, index 1 = Status. {-1, -1}
     * si no existe. {0, 0} si ocurre una excepción.
     */
    public int[] exists(JDBConnection connection, UserDTO dto) {
        // CORRECCIÓN: Agregamos "id" al SELECT para poder recuperarlo después
        StringBuilder sb = new StringBuilder("SELECT id, status FROM usr_user WHERE ");
        List<Object> params = new ArrayList<>();

        // Construcción dinámica de criterios con OR para detectar duplicados
        if (Func.isNotNullEmptyBlank(dto.getId())) {
            sb.append("id = ? ");
            params.add(Integer.valueOf(dto.getId()));
        }

        if (Func.isNotNullEmptyBlank(dto.getCurp())) {
            if (!params.isEmpty()) {
                sb.append(" OR ");
            }
            sb.append("curp = ? ");
            params.add(dto.getCurp());
        }

        if (Func.isNotNullEmptyBlank(dto.getFirstName()) && Func.isNotNullEmptyBlank(dto.getLastName1())) {
            if (!params.isEmpty()) {
                sb.append(" OR ");
            }
            sb.append("(first_name = ? AND last_name1 = ? AND last_name2 = ?) ");
            params.add(dto.getFirstName());
            params.add(dto.getLastName1());
            params.add(dto.getLastName2());
        }

        // Si el DTO no tiene criterios de búsqueda, evitamos un query inválido
        if (params.isEmpty()) {
            return new int[]{-1, -1};
        }

        try (PreparedStatement ps = connection.getNewPreparedStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Ahora ambos campos están disponibles en el ResultSet
                    int id = rs.getInt("id");
                    int status = rs.getInt("status");
                    return new int[]{id, status};
                }
            }
            return new int[]{-1, -1}; // Usuario no encontrado

        } catch (SQLException e) {
            e.printStackTrace();
            return new int[]{0, 0}; // Error de persistencia
        }
    }

    /**
     * Mapea una fila completa del ResultSet al objeto DTO basado en Map.
     *
     * @param rs El ResultSet posicionado en la fila actual.
     * @return Un objeto UserDTO poblado.
     */
    public Optional<UserDTO> findById(JDBConnection connection, int userId) throws SQLException, Exception {
        Optional<UserDTO> res = Optional.empty();
        String query = "SELECT * FROM usr_user WHERE id = ?";
        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    int size = md.getColumnCount();
                    String[] fields = new String[size];
                    for (int i = 0; i < fields.length; i++) {
                        fields[i] = md.getColumnLabel(i + 1);
                    }
                    if (rs.next()) {
                        UserDTO aux = new UserDTO();
                        for (String field : fields) {
                            aux.getMap().put(field, rs.getString(field));
                        }
                        res = Optional.of(aux);
                    }
                }
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return res;
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

    @Override
    public List<UserDTO> getList(JDBConnection connection) throws SQLException, Exception {
        List<UserDTO> list = List.of();
        String query = "SELECT * FROM usr_user WHERE status = 1";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query); ResultSet rs = ps.executeQuery();) {
            ResultSetMetaData md = rs.getMetaData();
            int size = md.getColumnCount();
            String[] fields = new String[size];
            for (int i = 0; i < fields.length; i++) {
                fields[i] = md.getColumnLabel(i + 1);
            }
            list = new ArrayList<>(1000);
            while (rs.next()) {
                UserDTO aux = new UserDTO();
                for (String field : fields) {
                    aux.getMap().put(field, rs.getString(field));
                }
                list.add(aux);
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

}
