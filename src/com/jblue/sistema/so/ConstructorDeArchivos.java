/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class ConstructorDeArchivos {

    public static int ARCHIVO = 1;
    public static int DIRECTORIO = 2;

    private String rutaUsuario;
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

    public File getRuta(int tipo, int index) {
        if (tipo == ARCHIVO) {
            return RUTAS_ARCHIVOS.get(index);
        } else if (tipo == DIRECTORIO) {
            return RUTAS_DIRECTORIOS.get(index);
        }
        return null;
    }

    public void construirArchivos() {
        for (File file : RUTAS_ARCHIVOS) {
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    public void construirDirectorios() {
        for (File file : RUTAS_DIRECTORIOS) {
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }

    public void construirTodos() {
        construirDirectorios();
        construirArchivos();
    }

}
