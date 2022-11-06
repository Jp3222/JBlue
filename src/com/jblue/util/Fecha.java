/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 *
 * @author jp
 */
public class Fecha {

    private static Fecha instancia;

    public static Fecha getInstancia() {
        if (instancia == null) {
            instancia = new Fecha();
        }
        return instancia;
    }
    private final DateTimeFormatter ORDEN;
    private final Year year;
    private final Month month;
    private final LocalDate localDate;
    private final String[] MESES = {
        "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIV"
    };

    private Fecha() {
        this.ORDEN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.year = Year.now();
        this.localDate = LocalDate.now();
        this.month = localDate.getMonth();
        System.out.println(month.name());
    }

    /**
     * Metodo que retorna la fecha actual
     *
     * @return un objeto de tipo LocalDate con la fecha actual
     */
    public LocalDate fechaActual() {
        return localDate;
    }

    public String fechaActualString() {
        return localDate.format(ORDEN);
    }

    /**
     * Retorna un array de String con el mes actual
     *
     * @return un array cuya posicion
     * <br> 0: mes actual
     * <br> 1: fmin
     * <br> 2: fmax
     */
    public String[] getMes() {
        return new String[]{
            MESES[localDate.getMonthValue() - 1], "1", month.length(year.isLeap()) + ""
        };
    }
}
