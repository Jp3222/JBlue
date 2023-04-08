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
 * Esta clase define al usuario de sesion actual, quien hace uso del programa,
 * por lo cual esta clase solo se puede instanciar una vez
 *
 * @author jp
 */
public class Sesion {

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
     * Variable que guarda el usuario que ha iniciado sesion
     *
     */
    private OPersonal usuario;

    private Sesion() {

    }

    public OPersonal getUsuario() {
        return usuario;
    }

    public void setUsuario(OPersonal usuario) {
        this.usuario = usuario;
    }

    /**
     * Registra un inicio de sesion en la base de datos
     *
     * @return true solo si el registro se hizo correctamente
     */
    public boolean inicioSesion() {
        return registro("1");
    }

    /**
     * Registra un fin de sesion en la base de datos
     *
     * @return true solo si el registro se hizo correctamente
     */
    public boolean finSesion() {
        boolean rt = registro("2");
        usuario = null;
        return rt;
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
    boolean registro(String mov) {
        Fecha fc = new Fecha();
        Hora hr = new Hora();
        String user = usuario.getId();
        String fecha = fc.getNewFechaActualString();
        String hora = hr.getHoraActualString();
        //
        Operaciones op = new Operaciones(ConstBD.TABLAS[4], ConstBD.BD_HISTORIAL_MOVIMIENTOS);
        String[] valores = {user, mov, fecha, hora};
        return op.insertar(valores, null);
    }
}
