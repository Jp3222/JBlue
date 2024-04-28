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

/**
 *
 * @author jp
 */
public class FormatoCSV {

    private String[] columnas;
    private int tam_aprox_tab;
    private int tam_aprox_fil;
    private int tam_opt_col;
    private int tam_aprox;
    private final List<String[]> lista;

    public FormatoCSV(List<String[]> lista) {
        this.lista = lista;
        calTamOpt();
    }

    public FormatoCSV(String[] columnas, List<String[]> lista) {
        this.columnas = columnas;
        this.lista = lista;
        calTamOpt();
    }

    private void calTamOpt() {
        //calculando tamaño optimo de cabezera
        tam_aprox_fil = getTamFil(lista.get(0));

        //calculando tamaño optimo de cabezera
        int x = lista.size();
        int y = lista.get(0).length;
        tam_aprox_tab = x * y;
        //
        tam_aprox = tam_aprox_tab;

        //calculando tamaño optimo de cabezera
        if (columnas != null) {
            tam_opt_col = getTamFil(columnas);
            tam_aprox += tam_opt_col;
        }

    }

    private int getTamFil(String... col) {
        int tam = col.length;
        for (String i : col) {
            tam += i.length();
        }
        return tam;
    }

    private String constructorFilas(String... col) {
        StringBuilder sb = new StringBuilder(tam_aprox_fil);
        int i = 0;
        for (; i < col.length - 1; i++) {
            String string = col[i];
            sb.append(string).append(',');
        }
        i++;
        sb.append(col[i]);
        return sb.toString();
    }

    private String constructorDeCabezera() {
        return constructorFilas(columnas);
    }

    private String constructorDeTabla() {
        StringBuilder tabla = new StringBuilder(tam_aprox_tab);
        for (String[] i : lista) {
            tabla.append(constructorFilas(i)).append("\n");
        }
        return tabla.toString();
    }

    public String getCSV(boolean col) {
        StringBuilder sb = new StringBuilder(tam_aprox);
        if (!colNUll() && col) {
            sb.append(constructorDeCabezera()).append("\n");
        }
        sb.append(constructorDeTabla());
        return sb.toString();
    }

    public boolean colNUll() {
        return columnas == null;
    }

}
