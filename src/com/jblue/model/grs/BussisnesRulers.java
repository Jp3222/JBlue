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
package com.jblue.model.grs;

import com.jblue.model.constants._Const;
import com.jblue.model.dtos.AbstractPayments;
import com.jblue.model.dtos.OEmployee;
import com.jblue.model.dtos.OUser;
import com.jblue.model.factories.CacheFactory;
import com.jutil.dbcon.connection.JDBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author juanp
 */
public final class BussisnesRulers {

    /**
     * metodo para comprobar si un usuario es nulo
     *
     * @param o objeto usuario
     * @return true si el objeto es null en otro caso es false
     */
    public static boolean isNullUser(OUser o) {
        return o == null;
    }

    /**
     * metodo para comprobar si un objeto empleado es null
     *
     * @param o objeto empleado
     * @return true si el objeto es null en otro caso es false
     */
    public static boolean isNullEmployee(OEmployee o) {
        return o == null;
    }

    /**
     * Metodo para comprobar algun pago pendiente
     * <br> la conexion proporcionada no se cierra
     *
     * @param con conexion a la base de datos
     * @param o objeto usuario al que se le buscara un pago
     * @return 1 si el pago es de servicios, 2 si el pago es por un recargo, 3
     * si es otro tipo de pagos
     */
    public static int existPendingPayment(JDBConnection con, OUser o) {
        if (existPendingPayment4Service(con, o)) {
            return _Const.INDEX_PYM_SERVICE_PAYMENTS;
        }
        if (existPendingPayment4Surcharge(con, o)) {
            return _Const.INDEX_PYM_SURCHARGE_PAYMENTS;
        }
        if (existPendingPayment4Others(con, o)) {
            return _Const.INDEX_PYM_OTHER_PAYMENTS;
        }
        return -1;
    }

    public static boolean existPendingPayment4Service(JDBConnection con, OUser o) {
        return existPendingPayment(con, o, _Const.PYM_SERVICE_PAYMENTS_TABLE.getTableName());
    }

    public static boolean existPendingPayment4Surcharge(JDBConnection con, OUser o) {
        return existPendingPayment(con, o, _Const.PYM_SURCHARGE_PAYMENTS_TABLE.getTableName());
    }

    public static boolean existPendingPayment4Others(JDBConnection con, OUser o) {
        return existPendingPayment(con, o, _Const.PYM_OTHER_PAYMENTS_TABLE.getTableName());
    }

    private static boolean existPendingPayment(JDBConnection con, OUser o, String table) {
        LocalDate ld = LocalDate.now();
        String query = "SELECT * FROM %s WHERE user = %s AND YEAR(NOW()) = %s AND MONTH(NOW()) = %s";
        query = query.formatted(
                table,//tabla de pago
                ld.getYear(), // año actual
                ld.getMonthValue() // mes actual
        );

        try (ResultSet rs = con.query(query)) {
            return rs.next();
        } catch (SQLException ex) {
            System.getLogger(BussisnesRulers.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return false;
    }

    public static String getStatusDescription(AbstractPayments p) {
        return CacheFactory.ITEMS_STATUS_CAT[p.getStatus()];
    }

}
