/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.sistema.so.ConstructorDeArchivos;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import com.jblue.util.plataformas.soconfig.apariencia;
import com.jblue.vista.ventanas.MenuConfigBD;
import com.jblue.vista.ventanas.Login;
import com.jutil.jbd.conexion.Conexion;
import com.jutil.jexception.Excp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

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
    private final MenuConfigBD config;
    private final Archivos archivos;

    private Login log;
    private String usuario, contraseña, url;

    private Conexion cn;
    private boolean reinicio;

    private Sistema() {
        this.archivos = new Archivos();
        this.reinicio = false;
        config = new MenuConfigBD();
        apariencia.setDefault();
    }

    public boolean archivosSistema() {
        ConstructorDeArchivos constructor = archivos.getArchivos();

        if (constructor.isEmpty() == 0) {
            return true;
        }

        switch (constructor.isEmpty()) {
            case 1 ->
                constructor.construirArchivos();
            case 2 ->
                constructor.construirDirectorios();
            case 3 -> {
                archivos.cargar();
                constructor.construirTodos();
            }
        }
        constructor.print();
        return true;
    }

    public synchronized boolean conexionBD() {
        try {
            ConstructorDeArchivos constructor = archivos.getArchivos();
            File usuario_file = constructor.get(constructor.ARCHIVO, archivos.USUARIO_BD);
            BufferedReader fr = new BufferedReader(new FileReader(usuario_file));
            String linea = fr.readLine();
            if (linea == null) {
                synchronized (config) {
                    Thread t = new Thread(() -> config.setVisible(true));
                    t.start();
                    config.wait();
                    linea = fr.readLine();
                }
            }

            String[] split = linea.split(",");

            usuario = split[0];
            usuario = split[1];
            usuario = split[2];

            cn = Conexion.getInstancia(split[0], split[1], split[2]);

            return !cn.getConexion().isClosed();
        } catch (SQLException | IOException | InterruptedException ex) {
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
        //
        MC_USUARIOS.setQuery("titular > 0");
        MC_USUARIOS.cargar();
        //
        MC_USUARIOS.ordenarPorID();
        return MC_CALLES.getLista() != null
                && MC_PERSONAL.getLista() != null
                && MC_TIPOS_DE_TOMAS.getLista() != null
                && MC_USUARIOS.getLista() != null;
    }

    public synchronized boolean run() {
        this.reinicio = false;
        log = new Login(config);
        log.setVisible(true);
        return log.isVisible() && !config.isVisible();
    }

    public boolean isReinicio() {
        if (reinicio) {
            System.out.println("REINICIANDO SISTEMA");
        }
        return reinicio;
    }

    public Archivos getArchivos() {
        return archivos;
    }

    public void setReinicio(boolean reinicio) {
        this.reinicio = reinicio;
    }

    public Conexion getCn() {
        return cn;
    }

}
