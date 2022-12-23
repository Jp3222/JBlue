/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jblue.modelo.Const;
import com.jblue.modelo.funciones.OpCalles;
import com.jblue.modelo.funciones.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.util.FormatoBD;
import com.jblue.util.Mensajes;
import com.jblue.util.Validar;
import com.jblue.util.modelosgraficos.envoltorios.MovTablas;
import com.jblue.vista.ventanas.MenuBD;

/**
 *
 * @author jp
 */
public class CMenuBD {

    private final OpCalles OP_CALLES;
    private final Operaciones<OTipoTomas> OP_TTOMAS;
    private final MenuBD MENU_BD;
    //private final EnvoltorioTablas<OCalles> ENV_CALLES;
    private final MovTablas<OCalles> MOV_CALLES;
    private final MovTablas<OTipoTomas> MOV_TIPOS_TOMAS;

    public CMenuBD(MenuBD MENU_BD) {
        this.MENU_BD = MENU_BD;
        this.OP_CALLES = new OpCalles();
        this.OP_TTOMAS = new Operaciones(Const.TABLAS[4], Const.BD_TIPOS_DE_TOMAS);
        //this.ENV_CALLES = new EnvoltorioTablas(MENU_BD.getJtCalles(), OP_CALLES.getLista("id > 0"), new OCalles());

        this.MOV_CALLES = new MovTablas(MENU_BD.getJtCalles(), new Operaciones(Const.TABLAS[3], Const.BD_CALLES));
        this.MOV_TIPOS_TOMAS = new MovTablas(this.MENU_BD.getJtTiposTomas(), OP_TTOMAS);
    }

    // <editor-fold defaultstate="collapsed" desc="Funciones de la tabla calles">                 
    public void addCalles(String... o) {
        boolean datosNULL = Validar.datosNULL(o);
        boolean soloNumero1 = Validar.isSoloNumero(o[2]);

        if (datosNULL || !soloNumero1) {
            Mensajes.DatosNoValidos();
            return;
        }
        o = FormatoBD.bdEntrada(o);
        boolean insertar = OP_CALLES.insertar(o);
        if (insertar) {
            Mensajes.Mensaje(0, 0);
            MOV_CALLES.add(o);
            MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(0, 1);
        }
    }

    public void setCalles(int row, String... o) {
        boolean datosNULL = Validar.datosNULL(o);
        boolean soloNumero1 = Validar.isSoloNumero(o[2]);

        if (datosNULL && !soloNumero1) {
            Mensajes.DatosNoValidos();
            return;
        }
        o = FormatoBD.bdEntrada(o);
        boolean actualizar = OP_CALLES.actualizar(
                OP_CALLES.getCAMPOS(), o, "id = '" + o[0] + "'");
        if (actualizar) {
            Mensajes.Mensaje(2, 0);
            MOV_CALLES.set(row, o);
            MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(2, 1);
        }
    }

    public void removeCalle(int row, String id) {
        boolean remove = OP_CALLES.eliminar("id = '" + id + "'");
        if (remove) {
            Mensajes.Mensaje(1, 0);
            MOV_CALLES.remove(row);
            MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(1, 1);
        }
    }

    public void llenarDatosCalles() {
        MOV_CALLES.llenar();
    }

    public void vaciarDatosCalles() {
        MOV_CALLES.vaciar();
    }

    //</editor-fold> 
    //
    //
    // <editor-fold defaultstate="collapsed" desc="Funciones de la tabla tomas">                 
    public void addTToma(String... o) {
        boolean datosNULL = Validar.datosNULL(o);
        boolean soloNumero1 = Validar.isSoloNumero(o[2]);
        boolean soloNumero2 = Validar.isSoloNumero(o[3]);
        if (datosNULL || !soloNumero1 || !soloNumero2) {
            Mensajes.DatosNoValidos();
            return;
        }
        o = FormatoBD.bdEntrada(o);
        boolean insertar = OP_TTOMAS.insertar(o, null);
        if (insertar) {
            Mensajes.Mensaje(0, 0);
            MOV_TIPOS_TOMAS.add(o);
            MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(0, 1);
        }
    }

    public void setTToma(int row, String... o) {
        boolean datosNULL = Validar.datosNULL(o);
        boolean soloNumero1 = Validar.isSoloNumero(o[2]);
        boolean soloNumero2 = Validar.isSoloNumero(o[3]);
        if (datosNULL || !soloNumero1 || !soloNumero2) {
            Mensajes.DatosNoValidos();
            return;
        }
        o = FormatoBD.bdEntrada(o);
        boolean actualizar = OP_TTOMAS.actualizar(
                OP_TTOMAS.getCAMPOS(), o, "id = '" + o[0] + "'", null);
        if (actualizar) {
            Mensajes.Mensaje(2, 0);
            MOV_TIPOS_TOMAS.set(row, o);
            MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(2, 1);
        }
    }

    public void removeTToma(int row, String id) {
        boolean remove = OP_TTOMAS.eliminar("id = '" + id + "'", null);
        if (remove) {
            Mensajes.Mensaje(1, 0);
            MOV_TIPOS_TOMAS.remove(row);
            MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(1, 1);
        }
    }

    public void llenarDatosTToma() {
        MOV_TIPOS_TOMAS.llenar();
    }

    public void vaciarDatosTToma() {
        MOV_TIPOS_TOMAS.vaciar();
    }

    //</editor-fold> 
}
