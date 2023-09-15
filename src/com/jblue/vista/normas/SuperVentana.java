/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.normas;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JFrame;
import com.jblue.sistema.app.AppInfo;

/**
 *
 * @author jp
 */
public abstract class SuperVentana extends JFrame implements AppInfo, Permisos {

    protected Set<JComponent> _componentes_bloqueados = new HashSet<>(20);

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
        this.setIconImage(_ICONO_DEL_PROGRAMA.getImage());
        StringBuilder s = new StringBuilder(NOMBRE_DEL_PROGRAMA);
        if (_TITULO != 0) {
            s.append(" ");
            s.append(VERSION_DEL_PROGRAMA);
            s.append(" - ").append(_MENUS_DEL_PROGRAMA[_TITULO]);
            this.setTitle(s.toString());
        }

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

    @Override
    public void permisos() {
    }

    protected void updateTitle() {
        StringBuilder s = new StringBuilder(NOMBRE_DEL_PROGRAMA);
        if (_TITULO != 0) {
            s.append(" ");
            s.append(VERSION_DEL_PROGRAMA);
            s.append(" - ").append(_MENUS_DEL_PROGRAMA[_TITULO]);
            this.setTitle(s.toString());
        }
    }

}
