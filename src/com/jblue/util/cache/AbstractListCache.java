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
package com.jblue.util.cache;

import com.jblue.util.tools.ObjectUtils;
import com.jblue.modelo.dbconexion.ModeloFuncionesDB;
import com.jblue.modelo.objetos.Objeto;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.dbcon.cn.SimpleQuerys;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractListCache<T extends Objeto> implements ModeloListCache<T> {

    protected final ArrayList<T> cache;
    protected final Map<String, List<T>> buffer_cache;
    protected final ModeloFuncionesDB<T> conexion;
    protected int index_min, index_max, steps;
    protected long count;
    protected int call_count;

    public AbstractListCache(int capacity, ModeloFuncionesDB conexion) {
        this.cache = new ArrayList<>(capacity);
        this.buffer_cache = new HashMap<>(10);
        this.conexion = conexion;
        this.index_min = 1;
        this.index_max = capacity;
        steps = capacity;

    }

    public AbstractListCache(ModeloFuncionesDB conexion) {
        this(MIN, conexion);
    }

    @Override
    public void loadData() {
        System.out.println("load");
        String query = "SELECT * FROM %s WHERE id >= %s and id <= %s";
        query = query.formatted(conexion.getTable(), index_min, index_max);
        if (buffer_cache.containsKey(query)) {
            cache.addAll(buffer_cache.get(query));
            return;
        }
        System.out.println("leyendo base de datos...");
        try {
            DBConnection conn = DBConnection.getInstance();
            ResultSet rs_data = conn.query(query);
            String[] info;
            while (rs_data.next()) {
                info = new String[conexion.getFields().length];
                for (int i = 0; i < conexion.getFields().length; i++) {
                    info[i] = rs_data.getString(conexion.getFields()[i]);
                }
                Objeto objeto = ObjectUtils.getObjeto(conexion.getTable(), info);
                cache.add((T) objeto);
            }

            buffer_cache.put(query, (List<T>) cache.clone());
        } catch (SQLException ex) {
            Logger.getLogger(AbstractListCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public int getSteps() {
        return steps;
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
        dumpBuffer();
        loadData();
    }

    public void dumpBuffer() {
        buffer_cache.clear();
    }

    @Override
    public ArrayList<T> getList() {
        return cache;
    }

    @Override
    public List<T> getList(Predicate<T> filter) {
        return cache.stream().filter(filter).toList();
    }

    @Override
    public long count() {
//        switch (call_count) {
//            case 0 ->
//                count = count_count();
//            case 5 ->
//                call_count = 0;
//            default ->
//                call_count++;
//        }
//        return count;
        return count_count();
    }

    private long count_count() {
        String query = "SELECT count(id) FROM %s";
        DBConnection _conn = (DBConnection) conexion.getConnection();

        ResultSet res_count;
        long aux_count = 0;
        try {
            res_count = _conn.query(query.formatted(conexion.getTable()));
            if (res_count.next()) {
                aux_count = res_count.getLong(1);
            }
            res_count.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractListCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux_count;
    }

    public int getIndexMax() {
        return index_max;
    }

    public int getIndexMin() {
        return index_min;
    }

}
