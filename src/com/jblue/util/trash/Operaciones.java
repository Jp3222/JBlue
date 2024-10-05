/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.trash;

import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author jp
 * @param <T>
 */
public class Operaciones<T extends Objeto> extends FuncionesEnvoltorio implements Cloneable {

    public Operaciones(String tabla, String[] campos) {
        super(tabla, campos);
    }

    public boolean actualizar(String[] valores, String where) {
        return _ACTUALIZAR_SIN_ID(valores, where);
    }

    public T get(String where) {
        Objeto o;
        ArrayList<Objeto> lista = _GET("*", where);
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        o = lista.get(0);

        return (T) o;
    }

    public ArrayList<T> getLista(String where) {
        return _GET("*", where);
    }

    @Override
    public boolean insert(String[] valores) {
        return super.insert(valores);
    }

    public boolean eliminar(String where) {
        return delete(where);
    }

    public boolean actualizar(String campo, String valor, String where) {
        return update(campo, valor, where);
    }

    public boolean actualizar(String[] campos, String[] valores, String where) {
        return update(campos, valores, where);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Optional<ArrayList<T>> select(String campos, String where) {
        return Optional.empty();
    }

    @Override
    public Optional get(String campos, String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
