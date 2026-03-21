/*
 * Copyright (C) 2025 juanp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jsoftware.com.jblue.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsoftware.com.jblue.model.dto.SessionDTO;
import jsoftware.com.jblue.model.exp.imp.CorruptInsertionException;
import jsoftware.com.jblue.model.exp.imp.CorruptUpdateException;
import jsoftware.com.jblue.model.exp.imp.KeyNotGenerateException;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 * Clase DAO (Data Access Object) responsable de gestionar la lógica de sesión y
 * consultar datos relacionados con el estado de la aplicación, como la vigencia
 * de sesiones y la administración activa.
 *
 * @author Juan Campos
 * @version 1.0
 * @since 14/03/2026
 */
public class SessionDAO extends AbstractDAO {

    public SessionDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
    }

    public int insert(JDBConnection connection, SessionDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException {
        int id = INSERT_DEFAULT_RETURN;
        boolean res = false;
        String query = "INSERT INTO hys_session(employee_id, history_start_id, status) VALUES(?, ?, ?)";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getEmployeeId());
            ps.setString(2, dto.getHistoryStartId());
            ps.setString(3, "1");
            res = ps.executeUpdate() == 1;
            if (!res) {
                throw new CorruptInsertionException();
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new KeyNotGenerateException();
                }
                id = rs.getInt(1);
                dto.put("id", String.valueOf(id));
            }
        } catch (SQLException | CorruptInsertionException | KeyNotGenerateException e) {
            throw e;
        }
        return id;
    }

    public boolean updateStatus(JDBConnection connection, SessionDTO dto) throws SQLException, CorruptInsertionException, KeyNotGenerateException, CorruptUpdateException {
        boolean res = UPDATE_DEFAULT_RETURN;
        String query = """
                       UPDATE hys_session SET
                            status = 4,
                            history_end_id = ?,
                            date_out = CURRENT_TIMESTAMP
                       WHERE employee_id = ? AND status = 1
                       """;
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, dto.getHistoryEndId());
            ps.setString(2, dto.getEmployeeId());
            res = ps.executeUpdate() > 0;
            if (!res) {
                throw new CorruptUpdateException();
            }
        } catch (SQLException | CorruptUpdateException e) {
            throw e;
        }
        return res;
    }

    public boolean haveActiveSession(JDBConnection connection, String employee_id) throws Exception {
        boolean res = false;
        String query = "SELECT id FROM hys_session WHERE employee_id = ? AND status = 1";
        try (PreparedStatement ps = connection.getNewPreparedStatement(query)) {
            ps.setString(1, employee_id);
            try (ResultSet rs = ps.executeQuery()) {
                res = rs.next();
            }
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
}
