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
package com.jblue.modelo.dbconexion;

import com.jblue.modelo.constdb.Table;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.FormatoBD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class DBConnection<T extends Objeto> extends AbstractDBConnection<T> {

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
            out = connection.insert(table, fields, FormatoBD.valuesFormat(values));
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return out;
    }

    public Table getObjectTable() {
        return object_table;
    }

}
