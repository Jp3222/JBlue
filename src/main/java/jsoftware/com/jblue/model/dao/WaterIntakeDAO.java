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

    public int insert(JDBConnection connection, WaterIntakesDTO dto) throws SQLException {
        String query = """
               INSERT INTO wki_water_intakes 
               (cost_procedure, water_intake_type, user, user_name, street1, street2, location, description, status, last_employee_update)
               VALUES (?,?,?,?,?,?,?,?,?,?)
               """;

        // Nota: Eliminamos el manejo de transacciones de aquí para que el Service lo controle
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // 1. DECIMAL -> BigDecimal
            if (JFunc.isNull(dto.getConstProcedure())) {
                ps.setNull(1, Types.DECIMAL);
            } else {
                ps.setBigDecimal(1, new BigDecimal(dto.getConstProcedure()));
            }

            // 2. INT -> setInt
            ps.setInt(2, Integer.parseInt(dto.getWaterIntakeType()));
            ps.setInt(3, Integer.parseInt(dto.getUser()));
            ps.setString(4, dto.getUserName());

            // 3. Calles (IDs de cat_street)
            ps.setInt(5, Integer.parseInt(dto.getStreet1()));

            if (JFunc.isNull(dto.getStreet2())) {
                ps.setNull(6, Types.INTEGER);
            } else {
                ps.setInt(6, Integer.parseInt(dto.getStreet2()));
            }

            // 4. Textos
            ps.setString(7, dto.getLocation());
            ps.setString(8, dto.getDescription());

            // 5. Status e ID de Empleado (Ambos INT)
            ps.setInt(9, Integer.parseInt(dto.getStatusString())); // Asegúrate que devuelva el ID

            if (JFunc.isNull(dto.getLastEmployeeUpdate())) {
                ps.setNull(10, Types.INTEGER);
            } else {
                ps.setInt(10, Integer.parseInt(dto.getLastEmployeeUpdate()));
            }

            if (ps.executeUpdate() > 0) {
                try (ResultSet gk = ps.getGeneratedKeys()) {
                    if (gk.next()) {
                        return gk.getInt(1);
                    }
                }
            }
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
