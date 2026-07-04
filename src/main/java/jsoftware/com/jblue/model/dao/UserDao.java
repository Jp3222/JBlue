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
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.querys.UserQuerys;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 * Data Access Object para la gestión de usuarios generales de JBlue.
 * <br>
 * Centraliza las transacciones relacionales, inyecciones delta y el
 * enriquecimiento post-operación de la estructura dinámica del DTO.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2025-11-23
 * @version 1.5
 */
public class UserDao extends AbstractDAO implements TableComponentDAO<UserDTO>, ListComponentDAO<UserDTO> {

    private static final long serialVersionUID = 1L;

    public UserDao(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Registra un nuevo usuario en el sistema (usr_user).
     * <p>
     * Tras una inserción exitosa, el objeto {@code UserDTO} se enriquece con el
     * ID generado por MySQL y las marcas de tiempo locales del servidor.
     * </p>
     *
     * @param connection Conexión transaccional activa.
     * @param user Objeto DTO con el mapa de datos sanitizados.
     * @return El ID numérico (PK) generado por la base de datos.
     * @throws SQLException Si ocurre un error de duplicidad o de sintaxis SQL.
     * @throws CorruptInsertionException Si el número de filas afectadas es
     * distinto de 1.
     * @throws KeyNotGenerateException Si MySQL no devuelve la llave
     * autoincrementable.
     */
    public int insert(JDBConnection connection, UserDTO user) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int user_id = 0;
        String query = UserQuerys.INSERT_USER;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // 1. IDENTIDAD DE USUARIO (Mapeo secuencial exacto)
            ps.setString(1, user.getRfc());
            ps.setString(2, user.getCurp());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName1());
            // El segundo apellido puede ser opcional
            ps.setString(5, user.getLastName2());
            ps.setString(6, user.getGender());
            ps.setString(7, user.getBirthdate());

            // 2. CONTACTO DE USUARIO (Mapeo null-safe tolerante)
            setNull(ps, 8, user.getEmail());
            setNull(ps, 9, user.getPhoneNumber1());
            setNull(ps, 10, user.getPhoneNumber2());

            // 3. CONFIGURACIÓN E INSTITUCIÓN
            ps.setString(11, user.getUserType());
            ps.setInt(12, Integer.parseInt(user.getLastEmployeeUpdate()));
            ps.setInt(13, Integer.parseInt(user.getOfficeId()));
            ps.setString(14, user.getStatus());

            int affected_row = ps.executeUpdate();
            if (affected_row == PreparedStatement.EXECUTE_FAILED || affected_row != 1) {
                throw new CorruptInsertionException();
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                user_id = rs.getInt(1);

                // FLUJO JBLUE: Enriquecimiento dinámico del DTO tras el éxito de la operación
                user.put("id", String.valueOf(user_id));
                String now = Formats.getLocalDateTime(LocalDateTime.now());
                user.put("date_update", now);
                user.put("date_register", now);
            }
        }
        return user_id;
    }

    /**
     * Realiza una actualización selectiva (Delta Update) del usuario.
     */
    public boolean updateOldUser(JDBConnection connection, UserDTO old_user, UserDTO new_user) throws SQLException {
        StringBuilder sb = new StringBuilder(400);
        List<Object> params = new ArrayList<>();
        sb.append("UPDATE usr_user SET ");

        // Registro dinámico de cambios en columnas de texto y relaciones
        Func.appendIfChanged(sb, params, "rfc", old_user.getRfc(), new_user.getRfc(), false);
        Func.appendIfChanged(sb, params, "curp", old_user.getCurp(), new_user.getCurp(), false);
        Func.appendIfChanged(sb, params, "first_name", old_user.getFirstName(), new_user.getFirstName(), false);
        Func.appendIfChanged(sb, params, "last_name1", old_user.getLastName1(), new_user.getLastName1(), false);
        Func.appendIfChanged(sb, params, "last_name2", old_user.getLastName2(), new_user.getLastName2(), false);
        Func.appendIfChanged(sb, params, "gender", old_user.getGender(), new_user.getGender(), false);
        Func.appendIfChanged(sb, params, "birthdate", old_user.getBirthdate(), new_user.getBirthdate(), false);
        Func.appendIfChanged(sb, params, "email", old_user.getEmail(), new_user.getEmail(), false);
        Func.appendIfChanged(sb, params, "phone_number1", old_user.getPhoneNumber1(), new_user.getPhoneNumber1(), false);
        Func.appendIfChanged(sb, params, "phone_number2", old_user.getPhoneNumber2(), new_user.getPhoneNumber2(), false);
        Func.appendIfChanged(sb, params, "user_type", old_user.getUserType(), new_user.getUserType(), false);
        Func.appendIfChanged(sb, params, "status", old_user.getStatus(), new_user.getStatus(), false);

        // Para llaves foráneas numéricas, evaluamos el cambio antes de inyectar el Integer cast
        if (!java.util.Objects.equals(old_user.getOfficeId(), new_user.getOfficeId())) {
            sb.append("office_id = ?, ");
            params.add(Integer.valueOf(new_user.getOfficeId()));
        }

        if (params.isEmpty()) {
            return false; // Evita peticiones innecesarias al servidor de base de datos
        }

        // Cierre de auditoría mandatorio
        sb.append("last_employee_update = ? WHERE id = ? AND status = 1");
        params.add(Integer.valueOf(new_user.getLastEmployeeUpdate()));
        params.add(Integer.valueOf(old_user.getId()));

        try (PreparedStatement ps = connection.getNewPreparedStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza de forma dinámica las columnas mutadas utilizando el mapa de
     * autoconfiguración.
     */
    public boolean updateUserDynamic(JDBConnection connection, UserDTO old_user, UserDTO new_user) throws SQLException {
        List<String> setColumns = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // Evaluación exhaustiva de los 18 campos del nuevo estándar
        autoSet(old_user.getRfc(), new_user.getRfc(), "rfc", setColumns, parameters);
        autoSet(old_user.getCurp(), new_user.getCurp(), "curp", setColumns, parameters);
        autoSet(old_user.getFirstName(), new_user.getFirstName(), "first_name", setColumns, parameters);
        autoSet(old_user.getLastName1(), new_user.getLastName1(), "last_name1", setColumns, parameters);
        autoSet(old_user.getLastName2(), new_user.getLastName2(), "last_name2", setColumns, parameters);
        autoSet(old_user.getGender(), new_user.getGender(), "gender", setColumns, parameters);
        autoSet(old_user.getBirthdate(), new_user.getBirthdate(), "birthdate", setColumns, parameters);
        autoSet(old_user.getEmail(), new_user.getEmail(), "email", setColumns, parameters);
        autoSet(old_user.getPhoneNumber1(), new_user.getPhoneNumber1(), "phone_number1", setColumns, parameters);
        autoSet(old_user.getPhoneNumber2(), new_user.getPhoneNumber2(), "phone_number2", setColumns, parameters);
        autoSet(old_user.getUserType(), new_user.getUserType(), "user_type", setColumns, parameters);
        autoSet(old_user.getStatus(), new_user.getStatus(), "status", setColumns, parameters);

        if (!java.util.Objects.equals(old_user.getOfficeId(), new_user.getOfficeId())) {
            setColumns.add("office_id");
            parameters.add(Integer.valueOf(new_user.getOfficeId()));
        }

        if (setColumns.isEmpty()) {
            return true;
        }

        // Incorporación obligatoria del registro de auditoría
        setColumns.add("last_employee_update");
        parameters.add(Integer.valueOf(new_user.getLastEmployeeUpdate()));

        String setClause = String.join("=?, ", setColumns) + "=?";
        String dynamicUpdateSQL = "UPDATE usr_user SET " + setClause + " WHERE id=?";

        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(dynamicUpdateSQL)) {
            int index = 1;
            for (Object param : parameters) {
                if (param == null) {
                    ps.setNull(index, Types.VARCHAR);
                } else if (param instanceof Integer intParam) {
                    ps.setInt(index, intParam);
                } else {
                    ps.setObject(index, param);
                }
                index++;
            }

            ps.setInt(index, Integer.parseInt(new_user.getId()));
            return ps.executeUpdate() > 0;
        }
    }

    private <T> void autoSet(T oldValue, T newValue, String columnName, List<String> setColumns, List<Object> parameters) {
        if (!java.util.Objects.equals(oldValue, newValue)) {
            setColumns.add(columnName);
            parameters.add(newValue);
        }
    }

    public boolean delete(JDBConnection connection, int userId, int employeeId) throws SQLException {
        boolean res = false;
        String query = UserQuerys.UPDATE_USER_STATUS;
        try (PreparedStatement pstmt = connection.getNewPreparedStatement(query)) {
            pstmt.setInt(1, Const.INDEX_STATUS_ELIMINADO_3);
            pstmt.setInt(2, employeeId);
            pstmt.setInt(3, userId);

            int affectedRows = pstmt.executeUpdate();
            res = affectedRows == 1;
            if (!res) {
                throw new SQLException("EL QUERY AFECTÓ %s REGISTROS".formatted(affectedRows));
            }
        }
        return res;
    }

    /**
     * Recupera un usuario mapeando dinámicamente las columnas al mapa interno
     * del DTO.
     */
    public Optional<UserDTO> findById(JDBConnection connection, int userId) throws SQLException, Exception {
        Optional<UserDTO> res = Optional.empty();
        String query = "SELECT * FROM usr_user WHERE id = ?";
        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                // CORRECCIÓN: Quitamos el doble rs.next() que provocaba la pérdida del primer registro
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    int columns = md.getColumnCount();
                    String[] fields = new String[columns];

                    for (int i = 0; i < columns; i++) {
                        fields[i] = md.getColumnLabel(i + 1);
                    }

                    UserDTO aux = new UserDTO();
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
     * Verifica la existencia de un usuario por ID, RFC, CURP o Nombre Completo
     * de manera secuencial. Si el usuario existe, el método enriquece el DTO
     * inyectando el ID y el Status recuperados.
     *
     * @param connection Conexión activa a la base de datos de JBlue.
     * @param dto Datos del usuario a buscar.
     * @return true si el usuario ya existe bajo alguno de los criterios; false
     * en caso contrario.
     * @throws SQLException Si ocurre un error en la ejecución del
     * PreparedStatement.
     */
    public boolean exists(JDBConnection connection, UserDTO dto) throws SQLException {

        // 1. Criterio secuencial por ID
        if (Func.isNotNullEmptyBlank(dto.getId())) {
            if (existsById(connection, dto)) {
                return true;
            }
        }

        // 2. Criterio secuencial por RFC
        if (Func.isNotNullEmptyBlank(dto.getRfc())) {
            if (existsByRfc(connection, dto)) {
                return true;
            }
        }

        // 3. Criterio secuencial por CURP
        if (Func.isNotNullEmptyBlank(dto.getCurp())) {
            if (existsByCurp(connection, dto)) {
                return true;
            }
        }

        // 4. Criterio secuencial por Nombre Completo (Validación compuesta mínima requerida)
        if (Func.isNotNullEmptyBlank(dto.getFirstName()) && Func.isNotNullEmptyBlank(dto.getLastName1())) {
            if (existsByName(connection, dto)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Busca en la base de datos un usuario por id, si existe añade los datos id
     * y status al dto y retorna true, si no retorna un falso sin agregar datos
     * al objeto
     *
     * @param connection - conexion activa
     * @param dto - objeto usuario
     * @return true y solo true si el id existen en la base de datos
     * @throws SQLException
     */
    private boolean existsById(JDBConnection connection, UserDTO dto) throws SQLException {
        String query = "SELECT id, status FROM usr_user WHERE id = ?";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            // Cast manual requerido por el tipo de columna INT en MySQL
            ps.setInt(1, Integer.parseInt(dto.getId()));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto.put("id", rs.getString("id"));
                    dto.put("status", rs.getString("status"));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Busca en la base de datos un usuario por rfc, si existe añade los datos
     * id y status al dto y retorna true, si no retorna un falso sin agregar
     * datos al objeto
     *
     * @param connection - conexion activa
     * @param dto - objeto usuario
     * @return true y solo true si el id existen en la base de datos
     * @throws SQLException
     */
    private boolean existsByRfc(JDBConnection connection, UserDTO dto) throws SQLException {
        String query = "SELECT id, status FROM usr_user WHERE rfc = ?";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, dto.getRfc());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto.put("id", rs.getString("id"));
                    dto.put("status", rs.getString("status"));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Busca en la base de datos un usuario por curp, si existe añade los datos
     * id y status al dto y retorna true, si no retorna un falso sin agregar
     * datos al objeto
     *
     * @param connection - conexion activa
     * @param dto - objeto usuario
     * @return true y solo true si el id existen en la base de datos
     * @throws SQLException
     */
    private boolean existsByCurp(JDBConnection connection, UserDTO dto) throws SQLException {
        String query = "SELECT id, status FROM usr_user WHERE curp = ?";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, dto.getCurp());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto.put("id", rs.getString("id"));
                    dto.put("status", rs.getString("status"));
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Busca en la base de datos un usuario por nombre, apellido paterni y
     * apellido materni, si existe o hay una coincidencia con los primeros dos
     * campos añade los datos id y status al dto y retorna true, si no retorna
     * un falso sin agregar datos al objeto
     *
     * @param connection - conexion activa
     * @param dto - objeto usuario
     * @return true y solo true si el id existen en la base de datos
     * @throws SQLException
     */
    private boolean existsByName(JDBConnection connection, UserDTO dto) throws SQLException {
        String query = "SELECT id, status FROM usr_user WHERE first_name = ? AND last_name1 = ? AND last_name2 = ?";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, dto.getFirstName());
            ps.setString(2, dto.getLastName1());
            // Mapeo seguro si el segundo apellido es nulo en la interfaz Swing
            ps.setString(3, Func.isNotNullEmptyBlank(dto.getLastName2()) ? dto.getLastName2() : null);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto.put("id", rs.getString("id"));
                    dto.put("status", rs.getString("status"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<UserDTO> getList(JDBConnection connection, JTableModel model) {
        List<UserDTO> userList = new ArrayList<>();
        String query = "SELECT id, first_name, last_name1, last_name2, status, date_register FROM usr_user";

        try (PreparedStatement pstmt = connection.getNewPreparedStatement(query); ResultSet rs = pstmt.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            String[] arr = new String[columnCount];

            // CORRECCIÓN: Poblamos el arreglo de etiquetas para que el bucle interno funcione
            for (int i = 0; i < columnCount; i++) {
                arr[i] = md.getColumnLabel(i + 1);
            }

            // Inicialización segura del modelo de la tabla
            model = new JTableModel(arr, 0);

            while (rs.next()) {
                UserDTO user = new UserDTO();
                for (String field : arr) {
                    user.put(field, rs.getString(field));
                }
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.getLogger(this.getClass().getName()).log(System.Logger.Level.ERROR, "Error en getList con modelo", ex);
        }
        return userList;
    }

    @Override
    public List<UserDTO> getList(JDBConnection connection) throws SQLException, Exception {
        List<UserDTO> list = new ArrayList<>(100);
        String query = "SELECT * FROM usr_user WHERE status = 1";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query); ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();
            int size = md.getColumnCount();
            String[] fields = new String[size];

            for (int i = 0; i < size; i++) {
                fields[i] = md.getColumnLabel(i + 1);
            }

            while (rs.next()) {
                UserDTO aux = new UserDTO();
                for (String field : fields) {
                    aux.getMap().put(field, rs.getString(field));
                }
                list.add(aux);
            }
        }
        return list;
    }

    @Override
    public JTableModel buildModel(JDBConnection connection, JTableModel model) {
        return null;
    }
}
