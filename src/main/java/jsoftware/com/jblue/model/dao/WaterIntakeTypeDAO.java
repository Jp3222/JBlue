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
import jsoftware.com.jblue.model.dto.WaterIntakeTypeDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juanp
 */
public class WaterIntakeTypeDAO extends AbstractDAO implements ListComponentDAO<WaterIntakeTypeDTO>, TableComponentDAO<WaterIntakeTypeDTO> {

    private static final long serialVersionUID = 1L;

    public WaterIntakeTypeDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public int insert(JDBConnection connection, WaterIntakeTypeDTO dto) throws CorruptInsertionException, KeyNotGenerateException, SQLException {
        int id = -1;
        String query = """
                                        INSERT INTO wki_water_intake_type (
                                        type_name, current_price, previous_price, surcharge,
                                       charge_unit, unit_id, units, apply_rules, round, round_up,
                                       base_price, surcharge_percentage, annual_increase, status,
                                       last_employee_update
                                       )
                                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getTypeName());
            ps.setString(2, dto.getCurrentPrice());
            ps.setString(3, dto.getPreviousPrice());
            ps.setString(4, dto.getSurcharge());
            ps.setBoolean(5, dto.getChargeUnit());
            ps.setString(6, dto.getUnitId());
            ps.setString(7, dto.getUnits());
            ps.setBoolean(8, dto.getApplyRules());
            ps.setBoolean(9, dto.getRound());
            ps.setBoolean(10, dto.getRoundUp());
            ps.setBigDecimal(11, dto.getBasePrice());
            ps.setBigDecimal(12, dto.getSurchargePercentage());
            ps.setBigDecimal(13, dto.getAnnualIncrease());
            ps.setInt(14, dto.getStatus());
            ps.setString(15, dto.getLastEmployeeUpdate());
            boolean res = ps.executeUpdate() > 0;
            if (!res) {
                throw new CorruptInsertionException();
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                id = rs.getInt(1);
                dto.put("id", id);
            }
        }
        return id;
    }

    public void update(JDBConnection connection, WaterIntakeTypeDTO dto) {
    }

    public void delete(JDBConnection connection, WaterIntakeTypeDTO dto) {
    }

    @Override
    public List<WaterIntakeTypeDTO> getList(JDBConnection connection) throws SQLException, Exception {
        List<WaterIntakeTypeDTO> list = new ArrayList<>(15);
        String query = "SELECT * FROM wki_water_intake_type WHERE status = 1";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            try (ResultSet rs = ps.executeQuery();) {
                ResultSetMetaData md = rs.getMetaData();
                int size = md.getColumnCount();
                String[] fields = new String[size];
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = md.getColumnLabel(i + 1);
                }
                WaterIntakeTypeDTO o;
                while (rs.next()) {
                    o = new WaterIntakeTypeDTO();
                    for (String i : fields) {
                        o.getMap().put(i, rs.getString(i));
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
    public List<WaterIntakeTypeDTO> getList(JDBConnection connection, JTableModel model) {
        return null;
    }

    @Override
    public JTableModel buildModel(JDBConnection connection, JTableModel model) {
        return null;
    }

}
