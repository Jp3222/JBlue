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
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class Filtros {

    /**
     * Expresion regular que valida caracateres segun el conjunto a-z, A-Z y
     * _ñÑáÁéÉíÍóÓúÚ concatenar un rango para que pueda validar cadenas de texto
     */
    public static final String REGEX_SOLO_TEXTO = "( |[a-zA-Z]|[_ñÑáÁéÉíÍóÓúÚ])";
    public static final String RG_SOTEX = REGEX_SOLO_TEXTO + "{1,50}";

    public static String limpiar(String txt) {
        return txt.trim().replace(" ", "").replace("_", "").toUpperCase();
    }

    public static boolean limpiarYChecar(String a, String b) {
        return limpiar(a).contains(b);
    }

    public static boolean soloNumerosDecimales(String txt) {
        return txt.matches("([0-9]{1,4})(|(\\.([0-9]{1,2})))");
    }

    public static boolean soloNumerosEnteros(String txt) {
        return txt.matches("[0-9]{1,10}");
    }

    public static boolean soloTexto(String txt) {
        return txt.matches(RG_SOTEX);
    }

    public static boolean soloTexto(String txt, String rango) {
        StringBuilder defecto = new StringBuilder(REGEX_SOLO_TEXTO);
        defecto.append("{").append(rango).append("}");
        return txt.matches(defecto.toString());
    }

    public static boolean isNullOrBlank(String txt) {
        return txt == null || txt.isBlank() || txt.isEmpty();
    }

    public static boolean swIsCbxRangoValido(JComboBox o) {
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
}
