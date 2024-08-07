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
package com.jblue.util.modelo.funbd;

import com.jblue.util.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author juan-campos
 */
public interface ModeloFuncionesDB {

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
     * @param valores - nuevos valores para los campos
     * @param where - condicion para actualizar
     * @return true si la operacion se hizo correctamente en otro caso false
     */
    boolean update(String campos[], String valores[], String where);

    /**
     *
     * @param <T>
     * @param campos
     * @param where
     * @return
     */
    <T extends Objeto> Optional<ArrayList<T>> select(String campos, String where);

    
}
