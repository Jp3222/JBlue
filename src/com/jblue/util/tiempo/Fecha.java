/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.tiempo;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class Fecha {

    private final DateTimeFormatter ORDEN;

    private final Year year;
    private final Month month;
    private final LocalDate localDate;

    private final String[] MESES = {
        "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIV"
    };

    public Fecha() {
        this.ORDEN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.year = Year.now();
        this.localDate = LocalDate.now();
        this.month = localDate.getMonth();
    }

    public LocalDate getNewFechaActual() {
        return LocalDate.now();
    }

    public String getNewFechaActualString() {
        LocalDate now = LocalDate.now();
        return now.format(ORDEN);

    }

    public LocalDate getFechaObj(String o) {
        LocalDate now = LocalDate.parse(o, ORDEN);
        return now;
    }

    /**
     * Retorna un array de String con el mes actual
     *
     * @return un array cuya posicion
     * <br> 0: mes actual
     * <br> 1: fecha minima del mes = 1
     * <br> 2: fecha maxima del mes = 28, 30 o 31
     */
    public String[] getMes() {
        return new String[]{
            MESES[localDate.getMonthValue() - 1],
            "1",
            month.length(year.isLeap()) + ""
        };
    }

    public String getMes(int i) {
        return MESES[i];
    }

    public String[] get() {
        LocalDate l = LocalDate.now();
        String dia = "" + l.getDayOfMonth();
        String mes = "" + l.getMonthValue();
        String año = "" + l.getYear();
        return new String[]{dia, mes, año};
    }
}
