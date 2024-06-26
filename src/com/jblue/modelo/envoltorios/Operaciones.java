/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.envoltorios;

import com.jblue.modelo.envoltorios.op.FuncionAplicada;
import com.jblue.modelo.envoltorios.op.FuncionesAbstractas;
import com.jblue.modelo.envoltorios.op.FuncionesEnvoltorio;
import com.jblue.modelo.objetos.sucls.Objeto;
import java.util.ArrayList;

/**
 *
 * @author jp
 * @param <T>
 */
public class Operaciones<T extends Objeto> extends FuncionesEnvoltorio implements FuncionesAbstractas, Cloneable {

    public Operaciones(String tabla, String[] campos) {
        super(tabla, campos);
    }

    public synchronized boolean insertar(String[] valores, FuncionAplicada funcion) {
        if (funcion != null) {
            valores = funcion.procesamiento(valores);
        }
        return _INSERTAR(valores);
    }

    public boolean actualizar(String[] valores, String where) {
        return _ACTUALIZAR_SIN_ID(valores, where);
    }

    @Override
    public T get(String where) {
        Objeto o = null;
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

}
