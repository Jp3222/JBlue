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
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Data Access Object (DAO) para la gestión transaccional de la tabla de
 * vinculación de tomas de agua.
 * <br>
 * Administra el ciclo de vida, estados financieros y auditoría de la tabla
 * wki_user.
 *
 * * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-30
 * @version 1.0
 */
public class WaterIntakeUserDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public WaterIntakeUserDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Inserta un nuevo registro de vinculación entre un usuario y una toma de
     * agua.
     * <p>
     * El DAO realiza el cast final a tipos numéricos (Integer, Double)
     * requeridos por MySQL y enriquece el DTO con el ID autoincrementable y
     * marcas de tiempo de éxito.
     * </p>
     *
     * * @param connection Conexión activa.
     * @param dto DTO con los datos recolectados de la interfaz.
     * @return El ID autoincrementable generado por la base de datos.
     * @throws SQLException Si ocurre un error relacional en el motor.
     * @throws DataAccesObjectException Si la inserción es corrupta o falla la
     * generación de llaves.
     */
    public int insert(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException, DataAccesObjectException {
        int generated_id = -1;
        String query = """
                       INSERT INTO wki_user
                       (user_id, address_id, water_intake_id, water_intake_type_id, user_type_id, 
                        office_id, user_name, description, observation, current_fiscal_year, 
                        last_month_paid, last_amount_paid, employee_register, last_employee_update, 
                        original_process, last_process_type, status)
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // 1. Relaciones Foráneas Obligatorias (El DAO realiza el cast final a Integer)
            ps.setInt(1, Integer.parseInt(dto.getUserId()));
            ps.setInt(2, Integer.parseInt(dto.getAddressId()));
            ps.setInt(3, Integer.parseInt(dto.getWaterIntakeId()));
            ps.setInt(4, Integer.parseInt(dto.getWaterIntakeTypeId()));
            ps.setInt(5, Integer.parseInt(dto.getUserTypeId()));
            ps.setInt(6, Integer.parseInt(dto.getOfficeId()));

            // 2. Información Descriptiva del Padrón
            ps.setString(7, dto.getUserName());
            setNull(ps, 8, dto.getDescription());
            setNull(ps, 9, dto.getObservation());

            // 3. Estado Financiero y de Recaudación (Casteo manual numérico seguro)
            ps.setInt(10, Integer.parseInt(dto.getCurrentFiscalYear()));
            ps.setInt(11, Integer.parseInt(dto.getLastMonthPaid()));
            ps.setDouble(12, Double.parseDouble(dto.getLastAmountPaid()));

            // 4. Personal de Auditoría e Identificadores de Procesos
            ps.setInt(13, Integer.parseInt(dto.getEmployeeRegister()));
            ps.setInt(14, Integer.parseInt(dto.getLastEmployeeUpdate()));

            setNull(ps, 15, Func.isNotNullEmptyBlank(dto.getOriginalProcess()) ? Integer.valueOf(dto.getOriginalProcess()) : null);
            setNull(ps, 16, Func.isNotNullEmptyBlank(dto.getLastProcessType()) ? Integer.valueOf(dto.getLastProcessType()) : null);

            // 5. Estado Inicial
            ps.setString(17, dto.getStatus());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == PreparedStatement.EXECUTE_FAILED || affectedRows != 1) {
                throw new CorruptInsertionException();
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                generated_id = rs.getInt(1);

                // Enriquecimiento del DTO post-operación bajo el flujo JBlue
                dto.put("id", String.valueOf(generated_id));
                String now = Formats.getLocalDateTime(LocalDateTime.now());
                dto.put("date_update", now);
                dto.put("date_register", now);
            }
        }
        return generated_id;
    }

    /**
     * Recupera una vinculación específica por su ID empleando metadatos
     * dinámicos.
     *
     * * @param connection Conexión activa.
     * @param id Llave primaria del registro.
     * @return Un Optional conteniendo el WaterIntakeUserDTO mapeado, o vacío.
     * @throws SQLException Si ocurre un error en la consulta.
     */
    public Optional<WaterIntakeUserDTO> findById(JDBConnection connection, int id) throws SQLException {
        Optional<WaterIntakeUserDTO> result = Optional.empty();
        String query = "SELECT * FROM wki_user WHERE id = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ResultSetMetaData md = rs.getMetaData();
                    int columns = md.getColumnCount();
                    WaterIntakeUserDTO dto = new WaterIntakeUserDTO();

                    for (int i = 1; i <= columns; i++) {
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
     * Verifica la existencia previa de una vinculación activa basándose en los
     * criterios clave del padrón. Utiliza comparaciones NULL-safe para la
     * descripción.
     */
    public boolean exists(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException {
        boolean isPresent = false;
        // CORREGIDO: Nombre de tabla unificado y sintaxis de paréntesis rectificada
        String query = """
                       SELECT id, status, office_id FROM wki_user 
                       WHERE water_intake_id = ? 
                         AND user_id = ? 
                         AND address_id = ? 
                         AND user_name = ? 
                         AND description <=> ? 
                       LIMIT 1
                       """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            // 1. Flujo JBlue: Casteo manual numérico seguro
            ps.setInt(1, Integer.parseInt(dto.getWaterIntakeId()));
            ps.setInt(2, Integer.parseInt(dto.getUserId()));
            ps.setInt(3, Integer.parseInt(dto.getAddressId()));

            // 2. Criterios de texto
            ps.setString(4, dto.getUserName());
            ps.setString(5, dto.getDescription());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isPresent = true;
                }
            }
        }
        return isPresent;
    }

    /**
     * Procesa el cambio de propietario de la toma de agua.
     */
    public boolean updateOwnerChange(JDBConnection connection, WaterIntakeUserDTO dto, String old_user_id) throws SQLException {
        boolean res = false;
        // CORREGIDO: Se añadió la coma antes de status
        String query = """
                       UPDATE wki_user SET
                           user_id = ?,
                           user_type_id = ?,
                           user_name = ?,
                           description = ?,
                           observation = ?,
                           last_employee_update = ?,
                           last_process_type = ?,
                           status = '1'
                       WHERE id = ? AND user_id = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            // Flujo JBlue: Cast explícito a tipos numéricos requeridos por MySQL
            ps.setInt(1, Integer.parseInt(dto.getUserId()));
            ps.setInt(2, Integer.parseInt(dto.getUserTypeId()));
            ps.setString(3, dto.getUserName());
            setNull(ps, 4, dto.getDescription());
            setNull(ps, 5, dto.getObservation());
            ps.setInt(6, Integer.parseInt(dto.getLastEmployeeUpdate()));

            // Proceso opcional
            setNull(ps, 7, Func.isNotNullEmptyBlank(dto.getLastProcessType()) ? Integer.valueOf(dto.getLastProcessType()) : null);

            // Cláusula WHERE (CORREGIDO: Mapeo de índices secuenciales 8 y 9 sin colisiones)
            ps.setInt(8, Integer.parseInt(dto.getId()));
            ps.setInt(9, Integer.parseInt(old_user_id));

            if (ps.executeUpdate() == 1) {
                res = true;
            }
        }
        return res;
    }

    /**
     * METODO PARA ACTUALIZAR EL STATUS DESDE LAS RUTINAS DEL SISTEMA.
     */
    public boolean updateStatus(JDBConnection connection, WaterIntakeUserDTO dto, String new_status) throws SQLException {
        boolean res = false;
        // CORREGIDO: Se añadió la coma antes de status
        String query = """
                       UPDATE wki_user SET
                           description = ?,
                           observation = ?,
                           last_employee_update = ?,
                           last_process_type = ?,
                           status = ?
                       WHERE id = ? AND user_id = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            setNull(ps, 1, dto.getDescription());
            setNull(ps, 2, dto.getObservation());

            // Flujo JBlue: Cast manual de auditorías y llaves
            ps.setInt(3, Integer.parseInt(dto.getLastEmployeeUpdate()));
            setNull(ps, 4, Func.isNotNullEmptyBlank(dto.getLastProcessType()) ? Integer.valueOf(dto.getLastProcessType()) : null);

            // Status e identificadores
            ps.setString(5, new_status);
            ps.setInt(6, Integer.parseInt(dto.getId()));
            ps.setInt(7, Integer.parseInt(dto.getUserId()));

            if (ps.executeUpdate() == 1) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Realiza una actualización dinámica basada en deltas (Delta Update) sobre
     * los campos del padrón.
     *
     * * @param connection Conexión transaccional activa.
     * @param oldData Datos vigentes en la base de datos.
     * @param newData Datos modificados capturados en Swing.
     * @return true si se registraron cambios en MySQL, false si no hubo
     * alteración de datos.
     * @throws SQLException Si ocurre un fallo en las sentencias del driver.
     */
    public boolean update(JDBConnection connection, WaterIntakeUserDTO oldData, WaterIntakeUserDTO newData) throws SQLException {
        List<String> setColumns = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // Escaneo secuencial seguro de deltas de texto y auditorías
        autoSet(oldData.getUserName(), newData.getUserName(), "user_name", setColumns, parameters);
        autoSet(oldData.getDescription(), newData.getDescription(), "description", setColumns, parameters);
        autoSet(oldData.getObservation(), newData.getObservation(), "observation", setColumns, parameters);
        autoSet(oldData.getStatus(), newData.getStatus(), "status", setColumns, parameters);

        // Control dinámico de Llaves e Índices Numéricos modificados
        if (!java.util.Objects.equals(oldData.getAddressId(), newData.getAddressId())) {
            setColumns.add("address_id");
            parameters.add(Integer.valueOf(newData.getAddressId()));
        }
        if (!java.util.Objects.equals(oldData.getWaterIntakeTypeId(), newData.getWaterIntakeTypeId())) {
            setColumns.add("water_intake_type_id");
            parameters.add(Integer.valueOf(newData.getWaterIntakeTypeId()));
        }
        if (!java.util.Objects.equals(oldData.getUserTypeId(), newData.getUserTypeId())) {
            setColumns.add("user_type_id");
            parameters.add(Integer.valueOf(newData.getUserTypeId()));
        }

        // Control selectivo del Historial de Pagos y Recaudación (Casteos de tipos MySQL)
        if (!java.util.Objects.equals(oldData.getCurrentFiscalYear(), newData.getCurrentFiscalYear())) {
            setColumns.add("current_fiscal_year");
            parameters.add(Integer.valueOf(newData.getCurrentFiscalYear()));
        }
        if (!java.util.Objects.equals(oldData.getLastMonthPaid(), newData.getLastMonthPaid())) {
            setColumns.add("last_month_paid");
            parameters.add(Integer.valueOf(newData.getLastMonthPaid()));
        }
        if (!java.util.Objects.equals(oldData.getLastAmountPaid(), newData.getLastAmountPaid())) {
            setColumns.add("last_amount_paid");
            parameters.add(Double.valueOf(newData.getLastAmountPaid()));
        }

        // Si el formulario en Swing no alteró ningún JTextField, cancelamos el viaje a la BD
        if (setColumns.isEmpty()) {
            return false;
        }

        // Adición forzada del operador responsable y tipos de procesos mutados
        setColumns.add("last_employee_update");
        parameters.add(Integer.valueOf(newData.getLastEmployeeUpdate()));

        if (Func.isNotNullEmptyBlank(newData.getLastProcessType())) {
            setColumns.add("last_process_type");
            parameters.add(Integer.valueOf(newData.getLastProcessType()));
        }

        String setClause = String.join("=?, ", setColumns) + "=?";
        String query = "UPDATE wki_user SET " + setClause + " WHERE id = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            int index = 1;
            for (Object param : parameters) {
                if (param == null) {
                    ps.setNull(index, Types.VARCHAR);
                } else if (param instanceof Integer intVal) {
                    ps.setInt(index, intVal);
                } else if (param instanceof Double doubleVal) {
                    ps.setDouble(index, doubleVal);
                } else {
                    ps.setObject(index, param);
                }
                index++;
            }

            // Cierre posicional del parámetro del WHERE (ID de la vinculación)
            ps.setInt(index, Integer.parseInt(newData.getId()));
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * ACTUALIZA LA TOMA A STATUS "ELIMINADO"
     *
     * @param connection
     * @param dto
     * @return
     * @throws SQLException
     */
    public boolean updateStatusDelete(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException {
        return updateStatus(connection, dto, "3");
    }

    /**
     * ACTUALIZA LA TOMA A STATUS "REGISTRADO" EN CASO DE QUE NO SE HAYA
     * REGISTRADO EL PAGO
     *
     * @param connection
     * @param dto
     * @return
     * @throws SQLException
     */
    public boolean updateStatusRegister(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException {
        return updateStatus(connection, dto, "21");
    }

    /**
     * ACTUALIZA LA TOMA A STATUS "RECONECTADO" EN CASO DE HABER ESTADO CON
     * STATUS "DESCONECTADO"
     *
     * @param connection
     * @param dto
     * @return
     * @throws SQLException
     */
    public boolean updateStatusReconected(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException {
        return updateStatus(connection, dto, "22");
    }

    /**
     * ACTUALIZA LA TOMA A STATUS "DESCONECTADO" EN CASO DE NO CUMPLIR LOS PAGOS
     *
     * @param connection
     * @param dto
     * @return
     * @throws SQLException
     */
    public boolean updateStatusDesconected(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException {
        return updateStatus(connection, dto, "23");
    }

    private <T> void autoSet(T oldValue, T newValue, String columnName, List<String> setColumns, List<Object> parameters) {
        if (!java.util.Objects.equals(oldValue, newValue)) {
            setColumns.add(columnName);
            parameters.add(newValue);
        }
    }
}
