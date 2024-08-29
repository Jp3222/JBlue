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
package com.jblue.modelo.cache;

import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.absobj.Objeto;
import com.jblue.util.tools.ObjetoUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstraccionListCache<T extends Objeto> implements ModeloListCache<T> {

    protected final ArrayList<T> cache;
    protected final ArrayList<T> buffer_cache;
    private final FuncionesBD conexion;
    private int index_min, index_max, steps;

    public AbstraccionListCache(int capacity, FuncionesBD conexion) {
        this.cache = new ArrayList<>(capacity);
        this.buffer_cache = new ArrayList<>(capacity);
        this.conexion = conexion;
        this.index_min = 1;
        this.index_max = capacity;
        steps = capacity;

    }

    public AbstraccionListCache(FuncionesBD conexion) {
        this(MIN, conexion);
    }

    @Override
    public void loadData() {
        String query = "SELECT * FROM %s WHERE id >= %s and id <= %s";
        try {
            ResultSet rs = conexion.getConexion().queryResult(query.formatted(conexion.getTabla(), index_min, index_max));
            String[] info;
            while (rs.next()) {
                info = new String[conexion.getCampos().length];
                for (int i = 0; i < conexion.getCampos().length; i++) {
                    info[i] = rs.getString(conexion.getCampos()[i]);
                }
                Objeto objeto = ObjetoUtil.getObjeto(conexion.getTabla(), info);
                cache.add((T) objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbstraccionListCache.class.getName()).log(Level.SEVERE, null, ex);
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
        loadData();
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
        String query = "SELECT count(id) FROM %s";
        long count = 0;
        try {
            ResultSet rs = conexion.getConexion().queryResult(query.formatted(conexion.getTabla()));
            count = rs.getLong(1);
        } catch (SQLException ex) {
            Logger.getLogger(AbstraccionListCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
