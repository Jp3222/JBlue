/*
 * Copyright (C) 2025 juan pablo campos casasanero
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
import java.sql.SQLException;
import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jblue.model.dao.HysHistoryDAO;
import jsoftware.com.jblue.model.exp.PaymentExeption;
import jsoftware.com.jblue.util.PaymentsRulers;
import jsoftware.com.jblue.views.components.UserMessagesFactory;

public class ServicePaymentLogic extends AbstractPayment {

    private static final long serialVersionUID = 1L;

    public ServicePaymentLogic() {
        super();
    }

    ServicePaymentLogic(PaymentBuilder builder, int type_payment) {
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
        boolean res = false;
        if (!gameRulers()) {
            return res;
        }
        try {
            //SE DEHABILITA EL AUTOCOMMIT
            connection.setAutoCommit(false);
            //SE CALCULA EL TOTAL BASE
            this.total_cost = PaymentsRulers.calculateBaseTotal(
                    month_paid_list.size(),
                    new BigDecimal(water_intake_type.getCurrentPrice())
            );
            // SE REGISTRA EL PAGO Y SE GENERA EL ID
            int key = payment(PaymentModel.EFECTIVO, PAGADO);
            res = key > 0;
            if (!res) {
                throw new PaymentExeption(1001, "EL PAGO NO SE GENERO CORRECTAMENTE", "REGISTRO CORRUPTO");
            }
            //SE REGISTRA LA LISTA DE ITEMS PAGADOS
            res = payments_list_dao.insertPaymentList(
                    connection,
                    key,
                    month_paid_list,
                    new BigDecimal(water_intake_type.getCurrentPrice()),
                    PAGADO
            );
            if (!res) {
                throw new PaymentExeption(1001, "LA LISTA DE CONCEPTOS NO SE REGISTRO CORRECTAMENTE", "REGISTRO CORRUPTO");
            }
            //SE REGISTRA EN EL HISTORIAL
            res = HysHistoryDAO.getINSTANCE().insert(
                    Const.INDEX_PYM_PAYMENT,
                    "SE GENERO EL PAGO: %s".formatted(key)
            );
            if (!res) {
                throw new SQLException("REGISTRO EN BITACORA CORRUPTO");
            }
            //SE GUARDAN LOS CAMBIOS HECHOS, FIN DE LA TRANSACCION

        } catch (PaymentExeption ex) {
            UserMessagesFactory.showPaymentErr(null, ex.getUserMessage());
            connection.rollBack();
            System.getLogger(ServicePaymentLogic.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SQLException ex) {
            connection.rollBack();
            System.getLogger(ServicePaymentLogic.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (Exception ex) {
            connection.rollBack();
            System.getLogger(ServicePaymentLogic.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
            connection.commit();
            connection.setAutoCommit(true);
        }
        return res;
    }

    /**
     * Metodo que inserta pagos con status No Pagados
     *
     * @return
     */
    @Override
    public boolean insertToDefault() {
        return payment(EFECTIVO, PENDIENTE) > 0;
    }

}
