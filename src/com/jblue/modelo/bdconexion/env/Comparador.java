/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.bdconexion.env;

import com.jblue.util.modelo.objetos.Objeto;

/**
 *
 * @author jp
 * @param <T>
 * @param <K>
 */
@FunctionalInterface
public interface Comparador<T extends Objeto, K extends Objeto> {

    public int comparador(T x, K y);
}
