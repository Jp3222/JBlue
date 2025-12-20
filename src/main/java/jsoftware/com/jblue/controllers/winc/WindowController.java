/*
 * Copyright (C) 2024 juan pablo campos casasanero
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
package jsoftware.com.jblue.controllers.winc;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import jsoftware.com.jblue.controllers.Controller;
import jsoftware.com.jblue.views.framework.AbstractAppWindows;

/**
 *
 * @author juan pablo campos casasanero
 */
public abstract class WindowController extends Controller implements WindowListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    /**
     * Este metodo solo lanza un mensaje
     *
     * @param view
     * @param msg
     */
    public void returnMessage(AbstractAppWindows view, String msg) {
        JOptionPane.showMessageDialog(view,
                msg,
                "Estado de la operacion",
                JOptionPane.INFORMATION_MESSAGE);

    }
}
