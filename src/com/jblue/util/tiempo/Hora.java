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

    public static final DateTimeFormatter PATRON = DateTimeFormatter.ofPattern("hh-mm-ss");

    public static String getHoraActualStr() {
        LocalTime l = LocalTime.now();
        return l.format(PATRON);
    }

    public static LocalTime getHoraActual() {
        return LocalTime.now();
    }
}
