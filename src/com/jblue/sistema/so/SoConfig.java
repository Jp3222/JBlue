/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jp
 */
public abstract class SoConfig implements SoInfo {

    private final String APARIENCIA_DEL_SISTEMA;

    /**
     *
     * @param APARIENCIA_DEL_SISTEMA
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws UnsupportedLookAndFeelException
     */
    public SoConfig(String APARIENCIA_DEL_SISTEMA) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        this.APARIENCIA_DEL_SISTEMA = APARIENCIA_DEL_SISTEMA;
        UIManager.setLookAndFeel(APARIENCIA_DEL_SISTEMA);
    }

}
