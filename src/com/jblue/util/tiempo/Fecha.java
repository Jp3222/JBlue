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

    public static final String[] MESES = {
        "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"
    };

    private final DateTimeFormatter FORMATO;
    private final Year AÑO;
    private final Month MES;
    private final LocalDate FECHA_ACTUAL;

    public Fecha() {
        this.FORMATO = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.AÑO = Year.now();
        this.FECHA_ACTUAL = LocalDate.now();
        this.MES = FECHA_ACTUAL.getMonth();
    }

    public LocalDate getNewFechaActual() {
        return LocalDate.now();
    }

    public String getNewFechaActualString() {
        LocalDate now = LocalDate.now();
        return now.format(FORMATO);

    }

    public LocalDate getFechaObj(String o) {
        LocalDate now = LocalDate.parse(o, FORMATO);
        return now;
    }

    /**
     * Retorna un array de Strings con la informacion del mes actual
     *
     * @return un array cuya posicion
     * <br> 0 = mes actual
     * <br> 1 = fecha minima del mes = 1
     * <br> 2 = fecha maxima del mes = 28, 30 o 31
     */
    public String[] getInfoMes() {
        return new String[]{
            MESES[FECHA_ACTUAL.getMonthValue() - 1],
            "1",
            MES.length(AÑO.isLeap()) + ""
        };
    }

    /**
     *
     * @param i
     * @return
     */
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

    public int getMaxDiaDelMes() {
        return FECHA_ACTUAL.getMonth().length(AÑO.isLeap());
    }

    public int getDiaDelMes() {
        return FECHA_ACTUAL.getDayOfMonth();
    }

    public int getMes() {
        return FECHA_ACTUAL.getMonthValue();
    }

    public int getAño() {
        return FECHA_ACTUAL.getYear();
    }
}
