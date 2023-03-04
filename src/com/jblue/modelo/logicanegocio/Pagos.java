/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.logicanegocio;

import com.jblue.modelo.ConstBD;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.envoltorios.env.EnvUsuario;
import com.jblue.modelo.objetos.OPagos;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.tiempo.Fecha;

/**
 *
 * @author jp
 */
public class Pagos {

    private final Operaciones<OPagos> operaciones;
    private Fecha fecha;

    public Pagos() {
        this.operaciones = new Operaciones("pagos", ConstBD.BD_USUARIOS);
        fecha = new Fecha();
    }

    public boolean registrarPago(OUsuarios o, String mesesPagados, String dineroIngresado) {
        int meses = Integer.parseInt(mesesPagados);
        int dinero = Integer.parseInt(dineroIngresado);
        if (!mesesValidos(o, meses)) {
            return false;
        }
        OTipoTomas tipo_Toma = EnvUsuario.getTipo_Toma(o.getToma());
        int total = tipo_Toma.getCosto() * meses;
        if (total > dinero) {
            return false;
        }

        return true;
    }

    public boolean mesesValidos(OUsuarios usuario, int meses) {
        if (meses > 12) {
            return false;
        }
        String query = "id = " + usuario.getId() + " ";
        query += "and anio = " + fecha.getFechaActual().getYear();
        operaciones.get(query);
        return true;
    }

    public int atrasos(OUsuarios usuario, OTipoTomas toma) {
        return -1;
    }

}
