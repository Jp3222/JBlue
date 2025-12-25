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
package jsoftware.com.jblue.views.framework;

import jsoftware.com.jblue.controllers.compc.TableController;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author jp
 */
public abstract class DBView<T extends JDBMapObject> extends SimpleView implements TableSearchViewModel {

    private static final long serialVersionUID = 1L;

    protected TableController<T> table_controller;
    protected int view_show;

    @Override
    public void build() {
    }

    @Override
    public void events() {
    }

    @Override
    public void components() {
    }

    @Override
    public void initialState() {
    }

    @Override
    public void finalState() {
    }

}
