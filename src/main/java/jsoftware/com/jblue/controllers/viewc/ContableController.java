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

import jsoftware.com.jblue.controllers.AbstractViewController;
import jsoftware.com.jblue.model.constants._Const;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.dtos.OServicePayments;
import jsoftware.com.jblue.util.cache.MemoListCache;
import jsoftware.com.jblue.views.VContabilidad;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        StringBuilder query = new StringBuilder(sum_query);
        query.append("DAY(")
                .append(_Const.PYM_SERVICE_PAYMENTS_TABLE.getFields()[date_register_index])
                .append(") = DAY(NOW())");
        query.append("AND MONTH(")
                .append(_Const.PYM_SERVICE_PAYMENTS_TABLE.getFields()[date_register_index])
                .append(") = MONTH(NOW())");
        query.append("AND YEAR(")
                .append(_Const.PYM_SERVICE_PAYMENTS_TABLE.getFields()[date_register_index])
                .append(") = YEAR(NOW())");
        try {
            ResultSet rs = cache.getConnection().getJDBConnection().query(query.toString());
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
        StringBuilder query = new StringBuilder(sum_query);
        query.append("MONTH(")
                .append(_Const.PYM_SERVICE_PAYMENTS_TABLE.getFields()[date_register_index])
                .append(") = MONTH(NOW())");
        query.append("AND YEAR(")
                .append(_Const.PYM_SERVICE_PAYMENTS_TABLE.getFields()[date_register_index])
                .append(") = YEAR(NOW())");
        try {
            ResultSet rs = cache.getConnection().getJDBConnection().query(query.toString());
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
        StringBuilder query = new StringBuilder(sum_query);
        query.append("YEAR(")
                .append(_Const.PYM_SERVICE_PAYMENTS_TABLE.getFields()[date_register_index])
                .append(") = YEAR(NOW())");
        try {
            ResultSet rs = cache.getConnection().getJDBConnection().query(query.toString());
            if (rs.next()) {
                res = rs.getDouble(1);
                total += res;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.setYear_field(res);
    }
    private final String sum_query = "SELECT SUM(price) FROM pym_service_payments WHERE ";
    private final int date_register_index = 7;

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
