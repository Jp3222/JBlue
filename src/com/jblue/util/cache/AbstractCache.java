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

import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.sistema.DevFlags;
import com.jblue.sistema.SystemLogs;
import com.jblue.util.tools.ObjectUtils;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.jexception.Excp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class AbstractCache<T extends Objeto> implements CacheModel<T> {

    protected final List<T> cache;
    protected final Map<String, List<T>> buffer_cache;
    protected final JDBConnection<T> conexion;
    protected final String default_query;

    protected int index_min, index_max, steps;
    protected long count;
    protected int call_count;

    protected String query;

    protected boolean other_query;

    protected ObjectAdapterModel adapter;

    public AbstractCache(List<T> cache, int capacity, JDBConnection conexion) {
        this.buffer_cache = new HashMap<>(40);
        this.cache = cache;
        this.conexion = conexion;
        this.index_min = 1;
        this.index_max = capacity;
        this.steps = capacity;
        this.default_query = "SELECT * FROM %s WHERE id >= %s and id <= %s and status != 3";
        setDefaultAdapter();
    }

    @Override
    public void loadData() {
        try {
            if (DevFlags.DEV_MSG_CODE) {
                System.out.println("load");
            }
            String aux = default_query.formatted(conexion.getTable(), index_min, index_max);
            if (buffer_cache.containsKey(aux)) {
                cache.addAll(buffer_cache.get(aux));
                return;
            }

            if (DevFlags.DEV_MSG_CODE) {
                System.out.println("leyendo base de datos...");
            }
            DBConnection conn = conexion.getConnection();

            load(adapter, conn.query(aux), aux, conexion);

        } catch (SQLException ex) {
            Logger.getLogger(AbstractListCache.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void load(ObjectAdapterModel<T> adapter, ResultSet rs_data, String aux, JDBConnection<T> connection) {
        try {
            while (rs_data.next()) {
                Objeto objeto = adapter.adapter(rs_data, connection);
                cache.add((T) objeto);
            }
            buffer_cache.put(aux, List.copyOf(cache));
        } catch (SQLException ex) {
            Excp.impTerminal(ex, getClass(), DevFlags.DEV_MSG_LOG_DATA_BASE);
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
        String count_query = "SELECT count(id) FROM %s";
        DBConnection _conn = conexion.getConnection();
        ResultSet res_count;
        long aux_count = 0;
        try {
            res_count = _conn.query(count_query.formatted(conexion.getTable()));

            if (res_count.next()) {
                aux_count = res_count.getLong(1);
            }
            res_count.close();
        } catch (SQLException ex) {
            SystemLogs.severeDbLogs(count_query, ex);
        }
        return aux_count;
    }

    public void setAdapter(ObjectAdapterModel adapter) {
        this.adapter = adapter;
    }

    public final void setDefaultAdapter() {
        adapter = (rs_data, connection) -> defaultAdapter(rs_data, connection);
    }

    private Objeto defaultAdapter(ResultSet rs_data, JDBConnection connection) {
        String[] info = new String[connection.getFields().length];
        for (int i = 0; i < info.length; i++) {
            try {
                info[i] = rs_data.getString(i + 1);
            } catch (SQLException ex) {
                Logger.getLogger(AbstractListCache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ObjectUtils.getObjeto(connection.getTable(), info);
    }
}
