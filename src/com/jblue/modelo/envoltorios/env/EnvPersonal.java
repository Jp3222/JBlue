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

    public static OPersonal getPersonal(String txt) {
        ArrayList<OPersonal> lista = FabricaCache.MC_PERSONAL.getLista();
        for (OPersonal i : lista) {
            if (i.getId().equals(txt) || i.getStringR().contains(txt)) {
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

    public static boolean isAdministrador(OPersonal o) {
        return o.getCargo().equals("5");
    }

    public static boolean isPresidente(OPersonal o) {
        return o.getCargo().equals("4");
    }

    public static boolean isTesorero(OPersonal o) {
        return o.getCargo().equalsIgnoreCase("3");
    }

    public static boolean isSecretario(OPersonal o) {
        return o.getCargo().equals("2");
    }

    public static boolean isPasante(OPersonal o) {
        return o.getCargo().equals("1");
    }

    public static String getCargoString(OPersonal o) {
        return switch (o.getCargo()) {
            case "1":
                yield "Pasante";
            case "2":
                yield "Secretario";
            case "3":
                yield "Tesorero";
            case "4":
                yield "Presidente";
            case "5":
                yield "Administrador";
            default:
                yield "none";
        };
    }

    private EnvPersonal() {
    }
}
