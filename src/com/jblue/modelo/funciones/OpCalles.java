/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.funciones;

import com.jblue.modelo.Const;
import com.jblue.modelo.funciones.op.FuncionesAbstractas;
import com.jblue.modelo.funciones.op.FuncionesEnvoltorio;
import com.jblue.modelo.objetos.OCalles;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OpCalles extends FuncionesEnvoltorio implements FuncionesAbstractas {

    public OpCalles() {
        super(Const.TABLAS[3], Const.BD_CALLES);
    }

    @Override
    public boolean insertar(String[] valores) {
        return INSERTAR(valores);
    }

    @Override
    public boolean eliminar(String where) {
        return ELIMINAR(where);
    }

    @Override
    public boolean actualizar(String campo, String valor, String where) {
        return ACTUALIZAR(campo, valor, where);
    }

    @Override
    public boolean actualizar(String[] campos, String[] valores, String where) {
        return ACTUALIZAR(campos, valores, where);
    }

    @Override
    public OCalles get(String where) {
        ArrayList<OCalles> lista = GET("*", where);
        OCalles o = lista.get(0);
        lista.clear();
        return o;
    }

    @Override
    public ArrayList<OCalles> getLista(String where) {
        ArrayList<OCalles> lista = GET("*", where);
        return lista;
    }

}
