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
package com.jblue.controlador.winc;

import com.jblue.vista.windows.WMainMenu;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 */
public class MainController extends WindowController {

    private final WMainMenu view;

    public MainController(WMainMenu view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand == null || actionCommand.isBlank()) {
            JOptionPane.showMessageDialog(view, "El comando %s no es valido".formatted(actionCommand));
            return;
        }
        if (actionCommand.equals(WMainMenu.OUT)) {
            view.dispose();
            return;
        }
        view.getLabelTitle().setText(actionCommand);
        view.getCardLayout().show(view.getViewsPanel(), actionCommand);
        view.updateTitle(actionCommand);
    }

}
