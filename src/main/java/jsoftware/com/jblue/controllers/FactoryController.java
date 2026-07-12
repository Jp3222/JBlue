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
package jsoftware.com.jblue.controllers;

import jsoftware.com.jblue.controllers.viewc.EmployeeController;
import jsoftware.com.jblue.controllers.viewc.ShopCartController;
import jsoftware.com.jblue.controllers.viewc.StreetsController;
import jsoftware.com.jblue.controllers.viewc.UserController;
import jsoftware.com.jblue.controllers.viewc.WaterIntakesController;
import jsoftware.com.jblue.controllers.viewc.WaterIntakesTypesController;
import jsoftware.com.jblue.controllers.winc.LoginController;
import jsoftware.com.jblue.controllers.winc.WindowController;
import jsoftware.com.jblue.views.EmployeesView;
import jsoftware.com.jblue.views.ShopCartProcess;
import jsoftware.com.jblue.views.UserView;
import jsoftware.com.jblue.views.WaterIntakesTypesView;
import jsoftware.com.jblue.views.WaterIntakesView;

/**
 *
 * @author juan pablo campos casasanero
 */
public class FactoryController {

//    public static WindowController getLoginController(S) {
//        return new LoginController();
//    }

    public static WindowController getLoginController(boolean flag_dev, String process_name) {
        return new LoginController(flag_dev, process_name);
    }

    public static Controller getShopCartController(ShopCartProcess view) {
        return new ShopCartController(view);
    }

    public static Controller getStreetsController() {
        return new StreetsController();
    }

    public static Controller getUserController(UserView view) {
        return new UserController(view);
    }

    public static Controller getWaterIntakeTypesController(WaterIntakesTypesView view) {
        return new WaterIntakesTypesController(view);
    }

    public static Controller getWaterIntakesController(WaterIntakesView view) {
        return new WaterIntakesController(view);
    }

    public static Controller getEmployeeController(EmployeesView view) {
        return new EmployeeController(view);
    }
}
