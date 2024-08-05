/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.bdconexion;

import com.jblue.modelo.bdconexion.op.FuncionesAbstractas;
import com.jblue.modelo.bdconexion.op.FuncionesEnvoltorio;
import com.jblue.util.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author jp
 * @param <T>
 */
public class Operaciones<T extends Objeto> extends FuncionesEnvoltorio implements FuncionesAbstractas, Cloneable {

    public Operaciones(String tabla, String[] campos) {
        super(tabla, campos);
    }

    public boolean actualizar(String[] valores, String where) {
        return _ACTUALIZAR_SIN_ID(valores, where);
    }

    @Override
    public T get(String where) {
        Objeto o;
        ArrayList<Objeto> lista = _GET("*", where);
        if (lista == null || lista.isEmpty()) {
            return null;
        }
        o = lista.get(0);

        return (T) o;
    }

    @Override
    public ArrayList<T> getLista(String where) {
        return _GET("*", where);
    }

    @Override
    public boolean insertar(String[] valores) {
        return _INSERTAR(valores);
    }

    @Override
    public boolean eliminar(String where) {
        return _ELIMINAR(where);
    }

    @Override
    public boolean actualizar(String campo, String valor, String where) {
        return _ACTUALIZAR(campo, valor, where);
    }

    @Override
    public boolean actualizar(String[] campos, String[] valores, String where) {
        return _ACTUALIZAR(campos, valores, where);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public <T extends Objeto> Optional<ArrayList<T>> _SELECT(String campos, String where) {
        return Optional.empty();
    }

}
