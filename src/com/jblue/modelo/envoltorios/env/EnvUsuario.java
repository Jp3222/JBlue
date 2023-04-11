/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.envoltorios.env;

import com.jblue.modelo.envoltorios.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.cache.FabricaCache;
import com.jblue.util.cache.FabricaOpraciones;
import com.jblue.util.cache.MemoCache;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class EnvUsuario {

    public static OCalles getCalle(String txt) {
        MemoCache<OCalles> calles = FabricaCache.MC_CALLES;
        ArrayList<OCalles> memoria = calles.getLista();
        OCalles o = null;
        for (OCalles i : memoria) {
            if (i.getId().equals(txt)
                    || i.getStringR().equalsIgnoreCase(txt)) {
                o = i;
            }
        }
        return o;
    }

    public static OTipoTomas getTipo_De_Toma(String txt) {
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
        OUsuarios aux = null;
        for (OUsuarios i : lista) {
            if (i.getId().equals(txt) || i.getStringR().equalsIgnoreCase(txt)) {
                aux = i;
                return aux;
            }
        }
        return aux;
    }

    public static int getMesesPagados(String año, String id) {
        Operaciones<OPagosServicio> op = FabricaOpraciones.PAGOS_X_SERVICIO;
        ArrayList<OPagosServicio> lista = op.getLista("año = " + año + " and usuario = " + id);
        int size = lista.size();
        lista.clear();
        return size;
    }

}
