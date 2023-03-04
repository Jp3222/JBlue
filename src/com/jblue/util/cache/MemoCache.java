/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.cache;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.interfacesSuper.InterfaceDatos;
import com.jutil.jbd.conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 * @param <T>
 */
public class MemoCache<T extends Objeto> implements InterfaceDatos {

    private final ArrayList<T> lista;
    private final Operaciones<T> operaciones;
    private int primerId, ultimoId;
    private String query;

    public MemoCache(Operaciones<T> operaciones) {
        this.lista = new ArrayList<>(100);
        this.operaciones = operaciones;
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
                    primerId = Integer.parseInt(primer_id.getString("id"));
                }
            }
            try (ResultSet ultimo_id = cn.queryResult("SELECT id FROM " + operaciones.getTABLA() + " ORDER BY id desc LIMIT 1")) {
                if (ultimo_id.next()) {
                    ultimoId = Integer.parseInt(ultimo_id.getString("id"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getPrimerId() {
        return primerId;
    }

    public int getUltimoId() {
        return ultimoId;
    }

    public void setPrimerId(int primerId) {
        this.primerId = primerId;
    }

    public void setUltimoId(int ultimoId) {
        this.ultimoId = ultimoId;
    }

    public ArrayList<T> getLista() {
        return lista;
    }

    public Operaciones<T> getOperaciones() {
        return operaciones;
    }

    @Override
    public void cargar() {
        ArrayList<T> aux = operaciones.getLista(query);
        for (T item : aux) {
            try {
                lista.add((T) item.clone());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(MemoCache.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        aux.clear();
    }

    @Override
    public void vaciar() {
        if (lista.isEmpty()) {
            return;
        }
        lista.clear();
    }

    @Override
    public void actualizar() {
        vaciar();
        cargar();
        getIdsMinMax();
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void deleteQuery() {
        query = null;
    }

}
