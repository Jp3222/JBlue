/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.cache;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.interfacesSuper.InterfaceDatos;
import com.jblue.util.interfacesSuper.InterfaceIteradora;
import com.jutil.jbd.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 * @param <T>
 */
public class MemoCache2<T extends Objeto> implements InterfaceIteradora, InterfaceDatos {

    private final ArrayList<T> MEMORIA;
    private final Operaciones<T> OPERACIONES;
    private int rango, IdMin, IdMax;
    private int primerId, ultimoID;
    private String query;

    public MemoCache2(Operaciones<T> operaciones) {
        this.MEMORIA = new ArrayList<>(100);
        this.OPERACIONES = operaciones;
        this.rango = 1000;
        this.IdMin = 1;
        this.IdMax = rango;
        this.getIdsMinMax();
    }

    /**
     * Metodo para obtener el primer id y el ultimo id de una tabla en la base
     * de datos
     */
    private void getIdsMinMax() {
        try {
            Conexion cn = OPERACIONES.getCONEXION();
            try (ResultSet primer_id = cn.queryResult("SELECT id FROM " + OPERACIONES.getTABLA() + " LIMIT 1")) {
                if (primer_id.next()) {
                    primerId = Integer.parseInt(primer_id.getString("id"));
                }
            }
            try (ResultSet ultimo_id = cn.queryResult("SELECT id FROM " + OPERACIONES.getTABLA() + " ORDER BY id desc LIMIT 1")) {
                if (ultimo_id.next()) {
                    ultimoID = Integer.parseInt(ultimo_id.getString("id"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param rango
     */
    @Override
    public void setRango(int rango) {
        if (rango > 1000) {
            try {
                throw new Exception("pasos sobre pasa el numero de indices");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        this.rango = rango;
    }

    @Override
    public void sig() {
        IdMin += rango;
        IdMax += rango;
    }

    @Override
    public void ant() {
        if ((IdMin - rango) > 0) {
            IdMin -= rango;
            IdMax -= rango;
        }
    }

    @Override
    public boolean sigCol() {
        sig();
        actualizar();
        return MEMORIA.isEmpty();
    }

    @Override
    public boolean antCol() {
        ant();
        actualizar();
        return MEMORIA.isEmpty();
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void deleteQuery() {
        query = null;
    }

    @Override
    public void cargar() {
        ArrayList<T> lista = leer();
        for (T t : lista) {
            MEMORIA.add(t);
        }
        lista.clear();
    }

    @Override
    public void vaciar() {
        MEMORIA.clear();
    }

    @Override
    public void actualizar() {
        ArrayList<T> lista = leer();
        for (T t : lista) {
            this.MEMORIA.add(t);
        }
        lista.clear();
    }

    public T buscar(int campo, String o) {
        for (T t : MEMORIA) {
            if (t.getInfo()[campo].equalsIgnoreCase(o)) {
                return t;
            }
        }
        return null;
    }

    public T buscar(String id) {
        for (T t : MEMORIA) {
            if (t.getInfo()[0].equalsIgnoreCase(id)) {
                return t;
            }
        }
        return null;
    }

    private ArrayList<T> leer() {
        return leer(OPERACIONES, null, IdMin, IdMax);
    }

    public ArrayList<T> getMEMORIA() {
        return MEMORIA;
    }

    public Operaciones<T> getOPERACIONES() {
        return OPERACIONES;
    }

    public void incIdMin() {
        primerId++;
    }

    public void incIdMax() {
        ultimoID++;
    }

    public void decIdMin() {
        primerId--;
    }

    public void decIdMax() {
        ultimoID--;
    }

    public int getPrimerId() {
        return primerId;
    }

    public int getUltimoID() {
        return ultimoID;
    }
}
