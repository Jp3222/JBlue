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

import jsoftware.com.jblue.sys.app.AppFiles;
import jsoftware.com.jutil.jexception.JExcpWritter;

/**
 *
 * @author juanp
 */
public class LoggerRegister {

    private static final String LOG = AppFiles.DIR_PROG_LOG;

    public static void logInfoWriter(String module_name, Class cls, String method, String msg) {
        logWriter(module_name, JExcpWritter.INFO, cls, null, method, msg);
    }

    public static void logErrorWriter(String module_name, Class cls, Throwable error, String method) {
        logWriter(module_name, JExcpWritter.ERROR, cls, error, method, error.getMessage());
    }

    public static void logWarning(String module_name, String type, Class cls, String method, String msg) {
        logWriter(module_name, JExcpWritter.WARNING, cls, null, method, msg);
    }

    public static void logWriter(String module_name, String type, Class cls, Throwable error, String method, String msg) {
        JExcpWritter.getInstance(LOG, module_name).registerLog(type, cls, error, method, msg);
    }
}
