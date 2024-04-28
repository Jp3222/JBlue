/*
 * Copyright (C) 2023 jp
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
package com.jblue.vista.jbmarco;

/**
 *
 * @author jp
 */
public interface ConstTitutlos {

    //Apartados
    public static int TL_NUEVA_VENTANA = 0;
    public static int TL_INICIO_SESION = 1;
    public static int TL_MENU_PRINCIPAL = 2;

    public static final String[] TL_VENTANAS = {
        "Nueva Ventana",
        "Inicio Sesion",
        "Menu Principal",
        "Configuracion De Base de Datos"
    };

    //BD
    public static int TL_BD_USUARIOS = 1;
    public static int TL_BD_CALLES = 2;
    public static int TL_BD_TIPO_TOMAS = 3;
    /**
     * Constate que gusarda los siguientes titulos en las sguientes posiciones:
     * <ul>
     *  <li>
     *      0 : "Nueva Ventana"
     *  </li>
     *  <li>
     *      1: "BD Usuarios"
     *  </li> 
     *  <li>
     *      2: "BD Calles"
     *  </li>
     *  <li>
     *      3: "BD Tipo de tomas"
     *  </li>
     *  
     * </ul>
     * 
     * Para este array se usan las constantes cuyo prefijo empiece con: "TL_BD"
     */
    public static final String[] TL_BD = {
        TL_VENTANAS[0],
        "BD Usuarios",
        "BD Calles",
        "BD Tipo de tomas"
    };

    //Perfiles
    public static int TL_PERF_PRESIDENTE = 1;
    public static int TL_PERF_TESORERO = 2;
    public static int TL_PERF_ADMINISTRADOR = 3;
    public static int TL_PERF_SECRETARIO = 4;
    public static int TL_PERF_PERFIL = 5;
    
    /**
     * Constate que gusarda los siguientes titulos en las sguientes posiciones:
     * <ul>
     *  <li>
     *      0 : "Nueva Ventana"
     *  </li>
     *  <li>
     *      1: "Menu Presidente"
     *  </li> 
     *  <li>
     *      2: "Menu Tesorero"
     *  </li>
     *  <li>
     *      3: "Menu Administrador"
     *  </li>
     *  <li>
     *      4: "Menu Secretario"
     *  </li> 
     *  <li>
     *      5: "Perfil"
     *  </li>
     * </ul>
     * 
     * Para este array se usan las constantes cuyo prefijo empiece con: "TL_PREF"
     */
    public static final String[] TL_PERFILES = {
        TL_VENTANAS[0],
        "Menu Presidente",
        "Menu Tesorero",
        "Menu Administrador",
        "Menu Secretario",
        "Perfil"
    };
    /**
     * <br> 0 - "nueva ventana"
     * <br> 1 - "Inicio de sesion"
     * <br> 2 - "Menu Principal"
     * <br> 3 - "BD usuarios"
     * <br> 4 - "BD calles"
     * <br> 5 - "BD tipo de tomas"
     * <br> 6 - "Menu Tesoreria"
     * <br> 7 - "Menu Presidente"
     * <br> 8 - "Tomas Registradas"
     * <br> 9 - "Perfil de Usuario"
     * <br> 10 - "Administrador de Directorios"
     * <br> 11 - "Administrador de Documentos"
     * <br> 12 - "Comprobantes"
     * <br> 13 - "Administrador"
     */
    final String[] _MENUS_DEL_PROGRAMA = {
        "Nueva ventana",
        "Inicio de sesion",
        "Menu principal",
        "BD Usuarios",
        "BD Calles",
        "BD Tipo de Tomas",
        "Tesorero",
        "Presidente",
        "Tomas Registradas",
        "Perfil de Usuario",
        "Administrador de Directorios",
        "Administrador de Documentos",
        "Comprobantes",
        "Administrador"
    };
}
