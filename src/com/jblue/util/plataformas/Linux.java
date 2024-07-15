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
package com.jblue.util.plataformas;

import com.jutil.platf.So;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author juan-campos
 */
public class Linux extends SuperOS {

    public Linux(String[] diccionario) {
        super(diccionario);
    }

    @Override
    public String getPathInstalacion() {
        return null;
    }

    @Override
    public String getPathDocumentos() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        sb.append(So.USER_HOME).append(File.separator);
        sb.append(diccionario[0]);
        if (!(new File(sb.toString()).exists())) {
            throw new FileNotFoundException();
        }

        return sb.toString();

    }

    @Override
    public String getPathEscritorio() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
