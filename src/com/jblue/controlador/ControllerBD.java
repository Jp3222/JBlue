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

/**
 *
 * @author juan-campos
 */
public interface ControllerBD {

    static final String SAVE_COMMAND = "save";
    static final String UPDATE = "update";
    static final String DELETE = "delete";
    static final String SEARCH = "search";
    
    void save();
    
    void delete();
    
    void update();
    
    void cancel();
}
