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
package com.jblue.util.tools;

import com.jblue.modelo.objetos.OPersonal;
import com.jblue.sistema.app.AppFiles;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author juan-campos
 */
public class ArchivosPersonal {

    public static File crearCarpeta(OPersonal personal) throws IOException {
        String personal_url = personal.getId();
        String fil_dir_perosnal = AppFiles.FIL_DIR_PROG_PERSONAL.getAbsolutePath();

        StringBuilder sb = new StringBuilder(fil_dir_perosnal);
        sb.append(File.separator);
        sb.append(personal_url);
        File personal_file = new File(sb.toString());

        if (!personal_file.exists() && !personal_file.mkdir()) {
            throw new IOException("Error al crear el archivo");
        }

        return personal_file;
    }

}
