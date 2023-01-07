/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.controlador;

import com.jbd.conexion.Conexion;
import com.jblue.modelo.ConstBD;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OTitulares;
import com.jblue.util.FormatoBD;
import com.jblue.util.Mensajes;
import com.jblue.util.Validar;
import com.jblue.util.modelosgraficos.envoltorios.MovTablas;
import com.jblue.vista.ventanas.MenuBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class CMenuBD {

    private final Operaciones<OCalles> OP_CALLES;
    private final Operaciones<OTipoTomas> OP_TTOMAS;
    private final Operaciones<OTipoTomas> OP_TITULARES;
    private final MenuBD MENU_BD;
    //private final EnvoltorioTablas<OCalles> ENV_CALLES;
    private final MovTablas<OCalles> MOV_CALLES;
    private final MovTablas<OTipoTomas> MOV_TIPOS_TOMAS;
    private final MovTablas<OTitulares> MOV_TITULARES;

    public CMenuBD(MenuBD MENU_BD) {
        this.MENU_BD = MENU_BD;
        //Operaciones
        this.OP_CALLES = new Operaciones(ConstBD.TABLAS[3], ConstBD.BD_CALLES);
        this.OP_TITULARES = new Operaciones(ConstBD.TABLAS[1], ConstBD.BD_USUARIOS);
        this.OP_TTOMAS = new Operaciones(ConstBD.TABLAS[4], ConstBD.BD_TIPOS_DE_TOMAS);
        //Mov Tablas
        this.MOV_CALLES = new MovTablas(MENU_BD.getJtCalles(), OP_CALLES);
        this.MOV_TIPOS_TOMAS = new MovTablas(this.MENU_BD.getJtTiposTomas(), OP_TTOMAS);
        this.MOV_TITULARES = new MovTablas(this.MENU_BD.getJtTitulares(), OP_TITULARES);
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
        boolean insertar = OP_CALLES.insertar(o, null);
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
                OP_CALLES.getCAMPOS(), o, "id = '" + o[0] + "'", null);
        if (actualizar) {
            Mensajes.Mensaje(2, 0);
            MOV_CALLES.set(row, o);
            MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(2, 1);
        }
    }

    public void removeCalle(int row, String id) {
        boolean remove = OP_CALLES.eliminar("id = '" + id + "'", null);
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
            MENU_BD.initPanelTTomas();
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
            MENU_BD.initPanelTTomas();
        } else {
            Mensajes.Mensaje(2, 1);
        }
    }

    public void removeTToma(int row, String id) {
        boolean remove = OP_TTOMAS.eliminar("id = '" + id + "'", null);
        if (remove) {
            Mensajes.Mensaje(1, 0);
            MOV_TIPOS_TOMAS.remove(row);
            MENU_BD.initPanelTTomas();
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
    //
    //
    // <editor-fold defaultstate="collapsed" desc="Funciones de la tabla titulares">                 
    public void addTitular(String... o) {
        boolean datosNULL = Validar.datosNULL(o);
//        boolean soloNumero1 = Validar.isSoloNumero(o[2]);
//        boolean soloNumero2 = Validar.isSoloNumero(o[3]);
        if (datosNULL) {
            Mensajes.DatosNoValidos();
            return;
        }
        o = FormatoBD.bdEntrada(o);
        boolean insertar = OP_TITULARES.insertar(o, null);
        if (insertar) {
            Mensajes.Mensaje(0, 0);
            MOV_TITULARES.add(o);
            //MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(0, 1);
        }
    }

    public void setTitular(int row, String... o) {
        boolean datosNULL = Validar.datosNULL(o);
//        boolean soloNumero1 = Validar.isSoloNumero(o[2]);
//        boolean soloNumero2 = Validar.isSoloNumero(o[3]);
        if (datosNULL) {
            Mensajes.DatosNoValidos();
            return;
        }
        o = FormatoBD.bdEntrada(o);
        boolean actualizar = OP_TITULARES.actualizar(
                OP_TITULARES.getCAMPOS(), o, "id = '" + o[0] + "'", null);
        if (actualizar) {
            Mensajes.Mensaje(2, 0);
            MOV_TITULARES.set(row, o);
            //MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(2, 1);
        }
    }

    public void removeTitular(int row, String id) {
        boolean remove = OP_TITULARES.eliminar("id = '" + id + "'", null);
        if (remove) {
            Mensajes.Mensaje(1, 0);
            MOV_TITULARES.remove(row);
            //MENU_BD.initPanelCalles();
        } else {
            Mensajes.Mensaje(1, 1);
        }
    }

    public void llenarDatosTitular() {
        MOV_TITULARES.llenar();
    }

    public void vaciarDatosTitular() {
        MOV_TITULARES.vaciar();
    }

    public ArrayList<String> getListaCalles() {
        Conexion conexion = OP_CALLES.getCONEXION();
        try {
            ResultSet rs = conexion.select(ConstBD.TABLAS[3]);
            ArrayList<String> lista = new ArrayList<>(30);
            while (rs.next()) {
                String str = rs.getString(1) + " " + rs.getString(2) + rs.getString(3);
                lista.add(str);
            }
            conexion.closeRS();
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<String> getListaTTomas() {
        Conexion conexion = OP_CALLES.getCONEXION();
        try {
            ResultSet rs = conexion.select(ConstBD.TABLAS[4]);
            ArrayList<String> lista = new ArrayList<>(30);
            while (rs.next()) {
                String str = rs.getString(1) + " " + rs.getString(2);
                lista.add(str);
            }
            conexion.closeRS();
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //</editor-fold> 
}
