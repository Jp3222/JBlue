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
import jsoftware.com.jblue.model.dto.WaterIntakesDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class WaterIntakeDAO extends AbstractDAO implements ListComponentDAO<WaterIntakesDTO> {

    public WaterIntakeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public int insert(JDBConnection connection, WaterIntakesDTO dto) {
        String query = """
                   INSERT INTO wki_water_intakes 
                   (cost_procedure, water_intake_type, user, user_name, street1, street2, location, description, status, last_employee_update)
                   VALUES
                   (?,?,?,?,?,?,?,?,?,?)
                   """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            // 1. Iniciar transacción
            connection.setAutoCommit(false);

            ps.setString(1, dto.getConstProcedure());
            ps.setString(2, dto.getWaterIntakeType());
            ps.setString(3, dto.getUser());
            ps.setString(4, dto.getUserName());
            ps.setString(5, dto.getStreet1());
            ps.setString(6, dto.getStreet2());
            ps.setString(7, dto.getLocation());
            ps.setString(8, dto.getDescription());
            ps.setString(9, dto.getStatusString());
            ps.setString(10, dto.getLastEmployeeUpdate());

            // 2. Usar executeUpdate() y validar que afectó al menos 1 fila
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("No se insertó ningún registro, inserción fallida.");
            }

            // 3. Recuperar el ID generado
            try (ResultSet gk = ps.getGeneratedKeys()) {
                if (gk.next()) {
                    int generatedId = gk.getInt(1);

                    // 4. SI TODO SALIÓ BIEN, HACEMOS COMMIT AQUÍ
                    connection.commit();
                    return generatedId;
                } else {
                    throw new SQLException("Inserción exitosa pero no se pudo recuperar el ID.");
                }
            }

        } catch (SQLException e) {
            // 5. Solo hacemos rollback si hubo un error
            connection.rollBack();
            e.printStackTrace();
        } finally {
            // 6. Siempre regresamos al estado original
            connection.setAutoCommit(true);
        }
        return -1;
    }

    @Override
    public List<WaterIntakesDTO> getList() {
        List<WaterIntakesDTO> list = new ArrayList<>(15);
        String query = "SELECT * FROM wki_water_intakes WHERE status = 1";
        try (JDBConnection c = ConnectionFactory.getIntance().getCacheConnection(); PreparedStatement ps = c.getNewPreparedStatement(query)) {
            try (ResultSet rs = ps.executeQuery();) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                while (rs.next()) {
                    WaterIntakesDTO o = new WaterIntakesDTO();
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

}
