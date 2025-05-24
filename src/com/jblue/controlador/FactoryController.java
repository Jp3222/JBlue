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
package com.jblue.controlador;

import com.jblue.controlador.winc.WindowController;
import com.jblue.controlador.viewc.dbviews.UserController;
import com.jblue.controlador.winc.LoginController;
import com.jblue.controlador.viewc.ShopCartController;
import com.jblue.controlador.viewc.dbviews.StreetsController;
import com.jblue.controlador.viewc.dbviews.WaterIntakesController;
import com.jblue.controlador.winc.MainController;
import com.jblue.vista.views.WaterIntakesView;
import com.jblue.vista.views.UserView;
import com.jblue.vista.views.StreetsView;
import com.jblue.vista.views.ShopCartView;
import com.jblue.vista.windows.LoginWindows;
import com.jblue.vista.windows.ConfigWindow;
import com.jblue.vista.windows.WMainMenu;

/**
 *
 * @author juan-campos
 */
public class FactoryController {

    public static WindowController getLoginController(LoginWindows view, ConfigWindow view_config) {
        return new LoginController(view, view_config);
    }

    public static WindowController getLoginController(WMainMenu view) {
        return new MainController(view);
    }

    public static Controller getShopCartController(ShopCartView view) {
        return new ShopCartController(view);
    }

    public static Controller getStreetsController(StreetsView view) {
        return new StreetsController(view);
    }

    public static Controller getUserController(UserView view) {
        return new UserController(view);
    }

    public static Controller getWaterIntakesController(WaterIntakesView view) {
        return new WaterIntakesController(view);
    }

}
