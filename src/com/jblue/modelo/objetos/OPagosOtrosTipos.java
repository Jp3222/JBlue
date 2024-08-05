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
package com.jblue.modelo.objetos;

import com.jblue.util.modelo.objetos.Objeto;
import java.time.LocalDate;

/**
 *
 * @author juan-campos
 */
public class OPagosOtrosTipos extends Objeto {

    public String getMotivo() {
        return _conjunto[1];
    }

    public String getDescripcion() {
        return _conjunto[2];
    }

    public String getMonto() {
        return _conjunto[3];
    }

    public String getUrlNotas() {
        return _conjunto[4];
    }

    public LocalDate getFechaInicio() {
        return LocalDate.parse(_conjunto[5]);
    }

    public String getFechaFin() {
        return _conjunto[6];
    }

    @Override
    public String StringRepresentacion() {
        return getMonto();
    }

    @Override
    public String toString() {
        return StringRepresentacion();
    }

}
