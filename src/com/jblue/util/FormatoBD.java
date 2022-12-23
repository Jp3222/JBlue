/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 *
 * @author jp
 */
public class FormatoBD {

    public static String[] bdSalida(String... datos) {
        for (int i = 0; i < datos.length; i++) {
            datos[i] = datos[i].toUpperCase().replace('_', ' ');
        }
        return datos;
    }

    public static String[] bdEntrada(String... datos) {
        for (int i = 0; i < datos.length; i++) {
            datos[i] = datos[i].trim().toUpperCase().replace(" ", "_");
        }
        return datos;
    }

    public static String[] getArray(String... campos) {
        return campos;
    }

    public <T> T[] exp(T[] array, Integer... exp) {
        ArrayList<T> lista = new ArrayList<>(array.length);
        for (int i = 0; i < array.length; i++) {
            if (!in(exp, i)) {
                lista.add(array[i]);
            }
        }
        return (T[]) lista.toArray();
    }

    public <T extends Comparable<T>> boolean in(T[] coleccion, T objeto) {
        for (T t : coleccion) {
            if (objeto.compareTo(t) == 0) {
                return true;
            }
        }
        return false;
    }

    public <T extends Comparable<T>> boolean in(T[] coleccion, T objeto, Predicate<T> predicado) {
        for (T t : coleccion) {
            if (predicado.test(t)) {
                return true;
            }
        }
        return false;
    }
}
