/*
 * Copyright (C) 2025 juanp
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
package jsoftware.com.jblue.controllers.viewc;

import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.factories.CacheFactory;
import jsoftware.com.jblue.model.dtos.OWaterIntakes;
import jsoftware.com.jblue.views.WaterIntakesView;
import java.awt.event.ActionEvent;

/**
 *
 * @author juanp
 */
public class WaterIntakesController extends AbstractDBViewController<OWaterIntakes> {

    private WaterIntakesView view;

    public WaterIntakesController(WaterIntakesView view) {
        super(CacheFactory.WATER_INTAKES);
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void save() {
    }

    @Override
    public void delete() {
    }

    @Override
    public void update() {
    }

    @Override
    public void cancel() {
    }
}
