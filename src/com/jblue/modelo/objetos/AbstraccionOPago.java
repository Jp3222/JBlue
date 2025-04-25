/*
 * Copyright (C) 2025 juanp
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

import java.time.LocalDateTime;

/**
 *
 * @author juanp
 */
public class AbstraccionOPago extends Objeto {

    public AbstraccionOPago() {
    }

    public AbstraccionOPago(String[] info) {
        super(info);
    }

    public String getEmpleado() {
        return info[1];
    }

    public String getUsuario() {
        return info[2];
    }

    public String getCosto() {
        return info[3];
    }

    public String getMesPagado() {
        return info[4];
    }

    public String getEstado() {
        return info[5];
    }

    public String getDateRegister() {
        return info[6];
    }
    
    public int getAño() {
        LocalDateTime o = LocalDateTime.parse(info[6]);
        return o.getYear();
    }

}
