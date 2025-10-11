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
package jsoftware.com.jblue.util;

import jsoftware.com.jblue.model.dtos.OtherPaymentsType;
import jsoftware.com.jblue.model.dtos.OStreet;
import jsoftware.com.jblue.model.dtos.Objects;
import jsoftware.com.jblue.model.dtos.OWaterIntakeTypes;
import jsoftware.com.jblue.model.dtos.OWaterIntakes;
import jsoftware.com.jblue.model.dtos.OUser;
import jsoftware.com.jblue.model.dtos.OEmployee;
import jsoftware.com.jblue.model.dtos.OSurchargePayments;
import jsoftware.com.jblue.model.dtos.OHistory;
import jsoftware.com.jblue.model.dtos.OServicePayments;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.cache.MemoListCache;
import java.lang.reflect.InvocationTargetException;
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

    private static final Map<String, Objects> mapa;

    static {
        mapa = new HashMap<>(10);
        mapa.put(_Const.EMP_EMPLOYEES_TABLE.getTableName(), new OEmployee());
        mapa.put(_Const.USR_USERS_TABLE.getTableName(), new OUser());
        mapa.put(_Const.CAT_STREET_TABLE.getTableName(), new OStreet());
        mapa.put(_Const.WKI_WATER_INTAKE_TYPE_TABLE.getTableName(), new OWaterIntakeTypes());
        mapa.put(_Const.WKI_WATER_INTAKES_TABLE.getTableName(), new OWaterIntakes());
        mapa.put(_Const.PYM_SERVICE_PAYMENTS_TABLE.getTableName(), new OServicePayments());
        mapa.put(_Const.PYM_SURCHARGE_PAYMENTS_TABLE.getTableName(), new OSurchargePayments());
        mapa.put(_Const.PYM_OTHER_PAYMENTS_TABLE.getTableName(), new OtherPaymentsType());
        mapa.put("history", new OHistory());
    }

    public static <T extends Objects> T getObjeto(String tabla, String[] info) {
        Objects clone = new Objects();
        try {
            clone = mapa.get(tabla).clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ObjectUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        clone.setData(info);
        return (T) clone;
    }

    public static <T extends Objects> T getObjeto(Class cls, String[] info) {
        try {
            return (T) cls.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            System.getLogger(ObjectUtils.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return null;
    }

    public static OWaterIntakeTypes getTipoToma(String id) {
        return getObjetoById(CacheFactory.WATER_INTAKES_TYPES, id);
    }

    private static <T extends Objects> T getObjetoById(MemoListCache<T> cache, String id) {
        ArrayList<T> list = cache.getList();
        if (list.isEmpty()) {
            return null;
        }
        return cache.get(o -> o.getId().equals(id));
    }

    public static OStreet getStreedObject(String street_id) {
        if (street_id == null) {
            return null;
        }
        return searchInCacheObject(CacheFactory.STREETS, street_id);
    }

    public static OWaterIntakeTypes getWaterIntakeTypeObject(String water_intakes_id) {
        return searchInCacheObject(CacheFactory.WATER_INTAKES_TYPES, water_intakes_id);
    }

    public static OEmployee getEmployee(String employee_id) {
        return searchInCacheObject(CacheFactory.EMPLOYEES, employee_id);
    }

    public static OUser getUser(String user_id) {
        return searchInCacheObject(CacheFactory.USERS, user_id);
    }

    private static <T extends Objects> T searchInCacheObject(MemoListCache<T> cache, String id) {
        return cache.get((t) -> t.getId().equals(id));
    }

    public static int getIndexEmployeeCAT(String value) {
        return getIndexCAT(value, CacheFactory.EMPLOYEE_TYPE_CAT);
    }
    
    public static String getDescriptionEmployeeTypeCAT(int index){
        return getDescriptionCAT(index, CacheFactory.EMPLOYEE_TYPE_CAT);
    }

    public static int getIndexStatusCAT(String value) {
        return getIndexCAT(value, CacheFactory.ITEMS_STATUS_CAT);
    }
    
    public static String getDescriptionStatusCAT(int index){
        return getDescriptionCAT(index, CacheFactory.ITEMS_STATUS_CAT);
    
    }
    public static String getDescriptionCAT(int index, String[] cataloge) {
        return cataloge[index];
    }

    public static int getIndexCAT(String value, String[] cataloge) {
        for (int i = 0; i < cataloge.length; i++) {
            if (value.equalsIgnoreCase(cataloge[i])) {
                return i;
            }
        }
        return 5;
    }
}
