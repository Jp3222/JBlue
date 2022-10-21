/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.funciones.op;

import com.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public interface FuncionesAbstractas {

    public boolean insert();

    public boolean delete();

    public boolean update();

    public <T extends Objeto> T get(String where);

    public <T extends Objeto> ArrayList<T> getAll();

}
