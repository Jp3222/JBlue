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
package com.jblue.util.modelo.objetos;

/**
 *
 * @author juan-campos
 */
public abstract class AbstraccionObjeto {

    /**
     * Array principal en el que se almacena la colecion de datos segun el tipo
     * de objeto
     */
    protected String[] _conjunto;

    /**
     * Replica del conjunto principal con el proposito de almacenar informacion
     * que replace a llaves foraneas.
     * <br>
     * <br>Este conjunto se pretende mas para una representacion grafica
     */
    protected String[] _conjuntoSinFK;

    public abstract String StringRepresentacion();

}
