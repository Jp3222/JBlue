/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.tiempo;

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

    /**
     * Metodo que retorna la instancia guardada al inicar la clase
     *
     * @return un objeto de tipo LocalDate con la fecha actual
     */
    public LocalDate getFechaActual() {
        return localDate;
    }

    /**
     * Metodo que retorna la instancia guardada al inicar la clase con el
     * formato "dd-MM-yyyy" formato requerido para la base de datos
     *
     * @return un objeto de tipo LocalDate con la fecha actual
     */
    public String getFechaActualString() {
        return localDate.format(ORDEN);
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
}
