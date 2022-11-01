/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.funciones;

import com.jblue.modelo.Const;
import com.jblue.modelo.funciones.op.FuncionesAbstractas;
import com.jblue.modelo.funciones.op.FuncionesEnvoltorio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OpPersonal extends FuncionesEnvoltorio implements FuncionesAbstractas {

    public OpPersonal() {
        super("personal", Const.BD_PERSONAL);
    }

    @Override
    public boolean insertar(String[] valores) {
        return INSERTAR(valores);
    }

    @Override
    public boolean eliminar(String where) {
        return cn.delete(tabla, where);
    }

    @Override
    public boolean actualizar(String campo, String valor, String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(String[] campos, String[] valores, String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OPersonal get(String where) {
        ArrayList<Objeto> get = GET("*", where);
        if (get == null) {
            return null;
        }
        OPersonal o = (OPersonal) get.get(0);
        get.clear();
        return o;
    }

    @Override
    public ArrayList<OPersonal> getLista(String where) {
        ArrayList<Objeto> get = GET("*", where);
        if (get == null) {
            return null;
        }
        ArrayList<OPersonal> lista = new ArrayList<>(get.size());
        for (Objeto objeto : get) {
            lista.add((OPersonal) objeto);
        }
        get.clear();
        return lista;
    }

}
