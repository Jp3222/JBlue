/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.tools;

import com.jblue.util.trash.Operaciones;
import com.jblue.modelo.objetos.OCalles;
import com.jblue.modelo.objetos.OPagosServicio;
import com.jblue.modelo.objetos.OTipoTomas;
import com.jblue.modelo.objetos.OUsuarios;
import com.jblue.util.Filtros;
import com.jblue.modelo.factories.FabricaCache;
import com.jblue.modelo.factories.FabricaOpraciones;
import com.jblue.util.trash.MemoCache;
import com.jblue.util.tiempo.Fecha;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public interface UtilUsuario {

    public static OCalles getCalle(String txt) {
        MemoCache<OCalles> calles = FabricaCache.MC_CALLES;
        ArrayList<OCalles> memoria = calles.getLista();
        return memoria.stream()
                .filter(t -> t.getId().equals(txt) || t.toString().equals(txt))
                .toList()
                .getFirst();

    }

    public static OTipoTomas getTipoToma(String txt) {
        MemoCache<OTipoTomas> tomas = FabricaCache.MC_TIPOS_DE_TOMAS;
        ArrayList<OTipoTomas> memoria = tomas.getLista();
        return memoria.stream()
                .filter(t -> t.getId().equals(txt) || t.toString().equals(txt))
                .findFirst().get();
    }

    public static OUsuarios getUsuario(String txt) {
        ArrayList<OUsuarios> lista = FabricaCache.MC_USUARIOS.getLista();
        OUsuarios aux = null;
        for (OUsuarios i : lista) {
            if (i.getId().equals(txt) || i.toString().equalsIgnoreCase(txt) || i.getCodigo().equals(txt)) {
                aux = i;
                break;
            }
        }
        return aux;
    }

    public static OUsuarios getUsuarioXNombre(String nombre) {
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

    public static boolean isPrimerAño(OUsuarios o) {
        LocalDate x = LocalDate.parse(o.getRegistro(), Fecha.FORMATO);
        LocalDate y = LocalDate.now();
        return x.getYear() == y.getYear();
    }

    private static boolean filtroID(String txt, OUsuarios o) {
        return o.getId().equals(txt) || o.toString().contains(txt);
    }

    private static boolean filtroNombre(String txt, OUsuarios o) {
        String x = Filtros.limpiar(o.toString());
        String y = Filtros.limpiar(txt);
        return x.equals(y) || x.contains(y);
    }

    public static boolean filtros(String txt, OUsuarios o) {
        return filtroID(txt, o) || filtroNombre(txt, o);
    }
}
