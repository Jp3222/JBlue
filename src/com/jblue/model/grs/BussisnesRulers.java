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

import com.jblue.model.dtos.AbstractPayments;
import com.jblue.model.dtos.OEmployee;
import com.jblue.model.dtos.OUser;
import com.jutil.dbcon.connection.DBConnection;

/**
 *
 * @author juanp
 */
public final class BussisnesRulers {

    public static int SERVICE_PAYMENT = 1;
    public static int SURCHARGE_PAYMENT = 2;
    public static int OTHER_PAYMENT = 3;

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
    public static int existPendingPayment(DBConnection con, OUser o) {
        if (existPendingPayment4Service(con, o)) {
            return SERVICE_PAYMENT;
        }
        if (existPendingPayment4Surcharge(con, o)) {
            return SURCHARGE_PAYMENT;
        }
        if (existPendingPayment4Others(con, o)) {
            return OTHER_PAYMENT;
        }
        return -1;
    }

    public static boolean existPendingPayment4Service(DBConnection con, OUser o) {
        return false;
    }

    public static boolean existPendingPayment4Surcharge(DBConnection con, OUser o) {
        return false;
    }

    public static boolean existPendingPayment4Others(DBConnection con, OUser o) {
        return false;
    }

    public static String getStatusDescription(DBConnection o, AbstractPayments p) {
        String[] arr = {
            "ACTIVO",
            "INACTIVO",
            "ELIMINADO",
            "PAGADO",
            "NO PAGADO",
            "PENDIENTE",
            "FINALIZADO"
        };
        return arr[p.getStatus()];
    }
}
