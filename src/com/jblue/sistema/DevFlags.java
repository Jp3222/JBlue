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
package com.jblue.sistema;

/**
 * En esta clase se pueden poner variables booleanas para comportamientos en
 * desarrollo
 *
 *
 * @author juan-campos
 */
public class DevFlags {

    /*
    ejemplo:
    
    try{
        codigo...
    }catch{
        if(MNS_ERR){
            System.out.println("Mensaje de desarrollador");
        }
    }
    
     */
    /**
     * Bandera para encerrar todas las opciones de desarrollo futuras.
     */
    public static boolean FUTURE_VIEW = true;

    /**
     * Mensaje de error para el usuario
     */
    public static boolean DEV_MSN = true;

    /**
     * *
     *
     */
    public static boolean DEV_CONFIG = true;
}
