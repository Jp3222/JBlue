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
import com.jblue.model.dtos.Objeto;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class SpinnerController<T extends Objeto> extends AbstractComponentController<T> {

    public SpinnerController(JSpinner component) {
        super(component);
    }

    @Override
    public void loadData() {
        JSpinner o = getComponent();

        memo_cache.getList().forEach(e -> {
        
        });
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
