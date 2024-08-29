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
package com.jblue.util.tools;

import com.jblue.modelo.ConstBD;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OHisMovimientos;
import com.jblue.modelo.objetos.OPagosOtros;
import com.jblue.modelo.objetos.OPagosRecargos;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.OValores;
import com.jblue.modelo.absobj.Objeto;
import com.jutil.jexception.Excp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public class ObjetoUtil implements UtilPersonal, UtilUsuario, UtilValores {

    private static final Map<String, Objeto> map = new HashMap<>(10);

    static {
        map.put(ConstBD.TABLAS[0], new OPersonal());
        map.put(ConstBD.TABLAS[1], new OUsuarios());
        map.put(ConstBD.TABLAS[2], new OCalles());
        map.put(ConstBD.TABLAS[3], new OTipoTomas());
        map.put(ConstBD.TABLAS[4], new OHisMovimientos());
        map.put(ConstBD.TABLAS[6], new OPagosServicio());
        map.put(ConstBD.TABLAS[7], new OPagosRecargos());
        map.put(ConstBD.TABLAS[8], new OPagosOtros());
        map.put(ConstBD.TABLAS[9], new OValores());
    }

    public static Objeto getObjeto(String nombre, String[] info) {
        Objeto o = new Objeto();
        try {
            o = (Objeto) map.get(nombre).clone();
        } catch (CloneNotSupportedException ex) {
            Excp.impTerminal(ex, Object.class, true);
        }
        o.setInfo(info);
        return o;
    }

}
