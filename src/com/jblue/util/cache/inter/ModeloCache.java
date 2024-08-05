/*
 * Copyright (C) 2023 jp
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

import com.jblue.util.modelo.objetos.Objeto;
import java.util.Collection;
import java.util.function.Predicate;

/**
 *
 * @author jp
 * @param <T>
 */
public interface ModeloCache<T extends Objeto> {

    static final int CAP_MIN = 1000;
    static final int CAP_MID = 2000;
    static final int CAP_MAX = 3000;

    static final int CACHE_BACK = 1000;
    static final int CACHE_CURRENT = 2000;
    static final int CACHE_NEXT = 3000;
    static final int DATA_BASE = 4000;

    int firstID();

    int lastID();

    void setSteps(int step);

    int getSteps();

    void loadData();

    void dumpData();

    void reLoadData();

    int count(int o);

    int size();

    int sizeDB();

    Collection<T> get(Predicate<T> filter);

    Collection<T> getCache();

}
