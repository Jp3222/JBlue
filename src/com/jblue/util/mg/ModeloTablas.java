/*
 * Copyright (C) 2023 jp
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
package com.jblue.util.mg;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class ModeloTablas extends DefaultTableModel {

    private final ArrayList<Boolean> cellEdit;

    public ModeloTablas(String... columnNames) {
        super(new Object[][]{}, columnNames);
        cellEdit = new ArrayList<>(columnNames.length + 5);
        for (String columnName : columnNames) {
            cellEdit.add(true);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return cellEdit.get(columnIndex);
    }

    public void setCellEditable(int columnIndex, boolean value) {
        cellEdit.set(columnIndex, value);
    }

    public void setAllCellEditable(boolean value) {
        for (int i = 0; i < cellEdit.size(); i++) {
            cellEdit.set(i, value);
        }
    }

    public void clear() {
        if (dataVector.isEmpty()) {
            return;
        }
        int size = dataVector.size();
        dataVector.clear();
        fireTableRowsDeleted(0, size);
    }

    public boolean isDataEmpty() {
        return dataVector.isEmpty();
    }
}
