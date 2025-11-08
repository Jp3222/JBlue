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

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.daos.HysHistoryDAO;

public class ServicePaymentLogic extends AbsctractPayment {

    public ServicePaymentLogic() {
        super();
        this.pay_query = "INSERT INTO " + _Const.PYM_SERVICE_PAYMENTS_TABLE.getTableName() + " (employee, user, price, month_name) values %s";
    }

    ServicePaymentLogic(PaymentBuilder.Builder builder, int type_payment) {
        this.input_money = builder.getInput_money();
        this.type_payment = type_payment;
        this.user = builder.getUser();
        this.water_intake = builder.getWater_intake();
        this.water_intake_type = builder.getWater_intake_type();
        this.pay_query = builder.getPay_query();

    }

    @Override
    public String getQuery(String args) {
        return pay_query.formatted(args);
    }

    @Override
    public boolean gameRulers() {
        mov.put(KEY_STATUS_OP, STATUS_OK);
        if (month_paid_list.isEmpty()) {
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
        if (!month_paid_list.isEmpty() && deuda == 0.0) {
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
        deuda = month_paid_list.size() * water_intake_type.getCurrentPrice();

        if (!gameRulers()) {
            return false;
        }
        StringBuilder values = new StringBuilder();
        int i = 0;
        String col;
        while (i < month_paid_list.size() - 1) {
            col = "('" + current_employee.getId()
                    + "','"
                    + user.getId() + "','"
                    + water_intake_type.getCurrentPrice() + "','"
                    + month_paid_list.get(i) + "')";
            i++;
            values.append(col).append(",");

            mov_book.append(i).append(" - ")
                    .append(month_paid_list.get(i))
                    .append(" : ")
                    .append(water_intake_type.getCurrentPrice())
                    .append("\n");
        }
        col = "('" + current_employee.getId()
                + "','"
                + user.getId() + "','"
                + water_intake_type.getCurrentPrice() + "','"
                + month_paid_list.get(i) + "')";

        mov_book.append(i).append(" - ")
                .append(month_paid_list.get(i))
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
                            user.getId(),
                            user.getName(),
                            user.getLastName1(),
                            user.getLastName2(),
                            month_paid_list.toString()//
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
        return true;
    }

}
