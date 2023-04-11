/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.normas;

import javax.swing.JComponent;

/**
 *
 * @author jp
 */
public interface Permisos {

    void permisos();

    default void bloqueo(JComponent... componentes) {
        for (JComponent componente : componentes) {
            componente.setEnabled(false);
        }
    }

}
