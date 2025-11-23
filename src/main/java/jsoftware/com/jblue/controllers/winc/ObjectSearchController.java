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
package jsoftware.com.jblue.controllers.winc;

import jsoftware.com.jblue.model.dto.ForeingKeyObject;
import jsoftware.com.jblue.model.dto.Objects;
import jsoftware.com.jblue.views.components.ObjectSearchComponent;
import java.awt.event.ActionEvent;
import jsoftware.com.jblue.model.dto.StatusObjectModel;

/**
 *
 * @author juanp
 * @param <T>
 */
public class ObjectSearchController<T extends Objects & StatusObjectModel & ForeingKeyObject> extends WindowController {

    private static final long serialVersionUID = 1L;

    private ObjectSearchComponent<T> view;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
