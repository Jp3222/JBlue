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
package jsoftware.com.jblue.model.dto;

import java.time.LocalDate;

/**
 *
 * @author juan-campos
 */
public class OPaymentsTypes extends Objects {

    public String getMotivo() {
        return values[1];
    }

    public String getDescripcion() {
        return values[2];
    }

    public String getMonto() {
        return values[3];
    }

    public String getUrlNotas() {
        return values[4];
    }

    public LocalDate getFechaInicio() {
        return LocalDate.parse(values[5]);
    }

    public String getFechaFin() {
        return values[6];
    }

    @Override
    public String toString() {
        return getMotivo();
    }

}
