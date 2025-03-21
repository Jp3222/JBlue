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
import com.jblue.modelo.constdb.Const;
import com.jblue.modelo.fabricas.FactoryCache;
import com.jblue.modelo.objetos.*;
import com.jblue.util.cache.MemoListCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan-campos
 */
public class ObjectUtils {

    private static final Map<String, Objeto> mapa;

    static {
        mapa = new HashMap<>(10);
        mapa.put(ConstBD.TABLAS[0], new OPersonal());
        mapa.put(Const.EMPLOYEES.getTable(), new OPersonal());
        mapa.put(ConstBD.TABLAS[1], new OUsuarios());
        mapa.put(Const.USER.getTable(), new OUsuarios());
        mapa.put(ConstBD.TABLAS[2], new OCalles());
        mapa.put(Const.STREETS.getTable(), new OCalles());
        mapa.put(ConstBD.TABLAS[3], new OTipoTomas());
        mapa.put(Const.WATER_INTAKES.getTable(), new OTipoTomas());
        mapa.put(ConstBD.TABLAS[4], new OHisMovimientos());
        mapa.put(ConstBD.TABLAS[5], new OMovimientos());
        mapa.put(ConstBD.TABLAS[6], new OPagosServicio());
        //mapa.put(Const., new OPagosServicio());
        mapa.put(ConstBD.TABLAS[7], new OPagosRecargos());
        mapa.put(ConstBD.TABLAS[8], new OPagosOtros());
        mapa.put(ConstBD.TABLAS[9], new OValores());
    }

    public static Objeto getObjeto(String tabla, String[] info) {
        Objeto clone = new Objeto();
        try {
            clone = mapa.get(tabla).clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ObjectUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        clone.setInfo(info);
        return clone;
    }

    public static OTipoTomas getTipoToma(String id) {
        return getObjetoById(FactoryCache.TIPO_DE_TOMAS, id);
    }

    private static <T extends Objeto> T getObjetoById(MemoListCache<T> cache, String id) {
        ArrayList<T> list = cache.getList();
        if (list.isEmpty()) {
            return null;
        }
        return cache.get(o -> o.getId().equals(id));
    }

    public static boolean isPasante(OPersonal usuario) {
        return usuario.getType().equals("1");
    }

    public static boolean isSecretario(OPersonal usuario) {
        return usuario.getType().equals("2");
    }

    public static boolean isTesorero(OPersonal usuario) {
        return usuario.getType().equals("3");
    }

    public static boolean isPresidente(OPersonal usuario) {
        return usuario.getType().equals("4");
    }

    public static boolean isAdministrador(OPersonal usuario) {
        return usuario.getType().equals("5");
    }

    public static String getStreed(String calle) {
        return searchInCache(FactoryCache.CALLES, calle);
    }

    public static String getWaterIntakes(String toma) {
        return searchInCache(FactoryCache.TIPO_DE_TOMAS, toma);
    }

    private static <T extends Objeto> String searchInCache(MemoListCache<T> cache, String id) {
        return cache.get(o -> o.getId().equals(id)).toString();
    }
}
