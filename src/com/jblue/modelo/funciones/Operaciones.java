/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.funciones;

import com.jblue.modelo.funciones.op.FuncionAplicada;
import com.jblue.modelo.funciones.op.FuncionesEnvoltorio;
import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 * @param <T>
 */
public class Operaciones<T extends Objeto> extends FuncionesEnvoltorio {

    public Operaciones(String tabla, String[] campos) {
        super(tabla, campos);
    }

    public boolean insertar(String[] valores, FuncionAplicada funcion) {
        if (funcion != null) {
            valores = funcion.procesamiento(valores);
        }

        return INSERTAR(valores);
    }

    public boolean eliminar(String where, FuncionAplicada funcion) {

        return ELIMINAR(where);
    }

    public boolean actualizar(String campo, String valor, String where, FuncionAplicada funcion) {
        return ACTUALIZAR(campo, valor, where);
    }

    public boolean actualizar(String[] campos, String[] valores, String where, FuncionAplicada funcion) {
        if (funcion != null) {
            valores = funcion.procesamiento(valores);
        }
        return ACTUALIZAR(campos, valores, where);
    }

    public T get(String where) {
        Objeto o = null;
        try {
            ArrayList<T> lista = GET("*", where);
            o = (Objeto) lista.get(0).clone();
            lista.clear();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (T) o;
    }

    public ArrayList<T> getLista(String where) {
        ArrayList<T> lista = GET("*", where);
        return lista;
    }
    
    
    
}
