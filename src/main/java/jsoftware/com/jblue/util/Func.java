/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jsoftware.com.jblue.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import jsoftware.com.jblue.sys.app.AppConfig;

/**
 *
 * @author juanp
 */
public class Func {

    public static void devMessages(String msg) {
        if (AppConfig.isDevMessages()) {
            System.out.println(msg);
        }
    }

    // -----------------------------------------------------------------
    // 1. Método Base Central (Manejo de Contenido y Nulidad)
    // -----------------------------------------------------------------
    /**
     * Recupera el valor de la clave del Map si está presente. Si la clave no
     * existe o el valor es null, retorna null.
     */
    public static Object getIfNotNull(Map<?, ?> map, String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Object value = map.get(key);
        // Retorna null si el valor recuperado es null
        return value;
    }

    // -----------------------------------------------------------------
    // 2. Métodos de Acceso Tipado y Seguro contra NULL
    // -----------------------------------------------------------------
    // --- Tipos String ---
    /**
     * Obtiene el valor como String. Retorna null si el valor es null o no
     * existe.
     */
    public static String getString(Map<?, ?> map, String key) {
        Object value = getIfNotNull(map, key);
        // Corrección: Usar toString() si no es null, de lo contrario, null.
        return value != null ? value.toString() : null;
    }

    // --- Tipos Numéricos Primitivos (int) ---
    /**
     * Obtiene el valor como int. Retorna el valorPorDefecto si es null o la
     * conversión falla.
     */
    public static int getInt(Map<?, ?> map, String key, int defaultValue) {
        Object value = getIfNotNull(map, key);
        if (value == null) {
            return defaultValue;
        }
        try {
            if (value instanceof Number) {
                return ((Number) value).intValue();
            }
            // Intenta parsear si es una cadena
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            // Manejo de error: la conversión falla
            return defaultValue;
        }
    }

    /**
     * Sobrecarga de getInt: Retorna 0 si es null o falla la conversión.
     */
    public static int getInt(Map<?, ?> map, String key) {
        return getInt(map, key, 0);
    }

    // --- Tipos Numéricos Wrapper (Integer) ---
    /**
     * Obtiene el valor como Integer (permite null).
     */
    public static Integer getInteger(Map<?, ?> map, String key) {
        Object value = getIfNotNull(map, key);
        if (value == null) {
            return null;
        }
        try {
            if (value instanceof Number) {
                return ((Number) value).intValue();
            }
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // --- Tipos Decimales (BigDecimal) ---
    /**
     * Obtiene el valor como BigDecimal. Retorna null si el valor es null o la
     * conversión falla.
     */
    public static BigDecimal getBigDecimal(Map<?, ?> map, String key) {
        Object value = getIfNotNull(map, key);
        if (value == null) {
            return null;
        }
        try {
            if (value instanceof BigDecimal) {
                return (BigDecimal) value;
            }
            // Utilizar String constructor para evitar pérdida de precisión con Double
            return new BigDecimal(value.toString());
        } catch (Exception e) {
            return null;
        }
    }

    // --- Tipos Fecha (LocalDate) ---
    /**
     * Obtiene el valor como LocalDate. Asume que el valor es una cadena en
     * formato ISO (YYYY-MM-DD).
     */
    public static LocalDate getLocalDate(Map<?, ?> map, String key) {
        String dateString = getString(map, key);
        if (dateString == null) {
            return null;
        }
        try {
            // Se asume el formato ISO estándar (YYYY-MM-DD)
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            // Manejo de error si el formato es incorrecto
            return null;
        }
    }

    // --- Tipos Booleanos ---
    /**
     * Obtiene el valor como boolean. Retorna false si es null, si es 0, o si es
     * la cadena "false".
     */
    public static boolean getBoolean(Map<?, ?> map, String key) {
        Object value = getIfNotNull(map, key);
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        String str = value.toString().toLowerCase();
        // Evalúa 1/true/yes/si como true
        return str.equals("true") || str.equals("1") || str.equals("yes") || str.equals("si");
    }

}
