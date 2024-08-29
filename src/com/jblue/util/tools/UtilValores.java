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
package com.jblue.util.tools;

import com.jblue.modelo.objetos.OValores;
import com.jblue.util.tiempo.Fecha;
import com.jblue.util.tiempo.Hora;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author jp
 */
public interface UtilValores {

    public static int convertirValorInt(OValores o) {
        return Integer.parseInt(o.getValor());
    }

    public static double convertirValorDouble(OValores o) {
        return Double.parseDouble(o.getValor());
    }

    public static LocalDate convertirFecha(OValores o) {
        return LocalDate.parse(o.getValor(), Fecha.FORMATO);

    }

    public static LocalTime convertirHora(OValores o) {
        return LocalTime.parse(o.getValor(), Hora.PATRON);
    }

}
