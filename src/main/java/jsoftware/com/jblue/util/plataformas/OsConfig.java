/*
 * Copyright (C) 2024 juan pablo campos casasanero
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
package jsoftware.com.jblue.util.plataformas;

import jsoftware.com.jutil.platf.So;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan pablo campos casasanero
 */
public abstract class OsConfig implements OsConfigModel {

    public static final OsConfig getDefaultOsConfig() {
        if (So.isLinux()) {
            return getLinuxConfing();
        }
        if (So.isMac()) {
            return getMacConfing();
        }
        return getWindowsConfing();
    }

    public static final OsConfig getWindowsConfing() {
        return new Windows();
    }

    public static final OsConfig getLinuxConfing() {
        return new Windows();
    }

    public static final OsConfig getMacConfing() {
        return new Windows();
    }
    protected String documents_path[],
            desktop_path[];

    public OsConfig() {
        documents_path = new String[]{
            So.USER_HOME + "\\OneDrive\\Documents",
            So.USER_HOME + "\\OneDrive\\Documentos",
            So.USER_HOME + "\\Documents",
            So.USER_HOME + "\\Documentos"
        };
        desktop_path = new String[]{
            So.USER_HOME + "\\OneDrive\\Desktop",
            So.USER_HOME + "\\OneDrive\\Escritorio",
            So.USER_HOME + "\\Desktop",
            So.USER_HOME + "\\Escritorio"
        };

    }

    @Override
    public String getPathInstalacion() throws FileNotFoundException {
        File file;
        System.out.println(So.USER_HOME);
        for (String i : documents_path) {
            file = new File(i);
            if (file.exists()) {
                return file.getAbsolutePath();
            }
        }
        return So.USER_HOME;
    }

    @Override
    public String getPathDocumentos() throws FileNotFoundException {
        File file;
        for (String i : documents_path) {
            file = new File(i);
            System.out.println(file.getPath() + " : " + file.exists());
            if (file.exists()) {
                return i;
            }
        }
        return So.USER_HOME;
    }

    @Override
    public String getPathEscritorio() throws FileNotFoundException {
        File file;
        for (String i : desktop_path) {
            file = new File(i);
            if (file.exists()) {
                return i;
            }
        }
        return So.USER_HOME;
    }

    public String getDocumentos() {
        try {
            return getPathDocumentos();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OsConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
