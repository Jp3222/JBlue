/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.fabricas;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPagosOtros;
import com.jblue.modelo.objetos.OPagosRecargos;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUser;
import com.jblue.util.cache.MemoListCache;

/**
 *
 * @author jp
 */
public abstract class FactoryCache {

    public static boolean cache_list;

    public static final MemoListCache<OPersonal> PERSONAL = new MemoListCache(FactoryConnection.getEmployees());
    public static final MemoListCache<OUser> USUARIOS = new MemoListCache(FactoryConnection.getUser());
    public static final MemoListCache<OTipoTomas> TIPO_DE_TOMAS = new MemoListCache(FactoryConnection.getWaterIntakes());
    public static final MemoListCache<OCalles> CALLES = new MemoListCache(FactoryConnection.getStreets());
    public static final MemoListCache<OPagosServicio> SERVICE_PAYMENTS = new MemoListCache(FactoryConnection.getServicePayments());
    public static final MemoListCache<OPagosRecargos> SURCHARGE_PAYMENTS = new MemoListCache(FactoryConnection.getSurchargePayments());
    public static final MemoListCache<OPagosOtros> OTHER_PAYMENTS = new MemoListCache(FactoryConnection.getOtherPayments());
    public static final MemoListCache[] CACHES = {
        PERSONAL, USUARIOS, TIPO_DE_TOMAS, CALLES
    };

    public static boolean loadCaches() {
        cache_list = true;

        for (MemoListCache i : CACHES) {
            i.loadData();
        }
        return true;
    }
}
