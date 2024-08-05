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
package com.jblue.util.cache.inter;

import com.jblue.modelo.bdconexion.FuncionesBD;
import com.jblue.util.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.function.Predicate;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstraccionListCache<T extends Objeto> implements ModeloCache<T> {

    protected static int LinkedList = 1;
    protected static int Stack = 2;
    protected static int ArrayList = 3;

    protected final List<T> current_data;
    protected final List<T> aux_data;
    protected final FuncionesBD conexion;
    protected int steps;

    protected AbstraccionListCache(FuncionesBD conexion, int type_collection, int capacity, int steps) {
        this.conexion = conexion;
        this.current_data = getTypeList(steps, capacity);
        this.aux_data = getTypeList(steps, capacity);
        this.steps = steps;
    }

    @Override
    public int firstID() {
        return Integer.parseInt(current_data.getFirst().getId());
    }

    @Override
    public int lastID() {
        return Integer.parseInt(current_data.getLast().getId());
    }

    @Override
    public void setSteps(int step) {
        steps = step;
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public void loadData() {
        Optional<ArrayList<Objeto>> r = conexion._SELECT(null, null);
        if (r.isEmpty()) {
            return;
        }
        current_data.addAll((Collection<? extends T>) r.get());
    }

    @Override
    public void dumpData() {
        if (!current_data.isEmpty()) {
            current_data.clear();
        }
        if (!aux_data.isEmpty()) {
            current_data.clear();
        }
    }

    @Override
    public void reLoadData() {
        dumpData();
        loadData();
    }

    @Override
    public int count(int o) {
        return switch (o) {
            case CACHE_NEXT:
                yield current_data.size();
            case CACHE_CURRENT:
            default:
                yield current_data.size();

        };
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int sizeDB() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<T> get(Predicate<T> filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Collection<T> getCache() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private List<T> getTypeList(int type, int capacity) {
        return switch (type) {
            case 1:
                yield new LinkedList();
            case 2:
                yield new Stack();
            default:
                yield new ArrayList(capacity);
        };
    }
}
