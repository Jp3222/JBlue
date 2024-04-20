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
package com.jblue.vista.jbmarco;

import com.jblue.sistema.app.AppInfo;

/**
 *
 * @author juan-campos
 */
public interface Estados {

    /**
     * Metodo recomentado para invocar solo a los metodos definidos siguiendo el
     * siguiente orden
     * <br> - estado final
     * <br> - estado inicial
     * <br> - estado add componentes
     * <br> - estado add eventos
     * <br> una vez sobre escrito el metodo, tambien se recomienda ponerlo en
     * estado "final" para poder invocarlo en el constructor
     */
    void llamable();

    /**
     * e
     * Metodo recomendado para darle a ciertos componentes un estado inicial al
     * cual dado un evento en su clase o alguna clase ajena todos los
     * componentes puedan volver a dicho estado
     *
     */
    void componentesEstadoInicial();

    /**
     * Metodo recomentado para darle a ciertos compoentes un estado final el
     * cual no sera cambiado
     */
    void componentesEstadoFinal();

    /**
     * Metodo recomendado para agregar componentes a otros componentes
     */
    void construirComponentes();

    /**
     * Metodo recomendado para añadir eventos a los componentes definidos
     */
    void manejoEventos();

}
