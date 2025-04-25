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
package com.jblue.sistema.console;

import com.jblue.sistema.DevFlags;

/**
 *
 * @author juanp
 */
public class ReadConsole {

    public static void setFlags(String key, String value) {
        switch (key) {
            case "ALL_FLAGS":
                DevFlags.ALL_FLAGS = Boolean.parseBoolean(value);
                break;
            case "DEV_VEW_NEWS":
                DevFlags.DEV_VEW_NEWS = Boolean.parseBoolean(value);
                break;
            case "DEV_MSG_CODE":
                DevFlags.DEV_MSG_CODE = Boolean.parseBoolean(value);
                break;
            case "DEV_MSG_DATA_BASE":
                DevFlags.DEV_MSG_DATA_BASE = Boolean.parseBoolean(value);
                break;
            case "DEV_EXE_FUNCION":
                DevFlags.DEV_EXE_FUNCION = Boolean.parseBoolean(value);
                break;
            case "TST_EXE_FUNCION":
                DevFlags.TST_EXE_FUNCION = Boolean.parseBoolean(value);
                break;
            case "CAN_EXE_FUNCION":
                DevFlags.CAN_EXE_FUNCION = Boolean.parseBoolean(value);
                break;
            default:
                throw new AssertionError();
        }
    }

}
