/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.fabricas;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OtherPaymentsType;
import com.jblue.modelo.objetos.OPagosRecargos;
import com.jblue.modelo.objetos.OServicePayments;
import com.jblue.modelo.objetos.OEmployee;
import com.jblue.modelo.objetos.OWaterIntake;
import com.jblue.modelo.objetos.OUser;
import com.jblue.util.cache.MemoListCache;

/**
 *
 * @author jp
 */
public final class CacheFactory {

    public static boolean cache_list;

    public static final MemoListCache<OWaterIntake> WATER_INTAKES_TYPES = new MemoListCache(ConnectionFactory.getWaterIntakes());
    public static final MemoListCache<OCalles> STREETS = new MemoListCache(ConnectionFactory.getStreets());
    public static final MemoListCache<OEmployee> EMPLOYEES = new MemoListCache(ConnectionFactory.getEmployees());
    public static final MemoListCache<OUser> USERS = new MemoListCache(ConnectionFactory.getUser());
    public static final MemoListCache<OServicePayments> SERVICE_PAYMENTS = new MemoListCache(ConnectionFactory.getServicePayments());
    public static final MemoListCache<OPagosRecargos> SURCHARGE_PAYMENTS = new MemoListCache(ConnectionFactory.getSurchargePayments());
    public static final MemoListCache<OtherPaymentsType> OTHER_PAYMENTS = new MemoListCache(ConnectionFactory.getOtherPayments());

    public static final MemoListCache[] CACHES = {
        WATER_INTAKES_TYPES,
        STREETS,
        USERS,
        EMPLOYEES,
        SERVICE_PAYMENTS
    };

    public static boolean loadCaches() {
        cache_list = true;
        for (MemoListCache i : CACHES) {
            i.loadData();
        }
        return cache_list;
    }

    private CacheFactory() {
    }

}
