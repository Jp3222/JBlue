/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.envoltorios.env;

import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.modelo.objetos.Objeto;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.MemoCache;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class EnvUsuario {

    public static <T extends Objeto, K extends Objeto> int compareTo(T x, K y, Comparador<T, K> fun) {
        return fun.comparador(x, y);
    }

    public static OCalles getCalle(String txt) {
        MemoCache<OCalles> calles = FabricaCache.MC_CALLES;
        ArrayList<OCalles> memoria = calles.getLista();
        OCalles o = null;
        for (OCalles i : memoria) {
            if (i.getId().equals(txt) 
                    || i.getCalleStr().equalsIgnoreCase(txt)) {
                o = i;
            }
        }
        return o;
    }

    public static OTipoTomas getTipo_Toma(String txt) {
        MemoCache<OTipoTomas> tomas = FabricaCache.MC_TIPOS_DE_TOMAS;
        ArrayList<OTipoTomas> memoria = tomas.getLista();
        OTipoTomas o = null;
        for (OTipoTomas i : memoria) {
            if (i.getId().equals(txt) || i.getTipo().equalsIgnoreCase(txt)) {
                o = i;
            }
        }
        return o;
    }

    public static OUsuarios getUsuario(String txt) {
        ArrayList<OUsuarios> lista = FabricaCache.MC_USUARIOS.getLista();
        OUsuarios o = null;
        for (OUsuarios oUsuarios : lista) {
            if (oUsuarios.getId().equals(txt)
                    || oUsuarios.getNombreStr().equalsIgnoreCase(txt)) {
                o = oUsuarios;
            }
        }
        return o;
    }

}
