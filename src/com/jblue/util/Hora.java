/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class Hora {

    private static Hora instancia;

    public synchronized static Hora getInstancia() {
        if (instancia == null) {
            instancia = new Hora();
        }
        return instancia;
    }

    private LocalTime lt;
    private final DateTimeFormatter PATRON;

    private Hora() {
        PATRON = DateTimeFormatter.ofPattern("ss-mm-hh");
    }

    public String getHoraActual() {
        lt = LocalTime.now();
        return lt.format(PATRON);
    }

}
