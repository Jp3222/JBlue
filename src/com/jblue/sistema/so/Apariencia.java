/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import com.jbd.Exeption.ExeptionPrinter;
import java.util.Arrays;
import javax.swing.UIManager;

/**
 *
 * @author jp
 */
public class Apariencia implements ExeptionPrinter {

    private final String MET,
            NIM,
            CDE,
            GTK;

    private final String WIN_LOOK = "Windows",
            NIM_LOOK = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    private final String SO_NAME;

    public Apariencia(String SO_NAME) {
        this.SO_NAME = SO_NAME;
        this.MET = "Metal";
        this.GTK = "GTK+";
        this.NIM = "Nimbus";
        this.CDE = "CDE/Motif";

    }

    public void aparienciaPorDefecto() throws Exception {
        UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        String nombre = SO_NAME.toLowerCase();
        if (nombre.contains("linux")) {
            String lookGTK = getLook(installedLookAndFeels, GTK);
            UIManager.setLookAndFeel(lookGTK);
        } else if (nombre.contains("windows")) {
            String lookWIN = getLook(installedLookAndFeels, WIN_LOOK);
            UIManager.setLookAndFeel(lookWIN);
        } else {
            String lookDEF = getLook(installedLookAndFeels, CDE);
            UIManager.setLookAndFeel(lookDEF);
        }
    }

    public String getLook(UIManager.LookAndFeelInfo[] info, String nombre) {
        for (UIManager.LookAndFeelInfo lookAndFeelInfo : info) {
            if (lookAndFeelInfo.getName().equalsIgnoreCase(nombre)
                    || lookAndFeelInfo.getName().contains(nombre)) {
                return lookAndFeelInfo.getClassName();
            }
        }
        return null;
    }

    /**
     * Metodo que evalua si alguna libreria esta instalada
     *
     * @param installedLookAndFeels
     * @return
     */
    public boolean isLibInstalada(UIManager.LookAndFeelInfo[] installedLookAndFeels) {
        return Arrays.stream(installedLookAndFeels)
                .anyMatch((t) -> {
                    return comparador(t, GTK)
                            || comparador(t, NIM)
                            || comparador(t, MET)
                            || comparador(t, CDE);
                });
    }

    /**
     * Metodo para comparar el nombre de un elemento LookAndFeelInfo con el
     * nombre mandado de parametro
     *
     * @param o elemento de tipo LookAndFeelInfo el cual se evaluara su nombre
     * @param t Caena que contiene el nombre de algunos de los LookAndFeelInfo
     * que se supone que esta instalado
     * @return true si el nombre coincide
     */
    public boolean comparador(UIManager.LookAndFeelInfo o, String t) {
        return o.getName().equalsIgnoreCase(t);
    }

}
