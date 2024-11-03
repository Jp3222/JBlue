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

    private int page;

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
        int aux;

        if (mov == MOV_TO_BACK) {
            aux = index_min - steps;
//            if (aux <= 0) {
//                return false;
//            }
            this.index_min -= steps;
            this.index_max -= steps;
        }

        if (mov == MOV_TO_NEXT) {
            aux = index_max + steps;

//            if (aux > count()) {
//                return false;
//            }
            this.index_min += steps;
            this.index_max += steps;
        }
        reLoadData();
        return !cache.isEmpty();
    }

    @Override
    public boolean movBuffer(int page) {
        
        return true;
    }

    public boolean isBufferBack() {
        return buffer_direc == MOV_TO_BACK;
    }

    public boolean isBufferNext() {
        return buffer_direc == MOV_TO_NEXT;
    }

    private int buffer_direc;

}