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
package com.jblue.vista.componentes;

import com.jblue.util.Mensajes;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class FabricaDeMensajes implements Mensajes {

    public static void MensInsertOk(Component com) {
        Mensaje(com, 0, 0);
    }

    public static void MensUpdateOk(Component com) {
        Mensaje(com, 1, 0);
    }

    public static void MensDeleteOk(Component com) {
        Mensaje(com, 2, 0);
    }

    public static void MensInsertErr(Component com) {
        Mensaje(com, 0, 1);
    }

    public static void MensUpdateErr(Component com) {
        Mensaje(com, 1, 1);
    }

    public static void MensDeleteErr(Component com) {
        Mensaje(com, 2, 1);
    }

    /**
     * Metdo para lanza un mensa
     *
     * @param com
     * @param operaccion tipo de operaccion que se realizo:
     * <br>0 = "inserccion"
     * <br>1 = "eliminacion"
     * <br>2 = "actualizacion"
     * @param estado indicador del estado de la operaccion:
     * <br>0 = "exitoso"
     * <br>1 = "erroneo"
     */
    public static void Mensaje(Component com, int operaccion, int estado) {
        String mess = String.format(FORM_SS, _OPERACIONES[operaccion], _ESTADO[estado]);
        JOptionPane.showMessageDialog(com, mess);
    }

    public static void DatosNoValidos(Component com) {
        String mess = String.format(FORM_SS, _OPERACIONES[3], _ESTADO[2]);
        JOptionPane.showMessageDialog(com, mess);
    }

}
