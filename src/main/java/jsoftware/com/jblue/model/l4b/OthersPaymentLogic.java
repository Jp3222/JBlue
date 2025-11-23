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

import java.math.BigDecimal;
import static jsoftware.com.jblue.model.l4b.PaymentModel.KEY_ERROR;
import static jsoftware.com.jblue.model.l4b.PaymentModel.KEY_STATUS_OP;
import static jsoftware.com.jblue.model.l4b.PaymentModel.STATUS_ERR;
import static jsoftware.com.jblue.model.l4b.PaymentModel.STATUS_OK;

/**
 *
 * @author juan-campos
 */
public class OthersPaymentLogic extends AbstractPayment {

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
        if (!month_paid_list.isEmpty() && total_cost.equals(BigDecimal.ZERO)) {
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
        return mov.get(KEY_STATUS_OP).equals(STATUS_OK);
    }

    @Override
    public boolean insertToDefault() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
