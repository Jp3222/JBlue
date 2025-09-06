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
package com.jblue.model;

import com.jblue.model.constants.Table;
import com.jblue.model.dtos.Objects;
import com.jutil.dbcon.connection.JDBConnection;
import com.jutil.jexception.Excp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class DBConnection<T extends Objects> extends AbstractDBConnection<T> {

    private Table object_table;

    public DBConnection(String table, String[] fields) {
        super(table, fields);
    }

    public DBConnection(Table table) {
        super(table.getTable(), table.getFields());
    }

    public boolean insert(String fields, String... values) {
        boolean out = false;
        try {
            out = connection.insert(table, fields, format(VALUES, null, values));
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
        return out;
    }

    public Map<String, String> insertAndGet(String fields, String values) {
        Map<String, String> map = new HashMap<>(10);

        try (Statement st = connection.getConnection().createStatement()) {
            String query = JDBConnection.INSERT_COL.formatted(table, fields, format(VALUES, null, values));
            st.execute(query, new String[]{"id"});
            try (ResultSet rs = st.getResultSet()) {
                map.put(fields, rs.getString("id"));
            }
        } catch (SQLException ex) {
            Excp.imp(ex, getClass(), true, true);
        }
        return map;
    }

    public boolean update(String key_value, String where) {
        boolean out = false;
        try {
            out = connection.update(table, key_value, where);
        } catch (SQLException ex) {

        }
        return out;
    }

    public Table getObjectTable() {
        return object_table;
    }

}
