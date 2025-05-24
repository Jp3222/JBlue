/*
 * Copyright (C) 2024 juan-campos
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
package com.jblue.controlador;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author juan-campos
 */
public interface DBControllerModel {

    static final String SAVE_COMMAND = "save";
    static final String UPDATE_COMMAND = "update";
    static final String DELETE_COMMAND = "delete";
    static final String CANCEL_COMMAND = "cancel";
    static final String SEARCH_COMMAND = "search";

    void save();

    void delete();

    void update();

    void cancel();

    default void messages(JPanel view, boolean o) {
        if (o) {
            JOptionPane.showMessageDialog(view, "Operacion exitosa", "Estado de la operacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Operacion erronea", "Estado de la operacion", JOptionPane.ERROR_MESSAGE);
        }
    }
}
