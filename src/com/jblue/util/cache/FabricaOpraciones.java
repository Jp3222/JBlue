/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.cache;

import com.jblue.modelo.ConstBD;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OHisMovimientos;
import com.jblue.modelo.objetos.OPagosOtros;
import com.jblue.modelo.objetos.OPagosRecargos;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;

/**
 *
 * @author jp
 */
public class FabricaOpraciones {

    public static final Operaciones<OPersonal> PERSONAL = new Operaciones(ConstBD.TABLAS[0], ConstBD.BD_PERSONAL);
    public static final Operaciones<OUsuarios> USUARIOS = new Operaciones(ConstBD.TABLAS[1], ConstBD.BD_USUARIOS);
    public static final Operaciones<OCalles> CALLES = new Operaciones(ConstBD.TABLAS[2], ConstBD.BD_CALLES);
    public static final Operaciones<OTipoTomas> TIPOS_DE_TOMAS = new Operaciones(ConstBD.TABLAS[3], ConstBD.BD_TIPOS_DE_TOMAS);
    public static final Operaciones<OHisMovimientos> HISTORIAL_DE_MOVIMIENTOS = new Operaciones(ConstBD.TABLAS[4], ConstBD.BD_HISTORIAL_MOVIMIENTOS);
    public static final Operaciones<OPagosServicio> PAGOS_X_SERVICIO = new Operaciones(ConstBD.TABLAS[6], ConstBD.BD_PAGOS_X_SERVICIO);
    public static final Operaciones<OPagosRecargos> PAGOS_X_RECARGOS = new Operaciones(ConstBD.TABLAS[7], ConstBD.BD_PAGOS_X_RECARGO);
    public static final Operaciones<OPagosOtros> PAGOS_X_OTROS = new Operaciones(ConstBD.TABLAS[8], ConstBD.BD_PAGOS_X_OTROS);

    public static Operaciones<OCalles> getCALLES() {
        return CALLES;
    }

    public static Operaciones<OHisMovimientos> getHISTORIAL_DE_MOVIMIENTOS() {
        return new Operaciones(ConstBD.TABLAS[4], ConstBD.BD_HISTORIAL_MOVIMIENTOS);
    }

    public static Operaciones<OPagosOtros> getPAGOS_X_OTROS() {
        return new Operaciones(ConstBD.TABLAS[8], ConstBD.BD_PAGOS_X_OTROS);
    }

    public static Operaciones<OPagosRecargos> getPAGOS_X_RECARGOS() {
        return new Operaciones(ConstBD.TABLAS[7], ConstBD.BD_PAGOS_X_RECARGO);
    }

    public static Operaciones<OPagosServicio> getPAGOS_X_SERVICIO() {
        return new Operaciones(ConstBD.TABLAS[6], ConstBD.BD_PAGOS_X_SERVICIO);
    }

    public static Operaciones<OPersonal> getPERSONAL() {
        return new Operaciones(ConstBD.TABLAS[0], ConstBD.BD_PERSONAL);
    }

    public static Operaciones<OTipoTomas> getTIPOS_DE_TOMAS() {
        return new Operaciones(ConstBD.TABLAS[3], ConstBD.BD_TIPOS_DE_TOMAS);
    }

    public static Operaciones<OUsuarios> getUSUARIOS() {
        return new Operaciones(ConstBD.TABLAS[1], ConstBD.BD_USUARIOS);
    }

}
