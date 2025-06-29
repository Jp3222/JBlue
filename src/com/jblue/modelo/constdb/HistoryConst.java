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
package com.jblue.modelo.constdb;

/**
 *
 * @author juanp
 */
interface HistoryConst {

    /**
     * INSERT_TO_USER UPDATE_TO_USER LOGIC_DELETE_TO_USER DELETE_TO_USER
     * EXPORT_TO_USER IMPORT_TO_USER
     */
    public final static int USER_INSERT = 1;
    public final static int USER_UPDATE = 2;
    public final static int USER_DELETE = 3;
    public final static int USER_EXPORT = 4;
    public final static int USER_IMPORT = 5;

}
