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


/**
 *
 * @author juan-campos
 */
public class OthersPaymentLogic extends AbsctractPayment {

    @Override
    public String getQuery(String args) {
        return "INSERT INTO service_others (employee, user, price, month) values %s"
                .formatted(args);
    }

    @Override
    public boolean gameRulers() {
        mov.put(KEY_STATUS_OP, STATUS_OK);
        if (this.month_paid_list.isEmpty()) {
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
            connection.execute(getQuery(values.toString()));
            mov.put(KEY_STATUS_OP, STATUS_OK);
        } catch (SQLException ex) {
            mov.put(KEY_STATUS_OP, STATUS_ERR);
            mov.put(KEY_ERROR, ex.getMessage());
            Logger.getLogger(ServicePaymentLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mov.get(KEY_STATUS_OP).equals(STATUS_OK);
    }

    @Override
    public boolean insertToDefault() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
