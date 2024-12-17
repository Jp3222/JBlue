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
package com.jblue.util.cache;

/**
 *
 * @author juan-campos
 */
public interface Paginated {

    public static final int MOV_TO_BACK = -1;
    public static final int MOV_TO_NEXT = 1;
    
    
    boolean next();

    boolean back();

    boolean movBuffer(int mov);
    
    boolean movData(int mov);
    

}