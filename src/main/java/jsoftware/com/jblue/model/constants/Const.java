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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import jsoftware.com.jblue.model.constants.des.ConstDES;

/**
 *
 * @author juanp
 */
public class Const extends ConstDES {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE);
    }

    public static LocalTime parseTime(String time) {
        return LocalTime.parse(time, TIME);
    }

    public static LocalDateTime parseDateTime(String date_time) {
        return LocalDateTime.parse(date_time, DATE_TIME);
    }

    public static String parseLocalDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        // Formatea el objeto LocalDate usando el patrón de fecha.
        return date.format(DATE);
    }

    public static String parseLocalTime(LocalTime time) {
        if (time == null) {
            return "";
        }
        // Formatea el objeto LocalDate usando el patrón de fecha.
        return time.format(TIME);
    }

    public static String parseLocalDateTime(LocalDateTime date_time) {
        if (date_time == null) {
            return "";
        }
        // Formatea el objeto LocalDate usando el patrón de fecha.
        return date_time.format(DATE_TIME);
    }

    public static String getLocalDateTimeToString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        // Formatea el objeto LocalDateTime usando el patrón de fecha y hora.
        return dateTime.format(DATE_TIME);
    }

}
