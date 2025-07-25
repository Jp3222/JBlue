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
package com.jblue.controlador.winc;

import com.jblue.vista.windows.ProfileWindow;
import java.awt.event.ActionEvent;

/**
 *
 * @author juanp
 */
public class ProfileController extends WindowController {

    private final ProfileWindow view;

    public ProfileController(ProfileWindow view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println(actionCommand);
        view.getCardLayout().show(view.getRootPanel(), actionCommand);
        view.updateTitle(actionCommand);
    }

}
