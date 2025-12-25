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
package jsoftware.com.jblue.controllers.viewc;

import java.awt.event.ActionEvent;
import jsoftware.com.jblue.controllers.AbstractDBViewController;
import jsoftware.com.jblue.model.dto.WaterIntakesDTO;
import jsoftware.com.jblue.model.dto.StreetDTO;
import jsoftware.com.jblue.model.dto.UserDTO;
import jsoftware.com.jblue.model.dto.WaterIntakeTypesDTO;
import jsoftware.com.jblue.views.WaterIntakesView;
import jsoftware.com.jblue.views.components.ObjectSearchComponent;

/**
 *
 * @author juanp
 */
public class WaterIntakesController extends AbstractDBViewController<WaterIntakesDTO> {

    private WaterIntakesView view;

    public WaterIntakesController(WaterIntakesView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case SAVE_COMMAND ->
                save();
            case UPDATE_COMMAND ->
                update();
            case DELETE_COMMAND ->
                delete();
            case CANCEL_COMMAND ->
                cancel();
            case "search_user" ->
                search_user();
            case "search_water_intake_type" ->
                search_street1();
            case "search_street1" ->
                search_street2();
            case "search_street2" ->
                search_water_inatake_type();
            default ->
                throw new AssertionError();
        }
    }

    @Override
    public void save() {
        
    }

    @Override
    public void delete() {
    }

    @Override
    public void update() {
    }

    @Override
    public void cancel() {
    }

    private void search_user() {
        UserDTO user = ObjectSearchComponent.getUser(null);
        view.setUserSearch(user);
    }

    private void search_street1() {
        StreetDTO street = ObjectSearchComponent.getStreet(null);
        view.setStreet1_search(street);
    }

    private void search_street2() {
        StreetDTO street = ObjectSearchComponent.getStreet(null);
        view.setStreet2_search(street);
    }

    private void search_water_inatake_type() {
        WaterIntakeTypesDTO water_inatke_type = ObjectSearchComponent.getWaterIntakeType(null);
        view.setWater_intake_types_search(water_inatke_type);
    }
}
