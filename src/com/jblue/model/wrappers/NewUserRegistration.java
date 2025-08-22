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
package com.jblue.model.wrappers;

import com.jblue.model.dtos.OUser;
import com.jblue.model.dtos.OWaterIntakes;

/**
 *
 * @author juanp
 */
public class NewUserRegistration {
    
    private OUser user;
    private OWaterIntakes water_intake;

    public NewUserRegistration(OUser user, OWaterIntakes water_intake) {
        this.user = user;
        this.water_intake = water_intake;
    }
    
}
