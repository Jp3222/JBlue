/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.trash;

import com.jblue.util.trash.Operaciones;
import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 * @param <T>
 */
public class MemoriaCache <T extends Objeto> {

    private final ArrayList<T> cache;
    private final ArrayList<T> cache_aux;
    private final Operaciones<T> operaciones;
    private int ant;
    private int sig;
    private int rango;
    private boolean usar_distancia;
    private String query;

    public MemoriaCache(Operaciones<T> operaciones) {
        this.cache = new ArrayList(1000);
        this.cache_aux = new ArrayList(1000);
        this.operaciones = operaciones;
        rango = 1000;
        ant = 1;
        sig = rango;
    }

    private void busca() {
        StringBuilder sb = new StringBuilder(100);
        if (usar_distancia) {
            sb.append("id >= ").append(ant).append(" and ").append("id <= ").append(sig);
        }
        if (query != null) {
            if (usar_distancia) {
                sb.append(" and ");
            }
            sb.append(query);
        }
        ArrayList<T> lista = operaciones.getLista(query);
        cache.addAll(lista);
        lista.clear();
    }

    public boolean sig() {
        busca();
        boolean aux = cache.isEmpty();
        if (!aux) {
            ant += rango;
            sig += rango;
        }
        return aux;
    }

    public boolean ant() {
        busca();
        boolean aux = cache.isEmpty();
        if (!aux) {
            ant -= rango;
            sig -= rango;
        }
        return aux;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void actualizar() {
        cache.clear();
        busca();
    }

    public void limpiar() {
        cache.clear();
    }

    public ArrayList<T> getCache() {
        return cache;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
        ant = 1;
        sig = rango;
    }

    public boolean usandoDistancia() {
        return usar_distancia;
    }

    public void usarDistancia(boolean usar_distancia) {
        this.usar_distancia = usar_distancia;
    }

    public ArrayList<T> buscar(Predicate<T> filtro) {
        if (!cache_aux.isEmpty()) {
            cache_aux.clear();
        }
        try {
            for (T t : cache) {
                if (filtro.test(t)) {
                    cache_aux.add((T) t.clone());
                }
            }
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(MemoriaCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<T>) cache_aux.clone();
    }
}
