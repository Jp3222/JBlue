/*
 * Copyright (C) 2024 juan-campos
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
package jsoftware.com.jblue.util.cache;

import jsoftware.com.jblue.model.DBConnection;
import jsoftware.com.jblue.model.dtos.Objects;
import jsoftware.com.jblue.util.ObjectUtils;
import jsoftware.com.jutil.dbcon.connection.JDBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractSetCache<T extends Objects> implements SetCacheModel<T> {

    protected final Set<T> cache;
    protected final DBConnection<T> conexion;
    protected int index_min, index_max, steps;
    protected long count;
    protected int call_count;

    public AbstractSetCache(int capacity, DBConnection conexion) {
        this.cache = new HashSet<>(MIN);
        this.conexion = conexion;
        this.index_min = 1;
        this.index_max = capacity;
        steps = capacity;
    }

    @Override
    public int getSteps() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void loadData() {
        String query = "SELECT * FROM %s WHERE id >= %s and id <= %s";
        query = query.formatted(conexion.getTable(), index_min, index_max);
        System.out.println("leyendo base de datos...");
        try {
            JDBConnection conn = conexion.getJDBConnection();
            ResultSet rs_data;
            rs_data = conn.query(query);
            String[] info;
            while (rs_data.next()) {
                info = new String[conexion.getFields().length];
                for (int i = 0; i < conexion.getFields().length; i++) {
                    info[i] = rs_data.getString(conexion.getFields()[i]);
                }
                Objects objeto = ObjectUtils.getObjeto(conexion.getTable(), info);
                cache.add((T) objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstractListCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void dumpData() {
        if (cache.isEmpty()) {
            return;
        }
        cache.clear();
    }

    @Override
    public void reLoadData() {
        dumpData();
        loadData();
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Set<T> getSet() {
        return cache;
    }

    @Override
    public Set<T> getList(Predicate<T> filter) {
        List<T> toList = cache.stream().filter(filter).toList();
        return Set.copyOf(toList);
    }

}
