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

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dto.EmployeeDTO;
import jsoftware.com.jblue.model.factories.ConnectionFactory;
import jsoftware.com.jblue.sys.SystemSession;
import jsoftware.com.jutil.db.JDBConnection;
import jsoftware.com.jutil.model.AbstractDAO;

/**
 *
 * @author juanp
 */
public class HysHistoryDAO extends AbstractDAO {

    private static final long serialVersionUID = 1L;

    private static final HysHistoryDAO INSTANCE = new HysHistoryDAO(false, "HysHistoryDAO");

    public static HysHistoryDAO getINSTANCE(boolean flag_dev_log, String name_module) {
        return INSTANCE;
    }

    public static HysHistoryDAO getINSTANCE() {

        return INSTANCE;
    }

    private final String INSERT = "INSERT INTO hys_program_history(employee, db_user, affected_table, type_mov, description) VALUES(?,?,?,?,?)";

    private final HysEmployeeMovs INSTANCE2;
    private final HysUsersMovs INSTANCE3;

    public HysHistoryDAO(boolean flag_dev_log, String name_module) {
        super(flag_dev_log, name_module);
        this.INSTANCE3 = new HysUsersMovs();
        this.INSTANCE2 = new HysEmployeeMovs();
    }

    public boolean insert(int affected_table, String description) throws SQLException {
        return save(affected_table, Const.INDEX_INSERT, description);
    }

    public boolean update(int affected_table, String description) throws SQLException {
        return save(affected_table, Const.INDEX_UPDATE, description);
    }

    public boolean delete(int affected_table, String description) throws SQLException {
        return save(affected_table, Const.INDEX_LOGIC_DELETE, description);
    }

    public boolean select(int affected_table, String description) throws SQLException {
        return save(affected_table, Const.INDEX_SELECT, description);
    }

    public boolean save(int affected_table, int type_mov, String description) throws SQLException {
        boolean res;
        try (JDBConnection connection = ConnectionFactory.getIntance().getHistoryConnection()) {
            EmployeeDTO employee = SystemSession.getInstancia().getCurrentEmployee();
            String current_user = currentUser(connection);
            res = save(connection, current_user, employee, affected_table, type_mov, description);
        } catch (SQLException e) {
            throw e;
        }
        return res;
    }

    private boolean save(EmployeeDTO employee, int affected_table, int type_mov, String description) throws SQLException {
        boolean res;
        try (JDBConnection connection = ConnectionFactory.getIntance().getHistoryConnection()) {
            String current_user = currentUser(connection);
            res = save(connection, current_user, employee, affected_table, type_mov, description);
        } catch (SQLException e) {
            throw e;
        }
        return res;
    }

    protected boolean save(JDBConnection connection, String current_user, EmployeeDTO employee, int affected_table, int type_mov, String description) throws SQLException {
        boolean rt = false;
        try (PreparedStatement ps = connection.getNewCallableStatement(INSERT)) {
            ps.setString(1, employee.getId());
            ps.setString(2, current_user);
            ps.setInt(3, affected_table);
            ps.setInt(4, type_mov);
            ps.setString(5, description);
            rt = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw e;
        }
        return rt;
    }

    public String currentUser(JDBConnection connection) {
        String q = "SELECT CURRENT_USER";
        String current_user = null;
        try (PreparedStatement ps = connection.getNewPreparedStatement(q)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                current_user = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.getLogger(HysHistoryDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return current_user;
    }

    public HysEmployeeMovs getHysEmployeeMovs() {
        return INSTANCE2;
    }

    public HysUsersMovs getHysUsersMovs() {
        return INSTANCE3;
    }

    public class HysEmployeeMovs {

        public boolean insertToEmployee(String description) throws SQLException {
            return insert(Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean updateToEmployee(String description) throws SQLException {
            return insert(Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean deleteToEmployee(String description) throws SQLException {
            return insert(Const.INDEX_EMP_EMPLOYEES, description);
        }

        public boolean saveLogin(EmployeeDTO employee, String description) throws SQLException {
            return save(employee, Const.INDEX_HYS_PROGRAM_HISTORY, Const.INDEX_LOGIN, description);
        }

        public boolean saveLogOut(EmployeeDTO employee, String description) throws SQLException {
            return save(employee, Const.INDEX_HYS_PROGRAM_HISTORY, Const.INDEX_LOGOUT, description);
        }

    }

    public class HysUsersMovs implements Serializable {

        private static final long serialVersionUID = 1L;

        public boolean insertToUsers(String description) throws SQLException {
            return insert(Const.INDEX_USR_USERS, description);
        }

        public boolean updateToUsers(String description) throws SQLException {
            return insert(Const.INDEX_USR_USERS, description);
        }

        public boolean deleteToUsers(String description) throws SQLException {
            return insert(Const.INDEX_USR_USERS, description);
        }
    }

}
