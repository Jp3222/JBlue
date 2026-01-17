/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jsoftware.com.jblue.model.dto.WaterIntakeTypesDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public class WaterIntakeTypeDAO extends AbstractDAO implements ListComponentDAO<WaterIntakeTypesDTO>, TableComponentDAO<WaterIntakeTypesDTO> {

    private static final String TABLE = "wki_water_intake_type";
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE;
    private static final String SELECT_BY_ID = SELECT_ALL + " WHERE id = ?";

    // Columnas clave para inserción (omitiendo ID y fechas auto-generadas)
    private static final String INSERT_COLS = "type_name, current_price, previous_price, surcharge, status";
    private static final String INSERT_SQL = String.format("INSERT INTO %s (%s) VALUES (?, ?, ?, ?, ?)", TABLE, INSERT_COLS);

    // Estado de borrado lógico
    private static final int LOGICAL_DELETE_STATUS = 3;
    private static final long serialVersionUID = 1L;

    public WaterIntakeTypeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    // --- Auxiliar: Mapeo de ResultSet a Map-based DTO ---
    private WaterIntakeTypesDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        WaterIntakeTypesDTO dto = new WaterIntakeTypesDTO();
        // Mapeo dinámico de todas las columnas (similar a getPaymentList)
        java.sql.ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnLabel(i);
            Object value = rs.getObject(i);
            dto.put(columnName, value); // Asumiendo que 'put' es accesible
        }
        return dto;
    }

    // -----------------------------------------------------------------
    // CRUD: get, getList, insert, updateDynamic, logicalDelete
    // -----------------------------------------------------------------
    /**
     * 1. Obtener por ID
     */
    public WaterIntakeTypesDTO get(JDBConnection connection, int id) throws SQLException {
        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDTO(rs);
                }
            }
        }
        return null;
    }

    /**
     * 2. Obtener lista de todos los registros
     */
    public List<WaterIntakeTypesDTO> getList(JDBConnection connection) throws SQLException {
        List<WaterIntakeTypesDTO> list = new ArrayList<>();
        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToDTO(rs));
            }
        }
        return list;
    }

    /**
     * 3. Insertar nuevo registro
     */
    public int insert(JDBConnection connection, WaterIntakeTypesDTO dto) {
        int res = -1;
        try (PreparedStatement ps = connection.getNewPreparedStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            // 1. Asignación de parámetros (índices fijos según INSERT_COLS)
            ps.setString(1, dto.getTypeName());
            ps.setString(2, dto.getCurrentPrice());
            ps.setString(3, dto.getPreviousPrice());
            ps.setString(4, dto.getSurcharge());
            ps.setInt(5, dto.getStatus());
            res = ps.executeUpdate();
            if (res <= 0) {
                throw new SQLException("REGISTRO ERRONEO");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("LLAVE GENERADA CORRUPTA");
                }
                res = rs.getInt(1);
            }
        } catch (SQLException e) {
            connection.rollBack();
        } catch (Exception e) {
            connection.rollBack();
        } finally {
            connection.setAutoCommit(true);
        }
        return res;
    }

    /**
     * 4. Borrado Lógico (Actualizar status a 3)
     */
    public boolean logicalDelete(JDBConnection connection, int id) throws SQLException {
        String query = String.format("UPDATE %s SET status = ?, date_end = CURRENT_TIMESTAMP WHERE id = ?", TABLE);
        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, LOGICAL_DELETE_STATUS);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    /**
     * 5. Actualización Dinámica
     */
    public boolean updateDynamic(JDBConnection connection, WaterIntakeTypesDTO old_wki, WaterIntakeTypesDTO new_wki) throws SQLException {

        Map<String, Object> oldMap = old_wki.getMap();
        Map<String, Object> newMap = new_wki.getMap();

        List<String> setColumns = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // Columnas actualizables (excluyendo ID y fechas auto-generadas)
        String[] updatableColumns = {"type_name", "current_price", "previous_price", "surcharge", "status"};

        // Comparar campo por campo
        for (String col : updatableColumns) {
            Object oldValue = oldMap.get(col);
            Object newValue = newMap.get(col);

            // Si el valor ha cambiado o si uno es nulo y el otro no (requiere actualización)
            if (!Objects.equals(oldValue, newValue)) {
                setColumns.add(col);
                parameters.add(newValue);
            }
        }

        // Si no hay campos para actualizar, retornar false.
        if (setColumns.isEmpty()) {
            return false;
        }

        // Construir la sentencia SQL dinámica: UPDATE table SET col1=?, col2=? WHERE id=?
        String setClause = String.join("=?, ", setColumns) + "=?";
        String dynamicUpdateSQL = String.format("UPDATE %s SET %s WHERE id=?", TABLE, setClause);

        try (Connection conn = connection.getConnection(); PreparedStatement ps = conn.prepareStatement(dynamicUpdateSQL)) {

            int index = 1;
            // Asignar parámetros dinámicos
            for (Object param : parameters) {
                if (param == null) {
                    // Usar lógica de mapeo JDBC para nulos basada en el tipo esperado
                    if (setColumns.get(index - 1).contains("price") || setColumns.get(index - 1).equals("surcharge")) {
                        ps.setNull(index, Types.DECIMAL);
                    } else if (setColumns.get(index - 1).equals("status")) {
                        ps.setNull(index, Types.INTEGER);
                    } else {
                        ps.setNull(index, Types.VARCHAR);
                    }
                } else if (param instanceof BigDecimal) {
                    ps.setBigDecimal(index, (BigDecimal) param);
                } else if (param instanceof Integer) {
                    ps.setInt(index, (Integer) param);
                } else {
                    ps.setObject(index, param);
                }
                index++;
            }

            // Asignar el parámetro WHERE (ID)
            ps.setInt(index, Integer.parseInt(new_wki.getId()));

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<WaterIntakeTypesDTO> getList() {
        List<WaterIntakeTypesDTO> list = new ArrayList<>(15);
        String query = "SELECT * FROM wki_water_intake_type WHERE status = 1";
        try (JDBConnection c = ConnectionFactory.getIntance().getCacheConnection(); PreparedStatement ps = c.getNewCallableStatement(query)) {
            try (ResultSet rs = ps.executeQuery();) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                while (rs.next()) {
                    WaterIntakeTypesDTO o = new WaterIntakeTypesDTO();
                    for (int i = 1; i <= size; i++) {
                        String key = md.getColumnLabel(i);
                        o.put(key, rs.getString(key));
                    }
                    list.add(o);
                }
            }
        } catch (Exception e) {
            System.getLogger(StreetDAO.class.getName()).log(System.Logger.Level.ALL, e.getMessage());
        }
        return list;
    }

    @Override
    public List<WaterIntakeTypesDTO> getList(JDBConnection connection, JTableModel model) {
        List<WaterIntakeTypesDTO> list = new ArrayList<>(15);
        String query = "SELECT * FROM wki_water_intake_type WHERE status = 1";
        try (JDBConnection c = ConnectionFactory.getIntance().getCacheConnection(); PreparedStatement ps = c.getNewCallableStatement(query)) {
            try (ResultSet rs = ps.executeQuery();) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                while (rs.next()) {
                    WaterIntakeTypesDTO o = new WaterIntakeTypesDTO();
                    for (int i = 1; i <= size; i++) {
                        String key = md.getColumnLabel(i);
                        o.put(key, rs.getString(key));
                    }
                    list.add(o);
                }
            }
        } catch (Exception e) {
            System.getLogger(StreetDAO.class.getName()).log(System.Logger.Level.ALL, e.getMessage());
        }
        return list;
    }

    public boolean delete(JDBConnection connection, WaterIntakeTypesDTO o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean update(JDBConnection connection, WaterIntakeTypesDTO old_dto, WaterIntakeTypesDTO new_dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
