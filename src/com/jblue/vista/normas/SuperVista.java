/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.normas;

import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author jp
 */
public abstract class SuperVista extends JPanel implements Permisos {

    protected ImageIcon icon;

    protected Set<JComponent> _componentes_bloqueados = new HashSet<>(20);

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
     * e
     * Metodo recomendado para darle a ciertos componentes un estado inicial al
     * cual dado un evento en su clase o alguna clase ajena todos los
     * componentes puedan volver a dicho estado
     *
     */
    public abstract void componentesEstadoInicial();

    /**
     * Metodo recomentado para darle a ciertos compoentes un estado final el
     * cual no sera cambiado
     */
    protected void componentesEstadoFinal() {
    }

    /**
     * Metodo recomendado para agregar componentes a otros componentes
     */
    protected void contruirComponentes() {
    }

    /**
     * Metodo recomendado para añadir eventos a los componentes definidos
     */
    protected void manejoEventos() {
    }

    @Override
    public void permisos() {

    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }
    
}
