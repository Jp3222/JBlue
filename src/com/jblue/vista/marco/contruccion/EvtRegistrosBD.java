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
package com.jblue.vista.marco.contruccion;

import com.jblue.vista.marco.vistas.SimpleView;
import javax.swing.JOptionPane;

/**
 *
 * @author juan-campos
 */
public interface EvtRegistrosBD {

    /**
     * <p>
     * Metodo dedicdo al evento para "guardar" en la base de datos.
     *
     * En este metodo es necesario colocar los procedimientos para guardar la
     * informacion de la base de datos haciendo uso de los metodos:
     * <ul>
     * <li>camposValidos</li>
     * <li>getInfo
     * <p>
     * con el fin de validar campos y obtener la informacion de los mismos.
     * </p>
     * </li>
     * </ul>
     *
     * </p>
     */
    void evtGuardar();

    /**
     * <p>
     * Metodo dedicdo al evento para "guardar" en la base de datos.
     *
     * En este metodo es necesario colocar los procedimientos para guardar la
     * informacion de la base de datos haciendo uso de los metodos:
     * <ul>
     * <li>camposValidos
     * <p>
     * metodo utilizado para validar los campos.
     * <p>
     * </li>
     * <li>getInfo
     * <p>
     * metodo utilizado para obtener la informacion de los campos pasando como
     * parametro "true", para no modificar datos que no se necesiten modificar.
     * <p>
     * </li>
     * </ul>
     *
     * </p>
     */
    void evtActualizar();

    /**
     *
     */
    void evtEliminar();

    /**
     *
     */
    void evtBuscar();

    /**
     *
     */
    void evtCancelar();

    /**
     *
     * @param actualizacion
     * @return
     */
    String[] getInfo(boolean actualizacion);

    /**
     *
     * @return
     */
    boolean camposValidos();

    /**
     *
     * @param o
     * @return  */
    default boolean evtCancelar(SimpleView o) {
        int in = JOptionPane.showConfirmDialog(o, "¿Desea cancelar esta operacion?", "Cancelar Operacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return JOptionPane.YES_OPTION == in;
    }
}
