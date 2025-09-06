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
package com.jblue.util.cache;

import com.jblue.model.DBConnection;
import com.jblue.model.dtos.Objects;
import com.jblue.sys.DevFlags;
import com.jblue.util.ObjectUtils;
import com.jutil.dbcon.connection.JDBConnection;
import com.jutil.jexception.JExcp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class AbstractCache<T extends Objects> implements CacheModel<T> {

    protected final List<T> cache;
    protected final Map<String, List<T>> buffer_cache;
    protected final DBConnection<T> conexion;
    protected final String default_query;

    protected int index_min, index_max, steps, last_id;
    protected long count;
    protected int call_count;

    protected String query;
    protected String last_id_query;

    protected boolean other_query;

    protected ObjectAdapterModel adapter;

    public AbstractCache(List<T> cache, int capacity, DBConnection conexion) {
        this.buffer_cache = new HashMap<>(40);
        this.cache = cache;
        this.conexion = conexion;
        this.index_min = 1;
        this.index_max = capacity;
        this.steps = capacity;
        this.default_query = "SELECT * FROM %s WHERE id >= %s and id <= %s and status NOT IN(3,8)";
        this.last_id_query = "SELECT MAX(id) FROM %s;";
        setDefaultAdapter();
    }

    @Override
    public void loadData() {
        try {
            if (DevFlags.DEV_MSG_CODE) {
                System.out.println("load...");
            }
            String aux = default_query.formatted(conexion.getTable(), index_min, index_max);
            if (buffer_cache.containsKey(aux)) {
                if (DevFlags.DEV_MSG_CODE) {
                    System.out.println("read in the buffer....");
                }
                cache.addAll(buffer_cache.get(aux));
                return;
            }
            if (DevFlags.DEV_MSG_CODE) {
                System.out.println("leyendo base de datos.....");
            }
            JDBConnection conn = conexion.getJDBConnection();
            load(adapter, conn.query(aux), aux, conexion);
            try (ResultSet rs = conn.query(last_id_query.formatted(conexion.getTable()))) {
                if (rs.next()) {
                    last_id = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            JExcp.getInstance(false, DevFlags.LOGS_DEV).print(ex, getClass(), "loadData");
        }

    }

    public void load(ObjectAdapterModel<T> adapter, ResultSet rs_data, String aux, DBConnection<T> connection) {
        try {
            while (rs_data.next()) {
                Objects objeto = adapter.adapter(rs_data, connection);
                cache.add((T) objeto);
            }

            if (DevFlags.DEV_MSG_CODE) {
                System.out.println("saving in the buffer......");
            }
            buffer_cache.put(aux, List.copyOf(cache));
        } catch (SQLException ex) {
            JExcp.getInstance(false, DevFlags.LOGS_DEV).print(ex, getClass(), "load");
        }
    }

    @Override
    public void dumpData() {
        if (!cache.isEmpty()) {
            cache.clear();
        }
    }

    @Override
    public void reLoadData() {
        dumpData();
        loadData();
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public long count() {
        return last_id;
    }

    public void setAdapter(ObjectAdapterModel adapter) {
        this.adapter = adapter;
    }

    public final void setDefaultAdapter() {
        adapter = (rs_data, connection) -> defaultAdapter(rs_data, connection);
    }

    private Objects defaultAdapter(ResultSet rs_data, DBConnection connection) {
        String[] info = new String[connection.getFields().length];
        for (int i = 0; i < info.length; i++) {
            try {
                info[i] = rs_data.getString(i + 1);
            } catch (SQLException ex) {
                JExcp.getInstance(false, DevFlags.LOGS_DEV).print(ex, getClass(), "defaultAdapter");
            }
        }
        return ObjectUtils.getObjeto(connection.getTable(), info);
    }
}
