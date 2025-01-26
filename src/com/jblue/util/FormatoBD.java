/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 *
 * @author jp
 */
public class FormatoBD {

    private static final String FT = "'%s'";

    public static String[] inputFormat(String... datos) {
        for (int i = 0; i < datos.length; i++) {
            if (Filtros.isNullOrBlank(datos[i])) {
                continue;
            }
            datos[i] = datos[i].trim().toUpperCase().replace("[.,]", "").replace(" ", "_");
            datos[i] = FT.formatted(datos[i]);
        }
        return datos;
    }

    public static String valuesFormat(String... values) {
        return Arrays.toString(inputFormat(values)).replace('[', '(').replace(']', ')');
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

    private static boolean numero(String txt) {
        return txt.matches("[0-9]*(|.[0-9]{1,})");
    }
}
