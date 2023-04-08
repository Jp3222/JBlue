/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.media.Media;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.plataformas.SoConfig;
import com.jblue.util.plataformas.soconfig.apariencia;
import com.jblue.vista.ventanas.MenuConfigBD;
import com.jblue.vista.ventanas.Login;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sistema
 * <br> Esta clase es encargada de iniciar todos los datos del sistema
 *
 * @author jp
 */
public class Sistema {

    private static Sistema instancia;
    private final MenuConfigBD config;
    private final Archivos archivos;
    private final Media media;

    private Login log;
    private String usuario, contraseña, url;

    public synchronized static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    private Conexion cn;
    private boolean reinicio;
    private final SoConfig so;

    private Sistema() {
        this.archivos = new Archivos();
        so = new SoConfig(archivos.getArchivos());
        this.reinicio = false;
        config = new MenuConfigBD();
        media = new Media();
        So();
        media();
    }

    public void So() {
        so.createLanzador();
        so.setDefaultApariencia();
        so.ocultarFicheros();
    }

    public void media() {
        media.leer();
    }

    public boolean archivosSistema() {
        File get = archivos.getArchivos().get(archivos.getArchivos().ARCHIVO, 0);
        try (FileReader r = new FileReader(get); BufferedReader br = new BufferedReader(r)) {
            String linea = br.readLine();
            if (linea == null) {
                config.setVisible(true);
                return archivosSistema();
            }
            String[] split = linea.split(",");
            usuario = split[1];
            contraseña = split[2];
            url = split[3];
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean confgSistema() {
        apariencia.setDefault();
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
        MC_USUARIOS.setQuery("titular = -1");
        MC_USUARIOS.cargar();
        MC_USUARIOS.setQuery("titular > 0");
        MC_USUARIOS.cargar();
        MC_USUARIOS.ordenarPorID();
        return MC_CALLES.getLista() != null
                && MC_PERSONAL.getLista() != null
                && MC_TIPOS_DE_TOMAS.getLista() != null
                && MC_USUARIOS.getLista() != null;
    }

    public synchronized boolean run() {
        this.reinicio = false;
        log = new Login(null);
        log.setVisible(true);
        return log.isVisible() && !config.isVisible();
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
