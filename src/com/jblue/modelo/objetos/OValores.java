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
package com.jblue.modelo.objetos;

import com.jblue.util.modelo.objetos.Objeto;

/**
 *
 * @author jp
 */
public class OValores extends Objeto {

    public String getClave() {
        return _conjunto[1];
    }

    public String getValor() {
        return _conjunto[2];
    }

    public String getNombreGrafico() {
        return _conjunto[3];
    }

    @Override
    public String getStringR() {
        return getNombreGrafico();
    }

}
