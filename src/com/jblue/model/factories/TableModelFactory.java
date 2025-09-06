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

import com.jblue.model.constants.Const;
import com.jblue.model.constants.Table;
import com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juan-campos
 */
public final class TableModelFactory {

    public static JTableModel getUserTableModel() {
        return getTableModel(Const.USERS);
    }

    public static JTableModel getStreetTableModel() {
        return getTableModel(Const.STREETS);
    }

    public static JTableModel getWaterIntakeTypesTableModel() {
        return getTableModel(Const.WATER_INTAKES_TYPES);
    }

    public static JTableModel getWaterIntakesTableModel() {
        return getTableModel(Const.WATER_INTAKES);
    }

    public static JTableModel getEmployeesTableModel() {
        return getTableModel(Const.EMPLOYEES);
    }

    public static JTableModel getServicePaymentTableModel() {
        return getTableModel(Const.SERVICE_PAYMENTS);
    }

    public static JTableModel getSurchargePaymentTableModel() {
        return getTableModel(Const.SURCHARGE_PAYMENTS);
    }

    public static JTableModel getOtherPaymentTableModel() {
        return getTableModel(Const.OTHER_PAYMENTS);
    }

    private static JTableModel getTableModel(Table table) {
        return new JTableModel(table.getGraphics_field(), 0);
    }
}
