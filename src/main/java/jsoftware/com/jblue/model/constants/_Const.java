/*
 * Copyright (C) 2025 juanp
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
package jsoftware.com.jblue.model.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author juanp
 */
public class _Const extends _ConstTables {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static LocalDate parseDate(String dateString) {
        return LocalDate.parse(dateString, _Const.DATE);
    }

    public static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, _Const.DATE_TIME);
    }

    public static String getLocalDateToString(LocalDate date) {
        if (date == null) {
            return "";
        }
        // Formatea el objeto LocalDate usando el patrón de fecha.
        return date.format(_Const.DATE);
    }

    public static String getLocalDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        // Formatea el objeto LocalDateTime usando el patrón de fecha y hora.
        return dateTime.format(_Const.DATE_TIME);
    }
    public static int INDEX_INSERT = 1;
    public static int INDEX_UPDATE = 2;
    public static int INDEX_DELETE = 3;
    public static int INDEX_SELECT = 4;
    public static int INDEX_EXPORT = 5;
    public static int INDEX_IMPORT = 6;
    public static int INDEX_LOGIN = 7;
    public static int INDEX_LOGOUT = 8;
    public static int INDEX_LOGIC_DELETE = 9;

    public static int INDEX_CAT_HISTORY_TYPE_MOV = 1;
    public static int INDEX_CAT_STATUS = 2;
    public static int INDEX_CAT_STREET = 3;
    public static int INDEX_CAT_TABLES_DB = 4;
    public static int INDEX_CAT_USER_MOVEMENTS = 5;
    public static int INDEX_DEV_PARAMETERS = 6;
    public static int INDEX_EMP_EMPLOYEE_TYPES = 7;
    public static int INDEX_EMP_EMPLOYEES = 8;
    public static int INDEX_HYS_HISTORY = 9;
    public static int INDEX_HYS_PROGRAM_HISTORY = 10;
    public static int INDEX_LOGBOOK_TO_PAYMENTS = 11;
    public static int INDEX_LOGBOOK_TO_USERS = 12;
    public static int INDEX_LOGBOOK_TO_WATER_INTAKES = 13;
    public static int INDEX_LOGBOOK_TO_WATER_INTAKES_TYPE = 14;
    public static int INDEX_PRO_CONTRACT_PROCEDURE = 15;
    public static int INDEX_PYM_OTHER_PAYMENTS = 16;
    public static int INDEX_PYM_OTHER_PAYMENTS_TYPE = 17;
    public static int INDEX_PYM_PROCEDURE_PAYMENTS = 18;
    public static int INDEX_PYM_SERVICE_PAYMENTS = 19;
    public static int INDEX_PYM_SURCHARGE_PAYMENTS = 20;
    public static int INDEX_USR_USER_TYPE = 21;
    public static int INDEX_USR_USERS = 22;
    public static int INDEX_USR_USERS_CONSUMERS = 23;
    public static int INDEX_WKI_WATER_INTAKE_TYPE = 24;
    public static int INDEX_WKI_WATER_INTAKES = 25;
    public static int INDEX_PRO_CHANGE_OWNER = 26;
    public static int INDEX_CAT_PROCESS_TYPE = 27;

}
