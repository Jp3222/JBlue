/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import java.util.function.Predicate;
import javax.swing.JComponent;

/**
 *
 * @author jp
 */
public class VD {

    /**
     * Metodo que evalua si la cadena no es Null o Empty
     *
     * @param o cadena a evaluar
     * @return true solo si la varible no es null o vacia
     */
    public static boolean esNoE(String o) {
        return o == null || o.isEmpty();
    }

    /**
     * Evalua si la cadena es un numero entero
     *
     * @param o
     * @return
     */
    public static boolean isSoloNumero(String o) {
        return o.matches("[0-9]*");
    }

    /**
     *
     *
     * @param o - valor a evaluar
     * @param rango - numero de caracteres admitidos separados por una coma =
     * ","
     * @return
     */
    public static boolean isSoloNumero(String o, String rango) {
        String regex = "[0-9]{" + rango + "}";
        return o.matches(regex);
    }

    public static boolean esSoloTexto(String o) {
        return o.matches("[a-zA-Z]*");
    }

    public static boolean contieneSimbolos(String o) {
        return o.matches("[-+\\*/¿?!¡]");
    }

    public static <T extends JComponent> boolean validarJTF(T[] arr, Predicate<T> valido) {
        for (T o : arr) {
            if (!valido.test(o)) {
                return false;
            }
        }
        return true;
    }

    public static boolean NullVacio() {
        return true;
    }

    public static boolean soloTexto(String txt) {
        return true;
    }

    public static boolean soloNumeros(String txt) {
        return true;
    }

    public static boolean conSimbolos(String txt) {
        return true;
    }

}
