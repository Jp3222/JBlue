/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.conf;

import javax.swing.JPanel;

/**
 *
 * @author jp
 */
public abstract class SuperVista extends JPanel {

    /**
     * Metodo recomentado para invocar solo a los metodos definidos siguiendo el
     * siguiente orden
     * <br> - estado final
     * <br> - estado inicial
     * <br> - estado add componentes
     * <br> - estado add eventos
     * <br> una vez sobre escrito el metodo, tambien se recomienda ponerlo en
     * estado "final" para poder invocarlo en el constructor
     */
    protected abstract void llamable();

    /**
     * Metodo recomendado para darle a ciertos componentes un estado inicial al
     * cual dado un evento en su clase o alguna clase ajena todos los
     * componentes puedan volver a dicho estado
     *
     */
    public abstract void estadoInicial();

    /**
     * Metodo recomentado para darle a ciertos compoentes un estado final el
     * cual no sera cambiado
     */
    protected void estadoFinal() {
    }

    /**
     * Metodo recomendado para agregar componentes a otros componentes
     */
    protected void addComponentes() {
    }

    /**
     * Metodo recomendado para añadir eventos a los componentes definidos
     */
    protected void addEventos() {
    }

}
