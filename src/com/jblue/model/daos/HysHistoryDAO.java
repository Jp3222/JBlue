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
package com.jblue.model.daos;

import com.jblue.model.constants._Const;
import com.jblue.sys.SystemSession;
import com.jutil.dbcon.connection.JDBConnection;
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
    private final String query = "INSERT INTO hys_program_history(employee, db_user, affected_table, type_mov, description) VALUES(?,?,?,?,?)";

    public HysHistoryDAO() {
        connection = JDBConnection.getInstance();
    }

    public boolean insertToUsers(String description) {
        return insert(_Const.INDEX_USR_USERS, description);
    }

    public boolean updateToUsers(String description) {
        return insert(_Const.INDEX_USR_USERS, description);
    }

    public boolean deleteToUsers(String description) {
        return insert(_Const.INDEX_USR_USERS, description);
    }

    public boolean insert(int affected_table, String description) {
        return save(affected_table, _Const.INDEX_INSERT, description);
    }

    public boolean update(int affected_table, String description) {
        return save(affected_table, _Const.INDEX_UPDATE, description);
    }

    public boolean delete(int affected_table, String description) {
        return save(affected_table, _Const.INDEX_LOGIC_DELETE, description);
    }

    public boolean save(int affected_table, int type_mov, String description) {
        boolean rt = false;
        try {
            connection.getConnection().setAutoCommit(false);
            try (PreparedStatement ps = connection.getConnection().prepareStatement(query)) {
                ps.setString(1, SystemSession.getInstancia().getUsuario().getId());
                ps.setString(2, currentUser());
                ps.setInt(3, affected_table);
                ps.setInt(4, type_mov);
                ps.setString(5, description);
                rt = ps.executeUpdate() > 0;
                connection.getConnection().commit();
                connection.getConnection().setAutoCommit(true);
            }
        } catch (SQLException ex) {
            try {
                connection.getConnection().rollback();
            } catch (SQLException ex1) {
                System.getLogger(HysHistoryDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex1);
            }
            System.getLogger(HysHistoryDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return rt;
    }

    public String currentUser() {
        String q = "SELECT CURRENT_USER";
        String curus = null;
        try (ResultSet rs = connection.query(q);) {
            curus = rs.getString(1);
        } catch (SQLException ex) {
            System.getLogger(HysHistoryDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return curus;
    }

}
