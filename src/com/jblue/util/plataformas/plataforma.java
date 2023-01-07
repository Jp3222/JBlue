/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.util.plataformas;

import com.jblue.util.SoInfo;

/**
 *
 * @author jp
 */
public class plataforma {

    private static final String SO = SoInfo.SO_NOMBRE.toLowerCase();

    public static boolean isWindows() {
        return SO.contains("windows");
    }

    public static boolean isLinux() {
        return SO.contains("linux");
    }

    public static boolean isMac() {
        return SO.contains("mac");
    }

}
