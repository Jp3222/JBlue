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
import com.jblue.util.tiempo.Hora;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;

/**
 *
 * @author jp
 */
public abstract class Func {

    public static void habilitarComponente(boolean estado, JComponent componente) {
        componente.setEnabled(estado);
    }

    public static void habilitarComponentes(boolean estado, JComponent... componentes) {
        for (JComponent jComponent : componentes) {
            jComponent.setEnabled(estado);
        }
    }

    public static void ocultarComponente(boolean estado, JComponent componente) {
        componente.setVisible(estado);
    }

    public static void ocultarComponentes(boolean estado, JComponent... componentes) {
        for (JComponent jComponent : componentes) {
            jComponent.setVisible(estado);
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

    public static void hash(String... o) {
        StringBuilder sb = new StringBuilder(20);
        char c;
        for (String i : o) {
            if (Filtros.isNullOrBlank(i)) {
                continue;
            }
            c = i.charAt(0);
            if (c == '_') {
                c = 'x';
            }
            sb.append(c);
        }
        Hora h = new Hora();
        sb.append(h.getHora());
        sb.append(h.getMinuto());
        sb.append(h.getSegundo());
        System.out.println(sb.toString());
    }

}
