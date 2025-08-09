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
package com.jblue.controllers.compc;

import com.jblue.controllers.AbstractComponentController;
import com.jblue.model.dtos.Objects;
import com.jblue.util.cache.MemoListCache;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class ComboBoxController<T extends Objects> extends AbstractComponentController<T> {

    public ComboBoxController(JComboBox<T> component, MemoListCache<T> memo_cache) {
        super(component, memo_cache);
    }

    @Override
    public void loadData() {
        JComboBox<T> box = getComponent();
        box.addItem((T) new Objects() {
            @Override
            public String toString() {
                return "Seleccione elemento";
            }
        });
        memo_cache.getList().forEach((t) -> box.addItem(t));
    }

    @Override
    public void dumpData() {
        JComboBox<T> box = getComponent();
        DefaultComboBoxModel<T> model = (DefaultComboBoxModel<T>) box.getModel();
        if (model.getSize() == 0) {
            return;
        }
        box.removeAllItems();
    }

    @Override
    public void updateData() {
        dumpData();
        loadData();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
