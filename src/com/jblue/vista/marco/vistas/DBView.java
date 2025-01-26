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
package com.jblue.vista.marco.vistas;

import com.jblue.controlador.Controller;
import com.jblue.controlador.compc.TableController;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.objetos.ObjetoFK;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.jblue.vista.marco.TableSearchView;

/**
 *
 * @author jp
 */
public abstract class DBView extends SimpleView implements TableSearchView {

    protected TableController table_controller;
    protected int view_show;
    protected ImageIcon icon;

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = (ImageIcon) icon;
    }

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

    public <T extends Objeto & ObjetoFK> TableController<T> getTableController() {
        return table_controller;
    }

    public Controller getController() {
        return controller;
    }

}
