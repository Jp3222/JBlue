/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.modelo.envoltorios.env;

import com.jblue.modelo.objetos.OPersonal;

/**
 *
 * @author jp
 */
public abstract class EnvPersonal {

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

    private EnvPersonal() {
    }
}
