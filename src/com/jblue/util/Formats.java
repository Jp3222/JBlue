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
package com.jblue.util;

import java.util.ArrayList;

/**
 *
 * @author juanp
 */
public class Formats {

    public static String TIME_FORMAT = "hh:mm:ss";
    public static String DATE_FORMAT = "dd-MM-yyyy";
    public static String DATE_TIME_FORMAT = "dd-MM-yyyy hh:mm:ss";

    public static String[] getDBFormatInputArray(String... args) {
        String a;
        ArrayList<String> list = new ArrayList<>(args.length);
        for (String i : args) {
            a = i.trim().replace(" ", "_").toUpperCase();
            list.add(a);
        }
        return list.toArray(args);
    }
}
