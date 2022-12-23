/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.cache;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class MemoriaRAM {

    private static MemoriaRAM instancia;

    public static MemoriaRAM getInstancia() {
        if (instancia == null) {
            instancia = new MemoriaRAM();
        }
        return instancia;
    }

    private final ArrayList[] ARREGLO;

    public MemoriaRAM() {
        this.ARREGLO = new ArrayList[6];
    }

    public <T extends Objeto> void addArrayList(int index, ArrayList<T> lista) {
        ARREGLO[index] = lista;
    }

    public ArrayList<OCalles> listaCalles() {
        return ARREGLO[3];
    }
    
}
