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
package com.jblue.model.l4b;

import com.jblue.sys.app.AppConfig;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ServicePaymentLogic extends AbsctractPayment {

    public ServicePaymentLogic() {
        super();
        this.pay_query = "INSERT INTO service_payments (employee, user, price, month_name) values %s";
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
        deuda = meses_pagados.size() * toma.getPrice();

        if (!gameRulers()) {
            return false;
        }
        LocalDate now = LocalDate.now();
        if (AppConfig.isAutoPay() && (now.getDayOfMonth() > AppConfig.getPayDay())) {
            JOptionPane.showMessageDialog(null, "La fecha limite de pago ha sido excedida.¿Desea aplicarle un recargo a este usuario?");
            PaymentModel surcharge = PaymentFactory.getSurchargePayment();
            surcharge.insertToDefault();
            surcharge.setUsuario(usuario);
            surcharge.setDineroIngresado(meses_pagados.size() * toma.getSurcharge());
        }
        StringBuilder values = new StringBuilder();
        int i = 0;
        String col;
        while (i < meses_pagados.size() - 1) {
            col = "('" + personal.getId()
                    + "','"
                    + usuario.getId() + "','"
                    + toma.getPrice() + "','"
                    + meses_pagados.get(i) + "')";
            i++;
            values.append(col).append(",");

            mov_book.append(i).append(" - ")
                    .append(meses_pagados.get(i))
                    .append(" : ")
                    .append(toma.getPrice())
                    .append("\n");
        }
        col = "('" + personal.getId()
                + "','"
                + usuario.getId() + "','"
                + toma.getPrice() + "','"
                + meses_pagados.get(i) + "')";

        mov_book.append(i).append(" - ")
                .append(meses_pagados.get(i))
                .append(" : ")
                .append(toma.getPrice())
                .append("\n");
        i++;
        values.append(col);
        mov.put(KEY_MOVS, values.toString());
        try {
            connection.execute(getQuery(values.toString()));
            mov.put(KEY_STATUS_OP, STATUS_OK);
        } catch (SQLException ex) {
            mov.put(KEY_STATUS_OP, STATUS_ERR);
            mov.put(KEY_ERROR, ex.getMessage());
            Logger.getLogger(ServicePaymentLogic.class.getName()).log(Level.SEVERE, null, ex);
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
                    + toma.getPrice() + "','"
                    + meses_pagados.get(i)
                    + PaymentModel.STATUS_NOT_PAY + "')";
            i++;
            values.append(col).append(",");

            mov_book.append(i).append(" - ")
                    .append(meses_pagados.get(i))
                    .append(" : ")
                    .append(toma.getPrice())
                    .append("\n");
        }
        col = "('" + personal.getId()
                + "','"
                + usuario.getId() + "','"
                + toma.getPrice() + "','"
                + meses_pagados.get(i) + "')";

        mov_book.append(i).append(" - ")
                .append(meses_pagados.get(i))
                .append(" : ")
                .append(toma.getPrice())
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
