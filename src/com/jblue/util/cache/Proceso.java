/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.cache;

import com.jblue.modelo.objetos.sucls.Objeto;
import java.util.ArrayList;

@FunctionalInterface
public interface Proceso {

    <T extends Objeto>void proceso(ArrayList<T> o);

}
