/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.sistema.bdmov.Movimientos;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.tiempo.Fecha;
import com.jblue.util.tiempo.Hora;
import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Esta clase define al personal de sesion actual, quien hace uso del programa,
 * por lo cual esta clase solo se puede instanciar una vez
 *
 * @author jp
 */
public class Sesion extends Movimientos {

    private static Sesion instancia;

    /**
     * Retorna una unica instancia de la clase Sesion
     *
     * @return
     */
    public static synchronized Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    /**
     * Variable que guarda el personal que ha iniciado sesion
     *
     */
    private OPersonal personal;

    private Sesion() {
    }

    public OPersonal getUsuario() {
        return personal;
    }

    public void setUsuario(OPersonal personal) {
        this.personal = personal;
    }

    public boolean validaciones() {
        return _tiempo() && _horaValido();
    }

    boolean _horaValido() {
        return Hora.getHoraActual().compareTo(LocalTime.of(19, 0, 0)) > 0;
    }

    boolean _tiempo() {
        Fecha o = new Fecha();
        return o.getNewFechaActual().getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean inicioSesion() {
        boolean x = _inicioSesion(personal);
        return x;
    }

    public boolean finSesion() {
        boolean x = _finSesion(personal);
        personal = null;
        return x;
    }
}
