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
package jsoftware.com.jblue.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import jsoftware.com.jutil.db.JDBMapObject;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public abstract class AbstractComponentController<T extends JDBMapObject> extends Controller {

    protected final JComponent componente;
    protected final List<T> list;

    public AbstractComponentController(JComponent componente) {
        this(componente, new ArrayList<>(20));
    }

    public AbstractComponentController(JComponent componente, List<T> list) {
        this.componente = componente;
        this.list = list;
    }

    public <T extends JComponent> T getComponent() {
        return (T) componente;
    }

    public abstract void loadData();

    public abstract void dumpData();

    public abstract void updateData();

}
