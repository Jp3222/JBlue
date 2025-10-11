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
package jsoftware.com.jblue.model.daos;

import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.dbcon.connection.JDBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juanp
 */
public class HysHistoryDAO {

    private static final HysHistoryDAO INSTANCE = new HysHistoryDAO();

    public static HysHistoryDAO getINSTANCE() {
        return INSTANCE;
    }

    private final JDBConnection connection;
    private final String query;
    //
    private final HysEmployeeMovs INSTANCE2;
    private final HysUsersMovs INSTANCE3;

    public HysHistoryDAO() {
        this.INSTANCE3 = new HysUsersMovs();
        this.INSTANCE2 = new HysEmployeeMovs();
        this.query = "INSERT INTO hys_program_history(employee, db_user, affected_table, type_mov, description) VALUES(?,?,?,?,?)";
        connection = JDBConnection.getInstance();
    }

    public boolean insert(int affected_table, String description) throws SQLException {
        return save(affected_table, _Const.INDEX_INSERT, description);
    }

    public boolean update(int affected_table, String description) throws SQLException {
        return save(affected_table, _Const.INDEX_UPDATE, description);
    }

    public boolean delete(int affected_table, String description) throws SQLException {
        return save(affected_table, _Const.INDEX_LOGIC_DELETE, description);
    }

    public boolean save(int affected_table, int type_mov, String description) throws SQLException {
        OEmployee employee = SystemSession.getInstancia().getCurrentEmployee();
        return save(employee, affected_table, type_mov, description);
    }

    protected boolean save(OEmployee employee, int affected_table, int type_mov, String description) throws SQLException {
        boolean rt = false;
        try (PreparedStatement ps = connection.getConnection().prepareStatement(query)) {
            ps.setString(1, employee.getId());
            ps.setString(2, currentUser());
            ps.setInt(3, affected_table);
            ps.setInt(4, type_mov);
            ps.setString(5, description);
            rt = ps.executeUpdate() > 0;
        }
        return rt;
    }

    public String currentUser() {
        String q = "SELECT CURRENT_USER";
        String curus = null;
        try (ResultSet rs = connection.query(q);) {
            if (rs.next()) {
                curus = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.getLogger(HysHistoryDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return curus;
    }

    public HysEmployeeMovs getHysEmployeeMovs() {
        return INSTANCE2;
    }

    public HysUsersMovs getHysUsersMovs() {
        return INSTANCE3;
    }

    public class HysEmployeeMovs {

        public boolean insertToEmployee(String description) throws SQLException {
            return insert(_Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean updateToEmployee(String description) throws SQLException {
            return insert(_Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean deleteToEmployee(String description) throws SQLException {
            return insert(_Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean saveLogin(OEmployee employee, String description) throws SQLException {
            return save(employee, _Const.INDEX_HYS_PROGRAM_HISTORY, _Const.INDEX_LOGIN, description);
        }

        public boolean saveLogOut(OEmployee employee, String description) throws SQLException {
            return save(employee, _Const.INDEX_HYS_PROGRAM_HISTORY, _Const.INDEX_LOGOUT, description);
        }

    }

    public class HysUsersMovs {

        public boolean insertToUsers(String description) throws SQLException {
            return insert(_Const.INDEX_USR_USERS, description);
        }

        public boolean updateToUsers(String description) throws SQLException {
            return insert(_Const.INDEX_USR_USERS, description);
        }

        public boolean deleteToUsers(String description) throws SQLException {
            return insert(_Const.INDEX_USR_USERS, description);
        }

    }
}
