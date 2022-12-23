/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class Mensajes {

    /**
     * Metdo para lanza un mensa
     *
     * @param operaccion tipo de operaccion que se realizo:
     * <br>0 = "inserccion"
     * <br>1 = "eliminacion"
     * <br>2 = "actualizacion"
     * @param estado indicador del estado de la operaccion:
     * <br>0 = "exitoso"
     * <br>1 = "erroneo"
     */
    public static void Mensaje(int operaccion, int estado) {
        String[] operaciones = {
            "inserccion", "eliminacion", "actualizacion"
        };
        String[] estados = {
            "exitosa", "erronea"
        };
        JOptionPane.showMessageDialog(null, operaciones[operaccion] + " " + estados[estado]);
    }

    public static void DatosNoValidos() {
        JOptionPane.showMessageDialog(null, "Datos De Entrada No Validos", "Datos Null", JOptionPane.ERROR_MESSAGE);
    }

}
