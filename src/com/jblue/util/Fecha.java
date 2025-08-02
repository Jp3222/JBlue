/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class Fecha {

    public static final String[] MESES = {
        "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"
    };

    public static int getIndexMes(String mes) {
        for (int i = 0; i < MESES.length; i++) {
            String j = MESES[i];
            if (j.equalsIgnoreCase(mes)) {
                return i + 1;
            }
        }
        return -1;
    }

    public static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd-MM-yyyy");

}
