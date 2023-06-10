/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.normas;

import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author jp
 */
public interface Permisos {

    /**
     * Este metodo esta pensando para verificar los permisos de lectura y
     * escritura y bloquear los componentes llamando al metodo por defecto
     * _bloquean
     */
    void permisos();

    /**
     * Este metodo esta pensando para usarse dentro del metodo permisos con el
     * proposito de bloquear o desbloquear componentes segun los permios del
     * personal
     *
     * @param estado de bloqueo de los componentes
     * @param componentes - comopoentes que deben bloquear o desbloquearse
     */
    default void _bloquear(boolean estado, JComponent... componentes) {
        for (JComponent componente : componentes) {
            componente.setEnabled(estado);
        }
    }

}
