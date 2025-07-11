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
package com.jblue.util.cache;

import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.objetos.Objeto;
import java.sql.ResultSet;
/**
 *
 * @author juanp
 * @param <T>
 */
@FunctionalInterface
public interface ObjectAdapterModel<T extends Objeto> {

    T adapter(ResultSet rs_data, JDBConnection connection);
}
