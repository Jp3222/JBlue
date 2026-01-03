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
package jsoftware.com.jblue.model.factories;

import jsoftware.com.jblue.model.constants.Const;
import jsoftware.com.jutil.db.JDBTable;
import jsoftware.com.jutil.swingw.modelos.JTableModel;

/**
 *
 * @author juan pablo campos casasanero
 */
public final class TableModelFactory {

    public static JTableModel getUserTableModel() {
        return getTableModel(Const.USR_USER_TABLE);
    }

    public static JTableModel getStreetTableModel() {
        return getTableModel(Const.CAT_STREET_TABLE);
    }

    public static JTableModel getWaterIntakeTypesTableModel() {
        return getTableModel(Const.WKI_WATER_INTAKE_TYPE_TABLE);
    }

    public static JTableModel getWaterIntakesTableModel() {
        return getTableModel(Const.WKI_WATER_INTAKES_TABLE);
    }

    public static JTableModel getEmployeesTableModel() {
        return getTableModel(Const.EMP_EMPLOYEE_TABLE);
    }

    public static JTableModel getPayment() {
        return getTableModel(Const.PYM_PAYMENTS_TABLE);
    }
    
    public static JTableModel getStatus(){
        return getTableModel(Const.CAT_STATUS_TABLE);
    }

    private static JTableModel getTableModel(JDBTable table) {
        return new JTableModel(table.getGraphicsField(), 0);
    }
}
