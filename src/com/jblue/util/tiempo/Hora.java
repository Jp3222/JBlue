/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.tiempo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class Hora {

    private final DateTimeFormatter PATRON;

    public Hora() {
        PATRON = DateTimeFormatter.ofPattern("hh-mm-ss");
    }

    public LocalTime createHora(String str) {
        LocalTime o = LocalTime.parse(str);
        return o;
    }

    public int compareTo(String str1, String str2) {
        LocalTime x = LocalTime.parse(str1);
        LocalTime y = LocalTime.parse(str2);
        return x.compareTo(y);

    }

    public LocalTime getHoraActual() {
        return LocalTime.now();
    }

    public String getHoraActualString() {
        LocalTime lt = LocalTime.now();
        return lt.format(PATRON);
    }

}
