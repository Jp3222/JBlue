/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.sistema.so;

import com.jblue.util.SalidaDeErrores;
import java.util.Arrays;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jp
 */
public class Apariencia implements SalidaDeErrores {

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
        switch (SO_NAME.toLowerCase()) {
            case "linux":
                String lookLIN = getLook(installedLookAndFeels, GTK);
                UIManager.setLookAndFeel(lookLIN);
                break;
            case "windows 7":
            case "windows 8":
            case "windows 10":
                String lookWIN = getLook(installedLookAndFeels, GTK);
                UIManager.setLookAndFeel(lookWIN);
            default:
                String lookDEF = getLook(installedLookAndFeels, NIM);
                UIManager.setLookAndFeel(SO_NAME);
        }
    }

    public String getLook(UIManager.LookAndFeelInfo[] info, String nombre) {
        for (UIManager.LookAndFeelInfo lookAndFeelInfo : info) {
            if (lookAndFeelInfo.getName().contains(nombre)) {
                return lookAndFeelInfo.getClassName();
            }
        }
        return null;
    }

    public boolean isLibInstalada(UIManager.LookAndFeelInfo[] installedLookAndFeels) {
        return Arrays.stream(installedLookAndFeels).anyMatch((t) -> {
            return comparador(t, GTK)
                    || comparador(t, NIM)
                    || comparador(t, MET)
                    || comparador(t, CDE);
        });
    }

    public boolean comparador(UIManager.LookAndFeelInfo o, String t) {
        return o.getName().equalsIgnoreCase(t);
    }

}
