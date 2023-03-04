/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.vista.ventanas.Login;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.plataformas.soconfig.apariencia;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * Sistema
 * <br> Esta clase es encargada de iniciar todos los datos del sistema
 *
 * @author jp
 */
public class Sistema {

    private static Sistema instancia;

    public synchronized static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    private Conexion cn;
    private boolean reinicio;

    private Sistema() {
        this.reinicio = false;
    }

    public boolean archivosSistema() {
        apariencia.setDefault();
        return true;
    }

    public boolean confgSistema() {

        return true;
    }

    public boolean conexionBD() {
        try {
            cn = Conexion.getInstancia("jp", "12345", "jdbc:mysql://localhost/jblue");
            return !cn.getConexion().isClosed();
        } catch (SQLException ex) {
            Excp.impTerminal(ex, this.getClass(), true);
        }
        return false;
    }

    public boolean datosCache() {
        int i = 0;
        MemoCache<OCalles> MC_CALLES = FabricaCache.MC_CALLES;
        MC_CALLES.cargar();

        MemoCache<OPersonal> MC_PERSONAL = FabricaCache.MC_PERSONAL;
        MC_PERSONAL.cargar();

        MemoCache<OTipoTomas> MC_TIPOS_DE_TOMAS = FabricaCache.MC_TIPOS_DE_TOMAS;
        MC_TIPOS_DE_TOMAS.cargar();

        MemoCache<OUsuarios> MC_USUARIOS = FabricaCache.MC_USUARIOS;
        MC_USUARIOS.cargar();

        return MC_CALLES.getLista() != null
                && MC_PERSONAL.getLista() != null
                && MC_TIPOS_DE_TOMAS.getLista() != null
                && MC_USUARIOS.getLista() != null;
    }
    private Login log;

    public synchronized boolean run() {
        this.reinicio = false;
        log = new Login();
        log.setVisible(true);
        return log.isVisible();
    }

    public boolean isReinicio() {
        if (reinicio) {
            System.out.println("REINICIANDO SISTEMA");
        }
        return reinicio;
    }

    public void setReinicio(boolean reinicio) {
        this.reinicio = reinicio;
    }

    public Conexion getCn() {
        return cn;
    }

}
