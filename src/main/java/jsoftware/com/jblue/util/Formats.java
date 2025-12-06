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
package jsoftware.com.jblue.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juanp
 */
public class Formats {

    public static String TIME_FORMAT = "hh:mm:ss";
    public static String DATE_FORMAT = "dd-MM-yyyy";
    public static String DATE_TIME_FORMAT = "dd-MM-yyyy hh:mm:ss";

    public static LocalDateTime getLocalDateTime(String date) {
        if (Filters.isNullOrBlank(date)) {
            return null;
        }
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    }

    public static LocalDate getLocalDate(String date) {
        if (Filters.isNullOrBlank(date)) {
            return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }

    public static LocalTime getLocalTime(String time) {
        if (Filters.isNullOrBlank(time)) {
            return null;
        }
        return LocalTime.parse(time, DateTimeFormatter.ISO_TIME);
    }

    public static String getLocalDateTime(LocalDateTime date) {
        if (Filters.isNull(date)) {
            return null;
        }
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    public static String getLocalDate(LocalDateTime date) {
        if (Filters.isNull(date)) {
            return null;
        }
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    public static String getLocalTime(LocalDateTime time) {
        if (Filters.isNull(time)) {
            return null;
        }
        return time.format(DateTimeFormatter.ISO_DATE);
    }

    public static String[] getDBFormatInputArray(String... args) {
        String a;
        ArrayList<String> list = new ArrayList<>(args.length);
        for (String i : args) {
            a = i.trim().replace(" ", "_").toUpperCase();
            a = "'".concat(a).concat("'");
            list.add(a);
        }
        return list.toArray(args);
    }

    public static Map<String, String> getDBFormatInputMap(String[] keys, String[] values) {
        Map<String, String> map = new HashMap<>(keys.length);
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], getTextFormat(values[i]));
        }
        return map;
    }

    public static Map<String, String> getDBFormatInputMap(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            entry.setValue(getTextFormat(entry.getValue()));
        }
        return map;
    }

    public static String getTextFormat(String o) {
        return o.trim().replace(" ", "_").toUpperCase();
    }

    public static String[] getInsertFormats(Map<String, String> map) {
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            fields.append(entry.getKey()).append(",");
            values.append("'").append(entry.getValue()).append("',");
        }
        String[] info = new String[2];
        info[0] = fields.substring(0, fields.length() - 1);
        info[1] = values.substring(0, values.length() - 1);
        return info;
    }

    public static String getUpdateFormats(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        String aux;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            aux = UtilsConstants.UPDATE_COL_FORMAT.formatted(entry.getKey(), entry.getValue());
            sb.append(aux).append(",");
        }

        return sb.substring(0, sb.length() - 1);
    }
}
