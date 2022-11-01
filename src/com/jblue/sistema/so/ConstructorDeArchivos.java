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

    private final ArrayList<File> RUTAS_DIRECTORIOS;
    private final ArrayList<File> RUTAS_ARCHIVOS;

    public ConstructorDeArchivos() {
        this.RUTAS_DIRECTORIOS = new ArrayList<>(10);
        this.RUTAS_ARCHIVOS = new ArrayList<>(10);
    }

    public void add(int tipo, String o) {
        File fl = new File(o);
        if (tipo == ARCHIVO) {
            RUTAS_ARCHIVOS.add(fl);
        } else if (tipo == DIRECTORIO) {
            RUTAS_DIRECTORIOS.add(fl);
        }
    }

    public File removeRuta(int tipo, int index) {
        if (tipo == ARCHIVO) {
            return RUTAS_ARCHIVOS.remove(index);
        } else if (tipo == DIRECTORIO) {
            return RUTAS_DIRECTORIOS.remove(index);
        }
        return null;
    }

    public File get(int tipo, int index) {
        if (tipo == ARCHIVO) {
            return RUTAS_ARCHIVOS.get(index);
        } else if (tipo == DIRECTORIO) {
            return RUTAS_DIRECTORIOS.get(index);
        }
        return null;
    }

    /**
     * metodo encargado de construir unicamente archivos
     */
    public void construirArchivos() {
        try {
            for (File file : RUTAS_ARCHIVOS) {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo encargado de construir unicamente directorios
     */
    public void construirDirectorios() {
        for (File file : RUTAS_DIRECTORIOS) {
            if (!file.exists()) {
                file.mkdirs();
            }
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

    public int isEmpty() {
        int x = 0;
        int y = 0;
        if (RUTAS_ARCHIVOS.isEmpty()) {
            x = 1;
        }

        if (RUTAS_DIRECTORIOS.isEmpty()) {
            y = 2;
        }
        return x + y;
    }

}
