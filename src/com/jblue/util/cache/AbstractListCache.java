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

import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractListCache<T extends Objeto> extends AbstractCache<T> implements ListCacheModel<T> {

    public AbstractListCache(int capacity, JDBConnection conexion) {
        super(new ArrayList<>(capacity), capacity, conexion);
    }

    public AbstractListCache(JDBConnection conexion) {
        this(MIN, conexion);
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
        return (ArrayList<T>) cache;
    }

    @Override
    public List<T> getList(Predicate<T> filter) {
        return cache.stream().filter(filter).toList();
    }

    public int getIndexMax() {
        return index_max;
    }

    public int getIndexMin() {
        return index_min;
    }
}
