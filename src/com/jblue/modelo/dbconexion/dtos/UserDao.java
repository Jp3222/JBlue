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
package com.jblue.modelo.dbconexion.dtos;

import com.jblue.modelo.dbconexion.querys.UsersQuerys;
import com.jblue.modelo.fabricas.CacheFactory;
import com.jblue.sistema.SystemLogs;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.framework.LaunchApp;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanp
 */
public class UserDao {

    public static DBConnection connection = (DBConnection) LaunchApp.getInstance().getResources("connection");

    public static List<String[]> getUsersNotPayed() {
        ArrayList<String[]> list = new ArrayList<>((int) CacheFactory.USERS.count());
        try {
            LocalDate ly = LocalDate.now();
            ResultSet rs = connection.query(UsersQuerys.users_not_paid.formatted(ly.getYear()));
            String[] arr = new String[2];
            while (rs.next()) {
                arr[0] = rs.getString(1);
                arr[1] = rs.getString(2);
                list.add(arr.clone());
            }
        } catch (SQLException e) {
            SystemLogs.severeDbLogs("ERROR: USUARIOS NO PAGADOS", e);
        }
        return list;
    }
}
