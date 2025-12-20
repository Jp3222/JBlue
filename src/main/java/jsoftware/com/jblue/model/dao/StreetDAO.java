/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class StreetDAO extends AbstractDAO implements ListComponentDAO<StreetDTO> {

    public StreetDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    @Override
    public List<StreetDTO> getList() {
        List<StreetDTO> list = new ArrayList<>(15);
        String query = "SELECT * FROM cat_street WHERE status = 1";
        try (JDBConnection c = ConnectionFactory.getIntance().getCacheConnection(); PreparedStatement ps = c.getNewPreparedStatement(query)) {
            try (ResultSet rs = ps.executeQuery();) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                while (rs.next()) {
                    StreetDTO o = new StreetDTO();
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

    public int insert(JDBConnection connection, StreetDTO o) {
        String query = "INSERT INTO cat_street(street_name) VALUES(?)";
        int key = -1;
        // El try-with-resources es correcto
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);) {

            // **CORRECCIÓN 1: Establecer el parámetro del DTO**
            ps.setString(1, o.getStreetName());

            // **CORRECCIÓN 2: Usar executeUpdate (o simplemente ejecutar)**
            int res = ps.executeUpdate(); // Ejecuta la inserción
            if (res == PreparedStatement.EXECUTE_FAILED) {
                throw new SQLException("REGISTRO ERRONEO");
            }
            // **CORRECCIÓN 3: Manejo correcto de Generated Keys**
            try (ResultSet gk = ps.getGeneratedKeys();) {
                if (!gk.next()) { // Es OBLIGATORIO llamar a .next()
                    throw new SQLException("LLAVE NO GENERADA");
                }
                key = gk.getInt(1); // El ID generado está en el índice 1
            }
        } catch (SQLException ex) {
            System.getLogger(StreetDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return key;
    }

    public boolean update(JDBConnection connection, StreetDTO old_dto, StreetDTO new_dto) {
        boolean res = false;
        Map<String, Object> diff_map = Func.getChangedStringEntries(old_dto.getMap(), new_dto.getMap());
        if (diff_map.isEmpty()) {
            return true;
        }
        String query = getQueryUpdate(diff_map);
        // El try-with-resources es correcto
        try (PreparedStatement ps = connection.getNewPreparedStatement(query);) {
            int i = 1;
            if (!diff_map.isEmpty()) {
                for (Map.Entry<String, Object> entry : diff_map.entrySet()) {
                    ps.setString(i, entry.getValue().toString());
                    i++;
                }
            }
            ps.setString(i, new_dto.getId());
            res = ps.executeUpdate() > 0;
            if (!res) {
                throw new SQLException("ELIMINACION LOGICA CORRUPTA");
            }
        } catch (SQLException ex) {
            System.getLogger(StreetDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return res;
    }

    public String getQueryUpdate(Map<String, Object> map) {
        // 1. Validar que hay campos para actualizar
        if (map == null || map.isEmpty()) {
            // En un DAO, lanzar una excepción o retornar null/cadena vacía es mejor que generar un SQL inválido.
            throw new IllegalArgumentException("El mapa de campos a actualizar no puede ser nulo o vacío.");
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append("UPDATE cat_street SET ");
        // 2. Iterar solo sobre las claves que NO deben estar en el SET (ej: id)
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();

            // Evitar que la clave primaria (ID) o el estado se incluyan accidentalmente en el SET
            if (!"id".equalsIgnoreCase(key) && !"status".equalsIgnoreCase(key)) {
                sb.append(key).append(" = ?,");
            }
        }
        // 3. Manejo de mapa con solo campos excluidos (e.g., solo pasó el ID)
        // Si la longitud de sb es la misma que la inicial ("UPDATE cat_street SET "), significa que no se agregaron campos.
        if (sb.toString().endsWith("SET ")) {
            throw new IllegalArgumentException("El mapa solo contiene campos excluidos de la actualización.");
        }
        // 4. CORRECCIÓN CRÍTICA: Eliminar la coma final (,) y añadir espacio
        // sb.length() - 1 apunta a la coma
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" ");
        // 5. Añadir la cláusula WHERE (asumiendo que 'id' es requerido)
        // La clave 'id' deberá ser proporcionada al PreparedStatement más tarde.
        sb.append("WHERE id = ? AND status = 1");
        return sb.toString();
    }
    
    // Asumiendo que StreetDTO.getId() devuelve un Long. Si devuelve String, ajustar el setX
    public boolean delete(JDBConnection connection, StreetDTO o) throws SQLException { // Propagar SQLException para un manejo superior
        // CORRECCIÓN 1: Error tipográfico en la función SQL
        String query = "UPDATE cat_street SET status = 3, date_end = CURRENT_TIMESTAMP WHERE id = ? AND status = 1";
        boolean res = false;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            // CORRECCIÓN 2: Usar el método setX() apropiado (ej. setLong, asumiendo que el ID es numérico)
            ps.setString(1, o.getId());

            int updatedRows = ps.executeUpdate();
            res = updatedRows > 0;

            if (!res) {
                // Un fallo en la actualización (updatedRows == 0) significa que el registro
                // con ese ID y status=1 no existe. Es una falla controlada (Optimistic Lock).

                // Mejor práctica: Lanzar una excepción específica de negocio (ej. EntityNotFoundException)
                // para que la capa superior sepa que el registro no existe, en lugar de un error de SQL.
                // Para mantener el tipo de excepción original:
                throw new SQLException("El registro (ID: " + o.getId() + ") no pudo ser eliminado. Probablemente ya está inactivo o no existe.", "23000", 404);
            }

        } catch (SQLException e) {
            // Mejor práctica: Registrar el error...
            System.getLogger(StreetDAO.class.getName()).log(System.Logger.Level.ERROR, "Fallo al realizar la eliminación lógica.", e);
            throw e;
        }

        return res;
    }
}
