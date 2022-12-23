/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.funciones;

import com.jblue.modelo.Const;
import com.jblue.modelo.funciones.op.FuncionesAbstractas;
import com.jblue.modelo.funciones.op.FuncionesEnvoltorio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.crypto.EncriptadoAES;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author jp
 */
public class OpPersonal extends FuncionesEnvoltorio implements FuncionesAbstractas {

    public OpPersonal() {
        super(Const.TABLAS[0], Const.BD_PERSONAL);
    }

    @Override
    public boolean insertar(String[] valores) {
        try {
            EncriptadoAES o = new EncriptadoAES();
            String x = valores[valores.length - 2],
                    y = valores[valores.length - 1];
            String usuario = o.encriptar(valores[valores.length - 2], y);
            String pass = o.encriptar(valores[valores.length - 1], x);
            valores[valores.length - 2] = usuario;
            valores[valores.length - 2] = pass;
            return INSERTAR(valores);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            System.out.println("error: " + ex.getMessage());
            ex.printStackTrace(pwExeption);
            closeExeptionBuffer();
            return false;
        }
    }

    @Override
    public boolean eliminar(String where) {
        return CONEXION.delete(TABLA, where);
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
        ArrayList<OPersonal> obj = GET("*", where);
        if (obj == null) {
            return null;
        }
        OPersonal o = obj.get(0);
        obj.clear();
        return o;
    }

    @Override
    public ArrayList<OPersonal> getLista(String where) {
        ArrayList<OPersonal> lista = GET("*", where);
        if (lista == null) {
            return null;
        }
        return lista;
    }

}
