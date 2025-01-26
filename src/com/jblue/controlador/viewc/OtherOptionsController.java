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
package com.jblue.controlador.viewc;

import com.jblue.controlador.Controller;
import com.jblue.vista.views.OtherOptions;
import com.jblue.vista.windows.MenuCargos;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author juan-campos
 */
public class OtherOptionsController extends Controller {

    private OtherOptions view;
    private final MenuCargos view_2;

    public OtherOptionsController(OtherOptions view, MenuCargos view_2) {
        this.view = view;
        this.view_2 = view_2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "help" -> {
                view.setWIn((JButton) e.getSource());
            }
            case "profile" -> {
                view.setWIn((JButton) e.getSource());
            }
            case "secretary" -> {
                view.setWIn((JButton) e.getSource());
            }
            case "tesorero" -> {
                view.setWIn((JButton) e.getSource());
            }
            case "presidente" -> {
                view.setWIn((JButton) e.getSource());
            }
            case "admin" -> {
                view.setWIn((JButton) e.getSource());
            }
            case "config" -> {
                view.setWIn((JButton) e.getSource());
            }
            case "tools" -> {
                view.setWIn((JButton) e.getSource());
            }
            case "new" -> {
                view.setWIn((JButton) e.getSource());
            }
            default ->
                throw new AssertionError();
        }

    }

}
