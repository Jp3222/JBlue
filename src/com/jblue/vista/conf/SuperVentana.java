/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.conf;

import com.jblue.sistema.app.InfoApp;
import javax.swing.JFrame;

/**
 *
 * @author jp
 */
public abstract class SuperVentana extends JFrame implements InfoApp {

    /**
     * Variable que define el titulo de la ventana
     * <br> 0 "nueva ventana"
     * <br> 1 "Inicio de sesion"
     * <br> 2 "Menu Principal"
     * <br> 3 "BD usuarios"
     * <br> 4 "BD calles"
     * <br> 5 "BD tipo de tomas"
     * <br> 6 "Menu Tesoreria"
     * <br> 7 "Menu Presidente"
     */
    protected int _TITULO;

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
        this.setIconImage(ICONO_DEL_PROGRAMA.getImage());
        StringBuilder s = new StringBuilder(NOMBRE_DEL_PROGRAMA);

        s.append(" ")
                .append(VERSION_DEL_PROGRAMA)
                .append(" - ").append(MENUS_DEL_PROGRAMA[_TITULO]);

        this.setTitle(s.toString());
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
