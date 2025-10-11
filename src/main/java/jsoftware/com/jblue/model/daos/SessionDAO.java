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

import jsoftware.com.jblue.model.dtos.AdministrationHistoryObject;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jutil.dbcon.connection.JDBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author juanp
 */
public class SessionDAO {
    
    public static boolean isSessionCaduque(OEmployee o) {
        if (o.getDateEnd() == null) {
            return false;
        }
        if (o.getStatus() != 1) {
            return false;
        }
        return LocalDateTime.now().isBefore(o.getDateEnd());
    }
    
    public static AdministrationHistoryObject isAdministrarionRegister(JDBConnection connection) {
        AdministrationHistoryObject o = null;
        try {
            ResultSet rs = connection.query(getQuery());
            if (rs.next()) {
                o = new AdministrationHistoryObject();
                String[] arr = new String[16];
                for (int i = 0; i < 16; i++) {
                    arr[i] = rs.getString(i + 1);
                }
                o.setData(arr);
                return o;
            }
        } catch (SQLException ex) {
            System.getLogger(SessionDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return o;
    }
    
    private static String getQuery() {
        StringBuilder sb = new StringBuilder(300);
        sb.append("SELECT * FROM hys_administration_history \n");
        sb.append("WHERE year_of_administration = YEAR(CURRENT_TIMESTAMP) \n");
        sb.append("AND root != NULL");
        sb.append("AND president != NULL");
        sb.append("AND treasurer != NULL");
        sb.append("AND secretary != NULL");
        sb.append("AND status = 1");
        return sb.toString();
    }
}
