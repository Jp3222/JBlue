package com.jblue.model.l4b;

import static com.jblue.model.l4b.AbsctractPayment.KEY_ERROR;
import static com.jblue.model.l4b.AbsctractPayment.KEY_MOVS;
import static com.jblue.model.l4b.AbsctractPayment.KEY_STATUS_OP;
import static com.jblue.model.l4b.AbsctractPayment.STATUS_ERR;
import static com.jblue.model.l4b.AbsctractPayment.STATUS_OK;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
/**
 *
 * @author juan-campos
 */
public class SurchargePaymentsLogic extends AbsctractPayment {

    public SurchargePaymentsLogic() {
        super();
        this.pay_query = "UPDATE surcharge_payments SET status = %d".formatted(PaymentModel.STATUS_PAY);
        this.default_query = "INSERT INTO surcharge_payments(employee, user, price, month, status) VALUES %s";
    }

    @Override
    public String getQuery(String args) {
        return "INSERT INTO surcharge_payments(employee, user, price, month) VALUES %s";
    }

    @Override
    public boolean execPayment() {
        deuda = meses_pagados.size() * water_intake_type.getCurrentPrice();

        if (!gameRulers()) {
            return false;
        }
        System.out.println(Arrays.toString(personal.getData()));
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
        }
        col = "('" + personal.getId()
                + "','"
                + usuario.getId() + "','"
                + water_intake_type.getCurrentPrice() + "','"
                + meses_pagados.get(i) + "')";
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

    public void setStatus(int status) {

    }

    @Override
    public boolean gameRulers() {
        return true;
    }

    private boolean isPayed() {
        StringBuilder query = new StringBuilder("SELECT id FROM surcharge_payments WHERE month = %s");
        query.append("AND YEAR(date_register) = YEAR(NOW)");

        try {
            connection.query(query.toString().formatted(""));
        } catch (SQLException ex) {
            Logger.getLogger(SurchargePaymentsLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertToDefault() {
        int i = 0;
        String col;
        StringBuilder values = new StringBuilder(600);
        while (i < meses_pagados.size() - 1) {
            col = "('" + personal.getId()
                    + "','"
                    + usuario.getId() + "','"
                    + water_intake_type.getCurrentPrice() + "','"
                    + meses_pagados.get(i)
                    + PaymentModel.STATUS_NOT_PAY + "')";
            i++;
            values.append(col).append(",");
        }
        col = "('" + personal.getId()
                + "','"
                + usuario.getId() + "','"
                + water_intake_type.getCurrentPrice() + "','"
                + meses_pagados.get(i) + "')";
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

}
