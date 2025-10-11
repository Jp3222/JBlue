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
package jsoftware.com.jblue.sys;

import jsoftware.com.jblue.sys.app.AppConfig;

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

    /**
     * Banderaa para todas las vistas nuevas
     */
    public static boolean DEV_VEW_NEWS = AppConfig.isTestFunction();

    /**
     * Bandera para todos los mensajes puestos en condigo sobre el codigo
     */
    public static boolean DEV_MSG_CODE = AppConfig.isDevMessages();
    
    /**
     * Bandera para todos los mensajes puestos en el codigo sobre la base de
     * datos
     */
    public static boolean DEV_MSG_DATA_BASE = AppConfig.isDbMessages();

    /**
     * Bandera para ejecutar funciones en desarrollo
     */
    public static boolean DEV_EXE_FUNCION = AppConfig.isDevFunction();

    /**
     * Bandera para ejecutar funciones a prueba
     */
    public static boolean TST_EXE_FUNCION = AppConfig.isTestFunction();

    /**
     *
     */
    public static boolean LOGS_DEV = AppConfig.isLogsDev();

    /**
     *
     */
    public static boolean LOGS_TEST = AppConfig.isLogsTest();

    /**
     *
     */
    public static boolean LOGS_DB = AppConfig.isLogsDB();

    private DevFlags() {
    }

}
