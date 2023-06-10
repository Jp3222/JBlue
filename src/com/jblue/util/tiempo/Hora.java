/**
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
    private LocalTime hora_actual;

    public Hora() {
        PATRON = DateTimeFormatter.ofPattern("hh-mm-ss");
        hora_actual = LocalTime.now();
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
        return hora_actual;
    }

    public String getHoraActualString() {
        return hora_actual.format(PATRON);
    }

    public int getHora() {
        return hora_actual.getHour();
    }

    public int getMinuto() {
        return hora_actual.getMinute();
    }

    public int getSegundo() {
        return hora_actual.getSecond();
    }

    public void actualizarHora() {
        hora_actual = LocalTime.now();
    }

}
