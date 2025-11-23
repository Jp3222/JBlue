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
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsoftware.com.jblue.controllers.AbstractViewController;
import jsoftware.com.jblue.model.dto.OServicePayments;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jblue.views.VContabilidad;

/**
 *
 * @author juanp
 */
public final class ContableController extends AbstractViewController {

    private MemoListCache<OServicePayments> cache;
    private VContabilidad view;
    private double total;

    public ContableController(VContabilidad view) {
        cache = CacheFactory.SERVICE_PAYMENTS;
        this.view = view;
        load();
    }

    public void load() {
        this.totalOfDay();
        this.totalOfMonth();
        this.totalOfYear();
        view.setTotal_field(total);
    }

    private void totalOfDay() {
        double res = 0;
        String query = sum_total(true, true);
        try {
            ResultSet rs = cache.getConnection().getJDBConnection().query(query);
            if (rs.next()) {
                res = rs.getDouble(1);
                total += res;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.setDay_field(res);
    }

    private void totalOfMonth() {
        double res = 0;
        String query = sum_total(true, false);
        try {
            ResultSet rs = cache.getConnection().getJDBConnection().query(query);
            if (rs.next()) {
                res = rs.getDouble(1);
                total += res;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.setMonth_field(res);
    }

    private void totalOfYear() {
        double res = 0;
        String query = sum_total(false, false);
        try {
            ResultSet rs = cache.getConnection().getJDBConnection().query(query);
            if (rs.next()) {
                res = rs.getDouble(1);
                total += res;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.setYear_field(res);
    }

    public String sum_total(boolean month, boolean day) {
        StringBuilder sb = new StringBuilder("SELECT SUM(price) FROM pym_service_payments WHERE ");
        sb.append("YEAR(date_register) = YEAR(NOW()) \n");
        if (month) {
            sb.append("MONTH(date_register) = YEAR(NOW()) \n");
        }
        if (day) {
            sb.append("DAY(date_register) = YEAR(NOW())");
        }
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
