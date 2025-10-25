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

import javax.swing.JFrame;
import jsoftware.com.jblue.sys.app.AppInfo;
import static jsoftware.com.jblue.sys.app.AppInfo.PROGRAM_NAME;
import static jsoftware.com.jblue.sys.app.AppInfo.PROGRAM_VERSION;
import jsoftware.com.jutil.view.WindowStates;

/**
 *
 * @author jp
 */
public abstract class AbstractAppWindows extends JFrame implements AppInfo, WindowStates {

    private static final long serialVersionUID = 1L;

    public AbstractAppWindows() {
    }

    public final void updateTitle(String title) {
        StringBuilder s = new StringBuilder(PROGRAM_NAME);
        s.append(" ");
        s.append(PROGRAM_VERSION);
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
