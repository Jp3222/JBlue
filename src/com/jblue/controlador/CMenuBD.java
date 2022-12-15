/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.modelo.funciones.OpCalles;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.vista.modelos.envoltorios.EnvoltorioTablas;
import com.jblue.vista.ventanas.MenuBD;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class CMenuBD {

    private final OpCalles OP_CALLES;
    private final MenuBD MENU_BD;
    private final EnvoltorioTablas<OCalles> ENV_CALLES;

    public CMenuBD(MenuBD MENU_BD) {
        this.MENU_BD = MENU_BD;
        this.OP_CALLES = new OpCalles();
        this.ENV_CALLES = new EnvoltorioTablas(MENU_BD.getJtCalles(), OP_CALLES.getLista("id > 0"), new OCalles());
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones de la tabla calles">                 
    public void addCalles(String... o) {
        boolean insertar = OP_CALLES.insertar(o);
        if (insertar) {
            Mensaje(0, 0);
            ENV_CALLES.add(o);
            MENU_BD.initPanelCalles();
        } else {
            Mensaje(0, 1);
        }
    }

    public void setCalles(int row, String... o) {
        boolean actualizar = OP_CALLES.actualizar(
                OP_CALLES.getCampos(), o, "id = '" + o[0] + "'");
        if (actualizar) {
            Mensaje(2, 0);
            ENV_CALLES.set(row, o);
            MENU_BD.initPanelCalles();
        } else {
            Mensaje(2, 1);
        }
    }

    public void removeCalle(int row, String id) {
        boolean remove = OP_CALLES.eliminar("id = '" + id + "'");
        if (remove) {
            Mensaje(1, 0);
            ENV_CALLES.remove(row);
            MENU_BD.initPanelCalles();
        } else {
            Mensaje(1, 1);
        }
    }

    public void mostrarDatosCalles() {
        ENV_CALLES.mostrarDatos();
    }

    public void ocultarDatosCalles() {
        ENV_CALLES.ocultarDatos();
    }

    //</editor-fold> 
    public void addTTomas(String... o) {
    }

    public void setTTomas(int row, String... o) {
    }

    public void removeTTomas(int row, String index) {
    }

    public void mostrarDatosTTomas() {
    }

    public void ocultarDatosTTomas() {
    }

    /**
     * Metdo para lanza un mensa
     *
     * @param operaccion tipo de operaccion que se realizo:
     * <br>0 = "inserccion"
     * <br>1 = "eliminacion"
     * <br>2 = "actualizacion"
     * @param estado indicador del estado de la operaccion:
     * <br>0 = "exitoso"
     * <br>1 = "erroneo"
     */
    public void Mensaje(int operaccion, int estado) {
        String[] operaciones = {
            "inserccion", "eliminacion", "actualizacion"
        };
        String[] estados = {
            "exitosa", "erronea"
        };
        JOptionPane.showMessageDialog(MENU_BD, operaciones[operaccion] + " " + estados[estado]);
    }

}
