/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.modelo.ConstBD;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.tiempo.Fecha;
import com.jblue.util.tiempo.Hora;

/**
 *
 * @author jp
 */
public abstract class Movimientos {

    private String info[];

    public void mov(int TIPO) {
    }

    /**
     * Registra un inicio de sesion en la base de datos
     *
     * @param personal
     * @return true solo si el registro se hizo correctamente
     */
    public boolean _inicioSesion(OPersonal personal) {
        return _registro("1", personal);
    }

    /**
     * Registra un fin de sesion en la base de datos
     *
     * @param personal
     * @return true solo si el registro se hizo correctamente
     */
    public boolean _finSesion(OPersonal personal) {
        boolean rt = _registro("2", personal);
        return rt;
    }

    void _insertarSesion() {
    }

    void _eliminarSesion() {
    }

    void _actualizarSesion() {
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }

    /**
     * Metodo que hace un registro a la tabla "historial de movimientos" en la
     * base de datos
     *
     * @param mov - dinel el tipo de moviminto
     * <br> "1" si el movimiento es un inicio de sesion
     * <br> "2" si el movimiento es un fin de sesion
     *
     * @return true solo si el registro se hizo correctamente
     */
    boolean _registro(String mov, OPersonal personal) {
        Fecha fc = new Fecha();
        Hora hr = new Hora();
        String user = personal.getId();
        String fecha = fc.getNewFechaActualString();
        String hora = hr.getHoraActualString();
        //
        Operaciones op = new Operaciones(ConstBD.TABLAS[4], ConstBD.BD_HISTORIAL_MOVIMIENTOS);
        String[] valores = {user, mov, fecha, hora};
        return op.insertar(valores, null);
    }
}
