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
package com.jblue.controlador.compc;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class ComboBoxItemsController<T> extends ComponentController {

    public static ComboBoxItemsController getNumbericItems(JComboBox componente, int stard, int end) {
        String[] items = new String[end];
        for (int i = stard; i <= items.length; i++) {
            String item = String.valueOf(i);
            componente.addItem(item);
        }
        return new ComboBoxItemsController(componente, items);
    }

    DefaultComboBoxModel<String> model;

    public ComboBoxItemsController(JComboBox<T> component, String... cache) {
        super(component);
        if (!(component.getModel() instanceof DefaultComboBoxModel)) {
            model = new DefaultComboBoxModel(cache);
        } else {
            model = (DefaultComboBoxModel<String>) component.getModel();
            model.addAll(Arrays.asList(cache));
        }
    }

    @Override
    public void loadData() {

    }

    @Override
    public void dumpData() {

    }

    @Override
    public void updateData() {

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
