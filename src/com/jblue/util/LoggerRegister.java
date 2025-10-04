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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author juanp
 */
public class LoggerRegister {

    public static LoggerRegister instance;

    public static synchronized LoggerRegister getInstance(String path, String module_name) {
        if (instance == null) {
            instance = getInstance(path, module_name);
        }
        return instance;
    }

    private final String path;
    private final String module_name;

    private LoggerRegister(String path, String module_name) {
        this.path = path;
        this.module_name = module_name;

    }

    public void registerLog(Class cls, Throwable error, String method, String msg) {
        StringBuilder sb = new StringBuilder(10000);
        sb.append("Error {");
        
        if (cls != null) {

        }
        if (error != null) {
            putStackTrace(sb, error);
        }
        sb.append("}");
        String file_name = module_name + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
        writterLog(file_name, sb.toString());
    }

    private void putStackTrace(StringBuilder sb, Throwable e) {
        for (StackTraceElement i : e.getStackTrace()) {
            sb.append("     ").append(i.getClassName());
            sb.append(": ").append(i.getMethodName());
            sb.append("(line: ").append(i.getLineNumber()).append(")\n");
        }
    }

    private void writterLog(String file_name, String txt) {
        File file = createFile(file_name);
        try (OutputStream os = new FileOutputStream(file, true); BufferedOutputStream bos = new BufferedOutputStream(os);) {
            bos.write(txt.getBytes());
            os.flush();
            bos.flush();
        } catch (IOException e) {
            System.getLogger(LoggerRegister.class.getName()).log(System.Logger.Level.ERROR, "ESCRITURA ROTA");
        }
    }

    private File createFile(String file_name) {
        File file = null;
        boolean rt = false;
        try {
            file = new File(path);
            rt = file.exists() || file.createNewFile();
            return rt ? file : null;
        } catch (IOException e) {
            System.getLogger(LoggerRegister.class.getName()).log(System.Logger.Level.ERROR, "ARCHIVO CORRUPTO");
        }
        return file;
    }
}
