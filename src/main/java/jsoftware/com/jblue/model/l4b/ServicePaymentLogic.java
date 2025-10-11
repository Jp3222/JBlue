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
package jsoftware.com.jblue.model.l4b;

import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.daos.HysHistoryDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicePaymentLogic extends AbsctractPayment {

    public ServicePaymentLogic() {
        super();
        this.pay_query = "INSERT INTO " + _Const.PYM_SERVICE_PAYMENTS_TABLE.getTableName() + " (employee, user, price, month_name) values %s";
        this.default_query = "INSERT INTO service_payments (employee, user, price, month_name, status) values %s";
    }

    @Override
    public String getQuery(String args) {
        return pay_query.formatted(args);
    }

    @Override
    public boolean gameRulers() {
        mov.put(KEY_STATUS_OP, STATUS_OK);
        if (meses_pagados.isEmpty()) {
            mov.put(KEY_ERROR, "NO HAY MESES SELECCIONADOS");
            mov.put(KEY_STATUS_OP, STATUS_ERR);
        }
        if (isPersonalNull()) {
            mov.put(KEY_ERROR, "ERROR INTERNO");
            mov.put(KEY_STATUS_OP, STATUS_ERR);
        }
        if (isUserNull()) {
            mov.put(KEY_ERROR, "ERROR INTERNO");
            mov.put(KEY_STATUS_OP, STATUS_ERR);
        }
        if (isWaterIntakeNull()) {
            mov.put(KEY_ERROR, "ERROR INTERNO");
            mov.put(KEY_STATUS_OP, STATUS_ERR);
        }
        if (!meses_pagados.isEmpty() && deuda == 0.0) {
            mov.put(KEY_ERROR, "ERROR INTERNO");
            mov.put(KEY_STATUS_OP, STATUS_ERR);
        }
        if (isMontoMenor()) {
            mov.put(KEY_ERROR, "EL DINERO INGRESADO ES MENOR A LA DEUDA");
            mov.put(KEY_STATUS_OP, STATUS_ERR);
        }
        return mov.get(KEY_STATUS_OP).equals(STATUS_OK);
    }

    public boolean isHasSurcharge() {
        return true;
    }

    @Override
    public boolean execPayment() {
        deuda = meses_pagados.size() * water_intake_type.getCurrentPrice();

        if (!gameRulers()) {
            return false;
        }
        StringBuilder values = new StringBuilder();
        int i = 0;
        String col;
        while (i < meses_pagados.size() - 1) {
            col = "('" + personal.getId()
                    + "','"
                    + usuario.getId() + "','"
                    + water_intake_type.getCurrentPrice() + "','"
                    + meses_pagados.get(i) + "')";
            i++;
            values.append(col).append(",");

            mov_book.append(i).append(" - ")
                    .append(meses_pagados.get(i))
                    .append(" : ")
                    .append(water_intake_type.getCurrentPrice())
                    .append("\n");
        }
        col = "('" + personal.getId()
                + "','"
                + usuario.getId() + "','"
                + water_intake_type.getCurrentPrice() + "','"
                + meses_pagados.get(i) + "')";

        mov_book.append(i).append(" - ")
                .append(meses_pagados.get(i))
                .append(" : ")
                .append(water_intake_type.getCurrentPrice())
                .append("\n");
        i++;
        values.append(col);
        mov.put(KEY_MOVS, values.toString());
        try {
            connection.getConnection().setAutoCommit(false);
            connection.execute(getQuery(values.toString()));
            mov.put(KEY_STATUS_OP, STATUS_OK);
            HysHistoryDAO.getINSTANCE().insert(_Const.INDEX_PYM_SERVICE_PAYMENTS,
                    "PAGO DEL USUARIO: %s - %s %s %s, PAGO LOS MESES:%s".formatted(
                            usuario.getId(),
                            usuario.getName(),
                            usuario.getLastName1(),
                            usuario.getLastName2(),
                            meses_pagados.toString()
                    ));
            connection.getConnection().commit();
        } catch (SQLException ex) {
            try {
                connection.getConnection().rollback();
            } catch (SQLException ex1) {
                mov.put(KEY_STATUS_OP, STATUS_ERR);
                mov.put(KEY_ERROR, ex.getMessage());
                System.getLogger(ServicePaymentLogic.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex1);
            }
            mov.put(KEY_STATUS_OP, STATUS_ERR);
            mov.put(KEY_ERROR, ex.getMessage());
            Logger.getLogger(ServicePaymentLogic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                mov.put(KEY_STATUS_OP, STATUS_ERR);
                mov.put(KEY_ERROR, ex.getMessage());
                System.getLogger(ServicePaymentLogic.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return mov.get(KEY_STATUS_OP).equals(STATUS_OK);
    }

    /**
     * Metodo que inserta pagos con status No Pagados
     *
     * @return
     */
    @Override
    public boolean insertToDefault() {
        StringBuilder values = new StringBuilder();
        int i = 0;
        String col;
        while (i < meses_pagados.size() - 1) {
            col = "('" + personal.getId()
                    + "','"
                    + usuario.getId() + "','"
                    + water_intake_type.getCurrentPrice() + "','"
                    + meses_pagados.get(i)
                    + PaymentModel.STATUS_NOT_PAY + "')";
            i++;
            values.append(col).append(",");

            mov_book.append(i).append(" - ")
                    .append(meses_pagados.get(i))
                    .append(" : ")
                    .append(water_intake_type.getCurrentPrice())
                    .append("\n");
        }
        col = "('" + personal.getId()
                + "','"
                + usuario.getId() + "','"
                + water_intake_type.getCurrentPrice() + "','"
                + meses_pagados.get(i) + "')";

        mov_book.append(i).append(" - ")
                .append(meses_pagados.get(i))
                .append(" : ")
                .append(water_intake_type.getCurrentPrice())
                .append("\n");
        i++;
        values.append(col);
        mov.put(KEY_MOVS, values.toString());
        try {
            connection.execute(default_query.formatted(values.toString()));
            mov.put(KEY_STATUS_OP, STATUS_OK);
        } catch (SQLException ex) {
            mov.put(KEY_STATUS_OP, STATUS_ERR);
            mov.put(KEY_ERROR, ex.getMessage());
            Logger.getLogger(ServicePaymentLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mov.get(KEY_STATUS_OP).equals(STATUS_OK);
    }

}
