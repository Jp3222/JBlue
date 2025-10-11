/*
 * Copyright (C) 2024 juan-campos
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
import java.io.File;

/**
 * Esta clase esta pensaba para la creacion de funciones que ayuden a la
 * extraccion de archivos multimedia.
 *
 * @author juan-campos
 */
public class MediaUtils {

    /**
     *
     * @param id
     * @param name
     * @return
     */
    public static File getUserPhoto(String id, String name) {
        File file = AppFiles.FIL_DIR_PROG_USUARIOS;
        File photo = new File(file, buildPath(id, name));
        return photo;
    }

    private static String buildPath(String... items) {
        StringBuilder sb = new StringBuilder(items.length * items[0].length());
        int i = 0;
        while (i < items.length - 1) {
            sb.append(items[i]).append(File.separator);
            i++;
        }
        sb.append(items[i]);
        return sb.toString();
    }
}
