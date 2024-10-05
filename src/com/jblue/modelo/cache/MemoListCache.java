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

import com.jblue.util.cache.Paginado;
import com.jblue.util.cache.AbstraccionListCache;
import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.objetos.Objeto;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class MemoListCache<T extends Objeto> extends AbstraccionListCache<T> implements Paginado {

    public MemoListCache(int capacity, FuncionesBD conexion) {
        super(capacity, conexion);
    }

    public MemoListCache(FuncionesBD conexion) {
        super(conexion);
    }

    public T get(Predicate<T> filter) {
        List<T> list = getList(filter);
        return list.getFirst();
    }

    @Override
    public boolean next() {
        return movData(MOV_TO_NEXT);
    }

    @Override
    public boolean back() {
        return movData(MOV_TO_BACK);
    }

    @Override
    public boolean movData(int mov) {
        if (mov == MOV_TO_BACK) {
            this.index_min -= steps;
            if (index_min < 0) {
                this.index_min += steps;
                return false;
            }
            this.index_max -= steps;
        }
        if (mov == MOV_TO_NEXT) {
            this.index_min += steps;
            this.index_max += steps;
        }
        loadData();
        return cache.isEmpty();
    }
    
    private void tranferCache(){
        if (!buffer_cache.isEmpty()) {
        }
    }

    /* 
    cache = NEXT = 1
    buffer = NEXT = 1
    1: [1, 2, 3]
    2: [4, 5, 6]
    3: [7, 8, 9]
     */
    @Override
    public boolean movBuffer(int mov) {
        this.buffer_direc = mov;
        buffer_cache.addAll(cache);
        return !buffer_cache.isEmpty();
    }

    public boolean isBufferBack() {
        return buffer_direc == MOV_TO_BACK;
    }

    public boolean isBufferNext() {
        return buffer_direc == MOV_TO_NEXT;
    }

    private int buffer_direc;

}
