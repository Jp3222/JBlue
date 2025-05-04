/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.fabricas;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPagosOtros;
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

    public static final MemoListCache<OEmployee> PERSONAL = new MemoListCache(ConnectionFactory.getEmployees());
    public static final MemoListCache<OUser> USUARIOS = new MemoListCache(ConnectionFactory.getUser());
    public static final MemoListCache<OWaterIntake> TIPO_DE_TOMAS = new MemoListCache(ConnectionFactory.getWaterIntakes());
    public static final MemoListCache<OCalles> CALLES = new MemoListCache(ConnectionFactory.getStreets());
    public static final MemoListCache<OServicePayments> SERVICE_PAYMENTS = new MemoListCache(ConnectionFactory.getServicePayments());
    public static final MemoListCache<OPagosRecargos> SURCHARGE_PAYMENTS = new MemoListCache(ConnectionFactory.getSurchargePayments());
    public static final MemoListCache<OPagosOtros> OTHER_PAYMENTS = new MemoListCache(ConnectionFactory.getOtherPayments());

    public static final MemoListCache[] CACHES = {
        PERSONAL, USUARIOS, TIPO_DE_TOMAS, CALLES, SERVICE_PAYMENTS
    };

    public static boolean loadCaches() {
        cache_list = true;
        for (MemoListCache i : CACHES) {
            i.loadData();
        }
        return true;
    }

    private CacheFactory() {
    }

}
