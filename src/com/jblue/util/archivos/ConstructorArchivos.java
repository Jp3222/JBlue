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
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class ConstructorArchivos {

    public final int ARCHIVO = 1;
    public final int DIRECTORIO = 2;
    private final ArrayList<File> directorios;
    private final ArrayList<File> archivos;

    public ConstructorArchivos() {
        this.directorios = new ArrayList<>(10);
        this.archivos = new ArrayList<>(10);
    }

    public void add(int tipo, String o) {
        File fl = new File(o);
        if (tipo == ARCHIVO) {
            archivos.add(fl);
        } else if (tipo == DIRECTORIO) {
            directorios.add(fl);
        }
    }

    public File removeRuta(int tipo, int index) {
        if (tipo == ARCHIVO) {
            return archivos.remove(index);
        } else if (tipo == DIRECTORIO) {
            return directorios.remove(index);
        }
        return null;
    }

    public File get(int tipo, int index) {
        if (tipo == ARCHIVO) {
            return archivos.get(index);
        } else if (tipo == DIRECTORIO) {
            return directorios.get(index);
        }
        return null;
    }

    /**
     * metodo encargado de construir unicamente archivos
     */
    public void construirArchivos() {
        try {
            for (File file : archivos) {
                if (file.exists()) {
                    System.out.println("archivo existe");
                    continue;
                }
                file.createNewFile();
                System.out.println(file.getAbsolutePath() + file.exists());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo encargado de construir unicamente directorios
     */
    public void construirDirectorios() {
        for (File file : directorios) {
            if (file.exists()) {
                continue;
            }
            file.mkdir();
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * Metodo que llama primero a la funcion de construir directorios y
     * consecutivamente a la funcion de construir archivos
     */
    public void construirTodos() {
        construirDirectorios();
        construirArchivos();
    }

    public ArrayList<File> getArchivos() {
        return archivos;
    }

    public ArrayList<File> getDirectorios() {
        return directorios;
    }

    /**
     * metodo que indica si las listas de archivos estan vacias
     *
     * @return 0 si ninguna de las listas esta vacia
     * <br> 1 si la lista de archivos esta vacia
     * <br> 2 si la lista de directorios esta vacia
     * <br> 3 si ambas listas estan vacias
     */
    public int isEmpty() {
        int x = 0;
        int y = 0;
        if (archivos.isEmpty()) {
            x = 1;
        }
        if (directorios.isEmpty()) {
            y = 2;
        }
        return x + y;
    }

    public void print() {
        for (File directorio : directorios) {
            System.out.println(directorio.exists() + " = " + directorio.getAbsoluteFile());
        }
        for (File archivo : archivos) {
            System.out.println(archivo.exists() + " = " + archivo.getAbsoluteFile());
        }
    }
}
