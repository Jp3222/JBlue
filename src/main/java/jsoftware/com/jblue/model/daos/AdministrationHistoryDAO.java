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

import jsoftware.com.jutil.db.JDBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author juanp
 */
public class AdministrationHistoryDAO {

    public static int ADMINISTRATOR = 0;
    public static int PRESIDENT = 1;
    public static int TREASURER = 2;

    public static String[] EMPLOYEE_REGISTER = {
        "administrator", "president", "treasurer"
    };

    public static boolean isPresidentRegister(JDBConnection connection) {
        return isEmployeeRegister(connection, PRESIDENT);
    }

    public static boolean isTreasurerRegister(JDBConnection connection) {
        return isEmployeeRegister(connection, TREASURER);
    }

    public static boolean isAdministratorRegister(JDBConnection connection) {
        return isEmployeeRegister(connection, ADMINISTRATOR);
    }

    private static boolean isEmployeeRegister(JDBConnection connection, int employee) {
        boolean rt = false;
        String query = getQueryEmployeeRegisterInAdmin(employee);
        try (ResultSet rs = connection.query(query);) {
            rt = rs.next();
            return rt;
        } catch (SQLException ex) {
            System.getLogger(AdministrationHistoryDAO.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return rt;
    }

    public static String getQueryEmployeeRegisterInAdmin(int index_field) {
        StringBuilder sb = new StringBuilder(255);
        sb.append("SELECT * FROM his_administration_history \n");
        sb.append("WHERE year_of_administration = YEAR(CURRENT_TIMESTAMP) \n");
        sb.append("AND status = 1 \n");
        sb.append("AND ");
        sb.append(EMPLOYEE_REGISTER[index_field]);
        sb.append("!= NULL");
        return sb.toString();
    }
}
