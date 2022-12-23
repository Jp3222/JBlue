/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

/**
 *
 * @author jp
 */
public class Validar {
    
    /**
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * @param o
     * @return 
     */
    public static boolean datosNULL(String[] o) {
        for (String s : o) {
            if (s == null || s.isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Evalua si la cadena es un numero entero
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

    public static boolean isSoloTexto(String o) {
        return o.matches("[a-zA-Z]");
    }

    public static boolean contieneSimbolos(String o) {
        return o.matches("[-+\\*/¿?!¡]");
    }

}
