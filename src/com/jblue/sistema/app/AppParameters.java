/*
 * Copyright (C) 2025 juan-campos
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
package com.jblue.sistema.app;

import com.jutil.dbcon.connection.DBConnection;
import com.jutil.framework.LaunchApp;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author juan-campos
 */
public final class AppParameters {
    
    private static Object getParameter(String parameter_name) {
        DBConnection connection = (DBConnection) LaunchApp.getInstance().getResources("connection");
        Object res = null;
        try (ResultSet rs = connection.select("parameters", "value", "name = %s".formatted(parameter_name))) {
            if (rs.next()) {
                return rs.getObject(parameter_name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppParameters.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getMatserUser() {
        return (String) getParameter("MASTER_USER");
    }

    public static String getMatserPassword() {
        return (String) getParameter("MASTER_PASSWORD");
    }

}
