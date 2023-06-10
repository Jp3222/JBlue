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
package com.jblue.util.archivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public class Archivos {

    public static void crearDirectorios(String padre, String... dirs) {
        for (String dir : dirs) {
            crearDirectorio(padre, dir);
        }
    }

    public static void crearDirectorio(String url, String nombre) {
        File sup = new File(url);
        if (!sup.exists()) {
            sup.mkdirs();
        }

        StringBuilder nom = new StringBuilder(url);
        nom.append(File.separator);
        nom.append(nombre);
        //
        File dir = new File(nom.toString());
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static void copiarArchivos(File archivo, File destino) {
        StringBuilder s = new StringBuilder(destino.getAbsolutePath());
        String nom = numeroCopia(archivo);
        s.append("/").append(s);
    }

    public static String numeroCopia(File archivo) {
        if (!archivo.exists()) {
            return archivo.getName();
        }
        int i = 1;
        String nom = archivo.getName();
        boolean existe;
        String newNom;
        do {
            StringBuilder s = new StringBuilder(nom);
            s.append(" (").append(i).append(")");
            newNom = s.toString();
            File aux = new File(s.toString());
            existe = aux.exists();
            i++;
        } while (existe);

        return newNom;
    }

    public static boolean escribir(File file) {
        try {
            FileReader fr = new FileReader(file);
            InputStream s = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(s);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static String[] leer() {
        return null;
    }

}
