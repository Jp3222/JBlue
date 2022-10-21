/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jblue.vista.conf;

import com.jblue.util.SalidaDeErrores;
import java.util.Arrays;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jp
 */
public class Apariencia implements SalidaDeErrores {

    private final String SO;
    private final String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel",
            WIN = "Windows",
            NIM = "javax.swing.plaf.nimbus.NimbusLookAndFeel";

    public Apariencia(String SO) {
        this.SO = SO;
    }

    public void gtk() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("GTK+".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace(pwError);
                    closeBufferExeption();
                }
            }
        }
    }

    public void aparienciaPorDefecto() {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            boolean libInstalada = isLibInstalada(installedLookAndFeels);
            if (!libInstalada) {
                UIManager.setLookAndFeel(NIM);
            }
            switch (SO.toLowerCase()) {
                case "linux":
                    UIManager.setLookAndFeel(GTK);
                    break;
                case "windows":
                    UIManager.setLookAndFeel(WIN);
                    break;
                case "mac":
                    UIManager.setLookAndFeel(NIM);
                    break;
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            try (pwError) {
                System.out.println("Error al cargar la apariencia del sistema: " + e.getMessage());
                e.printStackTrace(pwError);
                closeBufferExeption();
            }
        }
    }

    public boolean isLibInstalada(UIManager.LookAndFeelInfo[] installedLookAndFeels) {
        boolean anyMatch = Arrays.stream(installedLookAndFeels).anyMatch((t) -> {
            return t.getClassName().equalsIgnoreCase(GTK)
                    || t.getClassName().equalsIgnoreCase(WIN);
        });
        return anyMatch;
    }
}
