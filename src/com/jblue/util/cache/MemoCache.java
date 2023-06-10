/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.cache;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.sucls.Objeto;
import com.jutil.jbd.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author jp
 * @param <T>
 */
public class MemoCache<T extends Objeto> {

    private final List<T> lista;
    private final Operaciones<T> operaciones;
    private int primer_id_leido, ultimo_id_leido;
    private int ant;
    private int sig;
    private int rango;
    private boolean rangoActivo;
    private String query;

    public MemoCache(Operaciones<T> operaciones) {
        this.lista = new ArrayList<>(1000);
        this.operaciones = operaciones;
        rango = 1000;
        ant = 1;
        sig = rango;
        getIdsMinMax();
    }

    /**
     * Metodo para obtener el primer id y el ultimo id de una tabla en la base
     * de datos
     */
    private void getIdsMinMax() {
        try {
            Conexion cn = operaciones.getCONEXION();
            try (ResultSet primer_id = cn.queryResult("SELECT id FROM " + operaciones.getTABLA() + " LIMIT 1")) {
                if (primer_id.next()) {
                    primer_id_leido = Integer.parseInt(primer_id.getString("id"));
                }
            }
            try (ResultSet ultimo_id = cn.queryResult("SELECT id FROM " + operaciones.getTABLA() + " ORDER BY id desc LIMIT 1")) {
                if (ultimo_id.next()) {
                    ultimo_id_leido = Integer.parseInt(ultimo_id.getString("id"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void rango(int rango) {
        this.rango = rango;
    }

    public void sig() {
        ant += rango;
        sig += rango;
    }

    public void ant() {
        int aux = ant - rango;
        if (aux > 0) {
            ant -= rango;
            sig -= rango;
        }
    }

    public int getPrimer_id_leido() {
        return primer_id_leido;
    }

    public int getUltimo_id_leido() {
        return ultimo_id_leido;
    }

    public ArrayList<T> getLista() {
        return (ArrayList<T>) lista;
    }

    public Operaciones<T> getOperaciones() {
        return operaciones;
    }

    public void cargar() {
        StringBuilder q = new StringBuilder();
        if (rangoActivo) {
            q.append("id >= ").append(ant);
            q.append(" and ");
            q.append("id <= ").append(sig);
        }

        if (query != null) {
            if (rangoActivo) {
                q.append(" and ");
            }
            q.append(query);
        }

        ArrayList<T> aux;
        if (q.isEmpty()) {
            aux = operaciones.getLista("");
        } else {
            aux = operaciones.getLista(q.toString());
        }
        for (T item : aux) {
            lista.add((T) item);
        }
        aux.clear();
    }

    public void ordenarPorID() {
        lista.sort((x, y) -> x.compareTo(y));
    }

    public void ordenarConPredicado(Comparator<T> o) {
        lista.sort(o);
    }

    public void vaciar() {
        if (lista.isEmpty()) {
            return;
        }
        lista.clear();
    }

    public void actualizar() {
        vaciar();
        cargar();
        getIdsMinMax();
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void deleteQuery() {
        query = null;
    }

    public ArrayList<T> subLista(Predicate<T> filtro) {
        ArrayList<T> arr_aux = new ArrayList<>(100);
        for (T t : lista) {
            if (filtro.negate().test(t)) {
                continue;
            }
            arr_aux.add(t);
        }
        return arr_aux;
    }

    public boolean isRangoActivo() {
        return rangoActivo;
    }

    public void setRangoActivo(boolean rangoActivo) {
        this.rangoActivo = rangoActivo;
    }

    public int getAnt() {
        return ant;
    }

    public int getSig() {
        return sig;
    }

}
