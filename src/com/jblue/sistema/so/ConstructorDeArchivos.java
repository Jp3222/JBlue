/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class ConstructorDeArchivos {

    public final int ARCHIVO = 1;
    public final int DIRECTORIO = 2;

    private final ArrayList<File> directorios;
    private final ArrayList<File> archivos;

    public ConstructorDeArchivos() {
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
