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
package com.jblue.util.modelosgraficos.model;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class ModeloTablas extends DefaultTableModel {

    private final ArrayList<String> lista;
    private ArrayList<Boolean> cellEdit;

    public ModeloTablas(String... columnNames) {
        super(new Object[][]{}, columnNames);
        lista = new ArrayList<>(columnNames.length + 5);
        cellEdit = new ArrayList<>(columnNames.length + 5);
        for (String columnName : columnNames) {
            cellEdit.add(true);
        }
        lista.addAll(Arrays.asList(columnNames));
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return cellEdit.get(columnIndex);
    }

    public void setCellEditable(int columnIndex, boolean value) {
        cellEdit.set(columnIndex, value);
    }
}
