/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.fabricas;

import com.jblue.modelo.objetos.OStreet;
import com.jblue.modelo.objetos.OtherPaymentsType;
import com.jblue.modelo.objetos.OSurchargePayments;
import com.jblue.modelo.objetos.OServicePayments;
import com.jblue.modelo.objetos.OEmployee;
import com.jblue.modelo.objetos.OWaterIntake;
import com.jblue.modelo.objetos.OUser;
import com.jblue.sistema.Sistema;
import com.jblue.util.cache.MemoListCache;
import com.jutil.dbcon.connection.DBConnection;
import com.jutil.framework.LaunchApp;
import com.jutil.jexception.JExcp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author jp
 */
public final class CacheFactory {

    public static boolean cache_list;

    public static final MemoListCache<OWaterIntake> WATER_INTAKES_TYPES = new MemoListCache(ConnectionFactory.getWaterIntakes());
    public static final MemoListCache<OStreet> STREETS = new MemoListCache(ConnectionFactory.getStreets());
    public static final MemoListCache<OEmployee> EMPLOYEES = new MemoListCache(ConnectionFactory.getEmployees());

    public static final MemoListCache<OUser> USERS = new MemoListCache(ConnectionFactory.getUser());
    public static final MemoListCache<OServicePayments> SERVICE_PAYMENTS = new MemoListCache(ConnectionFactory.getServicePayments());
    public static final MemoListCache<OSurchargePayments> SURCHARGE_PAYMENTS = new MemoListCache(ConnectionFactory.getSurchargePayments());
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
        loadCataloges((DBConnection) LaunchApp.getInstance().getResources(Sistema.DATA_BASE_KEY));
        for (MemoListCache i : CACHES) {
            i.loadData();
        }
        return cache_list;
    }
    public static String[] USER_TYPES_CAT;
    public static String[] HISTORY_TYPES_CAT;
    public static String[] ITEMS_STATUS_CAT;
    public static final String COUNT_FORMAT = "SELECT COUNT(%s) FROM %s";

    public static boolean loadCataloges(DBConnection connection) {
        int utc_size = sizeCat(connection, "id", "user_type");
        int htc_size = sizeCat(connection, "id", "history_type_mov");
        int isc_size = sizeCat(connection, "id", "items_status");
        USER_TYPES_CAT = new String[utc_size];
        USER_TYPES_CAT = readCat(connection, utc_size, "user_type", "user_type", "date_finalize IS NULL");
        System.out.println(Arrays.toString(USER_TYPES_CAT));

        HISTORY_TYPES_CAT = new String[htc_size];
        HISTORY_TYPES_CAT = readCat(connection, htc_size, "name_mov", "history_type_mov", "status NOT IN(3,8)");
        System.out.println(Arrays.toString(HISTORY_TYPES_CAT));
        ITEMS_STATUS_CAT = new String[isc_size];
        ITEMS_STATUS_CAT = readCat(connection, isc_size, "description", "items_status", "date_finalize IS NULL");
        System.out.println(Arrays.toString(ITEMS_STATUS_CAT));
        return USER_TYPES_CAT != null
                && HISTORY_TYPES_CAT != null
                && ITEMS_STATUS_CAT != null;
    }

    private static String[] readCat(DBConnection connection, int size, String field, String table, String where) {
        try {
            ResultSet rs = connection.query("SELECT %s FROM %s WHERE %s".formatted(field, table, where));
            String[] cat = new String[size + 1];
            System.out.println("size: " + size);
            cat[0] = "desconocido";
            int i = 1;
            while (rs.next()) {
                cat[i] = rs.getString(1);
                System.out.println(cat[i]);
                i++;
            }
            rs.close();
            System.out.println(Arrays.toString(cat));
            return cat;
        } catch (SQLException e) {
            JExcp.getInstance(false, true).print(e, CacheFactory.class, "readCat");
        }
        return null;
    }

    private static int sizeCat(DBConnection connection, String field, String table) {
        try (ResultSet query = connection.query(COUNT_FORMAT.formatted(field, table))) {
            if (query.next()) {
                return query.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return -1;
    }

    private CacheFactory() {
    }

}
