/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.envoltorios.env;

import com.jblue.modelo.objetos.OPersonal;
import com.jblue.util.cache.FabricaCache;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public abstract class EnvPersonal {

    public static final String CREACION = "c",
            LECTURA = "r",
            ACTUALIACION = "u",
            ELIMINACION = "d";

    public static OPersonal getPersonal(String txt) {
        ArrayList<OPersonal> lista = FabricaCache.MC_PERSONAL.getLista();
        for (OPersonal i : lista) {
            if (i.getId().equals(txt)) {
                return i;
            }
        }
        return null;
    }

    public static boolean allPermisos(OPersonal o) {
        return o.allPermisos();
    }

    public static String[] getPermisos(OPersonal o) {
        char[] c = o.getPermisos().toCharArray();
        if (c[0] == '1') {
            return null;
        }
        String[] arr = new String[c.length];
        int i = 0;
        for (char d : c) {
            arr[i] = "" + d;
        }
        return arr;
    }

    public static String[] getStrsPermisos(OPersonal o) {
        char[] c = o.getPermisos().toCharArray();
        if (c[0] == '1') {
            return null;
        }
        String[] arr = new String[c.length];
        int i = 0;
        for (char d : c) {
            arr[i] = "" + d;
        }
        return arr;
    }

    public static boolean conPermiso(String permiso, OPersonal o) {
        if (o.allPermisos()) {
            return true;
        }

        return o.getPermisos().contains(permiso);
    }

    private EnvPersonal() {
    }
}
