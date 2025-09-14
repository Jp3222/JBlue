/*
 * Copyright (C) 2023 jp
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
package com.jblue.sys.app;

import com.jblue.model.constants._Const;
import com.jutil.dbcon.connection.JDBConnection;
import com.jutil.framework.LaunchApp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public final class AppConfig {

    //
    public static final String DB_USER = "DB USER";
    public static final String DB_PASSWORD = "DB PASSWORD";
    public static final String DB_URL = "DB URL";
    public static final String DB_MOTOR = "DB MOTOR";
    public static final String DB_PORT = "DB PORT";
    public static final String DB_HOST = "DB HOST";
    public static final String DB_NAME = "DB NAME";

    public static final String[] DB_KEYS = {
        DB_USER, DB_PASSWORD, DB_HOST, DB_MOTOR, DB_NAME, DB_PORT
    };
    //
    public static final String TITLE1 = "TITLE 1";
    public static final String TITLE2 = "TITLE 2";
    public static final String LOGIN_ICON = "LOGIN ICON";
    //
    public static final String HOUR_OPEN = "HOUR OPEN";
    public static final String HOUR_CLOSE = "HOUR CLOSE";

    public static String getMaterUser() {
        return String.valueOf(getParameter("USUARIO_MAESTRO"));
    }

    public static String getMaterPassword() {
        return String.valueOf(getParameter("CONTRASEÑA_MAESTRA"));
    }

    public static LocalTime getOpenHour() {
        Object valueOf = getParameter("HORA_DE_APERTURA");
        if (valueOf == null) {
            return null;
        }
        String hour = String.valueOf(valueOf);
        return LocalTime.parse(hour, DateTimeFormatter.ofPattern("kk:mm:ss"));
    }

    public static LocalTime getCloseHour() {
        Object valueOf = getParameter("HORA_DE_CIERRE");
        if (valueOf == null) {
            return null;
        }
        String hour = String.valueOf(valueOf);
        return LocalTime.parse(hour, DateTimeFormatter.ofPattern("kk:mm:ss"));
    }

    public static boolean isWorkTime() {
        LocalTime a = getOpenHour(),
                b = getCloseHour(),
                o = LocalTime.now();

        if (a == null || b == null) {
            return false;
        }
        boolean hour_validate = isHourValidate();
        if (hour_validate) {
            return o.isAfter(a) && o.isBefore(b);
        }
        return true;
    }

    public static int getPayDay() {
        Object parameter = getParameter("ULTIMO_DIA_DE_PAGO");
        if (parameter == null) {
            return -1;
        }
        return Integer.parseInt(String.valueOf(parameter));
    }

    public static boolean isPayDay() {
        LocalDate o = LocalDate.now();
        if (getPayDay() <= 0) {
            return false;
        }
        return getPayDay() > o.getDayOfMonth();
    }

    public static boolean isAutoPay() {
        Object valueOf = getParameter("COBRO_AUTOMATICO");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isHourValidate() {
        Object valueOf = getParameter("VALIDAR_HORA_DE_ENTRADA");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isDevMessages() {
        Object valueOf = getParameter("MENSAJES_DEV");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isDbMessages() {
        Object valueOf = getParameter("MENSAJES_DB");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isTestMessages() {
        Object valueOf = getParameter("MENSAJES_TEST");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isDevFunction() {
        Object valueOf = getParameter("FUNCIONES_DEV");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isTestFunction() {
        Object valueOf = getParameter("FUNCIONES_TEST");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isLogsDev() {
        Object valueOf = getParameter("LOGS_DEV");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isLogsTest() {
        Object valueOf = getParameter("LOGS_TEST");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static boolean isLogsDB() {
        Object valueOf = getParameter("LOGS_DB");
        if (valueOf == null) {
            return false;
        }
        return Boolean.parseBoolean(String.valueOf(valueOf));
    }

    public static LocalDate getReferenceDate() {
        Object value = getParameter("FECHA_DE_INICIO");
        if (value == null) {
            return LocalDate.now();
        }
        return LocalDate.parse((CharSequence) value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static LocalDate getDataBaseDate() {
        Object value = getParameter("FECHA_DEL_PROGRAMA");
        if (value == null) {
            return LocalDate.now();
        }
        return LocalDate.parse((CharSequence) value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private static Object getParameter(String name) {
        try {
            JDBConnection connection = (JDBConnection) LaunchApp.getInstance().getResources("connection");
            String query = "SELECT value,data_type FROM %s WHERE parameter = '%s' AND status = 1"
                    .formatted(_Const.DEV_PARAMETERS_NAME, name);
            ResultSet rs = connection.query(query);
            if (rs.next()) {
                return rs.getObject(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private AppConfig() {

    }
}
