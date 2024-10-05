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
package com.jblue.controlador;

import com.jblue.modelo.dbconexion.FuncionesBD;
import com.jblue.modelo.fabricas.FabricaFuncionesBD;
import com.jblue.modelo.objetos.OUsuarios;

/**
 *
 * @author juan-campos
 */
public class CUsuarios {

    public static final int OK = 100;
    public static final int ERR = 100;
    public static final int UNKNOW = 100;
    public static final int NOT_ALLOWED = 100;

    public static void save(OUsuarios user, String[] data) {
        if (user == null) {
            save(data);
        } else {
            update(user.getId(), data);
        }
    }

    private static void save(String[] data) {
        FuncionesBD<OUsuarios> conexion = FabricaFuncionesBD.getUsuarios();
        boolean insert = conexion.insert(data);
        if (insert) {
            
        }
    }

    private static void update(String id, String[] data) {

    }

    public static void message(int status_code) {
        if (true) {

        }
    }
}
