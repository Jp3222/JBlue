/*
 * Copyright (C) 2025 juan-campos
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
package com.jblue.model.constants;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author juan-campos
 */
public class Const implements TableDBConst {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern(TIME_FORMAT);

    public static Table getUSER() {
        USER.setHistoryToFormat("user: %s %s %s");
        return USER;
    }

    public static Table getSTREETS() {
        return STREETS;
    }

    public static Table getWATER_INTAKES() {
        return WATER_INTAKES_TYPES;
    }

    public static Table getEMPLOYEES() {
        return EMPLOYEES;
    }

    public static Table getSERVICE_PAYMENTS() {
        return SERVICE_PAYMENTS;
    }

    public static Table getSURCHARGE_PAYMENTS() {
        return SURCHARGE_PAYMENTS;
    }

    public static Table getOTHER_PAYMENTS() {
        return OTHER_PAYMENTS;
    }

}
