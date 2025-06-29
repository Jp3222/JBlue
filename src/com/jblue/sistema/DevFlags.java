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
 * Esta clase esta dedicada a la declaracion de banderas con el proposito de
 * probar, habilitar o desactivar funciones que puedan tener un efecto negativo,
 * asi mismo se espera tener un control de todos los mensajes de alerta para
 * evitar revelar informacion sensible
 * <br>
 * Todas puedes activar o desactivar todas las banderas con usar la bandera
 * "ALL_FLAGS" o darle un valor a cada una de ellas segun las necesidades
 *
 *
 * @author juan-campos
 */
public final class DevFlags {

    public static void loadFlags() {

    }

    /**
     * **Reglas de Juego** Estructura del nombre de una bandera:
     *
     * PREFIJO_TIPO_NOMBRE ** RELGLAS DE ESCRITURA:** Los prefijos y los tipos
     * deben escribirse con solo 3 caracteres. Los nombres de las banderas
     * pueden escribirse de cualquier tamaño.
     *
     * |---------------------| |prefijo | Significado| |---------------------|
     * |DEV | en desarrollo | |TST | a prueba | |CAN | candidata |
     * |---------------------|
     *
     * Recuerda que cuando una funcion ya paso los prefijos "DEV", "TST" y "CAN"
     * } puedes quitar la bandera
     *
     * |---------------------| |Tipos | Significado | |---------------------|
     * |MSG | Mensaje | |FUN | Funcion | |PRP | Propiedad | |VEW | VISTA | |CFG
     * | Configuracion | |EXE | Ejecutar | |---------------------|
     *
     *
     */
//-----------------------BANDERAS GENERICAS-----------------------------------//
    /**
     * Bandera que le da un valor inicial a todas las banderas
     */
    public static boolean ALL_FLAGS = false;

    /**
     * Banderaa para todas las vistas nuevas
     */
    public static boolean DEV_VEW_NEWS = false;

    /**
     * Bandera para todos los mensajes puestos en condigo sobre el codigo
     */
    public static boolean DEV_MSG_CODE = false;

    /**
     * Bandera para todos los mensajes puestos en el codigo sobre la base de
     * datos
     */
    public static boolean DEV_MSG_DATA_BASE = false;

    /**
     * Bandera para ejecutar funciones en desarrollo
     */
    public static boolean DEV_EXE_FUNCION = false;

    /**
     * Bandera para ejecutar funciones a prueba
     */
    public static boolean TST_EXE_FUNCION = true;

    /**
     * Bandera para ejecutar funciones candidatas
     */
    public static boolean CAN_EXE_FUNCION = false;
    /**
     * Mensaje para mostrar los logs producido por llamada a la base de datos
     */
    public static boolean DEV_MSG_LOG_DATA_BASE = false;

    private DevFlags() {
    }

}
