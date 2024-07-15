/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.sistema.bdmov.Movimientos;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.crypto.EncriptadoAES;
import com.jblue.util.tiempo.Fecha;
import com.jblue.util.tiempo.Hora;
import com.jutil.jexception.Excp;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
    private Contraseña contraseña;

    private Sesion() {
        contraseña = new Contraseña();
    }

    public OPersonal getUsuario() {
        return personal;
    }

    public void setUsuario(OPersonal personal) {
        this.personal = personal;
        contraseña.setPersonal(personal);
    }

    public boolean validaciones() {
        return _tiempo() && _horaValido();
    }

    boolean _accesoValido() {
        if (personal == null) {
            return false;
        }
        return personal.getEstado() != 1;
    }

    boolean _horaValido() {
        return Hora.getHoraActual().compareTo(LocalTime.of(19, 0, 0)) > 0;
    }

    boolean _tiempo() {
        return Fecha.getNewFechaActual().getDayOfWeek() == DayOfWeek.SATURDAY;
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

//    public boolean contraseñaValida(String txt) {
//        String usuario = desUsuario(txt);
//
//    }
//
//    public boolean usuarioValido(String txt) {
//
//    }

    public String desUsuario(String contraseña) {
        try {
            return EncriptadoAES.desencriptar(personal.getUsuario(), contraseña);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Sesion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static class Contraseña {

        private OPersonal personal;
        private String usuario, contraseña;
        private boolean encriptado;

        public void setPersonal(OPersonal personal) {
            this.personal = personal;
        }

        public void desbloquear(String contraseña) {
            try {
                this.usuario = EncriptadoAES.desencriptar(personal.getUsuario(), contraseña);
            } catch (UnsupportedEncodingException
                    | NoSuchAlgorithmException
                    | InvalidKeyException
                    | NoSuchPaddingException
                    | IllegalBlockSizeException
                    | BadPaddingException ex) {
                Excp.impTerminal(ex, getClass(), true);
            }
        }
        
    }
}
