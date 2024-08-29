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
package com.jblue.controlador;

import com.jblue.modelo.factories.FabricaCache;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;

/**
 *
 * @author juan-campos
 */
public class CCobros {

    public static double getTotal(OUsuarios user, int meses_seleccionados) {
        if (user == null) {
            return -1;
        }
        OTipoTomas get = FabricaCache.TIPO_DE_TOMAS.get((t) -> user.getToma().equals(t.getId()));
        double total = meses_seleccionados * get.getCosto();
        return total;
    }
}
