/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.funciones;

import com.jblue.modelo.Const;
import com.jblue.modelo.funciones.op.FuncionesAbstractas;
import com.jblue.modelo.funciones.op.FuncionesEnvoltorio;
import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class OpConsumidores extends FuncionesEnvoltorio implements FuncionesAbstractas {

    public OpConsumidores() {
        super("consumidores", Const.BD_CONSUMIDORES);
    }

    @Override
    public boolean insertar(String[] valores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public <T extends Objeto> T get(String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T extends Objeto> ArrayList<T> getLista(String where) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
