/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.bdconexion.env;

import com.jblue.modelo.bdconexion.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.Filtros;
import com.jblue.util.fabricas.FabricaCache;
import com.jblue.util.fabricas.FabricaOpraciones;
import com.jblue.util.modelo.MemoCache;
import com.jblue.util.tiempo.Fecha;
import java.time.LocalDate;
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

    public static OTipoTomas getTipoDeTomaEnCache(String id_usuario) {
        MemoCache<OTipoTomas> tomas = FabricaCache.MC_TIPOS_DE_TOMAS;
        ArrayList<OTipoTomas> memoria = tomas.getLista();
        OTipoTomas o = null;
        for (OTipoTomas i : memoria) {
            if (i.getId().equals(id_usuario) || i.getTipo().equalsIgnoreCase(id_usuario)) {
                o = i;
            }
        }
        return o;
    }

    public static OUsuarios getUsuarioEnCache(String txt) {
        ArrayList<OUsuarios> lista = FabricaCache.MC_USUARIOS.getLista();
        OUsuarios aux = null;
        for (OUsuarios i : lista) {
            if (i.getId().equals(txt) || i.getStringR().equalsIgnoreCase(txt) || i.getCodigo().equals(txt)) {
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
        q.append("nombre = '").append(split[0]).append("' and ");
        q.append("ap = '").append(split[1]).append("' and ");
        q.append("am = '").append(split[2]).append("'");

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

    public static ArrayList<String> getMesesPagadosDelAño(String id) {
        ArrayList<String> lista = new ArrayList<>(12);
        Operaciones<OPagosServicio> op = FabricaOpraciones.getPAGOS_X_SERVICIO();
        LocalDate l = LocalDate.now();
        ArrayList<OPagosServicio> get = op.getLista("año = " + l.getYear() + " and usuario = " + id);
        for (OPagosServicio i : get) {
            lista.add(i.getMesPagado());
        }
        return lista;
    }

    public static String getMesesPagados(String id) {
        int size = getMesesPagados(String.valueOf(Fecha.getNewFechaActual().getYear()), id);
        return String.valueOf(size);
    }

    public static boolean filtroIDExacto(String txt, OUsuarios o) {
        return o.getId().equals(txt);
    }

    public static boolean filtroIDAprox(String txt, OUsuarios o) {
        return o.getId().contains(txt);
    }

    public static boolean filtroContieneNombre(String txt, OUsuarios o) {
        String x = Filtros.limpiar(o.getStringR());
        String y = Filtros.limpiar(txt);
        return x.contains(y);
    }

    public static boolean filtroContieneCodigo(String txt, OUsuarios o) {
        String x = Filtros.limpiar(o.getCodigo());
        String y = Filtros.limpiar(txt);
        return x.contains(y);
    }

    public static boolean filtros(String txt, OUsuarios o) {
        return filtroIDExacto(txt, o) || filtroContieneNombre(txt, o) || filtroContieneCodigo(txt, o);
    }

    public static boolean isPrimerAño(OUsuarios o) {
        LocalDate x = LocalDate.parse(o.getRegistro(), Fecha.FORMATO);
        LocalDate y = LocalDate.now();
        return x.getYear() == y.getYear();
    }
}
