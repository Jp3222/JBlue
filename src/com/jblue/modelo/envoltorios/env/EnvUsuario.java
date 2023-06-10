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
import com.jblue.util.tiempo.Fecha;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class EnvUsuario {

    public static OCalles getCalleEnCache(String txt) {
        MemoCache<OCalles> calles = FabricaCache.MC_CALLES;
        ArrayList<OCalles> memoria = calles.getLista();
        OCalles o = null;
        for (OCalles i : memoria) {
            if (i.getId().equals(txt) || i.getStringR().equalsIgnoreCase(txt)) {
                o = i;
            }
        }
        return o;
    }

    public static OTipoTomas getTipoDeTomaEnCache(String txt) {
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

    public static OUsuarios getUsuarioEnCache(String txt) {
        ArrayList<OUsuarios> lista = FabricaCache.MC_USUARIOS.getLista();
        OUsuarios aux = null;
        for (OUsuarios i : lista) {
            if (i.getId().equals(txt) || i.getStringR().equalsIgnoreCase(txt)) {
                aux = i;
                break;
            }
        }
        return aux;
    }

    public synchronized static OUsuarios getUsuarioXID(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        Operaciones<OUsuarios> op = FabricaOpraciones.getUSUARIOS();
        OUsuarios o = op.get("id = " + id);
        return o;

    }

    public synchronized static OUsuarios getUsuarioXNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }
        String[] split = nombre.split(" ");
        StringBuilder q = new StringBuilder();
        q.append("nombre = ").append(split[0]).append(" and ");
        q.append("ap = ").append(split[1]).append(" and ");
        q.append("am = ").append(split[2]);

        Operaciones<OUsuarios> op = FabricaOpraciones.USUARIOS;
        OUsuarios get = op.get(q.toString());

        return get;
    }

    public static int getMesesPagados(String año, String id) {
        Operaciones<OPagosServicio> op = FabricaOpraciones.PAGOS_X_SERVICIO;
        ArrayList<OPagosServicio> lista = op.getLista("año = " + año + " and usuario = " + id);
        int size = lista.size();
        lista.clear();
        return size;
    }

    public static String getMesesPagados(String id) {
        Fecha fh = new Fecha();
        int size = getMesesPagados(String.valueOf(fh.getNewFechaActual().getYear()), id);
        return String.valueOf(size);
    }

    static String limpiar(String txt) {
        return txt.trim().replace(" ", "").replace("-", "").replace("_", "");
    }

}
