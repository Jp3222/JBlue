package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jblue.util.Func;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Data Access Object (DAO) para la gestión del catálogo de calles.
 * <br>
 * Implementa el mapeo dinámico por deltas y el control estricto de tipos de
 * datos.
 *
 * @author JUAN PABLO CAMPOS CASASANERO
 * @since 2026-05-31
 * @version 1.1
 */
public class StreetDAO extends AbstractDAO implements ListComponentDAO<StreetDTO> {

    private static final long serialVersionUID = 1L;

    public StreetDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    /**
     * Recupera el catálogo general de calles activas en el sistema.
     */
    @Override
    public List<StreetDTO> getList(JDBConnection connection) throws SQLException {
        List<StreetDTO> list = new ArrayList<>(50);
        String query = "SELECT * FROM cat_street WHERE status = 1";

        try (PreparedStatement ps = connection.getNewPreparedStatement(query); ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();
            int size = md.getColumnCount();
            String[] fields = new String[size];

            for (int i = 0; i < size; i++) {
                fields[i] = md.getColumnLabel(i + 1);
            }

            while (rs.next()) {
                StreetDTO dto = new StreetDTO();
                for (String field : fields) {
                    dto.getMap().put(field, rs.getString(field));
                }
                list.add(dto);
            }
        }
        return list;
    }

    /**
     * Inserta una nueva calle en el catálogo.
     * <p>
     * Realiza el casteo final a tipos numéricos para las relaciones foráneas de
     * MySQL y enriquece el DTO tras completarse la operación.
     * </p>
     */
    public int insert(JDBConnection connection, StreetDTO dto) throws SQLException, DataAccesObjectException {
        int generated_id = -1;
        String query = "INSERT INTO cat_street (street_name, colony_id, status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getStreetName());
            ps.setString(2, dto.getColonyId());
            ps.setString(3, dto.getStatus());

            if (ps.executeUpdate() != PreparedStatement.EXECUTE_FAILED) {
                throw new CorruptInsertionException();
            }
            try (ResultSet gk = ps.getGeneratedKeys()) {
                if (!gk.next()) {
                    throw new KeyNotGenerateException();
                }
                generated_id = gk.getInt(1);

                // Enriquecimiento del DTO alineado a las reglas de JBlue
                dto.put("id", String.valueOf(generated_id));
                String now = Formats.getLocalDateTime(LocalDateTime.now());
                dto.put("date_update", now);
                dto.put("date_register", now);
            }
        }
        return generated_id;
    }

    /**
     * Modifica selectivamente los campos modificados en la vista Swing mediante
     * mapeo por deltas.
     */
    public boolean update(JDBConnection connection, StreetDTO old_dto, StreetDTO new_dto) throws SQLException {
        boolean res = false;
        Map<String, Object> diff_map = Func.getChangedStringEntries(old_dto.getMap(), new_dto.getMap());

        if (diff_map.isEmpty()) {
            return true;
        }

        String query = getQueryUpdate(diff_map);

        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            int i = 1;
            for (Map.Entry<String, Object> entry : diff_map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().toString();

                // Control dinámico de tipos para evitar inyecciones corruptas de strings en llaves numéricas
                if ("colony_id".equalsIgnoreCase(key)) {
                    ps.setInt(i, Integer.parseInt(value));
                } else {
                    ps.setString(i, value);
                }
                i++;
            }

            // Inyectar el ID correspondiente al WHERE
            ps.setInt(i, Integer.parseInt(new_dto.getId()));

            res = ps.executeUpdate() > 0;
            if (!res) {
                throw new SQLException("MODIFICACIÓN CORRUPTA: El registro no existe o su estatus fue alterado.");
            }
        }
        return res;
    }

    /**
     * Construye dinámicamente la estructura SQL del update basada en los deltas
     * detectados.
     */
    public String getQueryUpdate(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("El mapa de deltas no contiene elementos para actualizar.");
        }

        StringBuilder sb = new StringBuilder(150);
        sb.append("UPDATE cat_street SET ");

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            // Filtrar llaves estructurales inmutables desde este método
            if (!"id".equalsIgnoreCase(key) && !"status".equalsIgnoreCase(key) && !"date_register".equalsIgnoreCase(key)) {
                sb.append(key).append(" = ?,");
            }
        }

        if (sb.toString().endsWith("SET ")) {
            throw new IllegalArgumentException("El mapa solo contiene campos estructurales excluidos.");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id = ? AND status = 1");

        return sb.toString();
    }

    /**
     * Realiza la baja lógica de la calle modificando su estado a inhabilitado.
     */
    public boolean delete(JDBConnection connection, StreetDTO dto) throws SQLException {
        String query = "UPDATE cat_street SET status = 3, date_end = CURRENT_TIMESTAMP WHERE id = ? AND status = 1";
        boolean res = false;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, Integer.parseInt(dto.getId()));

            int update_rows = ps.executeUpdate();
            res = update_rows != PreparedStatement.EXECUTE_FAILED && update_rows == 1;

            if (!res) {
                throw new SQLException("ELIMINACIÓN LÓGICA CORRUPTA: La calle con ID " + dto.getId() + " ya está inactiva o fue eliminada previamente.");
            }
        }
        return res;
    }
}
