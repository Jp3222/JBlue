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

import com.jblue.modelo.constdb.Const;
import com.jblue.modelo.dbconexion.JDBConnection;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPagosOtros;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUser;

/**
 *
 * @author juan-campos
 */
public class FactoryConnection {

    public static JDBConnection<OPersonal> getEmployees() {
        return new JDBConnection(Const.EMPLOYEES);
    }

    public static JDBConnection<OUser> getUser() {
        return new JDBConnection(Const.USER);
    }

    public static JDBConnection<OCalles> getStreets() {
        return new JDBConnection(Const.STREETS);
    }

    public static JDBConnection<OTipoTomas> getWaterIntakes() {
        return new JDBConnection(Const.WATER_INTAKES);
    }

    public static JDBConnection<OPagosServicio> getServicePayments() {
        return new JDBConnection(Const.SERVICE_PAYMENTS);
    }

    public static JDBConnection<OPagosServicio> getSurchargePayments() {
        return new JDBConnection(Const.SURCHARGE_PAYMENTS);
    }

    public static JDBConnection<OPagosOtros> getOtherPayments() {
        return new JDBConnection(Const.OTHER_PAYMENTS);
    }

}
