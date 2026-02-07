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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsoftware.com.jutil.platf.So;

/**
 *
 * @author juan pablo campos casasanero
 */
public abstract class OsConfig implements OsConfigModel {

    // Dentro de OsConfig.java, actualiza estos métodos:
    public static final OsConfig getDefaultOsConfig() {
        if (So.isLinux()) {
            return getLinuxConfig();
        }
        if (So.isMac()) {
            return getMacConfig();
        }
        return getWindowsConfig();
    }

    public static final OsConfig getWindowsConfig() {
        return new Windows();
    }

    public static final OsConfig getLinuxConfig() {
        return new Linux(); // Ahora sí devuelve Linux
    }

    public static final OsConfig getMacConfig() {
        return new MacOs(); // Ahora sí devuelve Mac
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
