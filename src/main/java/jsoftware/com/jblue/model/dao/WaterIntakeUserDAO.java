/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import jsoftware.com.jblue.model.dto.WaterIntakeUserDTO;
import jsoftware.com.jblue.model.exp.DataAccesObjectException;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jblue.model.querys.WaterIntakeUserQuery;
import jsoftware.com.jblue.util.Formats;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class WaterIntakeUserDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    public WaterIntakeUserDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public int insert(JDBConnection connection, WaterIntakeUserDTO dto) throws SQLException, DataAccesObjectException {
        int user_id = -1;
        boolean res = false;
        String query = WaterIntakeUserQuery.INSERT_NEW_OWNER;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getUserId());
            ps.setString(2, dto.getWaterIntakeId());
            ps.setString(3, dto.getWaterIntakeTypeId());
            ps.setString(4, dto.getDescription());
            ps.setString(5, dto.getNotes());
            ps.setString(6, dto.getEmployeeRegister());
            ps.setString(7, dto.getLastEmployeeUpdate());
            ps.setString(8, dto.getOriginalProcessId());
            ps.setString(9, dto.getLastProcessType());
            ps.setInt(10, dto.getStatus());
            res = ps.executeUpdate() == 1;
            if (!res) {
                throw new CorruptInsertionException();
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                user_id = rs.getInt(1);
                dto.getMap().put("id", user_id);
                dto.getMap().put("date_update", Formats.getLocalDate(LocalDateTime.now()));
                dto.getMap().put("date_register", Formats.getLocalDate(LocalDateTime.now()));
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (DataAccesObjectException ex) {
            throw ex;
        }
        return user_id;
    }
}
