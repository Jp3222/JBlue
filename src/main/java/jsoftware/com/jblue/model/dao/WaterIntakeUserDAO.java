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

    public int insert(JDBConnection connection, WaterIntakeUserDTO wki_user) throws SQLException {
        int user_id = -1;
        String query = WaterIntakeUserQuery.INSERT_NEW_OWNER;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, wki_user.getUserId());
            ps.setString(2, wki_user.getWaterIntakeId());
            ps.setString(3, wki_user.getDescription());
            ps.setString(4, wki_user.getNotes());
            ps.setString(5, wki_user.getEmployeRegister());
            ps.setString(6, wki_user.getLastUpdateEmployee());
            ps.setString(7, wki_user.getOriginalProcessId());
            ps.setString(8, wki_user.getLastUpdateEmployee());
            ps.setInt(9, wki_user.getStatus());
            int affected_row = ps.executeUpdate();
            if (affected_row == PreparedStatement.RETURN_GENERATED_KEYS || affected_row != 1) {
                throw new SQLException("INSERCCION DE USUARIO AL PADRON DE TOMAS DE AGUA POTABLE ERRONEO");
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("ERROR AL OBTENER LLAVE PRIMARIA");
                }
                user_id = rs.getInt(1);
                wki_user.getMap().put("id", user_id);
                wki_user.getMap().put("date_update", Formats.getLocalDate(LocalDateTime.now()));
                wki_user.getMap().put("date_register", Formats.getLocalDate(LocalDateTime.now()));
                wki_user.getMap().put("id", user_id);
            }
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
        return user_id;
    }
}
