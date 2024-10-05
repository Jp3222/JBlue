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
package com.jblue.sistema;

import com.jblue.media.JBMedia;
import com.jutil.jbd.conexion.Conexion;

/**
 *
 * @author juan-campos
 */
public class SistemaR {

    private static Conexion cn;

    public static JBMedia getMedia() {
        return JBMedia.getInstance();
    }

    static void setConexion(Conexion cn) {
        SistemaR.cn = cn;
    }

    public static Conexion getConexion() {
        return cn;
    }
    
    
}
