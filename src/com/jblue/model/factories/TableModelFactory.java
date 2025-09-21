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
package com.jblue.model.factories;

import com.jblue.model.constants._Const;
import com.jutil.dbcon.tb.JDBTable;
import com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juan-campos
 */
public final class TableModelFactory {

    public static JTableModel getUserTableModel() {
        return getTableModel(_Const.USR_USERS_TABLE);
    }

    public static JTableModel getStreetTableModel() {
        return getTableModel(_Const.CAT_STREET_TABLE);
    }

    public static JTableModel getWaterIntakeTypesTableModel() {
        return getTableModel(_Const.WKI_WATER_INTAKE_TYPE_TABLE);
    }

    public static JTableModel getWaterIntakesTableModel() {
        return getTableModel(_Const.WKI_WATER_INTAKES_TABLE);
    }

    public static JTableModel getEmployeesTableModel() {
        return getTableModel(_Const.EMP_EMPLOYEES_TABLE);
    }

    public static JTableModel getServicePaymentTableModel() {
        return getTableModel(_Const.PYM_SERVICE_PAYMENTS_TABLE);
    }

    public static JTableModel getSurchargePaymentTableModel() {
        return getTableModel(_Const.PYM_SURCHARGE_PAYMENTS_TABLE);
    }

    public static JTableModel getOtherPaymentTableModel() {
        return getTableModel(_Const.PYM_OTHER_PAYMENTS_TABLE);
    }

    private static JTableModel getTableModel(JDBTable table) {
        return new JTableModel(table.getGraphicsField(), 0);
    }
}
