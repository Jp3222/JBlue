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
package com.jblue.util.modelo.cache;

import com.jblue.util.modelo.objetos.Objeto;

/**
 *
 * @author jp
 * @param <T>
 */
public interface ModeloCache<T extends Objeto> {

    static final int MIN = 1000;
    static final int MID = 2000;
    static final int MAX = 3000;

    int getSteps();

    void loadData();

    void dumpData();

    void reLoadData();

    int size();

    long sizeDB();

}
