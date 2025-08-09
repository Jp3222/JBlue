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

import com.jblue.model.dtos.Objects;
import com.jblue.util.ObjectUtils;
import com.jutil.dbcon.connection.DBConnection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractJDBConnection<T extends Objects> implements JDBConnectionModel {

    public static final int FIELDS = 1;
    public static final int VALUES = 2;
    public static final int KEY_VALUES = 3;

    protected final DBConnection connection;
    protected final String table;
    private final String[] fields;
    private final String format_insert = "'%s'";
    private final String format_update_col = "%s = '%s'";

    public AbstractJDBConnection(String table, String[] fields) {
        this.connection = DBConnection.getInstance();
        this.table = table;
        this.fields = fields;
    }

    @Override
    public boolean insert(String... valores) {
        boolean out = false;
        try {
            String[] _fields = Arrays.copyOfRange(fields, 1, fields.length);
            out = connection.insert(table,
                    format(FIELDS, null, _fields),
                    format(VALUES, null, valores));
        } catch (SQLException ex) {
        }
        return out;
    }

    @Override
    public boolean delete(String where) {
        boolean out = false;
        try {
            out = connection.delete(table, where);
        } catch (SQLException ex) {

        }
        return out;
    }

    @Override
    public boolean update(String campo, String valor, String where) {
        boolean out = false;
        try {
            out = connection.update(table, campo, valor, where);
        } catch (SQLException ex) {

        }
        return out;
    }

    @Override
    public boolean update(String[] campos, String[] newData, String where) {
        boolean out = false;
        try {
            out = connection.update(table, format(KEY_VALUES, campos, newData), where);
        } catch (SQLException ex) {

        }
        return out;
    }

    @Override
    public List<T> select(String campos, String where) {
        ArrayList<T> list = new ArrayList(100);
        try {
            if (campos.isBlank()) {
                return list;
            }
            String[] _fields = campos.replace(" ", "").split(",");
            if (!campos.contains("*")) {
                if (_fields.length == 0) {
                    return list;
                }
            }

            ResultSet select = connection.select(campos, table, where);
            String[] a = new String[fields.length];
            while (select.next()) {
                System.out.println(select);
                for (int i = 0; i < a.length; i++) {
                    a[i] = select.getString(i + 1);
                }
                list.add((T) ObjectUtils.getObjeto(table, a.clone()));
            }
        } catch (SQLException ex) {

        }
        return list;
    }

    @Override
    public Optional<T> get(String campos, String where) {
        List<T> select = select(campos, where);
        if (select.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(select.getFirst());
    }

    public String format(int type, String[] fields, String... data) {
        StringBuilder sb = new StringBuilder(100);
        int i = 0;
        return switch (type) {
            case FIELDS:
                while (i < data.length - 1) {
                    sb.append(data[i]).append(", ");
                    i++;
                }
                sb.append(data[i]);
                yield sb.toString();

            case VALUES:
                while (i < data.length - 1) {
                    sb.append(format_insert.formatted(data[i])).append(", ");
                    i++;
                }
                sb.append(format_insert.formatted(data[i]));

                yield sb.toString();
            case KEY_VALUES:
                if (fields.length != data.length) {
                    yield "null";
                }
                while (i < data.length - 1) {
                    sb.append(format_update_col.formatted(fields[i], data[i])).append(", ");
                    i++;
                }
                sb.append(format_update_col.formatted(fields[i], data[i]));
                yield sb.toString();
            default:
                yield "";
        };
    }

    @Override
    public String getTable() {
        return table;
    }

    @Override
    public String[] getFields() {
        return fields;
    }

    @Override
    public DBConnection getConnection() {
        return connection;
    }

}
