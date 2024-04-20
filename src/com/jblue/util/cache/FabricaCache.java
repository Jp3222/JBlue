/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.cache;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.OValores;
import static com.jblue.util.cache.FabricaOpraciones.PERSONAL;
import static com.jblue.util.cache.FabricaOpraciones.USUARIOS;
import static com.jblue.util.cache.FabricaOpraciones.CALLES;
import static com.jblue.util.cache.FabricaOpraciones.TIPOS_DE_TOMAS;

/**
 *
 * @author jp
 */
public abstract class FabricaCache {

    public static boolean cache;
    public static final MemoCache<OPersonal> MC_PERSONAL = new MemoCache(PERSONAL);
    public static final MemoCache<OCalles> MC_CALLES = new MemoCache(CALLES);
    public static final MemoCache<OTipoTomas> MC_TIPOS_DE_TOMAS = new MemoCache(TIPOS_DE_TOMAS);
    public static final MemoCache<OUsuarios> MC_USUARIOS = new MemoCache(USUARIOS);
    public static final MemoCache<OValores> MC_VALORES = new MemoCache(FabricaOpraciones.VALORES);

    public static boolean iniciarCache() {
        cache = true;
        
        MC_CALLES.cargar();
        MC_PERSONAL.cargar();
        MC_TIPOS_DE_TOMAS.cargar();
        MC_USUARIOS.cargar();
        MC_VALORES.cargar();
        
        return MC_CALLES.getLista() != null
                && MC_PERSONAL.getLista() != null
                && MC_TIPOS_DE_TOMAS.getLista() != null
                && MC_USUARIOS.getLista() != null
                && MC_VALORES.getLista() != null;
    }
}
