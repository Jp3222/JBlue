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
package jsoftware.com.jblue.controllers.compc;

import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import jsoftware.com.jblue.controllers.AbstractComponentController;
import jsoftware.com.jblue.model.dto.Objects;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public class SpinnerController<T extends Objects> extends AbstractComponentController<T> {

    private static final long serialVersionUID = 1L;

    public SpinnerController(JSpinner component) {
        super(component, null, null);
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
