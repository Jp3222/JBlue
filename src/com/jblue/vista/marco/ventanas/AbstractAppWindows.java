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
package com.jblue.vista.marco.ventanas;

import com.jblue.sistema.app.AppInfo;
import static com.jblue.sistema.app.AppInfo.NOMBRE_DEL_PROGRAMA;
import static com.jblue.sistema.app.AppInfo.VERSION_DEL_PROGRAMA;
import com.jutil.framework.WindowStates;
import javax.swing.JFrame;

/**
 *
 * @author jp
 */
public abstract class AbstractAppWindows extends JFrame implements AppInfo, WindowStates {

    public AbstractAppWindows() {
    }

    public final void updateTitle(String title) {
        StringBuilder s = new StringBuilder(NOMBRE_DEL_PROGRAMA);
        s.append(" ");
        s.append(VERSION_DEL_PROGRAMA);
        s.append(" - ").append(title);
        this.setTitle(s.toString());
    }

    @Override
    public void build() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void events() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void components() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialState() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void finalState() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
