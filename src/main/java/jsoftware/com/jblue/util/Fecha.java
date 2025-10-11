/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    /**
     * Convierte un objeto java.util.Date a un String con el formato
     * "yyyy-MM-dd".
     *
     * @param dateObject El objeto Date a convertir.
     * @return Un String con la fecha formateada o null si el objeto de entrada
     * es null.
     */
    public static String getDateToString(Date dateObject) {
        if (dateObject == null) {
            return null;
        }
        // Define el formato de fecha deseado
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Formatea el objeto Date a String y lo devuelve
        return sdf.format(dateObject);
    }

    /**
     * Convierte un String con formato "yyyy-MM-dd" a un objeto java.util.Date.
     *
     * @param dateString La cadena de texto de la fecha a convertir.
     * @return Un objeto Date si la conversión es exitosa, o null si la cadena
     * es inválida o nula.
     */
    public static Date getStringToDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        try {
            // Define el formato que se espera de la cadena de texto
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Analiza la cadena y devuelve el objeto Date
            return sdf.parse(dateString);
        } catch (ParseException e) {
            // Maneja la excepción si la cadena no coincide con el formato
            System.err.println("Error al analizar la fecha: " + dateString);
            return null;
        }
    }
}
