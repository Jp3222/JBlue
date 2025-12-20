package jsoftware.com.jblue.model.l4b;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


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
/**
 *
 * @author juan pablo campos casasanero
 */
public class SurchargePaymentsLogic extends AbstractPayment {

    private static final long serialVersionUID = 1L;

    public SurchargePaymentsLogic() {
        super();
    }

    @Override
    public boolean execPayment() {

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

        return mov.get(KEY_STATUS_OP).equals(STATUS_OK);
    }

}
