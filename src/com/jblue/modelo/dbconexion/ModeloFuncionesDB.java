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
package com.jblue.modelo.dbconexion;

import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public interface ModeloFuncionesDB<T extends Objeto> {

    /**
     * Este metodo inserta valores en la base de datos ignorando el primer valor
     * del array suponiendo que pertenece al campo "ID"
     *
     * @param valores - conjunto de valores que se guardaran en la base de datos
     * @return true si la inserccion se hizo correctamente en otro clase false
     */
    boolean insert(String[] valores);

    /**
     *
     * Este metodo elimina registros de la base de dataos segun la "condicion
     * escrita en sql" que se pasa por parametro
     *
     * @param where - condicion en "lenguaje sql" para la eliminacion de algun
     * registro
     * @return true si la eliminacion se hizo correctamente en caso de que la
     * condicion sea null o erronea false
     */
    boolean delete(String where);

    /**
     * Este metodo actualiza un solo campo a la vez
     *
     * @param campo - campo que se actualizara
     * @param valor - nuevo valor
     * @param where - condicion para actualizar ese campo
     * @return true si la operacion se hizo correctamente en otro caso false
     */
    boolean update(String campo, String valor, String where);

    /**
     * Este metodo actualiza multiples campos a la vez
     *
     * @param campos - campos que se actualizaran
     * @param newData - nuevos valores para los campos
     * @param where - condicion para actualizar
     * @return true si la operacion se hizo correctamente en otro caso false
     */
    boolean update(String campos[], String newData[], String where);

    /**
     * Este metodo recupera una lista de datos de la base de datos y trata las
     * excepciones retornado un objeto de tipo "Optional" para la verificacion
     * de existencia de datos.
     * <br>
     *
     *
     * @param campos - campos selolicitados a la base de datos; en caso de
     * colocar un "null","","*" la funcion solicitara todos lo campos de la
     * tabla solicitada
     * @param where - condicion bajo la cual se seleccionaran los datos
     * @return un objeto de tipo "optional" el cual contiene una lista con los
     * valores solicitados por la condicion where en caso de un "SQLExcption"
     * retornara un objeto vacio
     */
    Optional<ArrayList<T>> select(String campos, String where);

    /**
     ** Este metodo recupera un conjunto datos de la base de datos y trata las
     * excepciones retornado un objeto de tipo "Optional" para la verificacion
     * de existencia de datos.
     * <br>
     *
     *
     * @param campos - campos selolicitados a la base de datos; en caso de
     * colocar un "null","","*" la funcion solicitara todos lo campos de la
     * tabla solicitada
     * @param where - condicion bajo la cual se seleccionaran los datos
     * @return un objeto de tipo "optional" el cual contiene una lista con los
     * valores solicitados por la condicion where en caso de un "SQLExcption"
     * retornara un objeto vacio
     *
     */
    Optional<T> get(String campos, String where);
}
