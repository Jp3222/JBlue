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

import com.jblue.modelo.objetos.Objeto;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public interface ListCacheModel<T extends Objeto> {

    /**
     * Consulta la lista con los datos de la base de datos
     *
     * @return una lista con los datos "actuales" de la base de datos
     */
    List<T> getList();

    /**
     * Aplica un predicado usando el metodo stream
     *
     * @param filter predicado para filtar los datos
     * @return una lista con el filtro aplicado
     */
    List<T> getList(Predicate<T> filter);
}
