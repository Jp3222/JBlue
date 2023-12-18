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

import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.sucls.Objeto;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public abstract class FuncJBlue {

    public static void habilitarComponente(boolean estado, JComponent componente) {
        SwingUtilities.invokeLater(() -> componente.setEnabled(estado));
    }

    public static void habilitarComponentes(boolean estado, JComponent... componentes) {
        SwingUtilities.invokeLater(() -> {
            for (JComponent jComponent : componentes) {
                jComponent.setEnabled(estado);
            }
        });
    }

    public static void ocultarComponente(boolean estado, JComponent componente) {
        SwingUtilities.invokeLater(() -> componente.setVisible(estado));
    }

    public static void ocultarComponentes(boolean estado, JComponent... componentes) {
        SwingUtilities.invokeLater(() -> {
            for (JComponent jComponent : componentes) {
                jComponent.setVisible(estado);
            }
        });
    }

    public static void bloquearArbolComponentes(boolean estado, Component root) {
        if (root instanceof JComponent jc) {
            jc.setEnabled(estado);
            JPopupMenu jpm = jc.getComponentPopupMenu();
            if (jpm != null) {
                return;
            }
        }
        Component[] children = null;
        if ((root instanceof JMenu) || (root instanceof JMenuBar)) {
            return;
        }
        if (root instanceof Container container) {
            children = container.getComponents();
        }
        if (children != null) {
            for (Component child : children) {
                bloquearArbolComponentes(estado, child);
            }
        }
    }

    public static <T extends Objeto> ArrayList<T> buscador(List<T> lista, DefaultListModel<String> modelo, String txt, BiPredicate<T, String> filtro) {
        ArrayList<T> aux = new ArrayList<>(lista.size());
        String txt_aux;
        for (T t : lista) {
            txt_aux = t.getStringR();
            if (!filtro.test(t, txt_aux)) {
                continue;
            }
            aux.add(t);
            modelo.addElement(txt);
        }
        return aux;
    }

    public static void hash(OUsuarios o) {
        StringBuilder sb = new StringBuilder(20);
        for (String string : o.getInfo()) {
            sb.append(string.charAt(0));
        }
        System.out.println(sb.toString());
    }

    public static <T extends Objeto>void pintarLista(DefaultListModel<String> modelo, List<T> lista) {
        if (!modelo.isEmpty()) {
            modelo.clear();
            modelo.setSize(lista.size());
        }
        String aux;
        int j = 0;
        for (Objeto i : lista) {
            aux = String.format(FORMATO_S_S, i.getId(), i.getStringR());
            modelo.add(j, aux);
            j++;
        }
    }

    public static <T extends Objeto> void pintarComboBox(DefaultComboBoxModel<String> modelo, List<T> lista) {
        if (modelo.getSize() > 0) {
            modelo.removeAllElements();
        }
        String aux;
        int j = 0;
        for (Objeto i : lista) {
            aux = String.format(FORMATO_S_S, i.getId(), i.getStringR());
            modelo.insertElementAt(aux, j);
            j++;
        }
    }

    public static <T extends Objeto> void pintarTabla(DefaultTableModel modelo, List<T> lista) {
        if (modelo.getRowCount() > 0) {
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
        }
        for (Objeto i : lista) {
            modelo.addRow(i.getInfoSinFK());
        }
    }

    public boolean comparacionFiltrada(String texto, String texto_buscado) {
        String x = Filtros.limpiar(texto);
        String y = Filtros.limpiar(texto_buscado);
        return x.contains(y);
    }

    private static String FORMATO_S_S = "%s - %s";

}
