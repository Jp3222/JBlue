/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.marco.ventanas;

import javax.swing.JFrame;
import com.jblue.sistema.app.AppInfo;
import java.awt.HeadlessException;

/**
 *
 * @author jp
 */
public abstract class VentanaSimple extends JFrame implements AppInfo {

    public VentanaSimple() {
    }

    public VentanaSimple(String[] _TITULOS_VEN) throws HeadlessException {
        this._TITULOS_VEN = _TITULOS_VEN;
    }

    public VentanaSimple(int _TITULO, String[] _TITULOS_VEN) throws HeadlessException {
        this._TITULO = _TITULO;
        this._TITULOS_VEN = _TITULOS_VEN;
        updateTitle();
    }

    /**
     * <br> 0 - "nueva ventana"
     * <br> 1 - "Inicio de sesion"
     * <br> 2 - "Menu Principal"
     * <br> 3 - "BD usuarios"
     * <br> 4 - "BD calles"
     * <br> 5 - "BD tipo de tomas"
     * <br> 6 - "Menu Tesoreria"
     * <br> 7 - "Menu Presidente"
     * <br> 8 - "Tomas Registradas"
     * <br> 9 - "Perfil de Usuario"
     * <br> 10 - "Administrador de Directorios"
     * <br> 11 - "Administrador de Documentos"
     * <br> 12 - "Comprobantes"
     * <br> 13 - "Administrador"
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
        setIconImage(AppInfo._ICONO_DEL_PROGRAMA.getImage());
    }

    /**
     * Metodo recomendado para agregar componentes a otros componentes
     */
    protected void construirComponentes() {
    }

    /**
     * Metodo recomendado para añadir eventos a los componentes definidos
     */
    protected void manejoEventos() {
    }

    protected final void updateTitle() {
        StringBuilder s = new StringBuilder(NOMBRE_DEL_PROGRAMA);
        if (_TITULO != 0) {
            s.append(" ");
            s.append(VERSION_DEL_PROGRAMA);
            s.append(" - ").append(_TITULOS_VEN[_TITULO]);
            this.setTitle(s.toString());
        }
    }

    private String[] _TITULOS_VEN;

    public void setTITULOS_VEN(String[] _TITULOS_VEN) {
        this._TITULOS_VEN = _TITULOS_VEN;
    }

}
