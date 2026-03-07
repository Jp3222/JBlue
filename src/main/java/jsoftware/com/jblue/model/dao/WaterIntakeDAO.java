/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.model.dto.WaterIntakeDTO;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class WaterIntakeDAO extends AbstractDAO implements ListComponentDAO<WaterIntakeDTO> {

    public WaterIntakeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public int insert(JDBConnection connection, WaterIntakeDTO dto) throws SQLException {
        int generated_id = -1;
        boolean res = false;
        String query = """
                           INSERT INTO wki_water_intakes
                           (cost_procedure, last_process, water_intake_type, street1, street2, 
                           location, description, status, last_employee_update)
                           VALUES(?,?,?,?,?,?,?,?,?)
                           """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getCostProcedure());
            ps.setString(2, dto.getLastProcess());
            ps.setString(3, dto.getWaterIntakeType());
            ps.setString(4, dto.getStreet1());
            ps.setString(5, dto.getStreet2());
            ps.setString(6, dto.getLocation());
            ps.setString(7, dto.getDescription());
            ps.setInt(8, dto.getStatus());
            ps.setString(9, dto.getLastEmployeeUpdate());
            res = ps.executeUpdate() > 0;
            if (!res) {
                throw new SQLException("INSERT CORRUPTO");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("LLAVE NO GENERADA");
                }
                generated_id = rs.getInt(1);
                res = generated_id > 0;
                if (!res) {
                    throw new SQLException("LLAVE GENERADA CORRUPTA");
                }
                dto.getMap().put("id", String.valueOf(generated_id));
                dto.getMap().put("date_register", Formats.getLocalDate(LocalDateTime.now()));
                dto.getMap().put("date_update", Formats.getLocalDate(LocalDateTime.now()));
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return generated_id;
    }

    public boolean updateStatus(JDBConnection connection, WaterIntakeDTO dto) throws Exception {
        boolean res = false;
        String query = """
                       UPDATE wki_water_intakes SET 
                            status = ?,
                            last_employee_update = ?
                       WHERE 
                            id = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, dto.getStatus());
            ps.setString(2, dto.getLastEmployeeUpdate());
            ps.setString(3, dto.getId());
            int rows_afected = ps.executeUpdate();
            res = rows_afected > 0;
            if (!res) {
                throw new SQLException("LA ACTUALIZACION AFECTO A %s REGISTROS".formatted(rows_afected));
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    @Override
    public List<WaterIntakeDTO> getList(JDBConnection connection) throws SQLException, Exception {
        List<WaterIntakeDTO> list = new ArrayList<>(15);
        String query = """
                   SELECT * FROM wki_water_intakes 
                   WHERE status = 1
                   """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                String[] fields = new String[size];

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = md.getColumnLabel(i + 1);
                }

                while (rs.next()) {
                    WaterIntakeDTO o = new WaterIntakeDTO();
                    for (String field_name : fields) {
                        o.getMap().put(field_name, rs.getString(field_name));
                    }
                    list.add(o);
                }

                // Validación estricta: Si la lógica de negocio exige resultados
                if (list.isEmpty()) {
                    // Opcional: puedes lanzar una excepción si "no encontrar nada" es un error
                    // throw new SQLException("LA CONSULTA NO DEVOLVIÓ REGISTROS");
                }
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            // Mantenemos tu estructura de relanzar la excepción para control total
            throw e;
        }
        return list;
    }

}
