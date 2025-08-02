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
package com.jblue.controllers.viewc;

import com.jblue.controllers.AbstractDBViewController;
import com.jblue.model.factories.CacheFactory;
import com.jblue.model.dtos.OWaterIntakes;
import com.jblue.util.cache.MemoListCache;
import java.awt.event.ActionEvent;

/**
 *
 * @author juanp
 */
public class WaterIntakesController extends AbstractDBViewController<OWaterIntakes> {

    public WaterIntakesController(MemoListCache<OWaterIntakes> memo_cache) {
        super(CacheFactory.WATER_INTAKES);
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
