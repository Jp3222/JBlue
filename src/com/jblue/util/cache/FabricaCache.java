/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.cache;

import com.jblue.modelo.ConstBD;
import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPersonal;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;

/**
 *
 * @author jp
 */
public abstract class FabricaCache {

    public static final Operaciones<OPersonal> OP_PERSONAL = new Operaciones(ConstBD.TABLAS[0], ConstBD.BD_PERSONAL);
    public static final Operaciones<OUsuarios> OP_USUARIOS = new Operaciones(ConstBD.TABLAS[1], ConstBD.BD_USUARIOS);
    public static final Operaciones<OCalles> OP_CALLES = new Operaciones(ConstBD.TABLAS[2], ConstBD.BD_CALLES);
    public static final Operaciones<OTipoTomas> OP_TIPOS_DE_TOMAS = new Operaciones(ConstBD.TABLAS[3], ConstBD.BD_TIPOS_DE_TOMAS);
    
    public static final MemoCache<OPersonal> MC_PERSONAL = new MemoCache(OP_PERSONAL);
    public static final MemoCache<OCalles> MC_CALLES = new MemoCache(OP_CALLES);
    public static final MemoCache<OTipoTomas> MC_TIPOS_DE_TOMAS = new MemoCache(OP_TIPOS_DE_TOMAS);
    public static final MemoCache<OUsuarios> MC_USUARIOS = new MemoCache(OP_USUARIOS);

}
