/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.model.factories;

import com.jblue.model.constants._Const;
import com.jblue.model.dtos.OStreet;
import com.jblue.model.dtos.OtherPaymentsType;
import com.jblue.model.dtos.OSurchargePayments;
import com.jblue.model.dtos.OServicePayments;
import com.jblue.model.dtos.OEmployee;
import com.jblue.model.dtos.OEmployeeTypes;
import com.jblue.model.dtos.OWaterIntakeTypes;
import com.jblue.model.dtos.OUser;
import com.jblue.model.dtos.OWaterIntakes;
import com.jblue.sys.JBlueMainSystem;
import com.jblue.util.cache.MemoListCache;
import com.jutil.dbcon.connection.JDBConnection;
import com.jutil.framework.LaunchApp;
import com.jutil.jexception.JExcp;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jp
 */
public final class CacheFactory {

    public static String[] CAT_GENDER = {"DESCONOCIDO", "NO DEFINIDO", "MASCULINO", "FEMENINO"};
    public static boolean cache_list;
    public static String[] USER_TYPES_CAT;
    public static String[] HISTORY_TYPES_CAT;
    public static String[] ITEMS_STATUS_CAT;
    public static String[] EMPLOYEE_TYPE_CAT;

    //
    public static final String COUNT_FORMAT = "SELECT COUNT(%s) FROM %s";

    private static String[] readCat(JDBConnection connection, int size, String field, String table, String where) {
        String[] cat;
        String query = "SELECT %s FROM %s WHERE %s".formatted(field, table, where);
        try (ResultSet rs = connection.query(query)) {
            cat = new String[size + 1];
            cat[0] = "DESCONOCIDO";
            int i = 1;
            while (rs.next()) {
                cat[i] = rs.getString(1);
                i++;
            }
            return cat;
        } catch (SQLException e) {
            JExcp.getInstance(false, true).print(e, CacheFactory.class, "readCat");
        }
        return null;
    }

    private static int sizeCat(JDBConnection connection, String field, String table) {
        try (ResultSet query = connection.query(COUNT_FORMAT.formatted(field, table))) {
            if (query.next()) {
                return query.getInt(1);
            }
        } catch (SQLException e) {
            JExcp.getInstance(false, true).print(e, CacheFactory.class, "sizeCat");
        }
        return -1;
    }

    public static boolean loadCataloges(JDBConnection connection) {
        int usr_size = sizeCat(connection, "id", _Const.USR_USER_TYPE_TABLE.getTableName());
        int hys_size = sizeCat(connection, "id", _Const.CAT_HISTORY_TYPE_MOV_TABLE.getTableName());
        int sts_size = sizeCat(connection, "id", _Const.CAT_STATUS_TABLE.getTableName());
        int emp_size = sizeCat(connection, "id", _Const.EMP_EMPLOYEE_TYPES_TABLE.getTableName());

        USER_TYPES_CAT = new String[usr_size];
        USER_TYPES_CAT = readCat(connection, usr_size, "user_type", _Const.USR_USER_TYPE_TABLE.getTableName(), "date_finalize IS NULL");

        HISTORY_TYPES_CAT = new String[hys_size];
        HISTORY_TYPES_CAT = readCat(connection, hys_size, "description", _Const.CAT_HISTORY_TYPE_MOV_TABLE.getTableName(), "status NOT IN(3,20)");

        ITEMS_STATUS_CAT = new String[sts_size];
        ITEMS_STATUS_CAT = readCat(connection, sts_size, "description", _Const.CAT_STATUS_TABLE.getTableName(), "date_finalize IS NULL");

        EMPLOYEE_TYPE_CAT = new String[emp_size];
        EMPLOYEE_TYPE_CAT = readCat(connection, emp_size, "employee_type", _Const.EMP_EMPLOYEE_TYPES_TABLE.getTableName(), "status != 3");
        return USER_TYPES_CAT != null
                && HISTORY_TYPES_CAT != null
                && ITEMS_STATUS_CAT != null;
    }

    public static final MemoListCache<OEmployeeTypes> EMPLOYEE_TYPES = new MemoListCache(ConnectionFactory.getEmployeeTypes());
    public static final MemoListCache<OWaterIntakeTypes> WATER_INTAKES_TYPES = new MemoListCache(ConnectionFactory.getWaterIntakesTypes());
    public static final MemoListCache<OStreet> STREETS = new MemoListCache(ConnectionFactory.getStreets());
    public static final MemoListCache<OEmployee> EMPLOYEES = new MemoListCache(ConnectionFactory.getEmployees());
    public static final MemoListCache<OUser> USERS = new MemoListCache(ConnectionFactory.getUser());
    public static final MemoListCache<OWaterIntakes> WATER_INTAKES = new MemoListCache(ConnectionFactory.getWaterIntakes());

    public static final MemoListCache<OServicePayments> SERVICE_PAYMENTS = new MemoListCache(ConnectionFactory.getServicePayments());
    public static final MemoListCache<OSurchargePayments> SURCHARGE_PAYMENTS = new MemoListCache(ConnectionFactory.getSurchargePayments());
    public static final MemoListCache<OtherPaymentsType> OTHER_PAYMENTS = new MemoListCache(ConnectionFactory.getOtherPayments());

    public static final MemoListCache[] CACHES = {
        //EMPLOYEE_TYPES,
        WATER_INTAKES_TYPES,
        STREETS,
        EMPLOYEES,
        USERS,
        WATER_INTAKES,
        SERVICE_PAYMENTS,
        SURCHARGE_PAYMENTS,
        OTHER_PAYMENTS,};

    public static boolean loadCaches() {
        cache_list = true;
        loadCataloges((JDBConnection) LaunchApp.getInstance().getResources(JBlueMainSystem.DATA_BASE_KEY));
        for (MemoListCache i : CACHES) {
            i.loadData();
        }
        return cache_list;
    }

    private CacheFactory() {
    }

}
