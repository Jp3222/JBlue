/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.tiempo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class Fecha {

    public static final String[] MESES = {
        "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"
    };

    public static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static int getDiaDelMes() {
        LocalDate l = LocalDate.now();
        return l.getDayOfMonth();
    }

    public static int getDiaMaxDelMes() {
        LocalDate l = LocalDate.now();
        return l.getMonth().length(LocalDate.now().isLeapYear());
    }

    public static int getMesInt() {
        LocalDate l = LocalDate.now();
        return l.getMonthValue();
    }

    public static int getAñoActual() {
        LocalDate l = LocalDate.now();
        return l.getYear();
    }

    public static LocalDate getNewFechaActual() {
        return LocalDate.now();
    }

    public static String getNewFechaActualString() {
        LocalDate now = LocalDate.now();
        return now.format(FORMATO);
    }

    public static String getMesActual() {
        LocalDate now = LocalDate.now();
        int index = now.getMonthValue();
        return MESES[index - 1];
    }

    public static LocalDate getFechaObj(String o) {
        LocalDate now = LocalDate.parse(o, FORMATO);
        return now;
    }

}
