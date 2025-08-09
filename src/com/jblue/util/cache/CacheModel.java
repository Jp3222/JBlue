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
package com.jblue.util.cache;

import com.jblue.model.dtos.Objects;

/**
 *
 * @author jp
 * @param <T>
 */
public interface CacheModel<T extends Objects> {

    static final int MIN = 1000;
    static final int MID = 2000;
    static final int MAX = 3000;

    /**
     * retorna el rango de identificadores el cual puede ser MIN = 1000, MID =
     * 2000, MAX = 3000 el valor puede ser definido segun la capacidad del
     * dispositivo en este caso esta pensando para que un JTable no sea dificl
     * de leer
     *
     *
     * @return el rango de ID's
     */
    int getSteps();

    /**
     * Este metodo obtiene lo datos de la base de datos y los guarda en la
     * estructura especificada por el tipo de cache.
     */
    void loadData();

    /**
     * Este metodo vacia la estructura utilizada
     */
    void dumpData();

    /**
     * Este metodo reinicia la memoria cache usando "dumpData" para limpiar la
     * estructura y despues "loadData" para volver a leer los datos, el metodo
     * puede variar segun la implementacion o la coleccion utilizada por lo que
     * no se recomienda hacer llamadas muy frecuentes o al menos no en el hilo
     * principal de ejecucion.
     */
    void reLoadData();

    /**
     * @return el tamaño del la coleccion utilizada
     */
    int size();

    /**
     *
     * @return el numero de elementos total de la tabla especificada en la cache
     */
    long count();

}
