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
package com.jblue.vista.jbmarco.inter;

import com.jblue.vista.jbmarco.VistaSimple;
import javax.swing.JOptionPane;

/**
 * Esta interfaz describe los eventos basicos estandar para el manejo de
 * operaciones en la base de datos
 *
 * @author jp
 */
public interface EvtMetodosEstandarBD {

    void evtGuardar();

    void evtActualizar();

    void evtEliminar();

    void evtCancelar();

    default boolean evtCancelar(VistaSimple o) {
        int in = JOptionPane.showConfirmDialog(o, "¿Desea cancelar esta operacion?", "Cancelar Operacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return JOptionPane.YES_OPTION == in;
    }
}
