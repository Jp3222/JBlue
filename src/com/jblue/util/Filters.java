/*
 * Copyright (C) 2023 jp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jblue.util;

import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class Filters {

    /**
     * Expresion regular que valida caracateres segun el conjunto a-z, A-Z y
     * _ñÑáÁéÉíÍóÓúÚ concatenar un rango para que pueda validar cadenas de texto
     */
    public static final String REGEX_SOLO_TEXTO = "( |[a-zA-Z]|[_ñÑáÁéÉíÍóÓúÚ])";
    public static final String RG_SOTEX = REGEX_SOLO_TEXTO + "{1,50}";

    public static String clearText(String txt) {
        return txt.trim().replace(" ", "").replace("_", "").toUpperCase();
    }

    public static boolean clearAndCheck(String a, String b) {
        return clearText(a).contains(clearText(b));
    }

    public static boolean onlyFloat(String txt) {
        return txt.matches("([0-9]{1,10})(|(\\.([0-9]{1,10})))");
    }

    public static boolean OnlyInteger(String txt) {
        return txt.matches("[0-9]{1,10}");
    }

    public static boolean onlyText(String txt) {
        return txt.matches(RG_SOTEX);
    }

    public static boolean onlyText(String txt, String rango) {
        StringBuilder defecto = new StringBuilder(REGEX_SOLO_TEXTO);
        defecto.append("{").append(rango).append("}");
        return txt.matches(defecto.toString());
    }

    public static boolean isNullOrBlank(String txt) {
        return txt == null || txt.isEmpty()|| txt.isBlank();
    }

    public static boolean isNullOrBlank(String... txt) {
        for (String i : txt) {
            if (isNullOrBlank(i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidComboBox(JComboBox o) {
        return o.getItemCount() > 0
                && o.getSelectedIndex() > 0
                && o.getSelectedIndex() < o.getItemCount();
    }

    public static void swPintarTabla(DefaultTableModel modelo, List<String[]> lista) {
        for (String[] i : lista) {
            modelo.addRow(i);
        }
    }

    public static void swPintarLista(DefaultListModel<String> modelo, List<String> lista) {
        for (String i : lista) {
            modelo.addElement(i);
        }
    }

    // Método auxiliar
    public static void addIfChanged(Map<String, String> map, String key, String oldValue, String newValue) {
        if (oldValue == null && newValue != null && !newValue.isBlank()) {
            map.put(key, newValue);
        } else if (oldValue != null && newValue != null && !newValue.isBlank() && !oldValue.equals(newValue)) {
            map.put(key, newValue);
        }
    }

    /**
     * Pone una clave y un valor en el mapa si el valor no es nulo y no está en
     * blanco.
     *
     * @param map
     * @param key
     * @param value
     */
    public static void putIfPresentAndNotBlank(Map<String, String> map, String key, String value) {
        if (value != null && !value.isBlank()) {
            map.put(key, value);
        }
    }

    /**
     * Pone una clave y un valor en el mapa si el valor no es nulo.
     *
     * @param map
     * @param key
     * @param value
     */
    public static void putIfNotNull(Map<String, String> map, String key, String value) {
        if (value != null) {
            map.put(key, value);
        }
    }
}
