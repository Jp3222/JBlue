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
package com.jblue.model;

import com.jblue.model.dtos.Objects;
import com.jutil.dbcon.connection.JDBConnection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author juan-campos
 * @param <T>
 */
public interface DBConnectionModel<T extends Objects> {

    /**
     * Este metodo inserta valores en la base de datos ignorando el primer valor
     * del array suponiendo que pertenece al campo "ID"
     *
     * @param values - conjunto de valores que se guardaran en la base de datos
     * @return true si la inserccion se hizo correctamente en otro clase false
     */
    boolean insert(String[] values);

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
     * @param field - campo que se actualizara
     * @param value - nuevo valor
     * @param where - condicion para actualizar ese campo
     * @return true si la operacion se hizo correctamente en otro caso false
     */
    boolean update(String field, String value, String where);

    /**
     * Este metodo actualiza multiples campos a la vez
     *
     * @param oldValue - campos que se actualizaran
     * @param newValue - nuevos valores para los campos
     * @param where - condicion para actualizar
     * @return true si la operacion se hizo correctamente en otro caso false
     */
    boolean update(String oldValue[], String newValue[], String where);

    /**
     * Este metodo recupera una lista de datos de la base de datos y trata las
     * excepciones retornado un objeto de tipo "Optional" para la verificacion
     * de existencia de datos.
     * <br>
     *
     *
     * @param field - campos selolicitados a la base de datos; en caso de
     * colocar un "null","","*" la funcion solicitara todos lo campos de la
     * tabla solicitada
     * @param where - condicion bajo la cual se seleccionaran los datos
     * @return un objeto de tipo "optional" el cual contiene una lista con los
     * valores solicitados por la condicion where en caso de un "SQLExcption"
     * retornara un objeto vacio
     */
    List<T> select(String field, String where);

    /**
     ** Este metodo recupera un conjunto datos de la base de datos y trata las
     * excepciones retornado un objeto de tipo "Optional" para la verificacion
     * de existencia de datos.
     * <br>
     *
     *
     * @param field - campos selolicitados a la base de datos; en caso de
     * colocar un "null","","*" la funcion solicitara todos lo campos de la
     * tabla solicitada
     * @param where - condicion bajo la cual se seleccionaran los datos
     * @return un objeto de tipo "optional" el cual contiene una lista con los
     * valores solicitados por la condicion where en caso de un "SQLExcption"
     * retornara un objeto vacio
     *
     */
    Optional<T> get(String field, String where);

    String getTable();

    String[] getFields();

    JDBConnection getJDBConnection();

}
