/*
 * Copyright (C) 2025 juan-campos
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
package com.jblue.modelo.fabricas;

import com.jblue.modelo.ConstBD;
import com.jblue.modelo.dbconexion.DBConnection;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;

/**
 *
 * @author juan-campos
 */
public class FactoryConnection {

    public static DBConnection<OPersonal> getEmployees() {
        return new DBConnection(ConstBD.TABLAS[0], ConstBD.TABLA_PERSONAL);
    }

    public static DBConnection<OUsuarios> getUser() {
        return new DBConnection(ConstBD.TABLAS[1], ConstBD.TABLA_USUARIOS);
    }

    public static DBConnection<OCalles> getStreets() {
        return new DBConnection(ConstBD.TABLAS[2], ConstBD.TABLA_CALLES);
    }

    public static DBConnection<OTipoTomas> getWaterIntakes() {
        return new DBConnection(ConstBD.TABLAS[3], ConstBD.TABLA_TIPOS_DE_TOMAS);
    }

}
