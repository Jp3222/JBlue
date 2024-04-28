/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.envoltorios.op;

import com.jblue.modelo.objetos.sucls.Objeto;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public interface FuncionesAbstractas {

    boolean insertar(String[] valores);

    boolean eliminar(String where);

    boolean actualizar(String campo, String valor, String where);

    boolean actualizar(String campos[], String valores[], String where);

    <T extends Objeto> T get(String where);

    <T extends Objeto> ArrayList<T> getLista(String where);

}
