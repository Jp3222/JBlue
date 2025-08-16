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
package com.jblue.sys;

import com.jblue.sys.app.AppFiles;
import com.jutil.framework.LaunchApp;
import com.jutil.jexception.JExcp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author juanp
 */
public class SystemLogs {

    public static final String SYS_LOG = "sys.log";
    public static final String MOD_LOG = "mod.log";
    public static final String DB_LOG = "db.log";
    public static final String UKW_LOG = "ukw.log";

    public static PrintWriter getFile(int file) {
        String fil = switch (file) {
            case 1:
                yield AppFiles.DIR_PROG_ARC_SYS_LOG;
            case 2:
                yield AppFiles.DIR_PROG_ARC_MOD_LOG;
            case 3:
                yield AppFiles.DIR_PROG_ARC_DB_LOG;
            default:
                yield AppFiles.DIR_PROG_ARC_UKW_LOG;
        };
        try {
            return new PrintWriter(new File(fil));
        } catch (FileNotFoundException ex) {
            JExcp.getInstance(false, (boolean) LaunchApp.getInstance().getResources("sys_flag_logs"))
                    .print(ex, SystemLogs.class, "getFile");
        }
        return null;
    }

    public static FileHandler getFileHander(int file) {
        String fil = switch (file) {
            case 1:
                yield AppFiles.DIR_PROG_ARC_SYS_LOG;
            case 2:
                yield AppFiles.DIR_PROG_ARC_MOD_LOG;
            case 3:
                yield AppFiles.DIR_PROG_ARC_DB_LOG;
            default:
                yield AppFiles.DIR_PROG_ARC_UKW_LOG;
        };
        try {
            return new FileHandler(fil, true);
        } catch (IOException | SecurityException ex) {
            JExcp.getInstance(false, (boolean) LaunchApp.getInstance().getResources("sys_flag_logs"))
                    .print(ex, SystemLogs.class, "getFileHander");
        }
        return null;
    }

    public static void infoSysLogs(String msg) {
        logs(Level.INFO, SYS_LOG, msg, null);
    }

    public static void severeSysLogs(String msg, Throwable thr) {
        logs(Level.SEVERE, SYS_LOG, msg, thr);
    }

    public static void warningSysLogs(String msg, Throwable thr) {
        logs(Level.SEVERE, SYS_LOG, msg, thr);
    }

    public static void infoModLogs(String msg) {
        logs(Level.INFO, MOD_LOG, msg, null);
    }

    public static void severeModLogs(String msg, Throwable thr) {
        logs(Level.SEVERE, MOD_LOG, msg, thr);
    }

    public static void warningModLogs(String msg, Throwable thr) {
        logs(Level.SEVERE, MOD_LOG, msg, thr);
    }

    public static void infoDbLogs(String msg) {
        logs(Level.INFO, DB_LOG, msg, null);
    }

    public static void severeDbLogs(String msg, Throwable thr) {
        logs(Level.SEVERE, DB_LOG, msg, thr);
    }

    public static void warningDbLogs(String msg, Throwable thr) {
        logs(Level.SEVERE, DB_LOG, msg, thr);
    }

    public static void infoUkwLogs(String msg) {
        logs(Level.INFO, UKW_LOG, msg, null);
    }

    public static void severeUkwLogs(String msg, Throwable thr) {
        logs(Level.SEVERE, UKW_LOG, msg, thr);
    }

    public static void warningUkwLogs(String msg, Throwable thr) {
        logs(Level.SEVERE, UKW_LOG, msg, thr);
    }

    static void logs(Level level, String name, String msg, Throwable thr) {

        int file = switch (msg) {
            case SYS_LOG:
                yield 1;
            case MOD_LOG:
                yield 2;
            case DB_LOG:
                yield 3;
            default:
                yield 0;
        };
        FileHandler fh = getFileHander(file);
        //
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
        //
        Logger log = Logger.getLogger(name);
        log.addHandler(fh);
        log.setLevel(Level.ALL);
        log.setUseParentHandlers((boolean) LaunchApp.getInstance().getResources("sys_flag_logs"));

        if (thr != null) {
            log.log(level, msg, thr);
            thr.printStackTrace(getFile(file));
        } else {
            log.log(level, msg);
        }

    }

}
