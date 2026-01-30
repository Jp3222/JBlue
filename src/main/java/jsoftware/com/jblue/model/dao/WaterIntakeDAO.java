/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import jsoftware.com.jblue.model.dto.WaterIntakesDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.util.JFunc;

/**
 *
 * @author juanp
 */
public class WaterIntakeDAO extends AbstractDAO implements ListComponentDAO<WaterIntakesDTO> {

    public WaterIntakeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public int insert(JDBConnection connection, WaterIntakesDTO dto) throws Exception {
        int generated_id = -1;
        String query = """
                   INSERT INTO wki_water_intakes 
                       (cost_procedure, water_intake_type, user, user_name, 
                        street1, street2, location, description, 
                        status, last_employee_update)
                   VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                   """;

        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Manejo de tipos y nulos con lógica defensiva
            if (JFunc.isNull(dto.getConstProcedure())) {
                ps.setNull(1, Types.DECIMAL);
            } else {
                ps.setBigDecimal(1, new BigDecimal(dto.getConstProcedure()));
            }
            ps.setInt(2, Integer.parseInt(dto.getWaterIntakeType()));
            ps.setInt(3, Integer.parseInt(dto.getUser()));
            ps.setString(4, dto.getUserName());
            ps.setInt(5, Integer.parseInt(dto.getStreet1()));
            if (JFunc.isNull(dto.getStreet2())) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, Integer.parseInt(dto.getStreet2()));
            }
            ps.setString(7, dto.getLocation());
            ps.setString(8, dto.getDescription());
            ps.setInt(9, Integer.parseInt(dto.getStatusString()));
            if (JFunc.isNull(dto.getLastEmployeeUpdate())) {
                ps.setNull(10, Types.INTEGER);
            } else {
                ps.setInt(10, Integer.parseInt(dto.getLastEmployeeUpdate()));
            }
            // Validación estricta según tu estilo
            int rows_afected = ps.executeUpdate();
            if (rows_afected != 1) {
                throw new SQLException("LA INSERCIÓN FALLÓ, SE AFECTARON %s REGISTROS".formatted(rows_afected));
            }
            // Recuperación de la llave generada
            try (ResultSet gk = ps.getGeneratedKeys()) {
                if (gk.next()) {
                    generated_id = gk.getInt(1);
                } else {
                    throw new SQLException("ERROR AL RECUPERAR EL ID GENERADO");
                }
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return generated_id;
    }

    public boolean updateOwnerChage(JDBConnection connection, int original_user_id, WaterIntakesDTO dto) throws Exception {
        boolean res = false;
        String query = """
                       UPDATE wki_water_intakes SET 
                            cost_procedure = ?,
                            user = ?, 
                            user_name = ?,
                            last_employee_update = ?
                       WHERE 
                            user = ?
                            AND id = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, dto.getConstProcedure());
            ps.setString(2, dto.getUser());
            ps.setString(3, dto.getUserName());
            ps.setString(4, dto.getLastEmployeeUpdate());
            ps.setInt(5, original_user_id);
            ps.setString(6, dto.getId());
            int rows_afected = ps.executeUpdate();
            res = rows_afected == 1;
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

    public boolean updateStatus(JDBConnection connection, WaterIntakesDTO dto) throws Exception {
        boolean res = false;
        String query = """
                       UPDATE wki_water_intakes SET 
                            status = ?,
                            last_employee_update = ?
                       WHERE 
                            user = ?
                            AND id = ?
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setInt(1, dto.getStatus());
            ps.setString(2, dto.getLastEmployeeUpdate());
            ps.setString(3, dto.getUser());
            ps.setString(4, dto.getId());
            int rows_afected = ps.executeUpdate();
            res = rows_afected == 1;
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
    public List<WaterIntakesDTO> getList() throws SQLException, Exception {
        List<WaterIntakesDTO> list = new ArrayList<>(15);
        String query = """
                   SELECT * FROM wki_water_intakes 
                   WHERE status = 1
                   """;

        try (JDBConnection c = ConnectionFactory.getIntance().getCacheConnection(); PreparedStatement ps = c.getNewPreparedStatement(query)) {

            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                String[] fields = new String[size];

                for (int i = 0; i < fields.length; i++) {
                    fields[i] = md.getColumnLabel(i + 1);
                }

                while (rs.next()) {
                    WaterIntakesDTO o = new WaterIntakesDTO();
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
