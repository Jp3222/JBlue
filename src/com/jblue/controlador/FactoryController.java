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

import com.jblue.controlador.objc.ShopCartController;
import com.jblue.controlador.objc.StreetsController;
import com.jblue.vista.views.NewCalles;
import com.jblue.vista.views.NewVCaja;
import com.jblue.vista.windows.Login;
import com.jblue.vista.windows.MenuConfigBD;

/**
 *
 * @author juan-campos
 */
public class FactoryController {

    public static Controller getLoginController(Login WIN_LOGIN, MenuConfigBD DB_CONFIG_MENU) {
        return new CLogin(WIN_LOGIN, DB_CONFIG_MENU);
    }

    public static Controller getShopCartController(NewVCaja view) {
        return new ShopCartController(view);
    }
    
    public static Controller getStreetsController(NewCalles view){
        return new StreetsController(view);
    }
}
