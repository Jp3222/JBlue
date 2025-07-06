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

import com.jblue.modelo.constdb.Const;
import com.jblue.modelo.fabricas.CacheFactory;
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
        mapa.put(Const.EMPLOYEES.getTable(), new OEmployee());
        mapa.put(Const.USER.getTable(), new OUser());
        mapa.put(Const.STREETS.getTable(), new OStreet());
        mapa.put(Const.WATER_INTAKES.getTable(), new OWaterIntake());
        mapa.put(Const.SERVICE_PAYMENTS.getTable(), new OServicePayments());
        mapa.put(Const.SURCHARGE_PAYMENTS.getTable(), new OPagosRecargos());
        mapa.put(OPagosRecargos.class.getName(), new OServicePayments());
        mapa.put(OServicePayments.class.getName(), new OPagosRecargos());
        mapa.put(Const.OTHER_PAYMENTS.getTable(), new OtherPaymentsType());

    }

    public static <T extends Objeto> T getObjeto(String tabla, String[] info) {
        Objeto clone = new Objeto();
        try {
            clone = mapa.get(tabla).clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ObjectUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        clone.setInfo(info);
        return (T) clone;
    }

    public static OWaterIntake getTipoToma(String id) {
        return getObjetoById(CacheFactory.WATER_INTAKES_TYPES, id);
    }

    private static <T extends Objeto> T getObjetoById(MemoListCache<T> cache, String id) {
        ArrayList<T> list = cache.getList();
        if (list.isEmpty()) {
            return null;
        }
        return cache.get(o -> o.getId().equals(id));
    }

    public static boolean isPasante(OEmployee usuario) {
        return usuario.getType().equals("1");
    }

    public static boolean isSecretario(OEmployee usuario) {
        return usuario.getType().equals("2");
    }

    public static boolean isTesorero(OEmployee usuario) {
        return usuario.getType().equals("3");
    }

    public static boolean isPresidente(OEmployee usuario) {
        return usuario.getType().equals("4");
    }

    public static boolean isAdministrador(OEmployee usuario) {
        return usuario.getType().equals("5");
    }

    public static String getStreed(String street_id) {
        return searchInCache(CacheFactory.STREETS, street_id);
    }

    public static OStreet getStreedObject(String street_id) {
        return searchInCacheObject(CacheFactory.STREETS, street_id);
    }

    public static String getWaterIntakes(String water_intakes_id) {
        return searchInCache(CacheFactory.WATER_INTAKES_TYPES, water_intakes_id);
    }

    public static OWaterIntake getWaterIntakesObject(String water_intakes_id) {
        return searchInCacheObject(CacheFactory.WATER_INTAKES_TYPES, water_intakes_id);
    }

    public static OEmployee getEmployee(String employee_id) {
        return searchInCacheObject(CacheFactory.EMPLOYEES, employee_id);
    }

    public static OUser getUser(String user_id) {
        System.out.println("users: " + CacheFactory.USERS.getList().isEmpty());
        return searchInCacheObject(CacheFactory.USERS, user_id);
    }

    private static <T extends Objeto> String searchInCache(MemoListCache<T> cache, String id) {
        return cache.get(o -> o.getId().equals(id)).toString();
    }

    private static <T extends Objeto> T searchInCacheObject(MemoListCache<T> cache, String id) {
        return cache.get((t) -> t.getId().equals(id));
    }

}
