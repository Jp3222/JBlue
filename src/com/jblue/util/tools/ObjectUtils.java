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
import com.jblue.modelo.fabricas.FabricaCache;
import com.jblue.modelo.objetos.*;
import com.jblue.util.cache.MemoListCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juan-campos
 */
public class ObjectUtils {

    private static final Map<String, Objeto> mapa;

    static {
        mapa = new HashMap<>(10);
        mapa.put(ConstBD.TABLAS[0], new OPersonal());
        mapa.put(ConstBD.TABLAS[1], new OUsuarios());
        mapa.put(ConstBD.TABLAS[2], new OCalles());
        mapa.put(ConstBD.TABLAS[3], new OTipoTomas());
        mapa.put(ConstBD.TABLAS[4], new OHisMovimientos());
        mapa.put(ConstBD.TABLAS[5], new OMovimientos());
        mapa.put(ConstBD.TABLAS[6], new OPagosServicio());
        mapa.put(ConstBD.TABLAS[7], new OPagosRecargos());
        mapa.put(ConstBD.TABLAS[8], new OPagosOtros());
        mapa.put(ConstBD.TABLAS[9], new OValores());
    }

    public static Objeto getObjeto(String tabla, String[] info) {
        Objeto clone = mapa.get(tabla).clone();
        clone.setInfo(info);
        return clone;
    }

    public static OTipoTomas getTipoToma(String id) {
        return getObjetoById(FabricaCache.TIPO_DE_TOMAS, id);
    }

    private static <T extends Objeto> T getObjetoById(MemoListCache<T> cache, String id) {
        ArrayList<T> list = cache.getList();
        if (list.isEmpty()) {
            return null;
        }
        return cache.get(o -> o.getId().equals(id));
    }

    public static boolean isPasante(OPersonal usuario) {
        return usuario.getCargo().equals("1");
    }

    public static boolean isSecretario(OPersonal usuario) {
        return usuario.getCargo().equals("2");
    }

    public static boolean isTesorero(OPersonal usuario) {
        return usuario.getCargo().equals("3");
    }

    public static boolean isPresidente(OPersonal usuario) {
        return usuario.getCargo().equals("4");
    }

    public static boolean isAdministrador(OPersonal usuario) {
        return usuario.getCargo().equals("5");
    }

}
